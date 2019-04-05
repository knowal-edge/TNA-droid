package com.knowaledge.tna.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.R;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Activities> activityList;

    //getting the context and product list with constructor
    public ActivityAdapter(Context mCtx, List<Activities> activityList) {
        this.mCtx = mCtx;
        this.activityList = activityList;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.tab_activities, parent,false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final ActivityViewHolder holder, int position) {
        //getting the product of the specified position
        Activities activity = activityList.get(position);

        //binding the data with the viewholder views
        holder.textViewLeadDays.setText(activity.getLeadDays());
        holder.textViewStyleNo.setText(activity.getStyleNo());
        holder.textViewActivity.setText(activity.getActivity());
        holder.textViewTargetDate.setText(activity.getTargetDate());
        holder.textViewOrderConfDateDate.setText(activity.getOrderConfirmationDate());

    }


    @Override
    public int getItemCount() {
        return activityList.size();
    }


    class ActivityViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLeadDays, textViewStyleNo, textViewActivity, textViewTargetDate,textViewOrderConfDateDate;

        public ActivityViewHolder(View itemView) {
            super(itemView);

            textViewLeadDays = itemView.findViewById(R.id.leadDays);
            textViewStyleNo = itemView.findViewById(R.id.styleNo);
            textViewActivity = itemView.findViewById(R.id.activity);
            textViewTargetDate = itemView.findViewById(R.id.targetDate);
            textViewOrderConfDateDate = itemView.findViewById(R.id.orderConfDate);
        }
    }
}