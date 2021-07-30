package com.example.hcp.models.hcp;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "addvitalTable",id = "mobile_id")
public class addvitalll extends Model {

    @Column(name = "pid",unique = true)
    @SerializedName("pid")
    @Expose
    public Integer pid;

    @Column(name = "temperature")
    @SerializedName("temperature")
    @Expose
    public Double temperature;

    @Column(name = "pulse")
    @SerializedName("pulse")
    @Expose
    public Integer pulse;

    @Column(name = "bp_systolic")
    @SerializedName("bp_systolic")
    @Expose
    public Double bp_systolic;

    @Column(name = "bp_diastolic")
    @SerializedName("bp_diastolic")
    @Expose
    public Double bp_diastolic;

    @Column(name = "height")
    @SerializedName("height")
    @Expose
    public Double height;

    @Column(name = "weight")
    @SerializedName("weight")
    @Expose
    public Double weight;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public Integer user_id;




    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;



    @Column(name = "self_cnic")
    @SerializedName("self_cnic")
    @Expose
    public String self_cnic;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Double getBp_systolic() {
        return bp_systolic;
    }

    public void setBp_systolic(Double bp_systolic) {
        this.bp_systolic = bp_systolic;
    }

    public Double getBp_diastolic() {
        return bp_diastolic;
    }

    public void setBp_diastolic(Double bp_diastolic) {
        this.bp_diastolic = bp_diastolic;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getIsSync() {
        return IsSync;
    }

    public void setIsSync(Integer isSync) {
        IsSync = isSync;
    }

    public static List<addvitalll> searchBySync() {
        return new Select()
                .from(addvitalll.class)
                .where("IsSync = ?",0)
                .execute();
    }



    public static List<addvitalll> getall() {
        return new Select()
                .from(addvitalll.class)
                .execute();
    }

    public static addvitalll searchByid(Integer id) {
        return new Select()
                .from(addvitalll.class)
                .where("mobile_id = ?",id)
                .executeSingle();
    }
    public static addvitalll searchBypid(Integer id) {
        return new Select()
                .from(addvitalll.class)
                .where("pid = ?",id)
                .executeSingle();
    }

    public static addvitalll searchBycninc(String  cnic) {
        return new Select()
                .from(addvitalll.class)
                .where("self_cnic = ?",cnic)
                .executeSingle();
    }


    public static void deleteAll() {
        new Delete().from(addvitalll.class)
                .execute();
    }
}
