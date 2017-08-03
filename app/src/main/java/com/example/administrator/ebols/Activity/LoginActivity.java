package com.example.administrator.ebols.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.ebols.Fragment.LoginFragments.LoginFragment;
import com.example.administrator.ebols.Fragment.LoginFragments.SignUpFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.BrokerFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.CustomFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.FleetFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.OwnerFragment;
import com.example.administrator.ebols.R;

/**
 * Created by Administrator on 2017/6/8.
 */

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener, OwnerFragment.OnFragmentInteractionListener, BrokerFragment.OnFragmentInteractionListener,
        FleetFragment.OnFragmentInteractionListener, CustomFragment.OnFragmentInteractionListener{
    private TabLayout tabLayout;
    private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tabLayout = (TabLayout)findViewById(R.id.login_signup_tab);
        setTabLayout();
        if(index == 0){
            Fragment fragment = new LoginFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_signup_frame, fragment);
            fragmentTransaction.commit();
        }
    }
    public void setTabLayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                Fragment fragment = getFragment(index);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login_signup_frame, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private Fragment getFragment(int index) {
        Fragment fragment;
        switch(index){
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new SignUpFragment();
                break;
            default:
                fragment = new LoginFragment();
        }
        return fragment;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
