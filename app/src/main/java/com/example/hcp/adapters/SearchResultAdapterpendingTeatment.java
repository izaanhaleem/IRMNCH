package com.example.hcp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.fragments.pendingTratmentForm;
import com.example.hcp.fragments.sampleForm;
import com.example.hcp.models.AdaptersData.SearchResultDatapending;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.utils.Constants;

public class SearchResultAdapterpendingTeatment extends RecyclerView.Adapter<SearchResultAdapterpendingTeatment.ViewHolder> {

    private  SearchResultDatapending[] sData;
    private Context context;

    // RecyclerView recyclerView;


    public SearchResultAdapterpendingTeatment(SearchResultDatapending[] sData, Context context) {
        this.sData = sData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_result_item_pending, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResultDatapending myListData = sData[position];
        holder.MrNo.setText(sData[position].MrNo);
        holder.Address.setText(sData[position].getGneder());
        holder.LeaderName.setText(sData[position].getPatientName());
        holder.LeaderCNIC.setText(sData[position].getLeaderCNIC());
        holder.text1.setText(sData[position].getRsult_type());
        holder.FamilyMembers.setText(sData[position].getRsult_type()+" BaseLine Investigation");

        if(sData[position].getRsult_type()!=null){
            if(sData[position].getRsult_type().equalsIgnoreCase("HCV")){
                holder.Address.setText(sData[position].getHcvviralcount());
            }else if(sData[position].getRsult_type().equalsIgnoreCase("HBV")){
                holder.Address.setText(sData[position].getHbvviralcount());
            }else if(sData[position].getRsult_type().equalsIgnoreCase("BOTH")){
                holder.Address.setText("HBV: "+sData[position].getHbvviralcount()+", HCV: "+sData[position].getHcvviralcount());
            }else {
//                Toast.makeText(context, "Record not Found!", Toast.LENGTH_SHORT).show();
                holder.FamilyCard.setVisibility(View.VISIBLE);
                holder.testtypenull.setVisibility(View.VISIBLE);
                holder.Address.setVisibility(View.GONE);
            }
        }else {
//            Toast.makeText(context, "Record not Found!", Toast.LENGTH_SHORT).show();
                holder.FamilyCard.setVisibility(View.VISIBLE);
                holder.testtypenull.setVisibility(View.VISIBLE);
                holder.Address.setVisibility(View.GONE);
              }




//        holder.text2.setText(sData[position].getText2());

//        if(sData[position].getText1().equalsIgnoreCase("Null")){
//            holder.FamilyCard.setVisibility(View.GONE);
//        }


        holder.FamilyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = holder.MrNo.getText().toString();
                Constants.SelectedFamilyMrNo = temp;

                Fragment FMFragment = new pendingTratmentForm();
                Bundle args = new Bundle();
                args.putString("SelectedMrNo",temp);
                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
                args.putString("PatientName",sData[position].getPatientName());
                args.putString("Patienttype",sData[position].getPatienttype());
                args.putString("resultType",sData[position].getRsult_type());
                args.putInt("pid",sData[position].getPid());
//                args.putString("testType",sData[position].getText1());
                args.putString("hcvviralcount",sData[position].getHcvviralcount());
                args.putString("hbvviralcount",sData[position].getHbvviralcount());
                args.putString("sample_id",sData[position].getSample_id());
//                args.putString("iscorronic_patient",sData[position].getIs_cirrhotic_patient());
//                args.putInt("hcv_medicine_duration",sData[position].getHcv_medicine_duration());

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
        public TextView LeaderName,Address,MrNo,LeaderCNIC,text1,text2;
        public CardView FamilyCard;
        public Button FamilyMembers;
        public LinearLayout testtypenull;

        public ViewHolder(View itemView) {
            super(itemView);
            this.LeaderName = itemView.findViewById(R.id.cardLeaderName);
            this.Address =  itemView.findViewById(R.id.cardFamilyAddress);
            this.MrNo = itemView.findViewById(R.id.cardFamilyMrNo);
            this.FamilyMembers = itemView.findViewById(R.id.cardFamilyMembersbtn);
            this.LeaderCNIC = itemView.findViewById(R.id.cardLeaderCNIC);
            this.FamilyCard = itemView.findViewById(R.id.familyCard);
            this.text1 = itemView.findViewById(R.id.text1);
            this.testtypenull = itemView.findViewById(R.id.testtypenull);
//            this.text2 = itemView.findViewById(R.id.cardFamilyMembersbtn);
        }
    }



}
