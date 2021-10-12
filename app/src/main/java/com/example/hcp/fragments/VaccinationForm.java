package com.example.hcp.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VaccinationForm extends Fragment {
    EditText firstd,secondd,thirdd;
    Calendar myCalendar;
    String dateOfBirth;
    String SelectedMrNo,patientCNINC,PatientName,PatientType;
    int pid,alreadyTakenDose,stage;
    EditText name,mrno,patient,cnic;
    SwitchCompat firstvacdose;
    Button addvaccination;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vaccination_form, container, false);

        firstd = view.findViewById(R.id.firstd);
        secondd = view.findViewById(R.id.secondd);
        thirdd = view.findViewById(R.id.thirdd);
        firstvacdose = view.findViewById(R.id.firsts);
        addvaccination = (Button) view.findViewById(R.id.addvaccination);
        DobCalculator1();
        DobCalculator2();
        DobCalculator3();

        SelectedMrNo = getArguments().getString("SelectedMrNo");
        patientCNINC = getArguments().getString("PatientCNIC");
        PatientName = getArguments().getString("PatientName");
        PatientType = getArguments().getString("Patienttype");
        pid = getArguments().getInt("pid");
//      alreadyTakenDose = getArguments().getInt("AlreadytakenDose");

        name = view.findViewById(R.id.name);
        mrno = view.findViewById(R.id.mrno);
        patient = view.findViewById(R.id.patient);
        cnic = view.findViewById(R.id.cnic);

        name.setEnabled(false);
        mrno.setEnabled(false);
        cnic.setEnabled(false);
        patient.setEnabled(false);

        name.setText(PatientName);
        mrno.setText(SelectedMrNo);
        cnic.setText(patientCNINC);
        patient.setText(PatientType);



        firstvacdose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    stage = 1;
                } else {
                    stage = 0;
                }
            }
        });


        addvaccination.setOnClickListener(
                v -> FormValidation()
        );





        return view;
     }

    private void FormValidation() {
        boolean Validationstatus = true;

        if(firstd.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Plz Select Dose Date", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }
        if(stage == 0){
            Toast.makeText(getContext(), "Plz Select Dose ", Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }

        if(Validationstatus){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String matdate = sdf.format(new Date());

            int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInRole());
            int h=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());

            Vaccinationn va = new Vaccinationn();
            ActiveAndroid.beginTransaction();
            va.id = pid;
            va.stage = stage;
            va.dose_date = firstd.getText().toString();
            va.created = matdate;
            va.user_id =  i;
            va.hospital_id = h ;
            va.updated = matdate ;
            va.IsSync = 0;

            userdataaa mod = userdataaa.searchBypid(pid);

            mod.IS_Vaccination = 1;

            try {
                va.save();
                mod.save();


                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.teal_700));
            pDialog.setTitleText("Vaccination Save Successfully");
            pDialog.setCancelable(false);
            pDialog.show();
            Fragment FMFragment = new SampleDashboard();
            Bundle args = new Bundle();
//            args.putString("SelectedMrNo", mMRNO);
//            args.putInt("FamilyId", family_id);
//            if (FMFragment != null) {
//
                getActivity().onBackPressed();

//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                FMFragment.setArguments(args);
//                try {
//                    transaction.add(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();
//
//                } catch (IllegalStateException ignored) {
//
//                }
//            } else {
//                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
//            }
        }

    }


    private void DobCalculator1() {
        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        firstd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dpd =new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                dpd.show();
                // TODO Auto-generated method stub
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        firstd.setText(sdf.format(myCalendar.getTime()));
        dateOfBirth = firstd.getText().toString();
        LocalDate birthdate = new LocalDate(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),  myCalendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        firstd.setText(myCalendar.get(Calendar.YEAR)+"-"+myCalendar.get(Calendar.MONTH)+"-"+myCalendar.get(Calendar.DATE));
//        patientage = Integer.parseInt(etAge.getText().toString());

    }

    private void DobCalculator2() {
        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };
        secondd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dpd =new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                dpd.show();
                // TODO Auto-generated method stub
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

    }
    private void updateLabel2() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        secondd.setText(sdf.format(myCalendar.getTime()));
//        dateOfBirth = firstd.getText().toString();
        LocalDate birthdate = new LocalDate(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),  myCalendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        secondd.setText(myCalendar.get(Calendar.YEAR)+"/"+myCalendar.get(Calendar.MONTH)+"/"+myCalendar.get(Calendar.DATE));
//        patientage = Integer.parseInt(etAge.getText().toString());

    }

    private void DobCalculator3() {
        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel3();
            }

        };
        thirdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dpd =new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                dpd.show();
                // TODO Auto-generated method stub
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

    }
//    @SuppressLint("SetTextI18n")
    private void updateLabel3() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        thirdd.setText(sdf.format(myCalendar.getTime()));
//        dateOfBirth = firstd.getText().toString();
        LocalDate birthdate = new LocalDate(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),  myCalendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        thirdd.setText(myCalendar.get(Calendar.YEAR)+"/"+myCalendar.get(Calendar.MONTH)+"/"+myCalendar.get(Calendar.DATE));
//      patientage = Integer.parseInt(etAge.getText().toString());

    }
}