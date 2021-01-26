package com.example.demo.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Package com.example.demo.shell
 * @auther ZouJiaLiang
 * @data 2021/1/22 上午11:40
 */
public class test {
    //free | grep Mem | awk '{printf \"%f\\n\",$3*100/$2}'
    public static void main(String[] args) {
        Map<String, Object> reportData = new HashMap<>();
        String memoryFree = callShell("free | grep Mem | awk '{print $4}'").message;
        String memoryUsed = callShell("free | grep Mem | awk '{print $3}'").message;
        String memoryAll = callShell("free | grep Mem | awk '{print $2}'").message;
        double memoryPercent = new BigDecimal(memoryUsed).divide(new BigDecimal(memoryAll), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String cpuSpeed = callShell("cat /proc/cpuinfo|grep cpu\\ MHz | awk 'NR==1{print $4}'").message;
        String cpuPercent = callShell("top -b -n 1 | awk '/^(%)?Cpu/{t1=$2+$4}/^CPU/{t2=$2+$4}END{print t1+t2}'").message;
        String diskUsed = callShell("df -hm | awk 'NR!=1{print $3}' | awk '{sum+=$1}END{print sum}'").message;
        String distTotal = callShell("df -hm | awk 'NR!=1{print $4}' | awk '{sum+=$1}END{print sum}'").message;
        double diskPercent = new BigDecimal(diskUsed).divide(new BigDecimal(distTotal), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double recv = new BigDecimal(callShell("cat /proc/net/dev | grep enp | awk '{sum+=$3}END{print sum}'").message).divide(new BigDecimal(callShell("cat /proc/net/dev | grep enp | awk '{sum+=$2}END{print sum}'").message), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double send = new BigDecimal(callShell("cat /proc/net/dev | grep enp | awk '{sum+=$11}END{print sum}'").message).divide(new BigDecimal(callShell("cat /proc/net/dev | grep enp | awk '{sum+=$10}END{print sum}'").message), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        reportData.put("ssaRunState", 1);
        reportData.put("updateTime", System.currentTimeMillis());
        reportData.put("memoryUsed", memoryUsed + "kb");
        reportData.put("memoryFree", memoryFree + "kb");
        reportData.put("memoryPercent", memoryPercent);
        //CPU速率
        reportData.put("cpuSpeed", cpuSpeed + "MHz");
        reportData.put("cpuPercent", cpuPercent);
        //硬盘使用量
        reportData.put("diskUsed", diskUsed + "Mb");
        //硬盘总存储
        reportData.put("distTotal", distTotal + "Mb");
        reportData.put("diskPercent", diskPercent);
        //网络接收速率
        reportData.put("recv", recv);
        //网络发出速率
        reportData.put("send", send);
        System.out.println("运行状态上报的数据为" + reportData);

    }


    // 调用shell
    public static ShellResult callShell(String commend) {
        ShellResult shellResult = new ShellResult();
        int exitCode = -1;
        StringBuilder message = new StringBuilder();
        String[] cmd = {"/bin/bash", "-c", commend};
        BufferedReader in = null;
        try {
            ProcessBuilder ps = new ProcessBuilder(cmd);
            ps.redirectErrorStream(true);

            Process pr = ps.start();

            in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                // System.out.println("#ShellMessage# " + line);
                message.append(line).append("\n");
            }
            exitCode = pr.waitFor();
            in.close();
        } catch (Throwable e) {
            shellResult.isSuccess = false;
            shellResult.message = e.getMessage();
            return shellResult;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        shellResult.isSuccess = (exitCode == 0 ? true : false);
        if (message.length() > 0) {
            shellResult.message = message.substring(0, message.length() - 1);
        } else {
            shellResult.message = message.toString();
        }
        return shellResult;
    }

    public static class ShellResult {
        public boolean isSuccess;
        public String message;
    }

}
