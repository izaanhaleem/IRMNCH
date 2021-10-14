package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "Assessmentt",id = "mobile_id")
public class Assessmentt extends Model {



    @Column(name = "user_hospital")
    @SerializedName("user_hospital")
    @Expose
    private String user_hospital;

    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    public Integer patient_id;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @Column(name = "created")
    @SerializedName("created")
    @Expose
    private Integer created;

    @Column(name = "updated")
    @SerializedName("updated")
    @Expose
    private Integer updated;

    @Column(name = "note")
    @SerializedName("note")
    @Expose
    private String note;

    @Column(name = "frequent_therapeutic_injections")
    @SerializedName("frequent_therapeutic_injections")
    @Expose
    private String frequent_therapeutic_injections;

    @Column(name = "confirmed_case_of_stds")
    @SerializedName("confirmed_case_of_stds")
    @Expose
    private String confirmed_case_of_stds;

    @Column(name = "invasive_medical_and_surgical_intervention")
    @SerializedName("invasive_medical_and_surgical_intervention")
    @Expose
    private String invasive_medical_and_surgical_intervention;

    @Column(name = "surgery_type")
    @SerializedName("surgery_type")
    @Expose
    private String surgery_type;

    @Column(name = "surgery_when")
    @SerializedName("surgery_when")
    @Expose
    private String surgery_when;

    @Column(name = "close_contact_of_a_known_case_of_hcv_hbv")
    @SerializedName("close_contact_of_a_known_case_of_hcv_hbv")
    @Expose
    private String close_contact_of_a_known_case_of_hcv_hbv;

    @Column(name = "close_contact_is_on_treatment")
    @SerializedName("close_contact_is_on_treatment")
    @Expose
    private String close_contact_is_on_treatment;

    @Column(name = "blood_transfusion")
    @SerializedName("blood_transfusion")
    @Expose
    private String blood_transfusion;

    @Column(name = "blood_transfusion_when")
    @SerializedName("blood_transfusion_when")
    @Expose
    private String blood_transfusion_when;

    @Column(name = "blood_bank")
    @SerializedName("blood_bank")
    @Expose
    private String blood_bank;

    @Column(name = "confirmed_hiv_positive_persons")
    @SerializedName("confirmed_hiv_positive_persons")
    @Expose
    private String confirmed_hiv_positive_persons;

    @Column(name = "ever_been_hospitalized")
    @SerializedName("ever_been_hospitalized")
    @Expose
    private String ever_been_hospitalized;

    @Column(name = "hospitalization_within_last_2_years")
    @SerializedName("hospitalization_within_last_2_years")
    @Expose
    private String hospitalization_within_last_2_years;

    @Column(name = "individuals_with_tattooing_ear_nose_piercing")
    @SerializedName("individuals_with_tattooing_ear_nose_piercing")
    @Expose
    private String individuals_with_tattooing_ear_nose_piercing;

    @Column(name = "injectable_drug_user")
    @SerializedName("injectable_drug_user")
    @Expose
    private String injectable_drug_user;

    @Column(name = "dental_intervention")
    @SerializedName("dental_intervention")
    @Expose
    private String dental_intervention;

    @Column(name = "dental_clinic")
    @SerializedName("dental_clinic")
    @Expose
    private String dental_clinic;

    @Column(name = "history_of_multiple_sex_partners")
    @SerializedName("history_of_multiple_sex_partners")
    @Expose
    private String history_of_multiple_sex_partners;

    @Column(name = "truck_driver_or_transgender")
    @SerializedName("truck_driver_or_transgender")
    @Expose
    private String truck_driver_or_transgender;

//    @Column(name = "jaundice")
//    @SerializedName("jaundice")
//    @Expose
//    private String jaundice;
//
//    @Column(name = "unexplained_fever")
//    @SerializedName("unexplained_fever")
//    @Expose
//    private String unexplained_fever;

    @Column(name = "dark_colored_urine")
    @SerializedName("dark_colored_urine")
    @Expose
    private String dark_colored_urine;

    @Column(name = "loss_of_appetite")
    @SerializedName("loss_of_appetite")
    @Expose
    private String loss_of_appetite;

    @Column(name = "light_colored_faeces")
    @SerializedName("light_colored_faeces")
    @Expose
    private String light_colored_faeces;

    @Column(name = "fatigue")
    @SerializedName("fatigue")
    @Expose
    private String fatigue;

    @Column(name = "muscle_pain")
    @SerializedName("muscle_pain")
    @Expose
    private String muscle_pain;

    @Column(name = "nausea")
    @SerializedName("nausea")
    @Expose
    private String nausea;

    @Column(name = "stomach_ache")
    @SerializedName("stomach_ache")
    @Expose
    private String stomach_ache;

    @Column(name = "right_upper_quadrant_tenderness")
    @SerializedName("right_upper_quadrant_tenderness")
    @Expose
    private String right_upper_quadrant_tenderness;

    @Column(name = "gastric_irritation_burning")
    @SerializedName("gastric_irritation_burning")
    @Expose
    private String gastric_irritation_burning;

    @Column(name = "unusual_urethral_discharge")
    @SerializedName("unusual_urethral_discharge")
    @Expose
    private String unusual_urethral_discharge;

    @Column(name = "ear_nose_pirecing")
    @SerializedName("ear_nose_pirecing")
    @Expose
    private String ear_nose_pirecing;

    @Column(name = "transgender")
    @SerializedName("transgender")
    @Expose
    private String transgender;

    @Column(name = "sharing_toothbrush")
    @SerializedName("sharing_toothbrush")
    @Expose
    private String sharing_toothbrush;

    @Column(name = "sharing_hair_comb")
    @SerializedName("sharing_hair_comb")
    @Expose
    private String sharing_hair_comb;

    @Column(name = "rapid_testing")
    @SerializedName("rapid_testing")
    @Expose
    private String rapid_testing;

    @Column(name = "is_hbv_test")
    @SerializedName("is_hbv_test")
    @Expose
    private String is_hbv_test;

    @Column(name = "is_hcv_test")
    @SerializedName("is_hcv_test")
    @Expose
    private String is_hcv_test;

    @Column(name = "vaccination")
    @SerializedName("vaccination")
    @Expose
    private String vaccination;

    @Column(name = "pcr_option")
    @SerializedName("pcr_option")
    @Expose
    private String pcr_option;

    @Column(name = "pcr")
    @SerializedName("pcr")
    @Expose
    private String pcr;

    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;

    @Column(name = "counselling")
    @SerializedName("counselling")
    @Expose
    public String counselling;

    public String getCounselling() {
        return counselling;
    }

    public void setCounselling(String counselling) {
        this.counselling = counselling;
    }

    public String getIs_new_patient() {
        return is_new_patient;
    }

    public void setIs_new_patient(String is_new_patient) {
        this.is_new_patient = is_new_patient;
    }

    @Column(name = "is_new_patient")
    @SerializedName("is_new_patient")
    @Expose
    public String is_new_patient;

    public String getUser_hospital() {
        return user_hospital;
    }

    public void setUser_hospital(String user_hospital) {
        this.user_hospital = user_hospital;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrequent_therapeutic_injections() {
        return frequent_therapeutic_injections;
    }

    public void setFrequent_therapeutic_injections(String frequent_therapeutic_injections) {
        this.frequent_therapeutic_injections = frequent_therapeutic_injections;
    }

    public String getConfirmed_case_of_stds() {
        return confirmed_case_of_stds;
    }

    public void setConfirmed_case_of_stds(String confirmed_case_of_stds) {
        this.confirmed_case_of_stds = confirmed_case_of_stds;
    }

    public String getInvasive_medical_and_surgical_intervention() {
        return invasive_medical_and_surgical_intervention;
    }

    public void setInvasive_medical_and_surgical_intervention(String invasive_medical_and_surgical_intervention) {
        this.invasive_medical_and_surgical_intervention = invasive_medical_and_surgical_intervention;
    }

    public String getSurgery_type() {
        return surgery_type;
    }

    public void setSurgery_type(String surgery_type) {
        this.surgery_type = surgery_type;
    }

    public String getSurgery_when() {
        return surgery_when;
    }

    public void setSurgery_when(String surgery_when) {
        this.surgery_when = surgery_when;
    }

    public String getClose_contact_of_a_known_case_of_hcv_hbv() {
        return close_contact_of_a_known_case_of_hcv_hbv;
    }

    public void setClose_contact_of_a_known_case_of_hcv_hbv(String close_contact_of_a_known_case_of_hcv_hbv) {
        this.close_contact_of_a_known_case_of_hcv_hbv = close_contact_of_a_known_case_of_hcv_hbv;
    }

    public String getClose_contact_is_on_treatment() {
        return close_contact_is_on_treatment;
    }

    public void setClose_contact_is_on_treatment(String close_contact_is_on_treatment) {
        this.close_contact_is_on_treatment = close_contact_is_on_treatment;
    }

    public String getBlood_transfusion() {
        return blood_transfusion;
    }

    public void setBlood_transfusion(String blood_transfusion) {
        this.blood_transfusion = blood_transfusion;
    }

    public String getBlood_transfusion_when() {
        return blood_transfusion_when;
    }

    public void setBlood_transfusion_when(String blood_transfusion_when) {
        this.blood_transfusion_when = blood_transfusion_when;
    }

    public String getBlood_bank() {
        return blood_bank;
    }

    public void setBlood_bank(String blood_bank) {
        this.blood_bank = blood_bank;
    }

    public String getConfirmed_hiv_positive_persons() {
        return confirmed_hiv_positive_persons;
    }

    public void setConfirmed_hiv_positive_persons(String confirmed_hiv_positive_persons) {
        this.confirmed_hiv_positive_persons = confirmed_hiv_positive_persons;
    }

    public String getEver_been_hospitalized() {
        return ever_been_hospitalized;
    }

    public void setEver_been_hospitalized(String ever_been_hospitalized) {
        this.ever_been_hospitalized = ever_been_hospitalized;
    }

    public String getHospitalization_within_last_2_years() {
        return hospitalization_within_last_2_years;
    }

    public void setHospitalization_within_last_2_years(String hospitalization_within_last_2_years) {
        this.hospitalization_within_last_2_years = hospitalization_within_last_2_years;
    }

    public String getIndividuals_with_tattooing_ear_nose_piercing() {
        return individuals_with_tattooing_ear_nose_piercing;
    }

    public void setIndividuals_with_tattooing_ear_nose_piercing(String individuals_with_tattooing_ear_nose_piercing) {
        this.individuals_with_tattooing_ear_nose_piercing = individuals_with_tattooing_ear_nose_piercing;
    }

    public String getInjectable_drug_user() {
        return injectable_drug_user;
    }

    public void setInjectable_drug_user(String injectable_drug_user) {
        this.injectable_drug_user = injectable_drug_user;
    }

    public String getDental_intervention() {
        return dental_intervention;
    }

    public void setDental_intervention(String dental_intervention) {
        this.dental_intervention = dental_intervention;
    }

    public String getDental_clinic() {
        return dental_clinic;
    }

    public void setDental_clinic(String dental_clinic) {
        this.dental_clinic = dental_clinic;
    }

    public String getHistory_of_multiple_sex_partners() {
        return history_of_multiple_sex_partners;
    }

    public void setHistory_of_multiple_sex_partners(String history_of_multiple_sex_partners) {
        this.history_of_multiple_sex_partners = history_of_multiple_sex_partners;
    }

    public String getTruck_driver_or_transgender() {
        return truck_driver_or_transgender;
    }

    public void setTruck_driver_or_transgender(String truck_driver_or_transgender) {
        this.truck_driver_or_transgender = truck_driver_or_transgender;
    }

//    public String getJaundice() {
//        return jaundice;
//    }
//
//    public void setJaundice(String jaundice) {
//        this.jaundice = jaundice;
//    }
//
//    public String getUnexplained_fever() {
//        return unexplained_fever;
//    }
//
//    public void setUnexplained_fever(String unexplained_fever) {
//        this.unexplained_fever = unexplained_fever;
//    }

    public String getDark_colored_urine() {
        return dark_colored_urine;
    }

    public void setDark_colored_urine(String dark_colored_urine) {
        this.dark_colored_urine = dark_colored_urine;
    }

    public String getLoss_of_appetite() {
        return loss_of_appetite;
    }

    public void setLoss_of_appetite(String loss_of_appetite) {
        this.loss_of_appetite = loss_of_appetite;
    }

    public String getLight_colored_faeces() {
        return light_colored_faeces;
    }

    public void setLight_colored_faeces(String light_colored_faeces) {
        this.light_colored_faeces = light_colored_faeces;
    }

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    public String getMuscle_pain() {
        return muscle_pain;
    }

    public void setMuscle_pain(String muscle_pain) {
        this.muscle_pain = muscle_pain;
    }

    public String getNausea() {
        return nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    public String getStomach_ache() {
        return stomach_ache;
    }

    public void setStomach_ache(String stomach_ache) {
        this.stomach_ache = stomach_ache;
    }

    public String getRight_upper_quadrant_tenderness() {
        return right_upper_quadrant_tenderness;
    }

    public void setRight_upper_quadrant_tenderness(String right_upper_quadrant_tenderness) {
        this.right_upper_quadrant_tenderness = right_upper_quadrant_tenderness;
    }

    public String getGastric_irritation_burning() {
        return gastric_irritation_burning;
    }

    public void setGastric_irritation_burning(String gastric_irritation_burning) {
        this.gastric_irritation_burning = gastric_irritation_burning;
    }

    public String getUnusual_urethral_discharge() {
        return unusual_urethral_discharge;
    }

    public void setUnusual_urethral_discharge(String unusual_urethral_discharge) {
        this.unusual_urethral_discharge = unusual_urethral_discharge;
    }

    public String getEar_nose_pirecing() {
        return ear_nose_pirecing;
    }

    public void setEar_nose_pirecing(String ear_nose_pirecing) {
        this.ear_nose_pirecing = ear_nose_pirecing;
    }

    public String getTransgender() {
        return transgender;
    }

    public void setTransgender(String transgender) {
        this.transgender = transgender;
    }

    public String getSharing_toothbrush() {
        return sharing_toothbrush;
    }

    public void setSharing_toothbrush(String sharing_toothbrush) {
        this.sharing_toothbrush = sharing_toothbrush;
    }

    public String getSharing_hair_comb() {
        return sharing_hair_comb;
    }

    public void setSharing_hair_comb(String sharing_hair_comb) {
        this.sharing_hair_comb = sharing_hair_comb;
    }

    public String getRapid_testing() {
        return rapid_testing;
    }

    public void setRapid_testing(String rapid_testing) {
        this.rapid_testing = rapid_testing;
    }

    public String getIs_hbv_test() {
        return is_hbv_test;
    }

    public void setIs_hbv_test(String is_hbv_test) {
        this.is_hbv_test = is_hbv_test;
    }

    public String getIs_hcv_test() {
        return is_hcv_test;
    }

    public void setIs_hcv_test(String is_hcv_test) {
        this.is_hcv_test = is_hcv_test;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getPcr_option() {
        return pcr_option;
    }

    public void setPcr_option(String pcr_option) {
        this.pcr_option = pcr_option;
    }

    public String getPcr() {
        return pcr;
    }

    public void setPcr(String pcr) {
        this.pcr = pcr;
    }

    public static List<Assessmentt> searchforvaccination() {
        return new Select()
                .from(Assessmentt.class)
                .where("is_hbv_test = ?","N")
                .execute();
    }

    public static List<Assessmentt> getall() {
        return new Select()
                .from(Assessmentt.class)
                .execute();
    }

    public static Assessmentt searchByid(Integer id) {
        return new Select()
                .from(Assessmentt.class)
                .where("mobile_id = ?",id)
                .executeSingle();
    }

    public static Assessmentt searchBypid(Integer id) {
        return new Select()
                .from(Assessmentt.class)
                .where("patient_id = ?",id)
                .executeSingle();
    }
//    public static Assessmentt searchBycninc(String  cnic) {
//        return new Select()
//                .from(Assessmentt.class)
//                .where("self_cnic = ?",cnic)
//                .executeSingle();
//    }


    public static List<Assessmentt> searchBySync() {
        return new Select()
                .from(Assessmentt.class)
                .where("IsSync = ?",0)
                .execute();
    }
    public static void deleteAll() {
        new Delete().from(Assessmentt.class)
                .execute();
    }

}
