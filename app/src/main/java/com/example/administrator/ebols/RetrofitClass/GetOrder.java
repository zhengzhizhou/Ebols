package com.example.administrator.ebols.RetrofitClass;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.Adapter.HomeListAdapter;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.Initialize;
import com.example.administrator.ebols.Fragment.OrderFragments.NewOrderFragment;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.Object.HomeListObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/21.
 */

public class GetOrder implements Callback<OrdersResponse> {
    private String companyId, accessToken;
    private OauthService oauthService;
    private OrdersRequest ordersRequest;
    private DBHandler dbHandler;
    private String tabkey;
    public int success = 0;
    private Context context;
    public GetOrder(DBHandler dbHandler, String tabkey){
        this.dbHandler = dbHandler;
        this.tabkey = tabkey;
        companyId = dbHandler.getCompanyId();
        accessToken = "Bearer "+dbHandler.getAccessToken();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        ordersRequest = new OrdersRequest(companyId);
    }
    public void setContext(Context context){
        this.context = context;
    }
    public void run() throws UnsupportedEncodingException {
        Call<OrdersResponse> call = oauthService.getOrder(accessToken, Integer.parseInt(companyId), tabkey);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<OrdersResponse> call, retrofit2.Response<OrdersResponse> response) {
        dbHandler.insertData(response.body().getList(), tabkey);
        success = 1;
        if(context != null){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void onFailure(Call<OrdersResponse> call, Throwable t) {
        Log.d("bbbbbb", t.getMessage());
    }

}
