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
 * @since 2020-07-13
 */
@Data
public class DyMonitorRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 监控类型（0-直播间，1-粉丝列表，2-作品）
     */
    private Integer type;

    /**
     * 房间id
     */
    private String roomId;

    /**
     * 作品id
     */
    private String productId;

    /**
     * 查询总数
     */
    private Long totalCount;

    /**
     * 完成数
     */
    private Long finishCount;

    /**
     * 是否结束
     */
    private Integer end;


}
