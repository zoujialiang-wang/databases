package com.example.demo.dto;

import java.io.Serializable;

/**
 * @Description 作品的评论
 * @Author weed
 * @Address 江干区 迈达商业中心
 * @Date 2020/7/7 0007 17:22
 * @Version
 */
public class ProductFeed implements Serializable {
    private String productId;

    private long time;

    private String content;
}
