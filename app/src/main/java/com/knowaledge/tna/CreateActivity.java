package com.knowaledge.tna;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.knowaledge.tna.Constants.URLs.CREATEACTION;
import static com.knowaledge.tna.Constants.URLs.CREATEACTIVITY;

public class CreateActivity extends AppCompatActivity {

    EditText styleNoEditText,activityEditText,leadDaysEditText,targetDateEditText;
    private FloatingActionButton submitButton;
    ProgressDialog loading;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_activties_creation);
        activityEditText = findViewById(R.id.activity);
        styleNoEditText = findViewById(R.id.styleNo);
        leadDaysEditText = findViewById(R.id.leaddays);

        targetDateEditText = findViewById(R.id.targetDate);

        submitButton = findViewById(R.id.fab);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submitButton) {
                    if (activityEditText.getText().toString().equals("") && styleNoEditText.getText().toString().equals("") && leadDaysEditText.getText().toString().equals("") && targetDateEditText.getText().toString().equals("")){
                        activityEditText.setError("Activity cannot be empty");
                        styleNoEditText.setError("Styleno cannot be empty");
                        leadDaysEditText.setError("Lead days cannot be empty");
                        targetDateEditText.setError("Target date cannot be empty");

                    }

                    else {
                        createActivity();
                    }}
            }
        });

    }

    private void createActivity() {
        loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);

        final String Activity = activityEditText.getText().toString().trim() ;
        final String styleNo = styleNoEditText.getText().toString().trim() ;
        final String targetDate = targetDateEditText.getText().toString().trim();
        final String leadDays = leadDaysEditText.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");
        System.out.println("--sdfdsds"+username);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,CREATEACTIVITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        activityEditText.setText("");
                        styleNoEditText.setText("");
                        leadDaysEditText.setText("");
                        targetDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Activity Created Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        activityEditText.setText("");
                        styleNoEditText.setText("");
                        leadDaysEditText.setText("");
                        targetDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Error With creation of activity", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("style_no", styleNo);
                params.put("activity", Activity);
                params.put("lead_days", leadDays);
                params.put("target_date", targetDate);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

