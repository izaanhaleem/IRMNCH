package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "Vaccination_Table",id = "ide")
public class Vaccinationn extends Model {

    @Column(name = "id")
    @SerializedName("id")
    @Expose
    public Integer id;

    @Column(name = "stage")
    @SerializedName("stage")
    @Expose
    public Integer stage;

    @Column(name = "dose_date")
    @SerializedName("dose_date")
    @Expose
    public String dose_date;

    @Column(name = "created")
    @SerializedName("created")
    @Expose
    public String created;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public Integer user_id;

    @Column(name = "hospital_id")
    @SerializedName("hospital_id")
    @Expose
    public Integer hospital_id;

    @Column(name = "updated")
    @SerializedName("updated")
    @Expose
    public String updated;

    @Column(name = "mobile_id")
    @SerializedName("mobile_id")
    @Expose
    public Integer mobile_id;

    @Column(name = "IsSync")
    @SerializedName("IsSync")
    @Expose
    public Integer IsSync;


    public static void deleteAll() {
        new Delete().from(Vaccinationn.class)
                .execute();
    }
    public static Vaccinationn searchBypid(Integer id) {
        return new Select()
                .from(Vaccinationn.class)
                .where("id = ?",id)
                .executeSingle();
    }
    public static List<Vaccinationn> searchBySync() {
        return new Select()
                .from(Vaccinationn.class)
                .where("IsSync = ?",0)
                .execute();
    }
}
