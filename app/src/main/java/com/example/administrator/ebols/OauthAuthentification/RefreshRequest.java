package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/6/30.
 */

public class RefreshRequest {
    private String refresh_token;
    private String grant_type=Constant.grant_type_refresh;
    private String client_id=Constant.client_id;
    private String client_secret=Constant.client_secret;
    public RefreshRequest(String refresh_token){
        this.refresh_token = refresh_token;
    }
}
