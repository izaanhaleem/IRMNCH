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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.districtt;
import com.example.hcp.models.hcp.divisionn;
import com.example.hcp.models.hcp.healthFacilityy;
import com.example.hcp.models.hcp.jailListTable;
import com.example.hcp.models.hcp.savejail;
import com.example.hcp.models.hcp.tehsill;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.pixplicity.easyprefs.library.Prefs;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class transfer_jail extends Fragment {

    FragmentManager fragmentManager;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    EditText name,mrno,patient,cnic,hf;
    Spinner hftype,jailtojail,division, district, tehsil, hft;
    MaskEditText sampleno;
    EditText samp;
    MaskEditText samplenoo;
    int selectedhftypeindex,SelectedDivisionCode, SelectedDistrictedCode,selectedajailtojailindex,SelectTcode;
    Button transferjail;
    SwitchCompat samplecolect;
    public int  sno,year,patient_id;
    String HfValue,jailtohfValue,jailtojailtype,Selectedjailcode,Selectedjailname,SelectedHfname,SelectedHfcode;
    LinearLayout selecthealthfacality,jaillayout,healtyfacilitylayout;

    public String hospid  = "";
    public String hospname  = "";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_jail, container, false);
        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        patient_id = getArguments().getInt("pid");
        String healthfacility = Prefs.getString(Constants.USERNAME, "");
        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        cnic = view.findViewById(R.id.cnic);
        hf = view.findViewById(R.id.hf);
        hftype = view.findViewById(R.id.hftype);

        division = view.findViewById(R.id.division);
        district = view.findViewById(R.id.district);
        tehsil = view.findViewById(R.id.tehsil);
        hft = view.findViewById(R.id.hft);


        jailtojail = view.findViewById(R.id.jail);
        transferjail = view.findViewById(R.id.transferjail);


        jaillayout = view.findViewById(R.id.jaillayout);
        healtyfacilitylayout = view.findViewById(R.id.healtyfacilitylayout);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        hf.setText(healthfacility);

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        hf.setEnabled(false);

        SetSpinnerhftype();
        SetDivisions();
        SetSpinnerjailtojail();

        jaillayout.setVisibility(View.GONE);
        healtyfacilitylayout.setVisibility(View.GONE);


        transferjail.setOnClickListener(
                v -> FormValidation()
        );



        return view;
    }

    private void FormValidation() {

        boolean Validationstatus = true;







        if(selectedhftypeindex==0){
            Toast.makeText(getContext(), "Plz Select Health Facility Type", Toast.LENGTH_SHORT).show();

            Validationstatus = false;
        }

        if(selectedhftypeindex==1) {


            List<userdataaa> mod = userdataaa.searchByPatientIdtransferout(patient_id);

            if(mod.size()>0){
                Toast.makeText(getContext(), "This Patient Not Transfer to Health Facility", Toast.LENGTH_SHORT).show();
                Validationstatus = false;
            }



            if (SelectedDivisionCode == 0) {
                Toast.makeText(getContext(), Constants.division, Toast.LENGTH_SHORT).show();

                Validationstatus = false;
            }
            if (SelectedDistrictedCode == 0) {
                Toast.makeText(getContext(), Constants.district, Toast.LENGTH_SHORT).show();

                Validationstatus = false;
            }
            if (SelectTcode == 0) {
                Toast.makeText(getContext(), Constants.tehsil, Toast.LENGTH_SHORT).show();

                Validationstatus = false;
            }
            if (SelectedHfcode == "") {
                Toast.makeText(getContext(), Constants.healthfacility, Toast.LENGTH_SHORT).show();

                Validationstatus = false;
            }
        }else if(selectedhftypeindex==2){
            if (jailtojail.getSelectedItemPosition()==0) {
                Toast.makeText(getContext(), "Plz Select Jail", Toast.LENGTH_SHORT).show();

                Validationstatus = false;
            }
        }


        if(Validationstatus){

            savejail jai = new savejail();
            ActiveAndroid.beginTransaction();
            String hospitalname=new SharedPref(getContext()).GetHospitalName();
            jai.patient_id = patient_id;
            jai.prison_transfer_status=HfValue;
            jai.current_hospital_name = hospitalname;


            if(Selectedjailcode==null){
                jai.new_hospital_id = Integer.parseInt(SelectedHfcode);
            }
            else if(SelectedHfcode==null){
                jai.new_hospital_id = Integer.parseInt(Selectedjailcode);
            }
            else {
                jai.new_hospital_id = Integer.parseInt("");
            }
//            jai.new_hospital_id = Integer.parseInt(Selectedjailcode);

            if(Selectedjailname==null){
                jai.new_hospital_name = SelectedHfname;
            }else if(SelectedHfname==null){
                jai.new_hospital_name = Selectedjailname;
            }else {
                jai.new_hospital_name = "";
            }

//            jai.new_hospital_name = Selectedjailname;

            jai.IsSync = 0;

            userdataaa mod = userdataaa.searchByPatientId(patient_id);

            mod.ISTransfer = 0;

            try {
                jai.save();
                mod.save();

                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
            pDialog.setTitleText("Patient Transfer Successfully");
            pDialog.setCancelable(false);
            pDialog.show();
            Fragment FMFragment = new Dashboard_transfer_jail_to_jail();
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

    private void SetSpinnerhftype() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Jail to Health Facility");
        yesno.add("Transfer To Jail");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner

        hftype.setAdapter(dataAdapter);
        hftype.setSelection(0);
        hftype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hftype.getSelectedItemPosition() > 0) {
                    selectedhftypeindex = hftype.getSelectedItemPosition();

                    HfValue = yesno.get(selectedhftypeindex);


                    if(selectedhftypeindex==1){
                        healtyfacilitylayout.setVisibility(View.VISIBLE);
                        jaillayout.setVisibility(View.GONE);

                    }else if(selectedhftypeindex==2){
                        jaillayout.setVisibility(View.VISIBLE);
                        healtyfacilitylayout.setVisibility(View.GONE);
                    }else {
                        healtyfacilitylayout.setVisibility(View.GONE);
                        jaillayout.setVisibility(View.GONE);
                    }
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    HfValue = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




    }

    private void SetDivisions() {

        List<divisionn> DV = divisionn.getAll();

        district.setVisibility(View.GONE);
        tehsil.setVisibility(View.GONE);
        hft.setVisibility(View.GONE);

        String[] spinnerArray = new String[DV.size() + 1];

        spinnerArray[0] = "Division*";


        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = DV.get(i - 1).division_name;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        division.setAdapter(adapter);

        division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (division.getSelectedItemPosition() > 0) {
                    district.setVisibility(View.VISIBLE);
                    String Tname = division.getSelectedItem().toString();
//                    int TCode = districtslocal.get(districts.getSelectedItemPosition() - 1).getId();
                    int TCode = Integer.parseInt(DV.get(division.getSelectedItemPosition() - 1).division_code);
                    SelectedDivisionCode = TCode;
//                    GetTehsils(TCode);
                    SetDistrict(DV.get(division.getSelectedItemPosition() - 1).division_code);

                } else {
                    SelectedDivisionCode = 0;
                    district.setVisibility(View.GONE);
                    tehsil.setVisibility(View.GONE);
                    hft.setVisibility(View.GONE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    private void SetDistrict(String division_code) {

        tehsil.setVisibility(View.GONE);
        hft.setVisibility(View.GONE);

        List<districtt> districte = districtt.getDistrictById(division_code);
        String[] tspinnerArray = new String[districte.size() + 1];

        tspinnerArray[0] = "District*";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = districte.get(i - 1).district_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        district.setAdapter(adapter);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (district.getSelectedItemPosition() > 0) {

                    String DCode = districte.get(district.getSelectedItemPosition() - 1).district_code;
                    SelectedDistrictedCode = Integer.parseInt(DCode);


                    tehsil.setVisibility(View.VISIBLE);
                    SetTehsil(DCode);
                } else {
//                    SelectedTehsilCode = 0;
                    SelectedDistrictedCode = 0;
                    tehsil.setVisibility(View.GONE);
                    hft.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }

    private void SetTehsil(String dCode) {

        hft.setVisibility(View.GONE);
        List<tehsill> tehsili = tehsill.getTahsilById(dCode);
        String[] tspinnerArray = new String[tehsili.size() + 1];

        tspinnerArray[0] = "Tehsil *(تحصیل)";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = tehsili.get(i - 1).tehsil_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tehsil.setAdapter(adapter);

        tehsil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (tehsil.getSelectedItemPosition() > 0) {

                    String TCode = tehsili.get(tehsil.getSelectedItemPosition() - 1).tehsil_code;
                    SelectTcode = Integer.parseInt(TCode);


                    hft.setVisibility(View.VISIBLE);
                    SetHF(TCode);
                } else {
//                    SelectedTehsilCode = 0;
                    SelectTcode = 0;
                    hft.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }

    private void SetHF(String tCode) {


        List<healthFacilityy> hfi = healthFacilityy.searchById(tCode);
        String[] tspinnerArray = new String[hfi.size() + 1];

        tspinnerArray[0] = "Health Facility*";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = hfi.get(i - 1).hf_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hft.setAdapter(adapter);

        hft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hft.getSelectedItemPosition() > 0) {

                    String hfCode = hfi.get(hft.getSelectedItemPosition() - 1).hf_code;
                    String hfname = hfi.get(hft.getSelectedItemPosition()-1).hf_name;




                    SelectedHfcode = hfCode;
                    SelectedHfname = hfname;


//                    hf.setVisibility(View.VISIBLE);


                } else {
//                    SelectedTehsilCode = 0;
                    SelectedHfcode = "";
                    SelectedHfname = "";
//                    hf.setVisibility(View.GONE);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    private void SetSpinnerjailtojail() {
        List<jailListTable> jaillist = jailListTable.getAll();
        String[] spinnerArray = new String[jaillist.size() + 1];

        spinnerArray[0] = "Select Jail*";


        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = jaillist.get(i - 1).getHospital_name();
        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner

        jailtojail.setAdapter(dataAdapter);
        jailtojail.setSelection(0);
        jailtojail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {//
                if (jailtojail.getSelectedItemPosition() > 0) {
//                    selectedajailtojailindex = jailtojail.getSelectedItemPosition();
                    String jailcode = jaillist.get(jailtojail.getSelectedItemPosition() - 1).getHospital_id();
                    String  jailname = jaillist.get(jailtojail.getSelectedItemPosition() -1).getHospital_name();
                    Selectedjailcode = jailcode;
                    Selectedjailname = jailname;
//                    jailtojailtype = spinnerArray.get(selectedajailtojailindex);
////                    OptionValue.setText("");
//                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Selectedjailcode = "";
                    Selectedjailname = "";
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

}