package com.example.hcp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;

import com.example.hcp.loginRequest;
import com.example.hcp.loginResponse;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.Datum;
import com.example.hcp.models.hcp.Datum1;
import com.example.hcp.models.hcp.Datum2;
import com.example.hcp.models.hcp.Datum3;
import com.example.hcp.models.hcp.Datum4;
import com.example.hcp.models.hcp.Datum5;
import com.example.hcp.models.hcp.Datum6;
import com.example.hcp.models.hcp.MedDisbursmentResponse;
import com.example.hcp.models.hcp.MedicineDisbursment_Table;
import com.example.hcp.models.hcp.ReleaseLocalTable;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.jailListResponse;
import com.example.hcp.models.hcp.jailListTable;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.sample_statusResponse;
import com.example.hcp.models.hcp.sample_status_Table;
import com.example.hcp.models.hcp.savejail;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.models.hcp.HFresponse;
import com.example.hcp.models.hcp.OccupationResponse;
import com.example.hcp.models.hcp.districtResponse;
import com.example.hcp.models.hcp.districtt;
import com.example.hcp.models.hcp.divisionn;
import com.example.hcp.models.hcp.DivisionsModel;

import com.example.hcp.models.Locations.UCResponse;
import com.example.hcp.models.hcp.healthFacilityy;
import com.example.hcp.models.hcp.hfUserDataResponse;
import com.example.hcp.models.hcp.materialStatuss;
import com.example.hcp.models.hcp.occuptaionn;
import com.example.hcp.models.hcp.qualificationn;
import com.example.hcp.models.hcp.tehsilResponse;
import com.example.hcp.models.hcp.tehsill;
import com.example.hcp.models.hcp.userdataRequest;
import com.example.hcp.models.hcp.vitalListt;
import com.example.hcp.models.hcp.vitalPatientListRequest;
import com.example.hcp.models.hcp.vitalPatientResponse;

import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.example.hcp.utils.UtilsLocal;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.activeandroid.Cache.getContext;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnlogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("برائے مہربانی انتظار کریں !");
        dialog.setCancelable(false);


        btnlogin.setOnClickListener(
                v -> LoadDashboard()
        );
    }

    void LoadDashboard() {

        String usernamestr, passwordstr;

        usernamestr = username.getText().toString();
        passwordstr = password.getText().toString();

        if (usernamestr.isEmpty() || passwordstr.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter username & password to proceed", Toast.LENGTH_LONG).show();

        } else {
            if(UtilsLocal.isNetworkAvailable(getContext())){
                clearDB();
                Login(usernamestr, passwordstr);
            }else {
                Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void clearDB(){
        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record
            vitalListt.deleteAll();
            addvitalll.deleteAll();
            Assessmentt.deleteAll();
            Samplee.deleteAll();
            userdataaa.deleteAll();
//          addPatientModel.deleteAll();
            MedicineDisbursment_Table.deleteAll();
            ReleaseLocalTable.deleteAll();
            sample_status_Table.deleteAll();
            districtt.deleteAll();
            divisionn.deleteAll();
            tehsill.deleteAll();
            savejail.deleteAll();
            jailListTable.deleteAll();
            occuptaionn.deleteAll();
            qualificationn.deleteAll();
            materialStatuss.deleteAll();
            medicinee.deleteAll();
            Vaccinationn.deleteAll();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    void Login(String username, String password) {
        dialog.show();
        loginRequest loginRequest = new loginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call<loginResponse> call = RetrofitClient
                .getInstance().getApi().login(loginRequest);

//        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
//        Call<UserResponse> call = service.Login(username, password, "password");
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                if (response.code() == 200) {
                    if (response.body().getStatus()) {


                        Prefs.edit().putString(Constants.USERNAME, username).apply();
                        Prefs.edit().putString(Constants.PASSWORD, password).apply();
                        Prefs.putInt("hospitalid", 0);

                        loginResponse UR = response.body();
                        new SharedPref(getApplicationContext()).SaveCredentials(null, UR.getData().getHospital_id(), null, UR.getData().getIdentifier(), UR.getData().getUser_id(), UR.getData().getStart_range(), UR.getData().getEnd_range(), null, UR.getData().getHospital_name(), null, null);
                        String isdataloaded = new SharedPref(getApplicationContext()).GetDataLoaded();
                        if (isdataloaded != null) {
                            if (isdataloaded.equals("no")) {
                                GetDivisions();
                            } else if (isdataloaded.equals("yes")) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            GetDivisions();
                        }
//
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid Credentials .. ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Invalid Credentials .. ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "Something went wrong !" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void GetDivisions() {
        Call<DivisionsModel> call = RetrofitClient
                .getInstance().getApi().divisions();
        call.enqueue(new Callback<DivisionsModel>() {
            @Override
            public void onResponse(Call<DivisionsModel> call, Response<DivisionsModel> response) {
                Log.d("sdf", response.message());
                DivisionsModel division = response.body();
//              division.getData();
                if (response.body() != null && response.body().getStatus()) {
                    saveDivisionLocally(response.body().getData());

                } else {
                    Toast.makeText(getContext(), "Something went wrong on server ..", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DivisionsModel> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    private void saveDivisionLocally(List<Datum> data) {
        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                divisionn division = new divisionn();
                division.setDivision_name(data.get(i).getDivision_name());
                division.setDivision_code(data.get(i).getDivision_code());
                division.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        GetDistricts();
    }

    public void GetDistricts() {

        /*Create handle for the RetrofitInstance interface*/
        Call<districtResponse> call = RetrofitClient
                .getInstance().getApi().districts();
        call.enqueue(new Callback<districtResponse>() {
            @Override
            public void onResponse(Call<districtResponse> call, Response<districtResponse> response) {
                Log.d("sdf", response.message());
                if (response.body() != null && response.body().getStatus()) {
                    saveDistrictLocally(response.body().getData());

                } else {
                    Toast.makeText(getContext(), "Something went wrong on server ..", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<districtResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    private void saveDistrictLocally(List<Datum1> data) {

        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                districtt districts = new districtt();
                districts.setDistrict_name(data.get(i).getDistrict_name());
                districts.setDistrict_code(data.get(i).getDistrict_code());
                districts.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        GetTehsils();
    }

    public void GetTehsils() {

        Call<tehsilResponse> call = RetrofitClient
                .getInstance().getApi().tehsils();
        call.enqueue(new Callback<tehsilResponse>() {
            @Override
            public void onResponse(Call<tehsilResponse> call, Response<tehsilResponse> response) {

                if (response.body() != null && response.body().getStatus()) {
                    saveTehsilDataLocally(response.body().getData());
                } else {
                    Toast.makeText(getContext(), Constants.GeneralError, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<tehsilResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() + Constants.ServerError, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }

    private void saveTehsilDataLocally(List<Datum2> data) {

        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                tehsill tehsil = new tehsill();
                tehsil.setTehsil_name(data.get(i).getTehsil_name());
                tehsil.setTehsil_code(data.get(i).getTehsil_code());
                tehsil.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        GetHealthFacility();



    }


    public void GetHealthFacility() {
        Call<HFresponse> call = RetrofitClient
                .getInstance().getApi().healthFacility();
        call.enqueue(
                new Callback<HFresponse>() {
                    @Override
                    public void onResponse(Call<HFresponse> call, Response<HFresponse> response) {

//                        try {

                            if (response.body() != null && response.body().getStatus()) {

                                SaveHealthFacility(response.body().getData());
                            } else {
                                Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }

                    }

                    @Override
                    public void onFailure(Call<HFresponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );

    }

    private void SaveHealthFacility(List<Datum4> data) {

        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < data.size(); i++) {
                healthFacilityy hf = new healthFacilityy();
                hf.setHf_code(data.get(i).getHf_code());
                hf.setHf_name(data.get(i).getHf_name());
                hf.setTehsil_code(data.get(i).getTehsil_code());

                hf.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        GetOccupation();
    }


    public   void GetOccupation(){
        Call<OccupationResponse> call = RetrofitClient
                .getInstance().getApi().occuptaion();
        call.enqueue(
                new Callback<OccupationResponse>() {
                    @Override
                    public void onResponse(Call<OccupationResponse> call, Response<OccupationResponse> response) {
                        if (response.body() != null && response.body().getStatus()) {

                            SaveOccupationLocally(response.body().getData());
                        } else {
                            Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OccupationResponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );


    }

    private void SaveOccupationLocally(List<Datum3> data) {

        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                occuptaionn profession = new occuptaionn();

                profession.name=data.get(i).getName();
                profession.id=data.get(i).getId();

                profession.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        GetMeterialStatus();

    }


    public   void GetMeterialStatus(){
        Call<OccupationResponse> call = RetrofitClient
                .getInstance().getApi().materialStatus();
        call.enqueue(
                new Callback<OccupationResponse>() {
                    @Override
                    public void onResponse(Call<OccupationResponse> call, Response<OccupationResponse> response) {
                        if (response.body() != null && response.body().getStatus()) {

                            SaveMateriaLocally(response.body().getData());
                        } else {
                            Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OccupationResponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );


    }

    private void SaveMateriaLocally(List<Datum3> data) {

        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                materialStatuss material = new materialStatuss();

                material.name=data.get(i).getName();
                material.id=data.get(i).getId();

                material.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }



        GetEducation();
//        GetMedDisbursment();

    }



    public void GetEducation() {
        Call<OccupationResponse> call = RetrofitClient
                .getInstance().getApi().qualification();
        call.enqueue(
                new Callback<OccupationResponse>() {
                    @Override
                    public void onResponse(Call<OccupationResponse> call, Response<OccupationResponse> response) {
                        if (response.body() != null && response.body().getStatus()) {

                            SaveEducationLocally(response.body().getData());
                        } else {
                            Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OccupationResponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );

    }

    private void SaveEducationLocally(List<Datum3> data) {
        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                qualificationn qualication = new qualificationn();

                qualication.name=data.get(i).getName();
                qualication.id=data.get(i).getId();

                qualication.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                GetUsersData();

                GetJailList();
            }
        }, 2000);

//        Intent intent = new Intent(getBaseContext(), MainActivity.class);
//        startActivity(intent);
//        finish();



    }

    public void GetJailList() {
        Call<jailListResponse> call = RetrofitClient
                .getInstance().getApi().alljailList();
        call.enqueue(
                new Callback<jailListResponse>() {
                    @Override
                    public void onResponse(Call<jailListResponse> call, Response<jailListResponse> response) {



                        if (response.body() != null && response.body().getStatus()) {

                            SaveJailLsitLocally(response.body().getData());
                        } else {
                            Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<jailListResponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );

    }

    private void SaveJailLsitLocally(List<jailListResponse.Datum> data) {


        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
                jailListTable jail = new jailListTable();

                jail.setHospital_name(data.get(i).getHospital_name());
                jail.setHospital_id(data.get(i).getHospital_id());
                jail.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                GetUsersData();
//                GetsampleStauts();
            }
        }, 2000);

//        Intent intent = new Intent(getBaseContext(), MainActivity.class);
//        startActivity(intent);
//        finish();



    }


    private void GetUsersData() {
        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());
        userdataRequest  request = new userdataRequest();

        request.setUser_hospital(i);


        Call<hfUserDataResponse> call = RetrofitClient.getInstance().getApi().alldata(request);
        call.enqueue(new Callback<hfUserDataResponse>() {
                         @Override
                         public void onResponse(Call<hfUserDataResponse> call, Response<hfUserDataResponse> response) {

                             if(response.body().getStatus()){

                                 if (response.body() != null && response.body().getStatus()) {

                                     SaveUserDataLocally(response.body().getData());
                                 } else {
                                     Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                     Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                                     startActivity(intent);
//                                     finish();
                                 }
                             }else {
//                                 GetsampleStauts();
                                 GetMedDisbursment();
                             }



                         }
                         @Override
                         public void onFailure(Call<hfUserDataResponse> call, Throwable t) {
                             Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                             dialog.dismiss();
//                             GetsampleStauts();
                         }
                     }
        );

    }

    private void SaveUserDataLocally(List<Datum5> data) {

//        KProgressHUD progressdilaog=  KProgressHUD.create(this)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
//                .setCancellable(false)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f)
//                .show();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            //Background work here
            try {
                //First Delete all Previous local record then add new Record
                ActiveAndroid.beginTransaction();

                for (int i = 0; i < data.size(); i++) {
                    userdataaa dat = new userdataaa();
                    dat.setPatient_id(data.get(i).getId());
                    dat.setMrn_no(data.get(i).getMrn_no());
                    dat.setReg_date(data.get(i).getReg_date());
                    dat.setPatient_name(data.get(i).getPatient_name());
                    dat.setLname(data.get(i).getLname());
                    dat.setFather_name(data.get(i).getFather_name());
                    dat.setPatient_age(data.get(i).getPatient_age());
                    dat.setSelf_cnic(data.get(i).getSelf_cnic());
                    if(data.get(i).getGender()!=null){
                        dat.setGender(Integer.parseInt(data.get(i).getGender()));
                    }
                    dat.setMarital_status(data.get(i).getMarital_status());

                    dat.setPatient_from(data.get(i).getPatient_from());
                    dat.setUser_id(data.get(i).getUser_id());
                    dat.setUser_hospital(data.get(i).getUser_hospital());
                    dat.setHcv_screening_result(data.get(i).getHcv_screening_result());
                    dat.setHbv_screening_result(data.get(i).getHbv_screening_result());
                    dat.setPcr_confirmation_hcv(data.get(i).getPcr_confirmation_hcv());
                    dat.setPcr_confirmation_hbv(data.get(i).getPcr_confirmation_hbv());
                    dat.setIs_refered(data.get(i).getIs_refered());
                    dat.setPatient_type(data.get(i).getPatient_type());
                    dat.setCbl(data.get(i).getCbl());
                    dat.setBbl(data.get(i).getBbl());
                    dat.setHcv_medicine_name(data.get(i).getHcv_medicine_name());
                    dat.setNo_of_hcv_medicine_delivered(data.get(i).getNo_of_hcv_medicine_delivered());
                    dat.setMedicine_disbursment_date(data.get(i).getMedicine_disbursment_date());
                    dat.setNo_of_hcv_followups(data.get(i).getNo_of_hcv_followups());
                    dat.setHcv_medicine_duration(data.get(i).getHcv_medicine_duration());
                    dat.setNext_status(data.get(i).getNext_status());
                    dat.setIs_ignore(data.get(i).getIs_ignore());
                    dat.setIs_assesment(data.get(i).getIs_assesment());
                    dat.setIs_sample(data.get(i).getIs_sample());
                    dat.setCollect_sample(data.get(i).getCollect_sample());
                    dat.setIs_svr_form_submitted(data.get(i).getIs_svr_form_submitted());
                    dat.setIs_svr_sample(data.get(i).getIs_svr_sample());
                    dat.setPrevious_hbv(data.get(i).getPrevious_hbv());
                    dat.setPrevious_hcv(data.get(i).getPrevious_hcv());
                    dat.setRapid_testing(data.get(i).getRapid_testing());
                    dat.setContact_no_self(data.get(i).getContact_no_self());
                    dat.setPostal_address(data.get(i).getPostal_address());
                    dat.setIs_re_register(data.get(i).getIs_re_register());
                    dat.setIs_vital(data.get(i).getIs_vital());
                    dat.setNext_of_kin_cnic(data.get(i).getNext_of_kin_cnic());
                    dat.setIs_referal(data.get(i).getIs_referal());
                    dat.setNo_of_medicine_delivered(data.get(i).getNo_of_medicine_delivered());
                    dat.setIs_treatment(data.get(i).getIs_treatment());
                    dat.setIs_closed(data.get(i).getIs_closed());
                    dat.setIs_terminate(data.get(i).getIs_terminate());
                    dat.setBaseline_result_type(data.get(i).getBaseline_result_type());
                    dat.setVaccinate(data.get(i).getVaccinate());
                    dat.setCnic_status(data.get(i).getCnic_status());
                    if(data.get(i).getDivision()!=null){
                        dat.setDivision(Integer.parseInt(data.get(i).getDivision()));
                    }
                    if(data.get(i).getDistrict()!=null){
                        dat.setDistrict(Integer.parseInt(data.get(i).getDistrict()));
                    }
                    if(data.get(i).getTehsil()!=null){
                        dat.setTehsil(Integer.parseInt(data.get(i).getTehsil()));
                    }
                    if(data.get(i).getHospital()!=null){
                        dat.setHospital(Integer.parseInt(data.get(i).getHospital()));
                    }
                    dat.setPatient_dob(data.get(i).getPatient_dob());
                    dat.setIs_type_change(data.get(i).getIs_type_change());
                    dat.setLost_followup_id(data.get(i).getLost_followup_id());
                    if(data.get(i).getHcv_viral_count() == null){
                        dat.setHcv_viral_count("0");
                    }else {
                        try{
                            if(!data.get(i).getHcv_viral_count().equals("")){
                                dat.setHcv_viral_count(data.get(i).getHcv_viral_count());
                            }

                        } catch(NumberFormatException ex){ // handle your exception
                            ex.printStackTrace();
                        }
//                    dat.hcv_viral_count = Integer.valueOf(data.get(i).getHcv_viral_count());
                    }
                    if(data.get(i).getHbv_viral_count() == null){
                        dat.setHbv_viral_count("0");
                    }else {

                        try{
                            if(!data.get(i).getHbv_viral_count().equals("")){
                                dat.setHbv_viral_count(data.get(i).getHbv_viral_count());

                            }
                        } catch(NumberFormatException ex){ // handle your exception
                            ex.printStackTrace();
                        }

//                    dat.hbv_viral_count = Integer.valueOf(data.get(i).getHbv_viral_count());
                    }

                    if(data.get(i).getSample_id()==null){
                        dat.setSample_id("0");
                    }else {

                        try{
                            if(!data.get(i).getSample_id().equals("")){
                                dat.setSample_id(data.get(i).getSample_id());

                            }
                        } catch(NumberFormatException ex){ // handle your exception
                            ex.printStackTrace();
                        }

//                    dat.sample_id = Integer.valueOf(data.get(i).getSample_id());
                    }

                    dat.setIs_cirrhotic_patient(data.get(i).getIs_cirrhotic_patient());

                    dat.setFinger_print1(data.get(i).getFinger_print1());
                    dat.setFinger_print2(data.get(i).getFinger_print2());

                    if(data.get(i).getPrison_transfer_status()==null){
                        dat.setTransfer_flag(0);
                    }else {
                        dat.transfer_flag = 1;
                    }

                    dat.setPrison_transfer_status(data.get(i).getPrison_transfer_status());
                    dat.setCurrent_hospital_name( data.get(i).getCurrent_hospital_name());
                    dat.setEx_hospital_name(data.get(i).getEx_hospital_name());

                    dat.setIsActive(1);

                    dat.setISTransfer(1);
                    dat.setISRelease(1);
                    dat.setIS_delete(0);

//                  dat.setIsMedicine(0);

                    dat.save();
//                Log.d("asdf","sadf");

                }
                ActiveAndroid.setTransactionSuccessful();
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        GetsampleStauts();
                        GetMedDisbursment();
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
//                GetVitalListData();
                    }
                }, 2000);
            } finally {
                ActiveAndroid.endTransaction();
            }
        });



    }


    private void GetMedDisbursment() {
        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());
        userdataRequest  request = new userdataRequest();
        request.setUser_hospital(i);
        Call<MedDisbursmentResponse> call = RetrofitClient
                .getInstance().getApi().meddisbursment(request);
        call.enqueue(
                new Callback<MedDisbursmentResponse>() {
                    @Override
                    public void onResponse(Call<MedDisbursmentResponse> call, Response<MedDisbursmentResponse> response) {

                        if(response.body().getStatus()){
                            if (response.body() != null && response.body().getStatus()) {
                                SaveMedDisbursmentLocally(response.body().getData());
                            } else {
                                Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                                 GetsampleStauts();
                        }

                    }
                    @Override
                    public void onFailure(Call<MedDisbursmentResponse> call, Throwable t) {
                        Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );

    }

    private void SaveMedDisbursmentLocally(List<MedDisbursmentResponse.DatumM> data) {


        ActiveAndroid.beginTransaction();
        try {
            //First Delete all Previous local record then add new Record

            for (int i = 0; i < data.size(); i++) {
//                MedicineDisbursment_Table meddis = new MedicineDisbursment_Table();
//                meddis.setPatient_id(data.get(i).getId());
//                meddis.setMrn_no(data.get(i).getMrn_no());
//                meddis.setPatient_name(data.get(i).getPatient_name());
//                meddis.setSelf_cnic(data.get(i).getSelf_cnic());
//                meddis.setTest_type(data.get(i).getTest_type());
//                meddis.setSample_number(data.get(i).getSample_number());
//                meddis.setPatient_stage(data.get(i).getPatient_stage());
//                meddis.setHbv_viral_load(data.get(i).getHbv_viral_load());
//                meddis.setHcv_viral_load(data.get(i).getHcv_viral_load());
//                meddis.setIs_hbv_detected(data.get(i).getIs_hbv_detected());
//                meddis.setIs_hcv_detected(data.get(i).getIs_hcv_detected());
//                meddis.save();

                userdataaa medicine = new userdataaa();
                medicine.setPatient_id(Integer.parseInt(data.get(i).getId()));
                medicine.setMrn_no(data.get(i).getMrn_no());
                medicine.setPatient_name(data.get(i).getPatient_name());
                medicine.setSelf_cnic(data.get(i).getSelf_cnic());
                medicine.setTest_type(data.get(i).getTest_type());
                medicine.setSample_number(data.get(i).getSample_number());
                medicine.setPatient_stage(data.get(i).getPatient_stage());
                medicine.setHbv_viral_count(data.get(i).getHbv_viral_load());
                medicine.setHcv_viral_count(data.get(i).getHcv_viral_load());
                medicine.setIs_hbv_detected(data.get(i).getIs_hbv_detected());
                medicine.setIs_hcv_detected(data.get(i).getIs_hcv_detected());
                medicine.IsMedicine = 1;
                medicine.save();

            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                GetsampleStauts();
            }
        }, 2000);


    }


    private void GetsampleStauts() {
        int i=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());
        userdataRequest  request = new userdataRequest();

        request.setUser_hospital(i);


        Call<sample_statusResponse> call = RetrofitClient.getInstance().getApi().allsamplestauts(request);
        call.enqueue(new Callback<sample_statusResponse>() {
                         @Override
                         public void onResponse(Call<sample_statusResponse> call, Response<sample_statusResponse> response) {
//
                             if(response.body() != null)
                             {
                                 if(response.body().getStatus())
                                 {
                                     SavesampelstautsLocally(response.body().getData());
                                 }
                                 else
                                 {
                                     Toast.makeText(getContext(), "==> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                     Next();
                                 }
                             }
                             else
                                 {
                                     Next();
                                 }

                         }
                         @Override
                         public void onFailure(Call<sample_statusResponse> call, Throwable t) {
//                             Toast.makeText(getContext(), Constants.ServerError + t.getMessage(), Toast.LENGTH_SHORT).show();
                             dialog.dismiss();
                             Next();
                         }
                     }
        );

    }

    void Next()

    {
        Prefs.edit().putBoolean(Constants.isDataLoaded,true).apply();
        dialog.dismiss();
//                progressdilaog.dismiss();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void SavesampelstautsLocally(List<sample_statusResponse.Datum> data) {
        ActiveAndroid.beginTransaction();
        //Background work here
        try {
            //First Delete all Previous local record then add new Record


            for (int i = 0; i < data.size(); i++) {
                sample_status_Table stauts = new sample_status_Table();
                stauts.setPatient_id(data.get(i).getPatient_id());
                stauts.setSample_id(data.get(i).getSample_id());
                if(data.get(i).getLab_sample_id()!=null){
                    stauts.setLab_sample_id(data.get(i).getLab_sample_id());
                }

                if(data.get(i).getResult_id()!=null){
                    stauts.setResult_id(data.get(i).getResult_id());
                }

                if(data.get(i).getAction_id()!=null){
                    stauts.setAction_id(data.get(i).getAction_id());
                }

                stauts.setIs_reception(data.get(i).getIs_reception());
                if(data.get(i).getHcv_viral_load()!=null){
                    stauts.setHcv_viral_load(data.get(i).getHcv_viral_load());
                }
                if(data.get(i).getHbv_viral_load()!=null){
                    stauts.setHbv_viral_load(data.get(i).getHbv_viral_load());
                }

                stauts.setMrn_no(data.get(i).getMrn_no());
                stauts.setPatient_name(data.get(i).getPatient_name());
                stauts.setSelf_cnic(data.get(i).getSelf_cnic());
                stauts.setPatient_age(data.get(i).getPatient_age());
                stauts.setSample_status(data.get(i).getSample_status());
                stauts.setSample_number(data.get(i).getSample_number());
                stauts.save();
//                Log.d("asdf","sadf");

            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                GetUsersData();


//                GetUsersData();

            }
        }, 2000);
        Prefs.edit().putBoolean(Constants.isDataLoaded,true).apply();
        dialog.dismiss();
//                progressdilaog.dismiss();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
//        Intent intent = new Intent(getBaseContext(), MainActivity.class);
//        startActivity(intent);
//        finish();



    }

}