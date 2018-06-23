package cn.edu.hust.domain;

import java.io.Serializable;

public class SessionRandomExtract implements Serializable {
    private Long taskId;
    private String sessionId;
    private String startTime;
    private String searchKeyWords;
    private String click_category_ids;

    public SessionRandomExtract() {
    }

    public void set(Long taskId, String sessionId, String startTime, String searchKeyWords, String click_category_ids) {
        this.taskId = taskId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.searchKeyWords = searchKeyWords;
        this.click_category_ids = click_category_ids;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSearchKeyWords() {
        return searchKeyWords;
    }

    public void setSearchKeyWords(String searchKeyWords) {
        this.searchKeyWords = searchKeyWords;
    }

    public String getClick_category_ids() {
        return click_category_ids;
    }

    public void setClick_category_ids(String click_category_ids) {
        this.click_category_ids = click_category_ids;
    }
}
