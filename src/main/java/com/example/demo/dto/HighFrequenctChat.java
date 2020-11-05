package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zoujialiang
 * @date 2020/9/14 14:22
 */
@Data
@ToString
public class HighFrequenctChat implements Serializable {
    /**
     * 弹幕名称
     */
    private String name;
    /**
     * 弹幕权重
     */
    private Long value;
}
