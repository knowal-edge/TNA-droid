package com.knowaledge.tna.Models;

public class AlertActivities {
    private String styleNo;
    private String leadDays;
    private String activity;
    private String targetDate;

    public AlertActivities(String leadDays, String styleNo, String activity, String targetDate ) {
        this.leadDays = leadDays;
        this.styleNo = styleNo;
        this.activity = activity;
        this.targetDate = targetDate;
       }


    public String getStyleNo() {
        return styleNo;
    }



    public String getLeadDays() {
        return leadDays;
    }



    public String getActivity() {
        return activity;
    }

    public String getTargetDate() {
        return targetDate;
    }


}
