package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.UserRequest;
import com.example.administrator.ebols.OauthAuthentification.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/4.
 */

public class UserSession implements Callback<UserResponse> {
    public UserRequest userRequest;
    public int success = 0;
    private OauthService oauthService;
    private Context context;
    private DBHandler dbHandler;
    public UserSession(retrofit2.Response<AccessResponse> response2, Context context){
        this.context = context;
        dbHandler = new DBHandler(context);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        userRequest = new UserRequest();
        userRequest.setRefresh_token(response2.body().getRefresh_token());
        userRequest.setAccess_type(response2.body().getAccess_token());
        userRequest.setToken_type(response2.body().getToken_type());
    }
    public void run(){
        Call<UserResponse> call = oauthService.getSession(userRequest.getToken_type()+" "+userRequest.getAccess_type());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        dbHandler.insertData(response.body().getMember().get("id").toString(), response.body().getMember().get("name").toString(), 0);
        dbHandler.insertData(response.body().getCompanies().get(0).get("id").toString(),
                response.body().getCompanies().get(0).get("name").toString(), 1);
        dbHandler.insertData(response.body().getMember().get("id").toString(), (List<String>)response.body().getCompanies().get(0).get("roles"));

        success = 1;
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {
        Log.d("555555555", t.getMessage().toString());
        success = 2;
    }
}