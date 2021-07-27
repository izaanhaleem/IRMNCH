package com.example.hcp.models.hcp;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class medicineRequest {
    @SerializedName("invs_status")
    @Expose
    private String invs_status;
    @SerializedName("result_type")
    @Expose
    private String result_type;
    @SerializedName("hemoglobin")
    @Expose
    private String hemoglobin;
    @SerializedName("ast")
    @Expose
    private String ast;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("platelet")
    @Expose
    private String platelet;
    @SerializedName("tlc")
    @Expose
    private String tlc;
    @SerializedName("apri")
    @Expose
    private String apri;
    @SerializedName("viral_count")
    @Expose
    private String viral_count;
    @SerializedName("pcr_result")
    @Expose
    private String pcr_result;
    @SerializedName("sample_id")
    @Expose
    private String sample_id;
    @SerializedName("lab_name")
    @Expose
    private String lab_name;
    @SerializedName("other_lab_name")
    @Expose
    private Object other_lab_name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("baseline_type")
    @Expose
    private String baseline_type;
    @SerializedName("urea")
    @Expose
    private String urea;
    @SerializedName("creatinie")
    @Expose
    private String creatinie;
    @SerializedName("blood_sugar_random")
    @Expose
    private String blood_sugar_random;
    @SerializedName("drug_interaction")
    @Expose
    private String drug_interaction;
    @SerializedName("hcv_medicine_recommended")
    @Expose
    private String hcv_medicine_recommended;
    @SerializedName("disburse_6_mnth_dose")
    @Expose
    private String disburse_6_mnth_dose;
    @SerializedName("cirrhotic_medicine_flow")
    @Expose
    private String cirrhotic_medicine_flow;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("hospital_id")
    @Expose
    private Integer hospital_id;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("mobile_id")
    @Expose
    private Integer mobile_id;

    @SerializedName("hcv_medicine_duration")
    @Expose
    private Integer hcv_medicine_duration;




    @SerializedName("treatment_history")
    @Expose
    public String treatment_history;



    @SerializedName("disburse_3_mnth_dose")
    @Expose
    public String disburse_3_mnth_dose;



    @SerializedName("treatment_options")
    @Expose
    public String treatment_options;

    public String getTreatment_history() {
        return treatment_history;
    }

    public void setTreatment_history(String treatment_history) {
        this.treatment_history = treatment_history;
    }

    public String getDisburse_3_mnth_dose() {
        return disburse_3_mnth_dose;
    }

    public void setDisburse_3_mnth_dose(String disburse_3_mnth_dose) {
        this.disburse_3_mnth_dose = disburse_3_mnth_dose;
    }

    public String getTreatment_options() {
        return treatment_options;
    }

    public void setTreatment_options(String treatment_options) {
        this.treatment_options = treatment_options;
    }

    public Integer getHcv_medicine_duration() {
        return hcv_medicine_duration;
    }

    public void setHcv_medicine_duration(Integer hcv_medicine_duration) {
        this.hcv_medicine_duration = hcv_medicine_duration;
    }

    public String getInvs_status() {
        return invs_status;
    }

    public void setInvs_status(String invs_status) {
        this.invs_status = invs_status;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPlatelet() {
        return platelet;
    }

    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }

    public String getTlc() {
        return tlc;
    }

    public void setTlc(String tlc) {
        this.tlc = tlc;
    }

    public String getApri() {
        return apri;
    }

    public void setApri(String apri) {
        this.apri = apri;
    }

    public String getViral_count() {
        return viral_count;
    }

    public void setViral_count(String viral_count) {
        this.viral_count = viral_count;
    }

    public String getPcr_result() {
        return pcr_result;
    }

    public void setPcr_result(String pcr_result) {
        this.pcr_result = pcr_result;
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public Object getOther_lab_name() {
        return other_lab_name;
    }

    public void setOther_lab_name(Object other_lab_name) {
        this.other_lab_name = other_lab_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseline_type() {
        return baseline_type;
    }

    public void setBaseline_type(String baseline_type) {
        this.baseline_type = baseline_type;
    }

    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getCreatinie() {
        return creatinie;
    }

    public void setCreatinie(String creatinie) {
        this.creatinie = creatinie;
    }

    public String getBlood_sugar_random() {
        return blood_sugar_random;
    }

    public void setBlood_sugar_random(String blood_sugar_random) {
        this.blood_sugar_random = blood_sugar_random;
    }

    public String getDrug_interaction() {
        return drug_interaction;
    }

    public void setDrug_interaction(String drug_interaction) {
        this.drug_interaction = drug_interaction;
    }

    public String getHcv_medicine_recommended() {
        return hcv_medicine_recommended;
    }

    public void setHcv_medicine_recommended(String hcv_medicine_recommended) {
        this.hcv_medicine_recommended = hcv_medicine_recommended;
    }

    public String getDisburse_6_mnth_dose() {
        return disburse_6_mnth_dose;
    }

    public void setDisburse_6_mnth_dose(String disburse_6_mnth_dose) {
        this.disburse_6_mnth_dose = disburse_6_mnth_dose;
    }

    public String getCirrhotic_medicine_flow() {
        return cirrhotic_medicine_flow;
    }

    public void setCirrhotic_medicine_flow(String cirrhotic_medicine_flow) {
        this.cirrhotic_medicine_flow = cirrhotic_medicine_flow;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Integer hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Integer mobile_id) {
        this.mobile_id = mobile_id;
    }
}
