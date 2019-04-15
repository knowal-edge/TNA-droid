package com.knowaledge.tna.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivitiesList {

    private String id;
    @SerializedName("activity")
    @Expose
    private String activity;

    public ActivitiesList(String act) {
        this.activity = act;
    }

    public String getActivity() {
        return activity;
    }
}