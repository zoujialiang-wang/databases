package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Zoujialiang
 * @date 2020/9/29 15:03
 */
@Data
@ToString
public class ThreadDTO {
    /**
     * 线程名字
     */
    private String threadName;
    /**
     * 线程状态
     */
    private String getState;
    /**
     * 是否存活
     */
    private Boolean isAlive;
    /**
     * 是否为守护线程
     */
    private Boolean isDaemon;
    /**
     * 是否被打断
     */
    private Boolean isInterrupted;
}
