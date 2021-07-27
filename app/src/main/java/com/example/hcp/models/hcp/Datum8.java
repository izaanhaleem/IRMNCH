package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum8 {

    @SerializedName("patient_id")
    @Expose
    private String patient_id;
    @SerializedName("mobile_id")
    @Expose
    private Integer mobile_id;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Integer mobile_id) {
        this.mobile_id = mobile_id;
    }

}
