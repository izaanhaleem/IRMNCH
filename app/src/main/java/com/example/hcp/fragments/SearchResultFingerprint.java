package com.example.hcp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapter;
import com.example.hcp.models.AdaptersData.SearchResultData;
import com.example.hcp.models.Parcables.PatientDataParceable;


public class SearchResultFingerprint extends Fragment {

    FragmentManager fragmentManager;

    public TextView LeaderName,Address,MrNo,LeaderCNIC;
    public CardView FamilyCard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_result_fingerprint, container, false);
        fragmentManager = getFragmentManager();
        LeaderName = view.findViewById(R.id.cardLeaderName);
       Address =  view.findViewById(R.id.contactno);
        MrNo = view.findViewById(R.id.cardFamilyMrNo);
//        FamilyMembers = itemView.findViewById(R.id.cardFamilyMembersbtn);
        LeaderCNIC = view.findViewById(R.id.cardLeaderCNIC);


//        PatientDataParceable FDP = (PatientDataParceable ) getArguments().getSerializable("FDP");

        String name = getArguments().getString("name");
        String cnic = getArguments().getString("cnic");
        String contact = getArguments().getString("contact");
        String mrno = getArguments().getString("mrno");

        LeaderCNIC.setText(cnic);
        MrNo.setText(mrno);
        LeaderName.setText(name);
        Address.setText(contact);

//        SearchResultData[] myListData = new SearchResultData[FDP.length] ;

//        for(int i=0;i<myListData.length ; i++)
//        {
//            myListData[i] = new SearchResultData();
//            myListData[i].setPatientName(FDP[i].PatientName);
//            myListData[i].setMrNo(FDP[i].MrNo);
//            myListData[i].setContactNo(FDP[i].contactNo);
//            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
//        }

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_result_list);
//        SearchResultAdapter adapter = new SearchResultAdapter(myListData);
////        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(adapter);
//



        return view;
    }



}
