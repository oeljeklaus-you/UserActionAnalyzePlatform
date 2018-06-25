package cn.edu.hust.domain;

import java.io.Serializable;

public class Top10CategorySession implements Serializable{
    private Long taskId;
    private Long categoryId;
    private String sessionId;
    private Long clickCount;

    public Top10CategorySession() {
    }

    public void set(Long taskId, Long categoryId, String sessionId, Long clickCount) {
        this.taskId = taskId;
        this.categoryId = categoryId;
        this.sessionId = sessionId;
        this.clickCount = clickCount;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }
}
