package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Table(name = "Divisiontable")
public class divisionn extends Model {
    @Column(name = "division_code",unique = true)
    @SerializedName("division_code")
    @Expose
    public String division_code;
    @Column(name = "division_name")
    @SerializedName("division_name")
    @Expose
    public String division_name;

    public String getDivision_code() {
        return division_code;
    }

    public void setDivision_code(String division_code) {
        this.division_code = division_code;
    }

    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }
    public static List<divisionn> getAll() {
        return new Select()
                .from(divisionn.class)
                .orderBy("division_name")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(divisionn.class)
                .execute();
    }
}



