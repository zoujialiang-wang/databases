package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-12-10
 */
public class RunningState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 安监测运行状态 0：未运行，1 正常运行
     */
    @TableField("ssaRunState")
    private Integer  ssaRunState;

    /**
     * 修改时间

     */
    @TableField("updateTime")
    private String updateTime;

    /**
     * 内存占用大小（带单位）
     */
    @TableField("memoryUsed")
    private String memoryUsed;

    /**
     * 内存空闲大小 （带单位）
     */
    @TableField("memoryFree")
    private String memoryFree;

    /**
     * 内存使用率
     */
    @TableField("memoryPercent")
    private String memoryPercent;

    /**
     * Cpu 速度 （带单位）
     */
    @TableField("cpuSpeed")
    private String cpuSpeed;

    /**
     * Cpu 使用率
     */
    @TableField("cpuPercent")
    private String cpuPercent;

    /**
     * 磁盘使用大小（带单位）
     */
    @TableField("diskUsed")
    private String diskUsed;

    /**
     * 磁盘总大小（带单位）
     */
    @TableField("diskTotal")
    private String diskTotal;

    /**
     * 磁盘使用率
     */
    @TableField("diskPercent")
    private String diskPercent;

    /**
     * 网络接收流量速率（带单位）
     */
    private String recv;

    /**
     * 网络发送流量速率（带单位）
     */
    private String send;

    public Integer getSsaRunState() {
        return  ssaRunState;
    }

    public void setSsaRunState(Integer  ssaRunState) {
        this. ssaRunState =  ssaRunState;
    }
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(String memoryUsed) {
        this.memoryUsed = memoryUsed;
    }
    public String getMemoryFree() {
        return memoryFree;
    }

    public void setMemoryFree(String memoryFree) {
        this.memoryFree = memoryFree;
    }
    public String getMemoryPercent() {
        return memoryPercent;
    }

    public void setMemoryPercent(String memoryPercent) {
        this.memoryPercent = memoryPercent;
    }
    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }
    public String getCpuPercent() {
        return cpuPercent;
    }

    public void setCpuPercent(String cpuPercent) {
        this.cpuPercent = cpuPercent;
    }
    public String getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(String diskUsed) {
        this.diskUsed = diskUsed;
    }
    public String getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(String diskTotal) {
        this.diskTotal = diskTotal;
    }
    public String getDiskPercent() {
        return diskPercent;
    }

    public void setDiskPercent(String diskPercent) {
        this.diskPercent = diskPercent;
    }
    public String getRecv() {
        return recv;
    }

    public void setRecv(String recv) {
        this.recv = recv;
    }
    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "RunningState{" +
            " ssaRunState=" + ssaRunState +
            ", updateTime=" + updateTime +
            ", memoryUsed=" + memoryUsed +
            ", memoryFree=" + memoryFree +
            ", memoryPercent=" + memoryPercent +
            ", cpuSpeed=" + cpuSpeed +
            ", cpuPercent=" + cpuPercent +
            ", diskUsed=" + diskUsed +
            ", diskTotal=" + diskTotal +
            ", diskPercent=" + diskPercent +
            ", recv=" + recv +
            ", send=" + send +
        "}";
    }
}
