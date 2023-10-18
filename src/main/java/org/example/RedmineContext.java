package org.example;

import java.util.Date;

public class RedmineContext {
    private int week;
    private int statusId;
    private Date start;
    private Date end;

    public RedmineContext(int week, int statusId, Date start) {
        this.week = week;
        this.statusId = statusId;
        this.start = start;
        this.end = new Date(start.getYear(), start.getMonth(), start.getDate()+5);
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
