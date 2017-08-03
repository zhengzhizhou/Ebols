package com.example.administrator.ebols.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ebols.Activity.LoadInfoActivity;
import com.example.administrator.ebols.Object.HomeListObject;
import com.example.administrator.ebols.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private int selectedPos = 0;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView LoadId_textview;
        TextView IdLoaded_textview;
        TextView itemAmount_textview;
        TextView from_textview;
        TextView to_textview;
        TextView startPoint_textview;
        TextView destination_textview;

        public ViewHolder(final View itemView) {
            super(itemView);
            LoadId_textview = (TextView)itemView.findViewById(R.id.loadId);
            IdLoaded_textview =(TextView)itemView.findViewById(R.id.Id_Loaded);
            itemAmount_textview = (TextView)itemView.findViewById(R.id.itemAmount);
            from_textview =(TextView)itemView.findViewById(R.id.from);
            to_textview = (TextView)itemView.findViewById(R.id.to);
            startPoint_textview = (TextView)itemView.findViewById(R.id.startPoint);
            destination_textview = (TextView)itemView.findViewById(R.id.destination);

        }
    }
    public List<HomeListObject> homeListObject;
    public HomeListAdapter(List<HomeListObject> homeListObject, Context context){
        this.homeListObject = homeListObject;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_listitem, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPos);
                Intent intent = new Intent(view.getContext(), LoadInfoActivity.class);
                context.startActivity(intent);
            }
        });
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemAmount_textview.setText(homeListObject.get(position).itemAmount);
        holder.IdLoaded_textview.setText(homeListObject.get(position).Id);
        holder.startPoint_textview.setText(homeListObject.get(position).startPoint);
        holder.destination_textview.setText(homeListObject.get(position).destination);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return homeListObject.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
