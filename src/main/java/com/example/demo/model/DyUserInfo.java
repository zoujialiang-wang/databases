package com.example.demo.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author weed
 * @since 2020-07-10
 */
@Data
@ToString
public class DyUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户id - 纯数字
     */
    private String userId;

    /**
     * 抖音id - 可用户自定义的
     */

    private String dyId;

    /**
     * 特殊的id-在获取粉丝的列表中用到
     */
    private String secUserId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 粉丝数
     */
    private Long fansCount;

    /**
     * 平台粉丝数(可能没有)
     */
    private Long platformFansCount;

    /**
     * 关注数
     */
    private Long followCount;

    /**
     * 获赞数
     */
    private Long winPraise;

    /**
     * 作品数
     */
    private Integer productCount;

    /**
     * 动态数
     */
    private Integer dynamicCount;

    /**
     * 收藏作品数（点赞）
     */
    private Integer favoritingCount;

    /**
     * 转发
     */
    private Integer forwardCount;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 地址
     */
    private String location;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 国家
     */
    private String country;

    /**
     * 是否活跃用户
     */
    private Integer isActiveUser;

    /**
     * 是否是官媒认证,0:未认证1：官媒认证
     */
    private Integer verificationType;

    /**
     * 认证描述
     */
    private String enterpriseVerifyReason;

    /**
     * 性别(0-男，1-女，2-未设置)
     */
    private Integer gender;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 来源（0-直播，1-作品评论，2-粉丝列表）
     */
    private Integer source;

    /**
     * 消费等级图标的URL
     */
    private String consumeUrl;

    /**
     * 粉丝等级图标的URL
     */
    private String fansUrl;

    /**
     * 是否点赞
     */
    private String isLike;

    /**
     * 是否送礼物了
     */
    private String isGift;

    /**
     * 是否评论
     */
    private String isComment;

    /**
     * 类别
     */
    private Integer type;
}
