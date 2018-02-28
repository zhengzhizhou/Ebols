package com.example.administrator.ebols.RetrofitClass;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.Initialize;
import com.example.administrator.ebols.OauthAuthentification.AssignDriverRequest;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/5.
 */

public class AssignDriver implements Callback<UploadResponse> {
    private DBHandler dbHandler;
    private Context context;
    private OauthService oauthService;
    private AssignDriverRequest assignDriverRequest;
    private Retrofit retrofit;
    private Dialog dialog;
    private GetOrder getOrder;
    private String tab;
    public AssignDriver(Context context, int OrderId, Dialog dialog, String tab){
        this.dialog = dialog;
        this.context = context;
        this.tab = tab;
        retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        dbHandler = new DBHandler(context);
        getOrder = new GetOrder(dbHandler, tab);
        oauthService = retrofit.create(OauthService.class);
        assignDriverRequest = new AssignDriverRequest();
        assignDriverRequest.setId(OrderId);
    }

    public void run(){
        Call<UploadResponse> call = oauthService.assignDriver("Bearer " + dbHandler.getAccessToken(), assignDriverRequest, Integer.parseInt(dbHandler.getCompanyId()), Integer.parseInt(dbHandler.getOrderTable(tab).getString(1)));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        if(response.body().getErrorFields().isEmpty()){
            try {
                getOrder.setContext(context);
                getOrder.run();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        dialog.dismiss();
    }

    @Override
    public void onFailure(Call<UploadResponse> call, Throwable t) {
        dialog.dismiss();
        Log.d("error", "error");
    }
}
