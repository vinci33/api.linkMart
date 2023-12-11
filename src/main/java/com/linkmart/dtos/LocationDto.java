package com.linkmart.dtos;

public class LocationDto {

    private int id;
    private String locationName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "LocationDto{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
