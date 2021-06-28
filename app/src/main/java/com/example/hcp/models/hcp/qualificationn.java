package com.example.hcp.models.hcp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "QualilficationTable")
public class qualificationn extends Model {
    @Column(name = "_id",unique = true)
    @SerializedName("id")
    @Expose
    public String id;
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    public String name;

    public static List<qualificationn> getAll() {
        return new Select()
                .from(qualificationn.class)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(qualificationn.class)
                .execute();
    }

}
