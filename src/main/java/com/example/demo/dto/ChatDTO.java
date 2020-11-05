package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zoujialiang
 * @date 2020/9/16 10:59
 */
@Data
@ToString
public class ChatDTO implements Serializable {
    private String roomId;
    private Integer type;
    private Long index;
}
