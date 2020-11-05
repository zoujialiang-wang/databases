package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author weed
 * @since 2020-07-10
 */
@Data
public class DyMonitor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近一次直播监控时间
     */
    private Date lastLiveMonitorTime;

    /**
     * 最近一次直播监控的房间id
     */
    private String lastLiveMonitorRoomId;

    /**
     * 最近一次粉丝列表采集时间
     */
    private Date lastFansListCollectTime;

    /**
     * 最近一次作品评论采集时间
     */
    private Date lastProductFeedCollectTime;

    /**
     * 最近一次采集的作品id
     */
    private String lastCollectFeedProductId;

    /**
     * 直播监控:（0不开启，1开启）
     */
    private Integer isLiveMonitor;

    /**
     * 作品监控（0不开启，1开启）
     */
    private Integer isProductMonitor;

    /**
     * 粉丝列表抓取（0不开启，1开启）
     */
    private Integer isFansListMonitor;

    /**
     * 抓取的紧急级别（越大界别越高）
     */
    private Integer level;


}
