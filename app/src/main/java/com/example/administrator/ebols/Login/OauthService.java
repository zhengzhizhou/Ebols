package com.example.administrator.ebols.Login;

import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface OauthService {
    @POST("/oauth/token")
    Call<AccessResponse> getToken(@Body AccessRequest request);
}
