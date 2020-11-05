package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DyCounter implements Serializable {


    /**
     * 点心
     */
    private Item like;

    /**
     * 弹幕数
     */
    private Item chats;

    /**
     * 商品浏览数
     */
    private Item vistGoods;

    /**
     * 人数峰值要
     */
    private Item maxCount;


}
