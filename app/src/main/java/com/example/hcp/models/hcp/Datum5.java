package com.example.hcp.models.hcp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum5 {



        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mrn_no")
        @Expose
        private String mrn_no;
        @SerializedName("reg_date")
        @Expose
        private String reg_date;
        @SerializedName("patient_name")
        @Expose
        private String patient_name;
        @SerializedName("Lname")
        @Expose
        private String lname;
        @SerializedName("father_name")
        @Expose
        private String father_name;
        @SerializedName("self_cnic")
        @Expose
        private String self_cnic;
        @SerializedName("patient_from")
        @Expose
        private String patient_from;
        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("user_hospital")
        @Expose
        private String user_hospital;
        @SerializedName("hcv_screening_result")
        @Expose
        private String hcv_screening_result;
        @SerializedName("hbv_screening_result")
        @Expose
        private String hbv_screening_result;
        @SerializedName("pcr_confirmation_hcv")
        @Expose
        private String pcr_confirmation_hcv;
        @SerializedName("pcr_confirmation_hbv")
        @Expose
        private String pcr_confirmation_hbv;
        @SerializedName("is_refered")
        @Expose
        private String is_refered;
        @SerializedName("patient_type")
        @Expose
        private String patient_type;
        @SerializedName("cbl")
        @Expose
        private String cbl;
        @SerializedName("bbl")
        @Expose
        private String bbl;
        @SerializedName("hcv_medicine_name")
        @Expose
        private String  hcv_medicine_name;
        @SerializedName("no_of_hcv_medicine_delivered")
        @Expose
        private String no_of_hcv_medicine_delivered;
        @SerializedName("medicine_disbursment_date")
        @Expose
        private String medicine_disbursment_date;
        @SerializedName("no_of_hcv_followups")
        @Expose
        private String no_of_hcv_followups;
        @SerializedName("hcv_medicine_duration")
        @Expose
        private Integer hcv_medicine_duration;
        @SerializedName("next_status")
        @Expose
        private String next_status;
        @SerializedName("is_ignore")
        @Expose
        private String is_ignore;
        @SerializedName("is_assesment")
        @Expose
        private String is_assesment;
        @SerializedName("is_sample")
        @Expose
        private String is_sample;
        @SerializedName("collect_sample")
        @Expose
        private String collect_sample;
        @SerializedName("is_svr_form_submitted")
        @Expose
        private String is_svr_form_submitted;
        @SerializedName("is_svr_sample")
        @Expose
        private String is_svr_sample;
        @SerializedName("previous_hbv")
        @Expose
        private String previous_hbv;
        @SerializedName("previous_hcv")
        @Expose
        private String previous_hcv;
        @SerializedName("rapid_testing")
        @Expose
        private String rapid_testing;
        @SerializedName("contact_no_self")
        @Expose
        private String contact_no_self;
        @SerializedName("postal_address")
        @Expose
        private String postal_address;
        @SerializedName("is_re_register")
        @Expose
        private String is_re_register;
        @SerializedName("is_vital")
        @Expose
        private String is_vital;
        @SerializedName("next_of_kin_cnic")
        @Expose
        private String next_of_kin_cnic;
        @SerializedName("is_referal")
        @Expose
        private String is_referal;
        @SerializedName("no_of_medicine_delivered")
        @Expose
        private String no_of_medicine_delivered;
        @SerializedName("is_treatment")
        @Expose
        private String is_treatment;
        @SerializedName("is_closed")
        @Expose
        private String is_closed;
        @SerializedName("is_terminate")
        @Expose
        private String is_terminate;
        @SerializedName("baseline_result_type")
        @Expose
        private String baseline_result_type;
        @SerializedName("vaccinate")
        @Expose
        private String vaccinate;
        @SerializedName("cnic_status")
        @Expose
        private String cnic_status;
        @SerializedName("division")
        @Expose
        private String division;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("tehsil")
        @Expose
        private String tehsil;
        @SerializedName("hospital")
        @Expose
        private String hospital;
        @SerializedName("patient_dob")
        @Expose
        private String patient_dob;
        @SerializedName("is_type_change")
        @Expose
        private String is_type_change;
        @SerializedName("lost_followup_id")
        @Expose
        private String lost_followup_id;

    @SerializedName("hcv_viral_count")
    @Expose
    private Integer hcv_viral_count;

    @SerializedName("hbv_viral_count")
    @Expose
    private Integer hbv_viral_count;

    @SerializedName("sample_id")
    @Expose
    private Integer sample_id;

    @SerializedName("is_cirrhotic_patient")
    @Expose
    private String is_cirrhotic_patient;

    public Integer getSample_id() {
        return sample_id;
    }

    public void setSample_id(Integer sample_id) {
        this.sample_id = sample_id;
    }

    public String getIs_cirrhotic_patient() {
        return is_cirrhotic_patient;
    }

    public void setIs_cirrhotic_patient(String is_cirrhotic_patient) {
        this.is_cirrhotic_patient = is_cirrhotic_patient;
    }

    public Integer getHcv_viral_count() {
        return hcv_viral_count;
    }

    public void setHcv_viral_count(Integer hcv_viral_count) {
        this.hcv_viral_count = hcv_viral_count;
    }

    public Integer getHbv_viral_count() {
        return hbv_viral_count;
    }

    public void setHbv_viral_count(Integer hbv_viral_count) {
        this.hbv_viral_count = hbv_viral_count;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMrn_no() {
            return mrn_no;
        }

        public void setMrn_no(String mrn_no) {
            this.mrn_no = mrn_no;
        }

        public String getReg_date() {
            return reg_date;
        }

        public void setReg_date(String reg_date) {
            this.reg_date = reg_date;
        }

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getFather_name() {
            return father_name;
        }

        public void setFather_name(String father_name) {
            this.father_name = father_name;
        }

        public String getSelf_cnic() {
            return self_cnic;
        }

        public void setSelf_cnic(String self_cnic) {
            this.self_cnic = self_cnic;
        }

        public String getPatient_from() {
            return patient_from;
        }

        public void setPatient_from(String patient_from) {
            this.patient_from = patient_from;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_hospital() {
            return user_hospital;
        }

        public void setUser_hospital(String user_hospital) {
            this.user_hospital = user_hospital;
        }

        public String getHcv_screening_result() {
            return hcv_screening_result;
        }

        public void setHcv_screening_result(String hcv_screening_result) {
            this.hcv_screening_result = hcv_screening_result;
        }

        public String getHbv_screening_result() {
            return hbv_screening_result;
        }

        public void setHbv_screening_result(String hbv_screening_result) {
            this.hbv_screening_result = hbv_screening_result;
        }

        public String getPcr_confirmation_hcv() {
            return pcr_confirmation_hcv;
        }

        public void setPcr_confirmation_hcv(String pcr_confirmation_hcv) {
            this.pcr_confirmation_hcv = pcr_confirmation_hcv;
        }

        public String getPcr_confirmation_hbv() {
            return pcr_confirmation_hbv;
        }

        public void setPcr_confirmation_hbv(String pcr_confirmation_hbv) {
            this.pcr_confirmation_hbv = pcr_confirmation_hbv;
        }

        public String getIs_refered() {
            return is_refered;
        }

        public void setIs_refered(String is_refered) {
            this.is_refered = is_refered;
        }

        public String getPatient_type() {
            return patient_type;
        }

        public void setPatient_type(String patient_type) {
            this.patient_type = patient_type;
        }

        public String getCbl() {
            return cbl;
        }

        public void setCbl(String cbl) {
            this.cbl = cbl;
        }

        public String getBbl() {
            return bbl;
        }

        public void setBbl(String bbl) {
            this.bbl = bbl;
        }

        public String getHcv_medicine_name() {
            return hcv_medicine_name;
        }

        public void setHcv_medicine_name(String hcv_medicine_name) {
            this.hcv_medicine_name = hcv_medicine_name;
        }

        public String getNo_of_hcv_medicine_delivered() {
            return no_of_hcv_medicine_delivered;
        }

        public void setNo_of_hcv_medicine_delivered(String no_of_hcv_medicine_delivered) {
            this.no_of_hcv_medicine_delivered = no_of_hcv_medicine_delivered;
        }

        public String getMedicine_disbursment_date() {
            return medicine_disbursment_date;
        }

        public void setMedicine_disbursment_date(String medicine_disbursment_date) {
            this.medicine_disbursment_date = medicine_disbursment_date;
        }

        public String getNo_of_hcv_followups() {
            return no_of_hcv_followups;
        }

        public void setNo_of_hcv_followups(String no_of_hcv_followups) {
            this.no_of_hcv_followups = no_of_hcv_followups;
        }

        public Integer getHcv_medicine_duration() {
            return hcv_medicine_duration;
        }

        public void setHcv_medicine_duration(Integer hcv_medicine_duration) {
            this.hcv_medicine_duration = hcv_medicine_duration;
        }

        public String getNext_status() {
            return next_status;
        }

        public void setNext_status(String next_status) {
            this.next_status = next_status;
        }

        public String getIs_ignore() {
            return is_ignore;
        }

        public void setIs_ignore(String is_ignore) {
            this.is_ignore = is_ignore;
        }

        public String getIs_assesment() {
            return is_assesment;
        }

        public void setIs_assesment(String is_assesment) {
            this.is_assesment = is_assesment;
        }

        public String getIs_sample() {
            return is_sample;
        }

        public void setIs_sample(String is_sample) {
            this.is_sample = is_sample;
        }

        public String getCollect_sample() {
            return collect_sample;
        }

        public void setCollect_sample(String collect_sample) {
            this.collect_sample = collect_sample;
        }

        public String getIs_svr_form_submitted() {
            return is_svr_form_submitted;
        }

        public void setIs_svr_form_submitted(String is_svr_form_submitted) {
            this.is_svr_form_submitted = is_svr_form_submitted;
        }

        public String getIs_svr_sample() {
            return is_svr_sample;
        }

        public void setIs_svr_sample(String is_svr_sample) {
            this.is_svr_sample = is_svr_sample;
        }

        public String getPrevious_hbv() {
            return previous_hbv;
        }

        public void setPrevious_hbv(String previous_hbv) {
            this.previous_hbv = previous_hbv;
        }

        public String getPrevious_hcv() {
            return previous_hcv;
        }

        public void setPrevious_hcv(String previous_hcv) {
            this.previous_hcv = previous_hcv;
        }

        public String getRapid_testing() {
            return rapid_testing;
        }

        public void setRapid_testing(String rapid_testing) {
            this.rapid_testing = rapid_testing;
        }

        public String getContact_no_self() {
            return contact_no_self;
        }

        public void setContact_no_self(String contact_no_self) {
            this.contact_no_self = contact_no_self;
        }

        public String getPostal_address() {
            return postal_address;
        }

        public void setPostal_address(String postal_address) {
            this.postal_address = postal_address;
        }

        public String getIs_re_register() {
            return is_re_register;
        }

        public void setIs_re_register(String is_re_register) {
            this.is_re_register = is_re_register;
        }

        public String getIs_vital() {
            return is_vital;
        }

        public void setIs_vital(String is_vital) {
            this.is_vital = is_vital;
        }

        public String getNext_of_kin_cnic() {
            return next_of_kin_cnic;
        }

        public void setNext_of_kin_cnic(String next_of_kin_cnic) {
            this.next_of_kin_cnic = next_of_kin_cnic;
        }

        public String getIs_referal() {
            return is_referal;
        }

        public void setIs_referal(String is_referal) {
            this.is_referal = is_referal;
        }

        public String getNo_of_medicine_delivered() {
            return no_of_medicine_delivered;
        }

        public void setNo_of_medicine_delivered(String no_of_medicine_delivered) {
            this.no_of_medicine_delivered = no_of_medicine_delivered;
        }

        public String getIs_treatment() {
            return is_treatment;
        }

        public void setIs_treatment(String is_treatment) {
            this.is_treatment = is_treatment;
        }

        public String getIs_closed() {
            return is_closed;
        }

        public void setIs_closed(String is_closed) {
            this.is_closed = is_closed;
        }

        public String getIs_terminate() {
            return is_terminate;
        }

        public void setIs_terminate(String is_terminate) {
            this.is_terminate = is_terminate;
        }

        public String getBaseline_result_type() {
            return baseline_result_type;
        }

        public void setBaseline_result_type(String baseline_result_type) {
            this.baseline_result_type = baseline_result_type;
        }

        public String getVaccinate() {
            return vaccinate;
        }

        public void setVaccinate(String vaccinate) {
            this.vaccinate = vaccinate;
        }

        public String getCnic_status() {
            return cnic_status;
        }

        public void setCnic_status(String cnic_status) {
            this.cnic_status = cnic_status;
        }

        public String getDivision() {
            return division;
        }

        public void setDivision(String division) {
            this.division = division;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getTehsil() {
            return tehsil;
        }

        public void setTehsil(String tehsil) {
            this.tehsil = tehsil;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getPatient_dob() {
            return patient_dob;
        }

        public void setPatient_dob(String patient_dob) {
            this.patient_dob = patient_dob;
        }

        public String getIs_type_change() {
            return is_type_change;
        }

        public void setIs_type_change(String is_type_change) {
            this.is_type_change = is_type_change;
        }

        public String getLost_followup_id() {
            return lost_followup_id;
        }

        public void setLost_followup_id(String lost_followup_id) {
            this.lost_followup_id = lost_followup_id;
        }


    }
