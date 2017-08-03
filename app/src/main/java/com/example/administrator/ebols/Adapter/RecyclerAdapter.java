package com.example.administrator.ebols.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.ebols.R;

/**
 * Created by Administrator on 2017/6/29.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static String Tag = "tag";
    private String[] mDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(Tag, "Element"+getAdapterPosition() +" clicked");
                }
            });
            //textView = (TextView)itemView.findViewById(R.id.history_item);
        }
        public TextView getTextView(){
            return textView;
        }
    }
    public RecyclerAdapter(String[] dataSet){
        mDataSet=dataSet;
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(Tag, "Element "+position+" set");
        holder.getTextView().setText(mDataSet[position]);
    }


    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

}
