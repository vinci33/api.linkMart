package com.linkmart.forms;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UpdateOrderForm {
    private Integer logisticCompanyId;
    private String shippingOrderNo;
    private List<MultipartFile> file;

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }

    public Integer getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(Integer logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }
}
