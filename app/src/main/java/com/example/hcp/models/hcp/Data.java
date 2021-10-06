package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("mrn_no")
    @Expose
    private String mrn_no;
    @SerializedName("patient_name")
    @Expose
    private String patient_name;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("hf_name")
    @Expose
    private String hf_name;
    @SerializedName("mobile_id")
    @Expose
    private Integer mobile_id;

    public String getMrn_no() {
        return mrn_no;
    }

    public void setMrn_no(String mrn_no) {
        this.mrn_no = mrn_no;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public Integer getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Integer mobile_id) {
        this.mobile_id = mobile_id;
    }









    @SerializedName("patient_id")
    @Expose
    private Integer patient_id;
//    @SerializedName("mrn_no")
//    @Expose
//    private String mrn_no;
//    @SerializedName("mobile_id")
//    @Expose
//    private Integer mobile_id;
//    @SerializedName("pid")
//    @Expose
//    private Integer pid;
//
//    @SerializedName("stage")
//    @Expose
//    private String stage;
//    @SerializedName("hf_name")
//    @Expose
//    private String hf_name;
//
//    public String getStage() {
//        return stage;
//    }
//
//    public void setStage(String stage) {
//        this.stage = stage;
//    }
//
//    public String getHf_name() {
//        return hf_name;
//    }
//
//    public void setHf_name(String hf_name) {
//        this.hf_name = hf_name;
//    }
//
//    public Integer getPid() {
//        return pid;
//    }
//
//    public void setPid(Integer pid) {
//        this.pid = pid;
//    }
//
    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
//
//    public String getMrn_no() {
//        return mrn_no;
//    }
//
//    public void setMrn_no(String mrn_no) {
//        this.mrn_no = mrn_no;
//    }
//
//    public Integer getMobile_id() {
//        return mobile_id;
//    }
//
//    public void setMobile_id(Integer mobile_id) {
//        this.mobile_id = mobile_id;
//    }

}
