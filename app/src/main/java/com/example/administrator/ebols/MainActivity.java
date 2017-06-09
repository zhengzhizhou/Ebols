package com.example.administrator.ebols;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyAdapter myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new Fragment_New();
                    break;
                case 1:
                    fragment = new Fragment_b();
                    break;
                case 2:
                    fragment = new Fragment_c();
                    break;
                case 3:
                    fragment = new Fragment_d();
                    break;
                case 4:
                    fragment = new Fragment_e();
                    break;
                case 5:
                    fragment = new Fragment_f();
                    break;
                case 6:
                    fragment = new Fragment_g();
                    break;
                case 7:
                    fragment = new Fragment_h();
                    break;
                case 8:
                    fragment = new Fragment_i();
                    break;
                case 9:
                    fragment = new Fragment_j();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "tab" + position;
        }
    }
}
