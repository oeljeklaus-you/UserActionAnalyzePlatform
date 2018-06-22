package cn.edu.hust.domain;

import java.io.Serializable;

public class SessionAggrStat implements Serializable {
    private Long taskId;
    private Long SessionCount;
    private double visit_Length_1s_3s;
    private double visit_Length_4s_6s;
    private double visit_Length_7s_9s;
    private double visit_Length_10s_30s;
    private double visit_Length_30s_60s;
    private double visit_Length_1m_3m;
    private double visit_Length_3m_10m;
    private double visit_Length_10m_30m;
    private double visit_Length_30m;
    private double step_Length_1_3;
    private double step_Length_4_6;
    private double step_Length_7_9;
    private double step_Length_10_30;
    private double step_Length_30_60;
    private double step_Length_60;

    public SessionAggrStat() {
    }

    public void set(Long taskId, Long sessionCount, double visit_Length_1s_3s, double visit_Length_4s_6s, double visit_Length_7s_9s, double visit_Length_10s_30s, double visit_Length_30s_60s, double visit_Length_1m_3m, double visit_Length_3m_10m, double visit_Length_10m_30m, double visit_Length_30m, double step_Length_1_3, double step_Length_4_6, double step_Length_7_9, double step_Length_10_30, double step_Length_30_60, double step_Length_60) {
        this.taskId = taskId;
        SessionCount = sessionCount;
        this.visit_Length_1s_3s = visit_Length_1s_3s;
        this.visit_Length_4s_6s = visit_Length_4s_6s;
        this.visit_Length_7s_9s = visit_Length_7s_9s;
        this.visit_Length_10s_30s = visit_Length_10s_30s;
        this.visit_Length_30s_60s = visit_Length_30s_60s;
        this.visit_Length_1m_3m = visit_Length_1m_3m;
        this.visit_Length_3m_10m = visit_Length_3m_10m;
        this.visit_Length_10m_30m = visit_Length_10m_30m;
        this.visit_Length_30m = visit_Length_30m;
        this.step_Length_1_3 = step_Length_1_3;
        this.step_Length_4_6 = step_Length_4_6;
        this.step_Length_7_9 = step_Length_7_9;
        this.step_Length_10_30 = step_Length_10_30;
        this.step_Length_30_60 = step_Length_30_60;
        this.step_Length_60 = step_Length_60;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getSessionCount() {
        return SessionCount;
    }

    public void setSessionCount(Long sessionCount) {
        SessionCount = sessionCount;
    }

    public double getVisit_Length_1s_3s() {
        return visit_Length_1s_3s;
    }

    public void setVisit_Length_1s_3s(double visit_Length_1s_3s) {
        this.visit_Length_1s_3s = visit_Length_1s_3s;
    }

    public double getVisit_Length_4s_6s() {
        return visit_Length_4s_6s;
    }

    public void setVisit_Length_4s_6s(double visit_Length_4s_6s) {
        this.visit_Length_4s_6s = visit_Length_4s_6s;
    }

    public double getVisit_Length_7s_9s() {
        return visit_Length_7s_9s;
    }

    public void setVisit_Length_7s_9s(double visit_Length_7s_9s) {
        this.visit_Length_7s_9s = visit_Length_7s_9s;
    }

    public double getVisit_Length_10s_30s() {
        return visit_Length_10s_30s;
    }

    public void setVisit_Length_10s_30s(double visit_Length_10s_30s) {
        this.visit_Length_10s_30s = visit_Length_10s_30s;
    }

    public double getVisit_Length_30s_60s() {
        return visit_Length_30s_60s;
    }

    public void setVisit_Length_30s_60s(double visit_Length_30s_60s) {
        this.visit_Length_30s_60s = visit_Length_30s_60s;
    }

    public double getVisit_Length_1m_3m() {
        return visit_Length_1m_3m;
    }

    public void setVisit_Length_1m_3m(double visit_Length_1m_3m) {
        this.visit_Length_1m_3m = visit_Length_1m_3m;
    }

    public double getVisit_Length_3m_10m() {
        return visit_Length_3m_10m;
    }

    public void setVisit_Legth_3m_10m(double visit_Legth_3m_10m) {
        this.visit_Length_3m_10m = visit_Legth_3m_10m;
    }

    public double getVisit_Length_10m_30m() {
        return visit_Length_10m_30m;
    }

    public void setVisit_Length_10m_30m(double visit_Length_10m_30m) {
        this.visit_Length_10m_30m = visit_Length_10m_30m;
    }

    public double getVisit_Length_30m() {
        return visit_Length_30m;
    }

    public void setVisit_Length_30m(double visit_Length_30m) {
        this.visit_Length_30m = visit_Length_30m;
    }

    public double getStep_Length_1_3() {
        return step_Length_1_3;
    }

    public void setStep_Length_1_3(double step_Length_1_3) {
        this.step_Length_1_3 = step_Length_1_3;
    }

    public double getStep_Length_4_6() {
        return step_Length_4_6;
    }

    public void setStep_Length_4_6(double step_Length_4_6) {
        this.step_Length_4_6 = step_Length_4_6;
    }

    public double getStep_Length_7_9() {
        return step_Length_7_9;
    }

    public void setStep_Length_7_9(double step_Length_7_9) {
        this.step_Length_7_9 = step_Length_7_9;
    }

    public double getStep_Length_10_30() {
        return step_Length_10_30;
    }

    public void setStep_Length_10_30(double step_Length_10_30) {
        this.step_Length_10_30 = step_Length_10_30;
    }

    public double getStep_Length_30_60() {
        return step_Length_30_60;
    }

    public void setStep_Length_30_60(double step_Length_30_60) {
        this.step_Length_30_60 = step_Length_30_60;
    }

    public double getStep_Length_60() {
        return step_Length_60;
    }

    public void setStep_Length_60(double step_Length_60) {
        this.step_Length_60 = step_Length_60;
    }
}
