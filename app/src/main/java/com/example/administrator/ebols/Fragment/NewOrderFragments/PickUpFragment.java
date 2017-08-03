package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.administrator.ebols.Fragment.SignUpFragments.*;
import com.example.administrator.ebols.Fragment.NewOrderFragments.CustomFragment;
import com.example.administrator.ebols.Fragment.NewOrderFragments.Payment_Invoice_Fragment;
import com.example.administrator.ebols.R;

import java.util.HashMap;
import java.util.Map;

public class PickUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Spinner pickup_state, delivery_state;
    private CheckBox[] checkBoxes;
    private EditText pickup_name, pickup_contact, pickup_address, pickup_city, pickup_zip, delivery_name, delivery_contact,
    delivery_address, delivery_city, delivery_zip;
    private Button continue_btn;
    Map<String, String> info;
    Bundle bundle;
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
        delivery_name = (EditText)view.findViewById(R.id.newOrderDelieveryName);
        delivery_contact = (EditText)view.findViewById(R.id.newOrderDelieveryContact);
        delivery_address = (EditText)view.findViewById(R.id.newOrderDelieveryAddress);
        delivery_city = (EditText)view.findViewById(R.id.newOrderDelieveryCity);
        delivery_zip = (EditText)view.findViewById(R.id.newOrderDelieveryZip);


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
            bundle = new Bundle();
            bundle.putString("abc", pickup_name.getText().toString());
            Fragment fragment = new Vehicle_Fragment();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.newOrder_frame, fragment);
            fragmentTransaction.commit();
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
