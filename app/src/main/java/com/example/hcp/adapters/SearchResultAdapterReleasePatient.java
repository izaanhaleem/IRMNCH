package com.example.hcp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.activities.LoginActivity;
import com.example.hcp.activities.MainActivity;
import com.example.hcp.fragments.DashboardRleasePatients;
import com.example.hcp.fragments.transfer_jail;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.hcp.ReleaseLocalTable;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.pixplicity.easyprefs.library.Prefs;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.activeandroid.Cache.getContext;

public class SearchResultAdapterReleasePatient extends RecyclerView.Adapter<SearchResultAdapterReleasePatient.ViewHolder> {

    private  SearchResultDatavital[] sData;
    private Context context;


    // RecyclerView recyclerView;


    public SearchResultAdapterReleasePatient(SearchResultDatavital[] sData, Context context) {
        this.sData = sData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_release_patient, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResultDatavital myListData = sData[position];
        holder.MrNo.setText(sData[position].MrNo);
//        holder.samplestatus.setText(sData[position].getSample_staus());
        holder.LeaderName.setText(sData[position].getPatientName());
        holder.LeaderCNIC.setText(sData[position].getLeaderCNIC());
//        holder.Address.setText(sData[position].getSample_number());
//        if(sData[position].getHcvviralload()!=null && !sData[position].getHcvviralload().isEmpty()){
//            holder.viralload.setText("HCV: "+sData[position].getHcvviralload());
//        }else if(sData[position].getHbvviralload()!=null && !sData[position].getHbvviralload().isEmpty()){
//            holder.viralload.setText("HBV: "+sData[position].getHbvviralload());
//        }else{
//            holder.viralload.setVisibility(View.GONE);
//        }

//        holder.viralload.setText("HCV: "+sData[position].getHcvviralload()+", HBV: "+sData[position].getHbvviralload());


        holder.FamilyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = holder.MrNo.getText().toString();
                Constants.SelectedFamilyMrNo = temp;

                new AlertDialog.Builder(context)
                        .setTitle("Release")
                        .setMessage("Are you sure you want to Release Patient?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                try {

//                                    ReleaseLocalTable vtl =new  ReleaseLocalTable();
                                    ActiveAndroid.beginTransaction();
//                                    vtl.setIs_prison_release("y");
//                                    vtl.setPatient_id(sData[position].getPid());
//                                    vtl.setIs_sycn(0);

                                    userdataaa mod = userdataaa.searchByPatientId(sData[position].getPid());

                                    mod.ISRelease = 0;

                                    try {
//                                        vtl.save();
                                        mod.save();
                                        ActiveAndroid.setTransactionSuccessful();
                                    } finally {
                                        ActiveAndroid.endTransaction();
                                    }

                                    Toast.makeText(getContext(), "Patient Transfer Successfully!", Toast.LENGTH_SHORT).show();

                                    Fragment FMFragment = new DashboardRleasePatients();


                                    if(FMFragment != null)
                                    {
                                        FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

//                    FMFragment.setArguments(args);
                                        try {
                                            transaction.replace(R.id.content_frame, FMFragment,"FamilyMemberFragment").addToBackStack("a").commit();

                                        } catch (IllegalStateException ignored) {
                                        }
                                    }
                                    else {
//                                        Toast.makeText(view.get(), holder.MrNo.getText(), Toast.LENGTH_SHORT).show();
                                    }

                                }catch (Exception e){

                                }
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
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
