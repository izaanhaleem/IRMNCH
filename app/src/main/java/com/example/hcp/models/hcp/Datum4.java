package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum4 {

    @SerializedName("hf_code")
    @Expose
    private String hf_code;
    @SerializedName("hf_name")
    @Expose
    private String hf_name;
    @SerializedName("tehsil_code")
    @Expose
    private String tehsil_code;

    public String getHf_code() {
        return hf_code;
    }

    public void setHf_code(String hf_code) {
        this.hf_code = hf_code;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }


}
