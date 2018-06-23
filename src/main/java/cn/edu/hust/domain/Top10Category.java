package cn.edu.hust.domain;

public class Top10Category {
    private Long taskId;
    private Long categoryId;
    private Long clickCount;
    private Long orderCount;
    private Long payCount;


    public Top10Category() {
    }

    public void set(Long taskId, Long categoryId, Long clickCount, Long orderCount, Long payCount) {
        this.taskId = taskId;
        this.categoryId = categoryId;
        this.clickCount = clickCount;
        this.orderCount = orderCount;
        this.payCount = payCount;
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

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getPayCount() {
        return payCount;
    }

    public void setPayCount(Long payCount) {
        this.payCount = payCount;
    }
}
