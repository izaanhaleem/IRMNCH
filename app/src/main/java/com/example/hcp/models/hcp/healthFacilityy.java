package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Table(name = "HealthFacilityTable")
public class healthFacilityy extends Model {
    @Column(name = "hf_code",unique = true)
    @SerializedName("hf_code")
    @Expose
    public String hf_code;

    @Column(name = "hf_name")
    @SerializedName("hf_name")
    @Expose
    public String hf_name;

    @Column(name = "tehsil_code")
    @SerializedName("tehsil_code")
    @Expose
    public String tehsil_code;

    public String getHf_code() {
        return hf_code;
    }

    public void setHf_code(String hf_code) {
        this.hf_code = hf_code;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }


    public static List<healthFacilityy> getAll() {
        return new Select()
                .from(healthFacilityy.class)
                .orderBy("Title")
                .execute();
    }

    public static List<healthFacilityy> searchById(String tahsilId) {
        return new Select()
                .from(healthFacilityy.class)
                .where("tehsil_code = ?",tahsilId)
                .orderBy("RANDOM()")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(healthFacilityy.class)
                .execute();
    }
}
