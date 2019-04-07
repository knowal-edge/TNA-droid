package com.knowaledge.tna.DialogFragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowaledge.tna.R;


/**
 * Created by aramamurthy on 7/18/2016.
 */
public class viewActivityDetailsDialog extends DialogFragment {

    public ImageView close;
    public String leaddays,styleno,activity,buy,ex,order,garment;
    private EditText emp_referal;
    private TextView lead,style,act,buyer,ex_fact,order_refNo,garment_name;
    private Button submit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_activity_dialog, container,
                false);

        lead = (TextView)rootView.findViewById(R.id.leaddays);
        style = (TextView)rootView.findViewById(R.id.styleno);
        act = rootView.findViewById(R.id.activity);
        buyer = rootView.findViewById(R.id.buyer);
        ex_fact = rootView.findViewById(R.id.exFactoryDate);
        order_refNo = rootView.findViewById(R.id.orderRefNo);
        garment_name=rootView.findViewById(R.id.garmentName);
        close = rootView.findViewById(R.id.btn_cancel);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
             leaddays = bundle.getString("leaddays");
             styleno = bundle.getString("styleno");
            activity = bundle.getString("activity");
            buy = bundle.getString("buyer");
            ex = bundle.getString("exfactorydate");
            order = bundle.getString("orderrefno");
            garment = bundle.getString("garmentname");

            lead.setText(leaddays);
            style.setText(styleno);
            act.setText(activity);
            buyer.setText(buy);
            ex_fact.setText(ex);
            order_refNo.setText(order);
            garment_name.setText(garment);

        }



            return rootView;
        }


    }