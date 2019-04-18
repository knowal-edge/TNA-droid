package com.knowaledge.tna.TabFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowaledge.tna.LoginActivity;
import com.knowaledge.tna.R;
import com.knowaledge.tna.SplashActivity;

import java.util.HashMap;
import java.util.Map;

import static com.knowaledge.tna.Constants.URLs.UPDATEACTIVITY;

public class UpdateActivity extends AppCompatActivity {

    public ImageView close;
    public String aid,styleno,activity;
    private EditText targetEdit,statusEdit,actualDateEdit,RevisedDateEdit,ReasonEdit;
    private TextView lead,style,act;
    private FloatingActionButton submit;
    Toolbar toolbar;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_dialog);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        targetEdit=findViewById(R.id.targetDate);
        statusEdit=findViewById(R.id.status);
        actualDateEdit=findViewById(R.id.actualdate);
        RevisedDateEdit=findViewById(R.id.reviseddate);
        ReasonEdit=findViewById(R.id.reason);
        style = (TextView)findViewById(R.id.styleno);
        aid = getIntent().getStringExtra("aid");
        styleno = getIntent().getStringExtra("styleno");
        style.setText(styleno);

        submit=findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateActivity();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateActivity() {
      //  loading = ProgressDialog.show(getApplicationContext(), "Please wait...", "Loading...", false, false);

        final String status = statusEdit.getText().toString().trim() ;
        final String actualDate = actualDateEdit.getText().toString().trim() ;
        final String revisedDate = RevisedDateEdit.getText().toString().trim();
        final String reason = ReasonEdit.getText().toString().trim();
        final String target_date = targetEdit.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");


        StringRequest stringRequest = new StringRequest(Request.Method.PUT,UPDATEACTIVITY+username+"/"+styleno+"/"+aid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // loading.dismiss();

                        Toast.makeText(getApplicationContext(), "Activity updated Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // loading.dismiss();

                        Toast.makeText(getApplicationContext(), "Error With updation of activity", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("target_date", target_date);
                params.put("complete_status", status);
                params.put("actual_date", actualDate);
                params.put("revised_date", revisedDate);
                params.put("reason", reason);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}