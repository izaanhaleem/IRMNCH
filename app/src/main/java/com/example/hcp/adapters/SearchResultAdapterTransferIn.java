package com.example.hcp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcp.R;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;

public class SearchResultAdapterTransferIn extends RecyclerView.Adapter<SearchResultAdapterTransferIn.ViewHolder> {

    private  SearchResultDatavital[] sData;
    private Context context;


    // RecyclerView recyclerView;


    public SearchResultAdapterTransferIn(SearchResultDatavital[] sData, Context context) {
        this.sData = sData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_transfer_in, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResultDatavital myListData = sData[position];
        holder.MrNo.setText(sData[position].MrNo);
        holder.samplestatus.setText(sData[position].getTransferin());
        holder.LeaderName.setText(sData[position].getPatientName());
        holder.LeaderCNIC.setText(sData[position].getLeaderCNIC());

        holder.Address.setText(sData[position].getEx_hospital());
        holder.viralload.setText(sData[position].getCurrnet_hospital());

        if(sData[position].getTransferin()==null){
            holder.FamilyCard.setVisibility(View.GONE);
//            Toast.makeText(context, "Record Not Found!", Toast.LENGTH_SHORT).show();
        }
        else {
            holder.FamilyCard.setVisibility(View.VISIBLE);
        }



//        if(sData[position].getHcvviralload()!=null && !sData[position].getHcvviralload().isEmpty()){
//            holder.viralload.setText("HCV: "+sData[position].getHcvviralload());
//        }else if(sData[position].getHbvviralload()!=null && !sData[position].getHbvviralload().isEmpty()){
//            holder.viralload.setText("HBV: "+sData[position].getHbvviralload());
//        }else{
//            holder.viralload.setText("not found");
//            }

//        holder.viralload.setText("HCV: "+sData[position].getHcvviralload()+", HBV: "+sData[position].getHbvviralload());


//        holder.FamilyMembers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String temp = holder.MrNo.getText().toString();
//                Constants.SelectedFamilyMrNo = temp;
//
//                Fragment FMFragment = new sampleForm();
//                Bundle args = new Bundle();
//                args.putString("SelectedMrNo",temp);
//                args.putString("PatientCNIC",sData[position].getLeaderCNIC());
//                args.putString("PatientName",sData[position].getPatientName());
//                args.putString("Patienttype",sData[position].getPatienttype());
//                args.putInt("pid",sData[position].getPid());
//
//
//
////                addvitalll fg = addvitalll.searchBycninc(sData[position].getLeaderCNIC());
////                args.putDouble("temperature",fg.temperature);
////                args.putInt("puls",fg.pulse);
////                args.putInt("BP_Systolic",fg.bp_systolic);
////                args.putInt("BP_Diastolic",fg.bp_diastolic);
////                args.putDouble("Height",fg.height);
////                args.putDouble("Weight",fg.weight);
//
//                args.putBoolean("isEdit",false);
//
//
//
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
        public TextView LeaderName,Address,MrNo,LeaderCNIC,samplestatus,viralload;
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
            this.samplestatus = itemView.findViewById(R.id.samplestatus);
            this.viralload = itemView.findViewById(R.id.viralload);
        }
    }



}
