package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.GetDbData;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.Otto.NewOrderActivityPara;
import com.example.administrator.ebols.Otto.Order;
import com.example.administrator.ebols.R;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

public class PickUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Spinner pickup_state, delivery_state, spinner_states;
    private CheckBox[] checkBoxes;
    private EditText pickup_name, pickup_contact, pickup_address, pickup_city, pickup_zip, delivery_name, delivery_contact,
    delivery_address, delivery_city, delivery_zip, pickup_phone, pickup_note, delivery_phone, delivery_note;
    private Button continue_btn;
    private UploadRequest uploadRequest;
    private String id,tab, state;
    private Cursor cursor;
    private Activity mActivity;
    private DBHandler dbHandler;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pick_up_new_order, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pickup_state = (Spinner)view.findViewById(R.id.newOrderPickUpState);
        delivery_state = (Spinner)view.findViewById(R.id.newOrderDelieveryState);
        continue_btn=(Button)view.findViewById(R.id.btn_continue);
        continue_btn.setOnClickListener(btn_clickListener);
        checkBoxes = new CheckBox[2];
        checkBoxes[0] = (CheckBox)view.findViewById(R.id.select_pickup);
        checkBoxes[1] =(CheckBox)view.findViewById(R.id.select_Delievery);
        checkBoxes[0].setOnClickListener(checkBoxes_clickListener);
        checkBoxes[1].setOnClickListener(checkBoxes_clickListener);
        pickup_name = (EditText)view.findViewById(R.id.newOrderPickUpName);
        pickup_contact = (EditText)view.findViewById(R.id.newOrderPickUpContact);
        pickup_address = (EditText)view.findViewById(R.id.newOrderPickUpAddress);
        pickup_city = (EditText)view.findViewById(R.id.newOrderPickUpCity);
        pickup_zip = (EditText)view.findViewById(R.id.newOrderPickUpZip);
        pickup_phone = (EditText)view.findViewById(R.id.newOrderPickUpPhone);
        pickup_note = (EditText)view.findViewById(R.id.newOrderPickUpNote);
        delivery_name = (EditText)view.findViewById(R.id.newOrderDelieveryName);
        delivery_contact = (EditText)view.findViewById(R.id.newOrderDelieveryContact);
        delivery_address = (EditText)view.findViewById(R.id.newOrderDelieveryAddress);
        delivery_city = (EditText)view.findViewById(R.id.newOrderDelieveryCity);
        delivery_zip = (EditText)view.findViewById(R.id.newOrderDelieveryZip);
        delivery_phone = (EditText)view.findViewById(R.id.newOrderDelieveryPhone);
        delivery_note = (EditText)view.findViewById(R.id.newOrderDelieveryNote);

    }

    private View.OnClickListener checkBoxes_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int checked_ID = v.getId();
            for(int i = 0; i<checkBoxes.length; i++){
                final CheckBox current = checkBoxes[i];
                if(current.getId() == checked_ID){
                    current.setChecked(true);
                }else{
                    current.setChecked(false);
                }
            }
        }
    };
    private View.OnClickListener btn_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pickup_name.getText().toString().equals("") || pickup_address.getText().toString().equals("") || pickup_city.getText().toString().equals("")||
                    pickup_state.getSelectedItem().toString().equals("") || delivery_name.getText().toString().equals("") ||
                    delivery_state.getSelectedItem().toString().equals("")|| delivery_address.toString().equals("") ||
                    delivery_city.getText().toString().equals("")|| (!checkBoxes[0].isChecked()&&!checkBoxes[1].isChecked())){
                Toast.makeText(mActivity, "Name, address, city and state for original and destination, and the selection of  are required", Toast.LENGTH_SHORT).show();
            }else{
                Fragment fragment = new Vehicle_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.newOrder_frame, fragment).commit();
                fragmentManager.executePendingTransactions();
                getOrder();
                Order.getBus().post(uploadRequest);
            }
        }
    };

    public void getOrder(){
        Map<String, Object> original = new HashMap<>();
        Map<String, Object> destination = new HashMap<>();
        Map<String, Object> customer = new HashMap<>();
        customer.put("name", pickup_name.getText().toString());
        customer.put("addressLines", pickup_address.getText().toString());
        customer.put("addressCity", pickup_city.getText().toString());
        customer.put("addressZipcode", pickup_zip.getText().toString());
        customer.put("contact", pickup_contact.getText().toString());
        customer.put("phone", pickup_contact.getText().toString());
        original.put("customer", customer);
        original.put("note", pickup_note.getText().toString());
        customer.put("name", delivery_name.getText().toString());
        customer.put("addressLines", delivery_address.getText().toString());
        customer.put("addressCity", delivery_city.getText().toString());
        customer.put("addressZipcode", delivery_zip.getText().toString());
        customer.put("contact", delivery_contact.getText().toString());
        customer.put("phone", delivery_phone.getText().toString());
        destination.put("customer", customer);
        destination.put("note", pickup_note.getText().toString());
        uploadRequest.setState(state);
        uploadRequest.setOriginal(original);
        if(checkBoxes[0].isChecked()){
            uploadRequest.setBillClient("ORIGINAL");
        }else {
            uploadRequest.setBillClient("DESTINATION");
        }
        uploadRequest.setDestination(destination);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
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
            pickup_name.setText(cursor.getString(18));
            pickup_address.setText(cursor.getString(19));
            pickup_city.setText(cursor.getString(20));
            pickup_zip.setText(cursor.getString(22));
            pickup_contact.setText(cursor.getString(23));
            pickup_note.setText(cursor.getString(27));
            pickup_phone.setText(cursor.getString(24));
            delivery_name.setText(cursor.getString(41));
            delivery_address.setText(cursor.getString(42));
            delivery_city.setText(cursor.getString(43));
            delivery_zip.setText(cursor.getString(45));
            delivery_contact.setText(cursor.getString(46));
            delivery_note.setText(cursor.getString(50));
            delivery_phone.setText(cursor.getString(47));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
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
