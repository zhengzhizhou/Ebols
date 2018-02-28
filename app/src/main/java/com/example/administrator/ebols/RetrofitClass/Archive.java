package com.example.administrator.ebols.RetrofitClass;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.ArchiveRequest;
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
 * Created by Administrator on 2017/10/11.
 */

public class Archive implements Callback<UploadResponse> {
    private DBHandler dbHandler;
    private Context context;
    private OauthService oauthService;
    private ArchiveRequest archiveRequest;
    private Retrofit retrofit;
    private GetOrder getOrder;
    private String tab;
    private String OrderId;
    private String reason ;
    private Dialog dialog;
    public Archive(Context context, DBHandler dbHandler, Dialog dialog, String OrderId, String reason,String tab){
        this.context = context;
        this.dialog = dialog;
        this.reason = reason;
        this.OrderId = OrderId;
        this.tab = tab;
        this.dbHandler = dbHandler;
        getOrder = new GetOrder(dbHandler, tab);
        retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        archiveRequest = new ArchiveRequest();
        archiveRequest.setReason(reason);
    }

    public void run(){
        Call<UploadResponse> call = oauthService.archiveDriver("Bearer " + dbHandler.getAccessToken(), archiveRequest, Integer.parseInt(dbHandler.getCompanyId()), Integer.parseInt(OrderId));
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
