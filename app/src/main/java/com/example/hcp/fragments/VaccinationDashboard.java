package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterVaccination;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.MaskedEditText;

import java.util.ArrayList;
import java.util.List;

public class VaccinationDashboard extends Fragment {
    Button Search,AllAssesementList;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    MaskedEditText OptionValue;
    Spinner SearchOptions;
    RecyclerView recyclerView;
    TextView total_record;
    LinearLayout sync_data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vaccination_dashboard, container, false);

        Search=view.findViewById(R.id.btnSearch);
        AllAssesementList=view.findViewById(R.id.AllAssesementList);
        OptionValue=view.findViewById(R.id.Searchassessment);
        SearchOptions=view.findViewById(R.id.etSearchOptionAssessment);
        total_record = view.findViewById(R.id.total__sync_recordv);
        sync_data = view.findViewById(R.id.sync_datav);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);


        SetSearchOptions();
        AllAssesementList.setOnClickListener(
                v -> allVaccinationList()
        );

        Search.setOnClickListener(
                v -> Search()
        );



        return view;
    }

    private void allVaccinationList() {

        List<userdataaa> vaccination;
        vaccination=userdataaa.searchallISVaccination();
        SetDataArrayy(vaccination);




    }
    private void Search() {
        SelectedOptionVal = OptionValue.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
            List<userdataaa> vaccine;
            switch (SelectedOptionIndex) {
                case 3:
                    vaccine = userdataaa.searchByNameVaccination(SelectedOptionVal);
                    if (vaccine.size() > 0) {

                        SetDataArrayy(vaccine);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 2:
                    vaccine = userdataaa.searchByISVaccination(SelectedOptionVal);
                    if (vaccine.size() > 0) {

                        SetDataArrayy(vaccine);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 1:
//                    vitalpatient = vitalListt.searchBymrno(SelectedOptionVal);
//                    if (vitalpatient.size() > 0) {
//                        SetDataArrayy(vitalpatient);
//                    } else {
//                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
//                    }
                    break;


            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
    }
    private void SetDataArrayy(List<userdataaa> vaccination) {

        vitalDataParceable[] FDP = new vitalDataParceable[vaccination.size()];
        for (int i = 0; i < FDP.length; i++) {
            FDP[i] = new vitalDataParceable();
//            FDP[i].Address        =    SFR.get(i).getAddress();

//            FDP[i].Address = "Address";
//            FDP[i].FamilyId = SFR.get(i).getFamilyId().toString();
//            FDP[i].MrNo = SFR.get(i).mrn_no;
//            if (SFR.get(i).getFamilyMemberId() != null) {
//                FDP[i].LeaderId = SFR.get(i).getFamilyMemberId().toString();
//            } else {
//                FDP[i].LeaderId = "N/A";
//
//            }

            if (vaccination.get(i).patient_name != null) {
                FDP[i].PatientName = vaccination.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
//            FDP[i].Gender = vitalpatient.get(i).gender;
            FDP[i].LastName = vaccination.get(i).lname;
            FDP[i].pathentContactNo = vaccination.get(i).contact_no_self;
            FDP[i].LeaderCNIC = vaccination.get(i).self_cnic;
            FDP[i].MrNo = vaccination.get(i).mrn_no;
            FDP[i].patientType = vaccination.get(i).patient_type;
//            FDP[i].pid = assessment.get(i).getId().intValue();
            if(vaccination.get(i).patient_id==0){
                FDP[i].pid = vaccination.get(i).getId().intValue();
            }else {
                FDP[i].pid = vaccination.get(i).patient_id;
            }
        }

        SearchResultDatavital[] myListData = new SearchResultDatavital[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultDatavital();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].pathentContactNo);
            myListData[i].setGneder(FDP[i].LastName);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
            myListData[i].setPatienttype(FDP[i].patientType);
            myListData[i].setPid(FDP[i].pid);

        }
        SearchResultAdapterVaccination adapter = new SearchResultAdapterVaccination(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void SetSearchOptions() {
        List<String> categories = new ArrayList<String>();
        categories.add("آپشن منتخب کریں");
        categories.add("خاندان نمبر سے تلاش کریں");
        categories.add("شناختی کارڈ نمبر سے تلاش کریں");
        categories.add("نام سے تلاش کریں");
        categories.add("فون نمبر سے تلاش کریں");

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("select option");
        categoriesEng.add("Mr No");
        categoriesEng.add("CNIC");
        categoriesEng.add("Full Name");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        SearchOptions.setAdapter(dataAdapter);
        SearchOptions.setSelection(2);
        SearchOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (SearchOptions.getSelectedItemPosition() > 0) {


                    if(SearchOptions.getSelectedItemPosition() == 1) {
                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AAA-99-99-99999999999");

//                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
                    }else if(SearchOptions.getSelectedItemPosition() == 2){

                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("99999-9999999-9");

//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
//
                    }else if(SearchOptions.getSelectedItemPosition() == 3){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("");

                    }


                    SelectedOptionIndex = SearchOptions.getSelectedItemPosition();

                    SelectedOption = categoriesEng.get(SelectedOptionIndex);
                    OptionValue.setText("");
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
}