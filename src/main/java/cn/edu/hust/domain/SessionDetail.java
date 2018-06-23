package cn.edu.hust.domain;

import java.io.Serializable;

public class SessionDetail implements Serializable{
    private Long taskId;
    private Long userId;
    private String sessinId;
    private Long pageid;
    private String actionTime;
    private String searchKeyWord;
    private Long clickCategoryId;
    private Long clickProductId;
    private String orderCategoryIds;
    private String orderProductIds;
    private String payCategoryIds;
    private String payProductIds;

    public SessionDetail() {
    }

    public void set(Long taskId, Long userId, String sessinId, Long pageid, String actionTime, String searchKeyWord, Long clickCategoryId, Long clickProductId, String orderCategoryIds, String orderProductIds, String payCategoryIds, String payProductIds) {
        this.taskId = taskId;
        this.userId = userId;
        this.sessinId = sessinId;
        this.pageid = pageid;
        this.actionTime = actionTime;
        this.searchKeyWord = searchKeyWord;
        this.clickCategoryId = clickCategoryId;
        this.clickProductId = clickProductId;
        this.orderCategoryIds = orderCategoryIds;
        this.orderProductIds = orderProductIds;
        this.payCategoryIds = payCategoryIds;
        this.payProductIds = payProductIds;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSessinId() {
        return sessinId;
    }

    public void setSessinId(String sessinId) {
        this.sessinId = sessinId;
    }

    public Long getPageid() {
        return pageid;
    }

    public void setPageid(Long pageid) {
        this.pageid = pageid;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public Long getClickCategoryId() {
        return clickCategoryId;
    }

    public void setClickCategoryId(Long clickCategoryId) {
        this.clickCategoryId = clickCategoryId;
    }

    public Long getClickProductId() {
        return clickProductId;
    }

    public void setClickProductId(Long clickProductId) {
        this.clickProductId = clickProductId;
    }

    public String getOrderCategoryIds() {
        return orderCategoryIds;
    }

    public void setOrderCategoryIds(String orderCategoryIds) {
        this.orderCategoryIds = orderCategoryIds;
    }

    public String getOrderProductIds() {
        return orderProductIds;
    }

    public void setOrderProductIds(String orderProductIds) {
        this.orderProductIds = orderProductIds;
    }

    public String getPayCategoryIds() {
        return payCategoryIds;
    }

    public void setPayCategoryIds(String payCategoryIds) {
        this.payCategoryIds = payCategoryIds;
    }

    public String getPayProductIds() {
        return payProductIds;
    }

    public void setPayProductIds(String payProductIds) {
        this.payProductIds = payProductIds;
    }
}
