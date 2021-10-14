package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class addPatientRequest  {

    @SerializedName("patient_id")
    @Expose
    public Integer patient_id;

    @SerializedName("patient_name")
    @Expose
    public String patient_name;

    @SerializedName("Lname")
    @Expose
    public String lname;

    @SerializedName("father_name")
    @Expose
    public String father_name;

    @SerializedName("patient_age")
    @Expose
    public Integer patient_age;

    @SerializedName("patient_dob")
    @Expose
    public String patient_dob;

    @SerializedName("gender")
    @Expose
    public Integer gender;

    @SerializedName("self_cnic")
    @Expose
    public String self_cnic;

    @SerializedName("contact_no_self")
    @Expose
    public String contact_no_self;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("marital_status")
    @Expose
    public String marital_status;

    @SerializedName("occupation")
    @Expose
    public String occupation;

    @SerializedName("qualification")
    @Expose
    public String qualification;

    @SerializedName("patient_age_80")
    @Expose
    public String patient_age_80;

    @SerializedName("previous_hbv")
    @Expose
    public String previous_hbv;

    @SerializedName("previous_hcv")
    @Expose
    public String previous_hcv;

    @SerializedName("pcr_confirmation_hbv")
    @Expose
    public String pcr_confirmation_hbv;

    @SerializedName("pcr_confirmation_hcv")
    @Expose
    public String pcr_confirmation_hcv;

    @SerializedName("division")
    @Expose
    public Integer division;

    @SerializedName("district")
    @Expose
    public Integer district;

    @SerializedName("tehsil")
    @Expose
    public Integer tehsil;

    @SerializedName("hospital")
    @Expose
    public Integer hospital;

    @SerializedName("identifier")
    @Expose
    public String identifier;

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("hospital_id")
    @Expose
    public String hospital_id;


    @SerializedName("patient_type")
    @Expose
    public String patient_type;


    @SerializedName("mobile_id")
    @Expose
    public Long mobile_id;



    @SerializedName("finger_print1")
    @Expose
    public String finger_print1;


    @SerializedName("finger_print2")
    @Expose
    public String finger_print2;

    @SerializedName("prison_type")
    @Expose
    public Integer prison_type;

    @SerializedName("cnic_type")
    @Expose
    public String cnic_type;

    @SerializedName("is_cnic")
    @Expose
    public Integer is_cnic;

    public Integer getIs_cnic() {
        return is_cnic;
    }

    public void setIs_cnic(Integer is_cnic) {
        this.is_cnic = is_cnic;
    }

    public Integer getPrison_type() {
        return prison_type;
    }

    public void setPrison_type(Integer prison_type) {
        this.prison_type = prison_type;
    }

    public String getCnic_type() {
        return cnic_type;
    }

    public void setCnic_type(String cnic_type) {
        this.cnic_type = cnic_type;
    }

    public String getFinger_print1() {
        return finger_print1;
    }

    public void setFinger_print1(String finger_print1) {
        this.finger_print1 = finger_print1;
    }

    public String getFinger_print2() {
        return finger_print2;
    }

    public void setFinger_print2(String finger_print2) {
        this.finger_print2 = finger_print2;
    }

    public Long getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Long mobile_id) {
        this.mobile_id = mobile_id;
    }

    public String getPatient_type() {
        return patient_type;
    }

    public void setPatient_type(String patient_type) {
        this.patient_type = patient_type;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public Integer getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(Integer patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_dob() {
        return patient_dob;
    }

    public void setPatient_dob(String patient_dob) {
        this.patient_dob = patient_dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSelf_cnic() {
        return self_cnic;
    }

    public void setSelf_cnic(String self_cnic) {
        this.self_cnic = self_cnic;
    }

    public String getContact_no_self() {
        return contact_no_self;
    }

    public void setContact_no_self(String contact_no_self) {
        this.contact_no_self = contact_no_self;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPatient_age_80() {
        return patient_age_80;
    }

    public void setPatient_age_80(String patient_age_80) {
        this.patient_age_80 = patient_age_80;
    }

    public String getPrevious_hbv() {
        return previous_hbv;
    }

    public void setPrevious_hbv(String previous_hbv) {
        this.previous_hbv = previous_hbv;
    }

    public String getPrevious_hcv() {
        return previous_hcv;
    }

    public void setPrevious_hcv(String previous_hcv) {
        this.previous_hcv = previous_hcv;
    }

    public String getPcr_confirmation_hbv() {
        return pcr_confirmation_hbv;
    }

    public void setPcr_confirmation_hbv(String pcr_confirmation_hbv) {
        this.pcr_confirmation_hbv = pcr_confirmation_hbv;
    }

    public String getPcr_confirmation_hcv() {
        return pcr_confirmation_hcv;
    }

    public void setPcr_confirmation_hcv(String pcr_confirmation_hcv) {
        this.pcr_confirmation_hcv = pcr_confirmation_hcv;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTehsil() {
        return tehsil;
    }

    public void setTehsil(Integer tehsil) {
        this.tehsil = tehsil;
    }

    public Integer getHospital() {
        return hospital;
    }

    public void setHospital(Integer hospital) {
        this.hospital = hospital;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }



}
