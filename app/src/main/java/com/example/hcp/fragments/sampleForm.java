package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.AdaptersData.SearchResultData;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.InputFilterMinMax;
import com.example.hcp.utils.SharedPref;
import com.santalu.maskara.widget.MaskEditText;

import java.util.List;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class sampleForm extends Fragment {

    FragmentManager fragmentManager;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    EditText name,mrno,patient,cnic;
    MaskEditText sampleno;
    EditText samp;
    MaskEditText samplenoo;
    int pid,pideidt;
    Button addsample;
    SwitchCompat samplecolect;
    public int  sno,year;
    boolean isEidtresample = false;
    boolean isEidt = false;
    TextView samplenoedit;
    LinearLayout presamplelayout;

    String patientname,patientcnic,patientcontactNo,fingerprint,patienttype,previousSmapleno;
    Samplee sampleobject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sample_form, container, false);
        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);
        sampleno = view.findViewById(R.id.sampleNo);
        samplenoo = view.findViewById(R.id.samplenoo);
        addsample = view.findViewById(R.id.addsample);
        samplecolect = view.findViewById(R.id.samplecolect);
        samp = view.findViewById(R.id.samp);
        samplenoedit = view.findViewById(R.id.samplenoedit);
        presamplelayout = view.findViewById(R.id.presamplelayout);
        presamplelayout.setVisibility(View.GONE);

        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");


        if (getArguments() != null) {
            isEidtresample = getArguments().getBoolean("isEditresample");
       try {

           SelectedMrNo = getArguments().getString("SelectedMrNo");
           patientCNINC = getArguments().getString("PatientCNIC");
           PatientName = getArguments().getString("PatientName");
           previousSmapleno = getArguments().getString("preSampleNo");
           pid = getArguments().getInt("pid");

       }catch (Exception e){

       }

       }



        if (getArguments() != null) {
            isEidt = getArguments().getBoolean("isEdit");
            try {

                patientname = getArguments().getString("PatientName");
                patientcnic = getArguments().getString("PatientCNIC");
                patientcontactNo = getArguments().getString("PatientCNIC");
                fingerprint = getArguments().getString("fingerprint");
                pideidt = getArguments().getInt("pidEdit");
                patienttype = getArguments().getString("patientType");

            } catch (Exception e) {

            }
        }

        if(isEidt) {
            if (patientcnic != null || pideidt != -1) {
                List<userdataaa> patinetforeditvital = null;
                Samplee sam = null;
                if (pideidt != -1) {
//                     patinetforeditvital = userdataaa.searchByCNICLeader(patientcnicedit);
//                     assem = Assessmentt.searchBycninc(patientcnicedit);
//                 } else {
//                     patinetforeditvital = userdataaa.searchByPhoneLeader(patientname);
                    sam = Samplee.searchBypid(pideidt);
                }

                sampleobject = sam;

            }

            name.setText(patientname);
            cnic.setText(patientcnic);
            patient.setText(patienttype);
            samplenoedit.setText(sampleobject.getSample_no());
            presamplelayout.setVisibility(View.VISIBLE);

        }else if(isEidtresample){
            name.setText(patientname);
            cnic.setText(patientcnic);
            patient.setText(patienttype);
            samplenoedit.setText(previousSmapleno);
            presamplelayout.setVisibility(View.VISIBLE);
        }

        else {
            presamplelayout.setVisibility(View.GONE);
            name.setText(PatientName);
            mrno.setText(SelectedMrNo);
            cnic.setText(patientCNINC);
            patient.setText(PatientType);
        }



        samp.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "21")});

        addsample.setVisibility(View.GONE);
        fragmentManager = getFragmentManager();

        sampleno.setText(new SharedPref(getContext()).GetserverID()+"");
        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);
        sampleno.setEnabled(false);


        samplecolect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    addsample.setVisibility(View.VISIBLE);
                }else {
                    addsample.setVisibility(View.GONE);
                }
            }
        });

        if(isEidt){
            addsample.setOnClickListener(
                    v -> FormValidationedit()
            );
        }else {
            addsample.setOnClickListener(
                    v -> FormValidation()
            );
        }


        return view;
    }

    private void FormValidation() {

//        int startRange = Integer.parseInt(new SharedPref(getContext()).GetHFMISCode());
        int endRange = Integer.parseInt(new SharedPref(getContext()).GetHealthFacility());

        try {
            sno = Integer.parseInt(samplenoo.getText().toString());
            year = Integer.parseInt(samp.getText().toString());
        } catch (NumberFormatException ex) { // handle your exception

        }


        boolean Validationstatus = true;
        if (sno>endRange) {
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(samplenoo.getText().length()!=6){
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(samplenoo.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInRole());
        int h=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());


        if(Validationstatus){

            if(isEidtresample){
                Samplee FL = new Samplee();
                ActiveAndroid.beginTransaction();
                FL.pid = pid;
                FL.IsSync = 0;
                FL.setHospital_id(h);
                FL.setSample_no(new SharedPref(getContext()).GetserverID()+"-"+year+"-"+"00"+sno);
                FL.user_id=i;

                try {
                    FL.save();

                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
                pDialog.setTitleText("Sample Save Successfully");
                pDialog.setCancelable(false);
                pDialog.show();
                Fragment FMFragment = new sample_status_Dashboard();
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

            else
            {
                Samplee FL = new Samplee();
                ActiveAndroid.beginTransaction();
                FL.pid = pid;
                FL.IsSync = 0;
                FL.setHospital_id(h);
                FL.setSample_no(new SharedPref(getContext()).GetserverID()+"-"+year+"-"+"00"+sno);
                FL.user_id=i;

                userdataaa mod = userdataaa.searchBypid(pid);

                mod.ISSample = 1;

                try {
                    FL.save();
                    mod.save();


                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
                pDialog.setTitleText("Sample Save Successfully");
                pDialog.setCancelable(false);
                pDialog.show();
                Fragment FMFragment = new SampleDashboard();
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










    }

    private void FormValidationedit() {

//        int startRange = Integer.parseInt(new SharedPref(getContext()).GetHFMISCode());
        int endRange = Integer.parseInt(new SharedPref(getContext()).GetHealthFacility());

        try {
            sno = Integer.parseInt(samplenoo.getText().toString());
            year = Integer.parseInt(samp.getText().toString());
        } catch (NumberFormatException ex) { // handle your exception

        }


        boolean Validationstatus = true;
        if (sno>endRange) {
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(samplenoo.getText().length()!=6){
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(samplenoo.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getContext(), Constants.sampleNoIncorrect, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInRole());
        int h=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());


        if(Validationstatus){
                Samplee FL = new Samplee();
                ActiveAndroid.beginTransaction();
                sampleobject.pid = pideidt;
            sampleobject.IsSync = 0;
            sampleobject.setHospital_id(h);
            sampleobject.setSample_no(new SharedPref(getContext()).GetserverID()+"-"+year+"-"+"00"+sno);
            sampleobject.user_id=i;

                userdataaa mod = userdataaa.searchBypid(pideidt);

                mod.ISSample = 1;

                try {
                    sampleobject.save();
                    mod.save();


                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
                pDialog.setTitleText("Sample Update Successfully");
                pDialog.setCancelable(false);
                pDialog.show();
                Fragment FMFragment = new SampleDashboard();
                Bundle args = new Bundle();
//            args.putString("SelectedMrNo", mMRNO);
//            args.putInt("FamilyId", family_id);


                    getActivity().onBackPressed();

        }

    }


}

