package com.example.administrator.ebols.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.ebols.Fragment.ProfileFragments.ProfileFragment_withCompany;
import com.example.administrator.ebols.R;

public class ProfileActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_profile, frameLayout);
        Fragment fragment = new ProfileFragment_withCompany();
        FragmentTransaction fragmengTrasaction = getSupportFragmentManager().beginTransaction();
        fragmengTrasaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmengTrasaction.replace(R.id.profile_frame,fragment);
        fragmengTrasaction.commit();
    }

}
