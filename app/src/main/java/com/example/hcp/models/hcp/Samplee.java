package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "Samplee",id = "mobile_id")
public class Samplee extends Model {

    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    public Integer hospital_id;

    @Column(name = "pid")
    @SerializedName("pid")
    @Expose
    public Integer pid;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public Integer user_id;

    @Column(name = "sample_no")
    @SerializedName("sample_no")
    @Expose
    public String sample_no;


    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;


    public Integer getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Integer hospital_id) {
        this.hospital_id = hospital_id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getSample_no() {
        return sample_no;
    }

    public void setSample_no(String sample_no) {
        this.sample_no = sample_no;
    }


    public static void deleteAll() {
        new Delete().from(Samplee.class)
                .execute();
    }

    public static List<Samplee> getall() {
        return new Select()
                .from(Samplee.class)
                .execute();
    }
    public static Samplee searchByid(Integer id) {
        return new Select()
                .from(Samplee.class)
                .where("mobile_id = ?",id)
                .executeSingle();
    }
    public static List<Samplee> searchBySync() {
        return new Select()
                .from(Samplee.class)
                .where("IsSync = ?",0)
                .execute();
    }
}
