package com.example.administrator.ebols.Login;

import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterRequest;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterResponse;
import com.example.administrator.ebols.OauthAuthentification.RefreshRequest;
import com.example.administrator.ebols.OauthAuthentification.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface OauthService {
    @POST("/oauth/token")
    Call<AccessResponse> getToken(@Body AccessRequest request);

    @POST("/oauth/token")
    Call<AccessResponse> refreshToken(@Body RefreshRequest request);

    @Headers("Content-type: application/json")
    @GET("/api/v1/session")
    Call<UserResponse> getSession(@Header("Authorization") String token);

    @POST("/api/v1/register/company")
    Call<OwnerRegisterResponse> ownerRegister(@Body OwnerRegisterRequest registerRequest);

    @Headers("Content-type: application/json")
    @GET("/api/v1/company/:{companyId}/order/list-statistic")
    Call<OrdersResponse> getOrder(@Header("Authorization") String accessToken, @Path("companyId") int companyId);

}