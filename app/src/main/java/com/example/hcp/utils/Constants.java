package com.example.hcp.utils;

import android.content.Context;

import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;

public class Constants {

//    private static Constants _instance = null;
//
//    private Constants() {}
//
//
//    public static Constants instance() {
//        if (_instance == null) {
//            synchronized(Constants.class) {
//                _instance = new Constants();
//            }
//        }
//        return _instance;
//    }

    public static Context context;

    public static final String GeneralError = "کچھ غلط ہو گیا۔";
    public static final String ServerError = "سرور پر کچھ غلط ہو گیا۔";
    public static final String GetPermission = "اس ایپ کو چلانے کے لئے اہم اجازتیں فراہم کریں ...";
    public static final String GenderMissing = "جنس منتخب کریں.";
    public static final String division = "Missing Division";
    public static final String district = "Missing District";
    public static final String tehsil = "Missing Tehsil";
    public static final String healthfacility = "Missing HealthFacility";
    public static final String previoushbv = "Select Previous HBV";
    public static final String previoushcv = "Select Previous HCV";
    public static final String dateofbirth = "Missing Date of Birth";
    public static final String ageislessthensixx = "Age is Less then 6 Year";
    public static final String ageisgreatertheneighty = "Age is Greater then 80 Year";
    public static final String scanyour_finger = "Scan Finger Print!";
    public static final String DOBMissing = "تاریخ پیدائش کا انتخاب کریں.";
    public static final String DOBMissing1 = "سربراہ کی عمر 16 سال سے کم ہے.";
    public static final String NameMissing= "Plz Enter Patient Name";
    public static final String temp= "Enter Temperature in FH";
    public static final String error= "Temperature Must be equal or less then 106";
    public static final String bpsys= "BP(Systolic) Must be equal or less then 300";
    public static final String bpdys= "BP(Diastolic) Must be equal or less then 200";
    public static final String sampleNoIncorrect= "Incorrect Sample No";
    public static final String FHNameMissing= "Missing Father Name";
    public static final String lName= "Missing Last Name";
    public static final String remarksadd= "تبصرہ شامل کریں.";
    public static final String CNICMissing = "قومی شناختی کارڈ نمبر درج کریں.";
    public static final String CompleteCNIC="براہ کرم مکمل شناختی کارڈ کا انتخاب کریں";
    public static final String BFormMissing="براہ کرم بے فارم نمبر شامل کریں";
    public static final String PhoneMissing = "فون نمبر درج کریں.";
    public static final String PhoneMissing1 = "Phone No!";
    public static final String FormDataMissing = "براہ مہربانی مکمل طور پر فارم بھریں !";
    public static final String FamilyDataSaved = "فیملی کا ڈیٹا کامیابی کے ساتھ محفوظ ہوگیا !";
    public static final String AddressMissing = "Address is Missing!";
    public static final String UCCodeMissing= "اپنا یو سی منتخب کریں !";
    public static final String occupation= "Missing Occupation";
    public static final String EducationOccupationMissing = " Education Missing";
    public static final String RelationShipWithLeaderMissing = "خاندان کے سربراہ کے ساتھ تعلقات کو منتخب کریں !";
    public static final String DoorPicMissing = "دروازے کی تصویر حاصل کریں !";
    public static final String MaritalStatusMissing = "Marital Status!";
    public static final String hospitalstatus = " ہسپتال منتخب کریں\n   ";
    public static final String privathospitalName = " !";

//    public static final String hospitalstatus = " ہسپتال منتخب کریں\n   ";
    public static String SelectedFamilyID;
    public static String SelectedFamilyMrNo;
    public static final String isDataLoaded = "isDataLoaded";
    public static final String ErrorLog = "ErrorLog";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static Fmd FmdTest = null;
    public static String FmdBase64 = null;
    public static String Fmd = null;
    public static int width = 0;
    public static int height = 0;
    public static int quality = 0;
    public static int cbeff_id = 0;

    public static Fid cap_result = null;



}
