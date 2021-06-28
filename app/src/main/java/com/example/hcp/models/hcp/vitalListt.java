package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "vitalPatientTable",id = "ide")
public class vitalListt extends Model {

    @Column(name = "id",unique = true)
    @SerializedName("id")
    @Expose
    public Integer id;

    @Column(name = "mrn_no")
    @SerializedName("mrn_no")
    @Expose
    public String mrn_no;

    @Column(name = "reg_no")
    @SerializedName("reg_no")
    @Expose
    public String reg_no;

    @Column(name = "patient_type")
    @SerializedName("patient_type")
    @Expose
    public String patient_type;

    @Column(name = "patient_name")
    @SerializedName("patient_name")
    @Expose
    public String patient_name;
    @Column(name = "self_cnic")
    @SerializedName("self_cnic")
    @Expose
    public String self_cnic;

    @Column(name = "patient_age")
    @SerializedName("patient_age")
    @Expose
    public String patient_age;

    @Column(name = "gender")
    @SerializedName("gender")
    @Expose
    public String gender;

    @Column(name = "created")
    @SerializedName("created")
    @Expose
    public String created;

    @Column(name = "next_status")
    @SerializedName("next_status")
    @Expose
    public String next_status;

    @Column(name = "stage")
    @SerializedName("stage")
    @Expose
    public String stage;

    @Column(name = "is_reg_completed")
    @SerializedName("is_reg_completed")
    @Expose
    public String is_reg_completed;

    @Column(name = "is_blood_bank_patient")
    @SerializedName("is_blood_bank_patient")
    @Expose
    public String is_blood_bank_patient;



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




    public static List<vitalListt> searchBymrno(String mrno) {
        return new Select()
                .from(vitalListt.class)
                .where("mrn_no = ?",mrno)
                .execute();
    }
    public static List<vitalListt> searchBycnic(String cnic) {
        return new Select()
                .from(vitalListt.class)
                .where("self_cnic = ?",cnic)
                .execute();
    }
    public static List<vitalListt> searchByname(String name) {
        return new Select()
                .from(vitalListt.class)
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<vitalListt> getall() {
        return new Select()
                .from(vitalListt.class)
                .execute();
    }
    public static vitalListt searchByid(Integer id) {
        return new Select()
                .from(vitalListt.class)
                .where("id = ?",id)
                .executeSingle();
    }
    public static void deleteAll() {
        new Delete().from(vitalListt.class)
                .execute();
    }

}
