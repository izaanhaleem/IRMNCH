package com.example.hcp.models.hcp;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class jailObjectModel {

    @SerializedName("patient_id")
    @Expose
    private Integer patient_id;
    @SerializedName("prison_transfer_status")
    @Expose
    private String prison_transfer_status;
    @SerializedName("current_hospital_name")
    @Expose
    private String current_hospital_name;
    @SerializedName("new_hospital_id")
    @Expose
    private Integer new_hospital_id;
    @SerializedName("new_hospital_name")
    @Expose
    private String new_hospital_name;

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPrison_transfer_status() {
        return prison_transfer_status;
    }

    public void setPrison_transfer_status(String prison_transfer_status) {
        this.prison_transfer_status = prison_transfer_status;
    }

    public String getCurrent_hospital_name() {
        return current_hospital_name;
    }

    public void setCurrent_hospital_name(String current_hospital_name) {
        this.current_hospital_name = current_hospital_name;
    }

    public Integer getNew_hospital_id() {
        return new_hospital_id;
    }

    public void setNew_hospital_id(Integer new_hospital_id) {
        this.new_hospital_id = new_hospital_id;
    }

    public String getNew_hospital_name() {
        return new_hospital_name;
    }

    public void setNew_hospital_name(String new_hospital_name) {
        this.new_hospital_name = new_hospital_name;
    }
}
