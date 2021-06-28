package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Table(name = "DistrictTable")
public class districtt extends Model {

    @Column(name = "district_code",unique = true)
    @SerializedName("district_code")
    @Expose
    public String district_code;

    @Column(name = "district_name")
    @SerializedName("district_name")
    @Expose
    public String district_name;

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
    public static List<districtt> getAll() {
        return new Select()
                .from(districtt.class)
                .orderBy("district_name")
                .execute();
    }

    public static List<districtt> getDistrictById(String divisioncode) {
        return new Select()
                .from(districtt.class)
                .where("district_code LIKE ?", new String[]{'%' + divisioncode + '%'})
                .orderBy("district_code")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(districtt.class)
                .execute();
    }

}
