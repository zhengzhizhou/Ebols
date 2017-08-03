package com.example.administrator.ebols.OauthAuthentification;

/**
 * Created by Administrator on 2017/6/8.
 */

public class AccessResponse {
    private String access_token;
    private String token_type;
    private String expire_in;
    private String refresh_token;

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpire_in() {
        return expire_in;
    }
}
