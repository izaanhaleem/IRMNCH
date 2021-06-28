package com.example.hcp.models.Locations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class TehsilResponse {

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
    private List<TehsilDatum> data = null;

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

    public List<TehsilDatum> getData() {
        return data;
    }

    public void setData(List<TehsilDatum> data) {
        this.data = data;
    }

    public class TehsilDatum {
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Detail")
        @Expose
        private String detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

}
