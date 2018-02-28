package com.example.administrator.ebols.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.OrderFragments.ArchivedFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.AssignedFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.BilledFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.DeliveredFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.NewOrderFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.PaidFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.PickUpFragment;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.RetrofitClass.GetOrder;

import java.io.UnsupportedEncodingException;

public class HomeActivity extends MainActivity {

    private TabLayout tabLayout;
    private int index;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

        btn = (Button)view.findViewById(R.id.newOrder);
        tabLayout= (TabLayout) view.findViewById(R.id.Home_Tabs);
        if(index == 0){
            Fragment fragment = new NewOrderFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_home, fragment);
            fragmentTransaction.commit();
        }
        tabLayout.addTab(tabLayout.newTab().setText("New Order"));
        tabLayout.addTab(tabLayout.newTab().setText("Assigned"));
        tabLayout.addTab(tabLayout.newTab().setText("Picked Up"));
        tabLayout.addTab(tabLayout.newTab().setText("Delivered"));
        tabLayout.addTab(tabLayout.newTab().setText("Billed"));
        tabLayout.addTab(tabLayout.newTab().setText("Paid"));
        tabLayout.addTab(tabLayout.newTab().setText("Archived"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                changeFragment(index);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewOrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void changeFragment(int index) {
        Fragment fragment;
        switch (index){
            case 0:
                fragment = new NewOrderFragment();
                break;
            case 1:
                fragment = new AssignedFragment();
                break;
            case 2:
                fragment = new PickUpFragment();
                break;
            case 3:
                fragment = new DeliveredFragment();
                break;
            case 4:
                fragment = new BilledFragment();
                break;
            case 5:
                fragment = new PaidFragment();
                break;
            case 6:
                fragment = new ArchivedFragment();
                break;
            default:
                fragment = new NewOrderFragment();
                break;
        }
        FragmentTransaction fragmentTrasaction = getSupportFragmentManager().beginTransaction();
        fragmentTrasaction.replace(R.id.frame_home, fragment);
        fragmentTrasaction.commit();
    }


}
