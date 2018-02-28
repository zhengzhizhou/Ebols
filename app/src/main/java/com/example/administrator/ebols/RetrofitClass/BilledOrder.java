package com.example.administrator.ebols.RetrofitClass;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.AssignDriverRequest;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.SendInvoiceRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;
import com.example.administrator.ebols.Object.BilledListObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/11.
 */

public class BilledOrder implements Callback<UploadResponse> {
    private DBHandler dbHandler;
    private Context context;
    private OauthService oauthService;
    private SendInvoiceRequest sendInvoiceRequest;
    private Retrofit retrofit;
    private Dialog dialog;
    private GetOrder getOrder;
    private String tab;
    private String OrderId;
    private List<String> list;
    public BilledOrder(Context context, List<String> list, String OrderId, Dialog dialog, String tab){
        this.dialog = dialog;
        this.tab = tab;
        this.list = list;
        this.OrderId = OrderId;
        this.context = context;
        retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        dbHandler = new DBHandler(context);
        getOrder = new GetOrder(dbHandler, tab);
        oauthService = retrofit.create(OauthService.class);
        sendInvoiceRequest = new SendInvoiceRequest();
        sendInvoiceRequest.setReceiverFullName(list.get(0));
        sendInvoiceRequest.setFax(list.get(1));
        sendInvoiceRequest.setEmail(list.get(2));
        sendInvoiceRequest.setInvoiceNote(list.get(3));
        sendInvoiceRequest.setInvoiceNumber(list.get(4));
    }

    public void run(){
        Call<UploadResponse> call = oauthService.sendInvoiceOrder("Bearer " + dbHandler.getAccessToken(), sendInvoiceRequest, Integer.parseInt(dbHandler.getCompanyId()), Integer.parseInt(OrderId));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        if(response.body().getErrorMessage() == null){
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
        Log.d("abc", "abc");
    }
}
