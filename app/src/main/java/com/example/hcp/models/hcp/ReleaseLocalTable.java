package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "Release_Local_Table")
public class ReleaseLocalTable extends Model {


    @Column(name = "is_prison_release")
    @SerializedName("is_prison_release")
    @Expose
    private String is_prison_release;

    @Column(name = "patient_id")
    @SerializedName("patient_id")
    @Expose
    private Integer patient_id;

    @Column(name = "is_sycn")
    @SerializedName("is_sycn")
    @Expose
    private Integer is_sycn;


    public String getIs_prison_release() {
        return is_prison_release;
    }

    public void setIs_prison_release(String is_prison_release) {
        this.is_prison_release = is_prison_release;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getIs_sycn() {
        return is_sycn;
    }

    public void setIs_sycn(Integer is_sycn) {
        this.is_sycn = is_sycn;
    }


    public static List<ReleaseLocalTable> searchBySync() {
        return new Select()
                .from(ReleaseLocalTable.class)
                .where("is_sycn = ?",0)
                .execute();
    }

    public static ReleaseLocalTable searchBypid(Integer id) {
        return new Select()
                .from(ReleaseLocalTable.class)
                .where("patient_id = ?",id)
                .executeSingle();
    }

    public static void deleteAll() {
        new Delete().from(ReleaseLocalTable.class)
                .execute();
    }


}
