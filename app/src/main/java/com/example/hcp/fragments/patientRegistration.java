package com.example.hcp.fragments;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.digitalpersona.uareu.dpfpdd.ReaderCollectionImpl;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbException;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbHost;
import com.example.hcp.activities.ScanActivity;
import com.example.hcp.activities.ScanActivity2;
import com.example.hcp.activities.VerificationActivity;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.vitalListt;
import com.example.hcp.utils.FormatHelper;
import com.example.hcp.utils.GetReaderActivity;
import com.example.hcp.utils.Globals;
import com.example.hcp.utils.LAPI;
import com.example.hcp.utils.MaskedEditText;
import com.github.nikartm.support.StripedProcessButton;
import com.example.hcp.R;

import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.districtt;
import com.example.hcp.models.hcp.divisionn;
import com.example.hcp.models.hcp.healthFacilityy;
import com.example.hcp.models.hcp.materialStatuss;
import com.example.hcp.models.hcp.occuptaionn;
import com.example.hcp.models.hcp.qualificationn;
import com.example.hcp.models.hcp.tehsill;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;


import com.santalu.maskara.widget.MaskEditText;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import asia.kanopi.fingerscan.Status;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.media.CamcorderProfile.get;
import static com.activeandroid.Cache.getContext;
import static com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004;
import static com.example.hcp.utils.Constants.context;


public class patientRegistration extends Fragment {

    private ImageView ivFingerprint, ivFingerprint2,ma_iv_fingerprint_edit;
    private Button btScan, btScan2;

    private static final int SCAN_FINGERPRINT = 1234;
    private static final int SCAN_FINGERPRINT2 = 123;
    FragmentManager fragmentManager;
    MaskEditText etContactNo;
    MaskedEditText etCNIC;
    private int m_hDevice = 0;
    Button btnSubmit, Submit,ma_bt_scan_edit;
    LinearLayout layoutfirst, layoutsecond, secondlayout, fourlayout;
    Spinner seacchcnic, occupation, materialstatus, qualification, gendr, division, district, tehsil, hf;
    Spinner firsts, seconds, thirds, fours, fives;
    String SelectedOption;
    int SelectedOptionIndex, SelectedGenderIndex, SelectedDivisionCode, SelectedDistrictedCode;
    int Selectfirstyn, Selectsecondyn, Selectthirdyn, Selectfouryn, Selectfiveyn, SelectTcode, SelectedHfcode;
    String firstVal, secondVal, thirdVal, foursVal, fiveVal;
    String OccupationVal, materialstatusVal, qualificationVal, GenderVal;
    EditText dob, etAge, etPatientName, lastname, etFatherSpouse, etCompleteAddress;

    Calendar myCalendar;
    String dateOfBirth;
    int patientage;
    public String Name;
    public String cnicNo;
    boolean isEidt = false;
    String patientname, patientcnic;
   public userdataaa patient_edit;
    ImageView image_flag;
    String encodedfingerprint;
    String encodedfingerprint2;
    List<userdataaa> allData = new ArrayList<>();

    private String m_sn = "";
    private String m_deviceName = "";
    Reader m_reader;
    private final int GENERAL_ACTIVITY_RESULT = 1;
    private static final String ACTION_USB_PERMISSION = "com.digitalpersona.uareu.dpfpddusbhost.USB_PERMISSION";
    private Engine m_engine = null;


    LinearLayout edit_layout,allformlayout;
    TextView eidt_patient_name,toolbartext,patient_cnic_edit;
     public int patientid_edit;
   public int editpatient_age;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_registration, container, false);
        fragmentManager = getFragmentManager();
        etCNIC = view.findViewById(R.id.etCNIC);
        etContactNo = view.findViewById(R.id.etContactNo);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        image_flag = view.findViewById(R.id.image_flag);
        Submit = view.findViewById(R.id.Submit);
        layoutfirst = view.findViewById(R.id.layoutfirst);
        layoutsecond = view.findViewById(R.id.layoutsecond);
        seacchcnic = view.findViewById(R.id.seacchcnic);
        occupation = view.findViewById(R.id.occupation);
        materialstatus = view.findViewById(R.id.materialstatus);
        qualification = view.findViewById(R.id.qualification);
        gendr = view.findViewById(R.id.gendr);
        division = view.findViewById(R.id.division);
        district = view.findViewById(R.id.district);
        tehsil = view.findViewById(R.id.tehsil);
        hf = view.findViewById(R.id.hf);
        dob = view.findViewById(R.id.dob);
        etAge = view.findViewById(R.id.etAge);
        firsts = view.findViewById(R.id.firsts);
        seconds = view.findViewById(R.id.seconds);
        thirds = view.findViewById(R.id.thirds);
        fours = view.findViewById(R.id.fours);
        fives = view.findViewById(R.id.fives);
        secondlayout = view.findViewById(R.id.secondlayout);
        fourlayout = view.findViewById(R.id.fourlayout);
        etPatientName = view.findViewById(R.id.etPatientName);
        toolbartext = view.findViewById(R.id.toolbartext);
        lastname = view.findViewById(R.id.lastname);
        etFatherSpouse = view.findViewById(R.id.etFatherSpouse);
        etCompleteAddress = view.findViewById(R.id.etCompleteAddress);
        ivFingerprint = view.findViewById(R.id.ma_iv_fingerprint);
        btScan = view.findViewById(R.id.ma_bt_scan);
        ivFingerprint2 = view.findViewById(R.id.ma_iv_fingerprint2);
        ma_bt_scan_edit = view.findViewById(R.id.ma_bt_scan_edit);

        eidt_patient_name = view.findViewById(R.id.patient_name_foredit);
        patient_cnic_edit = view.findViewById(R.id.patient_cnic_edit);


        ma_iv_fingerprint_edit = view.findViewById(R.id.ma_iv_fingerprint_edit);

        btScan2 = view.findViewById(R.id.ma_bt_scan2);
        edit_layout = view.findViewById(R.id.edit_layout);
        allformlayout = view.findViewById(R.id.allformlayout);

        m_engine = UareUGlobal.GetEngine();



//        edit_layout.setVisibility(View.GONE);
//        allformlayout.setVisibility(View.VISIBLE);


        encodedfingerprint = "";
        encodedfingerprint2 = "";

        allData = userdataaa.getall();

        if (getArguments() != null) {
            isEidt = getArguments().getBoolean("isEdit");
            try {
                patientname = getArguments().getString("PatientName");
                patientcnic = getArguments().getString("PatientCNIC");
                patientid_edit = getArguments().getInt("pidEdit");

            } catch (Exception e) {


            }
        }

        if (isEidt) {

            toolbartext.setText("Add Finger Print");



            if (patientcnic != null || patientid_edit !=-1) {
                List<userdataaa> patinetforfingerprint = null;
                if (!patientcnic.equals("-       -")) {
                    patinetforfingerprint = userdataaa.searchByCNICLeader(patientcnic);
                } else {
                    patinetforfingerprint = userdataaa.searchBynameLeader(patientname);
                }

                for (int i = 0; i < patinetforfingerprint.size(); i++) {
                    patient_edit = patinetforfingerprint.get(i);

                }
            }




            String editpatientname = patient_edit.getPatient_name();
            String editpatientcnic = patient_edit.getSelf_cnic();
            String editpatient_lastname = patient_edit.getLname();
            String editpatient_fathername = patient_edit.getFather_name();
            String editpatient_dob = patient_edit.getPatient_dob();
            if(patient_edit.getPatient_age()==null){
                 editpatient_age = 0;
            }else {
                editpatient_age = patient_edit.getPatient_age();
            }

            String editpatient_contact = patient_edit.getContact_no_self();
            String eidtpatient_postal_address = patient_edit.getPostal_address();
            String eidtpatient_address = patient_edit.getAddress();

            etPatientName.setText(editpatientname);
            lastname.setText(editpatient_lastname);
            etFatherSpouse.setText(editpatient_fathername);
            etContactNo.setText(editpatient_contact);
            etCNIC.setMask("");
            etCNIC.setText(editpatientcnic);
            if(eidtpatient_address != null){
                etCompleteAddress.setText(eidtpatient_address);
            }else {
                etCompleteAddress.setText(eidtpatient_postal_address);
            }

            etAge.setText(String.valueOf(editpatient_age));
            dob.setText(editpatient_dob);

            if (patient_edit.getFinger_print1() != null&& !patient_edit.getFinger_print1().equals("")) {
                byte[] previousbyte = Base64.decode(patient_edit.getFinger_print1(), Base64.DEFAULT);
                Bitmap previousBitmap = BitmapFactory.decodeByteArray(previousbyte, 0, previousbyte.length);
                ivFingerprint.setImageBitmap(previousBitmap);
            }


//            ma_bt_scan_edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ActiveAndroid.beginTransaction();
//                    try {
//                        final String xml64 = Base64.encodeToString(Constants.cap_result.getData(), Base64.DEFAULT);
//                        patient_edit.setFinger_print2(xml64);
//                        patient_edit.IsSync = 0;
//                        patient_edit.save();
//                        ActiveAndroid.setTransactionSuccessful();
//                    } finally {
//                        ActiveAndroid.endTransaction();
//                    }
//
//                    final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
//                    pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
//                    pDialog.setTitleText("Finger Print Saved");
//                    pDialog.setCancelable(false);
//                    pDialog.show();
//                    Fragment FMFragment = new DashboardFragment();
//                    Bundle args = new Bundle();
////                  args.putString("SelectedMrNo", assessment.getMrn_no());
////                  args.putInt("FamilyId", family_id);
//                    args.putString("PatientCNIC", cnicNo);
//                    args.putString("PatientName", Name);
//
//                    if (FMFragment != null) {
//
//                        getActivity().onBackPressed();
//
//                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                        FMFragment.setArguments(args);
//                        try {
//                            transaction.replace(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();
//
//                        } catch (IllegalStateException ignored) {
//                        }
//                    } else {
//                        Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//            });

        }

        etAge.setEnabled(false);
        SelectedOption = "";
        SelectedOptionIndex = 0;



        if(isEidt){
            layoutfirst.setVisibility(View.VISIBLE);
            layoutsecond.setVisibility(View.VISIBLE);
            Submit.setVisibility(View.VISIBLE);
        }
        else {
            layoutfirst.setVisibility(View.GONE);
            layoutsecond.setVisibility(View.GONE);
            Submit.setVisibility(View.GONE);
        }

        SetSearchOptions();

        btnSubmit.setOnClickListener(
                v -> Searchfingerprint()
        );

        image_flag.setOnClickListener(
                v -> Search()
        );

        Submit.setOnClickListener(
                v -> FormValidation()
        );
        DobCalculator();
        SetOccupationSpinner();
        SetMaterialSpinner();
        SetQualificationSpinner();
        SetGenderSpinner();
        SetDivisions();
        SetSpinnerfirst();
        SetSpinnersecond();
        SetSpinnerthree();
        SetSpinnerfour();
        SetSpinnerfive();

        ivFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_FINGERPRINT);
                launchGetReader();
            }
        });


        ma_iv_fingerprint_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_FINGERPRINT);
                launchGetReader();
            }
        });



        return view;
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
                    ivFingerprint.setImageBitmap(decodedByte);

                        if (allData.size() > 0) {
                            for (int i = 0; i < allData.size(); i++) {
                                if (allData.get(i).getFinger_print2() != null && !allData.get(i).getFinger_print2().isEmpty()) {
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
                                                    Toast.makeText(getContext(), "Patient Already Registered matched!", Toast.LENGTH_LONG).show();
                                                    layoutfirst.setVisibility(View.GONE);
                                                    layoutsecond.setVisibility(View.GONE);
                                                    Submit.setVisibility(View.GONE);
                                                    break;
                                                } else {
//                                                Toast.makeText(getContext(), "not matched", Toast.LENGTH_LONG).show();
                                                    layoutfirst.setVisibility(View.VISIBLE);
                                                    layoutsecond.setVisibility(View.VISIBLE);
                                                    Submit.setVisibility(View.VISIBLE);

                                                }
                                            }

                                        } catch (UareUException e) {
                                            e.printStackTrace();
                                            Log.d("----", e.getMessage());
                                        }
                                    } catch (UareUException e) {
                                        e.printStackTrace();
                                    }

                                } else {

                                    layoutfirst.setVisibility(View.VISIBLE);
                                    layoutsecond.setVisibility(View.VISIBLE);
                                    Submit.setVisibility(View.VISIBLE);
                                }
//                          Toast.makeText(getContext(), "Finger Print not found!", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            layoutfirst.setVisibility(View.VISIBLE);
                            layoutsecond.setVisibility(View.VISIBLE);
                            Submit.setVisibility(View.VISIBLE);
                        }
//                    verfityPatient();




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




    public void verfityPatient(){
        List<addPatientModel> patientModel = new ArrayList<>();
        patientModel = addPatientModel.getall();
        if(patientModel.size() > 0)


        {
            for(int i=0;i<patientModel.size();i++)

//                            final String xml64 = Base64.encodeToString(Constants.cap_result.getData(), Base64.DEFAULT);
//                        FL.setFinger_fmd(xml64.trim());
//                        List<userdataaa> abc = userdataaa.searchbyfingerprint(xml64)


                if(patientModel.get(i).getFinger_fmd()!=null) {
                    {
                        byte[] xml64Bytes = Base64.decode(patientModel.get(i).getFinger_fmd(), Base64.DEFAULT);//allData.get(i).getFinger_fmd().getBytes(StandardCharsets.UTF_8);//Base64.decode(allData.get(i).getFinger_fmd(), Base64.DEFAULT);
                        Fmd d_fmd = null;
                        Fid d_fid = null;
                        try {
                            d_fid = UareUGlobal.GetImporter().ImportFid(xml64Bytes, Fid.Format.ANSI_381_2004);
                            d_fmd = m_engine.CreateFmd(d_fid, ANSI_378_2004);

                            try {

                                if (d_fmd != null) {
                                    int m_score = m_engine.Compare(d_fmd, 0, m_engine.CreateFmd(Constants.cap_result, ANSI_378_2004), 0);
                                    if (m_score < (0x7FFFFFFF / 100000)) {
                                        Toast.makeText(getContext(), "Patient Already Registered matched!", Toast.LENGTH_LONG).show();
                                        layoutfirst.setVisibility(View.GONE);
                                        layoutsecond.setVisibility(View.GONE);
                                        Submit.setVisibility(View.GONE);
                                        break;
                                    } else {
//                                                Toast.makeText(getContext(), "not matched", Toast.LENGTH_LONG).show();
                                        layoutfirst.setVisibility(View.VISIBLE);
                                        layoutsecond.setVisibility(View.VISIBLE);
                                        Submit.setVisibility(View.VISIBLE);

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


    }
    private void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }




    private void FormValidation() {

        Name = etPatientName.getText().toString();
        String lastName = lastname.getText().toString();
        String FatherName = etFatherSpouse.getText().toString();
        String ContactNo = etContactNo.getText().toString().trim();
        cnicNo = etCNIC.getText().toString().trim();
        String Address = etCompleteAddress.getText().toString();
        Fid finger =Constants.cap_result;

        boolean Validationstatus = true;

        if(isEidt){
//            String fin = patient_edit.getFinger_print2();
//            if (fin == null || fin.equalsIgnoreCase("")) {
//                Toast.makeText(getContext(), Constants.scanyour_finger, Toast.LENGTH_LONG).show();
//                Validationstatus = false;
//            }

            if(patient_edit.getFinger_print1()==null){
                if (finger == null) {
                    Toast.makeText(getContext(), Constants.scanyour_finger, Toast.LENGTH_LONG).show();
                    Validationstatus = false;
                }
            }else
            {
                Validationstatus = true;
            }



        }else {

            if (Name.isEmpty()) {
                Toast.makeText(getContext(), Constants.NameMissing, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
//        if (seacchcnic.getSelectedItemPosition() == 1) {
//            if (cnicNo.length() != 15) {
//                Toast.makeText(getContext(), "Enter 13 Digit CNIC", Toast.LENGTH_LONG).show();
//                Validationstatus = false;
//            }
//
//        }
//        if (seacchcnic.getSelectedItemPosition() == 2 && cnicNo.length() != 14) {
//            Toast.makeText(getContext(), "Enter 13 Digit CNIC", Toast.LENGTH_LONG).show();
//            Validationstatus = false;
//        }
            if (lastName.isEmpty()) {
                Toast.makeText(getContext(), Constants.lName, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
            if (FatherName.isEmpty()) {
                Toast.makeText(getContext(), Constants.FHNameMissing, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
            if (Address.isEmpty()) {
                Toast.makeText(getContext(), Constants.AddressMissing, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
//        else if (cnicNo.length() != 15) {
//            Toast.makeText(getContext(), Constants.CompleteCNIC, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }
            if (ContactNo.length() != 12) {
                Toast.makeText(getContext(), Constants.PhoneMissing1, Toast.LENGTH_LONG).show();

                Validationstatus = false;
            }
//         if (OccupationVal.isEmpty()) {
//            Toast.makeText(getContext(), Constants.occupation, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }
            if (materialstatusVal.isEmpty()) {
                Toast.makeText(getContext(), Constants.MaritalStatusMissing, Toast.LENGTH_LONG).show();

                Validationstatus = false;
            }
//         if (qualificationVal.isEmpty()) {
//            Toast.makeText(getContext(), Constants.EducationOccupationMissing, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }
            if (SelectedGenderIndex == 0) {
                Toast.makeText(getContext(), Constants.GenderMissing, Toast.LENGTH_LONG).show();

                Validationstatus = false;
            }
//         if (SelectedDivisionCode == 0) {
//            Toast.makeText(getContext(), Constants.division, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }  if (SelectedDistrictedCode == 0) {
//            Toast.makeText(getContext(), Constants.district, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }  if (SelectTcode == 0) {
//            Toast.makeText(getContext(), Constants.tehsil, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }  if (SelectedHfcode == 0) {
//            Toast.makeText(getContext(), Constants.healthfacility, Toast.LENGTH_LONG).show();
//
//            Validationstatus = false;
//        }
            if (firstVal.equals("")) {
                Toast.makeText(getContext(), Constants.previoushbv, Toast.LENGTH_LONG).show();

                Validationstatus = false;
            }
            if (thirdVal.equals("")) {
                Toast.makeText(getContext(), Constants.previoushcv, Toast.LENGTH_LONG).show();

                Validationstatus = false;
            }
            if (dateOfBirth == null) {
                Toast.makeText(getContext(), Constants.dateofbirth, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
            if (patientage <= 6) {
                Toast.makeText(getContext(), Constants.ageislessthensixx, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
            if (patientage > 80) {
                Toast.makeText(getContext(), Constants.ageisgreatertheneighty, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
            if (finger == null) {
                Toast.makeText(getContext(), Constants.scanyour_finger, Toast.LENGTH_LONG).show();
                Validationstatus = false;
            }
        }
        if (Validationstatus) {

            if(isEidt){
                ActiveAndroid.beginTransaction();

                patient_edit.IsSync = 0;

                patient_edit.setPatient_name(Name);
                patient_edit.setLname(lastName);
                patient_edit.setFather_name(FatherName);

//                patient_edit.setPatient_age(patientage);

                patient_edit.setPatient_dob(dob.getText().toString());
                patient_edit.setGender(SelectedGenderIndex);
                patient_edit.setSelf_cnic(cnicNo);
                patient_edit.setContact_no_self(ContactNo);
                patient_edit.setAddress(Address);
                patient_edit.setMarital_status(materialstatusVal);

//                if (patientage == 80) {
//                    patient_edit.setPatient_age_80("y");
//                } else {
//                    patient_edit.setPatient_age_80("n");
//                }
                patient_edit.setPrevious_hbv(firstVal);

//            FL.setPatient_type("New Patient");
                if(firstVal.equals("y") && secondVal.equals("y")){
                    patient_edit.setPatient_type("Pre-diagnosed Patient");
                }else if(thirdVal.equals("y") && foursVal.equals("y")){
                    patient_edit.setPatient_type("Pre-diagnosed Patient");
                }else {
                    patient_edit.setPatient_type("New Patient");
                }

//                patient_edit.ISVital = 0;
//                patient_edit.IS_assessment = 0;
//                patient_edit.IS_Vaccination = 0;
//                patient_edit.ISSample = 0;

                patient_edit.setPcr_confirmation_hbv(secondVal);
                patient_edit.setPrevious_hcv(thirdVal);
                patient_edit.setPcr_confirmation_hcv(foursVal);
                patient_edit.setDivision(SelectedDivisionCode);
                patient_edit.setDistrict(SelectedDistrictedCode);
                patient_edit.setTehsil(SelectTcode);
                patient_edit.setHospital(SelectedHfcode);
                patient_edit.setIdentifier(new SharedPref(getContext()).GetserverID());
//                patient_edit.setUser_id(new SharedPref(getContext()).GetLoggedInRole());
                patient_edit.setHospital_id(new SharedPref(getContext()).GetLoggedInUser());

                if(patient_edit.getFinger_print1()!=null){
                    patient_edit.setFinger_print1(patient_edit.getFinger_print1());
                }else{
                    patient_edit.setFinger_print1(Constants.FmdBase64);
                }

                if(patient_edit.getFinger_print2()!=null){
                    patient_edit.setFinger_print2(patient_edit.getFinger_print2());
                }else {
                    final String xml64 = Base64.encodeToString(Constants.cap_result.getData(), Base64.DEFAULT);
                    patient_edit.setFinger_print2(xml64);
                }

//            byte[] adf = Constants.cap_result.getData();
//            FL.setFinger_fmd(adf);
//            FL.setWidth(Constants.width);
//            FL.setHeight(Constants.height);
//            FL.setCbeff_id(Constants.cbeff_id);
//            FL.setQuality(Constants.quality);

                patient_edit.save();

                ActiveAndroid.setTransactionSuccessful();

                ActiveAndroid.endTransaction();
            }

            else {
                userdataaa FL = new userdataaa();
                ActiveAndroid.beginTransaction();
                FL.new_patient = 1;
                FL.IsActive = 1;
                FL.mrn_no = "";
                FL.IsSync = 0;
                FL.setPatient_id(0);
                FL.setPatient_name(Name);
                FL.setLname(lastName);
                FL.setFather_name(FatherName);
                FL.setPatient_age(patientage);
                FL.setPatient_dob(dateOfBirth);
                FL.setGender(SelectedGenderIndex);
                FL.setSelf_cnic(cnicNo);
                FL.setContact_no_self(ContactNo);
                FL.setAddress(Address);
                FL.setMarital_status(materialstatusVal);
                FL.setOccupation(OccupationVal);
                FL.setQualification(qualificationVal);
                if (patientage == 80) {
                    FL.setPatient_age_80("y");
                } else {
                    FL.setPatient_age_80("n");
                }
                FL.setPrevious_hbv(firstVal);

//            FL.setPatient_type("New Patient");
                if (firstVal.equals("y") && secondVal.equals("y")) {
                    FL.setPatient_type("Pre-diagnosed Patient");
                } else if (thirdVal.equals("y") && foursVal.equals("y")) {
                    FL.setPatient_type("Pre-diagnosed Patient");
                } else {
                    FL.setPatient_type("New Patient");
                }

                FL.ISVital = 0;
                FL.IS_assessment = 0;
                FL.IS_Vaccination = 0;
                FL.ISSample = 0;

                FL.setPcr_confirmation_hbv(secondVal);
                FL.setPrevious_hcv(thirdVal);
                FL.setPcr_confirmation_hcv(foursVal);
                FL.setDivision(SelectedDivisionCode);
                FL.setDistrict(SelectedDistrictedCode);
                FL.setTehsil(SelectTcode);
                FL.setHospital(SelectedHfcode);
                FL.setIdentifier(new SharedPref(getContext()).GetserverID());
                FL.setUser_id(new SharedPref(getContext()).GetLoggedInRole());
                FL.setHospital_id(new SharedPref(getContext()).GetLoggedInUser());
                FL.setFinger_print1(Constants.FmdBase64);


                final String xml64 = Base64.encodeToString(Constants.cap_result.getData(), Base64.DEFAULT);
                FL.setFinger_print2(xml64);
//            byte[] adf = Constants.cap_result.getData();
//            FL.setFinger_fmd(adf);
//            FL.setWidth(Constants.width);
//            FL.setHeight(Constants.height);
//            FL.setCbeff_id(Constants.cbeff_id);
//            FL.setQuality(Constants.quality);

                FL.save();


                ActiveAndroid.setTransactionSuccessful();

                ActiveAndroid.endTransaction();
            }

            userdataaa patientid = new userdataaa();
            patientid = userdataaa.searchBycnic(cnicNo);



            if(isEidt){
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
                pDialog.setTitleText("Patient Updated Successfully");
                pDialog.setCancelable(false);
                pDialog.show();
                Fragment FMFragment = new DashboardFragment();
                Bundle args = new Bundle();
//            args.putString("SelectedMrNo", assessment.getMrn_no());
//            args.putInt("FamilyId", family_id);
                args.putString("PatientCNIC", cnicNo);
                args.putString("PatientName", Name);



                if(firstVal== "y" && secondVal == "y"){
                    args.putString("Patienttype", "Pre-diagnosed Patient");
                }else if(thirdVal == "y" && foursVal == "y"){
                    args.putString("Patienttype", "Pre-diagnosed Patient");
                }else {
                    args.putString("Patienttype", "New Patient");
                }




//            if(secondVal == "y"){
//                args.putString("Patienttype", "Pre-diagnosed Patient");
//            }else if (secondVal == "n"){
//                args.putString("Patienttype", "New Patient");
//            }else {
//                args.putString("Patienttype", "New Patient");
//            }
//
//            if(foursVal == "y"){
//                args.putString("Patienttype", "Pre-diagnosed Patient");
//            }else if(foursVal == "n"){
//                args.putString("Patienttype", "New Patient");
//            }else {
//                args.putString("Patienttype", "New Patient");
//            }

//            args.putString("Patienttype", "New Patient");
                args.putInt("pid", patientid.getId().intValue());


//            if(patient_id==0){
//                FDP[i].pid = vitalpatient.get(i).getId().intValue();
//            }else {
//                FDP[i].pid = vitalpatient.get(i).patient_id;
//            }


                if (FMFragment != null) {

                    getActivity().onBackPressed();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    FMFragment.setArguments(args);
                    try {
                        transaction.replace(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();

                    } catch (IllegalStateException ignored) {
                    }
                } else {
                    Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }else {
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.BUTTON_NEUTRAL);
                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
                pDialog.setTitleText("Patient Save Successfully");
                pDialog.setCancelable(false);
                pDialog.show();
                Fragment FMFragment = new vitalForm();
                Bundle args = new Bundle();
//            args.putString("SelectedMrNo", assessment.getMrn_no());
//            args.putInt("FamilyId", family_id);
                args.putString("PatientCNIC", cnicNo);
                args.putString("PatientName", Name);



                if(firstVal== "y" && secondVal == "y"){
                    args.putString("Patienttype", "Pre-diagnosed Patient");
                }else if(thirdVal == "y" && foursVal == "y"){
                    args.putString("Patienttype", "Pre-diagnosed Patient");
                }else {
                    args.putString("Patienttype", "New Patient");
                }




//            if(secondVal == "y"){
//                args.putString("Patienttype", "Pre-diagnosed Patient");
//            }else if (secondVal == "n"){
//                args.putString("Patienttype", "New Patient");
//            }else {
//                args.putString("Patienttype", "New Patient");
//            }
//
//            if(foursVal == "y"){
//                args.putString("Patienttype", "Pre-diagnosed Patient");
//            }else if(foursVal == "n"){
//                args.putString("Patienttype", "New Patient");
//            }else {
//                args.putString("Patienttype", "New Patient");
//            }

//            args.putString("Patienttype", "New Patient");
                args.putInt("pid", patientid.getId().intValue());


//            if(patient_id==0){
//                FDP[i].pid = vitalpatient.get(i).getId().intValue();
//            }else {
//                FDP[i].pid = vitalpatient.get(i).patient_id;
//            }


                if (FMFragment != null) {

                    getActivity().onBackPressed();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    FMFragment.setArguments(args);
                    try {
                        transaction.replace(R.id.content_frame, FMFragment, "patientRegistrationFragment").addToBackStack("a").commit();

                    } catch (IllegalStateException ignored) {
                    }
                } else {
                    Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

//    private void addforvital() {
//
//
//        ActiveAndroid.beginTransaction();
//        int masterid = vitalListt.getall().size() + 1;
//        List<vitalListt> vti =vitalListt.getall();
//        for (int i = 0; i < vti.size(); i++) {
//            vitalListt vt = new vitalListt();
//            vt.setSelf_cnic(cnicNo);
//            vt.setPatient_name(Name);
//            vt.setMrn_no("jg");
//            vt.setCreated("415646");
//            vt.setGender("Gender");
//            vt.id = masterid;
//            vt.setIs_blood_bank_patient("N");
//            vt.setIs_reg_completed("hgfy");
//            vt.setNext_status("2");
//            vt.setPatient_age("20");
//            vt.setPatient_type("new");
//            vt.setStage("vital");
//            vt.save();
//        }
//        try {
//
//            ActiveAndroid.setTransactionSuccessful();
//        } finally {
//            ActiveAndroid.endTransaction();
//        }
//    }


    private void DobCalculator() {
        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                dpd.show();
                // TODO Auto-generated method stub
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });



    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
        dateOfBirth = dob.getText().toString();
        LocalDate birthdate = new LocalDate(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate();
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        etAge.setText(period.getYears() + "");
        patientage = Integer.parseInt(etAge.getText().toString());

    }


    void Search() {

        String SelectedOptionVal = etCNIC.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            etCNIC.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else if (seacchcnic.getSelectedItemPosition() == 1 && SelectedOptionVal.length() != 15) {
            etCNIC.setError("Please Add 13 Digit CNIC");
        } else if (seacchcnic.getSelectedItemPosition() == 2 && SelectedOptionVal.length() != 14) {
            etCNIC.setError("Please Add 13 Digit CNIC");
        } else {
            List<userdataaa> leaders;
            switch (SelectedOptionIndex) {
                case 1:
                case 2:
                    leaders = userdataaa.searchByCNICLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {

                        Toast.makeText(getContext(), "Patient Already Exist Against This CNIC", Toast.LENGTH_LONG).show();
                        layoutfirst.setVisibility(View.GONE);
                        layoutsecond.setVisibility(View.GONE);
                    } else {

                        layoutfirst.setVisibility(View.VISIBLE);
                        layoutsecond.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.VISIBLE);
//                        btnSubmit.setVisibility(View.GONE);

                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;

            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }

    }

    void Searchfingerprint() {
//        if (encodedfingerprint.equals("")) {
//            Toast.makeText(getContext(), "Scan Finger Print", Toast.LENGTH_SHORT).show();
//            layoutfirst.setVisibility(View.GONE);
//            layoutsecond.setVisibility(View.GONE);
//        }
//        else if (encodedfingerprint2.equals("")) {
//            Toast.makeText(getContext(), "Scan Finger Print 2", Toast.LENGTH_SHORT).show();
//            layoutfirst.setVisibility(View.GONE);
//            layoutsecond.setVisibility(View.GONE);
//        }
//        else {
//            List<addPatientModel> patientlocal;
//
//            patientlocal = addPatientModel.searchbyfingerprint(encodedfingerprint);
//            double score = new FingerprintMatcher()
//                    .index(probe)
//                    .match(candidate);
//            if (score >= 40) {

//                layoutfirst.setVisibility(View.GONE);
//                layoutsecond.setVisibility(View.GONE);
//                Submit.setVisibility(View.GONE);
//                btnSubmit.setVisibility(View.VISIBLE);
            // Found a match
//            }else {
            layoutfirst.setVisibility(View.VISIBLE);
            layoutsecond.setVisibility(View.VISIBLE);
            Submit.setVisibility(View.VISIBLE);
//            btnSubmit.setVisibility(View.GONE);
//            }

//        }

    }


    void SetSearchOptions() {


        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select cnic*");
        categoriesEng.add("Self CNIC");
        categoriesEng.add("AFGHAN CNIC");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        seacchcnic.setAdapter(dataAdapter);
        seacchcnic.setSelection(1);
        seacchcnic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (seacchcnic.getSelectedItemPosition() > 0) {

                    if (seacchcnic.getSelectedItemPosition() == 1) {
                        etCNIC.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        etCNIC.setText("");
                        etCNIC.setMask("99999-9999999-9");

//                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
                    } else if (seacchcnic.getSelectedItemPosition() == 2) {

                        etCNIC.setInputType(InputType.TYPE_CLASS_TEXT);
//                        etCNIC.setText("");
                        etCNIC.setMask("AA-99999999999");

//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
//
                    }


                    SelectedOptionIndex = seacchcnic.getSelectedItemPosition();

                    SelectedOption = categoriesEng.get(SelectedOptionIndex);

                    //                    OptionValue.setText("");
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

    private void SetOccupationSpinner() {

        List<occuptaionn> occupationList = occuptaionn.getAll();

        String[] spinnerArray = new String[occupationList.size() + 1];
        spinnerArray[0] = "Select*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = occupationList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        occupation.setAdapter(adapter);

        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (occupation.getSelectedItemPosition() > 0) {

                    OccupationVal = occupationList.get(occupation.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    OccupationVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEdit) {
//            for (int i = 0; i < educationList.size(); i++) {
//                int main_status = educationList.get(i).objectListDetailId;
//                if (member.getEducationId() != null) {
//                    int selected_status = member.getEducationId();
//
//                    if (main_status == selected_status) {
//                        Education.setSelection(i + 1);
//                        EducationVal = selected_status;
//                    }
//                }
//
//            }
//        }

    }

    private void SetMaterialSpinner() {

        List<materialStatuss> materialList = materialStatuss.getAll();

        String[] spinnerArray = new String[materialList.size() + 1];
        spinnerArray[0] = "Select*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = materialList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        materialstatus.setAdapter(adapter);

        materialstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (materialstatus.getSelectedItemPosition() > 0) {

                    materialstatusVal = materialList.get(materialstatus.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    materialstatusVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {
            for (int i = 0; i < materialList.size(); i++) {
                String  main_status = materialList.get(i).name;
                if (patient_edit.getMarital_status() != null) {
                    String  selected_status = patient_edit.getMarital_status();

                    if (main_status.equals(selected_status)) {
                        materialstatus.setSelection(i + 1);
                        materialstatusVal = selected_status;
                    }
                }

            }
        }

    }

    private void SetQualificationSpinner() {

        List<qualificationn> qualificationList = qualificationn.getAll();

        String[] spinnerArray = new String[qualificationList.size() + 1];
        spinnerArray[0] = "Select*";

        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = qualificationList.get(i - 1).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        qualification.setAdapter(adapter);

        qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (qualification.getSelectedItemPosition() > 0) {

                    qualificationVal = qualificationList.get(qualification.getSelectedItemPosition() - 1).name;
                    //   Toast.makeText(getContext(), "District => " + SelectedDistrictCode + ". Tehsil => " + SelectedTehsilCode + ". UC => " + SelectedUcCode, Toast.LENGTH_SHORT).show();
                } else {
                    qualificationVal = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEdit) {
//            for (int i = 0; i < educationList.size(); i++) {
//                int main_status = educationList.get(i).objectListDetailId;
//                if (member.getEducationId() != null) {
//                    int selected_status = member.getEducationId();
//
//                    if (main_status == selected_status) {
//                        Education.setSelection(i + 1);
//                        EducationVal = selected_status;
//                    }
//                }
//
//            }
//        }

    }

    private void SetGenderSpinner() {
        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("Select*");
        categoriesEng.add("Male");
        categoriesEng.add("Female");
        categoriesEng.add("Transgender");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        gendr.setAdapter(dataAdapter);

        gendr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (gendr.getSelectedItemPosition() > 0) {
                    SelectedGenderIndex = gendr.getSelectedItemPosition();

                    GenderVal = categoriesEng.get(SelectedGenderIndex);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    GenderVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {

            if(patient_edit.getGender()!=null){
                if(patient_edit.getGender()==1){
                    gendr.setSelection(1);
                }else if(patient_edit.getGender()==2){
                    gendr.setSelection(2);
                }else if(patient_edit.getGender()==3){
                    gendr.setSelection(2);
                }
            }else {
                gendr.setSelection(0);
            }


//            List<Integer> categoriesint = new ArrayList<Integer>();
//            categoriesint.add(0);
//            categoriesint.add(1);
//            categoriesint.add(2);
//            categoriesint.add(3);
//            for (int i = 0; i < categoriesint.size(); i++) {
//                int main_status = categoriesint.get(i);
//                if(patient_edit.getGender()!=null){
//                    int selected_status = patient_edit.getGender();
//
//                    if (main_status == selected_status) {
//                        gendr.setSelection(i + 1);
//                        SelectedGenderIndex = selected_status;
//                    }
//                }
//
//            }
        }
    }

    private void SetDivisions() {

        List<divisionn> DV = divisionn.getAll();

        district.setVisibility(View.GONE);
        tehsil.setVisibility(View.GONE);
        hf.setVisibility(View.GONE);

        String[] spinnerArray = new String[DV.size() + 1];

        spinnerArray[0] = "Division*";


        for (int i = 1; i < spinnerArray.length; i++) {
            spinnerArray[i] = DV.get(i - 1).division_name;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        division.setAdapter(adapter);

        division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (division.getSelectedItemPosition() > 0) {
                    district.setVisibility(View.VISIBLE);
                    String Tname = division.getSelectedItem().toString();
//                    int TCode = districtslocal.get(districts.getSelectedItemPosition() - 1).getId();
                    int TCode = Integer.parseInt(DV.get(division.getSelectedItemPosition() - 1).division_code);
                    SelectedDivisionCode = TCode;
//                    GetTehsils(TCode);
                    SetDistrict(DV.get(division.getSelectedItemPosition() - 1).division_code);

                } else {
                    SelectedDivisionCode = 0;
                    district.setVisibility(View.GONE);
                    tehsil.setVisibility(View.GONE);
                    hf.setVisibility(View.GONE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

//        if (isEidt) {
//            for (int i = 0; i < DM.size(); i++) {
//                int main_status = Integer.parseInt(DM.get(i).detail);
//                if (singalFamily != null && singalFamily.getDistrictID()!=null) {
//                    int selected_status = singalFamily.getDistrictID();
//
//                    if (main_status == selected_status) {
//                        districts.setSelection(i + 1);
//                        SelectedDistrictCode = selected_status;
//                    }
//                }
//
//            }
//        }


    }

    private void SetDistrict(String division_code) {

        tehsil.setVisibility(View.GONE);
        hf.setVisibility(View.GONE);

        List<districtt> districte = districtt.getDistrictById(division_code);
        String[] tspinnerArray = new String[districte.size() + 1];

        tspinnerArray[0] = "District*";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = districte.get(i - 1).district_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        district.setAdapter(adapter);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (district.getSelectedItemPosition() > 0) {

                    String DCode = districte.get(district.getSelectedItemPosition() - 1).district_code;
                    SelectedDistrictedCode = Integer.parseInt(DCode);


                    tehsil.setVisibility(View.VISIBLE);
                    SetTehsil(DCode);
                } else {
//                    SelectedTehsilCode = 0;
                    SelectedDistrictedCode = 0;
                    tehsil.setVisibility(View.GONE);
                    hf.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        if (isEidt) {
//            for (int i = 0; i < tehsil.size(); i++) {
//                int main_status = Integer.parseInt(tehsil.get(i).detail);
//                if (singalFamily != null) {
//                    int selected_status = singalFamily.getTehsilID();
//
//                    if (main_status == selected_status) {
//                        tehsils.setSelection(i + 1);
//                        SelectTcode = String.valueOf(selected_status);
//                    }
//                }
//
//            }
//        }

    }

    private void SetTehsil(String dCode) {

        hf.setVisibility(View.GONE);
        List<tehsill> tehsili = tehsill.getTahsilById(dCode);
        String[] tspinnerArray = new String[tehsili.size() + 1];

        tspinnerArray[0] = "Tehsil *(تحصیل)";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = tehsili.get(i - 1).tehsil_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tehsil.setAdapter(adapter);

        tehsil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (tehsil.getSelectedItemPosition() > 0) {

                    String TCode = tehsili.get(tehsil.getSelectedItemPosition() - 1).tehsil_code;
                    SelectTcode = Integer.parseInt(TCode);


                    hf.setVisibility(View.VISIBLE);
                    SetHF(TCode);
                } else {
//                    SelectedTehsilCode = 0;
                    SelectTcode = 0;
                    hf.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        if (isEidt) {
//            for (int i = 0; i < tehsil.size(); i++) {
//                int main_status = Integer.parseInt(tehsil.get(i).detail);
//                if (singalFamily != null) {
//                    int selected_status = singalFamily.getTehsilID();
//
//                    if (main_status == selected_status) {
//                        tehsils.setSelection(i + 1);
//                        SelectTcode = String.valueOf(selected_status);
//                    }
//                }
//
//            }
//        }


    }

    private void SetHF(String tCode) {


        List<healthFacilityy> hfi = healthFacilityy.searchById(tCode);
        String[] tspinnerArray = new String[hfi.size() + 1];

        tspinnerArray[0] = "Health Facility*";

        for (int i = 1; i < tspinnerArray.length; i++) {
            tspinnerArray[i] = hfi.get(i - 1).hf_name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, tspinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hf.setAdapter(adapter);

        hf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (hf.getSelectedItemPosition() > 0) {

                    String hfCode = hfi.get(hf.getSelectedItemPosition() - 1).hf_code;
                    SelectedHfcode = Integer.parseInt(hfCode);


//                    hf.setVisibility(View.VISIBLE);


                } else {
//                    SelectedTehsilCode = 0;
                    SelectedHfcode = 0;
//                    hf.setVisibility(View.GONE);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    private void SetSpinnerfirst() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner

        firsts.setAdapter(dataAdapter);
        firsts.setSelection(2);
        firsts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (firsts.getSelectedItemPosition() > 0) {
                    Selectfirstyn = firsts.getSelectedItemPosition();
                    if (Selectfirstyn == 2) {
                        secondlayout.setVisibility(View.GONE);
                        firstVal = "n";
                    } else if (Selectfirstyn == 1) {
                        firstVal = "y";

                        secondlayout.setVisibility(View.VISIBLE);
                    } else {
                        secondlayout.setVisibility(View.VISIBLE);
                    }


//                  firstVal = yesno.get(Selectfirstyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    firstVal = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (isEidt) {

            if (patient_edit.getPrevious_hbv() != null) {
                if (patient_edit.getPrevious_hbv().equals("y")) {
                    firsts.setSelection(1);
                } else if (patient_edit.getPrevious_hbv().equals("n")) {
                    firsts.setSelection(2);
                }
            } else {
                firsts.setSelection(0);
            }
        }


    }

    private void SetSpinnersecond() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        seconds.setAdapter(dataAdapter);

        seconds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (seconds.getSelectedItemPosition() > 0) {
                    Selectsecondyn = seconds.getSelectedItemPosition();

                    if (Selectsecondyn == 1) {
                        secondVal = "y";
                    } else {
                        secondVal = "n";
                    }

//                    secondVal = yesno.get(Selectsecondyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    secondVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        if (isEidt) {

            if (patient_edit.getPcr_confirmation_hbv() != null) {
                if (patient_edit.getPcr_confirmation_hbv().equals("y")) {
                    seconds.setSelection(1);
                } else if (patient_edit.getPcr_confirmation_hbv().equals("n")) {
                    seconds.setSelection(2);
                }
            } else {
                seconds.setSelection(0);
            }
        }
    }

    private void SetSpinnerthree() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        thirds.setAdapter(dataAdapter);
        thirds.setSelection(2);
        thirds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (thirds.getSelectedItemPosition() > 0) {
                    Selectthirdyn = thirds.getSelectedItemPosition();
                    if (Selectthirdyn == 2) {
                        fourlayout.setVisibility(View.GONE);
                        thirdVal = "n";
                    } else if (Selectthirdyn == 1) {
                        fourlayout.setVisibility(View.VISIBLE);
                        thirdVal = "y";
                    } else {
                        fourlayout.setVisibility(View.VISIBLE);
                    }
//                    thirdVal = yesno.get(Selectthirdyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    thirdVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        if (isEidt) {

            if (patient_edit.getPrevious_hcv() != null) {
                if (patient_edit.getPrevious_hcv().equals("y")) {
                    thirds.setSelection(1);
                } else if (patient_edit.getPrevious_hcv().equals("n")) {
                    thirds.setSelection(2);
                }
            } else {
                thirds.setSelection(0);
            }
        }
    }

    private void SetSpinnerfour() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        fours.setAdapter(dataAdapter);

        fours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (fours.getSelectedItemPosition() > 0) {
                    Selectfouryn = fours.getSelectedItemPosition();
                    if (Selectfouryn == 2) {
                        foursVal = "n";
                    } else if (Selectfouryn == 1) {
                        foursVal = "y";
                    }
//                    foursVal = yesno.get(Selectfouryn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    foursVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        if (isEidt) {

            if (patient_edit.getPcr_confirmation_hcv() != null) {
                if (patient_edit.getPcr_confirmation_hcv().equals("y")) {
                    fours.setSelection(1);
                } else if (patient_edit.getPcr_confirmation_hcv().equals("n")) {
                    fours.setSelection(2);
                }
            } else {
                fours.setSelection(0);
            }
        }
    }

    private void SetSpinnerfive() {
        List<String> yesno = new ArrayList<String>();
        yesno.add("select one");
        yesno.add("Yes");
        yesno.add("No");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yesno);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        fives.setAdapter(dataAdapter);

        fives.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (fives.getSelectedItemPosition() > 0) {
                    Selectfiveyn = fives.getSelectedItemPosition();
                    if (Selectfiveyn == 1) {
                        fiveVal = "y";
                    } else {
                        fiveVal = "n";
                    }
//                    fiveVal = yesno.get(Selectfiveyn);
//                    OptionValue.setText("");
                    //Toast.makeText(getContext(), SearchOptions.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    fiveVal = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

//    public void compareTemplates(byte[] img) {
//        boolean status = false;
//
//
//        String id = null;
//
//        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(img);
//
//        for (int i = 0; i < allData.size(); i++) {
//
//            if (allData.get(i).getFinger_print1() != null) {
//                byte[] a = org.apache.commons.codec.binary.Base64.decodeBase64(allData.get(i).getFinger_print1().getBytes());
//
//                if (COMPARE_ISO_TEMPS(decoded, a) >= 50) {
//                    status = true;
//                    Toast.makeText(getContext(),"Find Result",Toast.LENGTH_LONG).show();
//                    break;
//                }
//            }
//
//        }
//
//    }
//
//    public int COMPARE_ISO_TEMPS(byte[] tempScanner, byte[] tempDatabase) {
//        int score = this.m_cLAPI.CompareISO_Templates(this.m_hDevice, tempScanner, tempDatabase);
//        String format = String.format("CompareANSITemplates() = %d", new Object[]{Integer.valueOf(score)});
//        return score;
//    }

    public double compareByteArrays(byte[] a, byte[] b) {
        int n = Math.min(a.length, b.length), nLarge = Math.max(a.length, b.length);
        int unequalCount = nLarge - n;
        for (int i=0; i<n; i++)
            if (a[i] != b[i]) unequalCount++;
        return unequalCount * 100.0 / nLarge;
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

        Intent i = new Intent(getContext(), VerificationActivity.class);
        i.putExtra("serial_number", m_sn);
        i.putExtra("device_name", m_deviceName);
        startActivityForResult(i, 2);
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