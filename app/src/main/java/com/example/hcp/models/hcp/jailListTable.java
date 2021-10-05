package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "jail_List_Table")
public class jailListTable extends Model {


    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    private String hospital_id;

    @Column(name = "hospital_name")
    @SerializedName("hospital_name")
    @Expose
    private String hospital_name;

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }


    public static List<jailListTable> getAll() {
        return new Select()
                .from(jailListTable.class)
                .orderBy("hospital_name")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(jailListTable.class)
                .execute();
    }


}
