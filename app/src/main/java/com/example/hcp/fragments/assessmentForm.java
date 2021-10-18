package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class assessmentForm extends Fragment {
    FragmentManager fragmentManager;
    EditText name,mrno,patient,cnic,temperature,pulseBPM,BPSystolic,BPDiastolic,HeightCM,WeightKG;
    String Sname,Smrno,Spatient,Scnic,Stemperature,SpulseBPM,SBPSystolic,SBPDiastolic,SHeightCM,SWeightKG,Spid,Spulse,SBP_Systolic,SBP_Diastolic;
    EditText surgery_type,MO_Notes;
    Spinner Sergery_select_year,two,bloodBank_select_year1,blood_bank,Dental_Clinic;
    Button saveAssessment;
    SwitchCompat pcr,switch1,switch2,switch3,switch4,switch5,switch6,switch7,hopital_last_two_year,switch8,switch9,switch10,switch11,switch12,switch13,switch14,switch15,switch16,switch17,switch18,switch19,switch20,switch21,switch22,switch23,switch24,switch25,switch26,switch28,switch29,switch27,switch30,switch31;
    public String PCR,Sswitch1,Sswitch2,Sswitch3,Sswitch4,Sswitch5,Sswitch6,Sswitch7,Shopital_last_two_year,Sswitch8,Sswitch9,Sswitch10,Sswitch11,Sswitch12,Sswitch13,Sswitch14,Sswitch15,Sswitch16,Sswitch17,Sswitch18,Sswitch19,Sswitch20,Sswitch21,Sswitch22,Sswitch23,Sswitch24,Sswitch25,Sswitch26,Sswitch28,Sswitch29,Sswitch27,Sswitch30,Sswitch31;
    public String rapidtesting;
    String SergeryWhen,closeContractOnTreatment,bloodTranfusionWhen,bloodBank,Dentatl_clinic;
    String text;
    TextView addvised,counsel_closed;
    LinearLayout three,five;

    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    int pid,pulse;
    Double BP_Systolic,BP_Diastolic;
    Double tempera,Height,weight;
    boolean isEidt = false;
    String patientnameedit,patientcnicedit,patientcontactNoedit,patienttype;
    int pideidt;
    Assessmentt assesmentobject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assessment_form, container, false);

        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);
        temperature = view.findViewById(R.id.temperature);
        pulseBPM = view.findViewById(R.id.pulseBPM);
        BPSystolic = view.findViewById(R.id.BPSystolic);
        BPDiastolic = view.findViewById(R.id.BPDiastolic);
        HeightCM = view.findViewById(R.id.HeightCM);
        WeightKG = view.findViewById(R.id.WeightKG);



//        HeightCM.setText(height);
//        WeightKG.setText(weight);

        surgery_type = view.findViewById(R.id.surgery_type);
        MO_Notes = view.findViewById(R.id.MO_Notes);
        Sergery_select_year = view.findViewById(R.id.sergery_select_year);
        two = view.findViewById(R.id.two);
        bloodBank_select_year1 = view.findViewById(R.id.select_year1);
        blood_bank = view.findViewById(R.id.blood_bank);
        Dental_Clinic = view.findViewById(R.id.Dental_Clinic);


        three = view.findViewById(R.id.three);
        five = view.findViewById(R.id.five);

        switch1 = view.findViewById(R.id.switch1);
        switch2 = view.findViewById(R.id.switch2);
        switch3 = view.findViewById(R.id.switch3);
        switch4 = view.findViewById(R.id.switch4);
        switch5 = view.findViewById(R.id.switch5);
        switch6 = view.findViewById(R.id.switch6);
        switch7 = view.findViewById(R.id.switch7);
        switch8 = view.findViewById(R.id.switch8);
        switch9 = view.findViewById(R.id.switch9);
        switch10 = view.findViewById(R.id.switch10);
        switch11 = view.findViewById(R.id.switch11);
        switch12 = view.findViewById(R.id.switch12);
        switch13 = view.findViewById(R.id.switch13);
        switch14 = view.findViewById(R.id.switch14);
        switch15 = view.findViewById(R.id.switch15);
        switch16 = view.findViewById(R.id.switch16);
        switch17 = view.findViewById(R.id.switch17);
        switch18 = view.findViewById(R.id.switch18);
        switch19 = view.findViewById(R.id.switch19);
        switch20 = view.findViewById(R.id.switch20);
        switch21 = view.findViewById(R.id.switch21);
        switch22 = view.findViewById(R.id.switch22);
        switch23 = view.findViewById(R.id.switch23);
        switch24 = view.findViewById(R.id.switch24);
        switch25 = view.findViewById(R.id.switch25);
        switch26 = view.findViewById(R.id.switch26);
        switch27 = view.findViewById(R.id.switch27);
        switch28 = view.findViewById(R.id.switch28);
        switch29 = view.findViewById(R.id.switch29);
        switch30 = view.findViewById(R.id.switch30);
        switch31 = view.findViewById(R.id.switch31);
        hopital_last_two_year = view.findViewById(R.id.hopital_last_two_year);
        addvised = view.findViewById(R.id.addvised);
        counsel_closed = view.findViewById(R.id.counsel_closed);
        pcr = view.findViewById(R.id.pcr);


        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");
        String temp =  String.valueOf(tempera = getArguments().getDouble("temperature"));
        pulse = getArguments().getInt("puls");
        BP_Systolic = getArguments().getDouble("BP_Systolic");
        BP_Diastolic = getArguments().getDouble("BP_Diastolic");
//        String height = String.valueOf(  Height = getArguments().getDouble("Height"));
//        String weight =  String.valueOf(getArguments().getDouble("Weight"));


        if (getArguments() != null) {
            isEidt = getArguments().getBoolean("isEdit");
            try {

                patientnameedit = getArguments().getString("PatientName");
                patientcnicedit = getArguments().getString("PatientCNIC");
                patientcontactNoedit = getArguments().getString("PatientCNIC");
                pideidt = getArguments().getInt("pidEdit");
                patienttype = getArguments().getString("patientType");

            } catch (Exception e) {


            }
        }




        if(isEidt) {
            if (patientcnicedit != null || pideidt != -1) {
                List<userdataaa> patinetforeditvital = null;
                Assessmentt assem = null;
                if (pideidt != -1) {
//                     patinetforeditvital = userdataaa.searchByCNICLeader(patientcnicedit);
//                     assem = Assessmentt.searchBycninc(patientcnicedit);
//                 } else {
//                     patinetforeditvital = userdataaa.searchByPhoneLeader(patientname);
                    assem = Assessmentt.searchBypid(pideidt);
                }

                assesmentobject = assem;

            }
        }
        if (isEidt) {
            name.setText(patientnameedit);
            cnic.setText(patientcnicedit);
            patient.setText(patienttype);
        } else {
            name.setText(PatientName);
            mrno.setText(SelectedMrNo);
            patient.setText(PatientType);
            cnic.setText(patientCNINC);
            temperature.setText(temp);
            pulseBPM.setText(pulse + "");
            BPSystolic.setText(BP_Systolic + "");
            BPDiastolic.setText(BP_Diastolic + "");
        }






        fragmentManager = getFragmentManager();

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);
        temperature.setEnabled(false);
        pulseBPM.setEnabled(false);
        BPSystolic.setEnabled(false);
        BPDiastolic.setEnabled(false);
        HeightCM.setEnabled(false);
        WeightKG.setEnabled(false);

        Setselect_year();
        setClose_Contact_is_on_Treatment();
        setselect_year1();
        setblood_bank();
        setDental_Clinic();

        Sswitch28 = "N";
        Sswitch29 = "N";

if(isEidt) {
    userdataaa mod = userdataaa.searchBypid(pideidt);
    String pcrconfirmHbv = mod.getPcr_confirmation_hbv();

    String pcrconfirmHcv = mod.getPcr_confirmation_hcv();
    if (pcrconfirmHbv.equals("y") && pcrconfirmHcv.equals("y")) {
        switch29.setChecked(true);
        switch29.setClickable(false);
        switch28.setChecked(true);
        switch28.setClickable(false);
        addvised.setText("Advised for Both");
        addvised.setVisibility(View.VISIBLE);
        pcr.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection");
        switch30.setVisibility(View.GONE);
        switch31.setVisibility(View.GONE);
        PCR = "Y";
        Sswitch31 = "N";
        Sswitch30 = "N";
        Sswitch27 = "Y";
        Sswitch28 = "Y";
        Sswitch29 = "Y";

    } else if (pcrconfirmHbv.equals("y")) {
        switch28.setChecked(true);
        switch28.setClickable(false);
        switch29.setClickable(false);
        Sswitch28 = "Y";
        Sswitch29 = "N";
        rapidtesting = "Y";
        Sswitch30 = "N";
        Sswitch31 = "N";
        PCR = "Y";
        switch30.setVisibility(View.GONE);
        switch31.setVisibility(View.GONE);
        pcr.setVisibility(View.VISIBLE);
        addvised.setText("Advised for HBV");
        addvised.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection");
    } else if (pcrconfirmHcv.equals("y")) {
        switch29.setChecked(true);
        switch29.setClickable(false);
        switch28.setClickable(false);
        Sswitch29 = "Y";
        Sswitch28 = "N";
        rapidtesting = "Y";
        PCR = "Y";
        Sswitch31 = "N";
        switch31.setVisibility(View.GONE);
        pcr.setVisibility(View.VISIBLE);
        addvised.setText("Advised for HCV");
        addvised.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection and vaccination");
    } else {
        Sswitch28 = "N";
        Sswitch29 = "N";
        rapidtesting = "N";
        PCR = "N";
        Sswitch31 = "Y";
        counsel_closed.setText("");
        switch30.setVisibility(View.VISIBLE);
        switch31.setVisibility(View.VISIBLE);
        pcr.setVisibility(View.GONE);
        addvised.setVisibility(View.GONE);
        counsel_closed.setText("Counsel and Closed");
        Sswitch29 = "N";
        PCR = "N";
    }
}else {
    userdataaa mod = userdataaa.searchBypid(pid);

    String pcrconfirmHbv = mod.getPcr_confirmation_hbv();

    String pcrconfirmHcv = mod.getPcr_confirmation_hcv();

    if (pcrconfirmHbv.equals("y") && pcrconfirmHcv.equals("y")) {
        switch29.setChecked(true);
        switch29.setClickable(false);
        switch28.setChecked(true);
        switch28.setClickable(false);
        addvised.setText("Advised for Both");
        addvised.setVisibility(View.VISIBLE);
        pcr.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection");
        switch30.setVisibility(View.GONE);
        switch31.setVisibility(View.GONE);
        PCR = "Y";
        Sswitch31 = "N";
        Sswitch30 = "N";
        Sswitch27 = "Y";
        Sswitch28 = "Y";
        Sswitch29 = "Y";

    } else if (pcrconfirmHbv.equals("y")) {
        switch28.setChecked(true);
        switch28.setClickable(false);
        switch29.setClickable(false);
        Sswitch28 = "Y";
        Sswitch29 = "N";
        rapidtesting = "Y";
        Sswitch30 = "N";
        Sswitch31 = "N";
        PCR = "Y";
        switch30.setVisibility(View.GONE);
        switch31.setVisibility(View.GONE);
        pcr.setVisibility(View.VISIBLE);
        addvised.setText("Advised for HBV");
        addvised.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection");
    } else if (pcrconfirmHcv.equals("y")) {
        switch29.setChecked(true);
        switch29.setClickable(false);
        switch28.setClickable(false);
        Sswitch29 = "Y";
        Sswitch28 = "N";
        rapidtesting = "Y";
        PCR = "Y";
        Sswitch31 = "N";
        switch31.setVisibility(View.GONE);
        pcr.setVisibility(View.VISIBLE);
        addvised.setText("Advised for HCV");
        addvised.setVisibility(View.VISIBLE);
        counsel_closed.setText("Proceed to nurse for sample collection and vaccination");
    } else {
        Sswitch28 = "N";
        Sswitch29 = "N";
        rapidtesting = "N";
        PCR = "N";
        Sswitch31 = "Y";
        counsel_closed.setText("");
        switch30.setVisibility(View.VISIBLE);
        switch31.setVisibility(View.VISIBLE);
        pcr.setVisibility(View.GONE);
        addvised.setVisibility(View.GONE);
        counsel_closed.setText("Counsel and Closed");
        Sswitch29 = "N";
        PCR = "N";
    }
}

//




//        if(pcrconfirmHcv.equals("y")){
//
//            if(pcrconfirmHbv=="y"){
//                addvised.setText("Advised for Both");
//                PCR = "Y";
//                Sswitch31 = "N";
//            }else {
//                addvised.setText("Advised for HCV");
//                PCR = "N";
//            }
//
//        }else {
//            switch29.setClickable(false);
//            Sswitch29 = "N";
//            rapidtesting = "N";
//            PCR = "N";
//            if(!pcrconfirmHbv.equals("y")){
//                Sswitch31 = "Y";
//            }
//            counsel_closed.setText("");
//            switch31.setVisibility(View.VISIBLE);
//            pcr.setVisibility(View.GONE);
//            addvised.setVisibility(View.GONE);
//            counsel_closed.setText("Counsel and Closed");
//        }
//

        switch28.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch28 = "Y";
                    rapidtesting = "Y";
                    Sswitch30 = "N";
                    Sswitch31 = "N";
                    PCR = "Y";
                    switch30.setVisibility(View.GONE);
                    switch31.setVisibility(View.GONE);
                    pcr.setVisibility(View.VISIBLE);
                    addvised.setText("Advised for HBV");
                    addvised.setVisibility(View.VISIBLE);
                    counsel_closed.setText("Proceed to nurse for sample collection");

                    if(switch29.isChecked()){
                        addvised.setText("Advised for Both");
                        counsel_closed.setText("Proceed to nurse for sample collection and vaccination");
                        PCR = "Y";
                        Sswitch31 = "N";
                    }else {
                        addvised.setText("Advised for HBV");
                        PCR = "N";
                    }
                } else {

                    Sswitch28 = "N";
                    rapidtesting = "N";
                    PCR = "N";

                    counsel_closed.setText("");
                    switch30.setVisibility(View.VISIBLE);
                    switch31.setVisibility(View.VISIBLE);
                    pcr.setVisibility(View.GONE);
                    addvised.setVisibility(View.VISIBLE);
                    counsel_closed.setText("Counsel and Closed");

                    if(!switch29.isChecked()){
                        Sswitch31 = "Y";
                        counsel_closed.setText("Counsel and Closed");
                        addvised.setText("");
                    }else if(switch29.isChecked()){
                        counsel_closed.setText("Proceed to nurse for sample collection and vaccination");
                        addvised.setText("Advised for HCV");
                    }

                }
            }
        });

        if(isEidt){
            if(assesmentobject.getIs_hbv_test()!=null){
                if(assesmentobject.getIs_hbv_test().equalsIgnoreCase("Y")){
                    switch28.setChecked(true);
                }else{
                    switch28.setChecked(false);
                }
            }else {
                switch28.setChecked(false);
            }
        }








        switch29.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Sswitch29 = "Y";
                    rapidtesting = "Y";
                    PCR = "Y";
                    Sswitch31 = "N";
                    switch31.setVisibility(View.GONE);
                    pcr.setVisibility(View.VISIBLE);
                    addvised.setText("Advised for HCV");
                    addvised.setVisibility(View.VISIBLE);
                    counsel_closed.setText("Proceed to nurse for sample collection and vaccination");
                    if(switch28.isChecked()){
                        addvised.setText("Advised for Both");
                        PCR = "Y";
                        Sswitch31 = "N";
                    }else {
                        addvised.setText("Advised for HCV");
                        PCR = "N";
                    }
                }else {

                    Sswitch29 = "N";
                    rapidtesting = "N";
                    PCR = "N";

                    counsel_closed.setText("");
                    switch31.setVisibility(View.VISIBLE);
                    pcr.setVisibility(View.GONE);
                    addvised.setVisibility(View.VISIBLE);
                    counsel_closed.setText("Counsel and Closed");
                    if(!switch28.isChecked()){
                        Sswitch31 = "Y";
                        counsel_closed.setText("Counsel and Closed");
                        addvised.setText("");
                    }else if(switch28.isChecked()){
                        addvised.setText("Advised for HBV");
                        counsel_closed.setText("Proceed to nurse for sample collection");
                    }

                }
            }
        });


        if(isEidt){
            if(assesmentobject.getIs_hcv_test()!=null){
                if(assesmentobject.getIs_hcv_test().equalsIgnoreCase("Y")){
                    switch29.setChecked(true);
                }else{
                    switch29.setChecked(false);
                }
            }else {
                switch29.setChecked(false);
            }
        }



        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                            Sswitch1 = "Y";
                } else {
                            Sswitch1 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getFrequent_therapeutic_injections()!=null){
                if(assesmentobject.getFrequent_therapeutic_injections().equalsIgnoreCase("Y")){
                    switch1.setChecked(true);
                }else{
                    switch1.setChecked(false);
                }
            }else {
                switch1.setChecked(false);
            }
        }


        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch2 = "Y";
                } else {
                    Sswitch2 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getConfirmed_case_of_stds()!=null){
                if(assesmentobject.getConfirmed_case_of_stds().equalsIgnoreCase("Y")){
                    switch2.setChecked(true);
                }else{
                    switch2.setChecked(false);
                }
            }else {
                switch2.setChecked(false);
            }
        }

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch3 = "Y";
                    three.setVisibility(View.VISIBLE);
                } else {
                    three.setVisibility(View.GONE);
                    Sswitch3 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getInvasive_medical_and_surgical_intervention()!=null){
                if(assesmentobject.getInvasive_medical_and_surgical_intervention().equalsIgnoreCase("Y")){
                    switch3.setChecked(true);
                }else{
                    switch3.setChecked(false);
                }
            }else {
                switch3.setChecked(false);
            }
        }


        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch4 = "Y";
                    two.setVisibility(View.VISIBLE);
                } else {
                    Sswitch4 = "N";
                    two.setVisibility(View.GONE);
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getClose_contact_of_a_known_case_of_hcv_hbv()!=null){
                if(assesmentobject.getClose_contact_of_a_known_case_of_hcv_hbv().equalsIgnoreCase("Y")){
                    switch4.setChecked(true);
                }else{
                    switch4.setChecked(false);
                }
            }else {
                switch4.setChecked(false);
            }
        }


        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch5 = "Y";
                    five.setVisibility(View.VISIBLE);
                } else {
                    five.setVisibility(View.GONE);
                    Sswitch5 = "N";
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getBlood_transfusion()!=null){
                if(assesmentobject.getBlood_transfusion().equalsIgnoreCase("Y")){
                    switch5.setChecked(true);
                }else{
                    switch5.setChecked(false);
                }
            }else {
                switch5.setChecked(false);
            }
        }



        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch6 = "Y";
                } else {
                    Sswitch6 = "N";
                }
            }
        });



        if(isEidt){
            if(assesmentobject.getConfirmed_hiv_positive_persons()!=null){
                if(assesmentobject.getConfirmed_hiv_positive_persons().equalsIgnoreCase("Y")){
                    switch6.setChecked(true);
                } else {
                    switch6.setChecked(false);
                }
            }else {
                switch6.setChecked(false);
            }
        }



        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch7 = "Y";
                    hopital_last_two_year.setVisibility(View.VISIBLE);
                } else {
                    Sswitch7 = "N";
                    hopital_last_two_year.setVisibility(View.GONE);
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getEver_been_hospitalized()!=null){
                if(assesmentobject.getEver_been_hospitalized().equalsIgnoreCase("Y")){
                    switch7.setChecked(true);
                } else {
                    switch7.setChecked(false);
                }
            }else {
                switch7.setChecked(false);
            }
        }



        hopital_last_two_year.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shopital_last_two_year = "Y";
                }else {
                    Shopital_last_two_year = "N";
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getHospitalization_within_last_2_years()!=null){
                if(assesmentobject.getHospitalization_within_last_2_years().equalsIgnoreCase("Y")){
                    hopital_last_two_year.setChecked(true);
                } else {
                    hopital_last_two_year.setChecked(false);
                }
            }else {
                hopital_last_two_year.setChecked(false);
            }
        }




        switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch8 = "Y";
                } else {
                    Sswitch8 = "N";
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getIndividuals_with_tattooing_ear_nose_piercing()!=null){
                if(assesmentobject.getIndividuals_with_tattooing_ear_nose_piercing().equalsIgnoreCase("Y")){
                    switch8.setChecked(true);
                } else {
                    switch8.setChecked(false);
                }
            }else {
                switch8.setChecked(false);
            }
        }



        switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch9 = "Y";
                } else {
                    Sswitch9 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getInjectable_drug_user()!=null){
                if(assesmentobject.getInjectable_drug_user().equalsIgnoreCase("Y")){
                    switch9.setChecked(true);
                } else {
                    switch9.setChecked(false);
                }
            }else {
                switch9.setChecked(false);
            }
        }



        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch10 = "Y";
                    Dental_Clinic.setVisibility(View.VISIBLE);
                } else {
                    Sswitch10 = "N";
                    Dental_Clinic.setVisibility(View.GONE);
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getDental_intervention()!=null){
                if(assesmentobject.getDental_intervention().equalsIgnoreCase("Y")){
                    switch10.setChecked(true);
                } else {
                    switch10.setChecked(false);
                }
            }else {
                switch10.setChecked(false);
            }
        }



        switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch11 = "Y";
                } else {
                    Sswitch11 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getHistory_of_multiple_sex_partners()!=null){
                if(assesmentobject.getHistory_of_multiple_sex_partners().equalsIgnoreCase("Y")){
                    switch11.setChecked(true);
                } else {
                    switch11.setChecked(false);
                }
            }else {
                switch11.setChecked(false);
            }
        }


        switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch12 = "Y";
                } else {
                    Sswitch12 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getTruck_driver_or_transgender()!=null){
                if(assesmentobject.getTruck_driver_or_transgender().equalsIgnoreCase("Y")){
                    switch12.setChecked(true);
                } else {
                    switch12.setChecked(false);
                }
            }else {
                switch12.setChecked(false);
            }
        }

        switch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch13 = "Y";
                } else {
                    Sswitch13 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getEar_nose_pirecing()!=null){
                if(assesmentobject.getEar_nose_pirecing().equalsIgnoreCase("Y")){
                    switch13.setChecked(true);
                } else {
                    switch13.setChecked(false);
                }
            }else {
                switch13.setChecked(false);
            }
        }

        switch14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch14 = "Y";
                } else {
                    Sswitch14 = "Y";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getTransgender()!=null){
                if(assesmentobject.getTransgender().equalsIgnoreCase("Y")){
                    switch14.setChecked(true);
                } else {
                    switch14.setChecked(false);
                }
            }else {
                switch14.setChecked(false);
            }
        }

        switch15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch15 = "Y";
                } else {
                    Sswitch15 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getSharing_toothbrush()!=null){
                if(assesmentobject.getSharing_toothbrush().equalsIgnoreCase("Y")){
                    switch15.setChecked(true);
                } else {
                    switch15.setChecked(false);
                }
            }else {
                switch15.setChecked(false);
            }
        }



        switch16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch16 = "Y";
                } else {
                    Sswitch16 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getSharing_hair_comb()!=null){
                if(assesmentobject.getSharing_hair_comb().equalsIgnoreCase("Y")){
                    switch16.setChecked(true);
                } else {
                    switch16.setChecked(false);
                }
            }else {
                switch16.setChecked(false);
            }
        }

        switch17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch17 = "Y";
                } else {
                    Sswitch17 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getDark_colored_urine()!=null){
                if(assesmentobject.getDark_colored_urine().equalsIgnoreCase("Y")){
                    switch17.setChecked(true);
                } else {
                    switch17.setChecked(false);
                }
            }else {
                switch17.setChecked(false);
            }
        }


        switch18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch18 = "Y";
                } else {
                    Sswitch18 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getLoss_of_appetite()!=null){
                if(assesmentobject.getLoss_of_appetite().equalsIgnoreCase("Y")){
                    switch18.setChecked(true);
                } else {
                    switch18.setChecked(false);
                }
            }else {
                switch18.setChecked(false);
            }
        }


        switch19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch19 = "Y";
                } else {
                    Sswitch19 = "Y";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getLight_colored_faeces()!=null){
                if(assesmentobject.getLight_colored_faeces().equalsIgnoreCase("Y")){
                    switch19.setChecked(true);
                } else {
                    switch19.setChecked(false);
                }
            }else {
                switch19.setChecked(false);
            }
        }


        switch20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch20 = "Y";
                } else {
                    Sswitch20 = "Y";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getFatigue()!=null){
                if(assesmentobject.getFatigue().equalsIgnoreCase("Y")){
                    switch20.setChecked(true);
                } else {
                    switch20.setChecked(false);
                }
            }else {
                switch20.setChecked(false);
            }
        }




        switch21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch21 = "Y";
                } else {
                    Sswitch21 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getMuscle_pain()!=null){
                if(assesmentobject.getMuscle_pain().equalsIgnoreCase("Y")){
                    switch21.setChecked(true);
                } else {
                    switch21.setChecked(false);
                }
            }else {
                switch21.setChecked(false);
            }
        }


        switch22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch22 = "Y";
                } else {
                    Sswitch22 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getNausea()!=null){
                if(assesmentobject.getNausea().equalsIgnoreCase("Y")){
                    switch22.setChecked(true);
                } else {
                    switch22.setChecked(false);
                }
            }else {
                switch22.setChecked(false);
            }
        }


        switch23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch23 = "Y";
                } else {
                    Sswitch23 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getStomach_ache()!=null){
                if(assesmentobject.getStomach_ache().equalsIgnoreCase("Y")){
                    switch23.setChecked(true);
                } else {
                    switch23.setChecked(false);
                }
            }else {
                switch23.setChecked(false);
            }
        }

        switch24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch24 = "Y";
                } else {
                    Sswitch24 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getRight_upper_quadrant_tenderness()!=null){
                if(assesmentobject.getRight_upper_quadrant_tenderness().equalsIgnoreCase("Y")){
                    switch24.setChecked(true);
                } else {
                    switch24.setChecked(false);
                }
            }else {
                switch24.setChecked(false);
            }
        }


        switch25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch25 = "Y";
                } else {
                    Sswitch25 = "N";
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getGastric_irritation_burning()!=null){
                if(assesmentobject.getGastric_irritation_burning().equalsIgnoreCase("Y")){
                    switch25.setChecked(true);
                } else {
                    switch25.setChecked(false);
                }
            }else {
                switch25.setChecked(false);
            }
        }



        switch26.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch26 = "Y";
                } else {
                    Sswitch26 = "N";
                }
            }
        });

        if(isEidt){
            if(assesmentobject.getUnusual_urethral_discharge()!=null){
                if(assesmentobject.getUnusual_urethral_discharge().equalsIgnoreCase("Y")){
                    switch26.setChecked(true);
                } else {
                    switch26.setChecked(false);
                }
            }else {
                switch26.setChecked(false);
            }
        }


        switch27.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sswitch27 = "Y";
                } else {
                    Sswitch27 = "N";
                }
            }
        });




        switch30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Sswitch30 = "Y";
                }else {
                    Sswitch30 = "N";
                }
            }
        });


        if(isEidt){
            if(assesmentobject.getVaccination()!=null){
                if(assesmentobject.getVaccination().equalsIgnoreCase("Y")){
                    switch30.setChecked(true);
                } else {
                    switch30.setChecked(false);
                }
            }else {
                switch30.setChecked(false);
            }
        }



        switch31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Sswitch31 = "Y";
                }else {
                    Sswitch31 = "N";
                }
            }
        });
        if(isEidt){
            if(assesmentobject.getCounselling()!=null){
                if(assesmentobject.getCounselling().equalsIgnoreCase("Y")){
                    switch31.setChecked(true);
                } else {
                    switch31.setChecked(false);
                }
            }else {
                switch31.setChecked(false);
            }
        }


        saveAssessment = view.findViewById(R.id.addAssessment);


        if (isEidt) {
            saveAssessment.setOnClickListener(
                    v -> saveDataAssessmentedit()
            );
        } else {
            saveAssessment.setOnClickListener(
                    v -> saveDataAssessment()
            );
        }

        return view;
    }






    private void saveDataAssessment() {

        Assessmentt AS = new Assessmentt();
        ActiveAndroid.beginTransaction();

        AS.IsSync = 0;
        AS.setUser_hospital(new SharedPref(getContext()).GetLoggedInUser());
        AS.setPatient_id(pid);
        AS.setUser_id(new SharedPref(getContext()).GetLoggedInRole());
        AS.setCreated(0);
        AS.setUpdated(0);
        AS.setNote(MO_Notes.getText().toString());
        AS.setFrequent_therapeutic_injections(Sswitch1);
        AS.setConfirmed_case_of_stds(Sswitch2);
        AS.setInvasive_medical_and_surgical_intervention(Sswitch3);
        AS.setSurgery_type(surgery_type.getText().toString());
        AS.setSurgery_when(SergeryWhen);
        AS.setClose_contact_of_a_known_case_of_hcv_hbv(Sswitch4);
        AS.setClose_contact_is_on_treatment(closeContractOnTreatment);
        AS.setBlood_transfusion(Sswitch5);
        AS.setBlood_transfusion_when(bloodTranfusionWhen);
        AS.setBlood_bank(bloodBank);
        AS.setConfirmed_hiv_positive_persons(Sswitch6);
        AS.setEver_been_hospitalized(Sswitch7);
        AS.setHospitalization_within_last_2_years(Shopital_last_two_year);
        AS.setIndividuals_with_tattooing_ear_nose_piercing(Sswitch8);
        AS.setInjectable_drug_user(Sswitch9);
        AS.setDental_intervention(Sswitch10);
        AS.setDental_clinic(Dentatl_clinic);
        AS.setHistory_of_multiple_sex_partners(Sswitch11);
        AS.setTruck_driver_or_transgender(Sswitch12);
//        AS.setJaundice();
//        AS.setUnexplained_fever();
        AS.setEar_nose_pirecing(Sswitch13);
        AS.setTransgender(Sswitch14);
        AS.setSharing_toothbrush(Sswitch15);
        AS.setSharing_hair_comb(Sswitch16);
        AS.setDark_colored_urine(Sswitch17);
        AS.setLoss_of_appetite(Sswitch18);
        AS.setLight_colored_faeces(Sswitch19);
        AS.setFatigue(Sswitch20);
        AS.setMuscle_pain(Sswitch21);
        AS.setNausea(Sswitch22);
        AS.setStomach_ache(Sswitch23);
        AS.setRight_upper_quadrant_tenderness(Sswitch24);
        AS.setGastric_irritation_burning(Sswitch25);
        AS.setUnusual_urethral_discharge(Sswitch26);

        AS.setRapid_testing(rapidtesting);

        if(Sswitch28.equalsIgnoreCase("N")){
            AS.setIs_hbv_test("N");
        }else {
            AS.setIs_hbv_test(Sswitch28);
             }
        if(Sswitch29.equalsIgnoreCase("N")){
            AS.setIs_hcv_test("N");
        }else {
            AS.setIs_hcv_test(Sswitch29);
        }
        AS.setVaccination(Sswitch30);
        AS.setPcr_option(addvised.getText().toString());
        AS.setPcr(PCR);
        AS.counselling = Sswitch31;
        AS.is_new_patient = "true";

            userdataaa mod = userdataaa.searchBypid(pid);
            if(Sswitch28.equalsIgnoreCase("N") && Sswitch29.equalsIgnoreCase("N")){
                mod.IS_assessment = 1;
                mod.IS_Vaccination = 0;
                mod.ISSample = 2;
            }
            if(Sswitch28.equalsIgnoreCase("N") && Sswitch29.equalsIgnoreCase("Y")) {
                mod.IS_assessment = 1;
                mod.IS_Vaccination = 0;
                mod.ISSample = 0;
            }
            if(Sswitch28.equalsIgnoreCase("Y") && Sswitch29.equalsIgnoreCase("Y")){
                mod.IS_assessment = 1;
                mod.ISSample = 0;
                mod.IS_Vaccination = 2;
            }
            if(Sswitch28.equalsIgnoreCase("Y") && Sswitch29.equalsIgnoreCase("N")){
                mod.IS_assessment = 1;
                mod.ISSample = 0;
                mod.IS_Vaccination = 2;
            }
            mod.save();

        try {
            AS.save();

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
        pDialog.setTitleText("Assessment Save Successfully");
        pDialog.setCancelable(false);
        pDialog.show();
        Fragment FMFragment = new AssessmentDashboard();
        if (FMFragment != null) {

            getActivity().onBackPressed();

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            try {
                transaction.add(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();

            } catch (IllegalStateException ignored) {

            }
        } else {
            Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveDataAssessmentedit() {

        Assessmentt AS = new Assessmentt();
        ActiveAndroid.beginTransaction();

        assesmentobject.IsSync = 0;
        assesmentobject.setUser_hospital(new SharedPref(getContext()).GetLoggedInUser());
        assesmentobject.setPatient_id(pideidt);
        assesmentobject.setUser_id(new SharedPref(getContext()).GetLoggedInRole());
        assesmentobject.setCreated(0);
        assesmentobject.setUpdated(0);
        assesmentobject.setNote(MO_Notes.getText().toString());
        assesmentobject.setFrequent_therapeutic_injections(Sswitch1);
        assesmentobject.setConfirmed_case_of_stds(Sswitch2);
        assesmentobject.setInvasive_medical_and_surgical_intervention(Sswitch3);
        assesmentobject.setSurgery_type(surgery_type.getText().toString());
        assesmentobject.setSurgery_when(SergeryWhen);
        assesmentobject.setClose_contact_of_a_known_case_of_hcv_hbv(Sswitch4);
        assesmentobject.setClose_contact_is_on_treatment(closeContractOnTreatment);
        assesmentobject.setBlood_transfusion(Sswitch5);
        assesmentobject.setBlood_transfusion_when(bloodTranfusionWhen);
        assesmentobject.setBlood_bank(bloodBank);
        assesmentobject.setConfirmed_hiv_positive_persons(Sswitch6);
        assesmentobject.setEver_been_hospitalized(Sswitch7);
        assesmentobject.setHospitalization_within_last_2_years(Shopital_last_two_year);
        assesmentobject.setIndividuals_with_tattooing_ear_nose_piercing(Sswitch8);
        assesmentobject.setInjectable_drug_user(Sswitch9);
        assesmentobject.setDental_intervention(Sswitch10);
        assesmentobject.setDental_clinic(Dentatl_clinic);
        assesmentobject.setHistory_of_multiple_sex_partners(Sswitch11);
        assesmentobject.setTruck_driver_or_transgender(Sswitch12);
//        AS.setJaundice();
//        AS.setUnexplained_fever();
        assesmentobject.setEar_nose_pirecing(Sswitch13);
        assesmentobject.setTransgender(Sswitch14);
        assesmentobject.setSharing_toothbrush(Sswitch15);
        assesmentobject.setSharing_hair_comb(Sswitch16);
        assesmentobject.setDark_colored_urine(Sswitch17);
        assesmentobject.setLoss_of_appetite(Sswitch18);
        assesmentobject.setLight_colored_faeces(Sswitch19);
        assesmentobject.setFatigue(Sswitch20);
        assesmentobject.setMuscle_pain(Sswitch21);
        assesmentobject.setNausea(Sswitch22);
        assesmentobject.setStomach_ache(Sswitch23);
        assesmentobject.setRight_upper_quadrant_tenderness(Sswitch24);
        assesmentobject.setGastric_irritation_burning(Sswitch25);
        assesmentobject.setUnusual_urethral_discharge(Sswitch26);

        assesmentobject.setRapid_testing(rapidtesting);

        if(Sswitch28.equalsIgnoreCase("N")){
            assesmentobject.setIs_hbv_test("N");
        }else {
            assesmentobject.setIs_hbv_test(Sswitch28);
        }
        if(Sswitch29.equalsIgnoreCase("N")){
            assesmentobject.setIs_hcv_test("N");
        }else {
            assesmentobject.setIs_hcv_test(Sswitch29);
        }
        assesmentobject.setVaccination(Sswitch30);
        assesmentobject.setPcr_option(addvised.getText().toString());
        assesmentobject.setPcr(PCR);
        assesmentobject.counselling = Sswitch31;
        assesmentobject.is_new_patient = "true";


            userdataaa mod = userdataaa.searchBypid(pideidt);

            if(Sswitch28.equalsIgnoreCase("N") && Sswitch29.equalsIgnoreCase("N")){
                mod.IS_assessment = 1;
                mod.IS_Vaccination = 0;
                mod.ISSample = 2;

            }
            if(Sswitch28.equalsIgnoreCase("N") && Sswitch29.equalsIgnoreCase("Y")) {
                mod.IS_assessment = 1;
                mod.IS_Vaccination = 0;
                mod.ISSample = 0;
            }
            if(Sswitch28.equalsIgnoreCase("Y") && Sswitch29.equalsIgnoreCase("Y")){
                mod.IS_assessment = 1;
                mod.ISSample = 0;
                mod.IS_Vaccination = 2;

            }
            if(Sswitch28.equalsIgnoreCase("Y") && Sswitch29.equalsIgnoreCase("N")){
                mod.IS_assessment = 1;
                mod.ISSample = 0;
                mod.IS_Vaccination = 2;
            }

            mod.save();

        try {
            assesmentobject.save();

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
        pDialog.setTitleText("Assessment Update Successfully");
        pDialog.setCancelable(false);
        pDialog.show();
//        Fragment FMFragment = new AssessmentDashboard();
//        if (FMFragment != null) {

            getActivity().onBackPressed();

//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//            try {
//                transaction.add(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();
//
//            } catch (IllegalStateException ignored) {
//
//            }
//        } else {
//            Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
//        }
    }










    private void Setselect_year(){
        List<String> categoriessurgeryyear = new ArrayList<String>();
        categoriessurgeryyear.add("Select Year*");
        categoriessurgeryyear.add("2020");
        categoriessurgeryyear.add("2019");
        categoriessurgeryyear.add("2018");
        categoriessurgeryyear.add("2017");
        categoriessurgeryyear.add("2016");
        categoriessurgeryyear.add("2015");
        categoriessurgeryyear.add("2014");
        categoriessurgeryyear.add("2013");
        categoriessurgeryyear.add("2012");
        categoriessurgeryyear.add("2011");
        categoriessurgeryyear.add("2010");
        categoriessurgeryyear.add("2009");
        categoriessurgeryyear.add("2008");
        categoriessurgeryyear.add("2007");
        categoriessurgeryyear.add("2006");
        categoriessurgeryyear.add("2005");
        categoriessurgeryyear.add("2004");
        categoriessurgeryyear.add("2003");
        categoriessurgeryyear.add("2002");
        categoriessurgeryyear.add("2001");
        categoriessurgeryyear.add("2000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriessurgeryyear);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        Sergery_select_year.setAdapter(dataAdapter);

        Sergery_select_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (Sergery_select_year.getSelectedItemPosition() > 0) {
//                    SelectedGenderIndex = gendr.getSelectedItemPosition();
                    SergeryWhen = Sergery_select_year.getSelectedItem().toString();
 //                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
//                    GenderVal = "";
                    SergeryWhen = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {
            for (int i = 0; i < categoriessurgeryyear.size(); i++) {
                String main_status = categoriessurgeryyear.get(i);
                if (!main_status.isEmpty()) {
                    String selected_status = assesmentobject.getSurgery_when();

                    if (main_status.equals(selected_status)) {
                        Sergery_select_year.setSelection(i + 1);
                        SergeryWhen = selected_status;
                    }
                }

            }
        }

    }

    private void setClose_Contact_is_on_Treatment() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Option");
        categoriesEng.add("Y");
        categoriesEng.add("N");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        two.setAdapter(dataAdapter);

        two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (two.getSelectedItemPosition() > 0) {
//                    SelectedGenderIndex = gendr.getSelectedItemPosition();
                    closeContractOnTreatment = two.getSelectedItem().toString();
 //                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
//                    GenderVal = "";
                    closeContractOnTreatment = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {

            if (assesmentobject.getClose_contact_is_on_treatment() != null) {
                if (assesmentobject.getClose_contact_is_on_treatment().equals("Y")) {
                    two.setSelection(1);
                } else if (assesmentobject.getClose_contact_is_on_treatment().equals("N")) {
                    two.setSelection(2);
                }
            } else {
                two.setSelection(0);
            }
        }


    }

    private void setselect_year1() {
        List<String> categoriesEngblood = new ArrayList<String>();
        categoriesEngblood.add("Select Year*");
        categoriesEngblood.add("2020");
        categoriesEngblood.add("2019");
        categoriesEngblood.add("2018");
        categoriesEngblood.add("2017");
        categoriesEngblood.add("2016");
        categoriesEngblood.add("2015");
        categoriesEngblood.add("2014");
        categoriesEngblood.add("2013");
        categoriesEngblood.add("2012");
        categoriesEngblood.add("2011");
        categoriesEngblood.add("2010");
        categoriesEngblood.add("2009");
        categoriesEngblood.add("2008");
        categoriesEngblood.add("2007");
        categoriesEngblood.add("2006");
        categoriesEngblood.add("2005");
        categoriesEngblood.add("2004");
        categoriesEngblood.add("2003");
        categoriesEngblood.add("2002");
        categoriesEngblood.add("2001");
        categoriesEngblood.add("2000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEngblood);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        bloodBank_select_year1.setAdapter(dataAdapter);

        bloodBank_select_year1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (bloodBank_select_year1.getSelectedItemPosition() > 0) {
//                    SelectedGenderIndex = gendr.getSelectedItemPosition();
                    bloodTranfusionWhen = bloodBank_select_year1.getSelectedItem().toString();
//                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
//                    GenderVal = "";
                    bloodTranfusionWhen = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {
            for (int i = 0; i < categoriesEngblood.size(); i++) {
                String main_status = categoriesEngblood.get(i);
                if (!main_status.isEmpty()) {
                    String selected_status = assesmentobject.getBlood_transfusion_when();

                    if (main_status.equals(selected_status)) {
                        bloodBank_select_year1.setSelection(i + 1);
                        bloodTranfusionWhen = selected_status;
                    }
                }

            }
        }



    }

    private void setblood_bank() {

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Option");
        categoriesEng.add("Private Sector");
        categoriesEng.add("Public Sector");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        blood_bank.setAdapter(dataAdapter);

        blood_bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (blood_bank.getSelectedItemPosition() > 0) {
//                    SelectedGenderIndex = gendr.getSelectedItemPosition();
                    bloodBank = blood_bank.getSelectedItem().toString();
//                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
//                    GenderVal = "";
                    bloodBank = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        if (isEidt) {

            if (assesmentobject.getBlood_bank() != null) {
                if (assesmentobject.getBlood_bank().equals("Private Sector")) {
                    blood_bank.setSelection(1);
                } else if (assesmentobject.getBlood_bank().equals("Public Sector")) {
                    blood_bank.setSelection(2);
                }
            } else {
                blood_bank.setSelection(0);
            }
        }



    }

    private void setDental_Clinic() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Option");
        categoriesEng.add("Private");
        categoriesEng.add("Public");
        categoriesEng.add("Quack");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        Dental_Clinic.setAdapter(dataAdapter);

        Dental_Clinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (Dental_Clinic.getSelectedItemPosition() > 0) {
//                    SelectedGenderIndex = gendr.getSelectedItemPosition();
                    Dentatl_clinic = Dental_Clinic.getSelectedItem().toString();
//                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
//                    GenderVal = "";
                    Dentatl_clinic = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        if (isEidt) {

            if (assesmentobject.getDental_clinic() != null) {
                if (assesmentobject.getDental_clinic().equals("Private")) {
                    blood_bank.setSelection(1);
                } else if (assesmentobject.getDental_clinic().equals("Public")) {
                    blood_bank.setSelection(2);
                }else if(assesmentobject.getDental_clinic().equals("Quack")){
                    blood_bank.setSelection(3);
                }
            } else {
                blood_bank.setSelection(0);
            }
        }



    }

}