package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DyMessage implements Serializable {

    private byte[] response;

    private LiveRoomInfo liveRoomInfo;
}
