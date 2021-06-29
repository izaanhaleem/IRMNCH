package com.example.hcp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.models.hcp.vitalListt;
import com.github.nikartm.support.StripedProcessButton;
import com.example.hcp.R;

import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.districtt;
import com.example.hcp.models.hcp.divisionn;
import com.example.hcp.models.hcp.healthFacilityy;
import com.example.hcp.models.hcp.materialStatuss;
import com.example.hcp.models.hcp.occuptaionn;
import com.example.hcp.models.hcp.qualificationn;
import com.example.hcp.models.hcp.tehsill;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.santalu.maskara.widget.MaskEditText;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.media.CamcorderProfile.get;
import static com.activeandroid.Cache.getContext;


public class patientRegistration extends Fragment {

    FragmentManager fragmentManager;
    MaskEditText  etCNIC,etContactNo;
    StripedProcessButton btnSubmit,Submit;
    LinearLayout layoutfirst,layoutsecond,secondlayout,fourlayout;
    Spinner seacchcnic,occupation,materialstatus,qualification,gendr,division,district,tehsil,hf;
    Spinner firsts,seconds,thirds,fours,fives;
    String SelectedOption;
    int SelectedOptionIndex,SelectedGenderIndex,SelectedDivisionCode,SelectedDistrictedCode;
    int Selectfirstyn,Selectsecondyn,Selectthirdyn,Selectfouryn,Selectfiveyn,SelectTcode,SelectedHfcode;
    String firstVal,secondVal,thirdVal,foursVal,fiveVal;
    String OccupationVal,materialstatusVal,qualificationVal,GenderVal;
    EditText dob,etAge,etPatientName,lastname,etFatherSpouse,etCompleteAddress;

    Calendar myCalendar;
    String dateOfBirth;
    int patientage;
    public String Name;
    public String cnicNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patient_registration, container, false);
        fragmentManager = getFragmentManager();
        etCNIC = view.findViewById(R.id.etCNIC);
        etContactNo = view.findViewById(R.id.etContactNo);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        Submit = view.findViewById(R.id.Submit);
        layoutfirst = view.findViewById(R.id.layoutfirst);
        layoutsecond = view.findViewById(R.id.layoutsecond);
        seacchcnic = view.findViewById(R.id.seacchcnic);
        occupation = view.findViewById(R.id.occupation);
        materialstatus = view.findViewById(R.id.materialstatus);
        qualification = view.findViewById(R.id.qualification);
        gendr = view.findViewById(R.id.gendr);
        division = view.findViewById(R.id.division);
        district = view.findViewById(R.id.district);
        tehsil = view.findViewById(R.id.tehsil);
        hf = view.findViewById(R.id.hf);
        dob = view.findViewById(R.id.dob);
        etAge = view.findViewById(R.id.etAge);
        firsts = view.findViewById(R.id.firsts);
        seconds = view.findViewById(R.id.seconds);
        thirds = view.findViewById(R.id.thirds);
        fours = view.findViewById(R.id.fours);
        fives = view.findViewById(R.id.fives);
        secondlayout = view.findViewById(R.id.secondlayout);
        fourlayout = view.findViewById(R.id.fourlayout);
        etPatientName = view.findViewById(R.id.etPatientName);
        lastname = view.findViewById(R.id.lastname);
        etFatherSpouse = view.findViewById(R.id.etFatherSpouse);
        etCompleteAddress = view.findViewById(R.id.etCompleteAddress);


        etAge.setEnabled(false);
        SelectedOption = "";
        SelectedOptionIndex= 0;

        layoutfirst.setVisibility(View.GONE);
        layoutsecond.setVisibility(View.GONE);
        Submit.setVisibility(View.GONE);



        SetSearchOptions();

        btnSubmit.setOnClickListener(
                v -> Search()
        );
        Submit.setOnClickListener(
                v -> FormValidation()
        );
        DobCalculator();
        SetOccupationSpinner();
        SetMaterialSpinner();
        SetQualificationSpinner();
        SetGenderSpinner();
        SetDivisions();
        SetSpinnerfirst();
        SetSpinnersecond();
        SetSpinnerthree();
        SetSpinnerfour();
        SetSpinnerfive();






        return view;
    }
    boolean Validationstatus = true;
    private void FormValidation() {

        Name = etPatientName.getText().toString();
        String lastName = lastname.getText().toString();
        String FatherName = etFatherSpouse.getText().toString();
        String ContactNo = etContactNo.getText().toString().trim();
        cnicNo = etCNIC.getText().toString().trim();
        String Address = etCompleteAddress.getText().toString();


        if (Name.isEmpty()) {
            Toast.makeText(getContext(), Constants.NameMissing, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (lastName.isEmpty()) {
            Toast.makeText(getContext(), Constants.lName, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (FatherName.isEmpty()) {
            Toast.makeText(getContext(), Constants.FHNameMissing, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (Address.isEmpty()) {
            Toast.makeText(getContext(), Constants.AddressMissing, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (cnicNo.length() != 15) {
            Toast.makeText(getContext(), Constants.CompleteCNIC, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (ContactNo.length() != 12) {
            Toast.makeText(getContext(), Constants.PhoneMissing1, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (OccupationVal.isEmpty()) {
            Toast.makeText(getContext(), Constants.occupation, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (materialstatusVal.isEmpty()) {
            Toast.makeText(getContext(), Constants.MaritalStatusMissing, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (qualificationVal.isEmpty()) {
            Toast.makeText(getContext(), Constants.EducationOccupationMissing, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (SelectedGenderIndex == 0) {
            Toast.makeText(getContext(), Constants.GenderMissing, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (SelectedDivisionCode == 0) {
            Toast.makeText(getContext(), Constants.division, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (SelectedDistrictedCode == 0) {
            Toast.makeText(getContext(), Constants.district, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (SelectTcode == 0) {
            Toast.makeText(getContext(), Constants.tehsil, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (SelectedHfcode == 0) {
            Toast.makeText(getContext(), Constants.healthfacility, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (firstVal.equals("")) {
            Toast.makeText(getContext(), Constants.previoushbv, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (thirdVal.equals("")) {
            Toast.makeText(getContext(), Constants.previoushcv, Toast.LENGTH_LONG).show();

            Validationstatus = false;
        } else if (dateOfBirth.equals("")) {
            Toast.makeText(getContext(), Constants.dateofbirth, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (patientage <= 6) {
            Toast.makeText(getContext(), Constants.ageislessthensixx, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        } else if (patientage > 80) {
            Toast.makeText(getContext(), Constants.ageisgreatertheneighty, Toast.LENGTH_LONG).show();
            Validationstatus = false;
        }
        if (Validationstatus) {

            addPatientModel FL = new addPatientModel();
            ActiveAndroid.beginTransaction();
            FL.mrn_no = "";
            FL.IsSync = 0;
            FL.setPatient_id(0);
            FL.setPatient_name(Name);
            FL.setLname(lastName);
            FL.setFather_name(FatherName);
            FL.setPatient_age(patientage);
            FL.setPatient_dob(dateOfBirth);
            FL.setGender(SelectedGenderIndex);
            FL.setSelf_cnic(cnicNo);
            FL.setContact_no_self(ContactNo);
            FL.setAddress(Address);
            FL.setMarital_status(materialstatusVal);
            FL.setOccupation(OccupationVal);
            FL.setQualification(qualificationVal);
            if (patientage == 80) {
                FL.setPatient_age_80("y");
            } else {
                FL.setPatient_age_80("n");
            }
            FL.setPrevious_hbv(firstVal);

            FL.setPatient_type("New Patient");
            FL.ISVital = 0;
            FL.IS_assessment = 0;
            FL.ISSample = 0;

            FL.setPcr_confirmation_hbv(secondVal);
            FL.setPrevious_hcv(thirdVal);
            FL.setPcr_confirmation_hcv(foursVal);
            FL.setDivision(SelectedDivisionCode);
            FL.setDistrict(SelectedDistrictedCode);
            FL.setTehsil(SelectTcode);
            FL.setHospital(SelectedHfcode);
            FL.setIdentifier(new SharedPref(getContext()).GetserverID());
            FL.setUser_id(new SharedPref(getContext()).GetLoggedInRole());
            FL.setHospital_id(new SharedPref(getContext()).GetLoggedInUser());

            FL.save();


            ActiveAndroid.setTransactionSuccessful();

            ActiveAndroid.endTransaction();

            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
            pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
            pDialog.setTitleText("Patient Save Successfully");
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

//    private void addforvital() {
//
//
//        ActiveAndroid.beginTransaction();
//        int masterid = vitalListt.getall().size() + 1;
//        List<vitalListt> vti =vitalListt.getall();
//        for (int i = 0; i < vti.size(); i++) {
//            vitalListt vt = new vitalListt();
//            vt.setSelf_cnic(cnicNo);
//            vt.setPatient_name(Name);
//            vt.setMrn_no("jg");
//            vt.setCreated("415646");
//            vt.setGender("Gender");
//            vt.id = masterid;
//            vt.setIs_blood_bank_patient("N");
//            vt.setIs_reg_completed("hgfy");
//            vt.setNext_status("2");
//            vt.setPatient_age("20");
//            vt.setPatient_type("new");
//            vt.setStage("vital");
//            vt.save();
//        }
//        try {
//
//            ActiveAndroid.setTransactionSuccessful();
//        } finally {
//            ActiveAndroid.endTransaction();
//        }
//    }


    private void DobCalculator() {
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
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
        dateOfBirth = dob.getText().toString();
        LocalDate birthdate = new LocalDate(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),  myCalendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        etAge.setText(period.getYears()+"");
        patientage = Integer.parseInt(etAge.getText().toString());

    }


    void Search() {

        String SelectedOptionVal = etCNIC.getText().toString();

        if (SelectedOptionVal.isEmpty()) {
            etCNIC.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else if (SelectedOptionVal.length() != 15) {
            etCNIC.setError("Please Add CNIC");
        } else {
            List<userdataaa> leaders;
            switch (SelectedOptionIndex) {
                case 1:
                case 2:
                    leaders = userdataaa.searchByCNICLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {

                        Toast.makeText(getContext(), "Patient Already Exist Against This CNIC", Toast.LENGTH_LONG).show();

                    } else {

                        layoutfirst.setVisibility(View.VISIBLE);
                        layoutsecond.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.VISIBLE);
                        btnSubmit.setVisibility(View.GONE);

                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();

                    }
                    break;

            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }

    }
    void SetSearchOptions() {


        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select cnic");
        categoriesEng.add("Self CNIC");
        categoriesEng.add("AFGHAN CNINC");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        seacchcnic.setAdapter(dataAdapter);

        seacchcnic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (seacchcnic.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = seacchcnic.getSelectedItemPosition();

                    SelectedOption = categoriesEng.get(SelectedOptionIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    SelectedOption = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void SetOccupationSpinner() {

        List<occuptaionn> occupationList = occuptaionn.getAll();

        String[] spinnerArray = new String[occupationList.size() + 1];
        spinnerArray[0] = "Occupation*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = occupationList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        occupation.setAdapter(adapter);

        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (occupation.getSelectedItemPosition() > 0) {

                    OccupationVal = occupationList.get(occupation.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    OccupationVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEdit) {
//            for (int i = 0; i < educationList.size(); i++) {
//                int main_status = educationList.get(i).objectListDetailId;
//                if (member.getEducationId() != null) {
//                    int selected_status = member.getEducationId();
//
//                    if (main_status == selected_status) {
//                        Education.setSelection(i + 1);
//                        EducationVal = selected_status;
//                    }
//                }
//
//            }
//        }

    }
    private void SetMaterialSpinner() {

        List<materialStatuss> materialList = materialStatuss.getAll();

        String[] spinnerArray = new String[materialList.size() + 1];
        spinnerArray[0] = "Status*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = materialList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        materialstatus.setAdapter(adapter);

        materialstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (materialstatus.getSelectedItemPosition() > 0) {

                    materialstatusVal = materialList.get(materialstatus.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    materialstatusVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEdit) {
//            for (int i = 0; i < educationList.size(); i++) {
//                int main_status = educationList.get(i).objectListDetailId;
//                if (member.getEducationId() != null) {
//                    int selected_status = member.getEducationId();
//
//                    if (main_status == selected_status) {
//                        Education.setSelection(i + 1);
//                        EducationVal = selected_status;
//                    }
//                }
//
//            }
//        }

    }
    private void SetQualificationSpinner() {

        List<qualificationn> qualificationList = qualificationn.getAll();

        String[] spinnerArray = new String[qualificationList.size() + 1];
        spinnerArray[0] = "Status*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = qualificationList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        qualification.setAdapter(adapter);

        qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (qualification.getSelectedItemPosition() > 0) {

                    qualificationVal = qualificationList.get(qualification.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    qualificationVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEdit) {
//            for (int i = 0; i < educationList.size(); i++) {
//                int main_status = educationList.get(i).objectListDetailId;
//                if (member.getEducationId() != null) {
//                    int selected_status = member.getEducationId();
//
//                    if (main_status == selected_status) {
//                        Education.setSelection(i + 1);
//                        EducationVal = selected_status;
//                    }
//                }
//
//            }
//        }

    }
    private void SetGenderSpinner(){
       List<String> categoriesEng = new ArrayList<String>();
       categoriesEng.add("Gender*");
       categoriesEng.add("Male");
       categoriesEng.add("Female");
       categoriesEng.add("She Male");


       // Creating adapter for spinner
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

       // Drop down layout style - list view with radio button
       dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

       // attaching data adapter to spinner
       gendr.setAdapter(dataAdapter);

       gendr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               if (gendr.getSelectedItemPosition() > 0) {
                   SelectedGenderIndex = gendr.getSelectedItemPosition();

                   GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                   //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
               } else {
                   GenderVal = "";
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
        hf.setVisibility(View.GONE);

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
                    hf.setVisibility(View.GONE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEidt) {
//            for (int i = 0; i < DM.size(); i++) {
//                int main_status = Integer.parseInt(DM.get(i).detail);
//                if (singalFamily != null && singalFamily.getDistrictID()!=null) {
//                    int selected_status = singalFamily.getDistrictID();
//
//                    if (main_status == selected_status) {
//                        districts.setSelection(i + 1);
//                        SelectedDistrictCode = selected_status;
//                    }
//                }
//
//            }
//        }


    }
    private void SetDistrict(String division_code) {

        tehsil.setVisibility(View.GONE);
        hf.setVisibility(View.GONE);

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
                    hf.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        if (isEidt) {
//            for (int i = 0; i < tehsil.size(); i++) {
//                int main_status = Integer.parseInt(tehsil.get(i).detail);
//                if (singalFamily != null) {
//                    int selected_status = singalFamily.getTehsilID();
//
//                    if (main_status == selected_status) {
//                        tehsils.setSelection(i + 1);
//                        SelectTcode = String.valueOf(selected_status);
//                    }
//                }
//
//            }
//        }

    }
    private void SetTehsil(String  dCode) {

        hf.setVisibility(View.GONE);
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

                    String  TCode = tehsili.get(tehsil.getSelectedItemPosition() - 1).tehsil_code;
                    SelectTcode = Integer.parseInt(TCode);


                    hf.setVisibility(View.VISIBLE);
                    SetHF(TCode);
                } else {
//                    SelectedTehsilCode = 0;
                    SelectTcode = 0;
                    hf.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        if (isEidt) {
//            for (int i = 0; i < tehsil.size(); i++) {
//                int main_status = Integer.parseInt(tehsil.get(i).detail);
//                if (singalFamily != null) {
//                    int selected_status = singalFamily.getTehsilID();
//
//                    if (main_status == selected_status) {
//                        tehsils.setSelection(i + 1);
//                        SelectTcode = String.valueOf(selected_status);
//                    }
//                }
//
//            }
//        }


    }
    private void SetHF(String tCode) {


        List<healthFacilityy> hfi = healthFacilityy.searchById(tCode);
        String[] tspinnerArray = new String[hfi.size() + 1];

        tspinnerArray[0] = "HF*";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = hfi.get(i - 1).hf_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hf.setAdapter(adapter);

        hf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hf.getSelectedItemPosition() > 0) {

                    String hfCode = hfi.get(hf.getSelectedItemPosition() - 1).hf_code;
                    SelectedHfcode = Integer.parseInt(hfCode);


//                    hf.setVisibility(View.VISIBLE);


                } else {
//                    SelectedTehsilCode = 0;
                    SelectedHfcode = 0;
//                    hf.setVisibility(View.GONE);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    private void   SetSpinnerfirst(){
      List<String> yesno = new ArrayList<String>();
      yesno.add("select one");
      yesno.add("Yes");
      yesno.add("No");


      // Creating adapter for spinner
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

      // Drop down layout style - list view with radio button
      dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

      // attaching data adapter to spinner
      firsts.setAdapter(dataAdapter);

      firsts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

              if (firsts.getSelectedItemPosition() > 0) {
                  Selectfirstyn = firsts.getSelectedItemPosition();
                  if(Selectfirstyn==2){
                      secondlayout.setVisibility(View.GONE);
                      firstVal="n";
                  }
                  else if(Selectfirstyn==1){
                      firstVal="y";
                      secondlayout.setVisibility(View.VISIBLE);
                  }

                  else {
                      secondlayout.setVisibility(View.VISIBLE);
                  }


//                  firstVal = yesno.get(Selectfirstyn);
//                    OptionValue.setText("");
                  //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
              } else {
                  firstVal = "";
              }


          }

          @Override
          public void onNothingSelected(AdapterView<?> parentView) {
              // your code here
          }

      });

  }
    private void   SetSpinnersecond(){
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        seconds.setAdapter(dataAdapter);

        seconds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (seconds.getSelectedItemPosition() > 0) {
                    Selectsecondyn = seconds.getSelectedItemPosition();

                     if(Selectsecondyn==1){
                         secondVal="y";
                     }else {
                         secondVal="n";
                     }

//                    secondVal = yesno.get(Selectsecondyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    secondVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private void   SetSpinnerthree(){
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        thirds.setAdapter(dataAdapter);

        thirds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (thirds.getSelectedItemPosition() > 0) {
                    Selectthirdyn = thirds.getSelectedItemPosition();
                    if(Selectthirdyn==2){
                        fourlayout.setVisibility(View.GONE);
                        thirdVal="n";
                    }
                    else if (Selectthirdyn==1){
                        fourlayout.setVisibility(View.VISIBLE);
                        thirdVal="y";
                    }
                        else {
                        fourlayout.setVisibility(View.VISIBLE);
                    }
//                    thirdVal = yesno.get(Selectthirdyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    thirdVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private void   SetSpinnerfour(){
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        fours.setAdapter(dataAdapter);

        fours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (fours.getSelectedItemPosition() > 0) {
                    Selectfouryn = fours.getSelectedItemPosition();
                    if(Selectfouryn==2){
                        foursVal="n";
                    }
                    else if (Selectfouryn==1){
                        foursVal="y";
                    }
//                    foursVal = yesno.get(Selectfouryn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    foursVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private void   SetSpinnerfive(){
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        fives.setAdapter(dataAdapter);

        fives.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (fives.getSelectedItemPosition() > 0) {
                    Selectfiveyn = fives.getSelectedItemPosition();
if(Selectfiveyn==1){
    fiveVal="y";
}
else {
    fiveVal="n";
}
//                    fiveVal = yesno.get(Selectfiveyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    fiveVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

}