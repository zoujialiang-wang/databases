package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCounter {

    private Long time;
    private Integer userCount;
    private String timeStr;

}
