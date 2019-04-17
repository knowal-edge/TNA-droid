package com.knowaledge.tna.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlertActivities {
    @SerializedName("buyer")
    @Expose
    private String buyer;
    @SerializedName("style_no")
    @Expose
    private String styleNo;
    @SerializedName("order_ref_no")
    @Expose
    private String orderRefNo;
    @SerializedName("garment_name")
    @Expose
    private String garmentName;
    @SerializedName("lead_days")
    @Expose
    private String leadDays;
    @SerializedName("order_confirmation_date")
    @Expose
    private String orderConfirmationDate;
    @SerializedName("ex_factory_date")
    @Expose
    private String exFactoryDate;

    public AlertActivities(String lead_days, String style_no, String order_confirmation_date, String buyer, String order_ref_no, String garment_name, String ex_factory_date) {
        this.leadDays = lead_days;
        this.styleNo = style_no;
        this.orderConfirmationDate = order_confirmation_date;
        this.buyer = buyer;
        this.orderRefNo = order_ref_no;
        this.garmentName = garment_name;
        this.exFactoryDate = ex_factory_date;

    }


    public String getBuyer() {
        return buyer;
    }



    public String getStyleNo() {
        return styleNo;
    }



    public String getOrderRefNo() {
        return orderRefNo;
    }


    public String getGarmentName() {
        return garmentName;
    }


    public String getLeadDays() {
        return leadDays;
    }



    public String getOrderConfirmationDate() {
        return orderConfirmationDate;
    }


    public String getExFactoryDate() {
        return exFactoryDate;
    }

}
