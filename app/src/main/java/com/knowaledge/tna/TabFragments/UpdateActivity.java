package com.knowaledge.tna.TabFragments;

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
import com.knowaledge.tna.R;

import java.util.HashMap;
import java.util.Map;

import static com.knowaledge.tna.Constants.URLs.UPDATEACTIVITY;

public class UpdateActivity extends AppCompatActivity {

    public ImageView close;
    public String aid,styleno,activity;
    private EditText targetEdit,statusEdit,actualDateEdit,RevisedDateEdit,ReasonEdit;
    private TextView lead,style,act;
    private Button submit;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_dialog);
        targetEdit=findViewById(R.id.targetDate);
        statusEdit=findViewById(R.id.status);
        actualDateEdit=findViewById(R.id.actualdate);
        RevisedDateEdit=findViewById(R.id.reviseddate);
        ReasonEdit=findViewById(R.id.reason);
        style = (TextView)findViewById(R.id.styleno);
        act = findViewById(R.id.activity);
        aid = getIntent().getStringExtra("aid");
        styleno = getIntent().getStringExtra("styleno");
        activity = getIntent().getStringExtra("activity");
        style.setText(styleno);
        act.setText(activity);
        submit=findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateActivity();
            }
        });

    }
    private void updateActivity() {
        loading = ProgressDialog.show(getApplicationContext(), "Please wait...", "Loading...", false, false);

        final String status = statusEdit.getText().toString().trim() ;
        final String actualDate = actualDateEdit.getText().toString().trim() ;
        final String revisedDate = RevisedDateEdit.getText().toString().trim();
        final String reason = ReasonEdit.getText().toString().trim();
        final String target_date = targetEdit.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String username =  preferences.getString("username", "");
        System.out.println("--sdfdsds"+aid);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT,UPDATEACTIVITY+username+"/"+styleno+"/"+aid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        Toast.makeText(getApplicationContext(), "Activity updated Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();

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