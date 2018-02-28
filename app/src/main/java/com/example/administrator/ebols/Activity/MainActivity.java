package com.example.administrator.ebols.Activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Fragment.ProfileFragments.CompanyFragment;
import com.example.administrator.ebols.Fragment.ProfileFragments.HistoryFragment;
import com.example.administrator.ebols.Fragment.LoginFragments.LoginFragment;
import com.example.administrator.ebols.Fragment.ProfileFragments.PersonalFragment;
import com.example.administrator.ebols.Fragment.ProfileFragments.ProfileFragment_withCompany;
import com.example.administrator.ebols.Fragment.LoginFragments.SignUpFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.ArchivedFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.AssignedFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.BilledFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.DeliveredFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.NewOrderFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.PaidFragment;
import com.example.administrator.ebols.Fragment.OrderFragments.PickUpFragment;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.MyService.TimerService;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity implements ProfileFragment_withCompany.OnFragmentInteractionListener,
        CompanyFragment.OnFragmentInteractionListener, PersonalFragment.OnFragmentInteractionListener, HistoryFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener,ArchivedFragment.OnFragmentInteractionListener, BilledFragment.OnFragmentInteractionListener,
        AssignedFragment.OnFragmentInteractionListener, DeliveredFragment.OnFragmentInteractionListener, NewOrderFragment.OnFragmentInteractionListener,
        PaidFragment.OnFragmentInteractionListener, PickUpFragment.OnFragmentInteractionListener{
    private NavigationView navigationView;
    private int navIndex = 0;
    private Handler handler;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private String[] activityTitles;
    private DBHandler dbHandler;
    FrameLayout frameLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private OauthService oauthService;
    private OrdersRequest ordersRequest;
    private String companyId, accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        frameLayout = (FrameLayout)findViewById(R.id.frame);
        drawerLayout=(DrawerLayout)findViewById(R.id.DrawerLayout);
        handler = new Handler();

        Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                        Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this))
                .build();
        navigationView = (NavigationView)findViewById(R.id.navigationMenu);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        dbHandler = new DBHandler(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        checkForLogin();
        loadActivity();
    }

    private void checkForLogin() {
        if(dbHandler.getMemberData()==0){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            if(!isServiceRunnning(MainActivity.class)){
                startService(new Intent(MainActivity.this, TimerService.class));
            }
        }
    }

    private void loadActivity() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch (id){
                    case R.id.Navi_Home:
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.Navi_Profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.Navi_Logout:
                        logout();
                        break;
                    default:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                }
                return false;
            }
        });
    }

    private void logout() {
        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.Navi_Logout){
            Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean isServiceRunnning(Class<?> serviceClass){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)){
            if(serviceClass.getName().equals(serviceInfo.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}
