package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.ImageResponse;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.PickUpRequest;
import com.example.administrator.ebols.OauthAuthentification.PickUpResponse;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;

import java.io.File;
import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/9.
 */

public class PickUp implements Callback<UploadResponse> {
    public OauthService oauthService;
    public DBHandler dbHandler;
    public String accessToken;
    public PickUpRequest pickUpRequest;
    private Context context;
    public PickUp(PickUpRequest pickUpRequest, DBHandler dbHandler, Context context){
        this.pickUpRequest = pickUpRequest;
        this.dbHandler = dbHandler;
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
    }

    public void run(){
        accessToken = "Bearer " + dbHandler.getAccessToken();
        Call<UploadResponse> call = oauthService.pickupOrder(accessToken, pickUpRequest);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        GetOrder getOrder = new GetOrder(dbHandler, "pickedUp");
        if(response.body()!= null & response.body().getItem()!= null){
            try {
                getOrder.run();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        Log.d("abc", "test");
    }

    @Override
    public void onFailure(Call<UploadResponse> call, Throwable t) {
        Log.d("abc", "test");
    }
}
