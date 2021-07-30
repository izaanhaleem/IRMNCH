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
    Button addvital;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assessment_form, container, false);

        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");
        String temp =  String.valueOf(tempera = getArguments().getDouble("temperature"));
        pulse = getArguments().getInt("puls");
        BP_Systolic = getArguments().getDouble("BP_Systolic");
        BP_Diastolic = getArguments().getDouble("BP_Diastolic");
        String height = String.valueOf(  Height = getArguments().getDouble("Height"));
        String weight =  String.valueOf(getArguments().getDouble("Weight"));

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

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        patient.setText(PatientType);
        cnic.setText(patientCNINC);
        temperature.setText(temp);
        pulseBPM.setText(pulse+"");
        BPSystolic.setText(BP_Systolic+"");
        BPDiastolic.setText(BP_Diastolic+"");
        HeightCM.setText(height);
        WeightKG.setText(weight);

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
                        PCR = "Y";
                        Sswitch31 = "N";
                    }
                } else {

                    Sswitch28 = "N";
                    rapidtesting = "N";
                    PCR = "N";
                    if(!switch29.isChecked()){
                        Sswitch31 = "Y";
                    }
                    counsel_closed.setText("");
                    switch30.setVisibility(View.VISIBLE);
                    switch31.setVisibility(View.VISIBLE);
                    pcr.setVisibility(View.GONE);
                    addvised.setVisibility(View.GONE);
                    counsel_closed.setText("Counsel and Closed");

                }
            }
        });

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
            }
        }else {

            Sswitch29 = "N";
            rapidtesting = "N";
            PCR = "N";
            if(!switch28.isChecked()){
                Sswitch31 = "Y";
            }
            counsel_closed.setText("");
            switch31.setVisibility(View.VISIBLE);
            pcr.setVisibility(View.GONE);
            addvised.setVisibility(View.GONE);
            counsel_closed.setText("Counsel and Closed");
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




        addvital = view.findViewById(R.id.addAssessment);

        addvital.setOnClickListener(
                v -> saveDataAssessment()
        );


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
        AS.setIs_hbv_test(Sswitch28);
        AS.setIs_hcv_test(Sswitch29);
        AS.setVaccination(Sswitch30);
        AS.setPcr_option(addvised.getText().toString());
        AS.setPcr(PCR);
        AS.counselling = Sswitch31;
        AS.is_new_patient = "true";

        addPatientModel mod = addPatientModel.searchBycnic(patientCNINC);

        mod.IS_assessment = 1;

        try {
            AS.save();
            mod.save();


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

    private void Setselect_year(){
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Year*");
        categoriesEng.add("2020");
        categoriesEng.add("2019");
        categoriesEng.add("2018");
        categoriesEng.add("2017");
        categoriesEng.add("2016");
        categoriesEng.add("2015");
        categoriesEng.add("2014");
        categoriesEng.add("2013");
        categoriesEng.add("2012");
        categoriesEng.add("2011");
        categoriesEng.add("2010");
        categoriesEng.add("2009");
        categoriesEng.add("2008");
        categoriesEng.add("2007");
        categoriesEng.add("2006");
        categoriesEng.add("2005");
        categoriesEng.add("2004");
        categoriesEng.add("2003");
        categoriesEng.add("2002");
        categoriesEng.add("2001");
        categoriesEng.add("2000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

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


    }
    private void setselect_year1() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Year*");
        categoriesEng.add("2020");
        categoriesEng.add("2019");
        categoriesEng.add("2018");
        categoriesEng.add("2017");
        categoriesEng.add("2016");
        categoriesEng.add("2015");
        categoriesEng.add("2014");
        categoriesEng.add("2013");
        categoriesEng.add("2012");
        categoriesEng.add("2011");
        categoriesEng.add("2010");
        categoriesEng.add("2009");
        categoriesEng.add("2008");
        categoriesEng.add("2007");
        categoriesEng.add("2006");
        categoriesEng.add("2005");
        categoriesEng.add("2004");
        categoriesEng.add("2003");
        categoriesEng.add("2002");
        categoriesEng.add("2001");
        categoriesEng.add("2000");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

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

    }

}