package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccinationRequest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("stage")
    @Expose
    private Integer stage;
    @SerializedName("dose_date")
    @Expose
    private String dose_date;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getDose_date() {
        return dose_date;
    }

    public void setDose_date(String dose_date) {
        this.dose_date = dose_date;
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
