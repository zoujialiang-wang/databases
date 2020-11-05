package com.example.demo.model;


import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author weed
 * @since 2020-07-10
 */
public class DyRelatedUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 监控用户id
     */
    private String monitorUserId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 直播间出现次数
     */
    private Integer liveCount;

    /**
     * 作品评论中出现的次数
     */
    private Integer productCount;

    /**
     * 0-直播间，1-粉丝列表，2-作品
     */
    private Integer firstSource;

    /**
     * 是否是粉丝（0-不是，1-是）
     */
    private Integer fans;

    /**
     * 粉丝等级
     */
    private Integer fansLevel;

    /**
     * 粉丝牌图标
     */
    private String icon;

    /**
     * 粉丝团名称
     */
    private String fansClubName;

    /**
     * 历史监控作品id
     */
    private String historyMonitorProductId;

    /**
     * 历史监控直播间id
     */
    private String historyMonitorRoomId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(String monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getFirstSource() {
        return firstSource;
    }

    public void setFirstSource(Integer firstSource) {
        this.firstSource = firstSource;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getFansLevel() {
        return fansLevel;
    }

    public void setFansLevel(Integer fansLevel) {
        this.fansLevel = fansLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFansClubName() {
        return fansClubName;
    }

    public void setFansClubName(String fansClubName) {
        this.fansClubName = fansClubName;
    }

    public String getHistoryMonitorProductId() {
        return historyMonitorProductId;
    }

    public void setHistoryMonitorProductId(String historyMonitorProductId) {
        this.historyMonitorProductId = historyMonitorProductId;
    }

    public String getHistoryMonitorRoomId() {
        return historyMonitorRoomId;
    }

    public void setHistoryMonitorRoomId(String historyMonitorRoomId) {
        this.historyMonitorRoomId = historyMonitorRoomId;
    }

    @Override
    public String toString() {
        return "DyRelatedUser{" + "id=" + id + ", monitorUserId=" + monitorUserId + ", userId=" + userId + ", liveCount=" + liveCount + ", productCount=" + productCount + ", firstSource=" + firstSource + ", fans=" + fans + ", fansLevel=" + fansLevel + ", icon=" + icon + ", fansClubName=" + fansClubName + ", historyMonitorProductId=" + historyMonitorProductId + ", historyMonitorRoomId=" + historyMonitorRoomId + "}";
    }
}
