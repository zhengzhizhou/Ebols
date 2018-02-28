package com.example.administrator.ebols.Fragment.SignUpFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterRequest;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterResponse;
import com.example.administrator.ebols.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OwnerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText firstName, lastName, password, email, phone, companyName;
    Button signup, close;
    private OauthService oauthService;
    private OwnerRegisterRequest ownerRegisterRequest;
    private Map<String, Object> object;
    private Map<String, String> member, company;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_owner, container, false);

        firstName = (EditText)v.findViewById(R.id.Owner_firstName);
        lastName = (EditText)v.findViewById(R.id.Owner_lastName);
        password = (EditText)v.findViewById(R.id.Owner_Password);
        email = (EditText)v.findViewById(R.id.Owner_Email);
        phone = (EditText)v.findViewById(R.id.Owner_PhoneNumber);
        companyName = (EditText)v.findViewById(R.id.Owner_Company_Name);

        signup = (Button)v.findViewById(R.id.owner_signup);
        close = (Button)v.findViewById(R.id.owner_close);

        member = new HashMap<String, String>();
        company = new HashMap<String, String>();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new register().run();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class register implements Callback<OwnerRegisterResponse>{
        public register(){
            member.put("firstName", firstName.getText().toString());
            member.put("lastName", lastName.getText().toString());
            member.put("email", email.getText().toString());
            member.put("phone", phone.getText().toString());
            member.put("password", password.getText().toString());
            company.put("name", companyName.getText().toString());
            company.put("type", "owner");

            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService = retrofit.create(OauthService.class);
            ownerRegisterRequest = new OwnerRegisterRequest(member, company);
        }
        public void run() {
            Call<OwnerRegisterResponse> call = oauthService.ownerRegister(ownerRegisterRequest);
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call call, Response response) {
            Log.d("111111111111111111", response.body().toString());
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    }
}
