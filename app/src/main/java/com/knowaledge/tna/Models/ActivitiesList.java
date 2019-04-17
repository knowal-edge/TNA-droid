package com.knowaledge.tna.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivitiesList {

    @SerializedName("aid")
    @Expose
    private String aid;
    @SerializedName("activity")
    @Expose
    private String activity;

    public ActivitiesList(String act,String aid) {
        this.activity = act;
        this.aid = aid;
    }

    public String getActivity() {
        return activity;
    }
    public String getAid() {
        return aid;
    }
}