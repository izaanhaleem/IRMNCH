package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "MedicineDisbursment_Table", id = "mobile_id")
public class MedicineDisbursment_Table extends Model {

    @Column(name = "patient_id")
    @SerializedName("id")
    @Expose
    public String patient_id;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

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

    @Column(name = "patient_type")
    @SerializedName("patient_type")
    @Expose
    private String patient_type;

    @Column(name = "patient_stage")
    @SerializedName("patient_stage")
    @Expose
    private String patient_stage;

    @Column(name = "sample_number")
    @SerializedName("sample_number")
    @Expose
    private String sample_number;

    @Column(name = "test_type")
    @SerializedName("test_type")
    @Expose
    private String test_type;

    @Column(name = "is_hcv_detected")
    @SerializedName("is_hcv_detected")
    @Expose
    private String is_hcv_detected;

    @Column(name = "is_hbv_detected")
    @SerializedName("is_hbv_detected")
    @Expose
    private String is_hbv_detected;

    @Column(name = "hcv_viral_load")
    @SerializedName("hcv_viral_load")
    @Expose
    private String hcv_viral_load;

    @Column(name = "hbv_viral_load")
    @SerializedName("hbv_viral_load")
    @Expose
    private String hbv_viral_load;


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

    public String getPatient_type() {
        return patient_type;
    }

    public void setPatient_type(String patient_type) {
        this.patient_type = patient_type;
    }

    public String getPatient_stage() {
        return patient_stage;
    }

    public void setPatient_stage(String patient_stage) {
        this.patient_stage = patient_stage;
    }

    public String getSample_number() {
        return sample_number;
    }

    public void setSample_number(String sample_number) {
        this.sample_number = sample_number;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public String getIs_hcv_detected() {
        return is_hcv_detected;
    }

    public void setIs_hcv_detected(String is_hcv_detected) {
        this.is_hcv_detected = is_hcv_detected;
    }

    public String getIs_hbv_detected() {
        return is_hbv_detected;
    }

    public void setIs_hbv_detected(String is_hbv_detected) {
        this.is_hbv_detected = is_hbv_detected;
    }

    public static List<MedicineDisbursment_Table> searchByCNICLeader(String cnic) {
        return new Select()
                .from(MedicineDisbursment_Table.class)
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<MedicineDisbursment_Table> searchByMRNOLeader(String mrno) {
        return new Select()
                .from(MedicineDisbursment_Table.class)
                .where("mrn_no = ?",mrno )
                .execute();
    }


    public static List<MedicineDisbursment_Table> getall() {
        return new Select()
                .from(MedicineDisbursment_Table.class)
                .execute();
    }

    public static List<MedicineDisbursment_Table> getallISMedicine() {
        return new Select()
                .from(MedicineDisbursment_Table.class)
                .where("is_hbv_detected = ?","Y")
                .or("is_hcv_detected = ?","Y")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(MedicineDisbursment_Table.class)
                .execute();
    }
}




