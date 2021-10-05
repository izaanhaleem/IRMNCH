package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "save_jail")
public class savejail extends Model {

    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    public Integer patient_id;

    @Column(name = "prison_transfer_status")
    @SerializedName("prison_transfer_status")
    @Expose
    public String prison_transfer_status;

    @Column(name = "current_hospital_name")
    @SerializedName("current_hospital_name")
    @Expose
    public String current_hospital_name;

    @Column(name = "new_hospital_id")
    @SerializedName("new_hospital_id")
    @Expose
    public Integer new_hospital_id;

    @Column(name = "new_hospital_name")
    @SerializedName("new_hospital_name")
    @Expose
    public String new_hospital_name;


    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPrison_transfer_status() {
        return prison_transfer_status;
    }

    public void setPrison_transfer_status(String prison_transfer_status) {
        this.prison_transfer_status = prison_transfer_status;
    }

    public String getCurrent_hospital_name() {
        return current_hospital_name;
    }

    public void setCurrent_hospital_name(String current_hospital_name) {
        this.current_hospital_name = current_hospital_name;
    }

    public Integer getNew_hospital_id() {
        return new_hospital_id;
    }

    public void setNew_hospital_id(Integer new_hospital_id) {
        this.new_hospital_id = new_hospital_id;
    }

    public String getNew_hospital_name() {
        return new_hospital_name;
    }

    public void setNew_hospital_name(String new_hospital_name) {
        this.new_hospital_name = new_hospital_name;
    }

    public Integer getIsSync() {
        return IsSync;
    }

    public void setIsSync(Integer isSync) {
        IsSync = isSync;
    }

    public static void deleteAll() {
        new Delete().from(savejail.class)
                .execute();
    }


    public static List<savejail> searchBySync() {
        return new Select()
                .from(savejail.class)
                .where("IsSync = ?",0)
                .execute();
    }
    public static savejail searchBypid(Integer id) {
        return new Select()
                .from(savejail.class)
                .where("patient_id = ?",id)
                .executeSingle();
    }

}
