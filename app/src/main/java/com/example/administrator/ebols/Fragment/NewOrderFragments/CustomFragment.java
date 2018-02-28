package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.GetDbData;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.Otto.Order;
import com.example.administrator.ebols.Otto.OrderMsg;
import com.example.administrator.ebols.Otto.OrderToJson;
import com.example.administrator.ebols.R;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

public class CustomFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText shipperName, shipperAddress, shipperCity, shipperZip, shipperContact, shipperPhone, shipperEmail, shipperFax;
    Bundle bundle;
    private Button Continue_Custom;
    private DBHandler dbHandler;
    private OrderMsg orderMsg;
    private UploadRequest uploadRequest;
    private String id,tab, state;
    private Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_new_order, container, false);
        dbHandler = new DBHandler(getContext());
        orderMsg = new OrderMsg();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Continue_Custom = (Button)view.findViewById(R.id.Continue_Custom);
        shipperName = (EditText)view.findViewById(R.id.shipperName);
        shipperAddress = (EditText)view.findViewById(R.id.shipperAddress);
        shipperCity = (EditText)view.findViewById(R.id.shipperCity);
        shipperZip = (EditText)view.findViewById(R.id.shipperZip);
        shipperContact = (EditText)view.findViewById(R.id.shipperContact);
        shipperPhone = (EditText)view.findViewById(R.id.shipperPhone);
        shipperEmail = (EditText)view.findViewById(R.id.shipperEmail);
        shipperFax = (EditText)view.findViewById(R.id.shipperFax);
        Continue_Custom.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new Payment_Invoice_Fragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.newOrder_frame, fragment).commit();
            fragmentManager.executePendingTransactions();
            getOrder();
            Order.getBus().post(uploadRequest);
        }
    };

    private void getOrder() {
        Map<String, Object> customer = new HashMap<>();
        customer.put("name", "abc");
        customer.put("addressLines", "abc");
        customer.put("addressCity", "abc");
        customer.put("addressZipcode", "abc");
        customer.put("contact", "abc");
        customer.put("phone", "abc");
        customer.put("fax", "abc");
        customer.put("email", "abc");
        uploadRequest.setCustomer(customer);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        if(uploadRequest.getOrderId()!= null && uploadRequest.getState()!=null && uploadRequest.getTab()!=null){
            id = uploadRequest.getOrderId();
            tab = uploadRequest.getTab();
            state = uploadRequest.getState();
            dbHandler = new DBHandler(getContext());
            cursor = new GetDbData(dbHandler, id).getDbData(tab);
            Continue_Custom.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperName.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperAddress.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperCity.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperZip.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperContact.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperPhone.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperEmail.setText(dbHandler.getOrderTable(tab).getString(0));
            shipperFax.setText(dbHandler.getOrderTable(tab).getString(0));
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
