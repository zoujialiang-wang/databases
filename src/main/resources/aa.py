# 3.5版本

import json
import datetime

import pymysql
import base64
from Crypto.Cipher import AES
from binascii import b2a_hex, a2b_hex
import urllib3

nowtime = datetime.datetime.now().timestamp() * 1000
lastMouthTime = nowtime - 30 * 24 * 60 * 60 * 1000

# 查询数据库获取密码-----------------------------------------------------------------
conn = pymysql.connect(host='10.121.201.122', port=3306, user='root', passwd='DptechROOT@123$%^', db='serverrest',
                       charset='utf8')
# 使用cursor()方法获取操作游标 根据用户名获取密码
cursor = conn.cursor()
cursor.execute("select user_password from user where user_name = 'admin'")
for line in cursor.fetchall():
    password = line[0]
    print("数据库中的密码：" + password)
cursor.close()
conn.close()
# 处理密码-----------------------------------------------------------------
oldPassword = base64.b64decode(password.encode('utf-8'))
key = 'DPtechDPtechDP00'  # 自己密钥
newPassword = str(oldPassword)[-len(oldPassword) - 1:-1]
print("base64解密数据后的密码：" + newPassword)
# 3f7
BLOCK_SIZE = 16  # Bytes
# 数据进行 PKCS5Padding 的填充
pad = lambda s: (s + (BLOCK_SIZE - len(s) % BLOCK_SIZE) * chr(BLOCK_SIZE - len(s) % BLOCK_SIZE))
raw = pad(str(newPassword))
# 通过key值，使用ECB模式进行加密
cipher = AES.new(key.encode(), AES.MODE_ECB)
# 得到加密后的字节码
encrypted_text = cipher.encrypt(bytes(raw, encoding='utf-8'))
# 字节码转换成十六进制  再转成 字符串
newPassword = str(b2a_hex(encrypted_text), encoding='utf-8')
print("使用aes的ECB模式，pkcs5进行填充加密后的密码：" + newPassword)
# 自动执行接口-----------------------------------------------------------------
login = {
    "username": "admin",
    "password": newPassword,
}
headers = {'Accept': 'application/json',
           'content-type': 'application/json;charset=UTF-8'
           }
login_data = json.dumps(login).encode("utf-8")
http = urllib3.PoolManager()
# 登入------------------------------------------------------------
r = http.request("POST", "http://localhost:8080/login", body=login_data, headers=headers)
if r.status == 200:
    headers = r.headers
    if len(headers) > 0:
        cookie = headers['Set-Cookie']
        print(cookie)

getHeaders = {'Accept': "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
              'Cookie': 'shiroCookie=' + cookie
              }
# 获取攻击事件------------------------------------------------------------
r = http.request("GET",
                 "http://localhost:8080/stap/threatTrace/threatTraceOverview?startTime=" + lastMouthTime + "&endTime=" + nowtime + "&limit=12&page=1&traceStatus=[%220%22]&needToRet",
                 headers=headers)
print(str(r.data, encoding="utf-8"))
# 获取威胁总览------------------------------------------------------------
threat1 = http.request("GET",
                       "http://localhost:8080/stap/threatAnalysis/generalquery/generalQueryThreatNameLevel?direction=6&startTime=" + lastMouthTime + "&endTime=" + nowtime,
                       headers=headers)
threat2 = http.request("GET",
                       "http://localhost:8080/stap/threatAnalysis/generalquery/generalQueryThreatType?direction=5&queryType=1startTime=" + lastMouthTime + "&endTime=" + nowtime,
                       headers=headers)
threat3 = http.request("GET",
                       "http://localhost:8080/stap/threatAnalysis/generalquery/generalQueryThreatIp?direction=5&queryType=0&startTime=" + lastMouthTime + "&endTime=" + nowtime,
                       headers=headers)
threat4 = http.request("GET",
                       "http://localhost:8080/stap/threatAnalysis/generalquery/generalQueryThreatTrend?direction=6&queryType=1&startTime=" + lastMouthTime + "&endTime=" + nowtime,
                       headers=headers)
print(str(threat1.data, encoding="utf-8") + '\n' + str(threat2.data, encoding="utf-8") + '\n' + str(threat3.data,encoding="utf-8") + '\n' + str(threat4.data, encoding="utf-8"))
# 日志检索------------------------------------------------------------
log = http.request("GET",
                   "http://localhost:8080/stap/logCenter/securityLog/securityLogList?startTime=" + lastMouthTime + "&endTime=" + nowtime + "&limit=50&page=1&logType=[%222%22]&size=500",
                   headers=headers)
print(str(log.data, encoding="utf-8"))
# 资产管理------------------------------------------------------------
asset = http.request("GET",
             "http://localhost:8080/stap/assetMonitorCenter/assetManage/assetList?limit=50&page=1",
             headers=headers)
print(str(asset.data, encoding="utf-8"))
# 退出登入------------------------------------------------------------
http.request("DELETE","http://localhost:8080/login/logout")