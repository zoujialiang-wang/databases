#!/usr/bin/python
# -*- coding: utf-8 -*-
# 2.7版本
import sys
import base64
import MySQLdb
import json
import time
import urllib2
from binascii import b2a_hex

from Crypto.Cipher import AES

# 查询数据库获取密码-----------------------------------------------------------------
username = str(sys.argv[1])
password = str(sys.argv[2])
localhost = None
if len(sys.argv) > 3:
    localhost = str(sys.argv[3])
else:
    localhost = "localhost"
print "访问的ip地址为：" + localhost
print "输入数据库账号和密码为" + username + "  " + password
db = MySQLdb.connect(
    host=localhost,  # 主机名
    user=username,  # 用户名
    passwd=password,  # 密码
    db="serverrest")  # 数据库名称
cur = db.cursor()
cur.execute("select user_password from user where user_name = 'admin'")
password = cur.fetchone()
cur.close()
db.close()
# 处理密码-----------------------------------------------------------------
oldPassword = base64.b64decode(password[0].encode('utf-8'))
key = 'DPtechDPtechDP00'  # 自己密钥
print("base64解密数据后的密码：" + oldPassword)
BLOCK_SIZE = 16  # Bytes
# 数据进行 PKCS5Padding 的填充
pad = lambda s: (s + (BLOCK_SIZE - len(s) % BLOCK_SIZE) * chr(BLOCK_SIZE - len(s) % BLOCK_SIZE))
raw = pad(str(oldPassword))
# 通过key值，使用ECB模式进行加密
cipher = AES.new(key.encode(), AES.MODE_ECB)
# 得到加密后的字节码
encrypted_text = cipher.encrypt(raw.encode())
# 字节码转换成十六进制  再转成 字符串
newPassword = b2a_hex(encrypted_text).encode()
print("使用aes的ECB模式，pkcs5进行填充加密后的密码：" + newPassword)
# 自动执行接口-----------------------------------------------------------------
login = {
    "userName": "admin",
    "userPassword": "3f7238bed4d24207721c6fff54520376"
}
login_data = json.dumps({
    "userName": "admin",
    "userPassword": "3f7238bed4d24207721c6fff54520376"
})
# 登入------------------------------------------------------------
request = urllib2.Request("https://" + localhost + "/api/login", login_data)
request.add_header('content-type', 'application/json')
request.add_header('Accept', '*/*')
request.get_method = lambda: 'POST'
response = urllib2.urlopen(request)
cookie = response.headers['Set-Cookie']
cookie = cookie.split(';')[0]
print "cookie信息为" + cookie


def GET(url):
    request = urllib2.Request(url);
    request.add_header('Accept', "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    request.add_header('Cookie', cookie)
    request.get_method = lambda: 'GET'
    response = urllib2.urlopen(request).read()
    print response


nowtime = long(int(round(time.time() * 1000)))
lastMouthTime = nowtime - 30 * 24 * 60 * 60 * 1000
nowtime = str(nowtime)
lastMouthTime = str(lastMouthTime)
# 获取攻击事件------------------------------------------------------------
GET("https://" + localhost + "/api/stap/threatTrace/threatTraceOverview?startTime=" + str(
    lastMouthTime) + "&endTime=" + str(nowtime) + "&limit=12&page=1&traceStatus=[%220%22]&needToRet")
# 获取威胁总览------------------------------------------------------------
GET(
    "https://" + localhost + "/api/stap/threatAnalysis/generalquery/generalQueryThreatNameLevel?direction=6&startTime=" + str(
        lastMouthTime) + "&endTime=" + str(nowtime))
GET(
    "https://" + localhost + "/api/stap/threatAnalysis/generalquery/generalQueryThreatIp?direction=5&queryType=0&startTime=" + str(
        lastMouthTime) + "&endTime=" + str(nowtime))
GET(
    "https://" + localhost + "/api/stap/threatAnalysis/generalquery/generalQueryThreatTrend?direction=6&queryType=1&startTime=" + str(
        lastMouthTime) + "&endTime=" + str(nowtime))
# 日志检索------------------------------------------------------------
GET(
    "https://" + localhost + "/api/stap/logCenter/securityLog/securityLogList?startTime=" + str(
        lastMouthTime) + "&endTime=" + str(nowtime) + "&limit=50&page=1&logType=[%222%22]&size=500")
# 资产管理------------------------------------------------------------
GET("https://" + localhost + "/api/stap/assetMonitorCenter/assetManage/assetList?limit=50&page=1")
# 退出登入------------------------------------------------------------
request = urllib2.Request("https://" + localhost + "/api/login/logout")
request.get_method = lambda: 'DELETE'
urllib2.urlopen(request)
