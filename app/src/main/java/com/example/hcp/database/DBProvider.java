package com.example.hcp.database;

import com.activeandroid.Configuration;
import com.activeandroid.content.ContentProvider;

import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.MedicineDisbursment_Table;
import com.example.hcp.models.hcp.ReleaseLocalTable;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.districtt;
import com.example.hcp.models.hcp.divisionn;
import com.example.hcp.models.hcp.healthFacilityy;
import com.example.hcp.models.hcp.jailListTable;
import com.example.hcp.models.hcp.materialStatuss;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.occuptaionn;
import com.example.hcp.models.hcp.qualificationn;
import com.example.hcp.models.hcp.sample_status_Table;
import com.example.hcp.models.hcp.savejail;
import com.example.hcp.models.hcp.tehsill;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.models.hcp.vitalListt;

public class DBProvider extends ContentProvider {


    @Override
    protected Configuration getConfiguration() {
        Configuration.Builder builder = new Configuration.Builder(getContext());

        builder.addModelClass(divisionn.class);
        builder.addModelClass(districtt.class);
        builder.addModelClass(districtt.class);
        builder.addModelClass(tehsill.class);
        builder.addModelClass(occuptaionn.class);
        builder.addModelClass(materialStatuss.class);
        builder.addModelClass(qualificationn.class);
        builder.addModelClass(healthFacilityy.class);
        builder.addModelClass(userdataaa.class);
        builder.addModelClass(addPatientModel.class);
        builder.addModelClass(vitalListt.class);
        builder.addModelClass(addvitalll.class);
        builder.addModelClass(Assessmentt.class);
        builder.addModelClass(Samplee.class);
        builder.addModelClass(medicinee.class);
        builder.addModelClass(Vaccinationn.class);
        builder.addModelClass(MedicineDisbursment_Table.class);
        builder.addModelClass(sample_status_Table.class);
        builder.addModelClass(jailListTable.class);
        builder.addModelClass(savejail.class);
        builder.addModelClass(ReleaseLocalTable.class);


        return builder.create();
    }


}