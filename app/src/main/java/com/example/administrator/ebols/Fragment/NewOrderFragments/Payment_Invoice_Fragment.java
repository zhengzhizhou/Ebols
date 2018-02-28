package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.administrator.ebols.Activity.HomeActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.GetDbData;
import com.example.administrator.ebols.OauthAuthentification.MyCompanyBolSideToRequest;
import com.example.administrator.ebols.Object.DelieveredListObject;
import com.example.administrator.ebols.RetrofitClass.DelieveredOrder;
import com.example.administrator.ebols.RetrofitClass.GetOrder;
import com.example.administrator.ebols.OauthAuthentification.OauthService;
import com.example.administrator.ebols.OauthAuthentification.Constant;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;
import com.example.administrator.ebols.Otto.Order;
import com.example.administrator.ebols.Otto.OrderMsg;
import com.example.administrator.ebols.Otto.OrderToJson;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.RetrofitClass.PickupOrder;
import com.example.administrator.ebols.RetrofitClass.UploadData;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.otto.Subscribe;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Payment_Invoice_Fragment extends Fragment {

    private Button Submit_Payment_Invoice_btn;
    private OnFragmentInteractionListener mListener;
    private EditText price, paymentNotes, invoiceID, invoiceNotes;
    protected UploadRequest uploadRequest;
    private OauthService oauthService;
    private String id,tab, state;
    private Cursor cursor;
    private DBHandler dbHandler;
    private UploadData uploadData;
    private MyCompanyBolSideToRequest myCompanyBolSideToRequest;
    private DelieveredOrder delieveredOrder;
    private PickupOrder pickupOrder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_payment__invoice_, container, false);
        price = (EditText)view.findViewById(R.id.price);
        paymentNotes = (EditText)view.findViewById(R.id.paymentNotes);
        invoiceID = (EditText)view.findViewById(R.id.invoiceID);
        invoiceNotes = (EditText)view.findViewById(R.id.invoiceNotes);
        dbHandler = new DBHandler(getContext());
        myCompanyBolSideToRequest = new MyCompanyBolSideToRequest();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Submit_Payment_Invoice_btn = (Button)view.findViewById(R.id.Submit_Payment_Invoice_btn);
        price = (EditText)view.findViewById(R.id.price);
        paymentNotes = (EditText)view.findViewById(R.id.paymentNotes);
        invoiceID = (EditText)view.findViewById(R.id.invoiceID);
        invoiceNotes = (EditText)view.findViewById(R.id.invoiceNotes);

        Submit_Payment_Invoice_btn.setOnClickListener(mClickListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        Order.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Order.getBus().unregister(this);
    }

    @Subscribe
    public void receiveMsg(UploadRequest uploadRequest){
        this.uploadRequest = uploadRequest;
        myCompanyBolSideToRequest.setCustomer(uploadRequest.getCustomer());
        if(uploadRequest.getOrderId()!= null && uploadRequest.getState()!=null && uploadRequest.getTab()!=null){
            id = uploadRequest.getOrderId();
            tab = uploadRequest.getTab();
            state = uploadRequest.getState();
            cursor = new GetDbData(dbHandler, id).getDbData(tab);
            price.setText(dbHandler.getOrderTable(tab).getString(0));
            paymentNotes.setText(dbHandler.getOrderTable(tab).getString(0));
            invoiceID.setText(dbHandler.getOrderTable(tab).getString(0));
            invoiceNotes.setText(dbHandler.getOrderTable(tab).getString(0));
        }
    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinkedTreeMap<String, Object> payment = new LinkedTreeMap<>();
            payment.put("paymentNote", 111);
            payment.put("amount", 0.5);
            payment.put("paymentMethod", "none");
            payment.put("paymentDate", "1111");
            payment.put("invoiceNumber", "abc");
            payment.put("invoiceNote", "abc");
            uploadRequest.setPayment(payment);
            uploadRequest.setNumber(1111);

            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setTitle("test");
            dialog.show();
            uploadData = new UploadData("newLoad", getContext(), dbHandler, uploadRequest);
            uploadData.setCheck(true);
            uploadData.run();
            dialog.dismiss();
        }
    };


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
}
