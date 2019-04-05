package com.knowaledge.tna.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activities {
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
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("target_date")
    @Expose
    private String targetDate;

    public Activities(String buyer, String styleNo, String orderRefNo, String garmentName, String leadDays, String orderConfirmationDate, String exFactoryDate, String targetDate,String activity) {
        this.buyer = buyer;
        this.styleNo = styleNo;
        this.orderRefNo = orderRefNo;
        this.garmentName = garmentName;
        this.leadDays = leadDays;
        this.orderConfirmationDate = orderConfirmationDate;
        this.exFactoryDate = exFactoryDate;
        this.targetDate = targetDate;
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

    public String getActivity() {
        return activity;
    }

    public String getTargetDate() {
        return targetDate;
    }


}
