package com.example.hcp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
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

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.activities.MainActivity;

import com.example.hcp.models.Parcables.FamilyDataParceable;
import com.example.hcp.models.Parcables.PatientDataParceable;
import com.example.hcp.models.Users.UserResponse;
import com.example.hcp.models.hcp.AddVitalResponse;
import com.example.hcp.models.hcp.AssessmentResponse;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.SampleRequest;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.addAssessmentRequest;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addPatientRequest;
import com.example.hcp.models.hcp.addPatientResponse;
import com.example.hcp.models.hcp.addVitalRequest;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.medicineRequest;
import com.example.hcp.models.hcp.medicineResponse;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.sampleResponse;
import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    FragmentManager fragmentManager;
    Spinner SearchOptions;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    EditText OptionValue;
    Button Search, Register, export_db;
    //   private ProgressDialog dialog;
    ImageView scanner;
    LinearLayout sync_data;
    TextView total_record;
    int patientssubmitcount = 0;
    int vitalsubmitcount = 0;
    int assessmentsubmitcount = 0;
    int samplesubmitcount = 0;
    int pendingsubmitcount = 0;
    int syncedpatients = 0, syncedvitals = 0, syncedassessment = 0, syncedpending = 0;
    int CurremtIndex = 0;

    List<addPatientModel> paitents;
    List<addvitalll> vitals;
    List<Assessmentt> assessmentts;
    List<Samplee> sampless;
    List<medicinee> pending;

    public int totalSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_family_fragment, container, false);

        fragmentManager = getFragmentManager();
        total_record = view.findViewById(R.id.total__sync_record);
        SearchOptions = view.findViewById(R.id.etSearchOption);
        OptionValue = view.findViewById(R.id.etSearchVal);
        Search = view.findViewById(R.id.btnSearch);
        Register = view.findViewById(R.id.btnReg);
        scanner = view.findViewById(R.id.scanner);
        SelectedOptionIndex = 0;
        sync_data = view.findViewById(R.id.sync_data);
        SelectedOption = "";
        SelectedOptionVal = "";

        export_db = view.findViewById(R.id.export_db);

        SetSearchOptions();

        totalSYncREcord();

        export_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();
//                generateNoteOnSD(getContext(),"ErrorLog","");
            }
        });

        Search.setOnClickListener(
                v -> Search()
        );

        Register.setOnClickListener(
                v -> LoadRegistration()
        );

        sync_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = Prefs.getString(Constants.USERNAME, "");
                String passwrod = Prefs.getString(Constants.PASSWORD, "");

//                Login(username,passwrod);
                RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1500);
                rotate.setRepeatCount(2);
                rotate.setInterpolator(new LinearInterpolator());
                scanner.startAnimation(rotate);

                SyncRecord();

//                submitmedicine();
            }
        });

        return view;
    }


    void Login(String username, String password) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Getting Token please wait...");
        dialog.show();
        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
        Call<UserResponse> call = service.Login(username, password, "password");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                dialog.dismiss();
                if (response.code() == 200) {

                    Prefs.edit().putString(Constants.USERNAME, username).apply();
                    Prefs.edit().putString(Constants.PASSWORD, password).apply();

                    UserResponse UR = response.body();
                    new SharedPref(getActivity()).SaveCredentials(UR.getAccessToken(), UR.getUserName(), null, null, UR.getRole(), null, null, UR.getEmail(), UR.getFullName(), UR.getUserId(), UR.getGUIDNew());
//                    SyncRecord();
                } else {
                    Toast.makeText(getContext(), "Invalid Credentials .. ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong !" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    void Login(String username, String password) {
//        ProgressDialog dialog = new ProgressDialog(getContext());
//        dialog.setMessage("برائے مہربانی انتظار کریں !");
//        dialog.setCancelable(false);
//        dialog.show();
//        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
//        Call<UserResponse> call = service.Login(username, password, "password");
//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//
//                if (response.code() == 200) {
//                    dialog.dismiss();
//                    SyncRecord();
//                } else {
//                    dialog.dismiss();
//                    Toast.makeText(getContext(), "Invalid Credentials .. ", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                dialog.dismiss();
//                Toast.makeText(getContext(), "Something went wrong !" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    void LoadRegistration() {
        Fragment SRFragment = new patientRegistration();

        if (SRFragment != null) {
            FragmentTransaction transaction = MainActivity.mainActivity.getSupportFragmentManager().beginTransaction();

            try {
                transaction.replace(R.id.content_frame, SRFragment, "FamilyRegister").addToBackStack(null).commit();
            } catch (IllegalStateException ignored) {
            }
        }

    }

    void Search() {

        SelectedOptionVal = OptionValue.getText().toString();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
            List<addPatientModel> leaders;
            switch (SelectedOptionIndex) {
                case 1:
//                    leaders = userdataaa.searchByMRNOLeader(SelectedOptionVal);
//                    if (leaders.size() > 0) {
//                        SetDataArrayy(leaders);
//                    } else {
//                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
//                    }
//                    break;
                case 2:
                    leaders = addPatientModel.searchByCNICLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {
                        SetDataArrayy(leaders);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 3:
                    leaders = addPatientModel.searchBynameLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {
                        SetDataArrayy(leaders);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 4:
//                    leaders = userdataaa.searchByPhoneLeader(SelectedOptionVal);
//                    if (leaders.size() > 0) {
//                        SetDataArrayy(leaders);
//                    } else {
//                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
//                    }
//                    break;

            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
    }


    void SetSearchOptions() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select Option");
        categoriesEng.add("Mr_no");
        categoriesEng.add("CNIC");
        categoriesEng.add("Full_Name");
        categoriesEng.add("Contact_No");

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

    public void SetDataArrayy(List<addPatientModel> SFR) {
        PatientDataParceable[] FDP = new PatientDataParceable[SFR.size()];
        for (int i = 0; i < FDP.length; i++) {
            FDP[i] = new PatientDataParceable();
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

            if (SFR.get(i).patient_name != null) {
                FDP[i].PatientName = SFR.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
            FDP[i].contactNo = SFR.get(i).contact_no_self;
            FDP[i].LeaderCNIC = SFR.get(i).self_cnic;
            FDP[i].MrNo = SFR.get(i).mrn_no;
        }

        Fragment SRFragment = new SearchResultFragment();

        Bundle args = new Bundle();
        args.putSerializable("FDP", FDP);

        if (SRFragment != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            SRFragment.setArguments(args);
            try {
                transaction.add(R.id.content_frame, SRFragment, "searchFragment").addToBackStack(null).commit();
            } catch (IllegalStateException ignored) {
            }
        }

    }


    public void totalSYncREcord() {

        List<addPatientModel> patient = addPatientModel.searchBySync();
        List<addvitalll> vitals = addvitalll.searchBySync();
        List<Assessmentt> assessments = Assessmentt.searchBySync();
        List<Samplee> sample = Samplee.searchBySync();
//        List<medicinee> medicine = medicinee.searchBySync();
        totalSize = patient.size() + vitals.size() + assessments.size() + sample.size();
        total_record.setText(totalSize + "");

    }

    //
    public void SyncRecord() {


        patientssubmitcount = 0;
        vitalsubmitcount = 0;
        assessmentsubmitcount = 0;
        samplesubmitcount = 0;
//        pendingsubmitcount = 0;


        paitents = addPatientModel.searchBySync();
        patientssubmitcount = paitents.size();


//        vitalsubmitcount= vitals.size();


//        assessmentsubmitcount=assessmentts.size();


//        samplesubmitcount = sampless.size();

//        pending = medicinee.searchBySync();
//        pendingsubmitcount = pending.size();

        if (paitents.size() > 0) {
            submitePatients();
        } else {
            submitvitalData();
            submitAssessment();
            submitSamples();
        }
//       if(vitals.size()>0){
//            submitvitalData();
//        }
//       if(assessmentts.size()>0){
//            submitAssessment();
//        }
//       if(sampless.size()>0){
//            submitSamples();
//        }
//      else  {
//            Toast.makeText(getContext(), "All Data Synced", Toast.LENGTH_SHORT).show();
//        }
    }

    void SubRes(addPatientModel pat)

    {

    }

    public void submitePatients()

    {

//        if (paitents.size() > 0 && paitents.size() > CurremtIndex)
//
//        {

            for (int i = 0; i < paitents.size(); i++)

            {
                addPatientRequest fmb = new addPatientRequest();
                if (paitents.get(i).getPatient_id() != null) {
                    fmb.setPatient_id(paitents.get(i).getPatient_id());
                }
                if (paitents.get(i).getPatient_name() != null) {
                    fmb.setPatient_name(paitents.get(i).getPatient_name());
                } else {
                    fmb.setPatient_name("");

                }
                if (paitents.get(i).getLname() != null) {
                    fmb.setLname(paitents.get(i).getLname());
                } else {
                    fmb.setLname("");

                }
                if (paitents.get(i).getFather_name() != null) {
                    fmb.setFather_name(paitents.get(i).getFather_name());
                } else {
                    fmb.setFather_name("");

                }
                if (paitents.get(i).getPatient_age() != null) {
                    fmb.setPatient_age(paitents.get(i).getPatient_age());
                } else {
                    fmb.setPatient_age(0);

                }
                if (paitents.get(i).getPatient_dob() != null) {
                    fmb.setPatient_dob(paitents.get(i).getPatient_dob());
                } else {
                    fmb.setPatient_dob("");

                }
                if (paitents.get(i).getGender() != null) {
                    fmb.setGender(paitents.get(i).getGender());
                } else {
                    fmb.setGender(0);

                }
                if (paitents.get(i).getSelf_cnic() != null) {
                    fmb.setSelf_cnic(paitents.get(i).getSelf_cnic());
                } else {
                    fmb.setSelf_cnic("");

                }
                if (paitents.get(i).getContact_no_self() != null) {
                    fmb.setContact_no_self(paitents.get(i).getContact_no_self());
                } else {
                    fmb.setContact_no_self("");

                }
                if (paitents.get(i).getAddress() != null) {
                    fmb.setAddress(paitents.get(i).getAddress());
                } else {
                    fmb.setAddress("");

                }
                if (paitents.get(i).getMarital_status() != null) {
                    fmb.setMarital_status(paitents.get(i).getMarital_status());
                } else {
                    fmb.setMarital_status("");

                }
                if (paitents.get(i).getOccupation() != null) {
                    fmb.setOccupation(paitents.get(i).getOccupation());
                } else {
                    fmb.setOccupation("");

                }
                if (paitents.get(i).getQualification() != null) {
                    fmb.setQualification(paitents.get(i).getQualification());
                } else {
                    fmb.setQualification("");

                }
                if (paitents.get(i).getPatient_age_80() != null) {
                    fmb.setPatient_age_80(paitents.get(i).getPatient_age_80());
                } else {
                    fmb.setPatient_age_80("");

                }
                if (paitents.get(i).getPrevious_hbv() != null) {
                    fmb.setPrevious_hbv(paitents.get(i).getPrevious_hbv());
                } else {
                    fmb.setPrevious_hbv("");

                }
                if (paitents.get(i).getPrevious_hcv() != null) {
                    fmb.setPrevious_hcv(paitents.get(i).getPrevious_hcv());
                } else {
                    fmb.setPrevious_hcv("");

                }

//            fmb.setIsActive(leaders.get(i).getIsActive());
                if (paitents.get(i).getPcr_confirmation_hbv() != null) {
                    fmb.setPcr_confirmation_hbv(paitents.get(i).getPcr_confirmation_hbv());
                } else {
                    fmb.setPcr_confirmation_hbv("");

                }

                if (paitents.get(i).getPcr_confirmation_hcv() != null) {
                    fmb.setPcr_confirmation_hcv(paitents.get(i).getPcr_confirmation_hcv());
                } else {
                    fmb.setPcr_confirmation_hcv("");

                }

                if (paitents.get(i).getDivision() != null) {
                    fmb.setDivision(paitents.get(i).getDivision());
                } else {
                    fmb.setDivision(0);

                }

                if (paitents.get(i).getDistrict() != null) {
                    fmb.setDistrict(paitents.get(i).getDistrict());
                } else {
                    fmb.setDistrict(0);

                }

                if (paitents.get(i).getTehsil() != null) {
                    fmb.setTehsil(paitents.get(i).getTehsil());
                } else {
                    fmb.setTehsil(0);

                }

                if (paitents.get(i).getHospital() != null) {
                    fmb.setHospital(paitents.get(i).getHospital());
                } else {
                    fmb.setHospital(0);

                }
                if (paitents.get(i).getIdentifier() != null) {
                    fmb.setIdentifier(paitents.get(i).getIdentifier());
                } else {
                    fmb.setIdentifier("");

                }

                if (paitents.get(i).getUser_id() != null) {
                    fmb.setUser_id(paitents.get(i).getUser_id());
                } else {
                    fmb.setUser_id("");
                }

                if (paitents.get(i).getHospital_id() != null) {
                    fmb.setHospital_id(paitents.get(i).getHospital_id());
                } else {
                    fmb.setHospital_id("");

                }
                if (paitents.get(i).getPatient_type() != null) {
                    fmb.setPatient_type(paitents.get(i).getPatient_type());
                } else {
                    fmb.setPatient_type("");

                }
                if (paitents.get(i).getId() != null) {
                    fmb.setMobile_id(paitents.get(i).getId());
                } else {
                    fmb.setMobile_id(0L);
                }

//                List<addPatientModel> pati = new ArrayList<addPatientModel>();
//                pati.add(fmb);


//                final Handler handler = new Handler(Looper.getMainLooper());
//                int finalI = i;
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }, 3300);

                SubmitLeader(fmb, paitents.get(i), paitents.size(), i);


//               SubmitLeader(fmb);
            }

        }
//    }

    public void SubmitLeader(addPatientRequest currentMember, addPatientModel addPatientModel, int totalPatient, int currentPatient) {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient please wait . . ");
        dialog.show();

//        Gson gson = new Gson();
//        String json = gson.toJson(currentMember);
//
//        Log.d("CurrentObject",json);

//        String Request = new Gson().toJson(FMB);

//        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
//        Call<GetFamilyMemberResponse> call = service.AddFamilyAllMember(new SharedPref(getContext()).GetToken(), FMB);
        Call<addPatientResponse> call = RetrofitClient
                .getInstance().getApi().savePatient(currentMember);
        call.enqueue(new Callback<addPatientResponse>() {
            @Override
            public void onResponse(Call<addPatientResponse> call, Response<addPatientResponse> response) {
                dialog.dismiss();


                if (response.code() == 200) {


                    ActiveAndroid.beginTransaction();
                    try {

//                            List<addPatientModel> fl = addPatientModel.searchBySync();
//                            for (int i = 0; i < fl.size(); i++) {
//
//                                addPatientModel mod = addPatientModel.load(addPatientModel.class, response.body().getData().getMobile_id());
//                                mod.IsSync = 1;
//                                mod.mrn_no = response.body().getData().getMrn_no();
//                                mod.patient_id = response.body().getData().getPatient_id();
//                                mod.save();
//                            }
                        addPatientModel.IsSync = 1;
                        addPatientModel.mrn_no = response.body().getData().getMrn_no();
                        addPatientModel.patient_id = response.body().getData().getPatient_id();
                        addPatientModel.save();


                        addvitalll vtl = addvitalll.searchBypid(response.body().getData().getMobile_id());
                        if (vtl != null) {
                            vtl.pid = response.body().getData().getPatient_id();
                            vtl.save();
                        } else {
                            Toast.makeText(getContext(), "vital not found", Toast.LENGTH_SHORT).show();
                        }


                        Assessmentt assess = Assessmentt.searchBypid(response.body().getData().getMobile_id());
//                                   if(assess.patient_id == response.body().getData().getPatient_id()) {
                        if (assess != null) {
                            assess.patient_id = response.body().getData().getPatient_id();
                            assess.save();
                        } else {
                            Toast.makeText(getContext(), "Assessment not found", Toast.LENGTH_SHORT).show();
                        }


                        Samplee ss = Samplee.searchBypid(response.body().getData().getMobile_id());
                        if (ss != null) {
                            ss.pid = response.body().getData().getPatient_id();
                            ss.save();
                        } else {
                            Toast.makeText(getContext(), "sample not found", Toast.LENGTH_SHORT).show();
                        }


                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }

                    if (currentPatient + 1 == totalPatient) {
                        submitvitalData();
                        submitAssessment();
                        submitSamples();
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message().toString(), Toast.LENGTH_SHORT).show();
                }


//                        syncedpatients = syncedpatients + 1;
//                        if (syncedpatients == patientssubmitcount) {
//                            if(response.code()==200) {
//                                submitvitalData();
//                            }
//                        }
//                    } catch (Exception error) {
//                        syncedleaders = syncedleaders + 1;
//                        if (syncedleaders == leadersubmitcount) {
////                            submitLmpData();
//                        }
//                    }
//
//
                totalSYncREcord();
//                }else {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        String erroMessage= jObjError.getString("ExceptionMessage");
//                        if(erroMessage!=null){
////                            String error= Prefs.getString(Constants.ErrorLog,"");
//                            UtilsLocal.showErrorDialog(getContext(),erroMessage);
////                            Prefs.edit().putString(Constants.ErrorLog,error+"/////// \n"+erroMessage).apply();
//                        }
//
//                    } catch (Exception e) {
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }

            }

            @Override
            public void onFailure(Call<addPatientResponse> call, Throwable t) {

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

    private void submitvitalData() {
        vitals = addvitalll.searchBySync();
        if (vitals.size() > 0) {

            for (int i = 0; i < vitals.size(); i++) {
                addVitalRequest fmb = new addVitalRequest();
                if (vitals.get(i).pid != null) {
                    fmb.setPid(vitals.get(i).pid);
                }
                if (vitals.get(i).getTemperature() != null) {
                    fmb.setTemperature(vitals.get(i).getTemperature());
                } else {
                    fmb.setTemperature(0.0);

                }
                if (vitals.get(i).getPulse() != null) {
                    fmb.setPulse(vitals.get(i).getPulse());
                } else {
                    fmb.setPulse(0);

                }
                if (vitals.get(i).getBp_systolic() != null) {
                    fmb.setBp_systolic((int) Math.round(vitals.get(i).getBp_systolic()));
                } else {
                    fmb.setBp_systolic(0);

                }
                if (vitals.get(i).getBp_diastolic() != null) {
                    fmb.setBp_diastolic((int) Math.round(vitals.get(i).getBp_diastolic()));
                } else {
                    fmb.setBp_diastolic(0);

                }
                if (vitals.get(i).getHeight() != null) {
                    fmb.setHeight(vitals.get(i).getHeight());
                } else {
                    fmb.setHeight(0.0);

                }
                if (vitals.get(i).getWeight() != null) {
                    fmb.setWeight(vitals.get(i).getWeight());
                } else {
                    fmb.setWeight(0.0);

                }
                if (vitals.get(i).getUser_id() != null) {
                    fmb.setUser_id(vitals.get(i).getUser_id());
                } else {
                    fmb.setUser_id(0);

                }
                if (vitals.get(i).getId() != null) {
                    fmb.setMobile_id(vitals.get(i).getId());
                } else {
                    fmb.setMobile_id(0L);
                }

//                List<addPatientModel> fl = addPatientModel.getall();
//
//                vitals.get(i).pid = fl.get(i).getPatient_id();

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


                        addvitalll vital = addvitalll.searchBypid(fmb.getPid());
                        vital.IsSync = 1;
                        vital.save();
//                        List<addvitalll> fl = addvitalll.getall();
//                        for (int i = 0; i < fl.size(); i++) {
//
//                            addvitalll mod = addvitalll.load(addvitalll.class, response.body().getData().getMobile_id());
//                            mod.IsSync = 1;
//                            mod.save();
//
//                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }

//                    syncedvitals = syncedvitals + 1;
//                    if (syncedvitals == vitalsubmitcount) {
//                        submitAssessment();
//                    }


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

    private void submitAssessment() {
        assessmentts = Assessmentt.searchBySync();
        if (assessmentts.size() > 0) {

            for (int i = 0; i < assessmentts.size(); i++) {
                addAssessmentRequest asssess = new addAssessmentRequest();
                if (assessmentts.get(i).patient_id != null) {
                    asssess.setPatient_id(assessmentts.get(i).patient_id);
                }
                if (assessmentts.get(i).getUser_hospital() != null) {
                    asssess.setHospital_id(assessmentts.get(i).getUser_hospital());
                } else {
                    asssess.setHospital_id("N");
                }
                if (assessmentts.get(i).getUser_id() != null) {
                    asssess.setUser_id(assessmentts.get(i).getUser_id());
                } else {
                    asssess.setUser_id("0");

                }
                if (assessmentts.get(i).getCreated() != null) {
                    asssess.setCreated(assessmentts.get(i).getCreated());
                } else {
                    asssess.setCreated(0);

                }
                if (assessmentts.get(i).getUpdated() != null) {
                    asssess.setUpdated(assessmentts.get(i).getUpdated());
                } else {
                    asssess.setUpdated(0);

                }
                if (assessmentts.get(i).getFrequent_therapeutic_injections() != null) {
                    asssess.setFrequent_therapeutic_injections(assessmentts.get(i).getFrequent_therapeutic_injections());
                } else {
                    asssess.setFrequent_therapeutic_injections("N");

                }
                if (assessmentts.get(i).getNote() != null) {
                    asssess.setNote(assessmentts.get(i).getNote());
                } else {
                    asssess.setNote("0.0");

                }

                if (assessmentts.get(i).getConfirmed_case_of_stds() != null) {
                    asssess.setConfirmed_case_of_stds(assessmentts.get(i).getConfirmed_case_of_stds());
                } else {
                    asssess.setConfirmed_case_of_stds("N");
                }
                if (assessmentts.get(i).getInvasive_medical_and_surgical_intervention() != null) {
                    asssess.setInvasive_medical_and_surgical_intervention(assessmentts.get(i).getInvasive_medical_and_surgical_intervention());
                } else {
                    asssess.setInvasive_medical_and_surgical_intervention("N");

                }
                if (assessmentts.get(i).getSurgery_type() != null) {
                    asssess.setSurgery_type(assessmentts.get(i).getSurgery_type());
                } else {
                    asssess.setSurgery_type("N");
                }
                if (assessmentts.get(i).getSurgery_when() != null) {
                    asssess.setSurgery_when(assessmentts.get(i).getSurgery_when());
                } else {
                    asssess.setSurgery_when("N");
                }
                if (assessmentts.get(i).getClose_contact_of_a_known_case_of_hcv_hbv() != null) {
                    asssess.setClose_contact_of_a_known_case_of_hcv_hbv(assessmentts.get(i).getClose_contact_of_a_known_case_of_hcv_hbv());
                } else {
                    asssess.setClose_contact_of_a_known_case_of_hcv_hbv("N");
                }
                if (assessmentts.get(i).getClose_contact_is_on_treatment() != null) {
                    asssess.setClose_contact_is_on_treatment(assessmentts.get(i).getClose_contact_is_on_treatment());
                } else {
                    asssess.setClose_contact_is_on_treatment("N");
                }
                if (assessmentts.get(i).getBlood_transfusion() != null) {
                    asssess.setBlood_transfusion(assessmentts.get(i).getBlood_transfusion());
                } else {
                    asssess.setBlood_transfusion("N");
                }
                if (assessmentts.get(i).getBlood_bank() != null) {
                    asssess.setBlood_bank(assessmentts.get(i).getBlood_bank());
                } else {
                    asssess.setBlood_bank("N");
                }
                if (assessmentts.get(i).getConfirmed_hiv_positive_persons() != null) {
                    asssess.setConfirmed_hiv_positive_persons(assessmentts.get(i).getConfirmed_hiv_positive_persons());
                } else {
                    asssess.setConfirmed_hiv_positive_persons("N");
                }
                if (assessmentts.get(i).getEver_been_hospitalized() != null) {
                    asssess.setEver_been_hospitalized(assessmentts.get(i).getEver_been_hospitalized());
                } else {
                    asssess.setEver_been_hospitalized("N");
                }
                if (assessmentts.get(i).getHospitalization_within_last_2_years() != null) {
                    asssess.setHospitalization_within_last_2_years(assessmentts.get(i).getHospitalization_within_last_2_years());
                } else {
                    asssess.setHospitalization_within_last_2_years("N");
                }
                if (assessmentts.get(i).getIndividuals_with_tattooing_ear_nose_piercing() != null) {
                    asssess.setIndividuals_with_tattooing_ear_nose_piercing(assessmentts.get(i).getIndividuals_with_tattooing_ear_nose_piercing());
                } else {
                    asssess.setIndividuals_with_tattooing_ear_nose_piercing("N");
                }
                if (assessmentts.get(i).getInjectable_drug_user() != null) {
                    asssess.setInjectable_drug_user(assessmentts.get(i).getInjectable_drug_user());
                } else {
                    asssess.setInjectable_drug_user("N");
                }
                if (assessmentts.get(i).getDental_intervention() != null) {
                    asssess.setDental_intervention(assessmentts.get(i).getDental_intervention());
                } else {
                    asssess.setDental_intervention("N");
                }
                if (assessmentts.get(i).getDental_clinic() != null) {
                    asssess.setDental_clinic(assessmentts.get(i).getDental_clinic());
                } else {
                    asssess.setDental_clinic("N");
                }
                if (assessmentts.get(i).getHistory_of_multiple_sex_partners() != null) {
                    asssess.setHistory_of_multiple_sex_partners(assessmentts.get(i).getHistory_of_multiple_sex_partners());
                } else {
                    asssess.setHistory_of_multiple_sex_partners("N");
                }
                if (assessmentts.get(i).getTruck_driver_or_transgender() != null) {
                    asssess.setTruck_driver_or_transgender(assessmentts.get(i).getTruck_driver_or_transgender());
                } else {
                    asssess.setTruck_driver_or_transgender("N");
                }
                if (assessmentts.get(i).getDark_colored_urine() != null) {
                    asssess.setDark_colored_urine(assessmentts.get(i).getDark_colored_urine());
                } else {
                    asssess.setDark_colored_urine("N");
                }
                if (assessmentts.get(i).getLoss_of_appetite() != null) {
                    asssess.setLoss_of_appetite(assessmentts.get(i).getLoss_of_appetite());
                } else {
                    asssess.setLoss_of_appetite("N");
                }
                if (assessmentts.get(i).getLight_colored_faeces() != null) {
                    asssess.setLight_colored_faeces(assessmentts.get(i).getLight_colored_faeces());
                } else {
                    asssess.setLight_colored_faeces("N");
                }
                if (assessmentts.get(i).getFatigue() != null) {
                    asssess.setFatigue(assessmentts.get(i).getFatigue());
                } else {
                    asssess.setFatigue("N");
                }
                if (assessmentts.get(i).getMuscle_pain() != null) {
                    asssess.setMuscle_pain(assessmentts.get(i).getMuscle_pain());
                } else {
                    asssess.setMuscle_pain("N");
                }
                if (assessmentts.get(i).getNausea() != null) {
                    asssess.setNausea(assessmentts.get(i).getNausea());
                } else {
                    asssess.setNausea("N");
                }
                if (assessmentts.get(i).getStomach_ache() != null) {
                    asssess.setStomach_ache(assessmentts.get(i).getStomach_ache());
                } else {
                    asssess.setStomach_ache("N");
                }
                if (assessmentts.get(i).getRight_upper_quadrant_tenderness() != null) {
                    asssess.setRight_upper_quadrant_tenderness(assessmentts.get(i).getRight_upper_quadrant_tenderness());
                } else {
                    asssess.setRight_upper_quadrant_tenderness("N");
                }
                if (assessmentts.get(i).getGastric_irritation_burning() != null) {
                    asssess.setGastric_irritation_burning(assessmentts.get(i).getGastric_irritation_burning());
                } else {
                    asssess.setGastric_irritation_burning("N");
                }
                if (assessmentts.get(i).getUnusual_urethral_discharge() != null) {
                    asssess.setUnusual_urethral_discharge(assessmentts.get(i).getUnusual_urethral_discharge());
                } else {
                    asssess.setUnusual_urethral_discharge("N");
                }
                if (assessmentts.get(i).getEar_nose_pirecing() != null) {
                    asssess.setEar_nose_pirecing(assessmentts.get(i).getEar_nose_pirecing());
                } else {
                    asssess.setEar_nose_pirecing("N");
                }
                if (assessmentts.get(i).getTransgender() != null) {
                    asssess.setTransgender(assessmentts.get(i).getTransgender());
                } else {
                    asssess.setTransgender("N");
                }
                if (assessmentts.get(i).getSharing_toothbrush() != null) {
                    asssess.setSharing_toothbrush(assessmentts.get(i).getSharing_toothbrush());
                } else {
                    asssess.setSharing_toothbrush("N");
                }
                if (assessmentts.get(i).getSharing_hair_comb() != null) {
                    asssess.setSharing_hair_comb(assessmentts.get(i).getSharing_hair_comb());
                } else {
                    asssess.setSharing_hair_comb("N");
                }
                if (assessmentts.get(i).getRapid_testing() != null) {
                    asssess.setRapid_testing(assessmentts.get(i).getRapid_testing());
                } else {
                    asssess.setRapid_testing("N");
                }
                if (assessmentts.get(i).getIs_hcv_test() != null) {
                    asssess.setHcv(assessmentts.get(i).getIs_hcv_test());
                } else {
                    asssess.setHcv("N");
                }
                if (assessmentts.get(i).getIs_hbv_test() != null) {
                    asssess.setHbv(assessmentts.get(i).getIs_hbv_test());
                } else {
                    asssess.setHbv("N");
                }
                if (assessmentts.get(i).getVaccination() != null) {
                    asssess.setVaccination(assessmentts.get(i).getVaccination());
                } else {
                    asssess.setVaccination("N");
                }
                if (assessmentts.get(i).getPcr_option() != null) {
                    asssess.setPcr_option(assessmentts.get(i).getPcr_option());
                } else {
                    asssess.setPcr_option("N");
                }
                if (assessmentts.get(i).getPcr() != null) {
                    asssess.setPcr(assessmentts.get(i).getPcr());
                } else {
                    asssess.setPcr("N");
                }
                if (assessmentts.get(i).getId() != null) {
                    asssess.setMobile_id(assessmentts.get(i).getId());
                } else {
                    asssess.setMobile_id(0L);
                }
                if (assessmentts.get(i).getCounselling() != null) {
                    asssess.setCounselling(assessmentts.get(i).getCounselling());
                } else {
                    asssess.setCounselling("N");
                }
                if (assessmentts.get(i).getIs_new_patient() != null) {
                    asssess.setIs_new_patient(assessmentts.get(i).getIs_new_patient());
                } else {
                    asssess.setIs_new_patient("true");
                }


//                List<addPatientModel> fl = addPatientModel.getall();
//
//                assessmentts.get(i).patient_id = fl.get(i).getPatient_id();

                SubmitAssessment(asssess);

            }

        }

    }

    private void SubmitAssessment(addAssessmentRequest asssess) {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<AssessmentResponse> call = RetrofitClient
                .getInstance().getApi().saveAssessment(asssess);
        call.enqueue(new Callback<AssessmentResponse>() {
            @Override
            public void onResponse(Call<AssessmentResponse> call, Response<AssessmentResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {


                    ActiveAndroid.beginTransaction();

                    try {
                        Assessmentt mo = Assessmentt.searchBypid(asssess.getPatient_id());
                        mo.IsSync = 1;
                        mo.save();
//                        Assessmentt assessmentt= Assessmentt.searchBypid()
//                        List<Assessmentt> flo = Assessmentt.getall();
//                        for (int i = 0; i < flo.size(); i++) {
//
//                            Assessmentt mo = Assessmentt.load(Assessmentt.class, response.body().getData().getMobile_id());
//                            mo.IsSync = 1;
//                            mo.save();
//
//                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }

//                    syncedassessment = syncedassessment + 1;
//                    if (syncedassessment == assessmentsubmitcount) {
//                        submitSamples();
//                    }


//                    deleteformvitalList(response.body().getData().getMobile_id());
                }

                totalSYncREcord();


            }

            @Override
            public void onFailure(Call<AssessmentResponse> call, Throwable t) {

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

    private void submitSamples() {
        sampless = Samplee.searchBySync();
        if (sampless.size() > 0) {

            for (int i = 0; i < sampless.size(); i++) {
                SampleRequest smp = new SampleRequest();
                if (sampless.get(i).pid != null) {
                    smp.setPid(sampless.get(i).pid);
                }
                if (sampless.get(i).getHospital_id() != null) {
                    smp.setHospital_id(sampless.get(i).getHospital_id());
                } else {
                    smp.setHospital_id(0);

                }
                if (sampless.get(i).getUser_id() != null) {
                    smp.setUser_id(sampless.get(i).getUser_id());
                } else {
                    smp.setUser_id(0);

                }
                if (sampless.get(i).getSample_no() != null) {
                    smp.setSample_no(sampless.get(i).getSample_no());
                } else {
                    smp.setSample_no("0");

                }

                if (sampless.get(i).getId() != null) {
                    smp.setMobile_id(sampless.get(i).getId());
                } else {
                    smp.setMobile_id(0L);
                }

//                List<addPatientModel> fli = addPatientModel.getall();
//
//                sampless.get(i).pid = fli.get(i).getPatient_id();

                SubmitSamples(smp);

            }

        }


    }

    private void SubmitSamples(SampleRequest smp) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<sampleResponse> call = RetrofitClient
                .getInstance().getApi().saveSamples(smp);
        call.enqueue(new Callback<sampleResponse>() {
            @Override
            public void onResponse(Call<sampleResponse> call, Response<sampleResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {


                    ActiveAndroid.beginTransaction();
                    try {
                        Samplee mo = Samplee.searchBypid(smp.getPid());
                        mo.IsSync = 1;
                        mo.save();
//                        List<Samplee> s = Samplee.getall();
//                        for (int i = 0; i < s.size(); i++) {
//
//                            Samplee mo = Samplee.load(Samplee.class, response.body().getData().getMobile_id());
//                            mo.IsSync = 1;
//                            mo.save();
//
//                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }

//                    syncedpending = syncedpending + 1;
//                    if (syncedpending == pendingsubmitcount) {
//                        submitmedicine();
//                    }


//                    deleteformvitalList(response.body().getData().getMobile_id());
                }

                totalSYncREcord();


            }

            @Override
            public void onFailure(Call<sampleResponse> call, Throwable t) {

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

//    private void submitmedicine() {
//        pending = medicinee.searchBySync();
//        pendingsubmitcount = pending.size();
//        if (pending.size() > 0) {
//
//            for (int i = 0; i < pending.size(); i++) {
//                medicineRequest med = new medicineRequest();
//                if (pending.get(i).invs_status != null) {
//                    med.setInvs_status(pending.get(i).invs_status);
//                }else {
//                    med.setInvs_status("");
//                }
//                if (pending.get(i).result_type != null) {
//                    med.setResult_type(pending.get(i).result_type);
//                } else {
//                    med.setResult_type("");
//                }
//                if (pending.get(i).hemoglobin != null) {
//                    med.setHemoglobin(pending.get(i).hemoglobin);
//                } else {
//                    med.setUser_id(0);
//                }
//                if (pending.get(i).ast != null) {
//                    med.setAst(pending.get(i).ast);
//                } else {
//                    med.setAst("0");
//                }
//
//                if (pending.get(i).alt != null) {
//                    med.setAlt(pending.get(i).alt);
//                } else {
//                    med.setMobile_id(0);
//                }
//
//                if (pending.get(i).platelet != null) {
//                    med.setPlatelet(pending.get(i).platelet);
//                } else {
//                    med.setPlatelet("");
//                }
//
//                if (pending.get(i).tlc != null) {
//                    med.setTlc(pending.get(i).tlc);
//                } else {
//                    med.setTlc("");
//                }
//
//                if (pending.get(i).apri != null) {
//                    med.setApri(pending.get(i).apri);
//                } else {
//                    med.setApri("");
//                }
//
//                if (pending.get(i).viral_count != null) {
//                    med.setViral_count(pending.get(i).viral_count);
//                } else {
//                    med.setViral_count("0");
//                }
//
//                if (pending.get(i).pcr_result != null) {
//                    med.setPcr_result(pending.get(i).pcr_result);
//                } else {
//                    med.setPcr_result("0");
//                }
//
//                if (pending.get(i).sample_id != null) {
//                    med.setSample_id(pending.get(i).sample_id);
//                } else {
//                    med.setSample_id("");
//                }
//
//                if (pending.get(i).lab_name != null) {
//                    med.setLab_name(pending.get(i).lab_name);
//                } else {
//                    med.setLab_name("0");
//                }
//
//                if (pending.get(i).other_lab_name != null) {
//                    med.setOther_lab_name(pending.get(i).other_lab_name);
//                } else {
//                    med.setOther_lab_name("0");
//                }
//
//                if (pending.get(i).id != null) {
//                    med.setId(pending.get(i).id);
//                } else {
//                    med.setId("0");
//                }
//
//                if (pending.get(i).baseline_type != null) {
//                    med.setBaseline_type(pending.get(i).baseline_type);
//                } else {
//                    med.setBaseline_type("0");
//                }
//                if (pending.get(i).urea != null) {
//                    med.setUrea(pending.get(i).urea);
//                } else {
//                    med.setUrea("0");
//                }
//                if (pending.get(i).creatinie != null) {
//                    med.setCreatinie(pending.get(i).creatinie);
//                } else {
//                    med.setCreatinie("0");
//                }
//                if (pending.get(i).blood_sugar_random != null) {
//                    med.setBlood_sugar_random(pending.get(i).blood_sugar_random);
//                } else {
//                    med.setBlood_sugar_random("0");
//                }
//                if (pending.get(i).urea != null) {
//                    med.setUrea(pending.get(i).urea);
//                } else {
//                    med.setUrea("0");
//                }
//                if (pending.get(i).drug_interaction != null) {
//                    med.setDrug_interaction(pending.get(i).drug_interaction);
//                } else {
//                    med.setDrug_interaction("0");
//                }
//                if (pending.get(i).hcv_medicine_recommended != null) {
//                    med.setHcv_medicine_recommended(pending.get(i).hcv_medicine_recommended);
//                } else {
//                    med.setHcv_medicine_recommended("0");
//                }
//                if (pending.get(i).disburse_6_mnth_dose != null) {
//                    med.setDisburse_6_mnth_dose(pending.get(i).disburse_6_mnth_dose);
//                } else {
//                    med.setDisburse_6_mnth_dose("0");
//                }
//                if (pending.get(i).cirrhotic_medicine_flow != null) {
//                    med.setCirrhotic_medicine_flow(pending.get(i).cirrhotic_medicine_flow);
//                } else {
//                    med.setCirrhotic_medicine_flow("0");
//                }
//                if (pending.get(i).created != null) {
//                    med.setCreated(pending.get(i).created);
//                } else {
//                    med.setCreated("0");
//                }
//                if (pending.get(i).user_id != null) {
//                    med.setUser_id(pending.get(i).user_id);
//                } else {
//                    med.setUser_id(0);
//                }
//                if (pending.get(i).hospital_id != null) {
//                    med.setHospital_id(pending.get(i).hospital_id);
//                } else {
//                    med.setHospital_id(0);
//                }
//                if (pending.get(i).updated != null) {
//                    med.setUpdated(pending.get(i).updated);
//                } else {
//                    med.setUpdated("0");
//                }
//                if (pending.get(i).mobile_id != null) {
//                    med.setMobile_id(pending.get(i).mobile_id);
//                } else {
//                    med.setMobile_id(0);
//                }
//                if (pending.get(i).hcv_medicine_duration != null) {
//                    med.setHcv_medicine_duration(pending.get(i).hcv_medicine_duration);
//                } else {
//                    med.setHcv_medicine_duration(0);
//                }
//                if (pending.get(i).treatment_history != null) {
//                    med.setTreatment_history(pending.get(i).treatment_history);
//                } else {
//                    med.setTreatment_history("0");
//                }
//                if (pending.get(i).disburse_3_mnth_dose != null) {
//                    med.setDisburse_3_mnth_dose(pending.get(i).disburse_3_mnth_dose);
//                } else {
//                    med.setDisburse_3_mnth_dose("0");
//                }
//                if (pending.get(i).treatment_options != null) {
//                    med.setTreatment_options(pending.get(i).treatment_options);
//                } else {
//                    med.setTreatment_options("0");
//                }
//
////                List<addPatientModel> fli = addPatientModel.getall();
////
////                sampless.get(i).pid = fli.get(i).getPatient_id();
//
//                submitpending(med);
//
//            }
//
//        }
//    }
//
//    private void submitpending(medicineRequest med) {
//
//        ProgressDialog dialog = new ProgressDialog(getContext());
//        dialog.setMessage("Saving Patient Vital please wait . . ");
//        dialog.show();
//        Call<medicineResponse> call = RetrofitClient
//                .getInstance().getApi().savepending(med);
//        call.enqueue(new Callback<medicineResponse>() {
//            @Override
//            public void onResponse(Call<medicineResponse> call, Response<medicineResponse> response) {
//                dialog.dismiss();
//                if (response.body() != null) {
//
//
////                    ActiveAndroid.beginTransaction();
////                    try {
////
////                        List<Samplee> s = Samplee.getall();
////                        for (int i = 0; i < s.size(); i++) {
////
////                            Samplee mo = Samplee.load(Samplee.class, response.body().getData().getMobile_id());
////                            mo.IsSync = 1;
////                            mo.save();
////
////                        }
////                        ActiveAndroid.setTransactionSuccessful();
////                    } finally {
////                        ActiveAndroid.endTransaction();
////                    }
//
//
//
//
////                    deleteformvitalList(response.body().getData().getMobile_id());
//                }
//
//                totalSYncREcord();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<medicineResponse> call, Throwable t) {
//
//                try {
////                    syncedleaders = syncedleaders+1;
////                    if(syncedleaders==leadersubmitcount){
//////                        submitLmpData();
////                    }
////                    Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//
//                } catch (Exception exception) {
//                    dialog.dismiss();
//                }
//
//            }
//        });
//
//
//
//    }

    private void exportDB() {
        try {

            File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/hcp");

            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {
                File dbFile = new File(getActivity().getDatabasePath("hcip.db").getAbsolutePath());
                FileInputStream fis = new FileInputStream(dbFile);

                String outFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/hcp" + File.separator + "hcip_" + new SharedPref(getActivity()).GetserverID() + ".db";

                OutputStream output = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                fis.close();

                File File = new File(outFileName);


                shareFile(File);

            } else {
//                Toast.makeText(MainActivity.main, "Failed to create directory", Toast.LENGTH_SHORT).show();
            }


        } catch (IOException e) {
            Log.e("dbBackup:", e.getMessage());
        }
    }

    private void shareFile(File file) {

        Uri u = FileProvider.getUriForFile(getActivity(), "com.example.hcp.fileprovider", file);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, u);
        shareIntent.setType("text/*");
        startActivity(Intent.createChooser(shareIntent, "Share File"));

    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
