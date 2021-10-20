package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "medicinee",id = "ide")
public class medicinee extends Model {

    @Column(name = "invs_status")
    @SerializedName("invs_status")
    @Expose
    public String invs_status;

    @Column(name = "result_type")
    @SerializedName("result_type")
    @Expose
    public String result_type;

    @Column(name = "hemoglobin")
    @SerializedName("hemoglobin")
    @Expose
    public String hemoglobin;

    @Column(name = "ast")
    @SerializedName("ast")
    @Expose
    public String ast;

    @Column(name = "alt")
    @SerializedName("alt")
    @Expose
    public String alt;

    @Column(name = "platelet")
    @SerializedName("platelet")
    @Expose
    public String platelet;

    @Column(name = "tlc")
    @SerializedName("tlc")
    @Expose
    public String tlc;

    @Column(name = "apri")
    @SerializedName("apri")
    @Expose
    public String apri;

    @Column(name = "viral_count")
    @SerializedName("viral_count")
    @Expose
    public String viral_count;

    @Column(name = "pcr_result")
    @SerializedName("pcr_result")
    @Expose
    public String pcr_result;

    @Column(name = "sample_id")
    @SerializedName("sample_id")
    @Expose
    public String sample_id;

    @Column(name = "lab_name")
    @SerializedName("lab_name")
    @Expose
    public String lab_name;

    @Column(name = "other_lab_name")
    @SerializedName("other_lab_name")
    @Expose
    public String  other_lab_name;

    @Column(name = "id")
    @SerializedName("id")
    @Expose
    public String id;

    @Column(name = "baseline_type")
    @SerializedName("baseline_type")
    @Expose
    public String baseline_type;

    @Column(name = "urea")
    @SerializedName("urea")
    @Expose
    public String urea;

    @Column(name = "creatinie")
    @SerializedName("creatinie")
    @Expose
    public String creatinie;

    @Column(name = "blood_sugar_random")
    @SerializedName("blood_sugar_random")
    @Expose
    public String blood_sugar_random;

    @Column(name = "drug_interaction")
    @SerializedName("drug_interaction")
    @Expose
    public String drug_interaction;

    @Column(name = "hcv_medicine_recommended")
    @SerializedName("hcv_medicine_recommended")
    @Expose
    public String hcv_medicine_recommended;

    @Column(name = "hbv_medicine_recommended")
    @SerializedName("hbv_medicine_recommended")
    @Expose
    public String hbv_medicine_recommended;

    @Column(name = "disburse_6_mnth_dose")
    @SerializedName("disburse_6_mnth_dose")
    @Expose
    public String disburse_6_mnth_dose;

    @Column(name = "cirrhotic_medicine_flow")
    @SerializedName("cirrhotic_medicine_flow")
    @Expose
    public String cirrhotic_medicine_flow;

    @Column(name = "created")
    @SerializedName("created")
    @Expose
    public String created;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public Integer user_id;

    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    public Integer hospital_id;

    @Column(name = "updated")
    @SerializedName("updated")
    @Expose
    public String updated;

    @Column(name = "mobile_id")
    @SerializedName("mobile_id")
    @Expose
    public Integer mobile_id;

    @Column(name = "hcv_medicine_duration")
    @SerializedName("hcv_medicine_duration")
    @Expose
    public Integer hcv_medicine_duration;


    @Column(name = "treatment_history")
    @SerializedName("treatment_history")
    @Expose
    public String treatment_history;


    @Column(name = "disburse_3_mnth_dose")
    @SerializedName("disburse_3_mnth_dose")
    @Expose
    public String disburse_3_mnth_dose;


    @Column(name = "treatment_options")
    @SerializedName("treatment_options")
    @Expose
    public String treatment_options;

    @Column(name = "is_all_med_delivered_frm_baseline")
    @SerializedName("is_all_med_delivered_frm_baseline")
    @Expose
    public String is_all_med_delivered_frm_baseline;


    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;
    public static List<medicinee> searchBySync() {
        return new Select()
                .from(medicinee.class)
                .where("IsSync = ?",0)
                .execute();
    }

    public static List<medicinee> getall() {
        return new Select()
                .from(medicinee.class)
                .execute();
    }
    public static medicinee searchBypid(Integer id) {
        return new Select()
                .from(medicinee.class)
                .where("id = ?",id)
                .executeSingle();
    }
    public static void deleteAll() {
        new Delete().from(medicinee.class)
                .execute();
    }
}
