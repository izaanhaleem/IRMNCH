package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hcp.R;

import java.util.ArrayList;
import java.util.List;


public class pendingTratmentForm extends Fragment {
    FragmentManager fragmentManager;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    EditText name,mrno,patient,cnic,homoglobin,platelet,TLC,Urea,Creatinie,Blood_Sugar_Random,Pulse,Systolic,Diastolic,Weight;
    int shomoglobin,splatlets,stlc,surea,sBlood_Sugar_Random,sPulse,sSystolic,sDiastolic,sWeight;
    int pid;
    double sCreatinie;
    Spinner labname,resultType,hcvpcrResult,nativeexp,medicine_prescription;
    String selectedLabName,selectedResultType,selectedhcvpcrResult,selectednativeexp,selectedmedicine_prescription;
    int SelectedOptionIndex;
    LinearLayout renalfunctionlayout,vitalandweightlayout,treatmentanddrugLayout,medicineLayout,checkboxsLayout;
    SwitchCompat drugconfirm;
    TextView text;

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

        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);
        labname = view.findViewById(R.id.labname);
        resultType = view.findViewById(R.id.resultType);
        hcvpcrResult = view.findViewById(R.id.hcvpcrResult);
        nativeexp = view.findViewById(R.id.nativeexp);
        medicine_prescription = view.findViewById(R.id.medicine_prescription);
        homoglobin = view.findViewById(R.id.homoglobin);
        platelet = view.findViewById(R.id.platelet);
        TLC = view.findViewById(R.id.TLC);
        Urea = view.findViewById(R.id.Urea);
        Creatinie = view.findViewById(R.id.Creatinie);
        Blood_Sugar_Random = view.findViewById(R.id.Blood_Sugar_Random);
        Pulse = view.findViewById(R.id.Pulse);
        Systolic = view.findViewById(R.id.Systolic);
        Diastolic = view.findViewById(R.id.Diastolic);
        Weight = view.findViewById(R.id.Weight);
        drugconfirm = view.findViewById(R.id.drugconfirm);
        text = view.findViewById(R.id.text);


        renalfunctionlayout = view.findViewById(R.id.renalfunction);
        vitalandweightlayout = view.findViewById(R.id.vitalandweight);
        treatmentanddrugLayout = view.findViewById(R.id.treatmentanddrugLayout);
        medicineLayout = view.findViewById(R.id.medicineLayout);
        checkboxsLayout = view.findViewById(R.id.checkboxs);

        fragmentManager = getFragmentManager();

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);


        setlabname();
        setResultType();
        sethcvpcrResult();
        setnativeexp();
        setmedicine_prescription();
        renalfunctionlayout.setVisibility(View.GONE);
        renalfunctionlayout.setVisibility(View.GONE);
        treatmentanddrugLayout.setVisibility(View.GONE);
        vitalandweightlayout.setVisibility(View.GONE);
        checkboxsLayout.setVisibility(View.GONE);
        medicineLayout.setVisibility(View.GONE);


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

                    if((splatlets<901&&splatlets>99) && (stlc<21&&stlc>3))
                    {
                        renalfunctionlayout.setVisibility(View.VISIBLE);
                    }
              }
                else {
                    renalfunctionlayout.setVisibility(View.GONE);
                    }
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

                if (splatlets<901&&splatlets>99){

                    if((shomoglobin<19&&shomoglobin>5) && (stlc<21&&stlc>3)){
                        renalfunctionlayout.setVisibility(View.VISIBLE);
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

                    if((shomoglobin<19&&shomoglobin>5) && (splatlets<901&&splatlets>99)){
                        renalfunctionlayout.setVisibility(View.VISIBLE);
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
                        vitalandweightlayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    vitalandweightlayout.setVisibility(View.GONE);
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
                        vitalandweightlayout.setVisibility(View.VISIBLE);
                    }

                }else {
                    vitalandweightlayout.setVisibility(View.GONE);
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

                        vitalandweightlayout.setVisibility(View.VISIBLE);
                    }
                }else {
                    vitalandweightlayout.setVisibility(View.GONE);
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
                }else {
                    medicineLayout.setVisibility(View.GONE);
                }
            }
        });


        return view;
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
    private void setnativeexp() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Treatment");
        categoriesEng.add("Native");
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
                    }

//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    selectednativeexp = "";
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
                    SelectedOptionIndex = medicine_prescription.getSelectedItemPosition();

                    selectedmedicine_prescription = categoriesEng.get(SelectedOptionIndex);

                    if(medicine_prescription.getSelectedItemPosition()==1){
                       text.setText("Disburse 3 Monthly packs to Patient");
                    }else {
                        text.setText("Disburse 6 Monthly packs to Patient");
                    }

//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
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