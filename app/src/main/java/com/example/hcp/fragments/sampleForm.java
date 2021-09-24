package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.InputFilterMinMax;
import com.example.hcp.utils.SharedPref;
import com.santalu.maskara.widget.MaskEditText;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class sampleForm extends Fragment {

    FragmentManager fragmentManager;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    EditText name,mrno,patient,cnic;
    MaskEditText sampleno;
    EditText samp;
    MaskEditText samplenoo;
    int pid;
    Button addsample;
    SwitchCompat samplecolect;
   public int  sno,year;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sample_form, container, false);
        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");

        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);
        sampleno = view.findViewById(R.id.sampleNo);
        samplenoo = view.findViewById(R.id.samplenoo);
        addsample = view.findViewById(R.id.addsample);
        samplecolect = view.findViewById(R.id.samplecolect);
        samp = view.findViewById(R.id.samp);
        samp.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "21")});

        addsample.setVisibility(View.GONE);
        fragmentManager = getFragmentManager();

        sampleno.setText(new SharedPref(getContext()).GetserverID()+"");
        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);
        sampleno.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);
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
        addsample.setOnClickListener(
                v -> FormValidation()
        );

        return view;
    }

    private void FormValidation() {

//        int startRange = Integer.parseInt(new SharedPref(getContext()).GetHFMISCode());
        int endRange = Integer.parseInt(new SharedPref(getContext()).GetHealthFacility());

try {
     sno = Integer.parseInt(samplenoo.getText().toString());
    year = Integer.parseInt(samp.getText().toString());
} catch(NumberFormatException ex){ // handle your exception

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
        FL.pid = pid;
        FL.IsSync = 0;
        FL.setHospital_id(h);
        FL.setSample_no(new SharedPref(getContext()).GetserverID()+"-"+year+"-"+"00"+sno);
        FL.user_id=i;


        userdataaa mod = userdataaa.searchBycnic(patientCNINC);

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

