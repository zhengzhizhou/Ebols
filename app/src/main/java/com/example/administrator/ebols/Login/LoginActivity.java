package com.example.administrator.ebols.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/8.
 */

public class LoginActivity extends AppCompatActivity{
    Button Login;
    EditText username, password;
    CheckBox remember, showPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Login = (Button)findViewById(R.id.Login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        remember = (CheckBox)findViewById(R.id.Remember);
        showPass = (CheckBox)findViewById(R.id.ShowPassword);
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                }else{
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new usernameLogin().run();
            }
        });

    }


    public class usernameLogin implements Callback<AccessResponse> {
        OauthService oauthService;
        AccessRequest accessRequest;
        public usernameLogin() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService = retrofit.create(OauthService.class);
            accessRequest = new AccessRequest(username.getText().toString(), password.getText().toString());
        }

        protected void run(){
            Call<AccessResponse> call = oauthService.getToken(accessRequest);
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<AccessResponse> call, retrofit2.Response<AccessResponse> response) {
            Log.d("111111111111111111", response.body().getAccess_token());
        }

        @Override
        public void onFailure(Call<AccessResponse> call, Throwable t) {

        }
    }
}
