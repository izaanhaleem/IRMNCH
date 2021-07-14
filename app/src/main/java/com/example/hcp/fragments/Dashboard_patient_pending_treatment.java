package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterpendingTeatment;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.addPatientModel;

import java.util.ArrayList;
import java.util.List;


public class Dashboard_patient_pending_treatment extends Fragment {
    Button AllpendingList;
    RecyclerView recyclerView;
    Spinner etSearchOptionpending;
    Button btnSearch;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    EditText OptionValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_patient_pending_treatment, container, false);
        etSearchOptionpending = view.findViewById(R.id.etSearchOptionpending);
        AllpendingList=view.findViewById(R.id.AllpendingList);
        OptionValue=view.findViewById(R.id.Searchpending);
        btnSearch=view.findViewById(R.id.btnSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);
        SetSearchOptions();

        btnSearch.setOnClickListener(
                v -> Search()
        );



        AllpendingList.setOnClickListener(
                v -> allpendingpatientList()
        );
        return view;
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
        categoriesEng.add("MrNo");
        categoriesEng.add("CNIC");
        categoriesEng.add("FullName");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        etSearchOptionpending.setAdapter(dataAdapter);

        etSearchOptionpending.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (etSearchOptionpending.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = etSearchOptionpending.getSelectedItemPosition();

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

    private void Search() {
        SelectedOptionVal = OptionValue.getText().toString();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
            List<addPatientModel> vitals;
            switch (SelectedOptionIndex) {
                case 3:
                    vitals = addPatientModel.searchByNameSample(SelectedOptionVal);
                    if (vitals.size() > 0) {

                        SetDataArrayy(vitals);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 2:
                    vitals = addPatientModel.searchByCNICSample(SelectedOptionVal);
                    if (vitals.size() > 0) {

                        SetDataArrayy(vitals);
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

    private void allpendingpatientList() {

        List<addPatientModel> sample;
        sample=addPatientModel.searchallpendingtreament();
        SetDataArrayy(sample);

    }
    private void SetDataArrayy(List<addPatientModel> sample) {

        vitalDataParceable[] FDP = new vitalDataParceable[sample.size()];
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

            if (sample.get(i).patient_name != null) {
                FDP[i].PatientName = sample.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
//            FDP[i].Gender = vitalpatient.get(i).gender;
            FDP[i].LastName = sample.get(i).lname;
            FDP[i].pathentContactNo = sample.get(i).contact_no_self;
            FDP[i].LeaderCNIC = sample.get(i).self_cnic;
            FDP[i].MrNo = sample.get(i).mrn_no;
            FDP[i].patientType = sample.get(i).patient_type;
            FDP[i].pid = sample.get(i).getId().intValue();

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
        SearchResultAdapterpendingTeatment adapter = new SearchResultAdapterpendingTeatment(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}