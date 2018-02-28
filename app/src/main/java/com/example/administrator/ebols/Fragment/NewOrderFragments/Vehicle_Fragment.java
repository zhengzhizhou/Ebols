package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ebols.Activity.NewOrderActivity;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.GetDbData;
import com.example.administrator.ebols.ErrorCheck.DataTypeCheck;
import com.example.administrator.ebols.Fragment.OrderFragments.NewOrderFragment;
import com.example.administrator.ebols.Fragment.SignUpFragments.*;
import com.example.administrator.ebols.Fragment.NewOrderFragments.CustomFragment;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.Otto.Order;
import com.example.administrator.ebols.Otto.OrderMsg;
import com.example.administrator.ebols.Otto.OrderToJson;
import com.example.administrator.ebols.R;
import com.squareup.otto.Subscribe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class Vehicle_Fragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Button button, continue_btn;
    private int vehicle_num =1;
    private EditText editText_year, editText_make, editText_model, editText_color, editText_vin, editText_price;
    private TextView textView_spinner,textView_type, textView_year, textView_make, textView_model, textView_color, textView_price, textView_vin;
    private LinearLayout ll_year, ll_make, ll_model, ll_color, ll_vin, ll_price, ll_type, ll1, ll2, ll3, ll;
    private List<EditText> edittext_list_year, edittext_list_make, edittext_list_model, edittext_list_color, edittext_list_vin, edittext_list_price;
    private UploadRequest uploadRequest;
    private List<Spinner> spinner_list_type;
    private Spinner spinner_type;
    private String id,tab, state;
    private Cursor cursor;
    private DBHandler dbHandler;
    private Activity mActivity = getActivity();
    private DataTypeCheck dataTypeCheck_year, dataTypeCheck_price;
    private boolean check = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_vehicle_, container, false);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout)view.findViewById(R.id.ll);
        button = (Button)view.findViewById(R.id.add_vehicle);
        continue_btn = (Button)view.findViewById(R.id.continue_btn_vehicle);
        button.setOnClickListener(mClickListener);
        edittext_list_year = new ArrayList<>();
        edittext_list_make = new ArrayList<>();
        edittext_list_model = new ArrayList<>();
        edittext_list_color = new ArrayList<>();
        edittext_list_vin = new ArrayList<>();
        edittext_list_price = new ArrayList<>();
        spinner_list_type = new ArrayList<>();

        editText_year = (EditText)view.findViewById(R.id.year_vehicle);
        editText_make = (EditText)view.findViewById(R.id.Make_vehicle);
        editText_model = (EditText)view.findViewById(R.id.Model_vehicle);
        editText_color = (EditText)view.findViewById(R.id.Color_vehicle);
        editText_vin = (EditText)view.findViewById(R.id.VIN_vehicle);
        editText_price = (EditText)view.findViewById(R.id.Price_vehicle);
        spinner_type = (Spinner)view.findViewById(R.id.type_vehicle);

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CustomFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.newOrder_frame, fragment);
                getOrder();
                if(check == true){
                    fragmentTransaction.commit();
                    fragmentManager.executePendingTransactions();
                    Order.getBus().post(uploadRequest);
                }
            }
        });
    }

    private void getOrder() {
        Map<String, Object> vehicle = new HashMap<>();
        List<Map<String, Object>> vehicles = new ArrayList<>();
        if(vehicle_num == 1){
            dataTypeCheck_year = new DataTypeCheck(editText_year.getText().toString());
            dataTypeCheck_price = new DataTypeCheck(editText_price.getText().toString());
            if((dataTypeCheck_price.isInteger()||dataTypeCheck_price.isDouble()) && dataTypeCheck_year.isInteger()){
                vehicle.put("makeYear", Integer.parseInt(editText_year.getText().toString()));
                vehicle.put("price", Double.parseDouble(editText_price.getText().toString()));
                check = true;
            }else if(!dataTypeCheck_year.isInteger()){
                Toast.makeText(mActivity, "Number is required for year", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mActivity, "Number is required for price", Toast.LENGTH_SHORT).show();
            }
            vehicle.put("make", editText_make.getText().toString());
            vehicle.put("model", editText_model.getText().toString());
            vehicle.put("color", editText_color.getText().toString());
            vehicle.put("vin", editText_vin.getText().toString());
            vehicle.put("type", spinner_type.getSelectedItem().toString());
            vehicles.add(vehicle);
        }else{
            for(int i = 1; i<vehicle_num; i++){
                check = false;
                dataTypeCheck_year = new DataTypeCheck(edittext_list_year.get(i-1).getText().toString());
                dataTypeCheck_price = new DataTypeCheck(edittext_list_year.get(i-1).getText().toString());
                if(dataTypeCheck_price.isDouble() && dataTypeCheck_year.isInteger()){
                    vehicle.put("makeYear", Integer.parseInt(edittext_list_year.get(i-1).getText().toString()));
                    vehicle.put("price", Double.parseDouble(edittext_list_price.get(i-1).getText().toString()));
                    check = true;
                }else if(!dataTypeCheck_year.isInteger()){
                    Toast.makeText(mActivity, "Number is required for year", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mActivity, "Number is required for price", Toast.LENGTH_SHORT).show();
                }
                //vehicle.put("year", (edittext_list_year.get(i-1)).getText().toString());
                vehicle.put("make", (edittext_list_make.get(i-1)).getText().toString());
                vehicle.put("model", (edittext_list_model.get(i-1)).getText().toString());
                vehicle.put("color", (edittext_list_color.get(i-1)).getText().toString());
                vehicle.put("vin", (edittext_list_vin.get(i-1)).getText().toString());
                vehicle.put("type", (spinner_list_type.get(i-1)).getSelectedItem().toString());
                vehicle.put("price", (edittext_list_price.get(i-1)).getText().toString());
                vehicles.add(vehicle);
            }
        }
        uploadRequest.setVehicles(vehicles);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText new_vehicle_year, new_vehicle_make, new_vehicle_model, new_vehicle_vin, new_vehicle_price, new_vehicle_color;
            Spinner new_vehicle_type;
            vehicle_num++;
            Context c = getActivity().getApplicationContext();

            Spinner spinner = new Spinner(c);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c, R.array.type_vehicle, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);
            spinner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView textView = new TextView(c);
            textView.setText("Vehicle" + vehicle_num);

            AddNewView addNewView = new AddNewView(c);

            ll1 = addNewView.buildNewLinearLayout(ll1, LinearLayout.HORIZONTAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll2 = addNewView.buildNewLinearLayout(ll2, LinearLayout.VERTICAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll3 = addNewView.buildNewLinearLayout(ll3, LinearLayout.HORIZONTAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            ll_year = addNewView.buildNewLinearLayout(ll_year, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_make = addNewView.buildNewLinearLayout(ll_make, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_model = addNewView.buildNewLinearLayout(ll_model, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_color = addNewView.buildNewLinearLayout(ll_color, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_vin = addNewView.buildNewLinearLayout(ll_vin, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_price = addNewView.buildNewLinearLayout(ll_price, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            ll_type = addNewView.buildNewLinearLayout(ll_type, LinearLayout.VERTICAL, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            textView_spinner = addNewView.buildNewTextView(textView_spinner, "Type", Color.BLACK);
            textView_year = addNewView.buildNewTextView(textView_year, "Year", Color.BLACK);
            textView_type = addNewView.buildNewTextView(textView_type, "Type", Color.BLACK);
            textView_make = addNewView.buildNewTextView(textView_make, "Make", Color.BLACK);
            textView_model = addNewView.buildNewTextView(textView_model, "Model", Color.BLACK);
            textView_color = addNewView.buildNewTextView(textView_color, "Color", Color.BLACK);
            textView_price = addNewView.buildNewTextView(textView_price, "Price", Color.BLACK);
            textView_vin = addNewView.buildNewTextView(textView_vin, "VIN", Color.BLACK);

            new_vehicle_year = addNewEdittext(1);
            edittext_list_year.add(new_vehicle_year);
            new_vehicle_make = addNewEdittext(2);
            edittext_list_make.add(new_vehicle_make);
            new_vehicle_model = addNewEdittext(3);
            edittext_list_model.add(new_vehicle_model);
            new_vehicle_color = addNewEdittext(4);
            edittext_list_color.add(new_vehicle_color);
            new_vehicle_vin = addNewEdittext(5);
            edittext_list_vin.add(new_vehicle_vin);
            new_vehicle_price = addNewEdittext(6);
            edittext_list_price.add(new_vehicle_price);
            new_vehicle_type = addNewSpinner(7);
            spinner_list_type.add(new_vehicle_type);

            addNewView.addToLayout(ll_year, new View[]{textView_year, new_vehicle_year});
            addNewView.addToLayout(ll_make, new View[]{textView_make, new_vehicle_make});
            addNewView.addToLayout(ll_model, new View[]{textView_model, new_vehicle_model});
            addNewView.addToLayout(ll_type, new View[]{textView_type, new_vehicle_type});
            addNewView.addToLayout(ll_color, new View[]{textView_color, new_vehicle_color});
            addNewView.addToLayout(ll_price, new View[]{textView_price, new_vehicle_price});
            addNewView.addToLayout(ll_vin, new View[]{textView_vin, new_vehicle_vin});

            addNewView.addToLayout(ll1, new View[]{ll_year, ll_make, ll_model});
            addNewView.addToLayout(ll2, new View[]{textView_spinner, spinner});
            addNewView.addToLayout(ll3, new View[]{ll_color, ll_vin, ll_price});

            addNewView.addToLayout(ll, new View[]{textView, ll1, ll2, ll3});

        }
    };

    public EditText addNewEdittext(int vehicle_para){
        EditText edittext = new EditText(getContext());
        edittext.setId(Integer.valueOf(vehicle_num*10 + vehicle_para));
        return edittext;
    }
    public Spinner addNewSpinner(int vehicle_para){
        Spinner spinner = new Spinner(getContext());
        spinner.setId(Integer.valueOf(vehicle_num*10+vehicle_para));
        return spinner;
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
            if(uploadRequest.getVehicles()!=null){

                for(int i = 0; i<uploadRequest.getVehicles().size(); i++){
                    editText_year.setText(dbHandler.getOrderTable(tab).getString(0));
                    editText_make.setText(dbHandler.getOrderTable(tab).getString(0));
                    editText_model.setText(dbHandler.getOrderTable(tab).getString(0));
                    editText_color.setText(dbHandler.getOrderTable(tab).getString(0));
                    editText_vin.setText(dbHandler.getOrderTable(tab).getString(0));
                    editText_price.setText(dbHandler.getOrderTable(tab).getString(0));
                    if(uploadRequest.getVehicles().size()>1){
                        edittext_list_year.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                        edittext_list_make.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                        edittext_list_model.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                        edittext_list_color.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                        edittext_list_vin.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                        edittext_list_price.get(i-1).setText(dbHandler.getOrderTable(tab).getString(0));
                    }
                }
            }
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
