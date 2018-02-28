package com.example.administrator.ebols.Fragment.OrderFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.ebols.Adapter.HomeListAdapter;
import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.TableData.OrderTableListBuilder;
import com.example.administrator.ebols.Fragment.Initialize;
import com.example.administrator.ebols.Object.HomeListObject;
import com.example.administrator.ebols.RetrofitClass.GetOrder;
import com.example.administrator.ebols.R;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class NewOrderFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private HomeListAdapter homeListAdapter;
    private Initialize initialize;
    public RecyclerView recyclerView;
    public GetOrder getOrder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_order, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.NewOrderList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
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
        loadList();
    }

    private void loadList() {

        List<HomeListObject> homeListObjects = new ArrayList<>();
        initialize = new Initialize(getContext(), "newLoad");
        initialize.setHomeListObjects(homeListObjects);
        homeListObjects = initialize.initializeNewOrder();
        homeListAdapter = new HomeListAdapter(homeListObjects, getContext(), "newLoad");
        recyclerView.setAdapter(homeListAdapter);
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
