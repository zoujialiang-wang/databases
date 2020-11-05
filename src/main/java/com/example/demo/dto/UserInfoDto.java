package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zoujialiang
 * @date 2020/9/15 10:58
 */
@Data
@ToString
public class UserInfoDto implements Serializable {
    /**
     * 总人数
     */
    private Integer headCount;

    /**
     * 评论人数
     */
    private Integer chatCount;

    /**
     * 礼物总数
     */
    private Integer giftCount;

    /**
     * 点赞总数
     */
    private Integer likeCount;
}
