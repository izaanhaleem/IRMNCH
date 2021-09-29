package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedDisbursmentResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumM> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer status_code;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumM> getData() {
        return data;
    }

    public void setData(List<DatumM> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public class DatumM {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("mrn_no")
        @Expose
        private String mrn_no;
        @SerializedName("patient_name")
        @Expose
        private String patient_name;
        @SerializedName("self_cnic")
        @Expose
        private String self_cnic;
        @SerializedName("patient_type")
        @Expose
        private String patient_type;
        @SerializedName("patient_stage")
        @Expose
        private String patient_stage;
        @SerializedName("sample_number")
        @Expose
        private String sample_number;
        @SerializedName("test_type")
        @Expose
        private String test_type;
        @SerializedName("is_hcv_detected")
        @Expose
        private String is_hcv_detected;
        @SerializedName("is_hbv_detected")
        @Expose
        private String is_hbv_detected;
        @SerializedName("hcv_viral_load")
        @Expose
        private String hcv_viral_load;
        @SerializedName("hbv_viral_load")
        @Expose
        private String hbv_viral_load;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPatient_type() {
            return patient_type;
        }

        public void setPatient_type(String patient_type) {
            this.patient_type = patient_type;
        }

        public String getPatient_stage() {
            return patient_stage;
        }

        public void setPatient_stage(String patient_stage) {
            this.patient_stage = patient_stage;
        }

        public String getSample_number() {
            return sample_number;
        }

        public void setSample_number(String sample_number) {
            this.sample_number = sample_number;
        }

        public String getTest_type() {
            return test_type;
        }

        public void setTest_type(String test_type) {
            this.test_type = test_type;
        }

        public String getIs_hcv_detected() {
            return is_hcv_detected;
        }

        public void setIs_hcv_detected(String is_hcv_detected) {
            this.is_hcv_detected = is_hcv_detected;
        }

        public String getIs_hbv_detected() {
            return is_hbv_detected;
        }

        public void setIs_hbv_detected(String is_hbv_detected) {
            this.is_hbv_detected = is_hbv_detected;
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

    }


}
