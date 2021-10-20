package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputFilter;
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
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.InputFilterMinMax;
import com.example.hcp.utils.SharedPref;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.activeandroid.Cache.getContext;

public class vitalForm extends Fragment {
    FragmentManager fragmentManager;
    EditText name,mrno,patient,cnic,temperature,pulseBPM,BPSystolic,BPDiastolic,HeightCM,WeightKG;
    Button addvital;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    int pid,pideidt;
    List<addPatientModel> isvitals;
    boolean isEidt = false;
    String patientname,patientcnic,patientcontactNo,fingerprint,patienttype;
    userdataaa patientobject;
    addvitalll vitalobject;
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

        if (getArguments() != null) {
            isEidt = getArguments().getBoolean("isEdit");
            try {

                patientname = getArguments().getString("PatientName");
                patientcnic = getArguments().getString("PatientCNIC");
                patientcontactNo = getArguments().getString("PatientCNIC");
                fingerprint = getArguments().getString("fingerprint");
                patienttype = getArguments().getString("patientType");
                pideidt = getArguments().getInt("pidEdit");


            } catch (Exception e) {


            }
        }
        if(isEidt){

            name.setText(patientname);
//            mrno.setText(SelectedMrNo);
            cnic.setText(patientcnic);
            patient.setText(patienttype);

        }else {
            temperature.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "108")});
            BPSystolic.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "301")});
            BPDiastolic.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "202")});
        }



        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);





        if(isEidt){
            if (patientcnic != null || pideidt != -1) {
                List<userdataaa> patinetforeditvital = null;
                addvitalll vitalla = null;
                if (patientcnic != "-       -") {
                    patinetforeditvital = userdataaa.searchByCNICLeader(patientcnic);
                    vitalla = addvitalll.searchBycninc(patientcnic);
                    if(vitalla!=null){
                        vitalobject = vitalla;
                        String puls = String.valueOf(vitalobject.getPulse());
//                String bps = String.valueOf(vitalobject.getBp_systolic());
//                String bpd = String.valueOf(vitalobject.getBp_diastolic());
                        temperature.setText(vitalobject.getTemperature()+"");
                        pulseBPM.setText(puls);
                        BPSystolic.setText(vitalobject.getBp_systolic()+"");
                        BPDiastolic.setText(vitalobject.getBp_diastolic()+"");

                        for (int i = 0; i < patinetforeditvital.size(); i++) {
                            patientobject = patinetforeditvital.get(i);
                            name.setText(patientobject.getPatient_name());
                            cnic.setText(patientobject.getSelf_cnic());
                            String patientT = patientobject.getPatient_type();
                            patient.setText(patientT);
                            mrno.setText(patientobject.getMrn_no());
                        }
                    }else {

                    }
                } else {
                    patinetforeditvital = userdataaa.searchByPhoneLeader(patientname);
                    vitalla = addvitalll.searchBypid(pideidt);
                    if(vitalla!=null){
                        vitalobject = vitalla;
                        String puls = String.valueOf(vitalobject.getPulse());
//                String bps = String.valueOf(vitalobject.getBp_systolic());
//                String bpd = String.valueOf(vitalobject.getBp_diastolic());
                        temperature.setText(vitalobject.getTemperature()+"");
                        pulseBPM.setText(puls);
                        BPSystolic.setText(vitalobject.getBp_systolic()+"");
                        BPDiastolic.setText(vitalobject.getBp_diastolic()+"");

                        for (int i = 0; i < patinetforeditvital.size(); i++) {
                            patientobject = patinetforeditvital.get(i);
                            name.setText(patientobject.getPatient_name());
                            cnic.setText(patientobject.getSelf_cnic());
                            String patientT = patientobject.getPatient_type();
                            patient.setText(patientT);
                            mrno.setText(patientobject.getMrn_no());
                        }
                    } else {
                    }
                }
            }


//                Double tempdouble = vitalobject.getTemperature();
//                String temp = String.valueOf(tempdouble);

            }




        if(isEidt){
        addvital.setOnClickListener(
                v -> FormValidationedit()
        );
    }else {
        addvital.setOnClickListener(
                v -> FormValidation()
        );
    }

        return view;

    }








    private void FormValidation() {
        String  asdf = temperature.getText().toString();
        String pls = pulseBPM.getText().toString();
        String  bps = BPSystolic.getText().toString();
        String  bpd = BPDiastolic.getText().toString();
        boolean Validationstatus = true;
        if (asdf.isEmpty()) {
            Toast.makeText(getContext(), Constants.temp, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(bps.isEmpty()){
            Toast.makeText(getContext(), "BPSystolic is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(bpd.isEmpty()){
            Toast.makeText(getContext(), "BPDiastolic is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(pls.isEmpty()){
            Toast.makeText(getContext(), "Pulse is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

//        float val = Integer.parseInt(asdf);

        try {
            Float f= Float.parseFloat(asdf);
            Float B= Float.parseFloat(bps);
            Float D= Float.parseFloat(bpd);

        if(f > 106.0){
            Toast.makeText(getContext(), Constants.error, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

//        String  bps = BPSystolic.getText().toString();
//        Float B= Float.parseFloat(bps);
        if(B > 300.0){
            Toast.makeText(getContext(), Constants.bpsys, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

//        String  bpd = BPDiastolic.getText().toString();
//        Float D= Float.parseFloat(bpd);
        if(D > 200.0){
            Toast.makeText(getContext(), Constants.bpdys, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }


        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());

        if (Validationstatus) {
            double temperatur = Double.parseDouble(temperature.getText().toString());
            double bpsystolic = Double.parseDouble(BPSystolic.getText().toString());
            double bdsystolic = Double.parseDouble(BPDiastolic.getText().toString());


            addvitalll FL = new addvitalll();
            ActiveAndroid.beginTransaction();

            FL.pid = pid;
            FL.IsSync = 0;
            FL.temperature = temperatur;
            FL.pulse = Integer.parseInt(pulseBPM.getText().toString());
            FL.bp_systolic = bpsystolic;
            FL.bp_diastolic = bdsystolic;
//            FL.height=Double.parseDouble(HeightCM.getText().toString());
//            FL.weight=Double.parseDouble(WeightKG.getText().toString());;
            FL.user_id = i;
            FL.self_cnic = patientCNINC;

            userdataaa mod = userdataaa.searchBypid(pid);
            mod.ISVital = 1;
            mod.save();

            try {
                FL.save();

                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }

            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
            pDialog.setTitleText("Vitals Added Successfully");
            pDialog.setCancelable(false);
            pDialog.show();

            Fragment FMFragment = new DashboardFragment();
            Bundle args = new Bundle();
//            args.putString("SelectedMrNo", mMRNO);
//            args.putInt("FamilyId", family_id);
//            if (FMFragment != null) {
//
                getActivity().onBackPressed();
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                FMFragment.setArguments(args);
//                try {
//                    transaction.replace(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();
//
//                } catch (IllegalStateException ignored) {
//
//                }
//            } else {
//                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
//            }
        }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
    private void FormValidationedit() {
        String  asdf = temperature.getText().toString();
        String pls = pulseBPM.getText().toString();
        String  bps = BPSystolic.getText().toString();
        String  bpd = BPDiastolic.getText().toString();
        boolean Validationstatus = true;
        if (asdf.isEmpty()) {
            Toast.makeText(getContext(), Constants.temp, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(bps.isEmpty()){
            Toast.makeText(getContext(), "BPSystolic is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(bpd.isEmpty()){
            Toast.makeText(getContext(), "BPDiastolic is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }else if(pls.isEmpty()){
            Toast.makeText(getContext(), "Pulse is empty", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

//        float val = Integer.parseInt(asdf);

        try {
            Float f= Float.parseFloat(asdf);
            Float B= Float.parseFloat(bps);
            Float D= Float.parseFloat(bpd);

            if(f > 106.0){
                Toast.makeText(getContext(), Constants.error, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }

//        String  bps = BPSystolic.getText().toString();
//        Float B= Float.parseFloat(bps);
            if(B > 300.0){
                Toast.makeText(getContext(), Constants.bpsys, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }

//        String  bpd = BPDiastolic.getText().toString();
//        Float D= Float.parseFloat(bpd);
            if(D > 200.0){
                Toast.makeText(getContext(), Constants.bpdys, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }


            int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());

            if (Validationstatus) {
                double temperatur = Double.parseDouble(temperature.getText().toString());
                double bpsystolic = Double.parseDouble(BPSystolic.getText().toString());
                double bdsystolic = Double.parseDouble(BPDiastolic.getText().toString());


//                addvitalll FL = new addvitalll();
                ActiveAndroid.beginTransaction();

                vitalobject.pid = pideidt;
                vitalobject.IsSync = 0;
                vitalobject.temperature = temperatur;
                vitalobject.pulse = Integer.parseInt(pulseBPM.getText().toString());
                vitalobject.bp_systolic = bpsystolic;
                vitalobject.bp_diastolic = bdsystolic;
//            FL.height=Double.parseDouble(HeightCM.getText().toString());
//            FL.weight=Double.parseDouble(WeightKG.getText().toString());;
                vitalobject.user_id = i;
                vitalobject.self_cnic = patientCNINC;

                userdataaa mod = userdataaa.searchBypid(pideidt);
                mod.ISVital = 1;
                mod.save();

                try {
                    vitalobject.save();

                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }

                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
                pDialog.setTitleText("Vitals Update Successfully");
                pDialog.setCancelable(false);
                pDialog.show();

                Fragment FMFragment = new DashboardFragment();
                Bundle args = new Bundle();
//            args.putString("SelectedMrNo", mMRNO);
//            args.putInt("FamilyId", family_id);
//            if (FMFragment != null) {
//
                getActivity().onBackPressed();
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                FMFragment.setArguments(args);
//                try {
//                    transaction.replace(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();
//
//                } catch (IllegalStateException ignored) {
//
//                }
//            } else {
//                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
//            }
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}