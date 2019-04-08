package com.knowaledge.tna;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import static com.knowaledge.tna.Constants.URLs.SIGNIN;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    ProgressDialog loading;
    EditText usernameEditText,passwordEditText;
    TextView mRegiterpageScreen;
    private Button submitButton;
    int k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
         usernameEditText = findViewById(R.id.username);
          passwordEditText = findViewById(R.id.password);
          mRegiterpageScreen = findViewById(R.id.btnLinkToRegisterScreen);
        submitButton = findViewById(R.id.buttonLogin);

        mRegiterpageScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submitButton) {
                    if (usernameEditText.getText().toString().equals("") && passwordEditText.getText().toString().equals("")){
                        usernameEditText.setError("Username cannot be empty");
                        passwordEditText.setError("Password cannot be empty");
                    }

                    else {
                        loginToTimeAndAction();

                    }}}
        });

    }

    private void loginToTimeAndAction() {
        loading = ProgressDialog.show(this, "Please wait...", "Loading...", false, false);
        final String username = usernameEditText.getText().toString().trim() ;
        final String password = passwordEditText.getText().toString().trim() ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,SIGNIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        System.out.println("sfesfs"+response);
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                        final Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("Username",username );
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error in login the user", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Log.e("My Tags", "onBackPressed");
        k++;
        if (k == 1) {
            Toast.makeText(this, "Please press again to exit", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
    }
}

