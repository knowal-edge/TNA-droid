package com.knowaledge.tna;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class CreateActionActivity extends AppCompatActivity {

    EditText buyerlEditText,styleNoEditText,orderRefNoEditText,garmentNameEditText,leadDaysEditText,orderConfDateEditText,exFactoryDateEditText;
    private FloatingActionButton submitButton;
    ProgressDialog loading;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_action_creation);
        buyerlEditText = findViewById(R.id.buyer);
        styleNoEditText = findViewById(R.id.styleNo);
        orderRefNoEditText = findViewById(R.id.orderRefNo);
        garmentNameEditText = findViewById(R.id.garmentName);

        leadDaysEditText = findViewById(R.id.leadDays);

        orderConfDateEditText = findViewById(R.id.orderConfirmationDate);

        exFactoryDateEditText = findViewById(R.id.exFactoryDate);
        submitButton = findViewById(R.id.fab);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAction();
            }
        });

    }

    private void createAction() {
        loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);

        final String buyer = buyerlEditText.getText().toString().trim() ;
        final String styleNo = styleNoEditText.getText().toString().trim() ;
        final String orderRefNo = orderRefNoEditText.getText().toString().trim();
        final String garmentName = garmentNameEditText.getText().toString().trim();
        final String leadDays = leadDaysEditText.getText().toString().trim();
        final String orderConfDate = orderConfDateEditText.getText().toString().trim();
        final String exFactoryDate = exFactoryDateEditText.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");
        System.out.println("--sdfdsds"+username);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,CREATEACTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Action Created Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error With creation of Action", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("buyer", buyer);
                params.put("style_no", styleNo);
                params.put("order_ref_no", orderRefNo);
                params.put("garment_name", garmentName);
                params.put("lead_days", leadDays);
                params.put("order_confirmation_date", orderConfDate);
                params.put("ex_factory_date", exFactoryDate);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

