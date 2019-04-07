package com.knowaledge.tna.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activities {
    private String styleNo;
    private String leadDays;
    private String orderConfirmationDate;
    private String activity;
    private String targetDate;
    private String buyer;
    private String order_ref_no;
    private String garment_name;
    private String ex_factory_date;

    public Activities(  String leadDays,String styleNo,String activity, String targetDate,String orderConfirmationDate,String buyer,String order_ref_no,String garment_name,String ex_factory_date ) {
        this.leadDays = leadDays;
        this.styleNo = styleNo;
        this.activity = activity;
        this.targetDate = targetDate;
        this.orderConfirmationDate = orderConfirmationDate;
        this.buyer = buyer;
        this.order_ref_no = order_ref_no;
        this.garment_name = garment_name;
        this.ex_factory_date = ex_factory_date;
    }


    public String getStyleNo() {
        return styleNo;
    }



    public String getLeadDays() {
        return leadDays;
    }


    public String getOrderConfirmationDate() {
        return orderConfirmationDate;
    }

    public String getActivity() {
        return activity;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public String getBuyer() {
        return buyer;
    }
    public String getOrder_ref_no() {
        return order_ref_no;
    }
    public String getGarment_name() {
        return garment_name;
    }
    public String getEx_factory_date() {
        return ex_factory_date;
    }

}
