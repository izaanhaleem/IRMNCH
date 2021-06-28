package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Table(name = "TehsilsTable")
public class tehsill extends Model {
    @Column(name = "tehsil_code",unique = true)
    @SerializedName("tehsil_code")
    @Expose
    public String tehsil_code;

    @Column(name = "tehsil_name")
    @SerializedName("tehsil_name")
    @Expose
    public String tehsil_name;

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }

    public String getTehsil_name() {
        return tehsil_name;
    }

    public void setTehsil_name(String tehsil_name) {
        this.tehsil_name = tehsil_name;
    }

    public static List<tehsill> getAll() {
        return new Select()
                .from(tehsill.class)
                .orderBy("tehsil_name")
                .execute();
    }

    public static List<tehsill> getTahsilById(String districtId) {
        return new Select()
                .from(tehsill.class)
                .where("tehsil_code LIKE ?", new String[]{'%' + districtId + '%'})
                .orderBy("tehsil_name")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(tehsill.class)
                .execute();
    }
}
