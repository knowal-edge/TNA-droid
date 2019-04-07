package com.knowaledge.tna.TabFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import com.knowaledge.tna.Adapters.ActivitiesAdapter;
import com.knowaledge.tna.CreateActionActivity;
import com.knowaledge.tna.CreateActivity;
import com.knowaledge.tna.LoginActivity;
import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.R;
import com.knowaledge.tna.SplashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.knowaledge.tna.Constants.URLs.SHOWACTIVITY;


/**
 * Created by ananth on 10/23/2016.
 */
public class ActivitiesTabFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    List<Activities> activityList;
    ActivitiesAdapter adapter;
    View rootview;
    private Boolean isFabOpen = false;
    private ProgressDialog mProgressDialog;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tab_activities, container, false);
        fab = rootview.findViewById(R.id.fab);
        fab1 = rootview.findViewById(R.id.fab1);
        fab2 = rootview.findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activityList = new ArrayList<>();
        loadActivities();
        return rootview;

    }


    private void loadActivities() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String username =  preferences.getString("username", "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SHOWACTIVITY+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                System.out.println("s-sdfds"+obj);

                                activityList.add(new Activities(
                                        obj.getString("lead_days"),
                                        obj.getString("style_no"),
                                        obj.getString("activity"),
                                        obj.getString("target_date"),
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

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                final Intent i = new Intent(getActivity(), CreateActionActivity.class);
                startActivity(i);
                break;
            case R.id.fab2:
                final Intent j = new Intent(getActivity(), CreateActivity.class);
                startActivity(j);
                break;
           }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Action", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Action","open");

        }
    }

}
