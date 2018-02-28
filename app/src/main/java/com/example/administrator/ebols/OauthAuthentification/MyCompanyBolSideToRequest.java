package com.example.administrator.ebols.OauthAuthentification;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/11.
 */

public class MyCompanyBolSideToRequest {
    private String date;
    private Map<String, Object> customer;
    private String note;
    private Map<String, Object> customerSignature;
    private Map<String, Object> driverSignature;

    public Map<String, Object> getCustomerSignature() {
        return customerSignature;
    }

    public void setCustomerSignature(Map<String, Object> customerSignature) {
        this.customerSignature = customerSignature;
    }

    public Map<String, Object> getDriverSignature() {
        return driverSignature;
    }

    public void setDriverSignature(Map<String, Object> driverSignature) {
        this.driverSignature = driverSignature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<String, Object> getCustomer() {
        return customer;
    }

    public void setCustomer(Map<String, Object> customer) {
        this.customer = customer;
    }
}
