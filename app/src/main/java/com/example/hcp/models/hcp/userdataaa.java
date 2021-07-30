package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Table(name = "UserDataTableee")

public class userdataaa extends Model {

    @Column(name = "patient_id",unique = true)
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
    public String division;

    @Column(name = "district")
    @SerializedName("district")
    @Expose
    public String district;

    @Column(name = "tehsil")
    @SerializedName("tehsil")
    @Expose
    public String tehsil;

    @Column(name = "hospital")
    @SerializedName("hospital")
    @Expose
    public String hospital;

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
    public Integer hcv_viral_count;

    @Column(name = "hbv_viral_count")
    @SerializedName("hbv_viral_count")
    @Expose
    public Integer hbv_viral_count;


    @Column(name = "sample_id")
    @SerializedName("sample_id")
    @Expose
    public Integer sample_id;

    @Column(name = "is_cirrhotic_patient")
    @SerializedName("is_cirrhotic_patient")
    @Expose
    public String is_cirrhotic_patient;

    @Column(name = "IsActive")
    @SerializedName("IsActive")
    @Expose
    public Integer IsActive;

    public static List<userdataaa> searchByCNICLeader(String cnic) {
        return new Select()
                .from(userdataaa.class)
                .where("self_cnic = ?",cnic)
                .execute();
    }

    public static List<userdataaa> searchByMRNOLeader(String mrno) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
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
    public static List<userdataaa> searchByPhoneLeader(String phone) {
        return new Select()
                .from(userdataaa.class)
                .where("IsActive = ?" ,1)
                .where("contact_no_self = ?",phone)
                .execute();
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

}
