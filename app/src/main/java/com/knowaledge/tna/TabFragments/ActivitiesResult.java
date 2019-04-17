package com.knowaledge.tna.TabFragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowaledge.tna.Adapters.ActivitiesAdapter;
import com.knowaledge.tna.Adapters.ViewActivitiesAdapter;
import com.knowaledge.tna.Models.Activities;
import com.knowaledge.tna.Models.ActivitiesList;
import com.knowaledge.tna.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.knowaledge.tna.Constants.URLs.SHOWACTIVITY;
import static com.knowaledge.tna.Constants.URLs.SHOWACTIVITYVIE;

public class ActivitiesResult extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    List<ActivitiesList> activityList;
    ViewActivitiesAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public String styleno,activity;
    private EditText emp_referal;
    private Toolbar toolbar;
    private TextView lead,style,act,buyer,ex_fact,order_refNo,garment_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activities);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        style = (TextView)findViewById(R.id.styleNo);
        act = (TextView)findViewById(R.id.activity);


            styleno = getIntent().getStringExtra("StyleNo");
            activity = getIntent().getStringExtra("activity");
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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


    }

    @Override
    public void onRefresh() {
        loadActivities();
        activityList.clear();
    }



    private void loadActivities() {
        swipeRefreshLayout.setRefreshing(true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SHOWACTIVITYVIE+username+"/"+styleno,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        swipeRefreshLayout.setRefreshing(false);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                System.out.println("s-sdfds"+obj);

                                activityList.add(new ActivitiesList(
                                        obj.getString("activity"),
                                        obj.getString("aid")
                                ));


                            }

                            adapter = new ViewActivitiesAdapter(getApplicationContext(), activityList);
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}

