package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userdataRequest {
    @SerializedName("user_hospital")
    @Expose
    private Integer user_hospital;

    public Integer getUser_hospital() {
        return user_hospital;
    }

    public void setUser_hospital(Integer user_hospital) {
        this.user_hospital = user_hospital;
    }
}
