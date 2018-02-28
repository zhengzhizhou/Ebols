package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.MyService.TimerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/4.
 */
public class UsernameLogin implements Callback<AccessResponse> {
    public AccessRequest accessRequest;
    public int success = 0;
    public retrofit2.Response<AccessResponse> response;
    private OauthService oauthService;
    private DBHandler dbHandler;
    private Context context;
    public UsernameLogin(Context context, String username, String password) {
        this.context = context;
        dbHandler = new DBHandler(context);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        accessRequest = new AccessRequest(username, password);
    }

    public void run(){
        Call<AccessResponse> call = oauthService.getToken(accessRequest);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AccessResponse> call, retrofit2.Response<AccessResponse> response) {
        dbHandler.updataRefreshToken(response.body().getRefresh_token(), response.body().getAccess_token());
        success = 1;
        context.startService(new Intent(context, TimerService.class));
        this.response = response;
    }

    @Override
    public void onFailure(Call<AccessResponse> call, Throwable t) {
        Log.d("cccccccc", t.getMessage());
        success = 2;
    }
}