package com.linkmart.dtos;

public class LogisticCompanyDto {
    private int id;
    private String companyName;
    private String companyUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    @Override
    public String toString() {
        return "LogisticCompanyDto{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
