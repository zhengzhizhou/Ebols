package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.example.administrator.ebols.Fragment.SignUpFragments.*;
import com.example.administrator.ebols.Fragment.NewOrderFragments.CustomFragment;
import com.example.administrator.ebols.R;

public class Vehicle_Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button button, continue_btn;
    private int vehicle_num =1;
    private EditText editText_year, editText_make, editText_model, editText_color, editText_vin, editText_price;
    private TextView textView_spinner, textView_year, textView_make, textView_model, textView_color, textView_price, textView_vin;
    private LinearLayout ll_year, ll_make, ll_model, ll_color, ll_vin, ll_price, ll1, ll2, ll3, ll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout)view.findViewById(R.id.ll);
        button = (Button)view.findViewById(R.id.add_vehicle);
        continue_btn = (Button)view.findViewById(R.id.continue_btn_vehicle);
        button.setOnClickListener(mClickListener);
        continue_btn.setOnClickListener(mContinueClickListener);
    }

    private View.OnClickListener mContinueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new CustomFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.newOrder_frame, fragment);
            fragmentTransaction.commit();
        }
    };
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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

            textView_spinner = addNewView.buildNewTextView(textView_spinner, "Type", Color.BLACK);
            textView_year = addNewView.buildNewTextView(textView_year, "Year", Color.BLACK);
            textView_make = addNewView.buildNewTextView(textView_make, "Make", Color.BLACK);
            textView_model = addNewView.buildNewTextView(textView_model, "Model", Color.BLACK);
            textView_color = addNewView.buildNewTextView(textView_color, "Color", Color.BLACK);
            textView_price = addNewView.buildNewTextView(textView_price, "Price", Color.BLACK);
            textView_vin = addNewView.buildNewTextView(textView_vin, "VIN", Color.BLACK);

            editText_year = addNewView.buildNewEditText(editText_year, 5, 0, 0, 0, R.drawable.edittext_border);
            editText_make = addNewView.buildNewEditText(editText_year, 5, 0, 0, 0, R.drawable.edittext_border);
            editText_model = addNewView.buildNewEditText(editText_model, 5, 0, 0, 0, R.drawable.edittext_border);
            editText_color = addNewView.buildNewEditText(editText_color, 5, 0, 0, 0, R.drawable.edittext_border);
            editText_vin = addNewView.buildNewEditText(editText_vin, 5, 0, 0, 0, R.drawable.edittext_border);
            editText_price = addNewView.buildNewEditText(editText_price, 5, 0, 0, 0, R.drawable.edittext_border);

            addNewView.addToLayout(ll_year, new View[]{textView_year, editText_year});
            addNewView.addToLayout(ll_make, new View[]{textView_make, editText_make});
            addNewView.addToLayout(ll_model, new View[]{textView_model, editText_model});
            addNewView.addToLayout(ll_color, new View[]{textView_color, editText_color});
            addNewView.addToLayout(ll_price, new View[]{textView_price, editText_price});
            addNewView.addToLayout(ll_vin, new View[]{textView_vin, editText_vin});

            addNewView.addToLayout(ll1, new View[]{ll_year, ll_make, ll_model});
            addNewView.addToLayout(ll2, new View[]{textView_spinner, spinner});
            addNewView.addToLayout(ll3, new View[]{ll_color, ll_vin, ll_price});

            addNewView.addToLayout(ll, new View[]{textView, ll1, ll2, ll3});

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
