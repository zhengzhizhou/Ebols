package com.example.administrator.ebols.RetrofitClass;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.DriverResponse;
import com.example.administrator.ebols.OauthAuthentification.OauthService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/5.
 */

public class DriverList implements Callback<DriverResponse> {
    private Retrofit retrofit;
    private DBHandler dbHandler;
    private OauthService oauthService;
    private Context context;
    private String accessToken;
    private Map<String, Object> map;
    private boolean check = false;
    public DriverList(Context context){
        this.context = context;
        retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        dbHandler = new DBHandler(this.context);
        accessToken = "Bearer "+dbHandler.getAccessToken();
        map = new HashMap<>();
    }

    public void setCheck(boolean check){
        this.check = check;
    }
    public void run(){
        Call<DriverResponse> call = oauthService.getDriver(accessToken);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DriverResponse> call, Response<DriverResponse> response) {
        addToDB(response);
        if(response.body().getList().size() != 0){
            dbHandler.insertData(map, 2);
        }
    }

    private void addToDB(Response<DriverResponse> response) {
        List<Map<String, Object>> driver = response.body().getList();
        for(int i = 0; i<response.body().getTotal(); i++){
            map.put("id", driver.get(i).get("id"));
            map.put("name", driver.get(i).get("name"));
            map.put("prefix", driver.get(i).get("prefix"));
            map.put("firstName", driver.get(i).get("firstName"));
            map.put("lastName", driver.get(i).get("lastName"));
            map.put("birthday", driver.get(i).get("birthday"));
            map.put("mobilePhone", driver.get(i).get("mobilePhone"));
            map.put("email", driver.get(i).get("email"));
            map.put("createdDate", driver.get(i).get("createdDate"));
            map.put("activateDate", driver.get(i).get("activateDate"));
            map.put("blockedDate", driver.get(i).get("blockedDate"));
            map.put("type", driver.get(i).get("type"));
            if(driver.get(i).get("company") != null){
                Map<String, Object> company = (Map<String, Object>) driver.get(0).get("company");
                map.put("company_id", company.get("id"));
                map.put("company_name", company.get("name"));
                map.put("company_registerDate", company.get("registerdate"));
                if(Arrays.asList(company.get("roles")).toString().contains("SYSTEM_ADMIN")){
                    map.put("SYSTEM_ADMIN", 1);
                }else{
                    map.put("SYSTEM_ADMIN", 0);
                }
                if(Arrays.asList(company.get("roles")).toString().contains("COMPANY_ADMIN")){
                    map.put("COMPANY_ADMIN", 1);
                }else{
                    map.put("COMPANY_ADMIN", 0);
                }
                if(Arrays.asList(company.get("roles")).toString().contains("COMPANY_DRIVER")){
                    map.put("COMPANY_DRIVER", 1);
                }else{
                    map.put("COMPANY_DRIVER", 0);
                }
            }

        }
        if(check == true){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            check = false;
        }
    }

    @Override
    public void onFailure(Call<DriverResponse> call, Throwable t) {
        Log.d("error", "error");
    }
}
