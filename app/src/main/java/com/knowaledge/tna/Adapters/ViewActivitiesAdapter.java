package com.knowaledge.tna.Adapters;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowaledge.tna.DialogFragments.editActivityDetailsDialog;
import com.knowaledge.tna.Models.ActivitiesList;
import com.knowaledge.tna.R;
import com.knowaledge.tna.TabFragments.ActivitiesResult;
import com.knowaledge.tna.TabFragments.UpdateActivity;

import java.util.List;

public class ViewActivitiesAdapter extends RecyclerView.Adapter<ViewActivitiesAdapter.ActivitiesViewHolder> {

private Context mCtx;
private String StyleNo,activityy,aid;
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

    holder.menuIcon1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =  new Intent(mCtx, UpdateActivity.class);
            ActivitiesList activities1 = activitiesList.get(position);
            intent.putExtra("aid",activities1.getAid());
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mCtx);
            intent.putExtra("styleno",preferences.getString("styleNum", ""));
            intent.putExtra("activity",activities1.getActivity());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mCtx.startActivity(intent);
           }
    });

}


@Override
public int getItemCount() {
        return activitiesList.size();
        }


class ActivitiesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewActivity;

    ImageView menuIcon1;


    public ActivitiesViewHolder(View itemView) {
        super(itemView);
        textViewActivity = itemView.findViewById(R.id.activity);
       menuIcon1 = itemView.findViewById(R.id.menus);
    }
}
}
