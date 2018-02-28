package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.MyCompanyBolSideToRequest;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/11.
 */


public class UploadData implements Callback<UploadResponse> {
    public int success = 0;
    private OauthService oauthService;
    private String tab;
    private MyCompanyBolSideToRequest myCompanyBolSideToRequest;
    private UploadRequest uploadRequest;
    private DBHandler dbHandler;
    private Context context;
    private boolean check=false;
    public UploadData(String tab, Context context, DBHandler dbHandler, UploadRequest uploadRequest){
        this.dbHandler = dbHandler;
        this.uploadRequest = uploadRequest;
        this.tab = tab;
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
    }
    public void setCheck(boolean check){
        this.check = check;
    }
    public void run(){
        Call<UploadResponse> call = oauthService.uploadOrder("Bearer " + dbHandler.getAccessToken(), uploadRequest, Integer.parseInt(dbHandler.getCompanyId()));
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        GetOrder getOrder = new GetOrder(dbHandler, tab);

        if(response.body()!= null & response.body().getItem()!= null){
            try{
                getOrder.run();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(check == true){
            check = false;
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        Log.d("abc", "abcdeffg");
    }

    @Override
    public void onFailure(Call<UploadResponse> call, Throwable t) {
        Log.d("abcd", t.getMessage());
    }
}
