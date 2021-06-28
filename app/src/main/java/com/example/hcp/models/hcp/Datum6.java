package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum6 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mrn_no")
    @Expose
    private String mrn_no;
    @SerializedName("reg_no")
    @Expose
    private String reg_no;
    @SerializedName("patient_type")
    @Expose
    private String patient_type;
    @SerializedName("patient_name")
    @Expose
    private String patient_name;
    @SerializedName("self_cnic")
    @Expose
    private String self_cnic;
    @SerializedName("patient_age")
    @Expose
    private String patient_age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("next_status")
    @Expose
    private String next_status;
    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("is_reg_completed")
    @Expose
    private String is_reg_completed;
    @SerializedName("is_blood_bank_patient")
    @Expose
    private String is_blood_bank_patient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMrn_no() {
        return mrn_no;
    }

    public void setMrn_no(String mrn_no) {
        this.mrn_no = mrn_no;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getPatient_type() {
        return patient_type;
    }

    public void setPatient_type(String patient_type) {
        this.patient_type = patient_type;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getSelf_cnic() {
        return self_cnic;
    }

    public void setSelf_cnic(String self_cnic) {
        this.self_cnic = self_cnic;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getNext_status() {
        return next_status;
    }

    public void setNext_status(String next_status) {
        this.next_status = next_status;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getIs_reg_completed() {
        return is_reg_completed;
    }

    public void setIs_reg_completed(String is_reg_completed) {
        this.is_reg_completed = is_reg_completed;
    }

    public String getIs_blood_bank_patient() {
        return is_blood_bank_patient;
    }

    public void setIs_blood_bank_patient(String is_blood_bank_patient) {
        this.is_blood_bank_patient = is_blood_bank_patient;
    }

}
