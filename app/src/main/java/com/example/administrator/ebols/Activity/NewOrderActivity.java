package com.example.administrator.ebols.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.NewOrderFragments.Payment_Invoice_Fragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.PickUpFragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.CustomFragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.Vehicle_Fragment;
import com.example.administrator.ebols.R;

/**
 * Created by Administrator on 2017/7/10.
 */

public class NewOrderActivity extends AppCompatActivity implements CustomFragment.OnFragmentInteractionListener,
        Payment_Invoice_Fragment.OnFragmentInteractionListener, PickUpFragment.OnFragmentInteractionListener,
        Vehicle_Fragment.OnFragmentInteractionListener{
    Button button;
    Fragment newfragment;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        index = 0;
        Fragment fragment = new PickUpFragment();
        FragmentTransaction fragmentTrasaction = getSupportFragmentManager().beginTransaction();
        fragmentTrasaction.replace(R.id.newOrder_frame, fragment);
        fragmentTrasaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
