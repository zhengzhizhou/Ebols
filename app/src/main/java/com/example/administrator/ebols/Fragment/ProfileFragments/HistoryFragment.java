package com.example.administrator.ebols.Fragment.ProfileFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.ebols.Adapter.PersonAdapter;
import com.example.administrator.ebols.Object.Person;
import com.example.administrator.ebols.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private View view;
    private OnFragmentInteractionListener mListener;
    private List<Person> persons;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.historyRecycleView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        persons = new ArrayList<>();
        initialize();
        PersonAdapter personAdapter = new PersonAdapter(persons);
        recyclerView.setAdapter(personAdapter);
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

    public void initialize(){
        persons.add(new Person("name1", "age1", R.drawable.girl1));
        persons.add(new Person("name2", "age2", R.drawable.girl2));
        persons.add(new Person("name3", "age3", R.drawable.girl3));
        persons.add(new Person("name4", "age4", R.drawable.girl4));
        persons.add(new Person("name5", "age5", R.drawable.girl5));
    }
}
