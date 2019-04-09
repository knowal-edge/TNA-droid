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

public class CreateActionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText buyerlEditText,styleNoEditText,orderRefNoEditText,garmentNameEditText,orderConfDateEditText,exFactoryDateEditText;
    private FloatingActionButton submitButton;
    ProgressDialog loading;
    private Toolbar toolbar;
    public String lead_days;
    Spinner spinner_keymanagement;
    String[] days = { "30", "45", "60", "90", "100","121","132","141","160","180","227"};


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
        Spinner spin = (Spinner) findViewById(R.id.leaddays);
        spin.setOnItemSelectedListener(this);

        /* Creating the ArrayAdapter instance having the country list */
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,days);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        orderConfDateEditText = findViewById(R.id.orderConfirmationDate);

        exFactoryDateEditText = findViewById(R.id.exFactoryDate);
        submitButton = findViewById(R.id.fab);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submitButton) {
                    if (buyerlEditText.getText().toString().equals("") && styleNoEditText.getText().toString().equals("") && orderRefNoEditText.getText().toString().equals("") && garmentNameEditText.getText().toString().equals("") && orderConfDateEditText.getText().toString().equals("") && exFactoryDateEditText.getText().toString().equals("")){
                        buyerlEditText.setError("Buyer cannot be empty");
                        styleNoEditText.setError("Styleno cannot be empty");
                        orderRefNoEditText.setError("Order ref no. cannot be empty");
                        garmentNameEditText.setError("garment name cannot be empty");
                        orderConfDateEditText.setError("order conf. date cannot be empty");
                        exFactoryDateEditText.setError("Ex-factory date cannot be empty");
                    }

                    else {
                        createAction();
                    }}
            }
        });

    }

    private void createAction() {
        loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);

        final String buyer = buyerlEditText.getText().toString().trim() ;
        final String styleNo = styleNoEditText.getText().toString().trim() ;
        final String orderRefNo = orderRefNoEditText.getText().toString().trim();
        final String garmentName = garmentNameEditText.getText().toString().trim();
        final String leadDays = lead_days.trim();
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
                        buyerlEditText.setText("");
                        styleNoEditText.setText("");
                        orderRefNoEditText.setText("");
                        garmentNameEditText.setText("");
                        orderConfDateEditText.setText("");
                        exFactoryDateEditText.setText("");
                        Toast.makeText(getApplicationContext(), "Action Created Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        buyerlEditText.setText("");
                        styleNoEditText.setText("");
                        orderRefNoEditText.setText("");
                        garmentNameEditText.setText("");
                        orderConfDateEditText.setText("");
                        exFactoryDateEditText.setText("");
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

