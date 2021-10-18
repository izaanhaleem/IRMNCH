package com.example.hcp.fragments;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Base64;
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbException;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbHost;
import com.example.hcp.EditTextTelefoneMask;
import com.example.hcp.R;
import com.example.hcp.activities.VerificationActivity;
import com.example.hcp.adapters.SearchResultAdapterAssessment;
import com.example.hcp.adapters.SearchResultAdapterSample_status;
import com.example.hcp.adapters.SearchResultAdapterpendingTeatment;
import com.example.hcp.models.AdaptersData.SearchResultDatapending;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.pendingDataParceable;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.MedicineDisbursment_Table;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.medicineRequest;
import com.example.hcp.models.hcp.medicineResponse;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.sample_status_Table;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.GetReaderActivity;
import com.example.hcp.utils.Globals;
import com.example.hcp.utils.MaskedEditText;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004;


public class Dashboard_patient_pending_treatment extends Fragment {
    Button AllpendingList;
    RecyclerView recyclerView;
    Spinner etSearchOptionpending;
    Button btnSearch;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    MaskedEditText OptionValue;
    LinearLayout sync_data;
    List<medicinee> pending;
    public int totalSize;
    int pendingsubmitcount = 0;
    TextView total_record;

    private String m_sn = "";
    private String m_deviceName = "";
    Reader m_reader;
    private final int GENERAL_ACTIVITY_RESULT = 1;
    private static final String ACTION_USB_PERMISSION = "com.digitalpersona.uareu.dpfpddusbhost.USB_PERMISSION";
    private Engine m_engine = null;
    List<userdataaa> allData = new ArrayList<>();

    private ProgressDialog dialog;
    ImageView scanner,ma_iv_fingerprint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_patient_pending_treatment, container, false);
        m_engine = UareUGlobal.GetEngine();
        etSearchOptionpending = view.findViewById(R.id.etSearchOptionpending);
        AllpendingList=view.findViewById(R.id.AllpendingList);
        OptionValue=view.findViewById(R.id.Searchpending);
        btnSearch=view.findViewById(R.id.btnSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);
        sync_data = view.findViewById(R.id.sync_datapending);
        total_record = view.findViewById(R.id.total__sync_recordv);

        ma_iv_fingerprint = view.findViewById(R.id.ma_iv_fingerprint_med_desbursment_dashboard);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);
        loaddata();
        SetSearchOptions();
        allBaselineList();

//        OptionValue.addTextChangedListener(EditTextTelefoneMask.insert(OptionValue));


        btnSearch.setOnClickListener(
                v -> Search()
        );
//
//
//        AllpendingList.setOnClickListener(
//                v -> allpendingpatientList()
//        );

        totalSYncREcord();


        sync_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitmedicine();
            }
        });


        ma_iv_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_FINGERPRINT);
                launchGetReader();
            }
        });


        return view;
    }



    public void loaddata(){
        new Dashboard_patient_pending_treatment.ProgressAsyncTask().execute();
    }

    public class ProgressAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        int myProgress;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            dialog.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            allData = userdataaa.getall();
//            while(myProgress<100){
//                myProgress++;
//                publishProgress(myProgress);
//                SystemClock.sleep(100);
//            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub

        }

    }









    private void allBaselineList() {
        List<userdataaa> baseline;
        baseline=userdataaa.getallISMedicinepending();
        SetDataArrayy(baseline);
    }

    public void totalSYncREcord() {
        List<medicinee> medicine = medicinee.searchBySync();
        totalSize = medicine.size();
        total_record.setText(totalSize + "");
    }

    private void Search() {

        SelectedOptionVal = OptionValue.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {

            List<userdataaa> list ;

            switch (SelectedOptionIndex) {
                case 2:
                    list = userdataaa.searchByMRNOLeader(SelectedOptionVal);
                    if (list.size() > 0) {
                        SetDataArrayy(list);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                        SetDataArrayy(list);
                    }

                    break;

                case 1:
                case 3:

                    list = userdataaa.searchByCNICLeader(SelectedOptionVal);
                    if (list.size() > 0) {

                        SetDataArrayy(list);
                    }
                    else {
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
        categoriesEng.add("CNIC");
        categoriesEng.add("Mrn No");
        categoriesEng.add("Afghan CNIC");
        categoriesEng.add("Finger Print");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        etSearchOptionpending.setAdapter(dataAdapter);
        etSearchOptionpending.setSelection(1);
        etSearchOptionpending.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {




                if (etSearchOptionpending.getSelectedItemPosition() > 0) {

                    if(etSearchOptionpending.getSelectedItemPosition() == 1) {
                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("99999-9999999-9");


//                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
                    }else if(etSearchOptionpending.getSelectedItemPosition() == 2){
                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AAA-99-99-9999999999999");

//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
//
                    }else if(etSearchOptionpending.getSelectedItemPosition() == 3){
                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AA-99999999999");
                    }else if(etSearchOptionpending.getSelectedItemPosition() == 4){
                        ma_iv_fingerprint.setVisibility(View.VISIBLE);
                    }

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
//    private void allpendingpatientList() {
////
////        List<addPatientModel> sample;
////        sample=addPatientModel.searchallpendingtreament();
////        SetDataArrayy(sample);
//
//        List<MedicineDisbursment_Table> pending;
//        pending=userdataaa.searchallbothbaselineForm();
//        SetDataArrayy(pending);
//
//    }
    private void SetDataArrayy(List<userdataaa> med) {

        pendingDataParceable[] FDP = new pendingDataParceable[med.size()];
        for (int i = 0; i < FDP.length; i++) {
            FDP[i] = new pendingDataParceable();
////            FDP[i].Address        =    SFR.get(i).getAddress();
//
////            FDP[i].Address = "Address";
////            FDP[i].FamilyId = SFR.get(i).getFamilyId().toString();
////            FDP[i].MrNo = SFR.get(i).mrn_no;
////            if (SFR.get(i).getFamilyMemberId() != null) {
////                FDP[i].LeaderId = SFR.get(i).getFamilyMemberId().toString();
////            } else {
////                FDP[i].LeaderId = "N/A";
////
////            }
//
            if (med.get(i).getPatient_name() != null) {
                FDP[i].PatientName = med.get(i).getPatient_name();
            } else {
                FDP[i].PatientName = "N/A";
            }

//            if(med.get(i).getIs_hbv_detected()!=null || med.get(i).getIs_hcv_detected()!=null) {
//                if (med.get(i).getIs_hbv_detected().equalsIgnoreCase("Y") && med.get(i).getIs_hcv_detected().equalsIgnoreCase("Y")) {
//                    FDP[i].text1 = "Both";
//                    FDP[i].text2 = "Both Baseline Investigation Form";
//                } else if (med.get(i).getIs_hbv_detected().equalsIgnoreCase("Y")) {
//                    FDP[i].text1 = "HBV";
//                    FDP[i].text2 = "HBV Baseline Investigation Form";
//                } else if(med.get(i).getIs_hcv_detected().equalsIgnoreCase("Y") ) {
//                    FDP[i].text1 = "HCV";
//                    FDP[i].text2 = "HCV Baseline Investigation Form";
//                }else {
//                    FDP[i].text1 = "Null";
//                    FDP[i].text2 = "Null Baseline Investigation Form";               }
//            }else {
//                Toast.makeText(getContext(), "Record Not Found", Toast.LENGTH_SHORT).show();
////                FDP[i].text1 = "Both";
////                FDP[i].text2 = "Both Baseline Investigation Form";
//            }

            if(med.get(i).getHcv_viral_count() != null){
                FDP[i].hcvviralount = med.get(i).getHcv_viral_count();
            }else {
                FDP[i].hcvviralount = "0";
            }

//             FDP[i].hcvviralount = sample.get(i).hcv_viral_count;

            if(med.get(i).getHbv_viral_count() !=null){
                FDP[i].hbvviralcount = med.get(i).getHbv_viral_count();
            }else {
                FDP[i].hbvviralcount = "0";
            }


//             FDP[i].hbvviralcount = sample.get(i).hbv_viral_count;


            if (med.get(i).getSample_number() != null) {
                FDP[i].sample_id = med.get(i).getSample_number();
            } else {
                FDP[i].sample_id = "0";
            }
//            if (med.get(i).hcv_medicine_duration != null) {
//                FDP[i].hcv_medicine_duration = sample.get(i).hcv_medicine_duration;
//            } else {
//                FDP[i].hcv_medicine_duration = 0;
//            }
//             FDP[i].is_cirrhotic_patient = sample.get(i).is_cirrhotic_patient;
//            FDP[i].Gender = vitalpatient.get(i).gender;
//            FDP[i].LastName = sample.get(i).lname;
//            FDP[i].pathentContactNo = med.get(i).contact_no_self;
            FDP[i].LeaderCNIC = med.get(i).getSelf_cnic();
            FDP[i].MrNo = med.get(i).getMrn_no();
            FDP[i].patientType = med.get(i).getPatient_stage();
            FDP[i].rstultType = med.get(i).getTest_type();
            FDP[i].pid = med.get(i).getPatient_id();
        }

        SearchResultDatapending[] myListData = new SearchResultDatapending[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultDatapending();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].MrNo);
            myListData[i].setGneder(FDP[i].LastName);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
            myListData[i].setPatienttype(FDP[i].patientType);
            myListData[i].setRsult_type(FDP[i].rstultType);
            myListData[i].setPid(FDP[i].pid);
//            myListData[i].setText1(FDP[i].text1);
//            myListData[i].setText2(FDP[i].text2);
            myListData[i].setHcvviralcount(FDP[i].hcvviralount);
            myListData[i].setHbvviralcount(FDP[i].hbvviralcount);
            myListData[i].setSample_id(FDP[i].sample_id);
            myListData[i].setIs_cirrhotic_patient(FDP[i].is_cirrhotic_patient);
            myListData[i].setHcv_medicine_duration(FDP[i].hcv_medicine_duration);
            myListData[i].setHbvviralcount(FDP[i].hbvviralcount);
            myListData[i].setHcvviralcount(FDP[i].hcvviralount);

        }
        SearchResultAdapterpendingTeatment adapter = new SearchResultAdapterpendingTeatment(myListData,getContext());
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
                if (pending.get(i).hbv_medicine_recommended != null) {
                    med.setHbv_medicine_recommended(pending.get(i).hbv_medicine_recommended);
                } else {
                    med.setHbv_medicine_recommended("0");
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

//                    userdataaa s = userdataaa.searchByPatientId(response.body().getData().getPatient_id());
//                    s.IsActive = 0;
//                    s.save();
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

    protected void launchGetReader()
    {
        Intent i = new Intent(getContext(), GetReaderActivity.class);
        i.putExtra("serial_number", m_sn);
        i.putExtra("device_name", m_deviceName);
        startActivityForResult(i, 1);
    }

    public void CheckDevice()

    {
        try {
            m_reader.Open(Reader.Priority.EXCLUSIVE);
            launchCaptureFingerprint();
            m_reader.Close();

        } catch (UareUException e1) {
            displayReaderNotFound();
        }

    }
    protected void launchCaptureFingerprint() {
        try {
            Intent i = new Intent(getContext(), VerificationActivity.class);
            i.putExtra("serial_number", m_sn);
            i.putExtra("device_name", m_deviceName);
            startActivityForResult(i, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)

    {

        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode)
        {

            case (1):

            {
                if (data == null)

                {
                    displayReaderNotFound();
                    return;
                }

                Globals.ClearLastBitmap();
                m_sn         = (String) data.getExtras().get("serial_number");
                m_deviceName = (String) data.getExtras().get("device_name");

                if ((m_deviceName != null) && !m_deviceName.isEmpty()) {

                    try

                    {
                        Context applContext = getContext();
                        m_reader = Globals.getInstance().getReader(m_deviceName, applContext);

                        {
                            PendingIntent mPermissionIntent;
                            mPermissionIntent = PendingIntent.getBroadcast(applContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
                            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
                            applContext.registerReceiver(mUsbReceiver, filter);

                            if (DPFPDDUsbHost.DPFPDDUsbCheckAndRequestPermissions(applContext, mPermissionIntent, m_deviceName)) {
                                CheckDevice();
                            }
                        }
                    }

                    catch (UareUException | DPFPDDUsbException e1)

                    {
                        displayReaderNotFound();
                    }

                }

                else

                {
                    displayReaderNotFound();
                }

                break;
            }

            case (2):

            {
                if (resultCode == RESULT_OK)

                {
                    byte[] decodedString = Base64.decode(Constants.FmdBase64, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    ma_iv_fingerprint.setImageBitmap(decodedByte);

                    if(allData.size() > 0)
                    {
                        Boolean isfingermatch = false;
                        for(int i=0;i<allData.size();i++) {
                            if (allData.get(i).getFinger_print2() != null && !allData.get(i).getFinger_print2().isEmpty()) {
                                {
                                    byte[] xml64Bytes = Base64.decode(allData.get(i).getFinger_print2(), Base64.DEFAULT);//allData.get(i).getFinger_fmd().getBytes(StandardCharsets.UTF_8);//Base64.decode(allData.get(i).getFinger_fmd(), Base64.DEFAULT);
                                    Fmd d_fmd = null;
                                    Fid d_fid = null;
                                    try {
                                        d_fid = UareUGlobal.GetImporter().ImportFid(xml64Bytes, Fid.Format.ANSI_381_2004);
                                        d_fmd = m_engine.CreateFmd(d_fid, ANSI_378_2004);

                                        try {

                                            if (d_fmd != null) {
                                                int m_score = m_engine.Compare(d_fmd, 0, m_engine.CreateFmd(Constants.cap_result, ANSI_378_2004), 0);
                                                if (m_score < (0x7FFFFFFF / 100000)) {
                                                    Toast.makeText(getContext(), "Finger Print Found!", Toast.LENGTH_LONG).show();
                                                    isfingermatch=true;
//                                                   List<userdataaa> patient ;
//                                                   patient = userdataaa.searchByCNICLeader(allData.get(i).getSelf_cnic());
//                                                   SetDataArrayy(patient);


                                                    int patientid =   allData.get(i).getPatient_id();

                                                    List<MedicineDisbursment_Table> allpending = new ArrayList<>();
                                                    allpending = MedicineDisbursment_Table.getall();
                                                    if(allpending.size()>0) {

                                                        Boolean sampleflag = false;

                                                        for (int a = 0; a < allpending.size(); a++) {

                                                            if(patientid == Integer.parseInt(allpending.get(a).getPatient_id())){

                                                                List<MedicineDisbursment_Table> samo = new ArrayList<>();
                                                                samo.add(allpending.get(a));
//                                                                SetDataArrayy(samo);
                                                                sampleflag = true;
                                                                Toast.makeText(getContext(), "patient found", Toast.LENGTH_SHORT).show();
                                                                break;
                                                            }else {
                                                                SearchResultDatavital[] myListData = new SearchResultDatavital[0];
                                                                SearchResultAdapterSample_status adapter = new SearchResultAdapterSample_status(myListData);
                                                                recyclerView.setAdapter(adapter);
                                                            }
                                                        }

                                                        if(!sampleflag){
                                                            Toast.makeText(getContext(), "patient not found", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else {
//                                                        Toast.makeText(getContext(), "sample not found", Toast.LENGTH_SHORT).show();
                                                    }

//                                                    SetDataArrayy(allData);
//                                                    allData.get(i).getFinger_print2();
                                                    break;
                                                } else {
                                                    SearchResultDatavital[] myListData = new SearchResultDatavital[0];
                                                    SearchResultAdapterSample_status adapter = new SearchResultAdapterSample_status(myListData);
                                                    recyclerView.setAdapter(adapter);
                                                }

                                            }

                                        } catch (UareUException e) {
                                            e.printStackTrace();
                                            Log.d("----", e.getMessage());
                                        }
                                    } catch (UareUException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                        if(!isfingermatch){
                            Toast.makeText(getContext(), "not found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    Log.d("---d---",Constants.Fmd + "");
                }

                if (resultCode == RESULT_CANCELED)

                {
                    Toast.makeText(getContext(), "Operation Canceled", Toast.LENGTH_SHORT).show();
                }


            }

            break;

        }

    }

    private void displayReaderNotFound()

    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        alertDialogBuilder.setTitle("Reader Not Found");

        alertDialogBuilder
                .setMessage("Plug in a reader and try again.")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        (dialog, id) -> {
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver()

    {
        public void onReceive(Context context, Intent intent)
        {

            String action = intent.getAction();

            if (ACTION_USB_PERMISSION.equals(action))

            {
                synchronized (this)

                {
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false))
                    {
                        if(device != null)
                        {
                            //call method to set up device communication
                            CheckDevice();
                        }
                    }
                    else
                    {
                        // setButtonsEnabled(false);
                    }
                }
            }
        }
    };






}