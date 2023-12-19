package com.linkmart.forms;

public class LogisticCompanyForm {
    private String companyName;
    private String companyUrl;

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
        return "LogisticCompanyForm{" +
                "companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                '}';
    }
}
