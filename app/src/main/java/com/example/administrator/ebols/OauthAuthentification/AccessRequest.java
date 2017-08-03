package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/6/8.
 */

public class AccessRequest {
    private String client_id = Constant.client_id;
    private String client_secret = Constant.client_secret;
    private String username;
    private String password;
    private String grant_type = Constant.grant_type;
    private String refresh_token;
    public AccessRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public AccessRequest(String refresh_token){
        this.refresh_token=refresh_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }
}
