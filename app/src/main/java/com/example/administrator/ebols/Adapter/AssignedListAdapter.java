package com.example.administrator.ebols.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.ebols.Activity.LoadInfoActivity;
import com.example.administrator.ebols.Object.AssignedListObject;
import com.example.administrator.ebols.R;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class AssignedListAdapter extends RecyclerView.Adapter<AssignedListAdapter.ViewHolder> {

    private int selectedPos = 0;
    private Context context;
    private String tab;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView LoadId_textview;
        TextView IdLoaded_textview;
        TextView itemAmount_textview;
        TextView assignedDriver;

        public ViewHolder(final View itemView) {
            super(itemView);
            LoadId_textview = (TextView)itemView.findViewById(R.id.loadId_assigned);
            IdLoaded_textview =(TextView)itemView.findViewById(R.id.Id_Loaded_assigned);
            itemAmount_textview = (TextView)itemView.findViewById(R.id.itemAmount_assigned);
            assignedDriver =(TextView)itemView.findViewById(R.id.assignedDriver);
        }
    }
    public List<AssignedListObject> assignedListObjects;
    public AssignedListAdapter(List<AssignedListObject> assignedListObjects, Context context, String tab){
        this.assignedListObjects = assignedListObjects;
        this.context = context;
        this.tab = tab;
    }
    @Override
    public AssignedListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_assigned, parent, false);

        final AssignedListAdapter.ViewHolder viewHolder = new AssignedListAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos =viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), LoadInfoActivity.class);
                intent.putExtra("id", assignedListObjects.get(selectedPos).list_id);
                intent.putExtra("tab", tab);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemAmount_textview.setText(assignedListObjects.get(position).itemAmount);
        holder.IdLoaded_textview.setText(assignedListObjects.get(position).list_id);
        holder.itemView.setSelected(selectedPos == position);
        holder.assignedDriver.setText(assignedListObjects.get(position).driverName);
    }

    @Override
    public int getItemCount() {
        return assignedListObjects.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
