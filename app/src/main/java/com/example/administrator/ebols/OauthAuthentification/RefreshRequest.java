package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/6/30.
 */

public class RefreshRequest {
    private String refresh_token;
    private String grant_type=Constant.grant_type_refresh;
    private String client_id=Constant.client_id;
    private String client_secret=Constant.client_secret;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
