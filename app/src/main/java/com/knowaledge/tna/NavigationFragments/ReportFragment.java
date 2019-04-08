package com.knowaledge.tna.NavigationFragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import static com.knowaledge.tna.Constants.URLs.CREATEACTIVITY;
import static com.knowaledge.tna.Constants.URLs.REPORTACTIVITY;

public class ReportFragment extends Fragment {
    private View rootview;
    private WebView webview;
    private ProgressBar pbar;
    EditText styleNoEditText,emailEditText;
    private Button submitButton;
    ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_activity_reports, container, false);
        styleNoEditText=rootview.findViewById(R.id.styleno);
        emailEditText=rootview.findViewById(R.id.email);
        submitButton=rootview.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == submitButton) {
                    if (emailEditText.getText().toString().equals("") && styleNoEditText.getText().toString().equals("")){
                        emailEditText.setError("Email cannot be empty");
                        styleNoEditText.setError("Styleno cannot be empty");
                    }

                    else {
                        generateReport();
                    }}
            }
        });
        return rootview;

    }
    private void generateReport() {
            loading = ProgressDialog.show(getActivity(), "Please wait...", "Loading...", false, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String username =  preferences.getString("username", "");
        final String styleNo = styleNoEditText.getText().toString().trim() ;
        final String email = emailEditText.getText().toString().trim();
        String url = REPORTACTIVITY +username+"/"+styleNo+"/"+email ;
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    loading.dismiss();
                    emailEditText.setText("");
                    styleNoEditText.setText("");
              Toast.makeText(getActivity(), "Report sent to your mail", Toast.LENGTH_LONG).show();
//
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "Check the internet connection and try again", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                            emailEditText.setText("");
                            styleNoEditText.setText("");

                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }
    }