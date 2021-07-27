package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class medicineResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("datum8")
    @Expose
    private Datum8 datum8;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Datum8 getDatum8() {
        return datum8;
    }

    public void setDatum8(Datum8 datum8) {
        this.datum8 = datum8;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
