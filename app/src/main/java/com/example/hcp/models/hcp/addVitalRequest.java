package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class addVitalRequest {
    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("pulse")
    @Expose
    private Integer pulse;
    @SerializedName("bp_systolic")
    @Expose
    private Integer bp_systolic;
    @SerializedName("bp_diastolic")
    @Expose
    private Integer bp_diastolic;
    @SerializedName("height")
    @Expose
    private Double height;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("mobile_id")
    @Expose
    private Long mobile_id;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getBp_systolic() {
        return bp_systolic;
    }

    public void setBp_systolic(Integer bp_systolic) {
        this.bp_systolic = bp_systolic;
    }

    public Integer getBp_diastolic() {
        return bp_diastolic;
    }

    public void setBp_diastolic(Integer bp_diastolic) {
        this.bp_diastolic = bp_diastolic;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Long getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Long mobile_id) {
        this.mobile_id = mobile_id;
    }

}
