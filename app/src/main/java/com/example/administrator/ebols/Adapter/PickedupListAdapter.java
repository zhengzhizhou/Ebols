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
import com.example.administrator.ebols.Object.PickUpListObject;
import com.example.administrator.ebols.R;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class PickedupListAdapter extends RecyclerView.Adapter<PickedupListAdapter.ViewHolder> {

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
    public List<PickUpListObject> pickUpListObjects;
    public PickedupListAdapter(List<PickUpListObject> pickUpListObjects, Context context, String tab){
        this.pickUpListObjects = pickUpListObjects;
        this.context = context;
        this.tab = tab;
    }
    @Override
    public PickedupListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_archived, parent, false);

        final PickedupListAdapter.ViewHolder viewHolder = new PickedupListAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos =viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), LoadInfoActivity.class);
                intent.putExtra("id", pickUpListObjects.get(selectedPos).list_id);
                intent.putExtra("tab", tab);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PickedupListAdapter.ViewHolder holder, int position) {
        holder.itemAmount_textview.setText(pickUpListObjects.get(position).itemAmount);
        holder.IdLoaded_textview.setText(pickUpListObjects.get(position).list_id);
        holder.startPoint_textview.setText(pickUpListObjects.get(position).startPoint);
        holder.destination_textview.setText(pickUpListObjects.get(position).destination);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return pickUpListObjects.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
