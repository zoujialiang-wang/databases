package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Zoujialiang
 * @date 2020/9/30 11:01
 */
@Data
@ToString
public class UserCounterList {
    private String roomId;
    private List<UserCounter> userCounters;
}
