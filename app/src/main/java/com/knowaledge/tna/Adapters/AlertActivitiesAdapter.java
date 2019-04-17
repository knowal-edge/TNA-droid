package com.knowaledge.tna.Adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowaledge.tna.DialogFragments.editActivityDetailsDialog;
import com.knowaledge.tna.DialogFragments.viewActivityDetailsDialog;
import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.Models.AlertActivities;
import com.knowaledge.tna.R;

import java.util.List;

public class AlertActivitiesAdapter extends RecyclerView.Adapter<AlertActivitiesAdapter.ActivitiesViewHolder> {

private Context mCtx;
private String leadDays,StyleNo,activityy,targetdate,orderconfdate,buyer,orderrefno,garmentname,exfactoredate;
private List<AlertActivities> activitiesList;


public AlertActivitiesAdapter(Context mCtx, List<AlertActivities> activitiesList) {
        this.mCtx = mCtx;
        this.activitiesList = activitiesList;
        }

@Override
public ActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.tab_alert_activities_list, parent,false);
        return new ActivitiesViewHolder(view);
        }

@Override
public void onBindViewHolder(final ActivitiesViewHolder holder, final int position) {
        final AlertActivities activities = activitiesList.get(position);
    holder.textViewLeadDays.setText(activities.getLeadDays());
    holder.textViewStyleNo.setText(activities.getStyleNo());
    holder.textViewBuyer.setText(activities.getBuyer());
    holder.textViewExfactoryDate.setText(activities.getExFactoryDate());
    holder.textViewOrderConfDateDate.setText(activities.getOrderConfirmationDate());

        }


@Override
public int getItemCount() {
        return activitiesList.size();
        }


class ActivitiesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewLeadDays, textViewStyleNo, textViewBuyer, textViewExfactoryDate,textViewOrderConfDateDate;
    ImageView warningIcon;

    public ActivitiesViewHolder(View itemView) {
        super(itemView);

        textViewLeadDays = itemView.findViewById(R.id.leadDays);
        textViewStyleNo = itemView.findViewById(R.id.styleNo);
        textViewBuyer = itemView.findViewById(R.id.buyer);
        textViewExfactoryDate = itemView.findViewById(R.id.exfactorydate);
        textViewOrderConfDateDate = itemView.findViewById(R.id.orderConfDate);
        warningIcon = itemView.findViewById(R.id.warning);
    }
}
}
