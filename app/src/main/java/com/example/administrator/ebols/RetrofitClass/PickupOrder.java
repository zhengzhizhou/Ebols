package com.example.administrator.ebols.RetrofitClass;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.AssignDriverRequest;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.MyCompanyBolSideToRequest;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/11.
 */

public class PickupOrder implements Callback<UploadResponse> {
    private DBHandler dbHandler;
    private Context context;
    private OauthService oauthService;
    private Retrofit retrofit;
    private GetOrder getOrder;
    private String tab;
    private Map<String, String> customer, customerSignature,driverSignature;
    private String orderId;
    private MyCompanyBolSideToRequest myCompanyBolSideToRequest;
    private boolean check=false;
    public PickupOrder(Context context, String orderId,String tab, MyCompanyBolSideToRequest myCompanyBolSideToRequest){
        this.tab = tab;
        this.context = context;
        this.myCompanyBolSideToRequest = myCompanyBolSideToRequest;
        this.orderId = orderId;
        retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        dbHandler = new DBHandler(context);
        oauthService = retrofit.create(OauthService.class);
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public void run(){
    }

    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        GetOrder getOrder = new GetOrder(dbHandler, tab);

        if(response.body()!= null & response.body().getItem()!= null){
            try {
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
        Log.d("abc", "abc");
//        dbHandler.insertData(response.body().getItem(), tab);
    }

    @Override
    public void onFailure(Call<UploadResponse> call, Throwable t) {
        Log.d("abc", "abc");
    }
}
