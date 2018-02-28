package com.example.administrator.ebols.Fragment.LoginFragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.TableData.OrderTableListBuilder;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.RetrofitClass.DriverList;
import com.example.administrator.ebols.RetrofitClass.GetOrder;
import com.example.administrator.ebols.RetrofitClass.UserSession;
import com.example.administrator.ebols.RetrofitClass.UsernameLogin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button Login;
    private EditText username, password;
    private CheckBox remember, showPass;
    private DBHandler dbHandler;
    private GetOrder getOrder;
    private DriverList driverList;
    private String str_username, str_password;
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
                str_username = username.getText().toString();
                str_password = password.getText().toString();
                new DownloadAndLogin().execute();
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

    public class DownloadAndLogin extends AsyncTask<Void, Void, Void> {
        private Dialog dialog;
        private Dialog alertDialog;
        protected UserSession userSession;
        private UsernameLogin usernameLogin;
        private String[] list = {"newLoad", "assigned", "pickedUp", "delivered", "billed", "paid", "archived" };

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= new ProgressDialog(getContext());
            dialog.setTitle("test");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            usernameLogin = new UsernameLogin(getContext(), str_username, str_password);
            usernameLogin.run();
            while(usernameLogin.success == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            usernameLogin.success = 0;
            userSession = new UserSession(usernameLogin.response, getContext());
            userSession.run();
            while(userSession.success == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            userSession.success = 0;
            for(int i = 0; i< list.length; i++){
                getOrder = new GetOrder(dbHandler, list[i]);
                try {
                    getOrder.run();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            driverList = new DriverList(getContext());
            driverList.setCheck(true);
            driverList.run();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}
