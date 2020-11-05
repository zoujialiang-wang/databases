package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 */
@Data
public class Chat implements Serializable {

    private String content;

    private Long dyShortId;

    private String dyId;

    private String nickName;

    private Date createTime;

    private String createTimeStr;
    /**
     * 评论的索引位置
     */
    private Long index;

    /**
     * 1,正常说话，2购买，3送豆，4分xiang
     */
    private int type;


}
