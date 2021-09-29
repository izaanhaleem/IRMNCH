package com.example.hcp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapter;
import com.example.hcp.adapters.SearchResultAdapterforFingerprint;
import com.example.hcp.models.AdaptersData.SearchResultData;

import com.example.hcp.models.Parcables.FamilyDataParceable;
import com.example.hcp.models.Parcables.PatientDataParceable;
import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultFragment extends Fragment {

    FragmentManager fragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_result_fragment, container, false);

        fragmentManager = getFragmentManager();

        PatientDataParceable[] FDP = (PatientDataParceable []) getArguments().getSerializable("FDP");

        SearchResultData[] myListData = new SearchResultData[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultData();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].MrNo);
            myListData[i].setContactNo(FDP[i].contactNo);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
            myListData[i].setFingerprint(FDP[i].fingerprint);
            myListData[i].setPid(FDP[i].pid);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_result_list);
        SearchResultAdapterforFingerprint adapter = new SearchResultAdapterforFingerprint(myListData);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);




        return view;
    }



}
