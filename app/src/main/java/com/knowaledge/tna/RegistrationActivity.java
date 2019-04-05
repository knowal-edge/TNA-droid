package com.knowaledge.tna;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static com.knowaledge.tna.Constants.URLs.SIGNUP;


public class RegistrationActivity extends AppCompatActivity {

    EditText usernameEditText,emailEditText,passwordEditText,buyerEditText;
    TextView mloginback;
    private Button submitButton;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        buyerEditText = findViewById(R.id.buyer);
        mloginback = findViewById(R.id.btnLinkToLoginScreen);
        submitButton = findViewById(R.id.buttonLogin);

        mloginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerToTimeAndAction();
            }
        });

    }

    private void registerToTimeAndAction() {
        loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);
        final String username = usernameEditText.getText().toString().trim() ;
        final String email = emailEditText.getText().toString().trim() ;
        final String password = passwordEditText.getText().toString().trim() ;
        final String buyer = buyerEditText.getText().toString().trim();
        System.out.println("sfdssd"+email);
        System.out.println("qwrwqr"+password);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        System.out.println("sfesfs"+response);
                        Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_LONG).show();
                        final Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error With Registration", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("buyer", buyer);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

