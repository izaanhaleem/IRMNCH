package com.example.hcp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.adapters.SearchResultAdapter;
import com.example.hcp.adapters.SearchResultAdaptervital;
import com.example.hcp.models.AdaptersData.SearchResultData;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.PatientDataParceable;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.AddVitalResponse;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addPatientRequest;
import com.example.hcp.models.hcp.addPatientResponse;
import com.example.hcp.models.hcp.addVitalRequest;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.vitalListt;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class vitalDashboard extends Fragment {
    Button Search,allpatientList;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    EditText OptionValue;
    Spinner SearchOptions;
    RecyclerView recyclerView;
    TextView total_record;
    LinearLayout sync_data;
    int vitallistcount = 0;
    List<addvitalll> vitalss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vital_dashboard, container, false);
        Search=view.findViewById(R.id.btnSearch);
        allpatientList=view.findViewById(R.id.allpatientList);
        OptionValue=view.findViewById(R.id.vitalSearchVal);
        SearchOptions=view.findViewById(R.id.etSearchOptionvital);
        total_record = view.findViewById(R.id.total__sync_recordv);
        sync_data = view.findViewById(R.id.sync_datav);


        totalSYncREcord();

        SetSearchOptions();
        Search.setOnClickListener(
                v -> Search()
        );
        allpatientList.setOnClickListener(
                v -> allvitalList()
        );
        recyclerView = (RecyclerView) view.findViewById(R.id.vitalRecy);

        sync_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SyncRecord();

            }
        });
         return view;
    }

    private void allvitalList() {

        List<addPatientModel> vitals;
        vitals=addPatientModel.searchallISvital();
        SetDataArrayy(vitals);

    }

    private void SyncRecord() {
        vitallistcount = 0;

        vitalss = addvitalll.searchBySync();
        vitallistcount = vitalss.size();

        if (vitalss.size() > 0) {
            submitePatients();
        }
    }

    private void submitePatients() {
        if (vitalss.size() > 0) {

            for (int i = 0; i < vitalss.size(); i++) {
                addVitalRequest fmb = new addVitalRequest();
                if (vitalss.get(i).pid != null) {
                    fmb.setPid(vitalss.get(i).pid);
                }
                if (vitalss.get(i).getTemperature() != null) {
                    fmb.setTemperature(vitalss.get(i).getTemperature());
                } else {
                    fmb.setTemperature(0.0);

                }
                if (vitalss.get(i).getPulse() != null) {
                    fmb.setPulse(vitalss.get(i).getPulse());
                } else {
                    fmb.setPulse(0);

                }
                if (vitalss.get(i).getBp_systolic() != null) {
                    fmb.setBp_systolic((int) Math.round(vitalss.get(i).getBp_systolic()));
                } else {
                    fmb.setBp_systolic(0);

                }
                if (vitalss.get(i).getBp_diastolic() != null) {
                    fmb.setBp_diastolic((int) Math.round(vitalss.get(i).getBp_diastolic()));
                } else {
                    fmb.setBp_diastolic(0);

                }
                if (vitalss.get(i).getHeight() != null) {
                    fmb.setHeight(vitalss.get(i).getHeight());
                } else {
                    fmb.setHeight(0.0);

                }
                if (vitalss.get(i).getWeight() != null) {
                    fmb.setWeight(vitalss.get(i).getWeight());
                } else {
                    fmb.setWeight(0.0);

                }
                if (vitalss.get(i).getUser_id() != null) {
                    fmb.setUser_id(vitalss.get(i).getUser_id());
                } else {
                    fmb.setUser_id(0);

                }
                if (vitalss.get(i).getId() != null) {
                    fmb.setMobile_id(vitalss.get(i).getId());
                } else {
                    fmb.setMobile_id(0L);
                }

                List<addPatientModel> fl = addPatientModel.getall();

                vitalss.get(i).pid = fl.get(i).getPatient_id();

                Submitvital(fmb);

            }

        }
    }

    private void Submitvital(addVitalRequest fmb) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<AddVitalResponse> call = RetrofitClient
                .getInstance().getApi().saveVital(fmb);
        call.enqueue(new Callback<AddVitalResponse>() {
            @Override
            public void onResponse(Call<AddVitalResponse> call, Response<AddVitalResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {


                    ActiveAndroid.beginTransaction();
                    try {

                        List<addvitalll> fl = addvitalll.getall();
                        for (int i = 0; i < fl.size(); i++) {

                            addvitalll mod = addvitalll.load(addvitalll.class, response.body().getData().getMobile_id());
                            mod.IsSync = 1;
                            mod.save();

                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
//                    deleteformvitalList(response.body().getData().getMobile_id());
                }

                totalSYncREcord();


            }

            @Override
            public void onFailure(Call<AddVitalResponse> call, Throwable t) {

                try {
//                    syncedleaders = syncedleaders+1;
//                    if(syncedleaders==leadersubmitcount){
////                        submitLmpData();
//                    }
//                    Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                } catch (Exception exception) {
                    dialog.dismiss();
                }

            }
        });

    }

//    private void deleteformvitalList(Integer mobile_id) {
//
//        ActiveAndroid.beginTransaction();
//        try {
//
//            vitalListt item =  vitalListt.searchByid(mobile_id);
//            item.delete();
//
//            ActiveAndroid.setTransactionSuccessful();
//        } finally {
//            ActiveAndroid.endTransaction();
//        }
//
//    }

    private void totalSYncREcord() {

        List<addvitalll> vital = addvitalll.searchBySync();
        int totalSize = vital.size();

        total_record.setText(totalSize + "");

    }

    void Search() {

        SelectedOptionVal = OptionValue.getText().toString();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
//            List<addPatientModel> vitals; addPatientModel.searchByISvital();
            List<addPatientModel> patientlist;
            List<addPatientModel> vitals;
            switch (SelectedOptionIndex) {
                case 3:
                    vitals = addPatientModel.searchByName(SelectedOptionVal);
                    if (vitals.size() > 0) {

                        SetDataArrayy(vitals);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 2:

                    vitals = addPatientModel.searchByISvital(SelectedOptionVal);
                    if (vitals.size() > 0) {

                                SetDataArrayy(vitals);
                            }
                            else {
                                Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                            }

                    break;
                case 1:
//                    patientlist = addPatientModel.searchByMrno(SelectedOptionVal);
//                    if (patientlist.size() > 0) {
//                        SetDataArrayy(patientlist);
//                    } else {
//                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
//                    }
                    break;


            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
    }

    private void SetDataArrayy(List<addPatientModel> vitalpatient) {

        vitalDataParceable[] FDP = new vitalDataParceable[vitalpatient.size()];
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

            if (vitalpatient.get(i).patient_name != null) {
                FDP[i].PatientName = vitalpatient.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
//            FDP[i].Gender = vitalpatient.get(i).gender;
            FDP[i].LastName = vitalpatient.get(i).lname;
            FDP[i].pathentContactNo = vitalpatient.get(i).contact_no_self;
            FDP[i].LeaderCNIC = vitalpatient.get(i).self_cnic;
            FDP[i].MrNo = vitalpatient.get(i).mrn_no;
            FDP[i].patientType = vitalpatient.get(i).patient_type;
            if(vitalpatient.get(i).patient_id==0){
                FDP[i].pid = vitalpatient.get(i).getId().intValue();
            }else {
                FDP[i].pid = vitalpatient.get(i).patient_id;
            }

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
        SearchResultAdaptervital adapter = new SearchResultAdaptervital(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    void SetSearchOptions() {

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