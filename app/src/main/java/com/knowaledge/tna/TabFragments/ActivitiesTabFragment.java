package com.knowaledge.tna.TabFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowaledge.tna.Adapters.ActivitiesAdapter;
import com.knowaledge.tna.CreateActionActivity;
import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.knowaledge.tna.Constants.URLs.SHOWACTIVITY;


/**
 * Created by ananth on 10/23/2016.
 */
public class ActivitiesTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    List<Activities> activityList;
    ActivitiesAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    View rootview;
    private Boolean isFabOpen = false;
    private ProgressDialog mProgressDialog;
    private com.github.clans.fab.FloatingActionButton  fab1;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tab_activities, container, false);
        fab1 = rootview.findViewById(R.id.fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent j = new Intent(getActivity(), CreateActionActivity.class);
                startActivity(j);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activityList = new ArrayList<>();
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SHOWACTIVITY+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        swipeRefreshLayout.setRefreshing(false);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);



                                activityList.add(new Activities(
                                        obj.getString("lead_days"),
                                        obj.getString("style_no"),
                                        obj.getString("order_confirmation_date"),
                                        obj.getString("buyer"),
                                        obj.getString("order_ref_no"),
                                        obj.getString("garment_name"),
                                        obj.getString("ex_factory_date")
                                        ));


                            }

                            adapter = new ActivitiesAdapter(getActivity(), activityList);
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
