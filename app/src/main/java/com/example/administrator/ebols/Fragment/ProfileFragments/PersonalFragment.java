package com.example.administrator.ebols.Fragment.ProfileFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.Login.OauthService;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.RefreshRequest;
import com.example.administrator.ebols.Object.mTimer;
import com.example.administrator.ebols.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalFragment extends Fragment {
    private View view;
    private OnFragmentInteractionListener mListener;
    private Button btn, btn2;
    private OauthService oauthService;
    private RefreshRequest refreshAccess;
    private DBHandler dbHandler;
    private String refresh_token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        btn = (Button)view.findViewById(R.id.historyIntent);
        btn2 = (Button)view.findViewById(R.id.btn2);
        dbHandler = new DBHandler(getContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getNewRefreshToken().run();
                Fragment fragment = new HistoryFragment();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.history_frame, fragment);
                fragmentTransaction.commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_token=dbHandler.getKey_Refresh_Token();
                new getNewAccessToken().execute();
            }
        });
        return view;
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

    private class getNewRefreshToken implements Callback<AccessResponse> {
        RefreshRequest refreshRequest;
        public getNewRefreshToken(){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.url).addConverterFactory(GsonConverterFactory.create()).build();
            oauthService=retrofit.create(OauthService.class);
            refreshRequest = new RefreshRequest(refresh_token);
        }
        public void run(){
            Call<AccessResponse> call = oauthService.refreshToken(refreshRequest);
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<AccessResponse> call, Response<AccessResponse> response) {
            Log.d("refresh_token", response.body().getRefresh_token());
            Log.d("access_token", response.body().getAccess_token());
            String refresh_token = response.body().getRefresh_token();
            String access_token=response.body().getAccess_token();
            dbHandler.updataRefreshToken(refresh_token, access_token);
        }

        @Override
        public void onFailure(Call<AccessResponse> call, Throwable t) {

        }
    }

    private class getNewAccessToken extends AsyncTask<String, Void, String>{
        ProgressDialog dialog;
        mTimer timer;

        @Override
        protected String doInBackground(String... params) {
            new getNewRefreshToken().run();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Loading");
            dialog.setMessage("loading...");
            dialog.setIndeterminate(true);
            dialog.show();
        }

    }
}
