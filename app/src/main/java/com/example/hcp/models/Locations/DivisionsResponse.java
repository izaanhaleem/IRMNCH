package com.example.hcp.models.Locations;

import com.example.hcp.models.hcp.DivisionsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class DivisionsResponse {

    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Data")
    @Expose
    private List<DivisionsModel> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<DivisionsModel> getData() {
        return data;
    }

    public void setData(List<DivisionsModel> data) {
        this.data = data;
    }
}
