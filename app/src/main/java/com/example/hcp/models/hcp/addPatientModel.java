package com.example.hcp.models.hcp;

import android.graphics.ColorSpace;

import androidx.media.AudioAttributesCompat;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;


@Table(name = "patientModelTable",id = "mobile_id")
public class addPatientModel extends Model {
    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    public Integer patient_id;

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

    @Column(name = "patient_age")
    @SerializedName("patient_age")
    @Expose
    public Integer patient_age;

    @Column(name = "patient_dob")
    @SerializedName("patient_dob")
    @Expose
    public String patient_dob;

    @Column(name = "gender")
    @SerializedName("gender")
    @Expose
    public Integer gender;

    @Column(name = "self_cnic")
    @SerializedName("self_cnic")
    @Expose
    public String self_cnic;

    @Column(name = "contact_no_self")
    @SerializedName("contact_no_self")
    @Expose
    public String contact_no_self;

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

    @Column(name = "previous_hbv")
    @SerializedName("previous_hbv")
    @Expose
    public String previous_hbv;

    @Column(name = "previous_hcv")
    @SerializedName("previous_hcv")
    @Expose
    public String previous_hcv;

    @Column(name = "pcr_confirmation_hbv")
    @SerializedName("pcr_confirmation_hbv")
    @Expose
    public String pcr_confirmation_hbv;

    @Column(name = "pcr_confirmation_hcv")
    @SerializedName("pcr_confirmation_hcv")
    @Expose
    public String pcr_confirmation_hcv;

    @Column(name = "division")
    @SerializedName("division")
    @Expose
    public Integer division;

    @Column(name = "district")
    @SerializedName("district")
    @Expose
    public Integer district;

    @Column(name = "tehsil")
    @SerializedName("tehsil")
    @Expose
    public Integer tehsil;

    @Column(name = "hospital")
    @SerializedName("hospital")
    @Expose
    public Integer hospital;

    @Column(name = "identifier")
    @SerializedName("identifier")
    @Expose
    public String identifier;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public String user_id;

    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    public String hospital_id;


    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;

    @Column(name = "mrn_no")
    @SerializedName("mrn_no")
    @Expose
    public String  mrn_no;

    @Column(name = "patient_type")
    @SerializedName("patient_type")
    @Expose
    public String  patient_type;


    @SerializedName("mobile_id")
    @Expose
    public Long  mobile_id;

    @Column(name = "ISVital")
    @SerializedName("ISVital")
    @Expose
    public Integer ISVital;

    @Column(name = "IS_assessment")
    @SerializedName("IS_assessment")
    @Expose
    public Integer IS_assessment;

    @Column(name = "ISSample")
    @SerializedName("ISSample")
    @Expose
    public Integer ISSample;


    public Long getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(Long mobile_id) {
        this.mobile_id = mobile_id;
    }

    public String getPatient_type() {
        return patient_type;
    }

    public void setPatient_type(String patient_type) {
        this.patient_type = patient_type;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
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

    public Integer getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(Integer patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_dob() {
        return patient_dob;
    }

    public void setPatient_dob(String patient_dob) {
        this.patient_dob = patient_dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSelf_cnic() {
        return self_cnic;
    }

    public void setSelf_cnic(String self_cnic) {
        this.self_cnic = self_cnic;
    }

    public String getContact_no_self() {
        return contact_no_self;
    }

    public void setContact_no_self(String contact_no_self) {
        this.contact_no_self = contact_no_self;
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

    public String getPcr_confirmation_hbv() {
        return pcr_confirmation_hbv;
    }

    public void setPcr_confirmation_hbv(String pcr_confirmation_hbv) {
        this.pcr_confirmation_hbv = pcr_confirmation_hbv;
    }

    public String getPcr_confirmation_hcv() {
        return pcr_confirmation_hcv;
    }

    public void setPcr_confirmation_hcv(String pcr_confirmation_hcv) {
        this.pcr_confirmation_hcv = pcr_confirmation_hcv;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTehsil() {
        return tehsil;
    }

    public void setTehsil(Integer tehsil) {
        this.tehsil = tehsil;
    }

    public Integer getHospital() {
        return hospital;
    }

    public void setHospital(Integer hospital) {
        this.hospital = hospital;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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


    public static List<addPatientModel> getall() {
        return new Select()
                .from(addPatientModel.class)
                .execute();
    }


    public static List<addPatientModel> searchBySync() {
        return new Select()
                .from(addPatientModel.class)
                .where("IsSync = ?",0)
                .execute();
    }

    public static List<addPatientModel> searchByISvital(String cnic) {
        return new Select()
                  .from(addPatientModel.class)
                  .where("ISVital = ?",0 )
                  .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<addPatientModel> searchallISvital() {
        return new Select()
                .from(addPatientModel.class)
                .where("ISVital = ?",0 )
                .execute();
    }

    public static List<addPatientModel> searchallISAssessment() {
        return new Select()
                .from(addPatientModel.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .execute();
    }


    public static List<addPatientModel> searchallISSample() {
        return new Select()
                .from(addPatientModel.class)
                .where("ISSample = ?",0 )
                .where("ISVital = ?",1 )
                .where("IS_assessment = ?",1 )
                .execute();
    }


    public static List<addPatientModel> searchallpendingtreament() {
        return new Select()
                .from(addPatientModel.class)
                .where("ISSample = ?",1 )
                .where("ISVital = ?",1 )
                .where("IS_assessment = ?",1 )
                .execute();
    }


    public static List<addPatientModel> searchByISAssessment(String cnic) {
        return new Select()
                .from(addPatientModel.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<addPatientModel> searchByCNICSample(String cnic) {
        return new Select()
                .from(addPatientModel.class)
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<addPatientModel> searchByName(String name) {
        return new Select()
                .from(addPatientModel.class)
                .where("ISVital = ?",0 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<addPatientModel> searchByNameAssessment(String name) {
        return new Select()
                .from(addPatientModel.class)
                .where("IS_assessment = ?",0 )
                .where("ISVital = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }


    public static List<addPatientModel> searchByNameSample(String name) {
        return new Select()
                .from(addPatientModel.class)
                .where("IS_assessment = ?",1 )
                .where("ISVital = ?",1 )
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }





    public static List<addPatientModel> searchByCNICLeader(String cnic) {
        return new Select()
                .from(addPatientModel.class)
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static addPatientModel searchBycnic(String cnic) {
        return new Select()
                .from(addPatientModel.class)
                .where("self_cnic = ?",cnic)
                .executeSingle();
    }

    public static List<addPatientModel> searchByMrno(String mrno) {
        return new Select()
                .from(addPatientModel.class)
                .where("mrn_no = ?",mrno )
                .execute();
    }

    public static List<addPatientModel> searchBynameLeader(String name) {
        return new Select()
                .from(addPatientModel.class)
                .where("patient_name LIKE ?", new String[]{'%' + name + '%'})
                .execute();
    }

    public static List<addPatientModel> searchByID(Long id) {
        return new Select()
                .from(addPatientModel.class)
                .where("Id = ?",id)
                .execute();
    }

    public static addPatientModel searchByMobileId(Long id) {
        return new Select()
                .from(addPatientModel.class)
                .where("mobile_id = ?",id)

                .executeSingle();
    }

    public static void deleteAll() {
        new Delete().from(addPatientModel.class)
                .execute();
    }

}
