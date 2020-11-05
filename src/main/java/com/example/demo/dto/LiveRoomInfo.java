package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveRoomInfo implements Serializable {

    private String roomId;
    private String userId;

}
