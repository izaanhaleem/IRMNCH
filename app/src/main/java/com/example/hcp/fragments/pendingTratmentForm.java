package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class pendingTratmentForm extends Fragment {
    FragmentManager fragmentManager;
    String SelectedMrNo,patientCNINC,PatientName,PatientType,testtype,isCorronic_patient;
    EditText name,mrno,patient,cnic,homoglobin,platelet,TLC,Urea,Creatinie,Blood_Sugar_Random,Pulse,Systolic,Diastolic,Weight,ast,alt,APRI;
    int shomoglobin,sast,salt,splatlets,stlc,surea,sBlood_Sugar_Random,sPulse,sSystolic,sDiastolic,sWeight;
    int pid,hcvviralcount,hbvviralcount,sampleID,hcv_medicine_duration;
    double sCreatinie;
    Spinner labname,resultType,hcvpcrResult,hbvpcrResult,nativeexp,medicine_prescription;
    String selectedLabName,selectedResultType,selectedhcvpcrResult,selectedhbvpcrResult,selectednativeexp,selectedmedicine_prescription;
    int SelectedOptionIndex,SelectedOptionIndexmed;
    LinearLayout renalfunctionlayout,vitalandweightlayout,treatmentanddrugLayout,medicineLayout,checkboxsLayout,hcvlayout,hbvlayout;
    SwitchCompat drugconfirm;
    TextView text,hcvvira,hbvviral;
    Button addpendingBtn;
    public double apri_value;
    public String drug_interaction;
    CheckBox inf,sd,sr;
    public String formatted;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pending_tratment_form, container, false);

        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");
        testtype = getArguments().getString("testType");
        hcvviralcount = getArguments().getInt("hcvviralcount");
        hbvviralcount = getArguments().getInt("hbvviralcount");
        sampleID = getArguments().getInt("sample_id");
        isCorronic_patient = getArguments().getString("iscorronic_patient");
        hcv_medicine_duration = getArguments().getInt("hcv_medicine_duration");


        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);
        labname = view.findViewById(R.id.labname);
        resultType = view.findViewById(R.id.resultType);
        hcvpcrResult = view.findViewById(R.id.hcvpcrResult);
        hbvpcrResult = view.findViewById(R.id.hbvpcrResult);
        nativeexp = view.findViewById(R.id.nativeexp);
        medicine_prescription = view.findViewById(R.id.medicine_prescription);
        homoglobin = view.findViewById(R.id.homoglobin);
        ast = view.findViewById(R.id.ast);
        alt = view.findViewById(R.id.alt);
        platelet = view.findViewById(R.id.platelet);
        TLC = view.findViewById(R.id.TLC);
        APRI = view.findViewById(R.id.APRI);
        Urea = view.findViewById(R.id.Urea);
        Creatinie = view.findViewById(R.id.Creatinie);
        Blood_Sugar_Random = view.findViewById(R.id.Blood_Sugar_Random);
        Pulse = view.findViewById(R.id.Pulse);
        Systolic = view.findViewById(R.id.Systolic);
        Diastolic = view.findViewById(R.id.Diastolic);
        Weight = view.findViewById(R.id.Weight);
        drugconfirm = view.findViewById(R.id.drugconfirm);
        text = view.findViewById(R.id.text);
        hcvvira = view.findViewById(R.id.hcvvira);
        hbvviral = view.findViewById(R.id.hbvviral);
        addpendingBtn = view.findViewById(R.id.addpendingBtn);
        inf = view.findViewById(R.id.inf);
        sd = view.findViewById(R.id.sd);
        sr = view.findViewById(R.id.sr);




        addpendingBtn.setOnClickListener(
                v -> FormValidation()
        );


        renalfunctionlayout = view.findViewById(R.id.renalfunction);
        vitalandweightlayout = view.findViewById(R.id.vitalandweight);
        treatmentanddrugLayout = view.findViewById(R.id.treatmentanddrugLayout);
        medicineLayout = view.findViewById(R.id.medicineLayout);
        checkboxsLayout = view.findViewById(R.id.checkboxs);
        hcvlayout = view.findViewById(R.id.hcvlayout);
        hbvlayout = view.findViewById(R.id.hbvlayout);

        fragmentManager = getFragmentManager();

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);
        hcvvira.setText(hcvviralcount+"");
        hbvviral.setText(hbvviralcount+"");

        if(testtype.equalsIgnoreCase("HCV")){
            hcvlayout.setVisibility(View.VISIBLE);
            hbvlayout.setVisibility(View.GONE);
        }else if(testtype.equalsIgnoreCase("HBV")){
            hcvlayout.setVisibility(View.GONE);
            hbvlayout.setVisibility(View.VISIBLE);
        }else {
            hbvlayout.setVisibility(View.VISIBLE);
            hcvlayout.setVisibility(View.VISIBLE);
        }


        setlabname();
        setResultType();
        sethcvpcrResult();
        sethbvpcrResult();
        setnativeexp();
        setmedicine_prescription();
        renalfunctionlayout.setVisibility(View.GONE);
        renalfunctionlayout.setVisibility(View.GONE);
        treatmentanddrugLayout.setVisibility(View.GONE);
        vitalandweightlayout.setVisibility(View.GONE);
        checkboxsLayout.setVisibility(View.GONE);
        medicineLayout.setVisibility(View.GONE);
        addpendingBtn.setVisibility(View.GONE);
        APRI.setEnabled(false);
        homoglobin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   shomoglobin = Integer.parseInt(homoglobin.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (shomoglobin<19&&shomoglobin>5){

                    if((splatlets<901&&splatlets>99) && (stlc<21&&stlc>3)) {
                        if (0.0 < apri_value && apri_value < 1000.0) {
                            renalfunctionlayout.setVisibility(View.VISIBLE);
                        }
                    }
              }
                else {
                    renalfunctionlayout.setVisibility(View.GONE);
                    }
            }
        });

    ast.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {   sast = Integer.parseInt(ast.getText().toString()); }
        catch (NumberFormatException e)
        {     e.printStackTrace(); }
    }

    @Override
    public void afterTextChanged(Editable s) {

//        Float litersOfPetrol=Float.parseFloat(String.valueOf(apri_value));
//        DecimalFormat df = new DecimalFormat("0.00");
//        df.setMaximumFractionDigits(2);
//        stringLitersOfPetrol = df.format(litersOfPetrol);



//        if(0.0<apri_value && apri_value<1000.0){
//            renalfunctionlayout.setVisibility(View.VISIBLE);
            if (shomoglobin<19&&shomoglobin>5){
                if((splatlets<901&&splatlets>99) && (stlc<21&&stlc>3))
                {
                    renalfunctionlayout.setVisibility(View.VISIBLE);
                }
            }
//        }
        else {
            renalfunctionlayout.setVisibility(View.GONE);
        }

//        else {
//            renalfunctionlayout.setVisibility(View.GONE);
//        }
    }
});

alt.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {   salt = Integer.parseInt(alt.getText().toString()); }
        catch (NumberFormatException e)
        {     e.printStackTrace(); }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});



        platelet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   splatlets = Integer.parseInt(platelet.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {

                apri_value =    ((sast /35.0) / splatlets)*100.0 ;
                DecimalFormat df = new DecimalFormat("#.####");
                formatted = df.format(apri_value);

                APRI.setText(formatted);
                APRI.setEnabled(false);

                if (splatlets<901&&splatlets>99){

                    if((shomoglobin<19&&shomoglobin>5) && (stlc<21&&stlc>3)) {
                        if (0.0 < apri_value && apri_value < 1000.0) {
                            renalfunctionlayout.setVisibility(View.VISIBLE);
                        }
                    }
                }else {
                    renalfunctionlayout.setVisibility(View.GONE);
                }
            }
        });
        TLC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   stlc = Integer.parseInt(TLC.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (stlc<21&&stlc>3){

                    if((shomoglobin<19&&shomoglobin>5) && (splatlets<901&&splatlets>99)) {
                        if (0.0 < apri_value && apri_value < 1000.0) {
                            renalfunctionlayout.setVisibility(View.VISIBLE);
                        }
                    }
                }else {
                    renalfunctionlayout.setVisibility(View.GONE);
                }


            }
        });




        Urea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   surea = Integer.parseInt(Urea.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (surea<51&&surea>9){

                    if((sCreatinie<1.3&&sCreatinie>0.4)&& (sBlood_Sugar_Random<251&&sBlood_Sugar_Random>69))
                    {
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Creatinie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sCreatinie = Double.parseDouble(Creatinie.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sCreatinie<1.3&&sCreatinie>0.4){
                    if((surea<51&&surea>9)&&(sBlood_Sugar_Random<251&&sBlood_Sugar_Random>69))
                    {
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Blood_Sugar_Random.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sBlood_Sugar_Random = Integer.parseInt(Blood_Sugar_Random.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sBlood_Sugar_Random<251&&sBlood_Sugar_Random>69){
                    if((sCreatinie<1.3&&sCreatinie>0.4)&&(surea<51&&surea>9)){

                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }
                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Pulse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sPulse = Integer.parseInt(Pulse.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sPulse<151&&sPulse>59){
                    if((sSystolic<161&&sSystolic>99)&&(sDiastolic<111&&sDiastolic>69)&&(sWeight<151&&sWeight>39)){
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }
                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Systolic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sSystolic = Integer.parseInt(Systolic.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sSystolic<161&&sSystolic>99){
                    if((sPulse<151&&sPulse>59)&&(sDiastolic<111&&sDiastolic>69)&&(sWeight<151&&sWeight>39)){
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Diastolic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sDiastolic = Integer.parseInt(Diastolic.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sDiastolic<111&&sDiastolic>69){

                    if((sPulse<151&&sPulse>59)&&(sSystolic<161&&sSystolic>99)&&(sWeight<151&&sWeight>39)){
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });
        Weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {   sWeight = Integer.parseInt(Weight.getText().toString()); }
                catch (NumberFormatException e)
                {     e.printStackTrace(); }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sWeight<151&&sWeight>39){
                    if((sPulse<151&&sPulse>59)&&(sSystolic<161&&sSystolic>99)&&(sDiastolic<111&&sDiastolic>69)){
                        treatmentanddrugLayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    treatmentanddrugLayout.setVisibility(View.GONE);
                }
            }
        });

        drugconfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    medicineLayout.setVisibility(View.VISIBLE);
                     drug_interaction = "Y";

                }else {
                    medicineLayout.setVisibility(View.GONE);
                    drug_interaction = "N";
                }
            }
        });

        return view;

    }

    private void FormValidation() {
        boolean Validationstatus = true;
//        if (sno>endRange) {
//            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
//            Validationstatus = false;
//        }else if(samplenoo.getText().length()!=6){
//            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
//            Validationstatus = false;
//        }

      if(Validationstatus){
//          Date date = Calendar.getInstance().getTime();
//          String matdate = date.getDate() + "-" + date.getMonth() + "-" + date.getYear();


              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
              LocalDateTime now = LocalDateTime.now();
              String matdate = dtf.format(now);


          int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInRole());
          int h=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());

          medicinee Mad = new medicinee();
          ActiveAndroid.beginTransaction();

//          int value = Integer.parseInt(formatted);
          double a = 0.0000;
          double b = 1000.0000;

          if(apri_value>=0.0000 && apri_value<=1000.0000){
              Mad.invs_status = "" ;
          }else if(apri_value<0.0000){
              Mad.invs_status = "defered" ;
          }else if(apri_value>1000.0000){
              Mad.invs_status = "cirrhotic_pat" ;
          }else {
              Mad.invs_status = "";
          }

          Mad.result_type =  selectedResultType;
          Mad.hemoglobin = shomoglobin+"" ;
          Mad.ast = sast+"" ;
          Mad.alt = salt+"" ;
          Mad.platelet = splatlets+"" ;
          Mad.tlc =  stlc+"";
          Mad.apri = formatted ;

          Mad.viral_count = hcvviralcount+"" ;

          Mad.pcr_result = selectedhcvpcrResult ;
          Mad.sample_id = sampleID+"" ;
          Mad.lab_name = selectedLabName ;
          Mad.other_lab_name = "" ;
          Mad.id = pid+"" ;

          Mad.baseline_type = "HCV" ;

          Mad.urea =  surea+"";
          Mad.creatinie = sCreatinie+"" ;
          Mad.blood_sugar_random = sBlood_Sugar_Random+"" ;
          Mad.drug_interaction =  drug_interaction;

          if(SelectedOptionIndexmed==1){
              Mad.hcv_medicine_recommended = "sd_pack" ;
              Mad.disburse_3_mnth_dose = "Y" ;
              Mad.disburse_6_mnth_dose  = "N";

          }else if(SelectedOptionIndexmed == 2){
              Mad.hcv_medicine_recommended = "sr_pack" ;
              Mad.disburse_3_mnth_dose = "N" ;
              Mad.disburse_6_mnth_dose  = "Y";
          }else if(SelectedOptionIndexmed == 3){
              Mad.hcv_medicine_recommended = "sdr" ;
              Mad.disburse_3_mnth_dose = "N" ;
              Mad.disburse_6_mnth_dose  = "Y";
          }else {
              Mad.hcv_medicine_recommended = "" ;
              Mad.disburse_3_mnth_dose = "" ;
          }

          Mad.cirrhotic_medicine_flow = isCorronic_patient ;
          Mad.created = matdate;
          Mad.user_id =  i;
          Mad.hospital_id = h ;
          Mad.updated = matdate ;
          Mad.mobile_id = getId() ;

          if(hcv_medicine_duration == 0){
              if(SelectedOptionIndexmed == 1){
                  Mad.hcv_medicine_duration=3;
              }else {
                  Mad.hcv_medicine_duration=6;
              }
          }else {
              Mad.hcv_medicine_duration = hcv_medicine_duration ;
          }

          Mad.treatment_history = selectednativeexp ;

          String ch = "";
          if(inf.isChecked()){
              ch = ch +" INF" ;
          } if(sd.isChecked()){
              ch = ch+" SD" ;
          } if(sr.isChecked()){
              ch = ch+" SR" ;
          }
              Mad.treatment_options = ch;

              Mad.IsSync = 0;

          try {
              Mad.save();

              ActiveAndroid.setTransactionSuccessful();
          } finally {
              ActiveAndroid.endTransaction();
          }
          final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
          pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
          pDialog.setTitleText("Sample Save Successfully");
          pDialog.setCancelable(false);
          pDialog.show();


          Fragment FMFragment = new DashboardFragment();
          Bundle args = new Bundle();
//            args.putString("SelectedMrNo", mMRNO);
//            args.putInt("FamilyId", family_id);
          if (FMFragment != null) {

              getActivity().onBackPressed();

              FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

              FMFragment.setArguments(args);
              try {
                  transaction.add(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();

              } catch (IllegalStateException ignored) {

              }
          } else {
              Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
          }

      }


    }

    private void setlabname() {

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Lab");
        categoriesEng.add("HCP");
        categoriesEng.add("PKLI");
        categoriesEng.add("Specialized");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        labname.setAdapter(dataAdapter);

        labname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (labname.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = labname.getSelectedItemPosition();

                    selectedLabName = categoriesEng.get(SelectedOptionIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectedLabName = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    private void setResultType() {


        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Result Type");
        categoriesEng.add("Quantitative");
        categoriesEng.add("Qualitative");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        resultType.setAdapter(dataAdapter);

        resultType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (resultType.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = resultType.getSelectedItemPosition();

                    selectedResultType = categoriesEng.get(SelectedOptionIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectedResultType = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    private void sethcvpcrResult() {

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Result Type");
        categoriesEng.add("Detected");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        hcvpcrResult.setAdapter(dataAdapter);

        hcvpcrResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hcvpcrResult.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = hcvpcrResult.getSelectedItemPosition();

                    selectedhcvpcrResult = categoriesEng.get(SelectedOptionIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectedhcvpcrResult = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    private void sethbvpcrResult() {

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Result Type");
        categoriesEng.add("Detected");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        hbvpcrResult.setAdapter(dataAdapter);

        hbvpcrResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hbvpcrResult.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = hbvpcrResult.getSelectedItemPosition();

                    selectedhbvpcrResult = categoriesEng.get(SelectedOptionIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectedhbvpcrResult = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    private void setnativeexp() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Treatment");
        categoriesEng.add("Naive");
        categoriesEng.add("Experienced");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        nativeexp.setAdapter(dataAdapter);

        nativeexp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (nativeexp.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = nativeexp.getSelectedItemPosition();

                    selectednativeexp = categoriesEng.get(SelectedOptionIndex);

                    if(nativeexp.getSelectedItemPosition()==2){
                        checkboxsLayout.setVisibility(View.VISIBLE);
                    }else {
                        checkboxsLayout.setVisibility(View.GONE);
                    }

//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectednativeexp = "";
                    checkboxsLayout.setVisibility(View.GONE);
                    checkboxsLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    private void setmedicine_prescription() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Medicine");
        categoriesEng.add("Single Regimen (Sofosbuvir + Daclatisvir) for 3 Months");
        categoriesEng.add("Single Regimen (Sofosbuvir + Daclatisvir) for 6 Months");
        categoriesEng.add("SR Pack (Sofosbuvir + Ribavirin) for 6 Months");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        medicine_prescription.setAdapter(dataAdapter);

        medicine_prescription.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (medicine_prescription.getSelectedItemPosition() > 0) {
                    addpendingBtn.setVisibility(View.VISIBLE);
                    SelectedOptionIndexmed = medicine_prescription.getSelectedItemPosition();

                    selectedmedicine_prescription = categoriesEng.get(SelectedOptionIndexmed);

                    if(medicine_prescription.getSelectedItemPosition()==1){
                       text.setText("Disburse 3 Monthly packs to Patient");
                    }else {
                        text.setText("Disburse 6 Monthly packs to Patient");
                    }

//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    addpendingBtn.setVisibility(View.GONE);
                    selectedmedicine_prescription = "";
                    text.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

}

