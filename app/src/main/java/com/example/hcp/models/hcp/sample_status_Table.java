package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "sample_status_Table")
public class sample_status_Table  extends Model {

    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    private String patient_id;

    @Column(name = "sample_id")
    @SerializedName("sample_id")
    @Expose
    private String sample_id;

    @Column(name = "lab_sample_id")
    @SerializedName("lab_sample_id")
    @Expose
    private String lab_sample_id;

    @Column(name = "result_id")
    @SerializedName("result_id")
    @Expose
    private String result_id;

    @Column(name = "action_id")
    @SerializedName("action_id")
    @Expose
    private String action_id;

    @Column(name = "is_reception")
    @SerializedName("is_reception")
    @Expose
    private String is_reception;

    @Column(name = "hcv_viral_load")
    @SerializedName("hcv_viral_load")
    @Expose
    private String hcv_viral_load;

    @Column(name = "hbv_viral_load")
    @SerializedName("hbv_viral_load")
    @Expose
    private String hbv_viral_load;

    @Column(name = "mrn_no")
    @SerializedName("mrn_no")
    @Expose
    private String mrn_no;

    @Column(name = "patient_name")
    @SerializedName("patient_name")
    @Expose
    private String patient_name;

    @Column(name = "self_cnic")
    @SerializedName("self_cnic")
    @Expose
    private String self_cnic;

    @Column(name = "patient_age")
    @SerializedName("patient_age")
    @Expose
    private String patient_age;

    @Column(name = "sample_status")
    @SerializedName("sample_status")
    @Expose
    private String sample_status;

    @Column(name = "sample_number")
    @SerializedName("sample_number")
    @Expose
    private String sample_number;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getLab_sample_id() {
        return lab_sample_id;
    }

    public void setLab_sample_id(String lab_sample_id) {
        this.lab_sample_id = lab_sample_id;
    }

    public String getResult_id() {
        return result_id;
    }

    public void setResult_id(String result_id) {
        this.result_id = result_id;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getIs_reception() {
        return is_reception;
    }

    public void setIs_reception(String is_reception) {
        this.is_reception = is_reception;
    }

    public String getHcv_viral_load() {
        return hcv_viral_load;
    }

    public void setHcv_viral_load(String hcv_viral_load) {
        this.hcv_viral_load = hcv_viral_load;
    }

    public String getHbv_viral_load() {
        return hbv_viral_load;
    }

    public void setHbv_viral_load(String hbv_viral_load) {
        this.hbv_viral_load = hbv_viral_load;
    }

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

    public String getSample_status() {
        return sample_status;
    }

    public void setSample_status(String sample_status) {
        this.sample_status = sample_status;
    }

    public String getSample_number() {
        return sample_number;
    }

    public void setSample_number(String sample_number) {
        this.sample_number = sample_number;
    }

    public static List<sample_status_Table> searchByMRNOLeader(String mrno) {
        return new Select()
                .from(sample_status_Table.class)
                .where("mrn_no = ?",mrno )
                .execute();
    }

    public static List<sample_status_Table> searchByCNICSample(String cnic) {
        return new Select()
                .from(sample_status_Table.class)
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<sample_status_Table> searchBysampleNumber(String samplenumber) {
        return new Select()
                .from(sample_status_Table.class)
                .where("sample_number = ?",samplenumber)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(sample_status_Table.class)
                .execute();
    }
}
