package com.example.hcp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterpendingTeatment;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.addPatientModel;

import java.util.List;


public class Dashboard_patient_pending_treatment extends Fragment {
    Button AllpendingList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_patient_pending_treatment, container, false);

        AllpendingList=view.findViewById(R.id.AllpendingList);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);
        AllpendingList.setOnClickListener(
                v -> allpendingpatientList()
        );
        return view;
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