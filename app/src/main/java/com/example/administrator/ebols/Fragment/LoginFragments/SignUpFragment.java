package com.example.administrator.ebols.Fragment.LoginFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.ebols.Fragment.SignUpFragments.BrokerFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.CustomFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.FleetFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.OwnerFragment;
import com.example.administrator.ebols.R;

import java.security.acl.Owner;
import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment implements BrokerFragment.OnFragmentInteractionListener,
        CustomFragment.OnFragmentInteractionListener, FleetFragment.OnFragmentInteractionListener,
        OwnerFragment.OnFragmentInteractionListener{

    private OnFragmentInteractionListener mListener;
    private RadioButton checkbox_owner, checkbox_broker, checkbox_fleet, checkbox_custom;
    private RadioGroup radioGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        checkbox_owner = (RadioButton)v.findViewById(R.id.Owner);
        checkbox_fleet = (RadioButton)v.findViewById(R.id.Fleet);
        checkbox_custom = (RadioButton)v.findViewById(R.id.Custom_solution);
        checkbox_broker = (RadioButton)v.findViewById(R.id.Broker);
        radioGroup = (RadioGroup)v.findViewById(R.id.RGroup);
        if(!radioGroup.isActivated()){
            Fragment fragment = new OwnerFragment();
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.signup_frame, fragment);
            fragmentTransaction.commit();
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment;
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.Owner){
                    fragment = new OwnerFragment();
                }else if(checkedId == R.id.Broker){
                    fragment = new BrokerFragment();
                }else if(checkedId == R.id.Custom_solution){
                    fragment = new CustomFragment();
                }else if(checkedId == R.id.Fleet){
                    fragment = new FleetFragment();
                }
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signup_frame, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
