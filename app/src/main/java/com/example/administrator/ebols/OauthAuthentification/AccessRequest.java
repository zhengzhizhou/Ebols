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
    public AccessRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
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
