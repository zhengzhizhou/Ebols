package com.example.administrator.ebols.OauthAuthentification;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */

public class UploadResponse {
    private Map<String, Object> item;
    private List<String> errorFields;
    private String errorMessage;

    public List<String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(List<String> errorFields) {
        this.errorFields = errorFields;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> getItem() {
        return item;
    }

    public void setItem(Map<String, Object> item) {
        this.item = item;
    }
}
