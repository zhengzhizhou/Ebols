package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/7/5.
 */

public class OwnerRegisterResponse {
    private int memberId;
    private String[] errorFields;
    private String errorMessage;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String[] getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(String[] errorFields) {
        this.errorFields = errorFields;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
