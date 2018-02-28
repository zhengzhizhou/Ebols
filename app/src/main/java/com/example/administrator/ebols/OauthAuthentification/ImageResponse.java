package com.example.administrator.ebols.OauthAuthentification;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23.
 */

public class ImageResponse {
    private Map<String, String> item;
    private String messageError;

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
