package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/6/15.
 */

public class UserRequest {
    String token_type;
    String access_type;
    String refresh_token;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getAccess_type() {
        return access_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }
}