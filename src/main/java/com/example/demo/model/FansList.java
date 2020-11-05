package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Zoujialiang
 * @date 2020/9/30 10:47
 */
@Data
@ToString
public class FansList {
    private String roomId;
    private List<DyUserInfo> dyUserInfos;
}
