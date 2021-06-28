package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum2 {

    @SerializedName("tehsil_code")
    @Expose
    private String tehsil_code;
    @SerializedName("tehsil_name")
    @Expose
    private String tehsil_name;

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }

    public String getTehsil_name() {
        return tehsil_name;
    }

    public void setTehsil_name(String tehsil_name) {
        this.tehsil_name = tehsil_name;
    }

}
