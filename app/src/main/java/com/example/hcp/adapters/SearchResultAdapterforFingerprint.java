package com.example.hcp.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.fragments.assessmentForm;
import com.example.hcp.fragments.patientRegistration;
import com.example.hcp.fragments.vitalForm;
import com.example.hcp.models.AdaptersData.SearchResultData;

public class SearchResultAdapterforFingerprint extends RecyclerView.Adapter<SearchResultAdapterforFingerprint.ViewHolder> {

    private  SearchResultData[] sData;


    // RecyclerView recyclerView;
    public SearchResultAdapterforFingerprint(SearchResultData[] listdata) {
        this.sData = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_result_item_missingfingerprint, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResultData myListData = sData[position];
        holder.MrNo.setText(sData[position].MrNo);
        holder.Address.setText(sData[position].getContactNo());
        holder.LeaderName.setText(sData[position].getPatientName());
        holder.LeaderCNIC.setText(sData[position].getLeaderCNIC());

//        String fingerprint = sData[position].fingerprint;

//            if (fingerprint.equalsIgnoreCase("notfound")){
//                holder.addfingerprint.setVisibility(View.VISIBLE);
//            }else {
//                holder.addfingerprint.setVisibility(View.GONE);
//            }


        holder.addfingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(view.getContext(), "Hello world", Toast.LENGTH_SHORT).show();
                Fragment FMFragment = new patientRegistration();
                Bundle args = new Bundle();
                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
                args.putString("PatientName",sData[position].getPatientName());
                args.putInt("pidEdit",sData[position].pid);

                args.putBoolean("isEdit",true);
                if(FMFragment != null)
                {
                    FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

                    FMFragment.setArguments(args);
                    try {
                        transaction.replace(R.id.content_frame, FMFragment,"FamilyMemberFragment").addToBackStack("a").commit();
                    } catch (IllegalStateException ignored) {
                    }
                }
                else {
                    Toast.makeText(view.getContext(), holder.MrNo.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(view.getContext(), "Hello world", Toast.LENGTH_SHORT).show();
                Fragment FMFragment = new vitalForm();
                Bundle args = new Bundle();
                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
                args.putString("PatientName",sData[position].getPatientName());
                args.putString("ContactNo",sData[position].getContactNo());
                args.putInt("pidEdit",sData[position].pid);
                args.putBoolean("isEdit",true);
                if(FMFragment != null)
                {
                    FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

                    FMFragment.setArguments(args);
                    try {
                        transaction.replace(R.id.content_frame, FMFragment,"FamilyMemberFragment").addToBackStack("a").commit();

                    } catch (IllegalStateException ignored) {
                    }
                }
                else {
                    Toast.makeText(view.getContext(), holder.MrNo.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.assesstment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(view.getContext(), "Hello world", Toast.LENGTH_SHORT).show();
                Fragment FMFragment = new assessmentForm();
                Bundle args = new Bundle();
                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
                args.putString("PatientName",sData[position].getPatientName());
                args.putString("ContactNo",sData[position].getContactNo());
                args.putInt("pidEdit",sData[position].pid);
                args.putBoolean("isEdit",true);
                if(FMFragment != null)
                {
                    FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

                    FMFragment.setArguments(args);
                    try {
                        transaction.replace(R.id.content_frame, FMFragment,"FamilyMemberFragment").addToBackStack("a").commit();

                    } catch (IllegalStateException ignored) {
                    }
                }
                else {
                    Toast.makeText(view.getContext(), holder.MrNo.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String temp = holder.MrNo.getText().toString();
//                Constants.SelectedFamilyMrNo = temp;
////
//                Fragment FMFragment = new patientRegistration();
//                Bundle args = new Bundle();
//                args.putString("SelectedMrNo",temp);
//                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
//                args.putString("PatientName",sData[position].getPatientName());
//                args.putBoolean("isEdit",true);
//                if(FMFragment != null)
//                {
//                    FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
//
//                    FMFragment.setArguments(args);
//                    try {
//                        transaction.replace(R.id.content_frame, FMFragment,"FamilyMemberFragment").addToBackStack("a").commit();
//
//                    } catch (IllegalStateException ignored) {
//                    }
//                }
//                else {
//                    Toast.makeText(view.getContext(), holder.MrNo.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return sData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView LeaderName,Address,MrNo,LeaderCNIC,vital,assesstment,vaccination,sample;
        public CardView FamilyCard;
        public Button addfingerprint;
        public ViewHolder(View itemView) {
            super(itemView);
            this.LeaderName = itemView.findViewById(R.id.cardLeaderName);
            this.Address =  itemView.findViewById(R.id.cardFamilyAddress);
            this.MrNo = itemView.findViewById(R.id.cardFamilyMrNo);
            this.addfingerprint = itemView.findViewById(R.id.addfingerprint);
            this.LeaderCNIC = itemView.findViewById(R.id.cardLeaderCNIC);
            this.FamilyCard = itemView.findViewById(R.id.familyCard);
            this.vital = itemView.findViewById(R.id.vataledit);
            this.assesstment = itemView.findViewById(R.id.assessmentedit);
            this.vaccination = itemView.findViewById(R.id.vacedit);
            this.sample = itemView.findViewById(R.id.sampleedit);
        }
    }

}
