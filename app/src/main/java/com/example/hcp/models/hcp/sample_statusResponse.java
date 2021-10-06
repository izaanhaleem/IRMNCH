package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class sample_statusResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status_code")
    @Expose
    private Integer status_code;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }



    public class Datum {
        @SerializedName("patient_id")
        @Expose
        private String patient_id;
        @SerializedName("sample_id")
        @Expose
        private String sample_id;
        @SerializedName("lab_sample_id")
        @Expose
        private String lab_sample_id;
        @SerializedName("result_id")
        @Expose
        private String result_id;
        @SerializedName("action_id")
        @Expose
        private String action_id;
        @SerializedName("is_reception")
        @Expose
        private String is_reception;
        @SerializedName("hcv_viral_load")
        @Expose
        private String hcv_viral_load;
        @SerializedName("hbv_viral_load")
        @Expose
        private String hbv_viral_load;
        @SerializedName("mrn_no")
        @Expose
        private String mrn_no;
        @SerializedName("patient_name")
        @Expose
        private String patient_name;
        @SerializedName("self_cnic")
        @Expose
        private String self_cnic;
        @SerializedName("patient_age")
        @Expose
        private String patient_age;
        @SerializedName("sample_status")
        @Expose
        private String sample_status;

        @SerializedName("sample_number")
        @Expose
        private String sample_number;

        public String getSample_number() {
            return sample_number;
        }

        public void setSample_number(String sample_number) {
            this.sample_number = sample_number;
        }

        public String getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(String patient_id) {
            this.patient_id = patient_id;
        }

        public String getSample_id() {
            return sample_id;
        }

        public void setSample_id(String sample_id) {
            this.sample_id = sample_id;
        }

        public String getLab_sample_id() {
            return lab_sample_id;
        }

        public void setLab_sample_id(String lab_sample_id) {
            this.lab_sample_id = lab_sample_id;
        }

        public String getResult_id() {
            return result_id;
        }

        public void setResult_id(String result_id) {
            this.result_id = result_id;
        }

        public String getAction_id() {
            return action_id;
        }

        public void setAction_id(String action_id) {
            this.action_id = action_id;
        }

        public String getIs_reception() {
            return is_reception;
        }

        public void setIs_reception(String is_reception) {
            this.is_reception = is_reception;
        }

        public String getHcv_viral_load() {
            return hcv_viral_load;
        }

        public void setHcv_viral_load(String hcv_viral_load) {
            this.hcv_viral_load = hcv_viral_load;
        }

        public String getHbv_viral_load() {
            return hbv_viral_load;
        }

        public void setHbv_viral_load(String hbv_viral_load) {
            this.hbv_viral_load = hbv_viral_load;
        }

        public String getMrn_no() {
            return mrn_no;
        }

        public void setMrn_no(String mrn_no) {
            this.mrn_no = mrn_no;
        }

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getSelf_cnic() {
            return self_cnic;
        }

        public void setSelf_cnic(String self_cnic) {
            this.self_cnic = self_cnic;
        }

        public String getPatient_age() {
            return patient_age;
        }

        public void setPatient_age(String patient_age) {
            this.patient_age = patient_age;
        }

        public String getSample_status() {
            return sample_status;
        }

        public void setSample_status(String sample_status) {
            this.sample_status = sample_status;
        }

    }


}
