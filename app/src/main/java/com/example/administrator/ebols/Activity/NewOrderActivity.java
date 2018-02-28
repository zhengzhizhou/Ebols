package com.example.administrator.ebols.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.NewOrderFragments.Payment_Invoice_Fragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.PickUpFragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.CustomFragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.Vehicle_Fragment;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.Otto.NewOrderActivityPara;
import com.example.administrator.ebols.Otto.Order;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.RetrofitClass.GetOrder;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */

public class NewOrderActivity extends AppCompatActivity implements CustomFragment.OnFragmentInteractionListener,
        Payment_Invoice_Fragment.OnFragmentInteractionListener, PickUpFragment.OnFragmentInteractionListener,
        Vehicle_Fragment.OnFragmentInteractionListener{
    private String id, tab;
    private UploadRequest uploadRequest;
    //    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sharedPreferences = getSharedPreferences("NewOrderActivityPreference", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_new_order);
        uploadRequest = new UploadRequest();
        Intent intent = getIntent();
        if(intent.getStringExtra("id")!=null){
            id = intent.getStringExtra("id");
            tab = intent.getStringExtra("tab");
            uploadRequest.setOrderId(id);
            uploadRequest.setTab(tab);
        }

//        editor.putString("id", id);
//        editor.putString("state", state);
//        editor.putString("tab", tab);
//        editor.commit();
        Fragment fragment = new PickUpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.newOrder_frame, fragment).commit();;
        fragmentManager.executePendingTransactions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Order.getBus().register(this);
        Order.getBus().post(uploadRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Order.getBus().unregister(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
