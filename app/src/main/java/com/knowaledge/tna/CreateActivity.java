package com.knowaledge.tna;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class CreateActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    EditText styleNoEditText,activityEditText,targetDateEditText;
    private FloatingActionButton submitButton;
    ProgressDialog loading;
    private Toolbar toolbar;
    public String lead_days;
    String[] days = { "30", "45", "60", "90", "100","121","132","141","160","180","227"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_activties_creation);
        activityEditText = findViewById(R.id.activity);
        styleNoEditText = findViewById(R.id.styleNo);

        targetDateEditText = findViewById(R.id.targetDate);
        Spinner spin = (Spinner) findViewById(R.id.leaddays);
        spin.setOnItemSelectedListener(this);

        /* Creating the ArrayAdapter instance having the country list */
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,days);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        submitButton = findViewById(R.id.fab);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submitButton) {
                    if (activityEditText.getText().toString().equals("") && styleNoEditText.getText().toString().equals("")  && targetDateEditText.getText().toString().equals("")){
                        activityEditText.setError("Activity cannot be empty");
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
        final String targetDate = targetDateEditText.getText().toString().trim();
        final String leadDays = lead_days.trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");
        System.out.println("--sdfdsds"+username);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,CREATEACTIVITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        activityEditText.setText("");
                        targetDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Activity Created Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        activityEditText.setText("");
                        targetDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Error With creation of activity", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("activity", Activity);
                params.put("lead_days", leadDays);
                params.put("target_date", targetDate);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        lead_days = days[position].toString();
        Toast.makeText(getApplicationContext(),days[position] , Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

