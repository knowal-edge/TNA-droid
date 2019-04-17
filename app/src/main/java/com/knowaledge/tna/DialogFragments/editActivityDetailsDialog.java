package com.knowaledge.tna.DialogFragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import static com.knowaledge.tna.Constants.URLs.CREATEACTIVITY;
import static com.knowaledge.tna.Constants.URLs.UPDATEACTIVITY;


/**
 * Created by aramamurthy on 7/18/2016.
 */
public class editActivityDetailsDialog extends DialogFragment {

    public ImageView close;
    public String aid,styleno,activity;
    private EditText targetEdit,statusEdit,actualDateEdit,RevisedDateEdit,ReasonEdit;
    private TextView lead,style,act;
    private Button submit;
    ProgressDialog loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_activity_dialog, container,
                false);
        targetEdit=rootView.findViewById(R.id.targetDate);
        statusEdit=rootView.findViewById(R.id.status);
        actualDateEdit=rootView.findViewById(R.id.actualdate);
        RevisedDateEdit=rootView.findViewById(R.id.reviseddate);
        ReasonEdit=rootView.findViewById(R.id.reason);
        style = (TextView)rootView.findViewById(R.id.styleno);
        act = rootView.findViewById(R.id.activity);
        close = rootView.findViewById(R.id.btn_cancel);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        submit=rootView.findViewById(R.id.submit);

        Bundle bundle = getArguments();
        if (bundle != null) {
             aid = bundle.getString("aid");
             styleno = bundle.getString("styleno");
            activity = bundle.getString("activity");
            style.setText(styleno);
            act.setText(activity);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateActivity();
            }
        });


            return rootView;
        }
    private void updateActivity() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Loading...", false, false);

        final String status = statusEdit.getText().toString().trim() ;
        final String actualDate = actualDateEdit.getText().toString().trim() ;
        final String revisedDate = RevisedDateEdit.getText().toString().trim();
        final String reason = ReasonEdit.getText().toString().trim();
        final String target_date = targetEdit.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String username =  preferences.getString("username", "");
        System.out.println("--sdfdsds"+aid);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT,UPDATEACTIVITY+username+"/"+styleno+"/"+aid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        Toast.makeText(getActivity(), "Activity updated Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();

                        Toast.makeText(getActivity(), "Error With updation of activity", Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    }