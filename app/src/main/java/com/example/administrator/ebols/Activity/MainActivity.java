package com.example.administrator.ebols.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.administrator.ebols.Login.OauthService;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        navigationView = (NavigationView)findViewById(R.id.navigationMenu);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        dbHandler = new DBHandler(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        loadActivity();
        checkForLogin();
    }

    private void checkForLogin() {
        if(dbHandler.getMemberData()==0){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            companyId = dbHandler.getCompanyId();
            accessToken = "Bearer "+dbHandler.getAccessToken();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
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

    class downloadDatabaseThread extends Thread{

        @Override
        public void run() {

        }
    }

    public class getOrder implements Callback<OrdersResponse> {

        public getOrder(){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService = retrofit.create(OauthService.class);
            ordersRequest = new OrdersRequest(companyId);
        }
        public void run(){
            Call<OrdersResponse> call = oauthService.getOrder(accessToken, Integer.parseInt(companyId));
            call.enqueue(this);
        }
        @Override
        public void onResponse(Call<OrdersResponse> call, retrofit2.Response<OrdersResponse> response) {
            dbHandler.insertData(response.body().getList(), 0);
            dbHandler.insertData(response.body().getList(), 1);
            dbHandler.insertData(response.body().getList(), 2);
            Toast.makeText(getBaseContext(), "done", Toast.LENGTH_LONG).show();
            Thread.currentThread().interrupt();
        }

        @Override
        public void onFailure(Call<OrdersResponse> call, Throwable t) {
            Log.d("bbbbbb", t.getMessage());
        }
    }
}
