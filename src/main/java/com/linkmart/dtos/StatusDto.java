package com.linkmart.dtos;

public class StatusDto {
    private int StatusId;

    private String statusName;

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "StatusDto{" +
                "StatusId=" + StatusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
