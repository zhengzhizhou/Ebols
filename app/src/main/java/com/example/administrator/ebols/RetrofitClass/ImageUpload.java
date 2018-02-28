package com.example.administrator.ebols.RetrofitClass;

import android.util.Log;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.ImageResponse;
import com.example.administrator.ebols.OauthAuthentification.OauthService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/10.
 */

public class ImageUpload implements Callback<ImageResponse> {
    private String select;
    private File file;
    private DBHandler dbHandler;
    private String accessToken;
    private OauthService oauthService;
    public ImageUpload(DBHandler dbHandler, String select, File file){
        this.select = select;
        this.file = file;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
        oauthService = retrofit.create(OauthService.class);
        this.dbHandler = dbHandler;
    }

    public void run(){
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        accessToken = "Bearer " + dbHandler.getAccessToken();
        Call<ImageResponse> call = oauthService.uploadImage(accessToken, filePart);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
        Log.d("response", response.body().toString());
        String id = response.body().getItem().get("id");
        String url = response.body().getItem().get("url");
        String name = response.body().getItem().get("name");
        String key = response.body().getItem().get("key");
        String size = response.body().getItem().get("size");
        String mimeType = response.body().getItem().get("mimeType");

    }

    @Override
    public void onFailure(Call<ImageResponse> call, Throwable t) {
        Log.d("response", t.getMessage().toString());
    }
}