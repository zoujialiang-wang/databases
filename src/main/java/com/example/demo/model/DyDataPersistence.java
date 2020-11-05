package com.example.demo.model;


import com.example.demo.dto.Chat;
import com.example.demo.dto.HighFrequenctChat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Zoujialiang
 * @date 2020/9/14 17:11
 */
@Data
@ToString
public class DyDataPersistence {
    /**
     * 房间号
     */
    private String roomId;
    /**
     * 观众列表
     */
    private List<DyUserInfo> touristUserInfo;
    /**
     * 粉丝信息
     */
    private List<DyUserInfo> fansUserInfo;
    /**
     * 弹幕列表
     */
    private List<Chat> feedChat;

    /**
     * 高频弹幕集合
     */
    private List<HighFrequenctChat> highChat;
    /**
     * 购物浏览列表
     */
    private List<Chat> shopChat;
    /**
     * 对应时间的人数
     */
    private List<UserCounter> userCounters;
    /**
     * 评论总数
     */
    private Long chatSum;
    /**
     * 购物总数
     */
    private Long shopSum;
    /**
     * 点赞总数
     */
    private Long likeCount;
    /**
     * 峰值数
     */
    private Long maxCount;

    /**
     * 创建时间
     */
    private Date startTime;

    /**
     * 直播人的名称
     */
    private String userName;

    /**
     * 监控人的名称
     */
    private String userPhone;

    /**
     * 时间字符串
     */
    private String startTimeStr;
}
