package com.linkmart.dtos;

public class LogisticCompanyDto {
    private int logisticCompanyId;
    private String companyName;
    private String companyUrl;

    public int getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(int logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
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
                "id=" + logisticCompanyId +
                ", companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
