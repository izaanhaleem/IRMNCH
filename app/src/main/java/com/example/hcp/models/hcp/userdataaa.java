package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.jetbrains.annotations.NotNull;

import java.util.List;

@Table(name = "UserData_Tableee",id = "mobile_id")

public class userdataaa extends Model {

    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;

    @Column(name = "patient_age")
    @SerializedName("patient_age")
    @Expose
    public String patient_age;

    @Column(name = "gender")
    @SerializedName("gender")
    @Expose
    public Integer gender;

    @Column(name = "address")
    @SerializedName("address")
    @Expose
    public String address;

    @Column(name = "marital_status")
    @SerializedName("marital_status")
    @Expose
    public String marital_status;

    @Column(name = "occupation")
    @SerializedName("occupation")
    @Expose
    public String occupation;

    @Column(name = "qualification")
    @SerializedName("qualification")
    @Expose
    public String qualification;

    @Column(name = "patient_age_80")
    @SerializedName("patient_age_80")
    @Expose
    public String patient_age_80;

    @Column(name = "ISVital")
    @SerializedName("ISVital")
    @Expose
    public Integer ISVital;

    @Column(name = "IS_assessment")
    @SerializedName("IS_assessment")
    @Expose
    public Integer IS_assessment;

    @Column(name =  "IS_Vaccination")
    @SerializedName("IS_Vaccination")
    @Expose
    public Integer IS_Vaccination;

    @Column(name = "ISSample")
    @SerializedName("ISSample")
    @Expose
    public Integer ISSample;

    @Column(name = "identifier")
    @SerializedName("identifier")
    @Expose
    public String identifier;

    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    public String hospital_id;

    @Column(name = "finger_base64")
    @SerializedName("finger_base64")
    @Expose
    public String finger_base64;


    @Column(name = "finger_fmd")
    @SerializedName("finger_fmd")
    @Expose
    public String finger_fmd;

    @Column(name = "new_patient")
    @SerializedName("new_patient")
    @Expose
    public Integer new_patient;

    @Column(name = "cnic_type")
    @SerializedName("cnic_type")
    @Expose
    public String cnic_type;

    @Column(name = "prison_type")
    @SerializedName("prison_type")
    @Expose
    public Integer prison_type;


    @Column(name = "is_prison_release")
    @SerializedName("is_prison_release")
    @Expose
    public String is_prison_release;

    @Column(name = "IS_delete")
    @SerializedName("IS_delete")
    @Expose
    public Integer IS_delete;


    @Column(name = "deleted_mrn_no")
    @SerializedName("deleted_mrn_no")
    @Expose
    public String deleted_mrn_no;

    @Column(name = "deleted_cnic")
    @SerializedName("deleted_cnic")
    @Expose
    public String deleted_cnic;

    @Column(name = "deleted_name")
    @SerializedName("deleted_name")
    @Expose
    public String deleted_name;



    @Column(name = "deleted_hf_name")
    @SerializedName("deleted_hf_name")
    @Expose
    public String deleted_hf_name;

    @Column(name = "deleted_stage")
    @SerializedName("deleted_stage")
    @Expose
    public String deleted_stage;


    @Column(name = "local_mobileid")
    @SerializedName("local_mobileid")
    @Expose
    public Integer local_mobileid;

    public Integer getLocal_mobileid() {
        return local_mobileid;
    }

    public void setLocal_mobileid(Integer local_mobileid) {
        this.local_mobileid = local_mobileid;
    }

    public String getDeleted_mrn_no() {
        return deleted_mrn_no;
    }

    public void setDeleted_mrn_no(String deleted_mrn_no) {
        this.deleted_mrn_no = deleted_mrn_no;
    }

    public String getDeleted_cnic() {
        return deleted_cnic;
    }

    public void setDeleted_cnic(String deleted_cnic) {
        this.deleted_cnic = deleted_cnic;
    }

    public String getDeleted_name() {
        return deleted_name;
    }

    public void setDeleted_name(String deleted_name) {
        this.deleted_name = deleted_name;
    }

    public String getDeleted_hf_name() {
        return deleted_hf_name;
    }

    public void setDeleted_hf_name(String deleted_hf_name) {
        this.deleted_hf_name = deleted_hf_name;
    }

    public String getDeleted_stage() {
        return deleted_stage;
    }

    public void setDeleted_stage(String deleted_stage) {
        this.deleted_stage = deleted_stage;
    }

    public Integer getIS_delete() {
        return IS_delete;
    }

    public void setIS_delete(Integer IS_delete) {
        this.IS_delete = IS_delete;
    }

    public String getIs_prison_release() {
        return is_prison_release;
    }

    public void setIs_prison_release(String is_prison_release) {
        this.is_prison_release = is_prison_release;
    }

    public Integer getPrison_type() {
        return prison_type;
    }

    public void setPrison_type(Integer prison_type) {
        this.prison_type = prison_type;
    }

    public Integer getNew_patient() {
        return new_patient;
    }

    public void setNew_patient(Integer new_patient) {
        this.new_patient = new_patient;
    }

    public Integer getIsSync() {
        return IsSync;
    }

    public void setIsSync(Integer isSync) {
        IsSync = isSync;
    }

    public String  getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPatient_age_80() {
        return patient_age_80;
    }

    public void setPatient_age_80(String patient_age_80) {
        this.patient_age_80 = patient_age_80;
    }

    public Integer getISVital() {
        return ISVital;
    }

    public void setISVital(Integer ISVital) {
        this.ISVital = ISVital;
    }

    public Integer getIS_assessment() {
        return IS_assessment;
    }

    public void setIS_assessment(Integer IS_assessment) {
        this.IS_assessment = IS_assessment;
    }

    public Integer getIS_Vaccination() {
        return IS_Vaccination;
    }

    public void setIS_Vaccination(Integer IS_Vaccination) {
        this.IS_Vaccination = IS_Vaccination;
    }

    public Integer getISSample() {
        return ISSample;
    }

    public void setISSample(Integer ISSample) {
        this.ISSample = ISSample;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getFinger_base64() {
        return finger_base64;
    }

    public void setFinger_base64(String finger_base64) {
        this.finger_base64 = finger_base64;
    }

    public String getFinger_fmd() {
        return finger_fmd;
    }

    public void setFinger_fmd(String finger_fmd) {
        this.finger_fmd = finger_fmd;
    }

    public String getCnic_type() {
        return cnic_type;
    }

    public void setCnic_type(String cnic_type) {
        this.cnic_type = cnic_type;
    }

    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    public Integer patient_id;

    @Column(name = "mrn_no")
    @SerializedName("mrn_no")
    @Expose
    public String mrn_no;

    @Column(name = "reg_date")
    @SerializedName("reg_date")
    @Expose
    public String reg_date;

    @Column(name = "patient_name")
    @SerializedName("patient_name")
    @Expose
    public String patient_name;

    @Column(name = "Lname")
    @SerializedName("Lname")
    @Expose
    public String lname;

    @Column(name = "father_name")
    @SerializedName("father_name")
    @Expose
    public String father_name;

    @Column(name = "self_cnic")
    @SerializedName("self_cnic")
    @Expose
    public String self_cnic;

    @Column(name = "patient_from")
    @SerializedName("patient_from")
    @Expose
    public String patient_from;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public String user_id;
//
    @Column(name = "user_hospital")
    @SerializedName("user_hospital")
    @Expose
    public String user_hospital;

    @Column(name = "hcv_screening_result")
    @SerializedName("hcv_screening_result")
    @Expose
    public String hcv_screening_result;

    @Column(name = "hbv_screening_result")
    @SerializedName("hbv_screening_result")
    @Expose
    public String hbv_screening_result;

    @Column(name = "pcr_confirmation_hcv")
    @SerializedName("pcr_confirmation_hcv")
    @Expose
    public String pcr_confirmation_hcv;

    @Column(name = "pcr_confirmation_hbv")
    @SerializedName("pcr_confirmation_hbv")
    @Expose
    public String pcr_confirmation_hbv;
//
    @Column(name = "is_refered")
    @SerializedName("is_refered")
    @Expose
    public String is_refered;

    @Column(name = "patient_type")
    @SerializedName("patient_type")
    @Expose
    public String patient_type;

    @Column(name = "cbl")
    @SerializedName("cbl")
    @Expose
    public String cbl;
//
    @Column(name = "bbl")
    @SerializedName("bbl")
    @Expose
    public String bbl;

    @Column(name = "hcv_medicine_name")
    @SerializedName("hcv_medicine_name")
    @Expose
    public String hcv_medicine_name;
//
    @Column(name = "no_of_hcv_medicine_delivered")
    @SerializedName("no_of_hcv_medicine_delivered")
    @Expose
    public String no_of_hcv_medicine_delivered;

    @Column(name = "medicine_disbursment_date")
    @SerializedName("medicine_disbursment_date")
    @Expose
    public String medicine_disbursment_date;

    @Column(name = "no_of_hcv_followups")
    @SerializedName("no_of_hcv_followups")
    @Expose
    public String no_of_hcv_followups;

    @Column(name = "hcv_medicine_duration")
    @SerializedName("hcv_medicine_duration")
    @Expose
    public Integer hcv_medicine_duration;

    @Column(name = "next_status")
    @SerializedName("next_status")
    @Expose
    public String next_status;

    @Column(name = "is_ignore")
    @SerializedName("is_ignore")
    @Expose
    public String is_ignore;

    @Column(name = "is_assesment")
    @SerializedName("is_assesment")
    @Expose
    public String is_assesment;

    @Column(name = "is_sample")
    @SerializedName("is_sample")
    @Expose
    public String is_sample;

    @Column(name = "collect_sample")
    @SerializedName("collect_sample")
    @Expose
    public String collect_sample;

    @Column(name = "is_svr_form_submitted")
    @SerializedName("is_svr_form_submitted")
    @Expose
    public String is_svr_form_submitted;

    @Column(name = "is_svr_sample")
    @SerializedName("is_svr_sample")
    @Expose
    public String is_svr_sample;

    @Column(name = "previous_hbv")
    @SerializedName("previous_hbv")
    @Expose
    public String previous_hbv;

    @Column(name = "previous_hcv")
    @SerializedName("previous_hcv")
    @Expose
    public String previous_hcv;

    @Column(name = "rapid_testing")
    @SerializedName("rapid_testing")
    @Expose
    public String rapid_testing;

    @Column(name = "contact_no_self")
    @SerializedName("contact_no_self")
    @Expose
    public String contact_no_self;

    @Column(name = "postal_address")
    @SerializedName("postal_address")
    @Expose
    public String postal_address;

    @Column(name = "is_re_register")
    @SerializedName("is_re_register")
    @Expose
    public String is_re_register;

    @Column(name = "is_vital")
    @SerializedName("is_vital")
    @Expose
    public String is_vital;

    @Column(name = "next_of_kin_cnic")
    @SerializedName("next_of_kin_cnic")
    @Expose
    public String next_of_kin_cnic;

    @Column(name = "is_referal")
    @SerializedName("is_referal")
    @Expose
    public String is_referal;

    @Column(name = "no_of_medicine_delivered")
    @SerializedName("no_of_medicine_delivered")
    @Expose
    public String no_of_medicine_delivered;

    @Column(name = "is_treatment")
    @SerializedName("is_treatment")
    @Expose
    public String is_treatment;

    @Column(name = "is_closed")
    @SerializedName("is_closed")
    @Expose
    public String is_closed;

    @Column(name = "is_terminate")
    @SerializedName("is_terminate")
    @Expose
    public String is_terminate;

    @Column(name = "baseline_result_type")
    @SerializedName("baseline_result_type")
    @Expose
    public String baseline_result_type;

    @Column(name = "vaccinate")
    @SerializedName("vaccinate")
    @Expose
    public String vaccinate;

    @Column(name = "cnic_status")
    @SerializedName("cnic_status")
    @Expose
    public String cnic_status;

    @Column(name = "division")
    @SerializedName("division")
    @Expose
    public int division;

    @Column(name = "district")
    @SerializedName("district")
    @Expose
    public int district;

    @Column(name = "tehsil")
    @SerializedName("tehsil")
    @Expose
    public int tehsil;

    @Column(name = "hospital")
    @SerializedName("hospital")
    @Expose
    public int hospital;

    @Column(name = "patient_dob")
    @SerializedName("patient_dob")
    @Expose
    public String patient_dob;

    @Column(name = "is_type_change")
    @SerializedName("is_type_change")
    @Expose
    public String is_type_change;

    @Column(name = "lost_followup_id")
    @SerializedName("lost_followup_id")
    @Expose
    public String lost_followup_id;

    @Column(name = "hcv_viral_count")
    @SerializedName("hcv_viral_count")
    @Expose
    public String hcv_viral_count;

    @Column(name = "hbv_viral_count")
    @SerializedName("hbv_viral_count")
    @Expose
    public String  hbv_viral_count;

    @Column(name = "sample_id")
    @SerializedName("sample_id")
    @Expose
    public String sample_id;

    @Column(name = "is_cirrhotic_patient")
    @SerializedName("is_cirrhotic_patient")
    @Expose
    public String is_cirrhotic_patient;

    @Column(name = "IsActive")
    @SerializedName("IsActive")
    @Expose
    public Integer IsActive;

    @Column(name = "ISTransfer")
    @SerializedName("ISTransfer")
    @Expose
    public Integer ISTransfer;

    @Column(name = "ISRelease")
    @SerializedName("ISRelease")
    @Expose
    public Integer ISRelease;


    @Column(name = "IsMedicine")
    @SerializedName("IsMedicine")
    @Expose
    public Integer IsMedicine;


    @Column(name = "finger_print1")
    @SerializedName("finger_print1")
    @Expose
    public String finger_print1;

    @Column(name = "finger_print2")
    @SerializedName("finger_print2")
    @Expose
    public String finger_print2;


    @Column(name = "prison_transfer_status")
    @SerializedName("prison_transfer_status")
    @Expose
    public String prison_transfer_status;

    @Column(name = "transfer_flag")
    @SerializedName("transfer_flag")
    @Expose
    public Integer transfer_flag;


    @Column(name = "current_hospital_name")
    @SerializedName("current_hospital_name")
    @Expose
    public String current_hospital_name;

    @Column(name = "ex_hospital_name")
    @SerializedName("ex_hospital_name")
    @Expose
    public String ex_hospital_name;


    @Column(name = "test_type")
    @SerializedName("test_type")
    @Expose
    private String test_type;


    @Column(name = "sample_number")
    @SerializedName("sample_number")
    @Expose
    private String sample_number;


    @Column(name = "patient_stage")
    @SerializedName("patient_stage")
    @Expose
    private String patient_stage;
    @Column(name = "is_hcv_detected")
    @SerializedName("is_hcv_detected")
    @Expose
    private String is_hcv_detected;

    @Column(name = "is_hbv_detected")
    @SerializedName("is_hbv_detected")
    @Expose
    private String is_hbv_detected;

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

    public String getPatient_stage() {
        return patient_stage;
    }

    public void setPatient_stage(String patient_stage) {
        this.patient_stage = patient_stage;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public String getSample_number() {
        return sample_number;
    }

    public void setSample_number(String sample_number) {
        this.sample_number = sample_number;
    }

    public Integer getIsMedicine() {
        return IsMedicine;
    }

    public void setIsMedicine(Integer isMedicine) {
        IsMedicine = isMedicine;
    }

    public Integer getISTransfer() {
        return ISTransfer;
    }

    public void setISTransfer(Integer ISTransfer) {
        this.ISTransfer = ISTransfer;
    }

    public Integer getTransfer_flag() {
        return transfer_flag;
    }

    public void setTransfer_flag(Integer transfer_flag) {
        this.transfer_flag = transfer_flag;
    }

    public String getCurrent_hospital_name() {
        return current_hospital_name;
    }

    public void setCurrent_hospital_name(String current_hospital_name) {
        this.current_hospital_name = current_hospital_name;
    }

    public String getEx_hospital_name() {
        return ex_hospital_name;
    }

    public void setEx_hospital_name(String ex_hospital_name) {
        this.ex_hospital_name = ex_hospital_name;
    }

    public String getPrison_transfer_status() {
        return prison_transfer_status;
    }

    public void setPrison_transfer_status(String prison_transfer_status) {
        this.prison_transfer_status = prison_transfer_status;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
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

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getTehsil() {
        return tehsil;
    }

    public void setTehsil(int tehsil) {
        this.tehsil = tehsil;
    }

    public int getHospital() {
        return hospital;
    }

    public void setHospital(int hospital) {
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

    public String getHcv_viral_count() {
        return hcv_viral_count;
    }

    public void setHcv_viral_count(String hcv_viral_count) {
        this.hcv_viral_count = hcv_viral_count;
    }

    public String getHbv_viral_count() {
        return hbv_viral_count;
    }

    public void setHbv_viral_count(String hbv_viral_count) {
        this.hbv_viral_count = hbv_viral_count;
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getIs_cirrhotic_patient() {
        return is_cirrhotic_patient;
    }

    public void setIs_cirrhotic_patient(String is_cirrhotic_patient) {
        this.is_cirrhotic_patient = is_cirrhotic_patient;
    }

    public Integer getIsActive() {
        return IsActive;
    }

    public void setIsActive(Integer isActive) {
        IsActive = isActive;
    }

    public String getFinger_print1() {
        return finger_print1;
    }

    public void setFinger_print1(String finger_print1) {
        this.finger_print1 = finger_print1;
    }

    public String getFinger_print2() {
        return finger_print2;
    }

    public void setFinger_print2(String finger_print2) {
        this.finger_print2 = finger_print2;
    }

    public Integer getISRelease() {
        return ISRelease;
    }

    public void setISRelease(Integer ISRelease) {
        this.ISRelease = ISRelease;
    }

    public static List<userdataaa> getall() {
        return new Select()
                .from(userdataaa.class)
                .execute();
    }


    public static List<userdataaa> pendingvitalwithfingerprints() {
        return new Select()
                .from(userdataaa.class)
                .execute();
    }


    public static List<userdataaa> searchByCNICgetall() {
        return new Select()
                .from(userdataaa.class)
                .where("ISVital = ?",0 )
                .execute();
    }



    public static List<userdataaa> getallfortranfer(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }


    public static List<userdataaa> transferstatus_not_null() {
        return new Select()
                .from(userdataaa.class)
                .where("transfer_flag = ?",1)
                .execute();
    }


    public static List<userdataaa> alldeletedpatients() {
        return new Select()
                .from(userdataaa.class)
                .where("IS_delete = ?",1)
                .execute();
    }


    public static List<userdataaa> getallISMedicine() {
        return new Select()
                .from(userdataaa.class)
                .where("IsMedicine = ?",1)
                .where("cbl = ?","Y")
                .or("bbl = ?","Y")
                .execute();
    }

    public static List<userdataaa> getallISMedicinepending() {
        return new Select()
                .from(userdataaa.class)
                .where("IsMedicine = ?",1)
                .execute();
    }


    public static List<userdataaa> searchByCNICLeader(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?",1)
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<userdataaa> searchByCNICLeadermedicine(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("IsMedicine = ?",1)
                .where("self_cnic = ?",cnic)
                .execute();
    }


    public static List<userdataaa> searchBypatientid(String pid) {
        return new Select()
                .from(userdataaa.class)
                .where("patient_id = ?",pid)
                .execute();
    }
    public static List<userdataaa> searchBylongid(Long pid) {
        return new Select()
                .from(userdataaa.class)
                .where("mobile_id = ?",pid)
                .execute();
    }

    public static List<userdataaa> searchbyfingerprint(String fingerprint) {
        return new Select()
                .from(userdataaa.class)
                .where("finger_print2 = ?",fingerprint)
                .execute();
    }

    public static List<userdataaa> searchByMRNOLeader(String mrno) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
                .where("mrn_no = ?",mrno )
                .execute();
    }

    public static List<userdataaa> searchByMRNOmedicine(String mrno) {
        return new Select()
                .from(userdataaa.class)
                .where("IsMedicine = ?",1)
                .where("mrn_no = ?",mrno )
                .execute();
    }

    public static List<userdataaa> searchByMRNOjail(String mrno) {
        return new Select()
                .from(userdataaa.class)
                .where("ISTransfer = ?",1 )
                .where("mrn_no = ?",mrno )
                .execute();
    }

    public static List<userdataaa> searchByMRNOjailrelease(String mrno) {
        return new Select()
                .from(userdataaa.class)
                .where("ISRelease = ?",1 )
                .where("mrn_no = ?",mrno )
                .execute();
    }



    public static List<userdataaa> searchBynameLeader(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<userdataaa> searchBynamejail(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("ISTransfer = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<userdataaa> searchBynamejailrelease(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("ISRelease = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }


    public static List<userdataaa> searchByPhoneLeader(String phone) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
                .where("contact_no_self = ?",phone)
                .execute();
    }


    public static userdataaa searchByPhone(String phone) {
        return new Select()
                .from(userdataaa.class)
                .where("contact_no_self = ?",phone)
                .executeSingle();
    }

    public static userdataaa searchlocal_fingerprint(String fignerprint) {
        return new Select()
                .from(userdataaa.class)
                .where("finger_print2 = ?",fignerprint)
                .executeSingle();
    }

    public static void deleteAll() {
        new Delete().from(userdataaa.class)
                .execute();
    }
    public static List<userdataaa> searchallhcvbaselineForm() {
        return new Select()
                .from(userdataaa.class)
                .where("pcr_confirmation_hcv = ?","y" )
                .where("cbl = ?","Y" )
                .execute();
    }
    public static List<userdataaa> searchallhbvbaselineForm() {
        return new Select()
                .from(userdataaa.class)
                .where("pcr_confirmation_hbv = ?","y" )
                .where("bbl = ?","Y" )
                .execute();
    }
    public static List<userdataaa> searchallbothbaselineForm() {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
                .where("pcr_confirmation_hbv = ?","y" )
                .where("pcr_confirmation_hcv = ?","y" )
                .execute();
    }

    public static userdataaa searchByPatientId(Integer patientid) {
        return new Select()
                .from(userdataaa.class)
                .where("patient_id = ?",patientid )
                .executeSingle();
    }

    public static List<userdataaa> searchByPatientIdtransferout(Integer patientid) {
        return new Select()
                .from(userdataaa.class)
                .where("patient_id = ?",patientid )
                .where("ISRelease = ?",0 )
                .execute();
    }


    public static List<userdataaa> searchByPatientcnic(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("self_cnic = ?",cnic )
                .where("ISTransfer = ?",1 )
                .execute();
    }

    public static List<userdataaa> searchByPatientcnicrelease(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("self_cnic = ?",cnic )
                .where("ISRelease = ?",1 )
                .execute();
    }

    public static List<userdataaa> searchBySync() {
        return new Select()
                .from(userdataaa.class)
                .where("IsSync = ?",0)
                .execute();
    }

    public static userdataaa searchBycnic(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("self_cnic = ?",cnic)
                .executeSingle();
    }

    public static userdataaa searchBypid(int pid) {
        return new Select()
                .from(userdataaa.class)
                .where("mobile_id = ?",pid)
                .executeSingle();
    }

    public static List<userdataaa> searchallISvital() {
        return new Select()
                .from(userdataaa.class)
                .where("ISVital = ?",0 )
                .execute();
    }

    public static List<userdataaa> pendingassessments() {
        return new Select()
                .from(userdataaa.class)
                .where("IS_assessment = ?",0 )
                .execute();
    }

    public static List<userdataaa> pendingvaccination() {
        return new Select()
                .from(userdataaa.class)
                .where("IS_Vaccination = ?",0 )
                .execute();
    }
    public static List<userdataaa> pendingsamples() {
        return new Select()
                .from(userdataaa.class)
                .where("ISSample = ?",0 )
                .execute();
    }

    public static List<userdataaa> searchByName(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("ISVital = ?",0 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }
    public static List<userdataaa> searchByISvital(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("ISVital = ?",0 )
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<userdataaa> searchallISAssessment() {
        return new Select()
                .from(userdataaa.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .execute();
    }
    public static List<userdataaa> searchByNameAssessment(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<userdataaa> searchByISAssessment(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .where("self_cnic = ?",cnic)
                .execute();
    }
    public static List<userdataaa> searchallISSample() {
        return new Select()
                .from(userdataaa.class)
                .where("ISSample = ?",0 )
                .where("ISVital = ?",1 )
                .where("IS_assessment = ?",1 )
                .execute();
    }

    public static List<userdataaa> searchallnewPatients() {
        return new Select()
                .from(userdataaa.class)
                .where("IS_delete = ?",0 )
                .where("new_patient = ?",1 )
//                .and("IS_delete",0)
                .execute();
    }


    public static List<userdataaa> searchByNameSample(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("ISSample = ?",0 )
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }


    public static List<userdataaa> searchByCNICSample(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("ISSample = ?",0 )
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<userdataaa> searchallISVaccination() {
        return new Select()
                .from(userdataaa.class)
                .where("ISVital = ?",1 )
                .where("IS_assessment = ?",1 )
                .where("IS_Vaccination = ?",0 )
                .execute();
    }

    public static List<userdataaa> searchByNameVaccination(String name) {
        return new Select()
                .from(userdataaa.class)
                .where("IS_Vaccination = ?",0 )
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<userdataaa> searchByISVaccination(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("IS_Vaccination = ?",0 )
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("self_cnic = ?",cnic)
                .execute();
    }

}
