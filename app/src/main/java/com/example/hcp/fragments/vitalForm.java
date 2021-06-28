package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.activeandroid.Cache.getContext;

public class vitalForm extends Fragment {
    FragmentManager fragmentManager;
    EditText name,mrno,patient,cnic,temperature,pulseBPM,BPSystolic,BPDiastolic,HeightCM,WeightKG;
    Button addvital;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    int pid;
    List<addPatientModel> isvitals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vital, container, false);
        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");
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
        addvital = view.findViewById(R.id.addvital);

        fragmentManager = getFragmentManager();

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);

        addvital.setOnClickListener(
                v -> FormValidation()
        );

        return view;

    }

    private void FormValidation() {
        String  asdf = temperature.getText().toString();

        boolean Validationstatus = true;
        if (asdf.isEmpty()) {
            Toast.makeText(getContext(), Constants.temp, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }
        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());

        if (Validationstatus) {
            double temperatur = Double.parseDouble(temperature.getText().toString());

            addvitalll FL = new addvitalll();
            ActiveAndroid.beginTransaction();
            FL.pid = pid;
            FL.IsSync = 0;
            FL.temperature= temperatur;
            FL.pulse=Integer.parseInt(pulseBPM.getText().toString());
            FL.bp_systolic=Integer.parseInt(BPSystolic.getText().toString());
            FL.bp_diastolic=Integer.parseInt(BPDiastolic.getText().toString());
            FL.height=Double.parseDouble(HeightCM.getText().toString());
            FL.weight=Double.parseDouble(WeightKG.getText().toString());;
            FL.user_id=i;
            FL.self_cnic = patientCNINC;

            addPatientModel mod = addPatientModel.searchBycnic(patientCNINC);

            mod.ISVital = 1;

            try {
                FL.save();
                mod.save();


                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }

            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
            pDialog.setTitleText("Vitals Added Successfully");
            pDialog.setCancelable(false);
            pDialog.show();

            Fragment FMFragment = new vitalDashboard();
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