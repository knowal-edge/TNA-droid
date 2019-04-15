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
import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.Models.ActivitiesList;
import com.knowaledge.tna.R;

import java.util.List;

public class ViewActivitiesAdapter extends RecyclerView.Adapter<ViewActivitiesAdapter.ActivitiesViewHolder> {

private Context mCtx;
private String leadDays,StyleNo,activityy,targetdate,orderconfdate,buyer,orderrefno,garmentname,exfactoredate;
private List<ActivitiesList> activitiesList;


public ViewActivitiesAdapter(Context mCtx, List<ActivitiesList> activitiesList) {
        this.mCtx = mCtx;
        this.activitiesList = activitiesList;
        }

@Override
public ActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.view_activities_list, parent,false);
        return new ActivitiesViewHolder(view);
        }

@Override
public void onBindViewHolder(final ActivitiesViewHolder holder, final int position) {
        final ActivitiesList activities = activitiesList.get(position);
        holder.textViewActivity.setText(activities.getActivity());
    holder.menuIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //creating a popup menu
            PopupMenu popup = new PopupMenu(mCtx, holder.menuIcon);
            //inflating menu from xml resource
            popup.inflate(R.menu.activity_menu_view);
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
                          //  ActivitiesList activities1 = activitiesList.get(position);
                           /* leadDays =activities1.getLeadDays();
                            StyleNo =activities1.getStyleNo();
                            activityy=activities1.getActivity();
                            targetdate=activities1.getTargetDate();
                            Bundle data1 = new Bundle();
                            data1.putString("leaddays",leadDays);
                            data1.putString("styleno",StyleNo);
                            data1.putString("activity",activityy);
                            data1.putString("targetdate",targetdate);
                            df1.setArguments(data1);

                           */ break;
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

    TextView textViewActivity;

    ImageView menuIcon;


    public ActivitiesViewHolder(View itemView) {
        super(itemView);
        textViewActivity = itemView.findViewById(R.id.activity);
       menuIcon = itemView.findViewById(R.id.menus);
    }
}
}
