package com.example.administrator.ebols.Activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.administrator.ebols.Fragment.ActionFragments.Action_Archive_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_AssignDriver_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_BOL_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Edit_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Invoice_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_MarkPaid_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_PickUp_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Trash_Fragment;
import com.example.administrator.ebols.R;

public class LoadInfoActivity extends AppCompatActivity implements Action_Archive_Fragment.OnFragmentInteractionListener,
        Action_AssignDriver_Fragment.OnFragmentInteractionListener, Action_BOL_Fragment.OnFragmentInteractionListener,
        Action_Edit_Fragment.OnFragmentInteractionListener, Action_Invoice_Fragment.OnFragmentInteractionListener,
        Action_MarkPaid_Fragment.OnFragmentInteractionListener, Action_PickUp_Fragment.OnFragmentInteractionListener,
        Action_Trash_Fragment.OnFragmentInteractionListener{
    private FrameLayout frameLayout;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_info);
        frameLayout = (FrameLayout) findViewById(R.id.loadInfo_frame);
        spinner = (Spinner)findViewById(R.id.loadInfo_action_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setFrameFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setFrameFragment() {


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
