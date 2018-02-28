package com.example.administrator.ebols.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.ebols.Activity.LoadInfoActivity;
import com.example.administrator.ebols.Object.ArchiveListObject;
import com.example.administrator.ebols.Object.AssignedListObject;
import com.example.administrator.ebols.Object.BilledListObject;
import com.example.administrator.ebols.R;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class BilledListAdapter extends RecyclerView.Adapter<BilledListAdapter.ViewHolder> {

    private int selectedPos = 0;
    private Context context;
    Bus bus;
    private String tab;
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
    public List<BilledListObject> billedListObjects;
    public BilledListAdapter(List<BilledListObject> billedListObjects, Context context, String tab){
        this.billedListObjects = billedListObjects;
        this.context = context;
        this.tab = tab;
    }
    @Override
    public BilledListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_archived, parent, false);

        final BilledListAdapter.ViewHolder viewHolder = new BilledListAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos =viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), LoadInfoActivity.class);
                intent.putExtra("id", billedListObjects.get(selectedPos).list_id);
                intent.putExtra("tab", tab);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BilledListAdapter.ViewHolder holder, int position) {
        holder.itemAmount_textview.setText(billedListObjects.get(position).itemAmount);
        holder.IdLoaded_textview.setText(billedListObjects.get(position).list_id);
        holder.startPoint_textview.setText(billedListObjects.get(position).startPoint);
        holder.destination_textview.setText(billedListObjects.get(position).destination);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return billedListObjects.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
