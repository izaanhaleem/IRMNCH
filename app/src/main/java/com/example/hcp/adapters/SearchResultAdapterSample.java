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
import com.example.hcp.fragments.sampleForm;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.utils.Constants;

public class SearchResultAdapterSample extends RecyclerView.Adapter<SearchResultAdapterSample.ViewHolder> {

    private  SearchResultDatavital[] sData;


    // RecyclerView recyclerView;
    public SearchResultAdapterSample(SearchResultDatavital[] listdata) {
        this.sData = listdata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_result_item_sample, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResultDatavital myListData = sData[position];
        holder.MrNo.setText(sData[position].MrNo);
        holder.Address.setText(sData[position].getGneder());
        holder.LeaderName.setText(sData[position].getPatientName());
        holder.LeaderCNIC.setText(sData[position].getLeaderCNIC());
        holder.FamilyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = holder.MrNo.getText().toString();
                Constants.SelectedFamilyMrNo = temp;

                Fragment FMFragment = new sampleForm();
                Bundle args = new Bundle();
                args.putString("SelectedMrNo",temp);
                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
                args.putString("PatientName",sData[position].getPatientName());
                args.putString("Patienttype",sData[position].getPatienttype());
                args.putInt("pid",sData[position].getPid());



//                addvitalll fg = addvitalll.searchBycninc(sData[position].getLeaderCNIC());
//                args.putDouble("temperature",fg.temperature);
//                args.putInt("puls",fg.pulse);
//                args.putInt("BP_Systolic",fg.bp_systolic);
//                args.putInt("BP_Diastolic",fg.bp_diastolic);
//                args.putDouble("Height",fg.height);
//                args.putDouble("Weight",fg.weight);

                args.putBoolean("isEdit",false);



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
    }


    @Override
    public int getItemCount() {
        return sData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView LeaderName,Address,MrNo,LeaderCNIC;
        public CardView FamilyCard;
        public Button FamilyMembers;
        public ViewHolder(View itemView) {
            super(itemView);
            this.LeaderName = itemView.findViewById(R.id.cardLeaderName);
            this.Address =  itemView.findViewById(R.id.cardFamilyAddress);
            this.MrNo = itemView.findViewById(R.id.cardFamilyMrNo);
            this.FamilyMembers = itemView.findViewById(R.id.cardFamilyMembersbtn);
            this.LeaderCNIC = itemView.findViewById(R.id.cardLeaderCNIC);
            this.FamilyCard = itemView.findViewById(R.id.familyCard);
        }
    }



}
