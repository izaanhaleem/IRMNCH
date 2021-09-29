package com.example.hcp.services;

import com.example.hcp.loginRequest;
import com.example.hcp.loginResponse;
import com.example.hcp.models.AppSettings.AppSettingsResponse;
import com.example.hcp.models.hcp.AddVitalResponse;
import com.example.hcp.models.hcp.AssessmentResponse;
import com.example.hcp.models.hcp.DivisionsModel;

import com.example.hcp.models.Locations.DistrictsResponse;
import com.example.hcp.models.Locations.TehsilResponse;
import com.example.hcp.models.Locations.UCResponse;
import com.example.hcp.models.Users.UserResponse;
import com.example.hcp.models.hcp.HFresponse;
import com.example.hcp.models.hcp.MedDisbursmentResponse;
import com.example.hcp.models.hcp.OccupationResponse;
import com.example.hcp.models.hcp.SampleRequest;
import com.example.hcp.models.hcp.VaccinationRequest;
import com.example.hcp.models.hcp.VaccinationResponse;
import com.example.hcp.models.hcp.addAssessmentRequest;
import com.example.hcp.models.hcp.addPatientRequest;
import com.example.hcp.models.hcp.addPatientResponse;
import com.example.hcp.models.hcp.addVitalRequest;
import com.example.hcp.models.hcp.districtResponse;
import com.example.hcp.models.hcp.hfUserDataResponse;
import com.example.hcp.models.hcp.medicineRequest;
import com.example.hcp.models.hcp.medicineResponse;
import com.example.hcp.models.hcp.sampleResponse;
import com.example.hcp.models.hcp.tehsilResponse;
import com.example.hcp.models.hcp.userdataRequest;
import com.example.hcp.models.hcp.vitalPatientListRequest;
import com.example.hcp.models.hcp.vitalPatientResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface GetDataService {

    @POST("Blood_Bank_Apis/user_login")
    Call<loginResponse> login(@Body loginRequest FMB);

    @POST("Apis/Patient_api/all_data_hf")
    Call<hfUserDataResponse> alldata(@Body userdataRequest req);

//    @POST("Apis/Vitals_api/index")
//    Call<vitalPatientResponse> allvitaldata(@Body vitalPatientListRequest req);

    @POST("Apis/Vitals_api/AddVitals")
    Call<AddVitalResponse> saveVital(@Body addVitalRequest req);

    @POST("Apis/Assesment_api/AddAssesment")
    Call<AssessmentResponse> saveAssessment(@Body addAssessmentRequest reques);


    @POST("Apis/Vaccination_api/Add")
    Call<VaccinationResponse> saveVaccination(@Body VaccinationRequest va);


    @POST("Apis/Sample_api/AddSample")
    Call<sampleResponse> saveSamples(@Body SampleRequest re);

    @POST("Apis/pending_enrollment_api/hcv_baseline_investigation")
    Call<medicineResponse> savepending(@Body medicineRequest req);

    @GET("Blood_Bank_Apis/total_division")
    Call<DivisionsModel> divisions();
    @GET("Blood_Bank_Apis/total_district")
    Call<districtResponse> districts();
    @GET("Blood_Bank_Apis/total_tehsil")
    Call<tehsilResponse> tehsils();

    @GET("Blood_Bank_Apis/total_health_facility")
    Call<HFresponse> healthFacility();


    @GET("Apis/Patient_api/occupation")
    Call<OccupationResponse> occuptaion();
    @GET("Apis/Patient_api/marital_status")
    Call<OccupationResponse> materialStatus();

    @GET("Apis/Patient_api/qualification")
    Call<OccupationResponse> qualification();

    @POST("Apis/pending_enrollment_api/pending_enrollment_content")
    Call<MedDisbursmentResponse> meddisbursment(@Body userdataRequest abc);


    @POST("Apis/Patient_api/Add")
    Call<addPatientResponse> savePatient(@Body addPatientRequest xc);


    @GET("/api/InstallationAPI/Installation/GetAppSettings")
    Call<AppSettingsResponse> getAppSettings();

//    @GET("/api/Common/Common/GetRelationTypes")
//    Call<GetRelationTypesResponse> getRelationTypes(@Header("Authorization") String bearer);

    @GET("/api/Common/Common/GetDistrict")
    Call<DistrictsResponse> getAllDistricts(@Header("Authorization") String bearer);

    @GET("/api/Common/Common/GetTehsil")
    Call<TehsilResponse> getAllTehsils(@Header("Authorization") String bearer,@Query("districtId") int districtId);

    @GET("/api/Common/Common/GetUC")
    Call<UCResponse> getAllUCs(@Header("Authorization") String bearer,@Query("tehsilId") int tehsilId);

    @FormUrlEncoded
    @POST("/getToken")
    Call<UserResponse> Login(@Field("Username") String username, @Field("Password") String password, @Field("grant_type") String grantType);

//    @POST("/api/Family/Family/SaveFamily")
//    Call<SaveFamilyResponse> SaveFamily(@Header("Authorization") String str, @Body FamilyBody familyBodyData);

//    @POST("/api/Family/FamilyOSync/SaveFamilyList")
//    Call<SaveFamilyResponse> SaveFamily(@Header("Authorization") String str, @Body List<FamilyBody> familyBodyData);
//
//
//
//    @POST("/api/Family/FamilyOSync/SavePregnancyDetail")
//    Call<LMPResponseModel> SavePregnancyDetail(@Header("Authorization") String str, @Body LMPModel  prgnancydata);
//
//
//    @GET("/api/Family/Family/GetFamilyAllData")
//    Call<SaveFamilyResponse> getAllFamilies(@Header("Authorization") String str);
//
//    @GET("/api/Family/Family/GetFamilyMembersAllData")
//    Call<GetFamilyMemberResponse> getAllFamilyMembers(@Header("Authorization") String str);
//
//    @GET("/api/Family/Family/SearchFamily")
//    Call<SearchFamilyResponse> SearchFamily(@Header("Authorization") String str, @Query("strType") String optionType , @Query("strValue") String optionValue);
//
//    @GET("/api/Family/Family/GetFamilyMembers")
//    Call<GetFamilyMemberResponse> GetFamilyMember(@Header("Authorization") String str, @Query("MrNo") String MrNo);
//
//
//
//    @POST("/api/Family/FamilyOSync/SaveFamilyMemberList")
//    Call<GetFamilyMemberResponse> AddFamilyAllMember(@Header("Authorization") String str, @Body List<FamilyMemberBody> FMB);
//
////    @POST("/api/Family/Family/SaveFamilyMember")
////    Call<SaveFamilyMemberResponse> AddFamilyMember(@Header("Authorization") String str, @Body FamilyMemberBody FMB);
//
//
//    @PATCH("/api/Family/Family/RemoveFamilyMember")
//    Call<DeleteFamilyMemberResponse> RemoveFamilyMember(@Header("Authorization") String str, @Query("familyMemberId") int FMID);
//
//    @GET("/api/Family/Family/GetFamilyMember")
//    Call<GetFamilyMemberDetailResponse> getMemberDetail(@Header("Authorization") String bearer, @Query("FamilyMemberId") int familyMemberId);
//
//    @POST("/api/Family/Family/SaveMemberPregnancy")
//    Call<SaveMemberPregnancyResponse> SaveMemberPregnancy(@Header("Authorization") String str, @Body SaveMemberPregnancyBody SMPB);
//
//
//    @POST("/api/Family/Family/UpdateLeader")
//    Call<updateLeaderResponse> changeLeader(@Header("Authorization") String bearer, @Query("NewLeaderId") int NewLeaderId, @Query("oldLeaderRelationTypeId") int oldLeaderRelationTypeId);
//
//
//    @GET("/api/Family/Family/GetEducationList")
//    Call<GetEducationTypesResponse> getallEducationList(@Header("Authorization") String bearer);
//
//    @GET("/api/Family/Family/GetSchoolTypeList")
//    Call<GetEducationTypesResponse> getSchoolTypelist(@Header("Authorization") String bearer);
//
//    @GET("/api/Family/Family/GetProfessionList")
//    Call<GetEducationTypesResponse> getProfessionlist(@Header("Authorization") String bearer);
//
//    @GET("api/Family/Family/GetBirthLocationList")
//    Call<GetEducationTypesResponse> getBirthLocationList(@Header("Authorization") String bearer);
//
//    @GET("/api/Family/Family/GetLanguageList")
//    Call<GetEducationTypesResponse> getLanguageList(@Header("Authorization") String bearer);
//
//
//    @GET("/api/Common/Common/GetHealthFacility")
//    Call<HealthFacilityResponse> getHealthFacilityList(@Header("Authorization") String bearer);
}

