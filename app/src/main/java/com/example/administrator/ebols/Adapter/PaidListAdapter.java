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
import com.example.administrator.ebols.Object.PaidListObject;
import com.example.administrator.ebols.R;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class PaidListAdapter extends RecyclerView.Adapter<PaidListAdapter.ViewHolder> {

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
    public List<PaidListObject> paidListObjects;
    public PaidListAdapter(List<PaidListObject> paidListObjects, Context context, String tab){
        this.paidListObjects = paidListObjects;
        this.context = context;
        this.tab = tab;
    }
    @Override
    public PaidListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_archived, parent, false);

        final PaidListAdapter.ViewHolder viewHolder = new PaidListAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos =viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), LoadInfoActivity.class);
                intent.putExtra("id", paidListObjects.get(selectedPos).list_id);
                intent.putExtra("tab", tab);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PaidListAdapter.ViewHolder holder, int position) {
        holder.itemAmount_textview.setText(paidListObjects.get(position).itemAmount);
        holder.IdLoaded_textview.setText(paidListObjects.get(position).list_id);
        holder.startPoint_textview.setText(paidListObjects.get(position).startPoint);
        holder.destination_textview.setText(paidListObjects.get(position).destination);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return paidListObjects.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
