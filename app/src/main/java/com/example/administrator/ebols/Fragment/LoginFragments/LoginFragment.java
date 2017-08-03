package com.example.administrator.ebols.Fragment.LoginFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.Activity.LoginActivity;
import com.example.administrator.ebols.Activity.MainActivity;
import com.example.administrator.ebols.Activity.NewOrderActivity;
import com.example.administrator.ebols.Adapter.HomeListAdapter;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.initialize;
import com.example.administrator.ebols.Login.OauthService;
import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.UserRequest;
import com.example.administrator.ebols.OauthAuthentification.UserResponse;
import com.example.administrator.ebols.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button Login;
    private EditText username, password;
    private CheckBox remember, showPass;
    private TextView textView;
    private OauthService oauthService, oauthService2;
    private DBHandler dbHandler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Login = (Button)v.findViewById(R.id.Login);
        username = (EditText)v.findViewById(R.id.username);
        password = (EditText)v.findViewById(R.id.password);
        remember = (CheckBox)v.findViewById(R.id.Remember);
        showPass = (CheckBox)v.findViewById(R.id.ShowPassword);
        textView = (TextView)v.findViewById(R.id.text);
        dbHandler = new DBHandler(getContext());
        login();
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
        return v;
    }
    private long mLastClickTime = 0;
    private void login() {

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime()-mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                new usernameLogin().run();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class usernameLogin implements Callback<AccessResponse> {
        AccessRequest accessRequest;

        public usernameLogin() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService = retrofit.create(OauthService.class);
            accessRequest = new AccessRequest("zhengzhi757@gmail.com", "wja560630");
        }

        protected void run(){
            Call<AccessResponse> call = oauthService.getToken(accessRequest);
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<AccessResponse> call, retrofit2.Response<AccessResponse> response) {
            dbHandler.updataRefreshToken(response.body().getRefresh_token(), response.body().getAccess_token());
            new userSession(response).run();
        }

        @Override
        public void onFailure(Call<AccessResponse> call, Throwable t) {
            Log.d("cccccccc", t.getMessage());
        }
    }
    private class userSession implements Callback<UserResponse> {
        UserRequest userRequest;

        private userSession(retrofit2.Response<AccessResponse> response2){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService2 = retrofit.create(OauthService.class);
            userRequest = new UserRequest();
            userRequest.setRefresh_token(response2.body().getRefresh_token());
            userRequest.setAccess_type(response2.body().getAccess_token());
            userRequest.setToken_type(response2.body().getToken_type());
        }
        private void run(){
            Call<UserResponse> call = oauthService2.getSession(userRequest.getToken_type()+" "+userRequest.getAccess_type());
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            if(response.body()==null){
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("No such user exists");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }else{
                dbHandler.insertData(response.body().getMember().get("id").toString(), response.body().getMember().get("name").toString(), 0);
                dbHandler.insertData(response.body().getCompanies().get(0).get("id").toString(),
                        response.body().getCompanies().get(0).get("name").toString(), 1);
                dbHandler.insertData(response.body().getMember().get("id").toString(), (List<String>)response.body().getCompanies().get(0).get("roles"));

                startActivity(intent);
                getActivity().finish();
            }
        }

        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {
            Log.d("555555555", t.getMessage().toString());
        }
    }
}
