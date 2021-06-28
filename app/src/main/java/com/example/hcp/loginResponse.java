package com.example.hcp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Data data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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


    public class Data {

        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("hospital_id")
        @Expose
        private String hospital_id;
        @SerializedName("identifier")
        @Expose
        private String identifier;
        @SerializedName("hospital_name")
        @Expose
        private String hospital_name;

        @SerializedName("start_range")
        @Expose
        private String start_range;

        @SerializedName("end_range")
        @Expose
        private String end_range;

        public String getStart_range() {
            return start_range;
        }

        public void setStart_range(String start_range) {
            this.start_range = start_range;
        }

        public String getEnd_range() {
            return end_range;
        }

        public void setEnd_range(String end_range) {
            this.end_range = end_range;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getHospital_id() {
            return hospital_id;
        }

        public void setHospital_id(String hospital_id) {
            this.hospital_id = hospital_id;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }

    }
}