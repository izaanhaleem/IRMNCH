package com.example.hcp.fragments;

import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterSample;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.addPatientModel;

import java.util.ArrayList;
import java.util.List;


public class SampleDashboard extends Fragment {

    Button Search,AllSampleList;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    EditText OptionValue;
    Spinner SearchOptions;
    RecyclerView recyclerView;
    TextView total_record;
    LinearLayout sync_data;
    int vitallistcount = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sample_dashboard, container, false);

        Search=view.findViewById(R.id.btnSearch);
        AllSampleList=view.findViewById(R.id.AllAssesementList);
        OptionValue=view.findViewById(R.id.Searchassessment);
        SearchOptions=view.findViewById(R.id.etSearchOptionAssessment);
        total_record = view.findViewById(R.id.total__sync_recordv);
        sync_data = view.findViewById(R.id.sync_datav);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);

        SetSearchOptions();
        AllSampleList.setOnClickListener(
                v -> allSampletList()
        );

        Search.setOnClickListener(
                v -> Search()
        );


        return view;
    }

    private void allSampletList() {

        List<addPatientModel> assessment;
        assessment=addPatientModel.searchallISSample();
        SetDataArrayy(assessment);

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

    private void SetDataArrayy(List<addPatientModel> samples) {

        vitalDataParceable[] FDP = new vitalDataParceable[samples.size()];
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

            if (samples.get(i).patient_name != null) {
                FDP[i].PatientName = samples.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
//            FDP[i].Gender = vitalpatient.get(i).gender;
            FDP[i].LastName = samples.get(i).lname;
            FDP[i].pathentContactNo = samples.get(i).contact_no_self;
            FDP[i].LeaderCNIC = samples.get(i).self_cnic;
            FDP[i].MrNo = samples.get(i).mrn_no;
            FDP[i].patientType = samples.get(i).patient_type;
            FDP[i].pid = samples.get(i).getId().intValue();

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
        SearchResultAdapterSample adapter = new SearchResultAdapterSample(myListData);
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
        categoriesEng.add("MrNo");
        categoriesEng.add("CNIC");
        categoriesEng.add("FullName");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        SearchOptions.setAdapter(dataAdapter);

        SearchOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (SearchOptions.getSelectedItemPosition() > 0) {
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