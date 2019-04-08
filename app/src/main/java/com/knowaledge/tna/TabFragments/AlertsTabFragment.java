package com.knowaledge.tna.TabFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowaledge.tna.Adapters.AlertActivitiesAdapter;
import com.knowaledge.tna.CreateActionActivity;
import com.knowaledge.tna.CreateActivity;
import com.knowaledge.tna.Models.AlertActivities;
import com.knowaledge.tna.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.knowaledge.tna.Constants.URLs.SHOWALERTACTIVITY;
import static java.lang.Integer.parseInt;


/**
 * Created by ananth on 10/23/2016.
 */
public class AlertsTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    List<AlertActivities> activityList;
    private SwipeRefreshLayout swipeRefreshLayout;
    AlertActivitiesAdapter adapter;

    View rootview;
    private Boolean isFabOpen = false;
    private ProgressDialog mProgressDialog;

    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tab_alert_activities, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activityList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        loadActivities();
                                    }
                                }
        );
        return rootview;

    }

    @Override
    public void onRefresh() {
        loadActivities();
        activityList.clear();
    }

    private void loadActivities() {
        swipeRefreshLayout.setRefreshing(true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String username =  preferences.getString("username", "");

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

// manipulate date
       // cal.add(Calendar.YEAR, 1);
      //  cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -5);
      //  cal.add(Calendar.DAY_OF_MONTH, 1);

// convert calendar to date
        Date modifiedDate = cal.getTime();
        System.out.println("sdds"+modifiedDate);

        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        /*int strDate= parseInt(formatter.format(date))-5;
      System.out.println("wrgere"+ strDate);
        Date date1 = new Date();
        */SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        String sDate = formatter1.format(modifiedDate);
        System.out.println("wrgere"+ sDate);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SHOWALERTACTIVITY+username+"/"+sDate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        swipeRefreshLayout.setRefreshing(false);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                System.out.println("s-sdfds"+obj);

                                activityList.add(new AlertActivities(
                                        obj.getString("lead_days"),
                                        obj.getString("style_no"),
                                        obj.getString("activity"),
                                        obj.getString("target_date")


                                ));


                            }

                            adapter = new AlertActivitiesAdapter(getActivity(), activityList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



}
