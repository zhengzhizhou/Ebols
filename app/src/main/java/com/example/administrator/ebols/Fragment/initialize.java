package com.example.administrator.ebols.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Login.OauthService;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.Object.HomeListObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/11.
 */

public class initialize {
    private List<HomeListObject> homeListObjects;
    private DBHandler dbHandler;
    private Cursor cursor_To, cursor_Origin, cursor_Destination;
    public initialize(List<HomeListObject> homeListObjects, Context context){
        dbHandler = new DBHandler(context);
        this.homeListObjects = homeListObjects;
    }
    public List<HomeListObject> initializeOrder(){
        this.homeListObjects.add(new HomeListObject("1", "1", "1", "1"));
        createOrderList();
        return this.homeListObjects;
    }



    private void createOrderList() {
        Cursor cursorDes = dbHandler.getMyCompanyBolDestinationData();
        Cursor cursorOriginal = dbHandler.getMyCompanyBolOriginalData();
        Cursor cursor = dbHandler.getMyCompanyBolData();
        for(int i = 0; i<cursor.getCount(); i++){
            homeListObjects.add(new HomeListObject(cursor.getString(1), cursor.getString(3),
                    cursorOriginal.getString(2)+" "+cursorOriginal.getString(3)+" "+cursorOriginal.getString(4)+" "+cursorOriginal.getString(5),
                    cursorDes.getString(2)+" "+cursorDes.getString(3)+" "+cursorDes.getString(4)+" "+cursorDes.getString(5)));
            cursor.moveToNext();
            cursorDes.moveToNext();
            cursorOriginal.moveToNext();
        }
    }
}
