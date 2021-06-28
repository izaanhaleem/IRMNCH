package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleRequest {

    @SerializedName("hospital_id")
    @Expose
    private Integer hospital_id;
    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("sample_no")
    @Expose
    private String sample_no;
    @SerializedName("mobile_id")
    @Expose
    private Long mobile_id;

    public Integer getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Integer hospital_id) {
        this.hospital_id = hospital_id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getSample_no() {
        return sample_no;
    }

    public void setSample_no(String sample_no) {
        this.sample_no = sample_no;
    }

    public Long getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Long mobile_id) {
        this.mobile_id = mobile_id;
    }

}
