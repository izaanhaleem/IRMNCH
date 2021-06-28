package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data1 {
    @SerializedName("patient_id")
    @Expose
    private Integer patient_id;
    @SerializedName("mobile_id")
    @Expose
    private Integer mobile_id;

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Integer mobile_id) {
        this.mobile_id = mobile_id;
    }
}
