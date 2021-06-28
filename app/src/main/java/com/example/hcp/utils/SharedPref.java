package com.example.hcp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private final String SharedPrefileName = "ems";


    private Context _context;

    private SharedPref instance;


    public SharedPref(Context context)
    {
        _context = context;
    }

    public void SaveKeyValue(String key, String value)
    {
        SharedPreferences prefs = _context.getSharedPreferences(SharedPrefileName, Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }


    public String GetKeyValue(String key)

    {
        SharedPreferences prefs = _context.getSharedPreferences(SharedPrefileName, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }


    public void SaveCredentials(String token, String username, String password, String serverID, String roleName, String HFMISCode, String HealthFacility,String email,String fullName, String userId,String GUIDNew)

    {
        SaveKeyValue("isLoggedIn"    , "true");
        SaveKeyValue("access_token"  , token);
        SaveKeyValue("userName"      , username);
        SaveKeyValue("password"      , password);
        SaveKeyValue("serverID"      , serverID);
        SaveKeyValue("RoleName"      , roleName);
        SaveKeyValue("HFMISCode"     , HFMISCode);
        SaveKeyValue("HealthFacility", HealthFacility);
        SaveKeyValue("email"         , email);
        SaveKeyValue("FullName"      , fullName);
        SaveKeyValue("UserId"        , userId);
        SaveKeyValue("GUIDNew"        , GUIDNew);

    }

    public void SaveFacilityIndicatorVersion(String facilityIndicatorVersion)     { SaveKeyValue("FacilityIndicatorVersion"    , facilityIndicatorVersion); }
    public void SaveFaciltyOutlookVersion(String faciltyOutlookVersion)           { SaveKeyValue("FaciltyOutlookVersion"       , faciltyOutlookVersion); }
    public void SaveMedicineAndSuppliesVersion(String medicineAndSuppliesVersion) { SaveKeyValue("MedicineAndSuppliesVersion"  , medicineAndSuppliesVersion); }
    public void SaveOTPVersion(String oTPVersion)                                 { SaveKeyValue("OTPVersion"                  , oTPVersion); }
    public void SaveSocialMobilizationVersion(String socialMobilizationVersion)   { SaveKeyValue("SocialMobilizationVersion"   , socialMobilizationVersion); }
    public void SaveUtilitiesVersion(String utilitiesVersion)                     { SaveKeyValue("UtilitiesVersion"            , utilitiesVersion); }
    public void SaveVacancyPositionVersion(String vacancyPositionVersion)         { SaveKeyValue("VacancyPositionVersion"      , vacancyPositionVersion); }
    public void SaveVaccineAndLogiticsVersion(String vaccineAndLogiticsVersion)   { SaveKeyValue("VaccineAndLogiticsVersion"   , vaccineAndLogiticsVersion); }
    public void SaveAttendanceVersion(String attendanceVersion)                   { SaveKeyValue("AttendanceVersion"           , attendanceVersion); }
    public void SaveDisplayVersion(String displayVersion)                         { SaveKeyValue("DisplayVersion"              , displayVersion); }
    public void SaveLocationVersion(String locationVersion)                       { SaveKeyValue("LocationVersion"             , locationVersion); }
    public void SaveEquipmentsVersion(String equipmentsVersion)                   { SaveKeyValue("EquipmentsVersion"           , equipmentsVersion); }
    public void SaveDataLoaded(String isDataLoaded)                   { SaveKeyValue("isDataLoaded"           , "no"); }
    public String GetDataLoaded()   { return GetKeyValue("isDataLoaded"); }

    public String GetGUIDNew()   { return GetKeyValue("GUIDNew"); }

    public String GetFacilityIndicatorVersion()   { return GetKeyValue("FacilityIndicatorVersion"); }
    public String GetFaciltyOutlookVersion()      { return GetKeyValue("FaciltyOutlookVersion"); }
    public String GetMedicineAndSuppliesVersion() { return GetKeyValue("MedicineAndSuppliesVersion"); }
    public String GetOTPVersion()                 { return GetKeyValue("OTPVersion"); }
    public String GetSocialMobilizationVersion()  { return GetKeyValue("SocialMobilizationVersion"); }
    public String GetUtilitiesVersion()           { return GetKeyValue("UtilitiesVersion"); }
    public String GetVacancyPositionVersion()     { return GetKeyValue("VacancyPositionVersion"); }
    public String GetVaccineAndLogiticsVersion()  { return GetKeyValue("VaccineAndLogiticsVersion"); }
    public String GetDisplayVersion()             { return GetKeyValue("DisplayVersion"); }
    public String GetAttendanceVersion()          { return GetKeyValue("AttendanceVersion"); }
    public String GetLocationVersion()            { return GetKeyValue("LocationVersion"); }

    public String GetEquipmentsVersion()            { return GetKeyValue("EquipmentsVersion"); }

    public boolean CheckLoggedIn()

    {
        String LoggedIn = GetKeyValue("isLoggedIn");
        return (LoggedIn != null && LoggedIn.equals("true"));
    }
    public boolean isDataLoaded(){
        String LoggedIn = GetKeyValue("isDataLoaded");
        return (LoggedIn != null && LoggedIn.equals("true"));
    }

    public String GetLoggedInUser()
    {
        return GetKeyValue("userName");
    }

    public String GetLoggedInPassword()
    {
        return GetKeyValue("password");
    }

    public String GetserverID() { return GetKeyValue("serverID"); }

    public String GetLoggedInRole()
    {
        return GetKeyValue("RoleName");
    }

    public String GetHFMISCode()
    {
        return GetKeyValue("HFMISCode");
    }

    public String GetHealthFacility()
    {
        return GetKeyValue("HealthFacility");
    }

    public String GetLocationID()
    {
        return GetKeyValue("LocationID");
    }

    public String GethfVersion()
    {
        return GetKeyValue("hf");
    }

    public String GetUcVersion()
    {
        return GetKeyValue("uc");
    }

    public String GetCheckListVersion()
    {
        return GetKeyValue("checkList");
    }

    public String GetDistrictName()
    {
        return GetKeyValue("DistrictName");
    }

    public String GetFirstName()

    {
        return GetKeyValue("FirstName");
    }

    public String GetLastName()
    {
        return GetKeyValue("LastName");
    }

    public String GetUserId()
    {
        return GetKeyValue("UserId");
    }


    public String GetDesignationVersion()
    {
        return GetKeyValue("designation");
    }


    public void Logout()

    {
        SaveCredentials(null,null, null, null, null,null,null,null,null,null,null);
        SaveKeyValue("isLoggedIn", null);
    }


    public void LoggedIn(String str, String str2) {
        SaveKeyValue("isloggedin", str2);
        SaveKeyValue("username", str);
    }


    public String GetToken() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bearer ");
        stringBuilder.append(GetKeyValue("access_token"));
        return stringBuilder.toString();
    }

}
