package com.example.hcp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterpendingTeatment;
import com.example.hcp.models.AdaptersData.SearchResultDatapending;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.pendingDataParceable;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.medicineRequest;
import com.example.hcp.models.hcp.medicineResponse;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard_patient_pending_treatment extends Fragment {
    Button AllpendingList;
    RecyclerView recyclerView;
    Spinner etSearchOptionpending;
    Button btnSearch;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    EditText OptionValue;
    LinearLayout sync_data;
    List<medicinee> pending;
    public int totalSize;
    int pendingsubmitcount = 0;
    TextView total_record;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_patient_pending_treatment, container, false);
        etSearchOptionpending = view.findViewById(R.id.etSearchOptionpending);
        AllpendingList=view.findViewById(R.id.AllpendingList);
        OptionValue=view.findViewById(R.id.Searchpending);
        btnSearch=view.findViewById(R.id.btnSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);
        sync_data = view.findViewById(R.id.sync_datapending);
        total_record = view.findViewById(R.id.total__sync_recordv);
        SetSearchOptions();

        btnSearch.setOnClickListener(
                v -> Search()
        );



        AllpendingList.setOnClickListener(
                v -> allpendingpatientList()
        );

        totalSYncREcord();


        sync_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitmedicine();
            }
        });

        return view;
    }
    public void totalSYncREcord() {


        List<medicinee> medicine = medicinee.searchBySync();
        totalSize = medicine.size();
        total_record.setText(totalSize + "");
    }


    private void Search() {

        
     
        SelectedOptionVal = OptionValue.getText().toString();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {

            List<userdataaa> list ;

            switch (SelectedOptionIndex) {
                case 3:
                    list = userdataaa.searchByMRNOLeader(SelectedOptionVal);
                    if (list.size() > 0) {
                        SetDataArrayy(list);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                        SetDataArrayy(list);
                    }

                    break;

                case 2:
                    list = userdataaa.searchByCNICLeader(SelectedOptionVal);
                    if (list.size() > 0) {

                        SetDataArrayy(list);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                        SetDataArrayy(list);
                    }
                    break;
                case 1:
                    list = userdataaa.searchBynameLeader(SelectedOptionVal);
                    if (list.size() > 0) {
                        SetDataArrayy(list);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                        SetDataArrayy(list);
                    }
                    break;


            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
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
        categoriesEng.add("Name");
        categoriesEng.add("CNIC");
        categoriesEng.add("Mrn_no");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        etSearchOptionpending.setAdapter(dataAdapter);

        etSearchOptionpending.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (etSearchOptionpending.getSelectedItemPosition() > 0) {
                    SelectedOptionIndex = etSearchOptionpending.getSelectedItemPosition();

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
    private void allpendingpatientList() {
//
//        List<addPatientModel> sample;
//        sample=addPatientModel.searchallpendingtreament();
//        SetDataArrayy(sample);

        List<userdataaa> pending;
        pending=userdataaa.searchallbothbaselineForm();
        SetDataArrayy(pending);

    }
    private void SetDataArrayy(List<userdataaa> sample) {

        pendingDataParceable[] FDP = new pendingDataParceable[sample.size()];
        for (int i = 0; i < FDP.length; i++) {
            FDP[i] = new pendingDataParceable();
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

//            if(sample.get(i).pcr_confirmation_hcv=="y" && sample.get(i).cbl=="Y"){
                FDP[i].text1 = "HCV";
                FDP[i].text2 = "HCV Baseline Investigation Form";
//            }else if(sample.get(i).pcr_confirmation_hbv=="y" && sample.get(i).bbl=="Y"){
//                FDP[i].text1 = "HBV";
//                FDP[i].text2 = "HBV Baseline Investigation Form";
//            }else {
//                FDP[i].text1 = "Both";
//                FDP[i].text2 = "Both Baseline Investigation Form";
//            }
            if(sample.get(i).hcv_viral_count != null){
                FDP[i].hcvviralount = sample.get(i).hcv_viral_count;
            }else {
                FDP[i].hcvviralount = 0;
            }

//             FDP[i].hcvviralount = sample.get(i).hcv_viral_count;

            if(sample.get(i).hbv_viral_count !=null){
                FDP[i].hbvviralcount = sample.get(i).hbv_viral_count;
            }else {
                FDP[i].hbvviralcount = 0;
            }


//             FDP[i].hbvviralcount = sample.get(i).hbv_viral_count;


            if (sample.get(i).sample_id != null) {
                FDP[i].sample_id = sample.get(i).sample_id;
            } else {
                FDP[i].sample_id = 0;
            }
            if (sample.get(i).hcv_medicine_duration != null) {
                FDP[i].hcv_medicine_duration = sample.get(i).hcv_medicine_duration;
            } else {
                FDP[i].hcv_medicine_duration = 0;
            }
             FDP[i].is_cirrhotic_patient = sample.get(i).is_cirrhotic_patient;
//            FDP[i].Gender = vitalpatient.get(i).gender;
            FDP[i].LastName = sample.get(i).lname;
            FDP[i].pathentContactNo = sample.get(i).contact_no_self;
            FDP[i].LeaderCNIC = sample.get(i).self_cnic;
            FDP[i].MrNo = sample.get(i).mrn_no;
            FDP[i].patientType = sample.get(i).patient_type;
            FDP[i].pid = sample.get(i).patient_id;
        }

        SearchResultDatapending[] myListData = new SearchResultDatapending[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultDatapending();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].pathentContactNo);
            myListData[i].setGneder(FDP[i].LastName);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
            myListData[i].setPatienttype(FDP[i].patientType);
            myListData[i].setPid(FDP[i].pid);
            myListData[i].setText1(FDP[i].text1);
            myListData[i].setText2(FDP[i].text2);
            myListData[i].setHcvviralcount(FDP[i].hcvviralount);
            myListData[i].setHbvviralcount(FDP[i].hbvviralcount);
            myListData[i].setSample_id(FDP[i].sample_id);
            myListData[i].setIs_cirrhotic_patient(FDP[i].is_cirrhotic_patient);
            myListData[i].setHcv_medicine_duration(FDP[i].hcv_medicine_duration);

        }
        SearchResultAdapterpendingTeatment adapter = new SearchResultAdapterpendingTeatment(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter .notifyDataSetChanged();
    }




    private void submitmedicine() {

        pending = medicinee.searchBySync();
        pendingsubmitcount = pending.size();
        if (pending.size() > 0) {

            for (int i = 0; i < pending.size(); i++) {
                medicineRequest med = new medicineRequest();
                if (pending.get(i).invs_status != null) {
                    med.setInvs_status(pending.get(i).invs_status);
                }else {
                    med.setInvs_status("");
                }
                if (pending.get(i).result_type != null) {
                    med.setResult_type(pending.get(i).result_type);
                } else {
                    med.setResult_type("");
                }
                if (pending.get(i).hemoglobin != null) {
                    med.setHemoglobin(pending.get(i).hemoglobin);
                } else {
                    med.setUser_id(0);
                }
                if (pending.get(i).ast != null) {
                    med.setAst(pending.get(i).ast);
                } else {
                    med.setAst("0");
                }

                if (pending.get(i).alt != null) {
                    med.setAlt(pending.get(i).alt);
                } else {
                    med.setMobile_id(0);
                }

                if (pending.get(i).platelet != null) {
                    med.setPlatelet(pending.get(i).platelet);
                } else {
                    med.setPlatelet("");
                }

                if (pending.get(i).tlc != null) {
                    med.setTlc(pending.get(i).tlc);
                } else {
                    med.setTlc("");
                }

                if (pending.get(i).apri != null) {
                    med.setApri(pending.get(i).apri);
                } else {
                    med.setApri("");
                }

                if (pending.get(i).viral_count != null) {
                    med.setViral_count(pending.get(i).viral_count);
                } else {
                    med.setViral_count("0");
                }

                if (pending.get(i).pcr_result != null) {
                    med.setPcr_result(pending.get(i).pcr_result);
                } else {
                    med.setPcr_result("0");
                }

                if (pending.get(i).sample_id != null) {
                    med.setSample_id(pending.get(i).sample_id);
                } else {
                    med.setSample_id("");
                }

                if (pending.get(i).lab_name != null) {
                    med.setLab_name(pending.get(i).lab_name);
                } else {
                    med.setLab_name("0");
                }

                if (pending.get(i).other_lab_name != null) {
                    med.setOther_lab_name(pending.get(i).other_lab_name);
                } else {
                    med.setOther_lab_name("0");
                }

                if (pending.get(i).id != null) {
                    med.setId(pending.get(i).id);
                } else {
                    med.setId("0");
                }

                if (pending.get(i).baseline_type != null) {
                    med.setBaseline_type(pending.get(i).baseline_type);
                } else {
                    med.setBaseline_type("0");
                }
                if (pending.get(i).urea != null) {
                    med.setUrea(pending.get(i).urea);
                } else {
                    med.setUrea("0");
                }
                if (pending.get(i).creatinie != null) {
                    med.setCreatinie(pending.get(i).creatinie);
                } else {
                    med.setCreatinie("0");
                }
                if (pending.get(i).blood_sugar_random != null) {
                    med.setBlood_sugar_random(pending.get(i).blood_sugar_random);
                } else {
                    med.setBlood_sugar_random("0");
                }
                if (pending.get(i).urea != null) {
                    med.setUrea(pending.get(i).urea);
                } else {
                    med.setUrea("0");
                }
                if (pending.get(i).drug_interaction != null) {
                    med.setDrug_interaction(pending.get(i).drug_interaction);
                } else {
                    med.setDrug_interaction("0");
                }
                if (pending.get(i).hcv_medicine_recommended != null) {
                    med.setHcv_medicine_recommended(pending.get(i).hcv_medicine_recommended);
                } else {
                    med.setHcv_medicine_recommended("0");
                }
                if (pending.get(i).disburse_6_mnth_dose != null) {
                    med.setDisburse_6_mnth_dose(pending.get(i).disburse_6_mnth_dose);
                } else {
                    med.setDisburse_6_mnth_dose("0");
                }
                if (pending.get(i).cirrhotic_medicine_flow != null) {
                    med.setCirrhotic_medicine_flow(pending.get(i).cirrhotic_medicine_flow);
                } else {
                    med.setCirrhotic_medicine_flow("0");
                }
                if (pending.get(i).created != null) {
                    med.setCreated(pending.get(i).created);
                } else {
                    med.setCreated("0");
                }
                if (pending.get(i).user_id != null) {
                    med.setUser_id(pending.get(i).user_id);
                } else {
                    med.setUser_id(0);
                }
                if (pending.get(i).hospital_id != null) {
                    med.setHospital_id(pending.get(i).hospital_id);
                } else {
                    med.setHospital_id(0);
                }
                if (pending.get(i).updated != null) {
                    med.setUpdated(pending.get(i).updated);
                } else {
                    med.setUpdated("0");
                }
                if (pending.get(i).mobile_id != null) {
                    med.setMobile_id(Math.toIntExact(pending.get(i).getId()));
                } else {
                    med.setMobile_id(0);
                }
                if (pending.get(i).hcv_medicine_duration != null) {
                    med.setHcv_medicine_duration(pending.get(i).hcv_medicine_duration);
                } else {
                    med.setHcv_medicine_duration(0);
                }
                if (pending.get(i).treatment_history != null) {
                    med.setTreatment_history(pending.get(i).treatment_history);
                } else {
                    med.setTreatment_history("0");
                }
                if (pending.get(i).disburse_3_mnth_dose != null) {
                    med.setDisburse_3_mnth_dose(pending.get(i).disburse_3_mnth_dose);
                } else {
                    med.setDisburse_3_mnth_dose("0");
                }
                if (pending.get(i).treatment_options != null) {
                    med.setTreatment_options(pending.get(i).treatment_options);
                } else {
                    med.setTreatment_options("0");
                }

                if (pending.get(i).is_all_med_delivered_frm_baseline != null) {
                    med.setIs_all_med_delivered_frm_baseline(pending.get(i).is_all_med_delivered_frm_baseline);
                } else {
                    med.setIs_all_med_delivered_frm_baseline("N");
                }

//                List<addPatientModel> fli = addPatientModel.getall();
//
//                sampless.get(i).pid = fli.get(i).getPatient_id();

                submitpending(med,pending.get(i));

            }

        }
    }

    private void submitpending(medicineRequest med, medicinee medicinee) {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<medicineResponse> call = RetrofitClient
                .getInstance().getApi().savepending(med);
        call.enqueue(new Callback<medicineResponse>() {
            @Override
            public void onResponse(Call<medicineResponse> call, Response<medicineResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {


//                    ActiveAndroid.beginTransaction();
//                    try {
//
//                        List<medicinee> s = medicinee.getall();
//                        for (int i = 0; i < s.size(); i++) {
//
//                            medicinee mo = medicinee.load(medicinee.class, response.body().getDatum8().getMobile_id());
//                            mo.IsSync = 1;
//                            mo.save();



                    medicinee.IsSync=1;
                    medicinee.save();

                    userdataaa s = userdataaa.searchByPatientId(response.body().getData().getPatient_id());
                    s.IsActive = 0;
                    s.save();
//                    userdataaa FL = userdataaa.load(userdataaa.class, response.body().getDatum8().getPatient_id());
//                        }
//                        ActiveAndroid.setTransactionSuccessful();
//                    } finally {
//                        ActiveAndroid.endTransaction();
//                    }




//                    deleteformvitalList(response.body().getData().getMobile_id());
                }

                totalSYncREcord();


            }

            @Override
            public void onFailure(Call<medicineResponse> call, Throwable t) {

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






}