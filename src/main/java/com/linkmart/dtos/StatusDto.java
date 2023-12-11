package com.linkmart.dtos;

public class StatusDto {
    private int id;

    private String statusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
