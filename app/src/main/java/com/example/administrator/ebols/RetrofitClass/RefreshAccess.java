package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.RefreshRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/4.
 */

public class RefreshAccess implements Callback<AccessResponse> {
    private OauthService oauthService;
    private RefreshRequest refreshRequest;
    private DBHandler dbHandler;
    private Context context;
    private String refresh_token;
    public RefreshAccess(Context context){
        this.context = context;
        dbHandler = new DBHandler(this.context);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        refreshRequest = new RefreshRequest();
        refresh_token =dbHandler.getKey_Refresh_Token();
        refreshRequest.setRefresh_token(refresh_token);
    }

    public void run(){
        Call<AccessResponse> call = oauthService.refreshToken(refreshRequest);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<AccessResponse> call, Response<AccessResponse> response) {
        dbHandler.updataRefreshToken(response.body().getRefresh_token(), response.body().getAccess_token());
    }

    @Override
    public void onFailure(Call<AccessResponse> call, Throwable t) {
        Log.d("test", "test");
    }
}
