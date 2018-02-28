package com.example.administrator.ebols.Fragment.OrderFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.ebols.Adapter.ArchivedListAdapter;
import com.example.administrator.ebols.Adapter.HomeListAdapter;
import com.example.administrator.ebols.Fragment.Initialize;
import com.example.administrator.ebols.Object.ArchiveListObject;
import com.example.administrator.ebols.Object.HomeListObject;
import com.example.administrator.ebols.R;

import java.util.ArrayList;
import java.util.List;

public class ArchivedFragment extends Fragment {

    private Initialize initialize;
    private List<ArchiveListObject> archiveListObjects;
    private ArchivedListAdapter archivedListAdapter;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_archived, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.ArchivedList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        archiveListObjects = new ArrayList<>();
        initialize = new Initialize(getContext(), "archived");
        initialize.setArchiveListObjects(archiveListObjects);
        archiveListObjects = initialize.inializeArchieved();
        archivedListAdapter = new ArchivedListAdapter(archiveListObjects, getContext(), "archived");
        recyclerView.setAdapter(archivedListAdapter);
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
}
