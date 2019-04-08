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
import com.knowaledge.tna.R;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesViewHolder> {

private Context mCtx;
private String leadDays,StyleNo,activityy,targetdate,orderconfdate,buyer,orderrefno,garmentname,exfactoredate;
private List<Activities> activitiesList;


public ActivitiesAdapter(Context mCtx, List<Activities> activitiesList) {
        this.mCtx = mCtx;
        this.activitiesList = activitiesList;
        }

@Override
public ActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.tab_activities_list, parent,false);
        return new ActivitiesViewHolder(view);
        }

@Override
public void onBindViewHolder(final ActivitiesViewHolder holder, final int position) {
        final Activities activities = activitiesList.get(position);
        holder.textViewLeadDays.setText(activities.getLeadDays());
        holder.textViewStyleNo.setText(activities.getStyleNo());
        holder.textViewBuyer.setText(activities.getBuyer());
        holder.textViewExfactoryDate.setText(activities.getEx_factory_date());
        holder.textViewOrderConfDateDate.setText(activities.getOrderConfirmationDate());

    holder.menuIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //creating a popup menu
            PopupMenu popup = new PopupMenu(mCtx, holder.menuIcon);
            //inflating menu from xml resource
            popup.inflate(R.menu.activity_menu);
            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu1:
                            //handle menu1 click
                            FragmentManager fm1= ((AppCompatActivity)mCtx).getSupportFragmentManager();
                            editActivityDetailsDialog df1 = new editActivityDetailsDialog();
                            df1.show(fm1,"Hi");
                            Activities activities1 = activitiesList.get(position);
                            leadDays =activities1.getLeadDays();
                            StyleNo =activities1.getStyleNo();
                            activityy=activities1.getActivity();
                            targetdate=activities1.getTargetDate();
                            Bundle data1 = new Bundle();
                            data1.putString("leaddays",leadDays);
                            data1.putString("styleno",StyleNo);
                            data1.putString("activity",activityy);
                            data1.putString("targetdate",targetdate);
                            df1.setArguments(data1);
                            break;
                        case R.id.menu2:
                            //handle menu2 click
                            FragmentManager fm = ((AppCompatActivity)mCtx).getSupportFragmentManager();
                            viewActivityDetailsDialog df = new viewActivityDetailsDialog();
                            df.show(fm,"Hello");
                            Activities activities = activitiesList.get(position);
                            leadDays =activities.getLeadDays();
                            StyleNo =activities.getStyleNo();
                            activityy=activities.getActivity();
                            targetdate=activities.getTargetDate();
                            orderconfdate=activities.getOrderConfirmationDate();
                            buyer=activities.getBuyer();
                            orderrefno=activities.getOrder_ref_no();
                            garmentname=activities.getGarment_name();
                            exfactoredate=activities.getEx_factory_date();
                            Bundle data = new Bundle();
                            data.putString("leaddays",leadDays);
                            data.putString("styleno",StyleNo);
                            data.putString("activity",activityy);
                            data.putString("targetdate",targetdate);
                            data.putString("orderconfdate",orderconfdate);
                            data.putString("buyer",buyer);
                            data.putString("orderrefno",orderrefno);
                            data.putString("garmentname",garmentname);
                            data.putString("exfactorydate",exfactoredate);
                            df.setArguments(data);
                            break;

                    }
                    return false;
                }
            });
            //displaying the popup
            popup.show();

        }
    });

        }


@Override
public int getItemCount() {
        return activitiesList.size();
        }


class ActivitiesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewLeadDays, textViewStyleNo, textViewBuyer, textViewExfactoryDate,textViewOrderConfDateDate;

    ImageView menuIcon;


    public ActivitiesViewHolder(View itemView) {
        super(itemView);

        textViewLeadDays = itemView.findViewById(R.id.leadDays);
        textViewStyleNo = itemView.findViewById(R.id.styleNo);
        textViewBuyer = itemView.findViewById(R.id.buyer);
        textViewExfactoryDate = itemView.findViewById(R.id.exfactorydate);
        textViewOrderConfDateDate = itemView.findViewById(R.id.orderConfDate);
        menuIcon = itemView.findViewById(R.id.menus);
    }
}
}
