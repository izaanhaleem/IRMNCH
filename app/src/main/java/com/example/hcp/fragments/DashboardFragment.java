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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.activeandroid.ActiveAndroid;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbException;
import com.digitalpersona.uareu.dpfpddusbhost.DPFPDDUsbHost;
import com.example.hcp.CustomBroadCastReceiver;
import com.example.hcp.EditTextTelefoneMask;
import com.example.hcp.Mask;
import com.example.hcp.R;
import com.example.hcp.activities.LoginActivity;
import com.example.hcp.activities.MainActivity;

import com.example.hcp.activities.ScanActivity;
import com.example.hcp.activities.VerificationActivity;
import com.example.hcp.adapters.SearchResultAdapter;
import com.example.hcp.adapters.SearchResultAdapterforFingerprint;
import com.example.hcp.adapters.SearchResultAdapternewpatieints;
import com.example.hcp.models.AdaptersData.SearchResultData;
import com.example.hcp.models.Parcables.PatientDataParceable;
import com.example.hcp.models.Users.UserResponse;
import com.example.hcp.models.hcp.AddVitalResponse;
import com.example.hcp.models.hcp.AssessmentResponse;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.models.hcp.ReleaseLocalTable;
import com.example.hcp.models.hcp.ReleseResponse;
import com.example.hcp.models.hcp.SampleRequest;
import com.example.hcp.models.hcp.Samplee;
import com.example.hcp.models.hcp.VaccinationRequest;
import com.example.hcp.models.hcp.VaccinationResponse;
import com.example.hcp.models.hcp.Vaccinationn;
import com.example.hcp.models.hcp.addAssessmentRequest;
import com.example.hcp.models.hcp.addPatientModel;
import com.example.hcp.models.hcp.addPatientRequest;
import com.example.hcp.models.hcp.addPatientResponse;
import com.example.hcp.models.hcp.addVitalRequest;
import com.example.hcp.models.hcp.addvitalll;
import com.example.hcp.models.hcp.jailListTable;
import com.example.hcp.models.hcp.jailObjectModel;
import com.example.hcp.models.hcp.medicinee;
import com.example.hcp.models.hcp.releaseObject;
import com.example.hcp.models.hcp.sampleResponse;
import com.example.hcp.models.hcp.savejail;
import com.example.hcp.models.hcp.submitJailResponse;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;
import com.example.hcp.services.RetrofitClient;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.GetReaderActivity;
import com.example.hcp.utils.Globals;
import com.example.hcp.utils.MaskedEditText;
import com.example.hcp.utils.SharedPref;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import asia.kanopi.fingerscan.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004;


public class DashboardFragment extends Fragment {

    FragmentManager fragmentManager;
    Spinner SearchOptions;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    MaskedEditText OptionValue;
    Button Search, Register, export_db;
    //   private ProgressDialog dialog;
    ImageView scanner,ma_iv_fingerprint;
    public String encodedfingerprint;
    LinearLayout sync_data,editlayout;
    TextView total_record,vitalcount,assessmentcount,vaccinationcount,samplecount;
    int patientssubmitcount = 0;
    int vitalsubmitcount = 0;
    int assessmentsubmitcount = 0;
    int vaccinationsubmitcount = 0;
    int samplesubmitcount = 0;
    int pendingsubmitcount = 0;
    int syncedpatients = 0, syncedvitals = 0, syncedassessment = 0, syncedpending = 0;
    int CurremtIndex = 0;



    List<userdataaa> paitents;
    List<addvitalll> vitals;
    List<Assessmentt> assessmentts;
    List<Vaccinationn> vaccinationns;
    List<Samplee> sampless;
    List<savejail> jailes;
    List<ReleaseLocalTable> releases;

    List<medicinee> pending;
    public String current_fmd;

    private static final int SCAN_FINGERPRINT = 1234;

    FingerprintTemplate probe;
    private String m_sn = "";
    private String m_deviceName = "";
    Reader m_reader;
    private final int GENERAL_ACTIVITY_RESULT = 1;
    private static final String ACTION_USB_PERMISSION = "com.digitalpersona.uareu.dpfpddusbhost.USB_PERMISSION";
    private Engine m_engine = null;
    List<userdataaa> allData = new ArrayList<>();
    public int totalSize;
    RecyclerView recyclerView;

    private ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_family_fragment, container, false);
        m_engine = UareUGlobal.GetEngine();
        fragmentManager = getFragmentManager();
        total_record = view.findViewById(R.id.total__sync_record);
        SearchOptions = view.findViewById(R.id.etSearchOption);
        OptionValue = view.findViewById(R.id.etSearchVal);
        Search = view.findViewById(R.id.btnSearch);
        Register = view.findViewById(R.id.btnReg);
        scanner = view.findViewById(R.id.scanner);
        SelectedOptionIndex = 0;
        sync_data = view.findViewById(R.id.sync_data);
        vitalcount = view.findViewById(R.id.vitalcount);
        assessmentcount = view.findViewById(R.id.assessmentcount);
        vaccinationcount = view.findViewById(R.id.vaccinationcount);
        samplecount = view.findViewById(R.id.samplecount);
        editlayout = view.findViewById(R.id.editlayout);
        ma_iv_fingerprint = view.findViewById(R.id.ma_iv_fingerprint_dashboard);
        SelectedOption = "";
        SelectedOptionVal = "";

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);
//        allData = userdataaa.getall();
//        getActivity().runOnUiThread(new Runnable(){
//            @Override
//            public void run(){
//                allData = userdataaa.getall();
//                // Do whatever you want
//            }
//        });

        StartProgress();



        export_db = view.findViewById(R.id.export_db);
        recyclerView = (RecyclerView) view.findViewById(R.id.newpatient);

        allnewpatientList();

        SetSearchOptions();

        totalSYncREcord();

        pendingvitals();
//        pendingassessments();
//        pendingvaccinations();
//        pendingsampels();



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


        ma_iv_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_FINGERPRINT);
                launchGetReader();
            }
        });


//        OptionValue.addTextChangedListener(EditTextTelefoneMask.insert(OptionValue));
        return view;
    }

    public void StartProgress(){
        new ProgressAsyncTask().execute();
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

    private void allnewpatientList() {
        List<userdataaa> newpatient;
        newpatient=userdataaa.searchallnewPatients();
        SetDataArrayynewpatient(newpatient);
    }

    private void SetDataArrayynewpatient(List<userdataaa> newpatient) {
        PatientDataParceable[] FDP = new PatientDataParceable[newpatient.size()];
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

            if (newpatient.get(i).patient_name != null) {
                FDP[i].PatientName = newpatient.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
            FDP[i].contactNo = newpatient.get(i).contact_no_self;
            FDP[i].LeaderCNIC = newpatient.get(i).self_cnic;
            FDP[i].MrNo = newpatient.get(i).mrn_no;

//            if(newpatient.get(i).finger_print2==null){
//                FDP[i].fingerprint = "notfound";
//            }else {
//                FDP[i].fingerprint = newpatient.get(i).finger_print2;
//            }

        }
        SearchResultData[] myListData = new SearchResultData[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultData();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].MrNo);
            myListData[i].setContactNo(FDP[i].contactNo);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
        }

        SearchResultAdapternewpatieints adapter = new SearchResultAdapternewpatieints(myListData,getContext());
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


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
//                                                    SetDataArrayy(allData.get(i));
                                                    List<userdataaa> samo = new ArrayList<>();
                                                    samo.add(allData.get(i));
                                                    SetDataArrayy(samo);
//                                                    allData.get(i).getFinger_print2();
                                                    break;
                                                } else {

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
                        if(isfingermatch==false){
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

    private void SetDataArrayyl(userdataaa userdataaa) {
        PatientDataParceable FDP = new PatientDataParceable();
            if (userdataaa.patient_name != null) {
                FDP.PatientName = userdataaa.patient_name;
            } else {
                FDP.PatientName = "N/A";

            }
            FDP.contactNo = userdataaa.contact_no_self;
            FDP.LeaderCNIC = userdataaa.self_cnic;
            FDP.MrNo = userdataaa.mrn_no;

        Fragment SRFragment = new SearchResultFingerprint();

        Bundle args = new Bundle();
//        args.putParcelable("FDP", FDP);
        args.putString("name",userdataaa.getPatient_name());
        args.putString("mrno",userdataaa.mrn_no);
        args.putString("contact",userdataaa.contact_no_self);
        args.putString("cnic",userdataaa.getSelf_cnic());

        if (SRFragment != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            SRFragment.setArguments(args);
            try {
                transaction.add(R.id.content_frame, SRFragment, "searchFragment").addToBackStack(null).commit();
            } catch (IllegalStateException ignored) {
            }
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
    //    private void toast(String msg){
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//    }
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

        SelectedOptionVal = OptionValue.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
            List<userdataaa> leaders;
            List<addPatientModel> localpatinets;
            switch (SelectedOptionIndex) {
                case 1:
                    leaders = userdataaa.searchByMRNOLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {
                        SetDataArrayy(leaders);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 3:
                case 2:

                    leaders = userdataaa.searchByCNICLeader(SelectedOptionVal);
                    localpatinets = addPatientModel.searchByCNICLeader(SelectedOptionVal);

                    if (leaders.size() != 0) {
                        SetDataArrayy(leaders);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 4:
                    leaders = userdataaa.searchBynameLeader(SelectedOptionVal);
                    localpatinets = addPatientModel.searchBynameLeader(SelectedOptionVal);
                    if (leaders.size() != 0) {
                        SetDataArrayy(leaders);
                    }
//                    else if(localpatinets.size()!=0) {
//                        SetDataArrayylocal(localpatinets);
//                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 5:
                    leaders = userdataaa.searchByPhoneLeader(SelectedOptionVal);
                    if (leaders.size() > 0) {
                        SetDataArrayy(leaders);
                    }
//                    else if(localpatinets.size() !=0){
//                        SetDataArrayylocal(localpatinets);
//                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 6:
//                    leaders = userdataaa.searchbyfingerprint(encodedfingerprint);
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
        categoriesEng.add("Mr No");
        categoriesEng.add("CNIC");
        categoriesEng.add("Afghan CNIC");
        categoriesEng.add("Full Name");
        categoriesEng.add("Contact No");
        categoriesEng.add("Finger Print");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categoriesEng);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // attaching data adapter to spinner
        SearchOptions.setAdapter(dataAdapter);
        SearchOptions.setSelection(2);
        SearchOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (SearchOptions.getSelectedItemPosition() > 0) {

                    if(SearchOptions.getSelectedItemPosition() == 1) {
                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AAA-99-99-99999999999");
                        ma_iv_fingerprint.setVisibility(View.GONE);
//                        editlayout.setVisibility(View.VISIBLE);
//                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
                    }else if(SearchOptions.getSelectedItemPosition() == 2){

                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("99999-9999999-9");
//                        editlayout.setVisibility(View.VISIBLE);
//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        ma_iv_fingerprint.setVisibility(View.GONE);
                    }else if(SearchOptions.getSelectedItemPosition() == 3){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AA-99999999999");
                        ma_iv_fingerprint.setVisibility(View.GONE);
//                        editlayout.setVisibility(View.VISIBLE);
                    }else if(SearchOptions.getSelectedItemPosition() == 4){
////                        OptionValue.addTextChangedListener(new Mask("####-#######"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("");


                        ma_iv_fingerprint.setVisibility(View.GONE);
//                        editlayout.setVisibility(View.VISIBLE);
                    }else if(SearchOptions.getSelectedItemPosition() == 5){

                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("9999-9999999");
                        OptionValue.setHint("9999-9999999");
                        ma_iv_fingerprint.setVisibility(View.GONE);
                    }else if(SearchOptions.getSelectedItemPosition() == 6){
                        //finger print search

                        ma_iv_fingerprint.setVisibility(View.VISIBLE);
                    }

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

    public void SetDataArrayy(List<userdataaa> SFR) {
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
            FDP[i].patientType = SFR.get(i).patient_type;
            FDP[i].MrNo = SFR.get(i).mrn_no;
            if(SFR.get(i).getPatient_id()==0){
                FDP[i].pid = (Integer) SFR.get(i).getId().intValue();
            }else {
                FDP[i].pid = (Integer) SFR.get(i).getPatient_id();
            }
//            FDP[i].pid = (Integer) SFR.get(i).getId().intValue();
            if(SFR.get(i).finger_print2==null || SFR.get(i).getFinger_print2().equalsIgnoreCase("")){
                FDP[i].fingerprint = "notfound";
            }else {
                FDP[i].fingerprint = SFR.get(i).finger_print2;
            }

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
    public void SetDataArrayylocal(List<addPatientModel> lcoal) {
        PatientDataParceable[] FDP = new PatientDataParceable[lcoal.size()];
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

            if (lcoal.get(i).patient_name != null) {
                FDP[i].PatientName = lcoal.get(i).patient_name;
            } else {
                FDP[i].PatientName = "N/A";

            }
            FDP[i].contactNo = lcoal.get(i).contact_no_self;
            FDP[i].LeaderCNIC = lcoal.get(i).self_cnic;
            FDP[i].MrNo = lcoal.get(i).mrn_no;
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
    public void pendingvitals(){
        List<userdataaa> pendingvitals = userdataaa.searchallISvital();
        List<userdataaa> pendingassessment = userdataaa.pendingassessments();
        List<userdataaa> pendingvaccination = userdataaa.pendingvaccination();
        List<userdataaa> pendingsamples = userdataaa.pendingsamples();
        vitalcount.setText(String.valueOf(pendingvitals.size()));
        assessmentcount.setText(String.valueOf(pendingassessment.size()));
        vaccinationcount.setText(String.valueOf(pendingvaccination.size()));
        samplecount.setText(String.valueOf(pendingsamples.size()));
    }

    public void totalSYncREcord() {

        List<userdataaa> patient = userdataaa.searchBySync();
        List<addvitalll> vitals = addvitalll.searchBySync();
        List<Assessmentt> assessments = Assessmentt.searchBySync();
        List<Vaccinationn> vacc = Vaccinationn.searchBySync();
        List<Samplee> sample = Samplee.searchBySync();
        List<medicinee> medici = medicinee.searchBySync();
        List<savejail> jail = savejail.searchBySync();
        List<ReleaseLocalTable> relese = ReleaseLocalTable.searchBySync();
//      List<medicinee> medicine = medicinee.searchBySync();
        totalSize = patient.size() + vitals.size() + assessments.size() + sample.size()+vacc.size()+medici.size()+jail.size()+relese.size();
        total_record.setText(totalSize + "");
    }

    //
    public void SyncRecord() {

        patientssubmitcount = 0;
        vitalsubmitcount = 0;
        assessmentsubmitcount = 0;
        vaccinationsubmitcount = 0;
        samplesubmitcount = 0;
//        pendingsubmitcount = 0;


        paitents = userdataaa.searchBySync();
        patientssubmitcount = paitents.size();



        if (paitents.size() > 0) {
            submitePatients();
        } else {
            submitvitalData();
            submitAssessment();
            submitVaccination();
            submitSamples();

            submitjailTransfers();
            submitjailRelease();
        }

    }


    public void submitePatients()

    {
        for (int i = 0; i < paitents.size(); i++)

        {
            addPatientRequest fmb = new addPatientRequest();
            if (paitents.get(i).getPatient_id() != null) {
                fmb.setPatient_id(paitents.get(i).getPatient_id());
            }
            if (paitents.get(i).getPatient_name() != null) {
                fmb.setPatient_name(paitents.get(i).getPatient_name());
            }
            if (paitents.get(i).getLname() != null) {
                fmb.setLname(paitents.get(i).getLname());
            }
            if (paitents.get(i).getFather_name() != null) {
                fmb.setFather_name(paitents.get(i).getFather_name());
            }
            if (paitents.get(i).getPatient_age() != null) {
                fmb.setPatient_age(Integer.parseInt(paitents.get(i).getPatient_age()));
            } else {
                fmb.setPatient_age(0);

            }
            if (paitents.get(i).getPatient_dob() != null) {
                fmb.setPatient_dob(paitents.get(i).getPatient_dob());
            }
            if (paitents.get(i).getGender() != null) {
                fmb.setGender(paitents.get(i).getGender());
            } else {
                fmb.setGender(0);
            }
            if (paitents.get(i).getSelf_cnic() != null) {

                if(paitents.get(i).getSelf_cnic().equalsIgnoreCase("-       -")){
                    fmb.setIs_cnic(0);
                    fmb.setSelf_cnic("");
                }else {
                    fmb.setSelf_cnic(paitents.get(i).getSelf_cnic());
                    fmb.setIs_cnic(1);
                }

            }else {
                fmb.setSelf_cnic(null);
            }

            if(paitents.get(i).getCnic_type() !=null){
                fmb.setCnic_type(paitents.get(i).getCnic_type());
            }else {
                fmb.setCnic_type("");
            }

            if(paitents.get(i).getPrison_type() !=null){
                fmb.setPrison_type(paitents.get(i).getPrison_type());
            }else {
                fmb.setPrison_type(0);
            }


            if (paitents.get(i).getContact_no_self() != null) {
                fmb.setContact_no_self(paitents.get(i).getContact_no_self());
            }
            if (paitents.get(i).getAddress() != null) {
                fmb.setAddress(paitents.get(i).getAddress());
            }else {
                fmb.setAddress("");
            }
            if (paitents.get(i).getMarital_status() != null) {
                fmb.setMarital_status(paitents.get(i).getMarital_status());
            }else {
                fmb.setMarital_status("");
            }
            if (paitents.get(i).getOccupation() != null) {
                fmb.setOccupation(paitents.get(i).getOccupation());
            }else {
                fmb.setOccupation("");
            }
            if (paitents.get(i).getQualification() != null) {
                fmb.setQualification(paitents.get(i).getQualification());
            }else {
                fmb.setQualification("");
            }
            if (paitents.get(i).getPatient_age_80() != null) {
                fmb.setPatient_age_80(paitents.get(i).getPatient_age_80());
            }else {
                fmb.setPatient_age_80("");
            }
            if (paitents.get(i).getPrevious_hbv() != null) {
                fmb.setPrevious_hbv(paitents.get(i).getPrevious_hbv());
            }
            if (paitents.get(i).getPrevious_hcv() != null) {
                fmb.setPrevious_hcv(paitents.get(i).getPrevious_hcv());
            }

//            fmb.setIsActive(leaders.get(i).getIsActive());
            if (paitents.get(i).getPcr_confirmation_hbv() != null) {
                fmb.setPcr_confirmation_hbv(paitents.get(i).getPcr_confirmation_hbv());
            }

            if (paitents.get(i).getPcr_confirmation_hcv() != null) {
                fmb.setPcr_confirmation_hcv(paitents.get(i).getPcr_confirmation_hcv());
            }

            if (paitents.get(i).getDivision() != 0) {
                fmb.setDivision(paitents.get(i).getDivision());
            } else {
                fmb.setDivision(0);

            }

            if (paitents.get(i).getDistrict() != 0) {
                fmb.setDistrict(paitents.get(i).getDistrict());
            } else {
                fmb.setDistrict(0);

            }

            if (paitents.get(i).getTehsil() != 0) {
                fmb.setTehsil(paitents.get(i).getTehsil());
            } else {
                fmb.setTehsil(0);

            }

            if (paitents.get(i).getHospital() != 0) {
                fmb.setHospital(paitents.get(i).getHospital());
            } else {
                fmb.setHospital(0);

            }
            if (paitents.get(i).getIdentifier() != null) {
                fmb.setIdentifier(paitents.get(i).getIdentifier());
            }

            if (paitents.get(i).getUser_id() != null) {
                fmb.setUser_id(paitents.get(i).getUser_id());
            }

            if (paitents.get(i).getHospital_id() != null) {
                fmb.setHospital_id(paitents.get(i).getHospital_id());
            }
            if (paitents.get(i).getPatient_type() != null) {
                fmb.setPatient_type(paitents.get(i).getPatient_type());
            }else {
                fmb.setPatient_type("");
                  }
            if (paitents.get(i).getId() != null) {
                fmb.setMobile_id(paitents.get(i).getId());
            } else {
                fmb.setMobile_id(0L);
            }
            if(paitents.get(i).getFinger_print1() !=null){
                fmb.setFinger_print1(paitents.get(i).getFinger_print1());
            }else {
                fmb.setFinger_print1(null);
            }
            if(paitents.get(i).getFinger_print2() !=null){
                fmb.setFinger_print2(paitents.get(i).getFinger_print2());
            }else {
                fmb.setFinger_print2(null);
            }

            if(paitents.get(i).getPrison_type() !=null){
                fmb.setPrison_type(paitents.get(i).getPrison_type());
            }else {
                fmb.setPrison_type(0);
            }

//            if(paitents.get(i).getCnic_type() !=null){
//                fmb.setCnic_type(paitents.get(i).getCnic_type());
//            }else {
//                fmb.setCnic_type("");
//            }


            Submitpatients(fmb, paitents.get(i), paitents.size(), i);


//               SubmitLeader(fmb);
        }

    }
//    }

    public void Submitpatients(addPatientRequest currentMember, userdataaa patientTable, int totalPatient, int currentPatient) {

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

                    if (response.body().getStatus_code() == 200) {

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

                            patientTable.IsSync = 1;
                            patientTable.mrn_no = response.body().getData().getMrn_no();
                            patientTable.patient_id = response.body().getData().getPatient_id();
                            patientTable.save();

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

                            Vaccinationn vac = Vaccinationn.searchBypid(response.body().getData().getMobile_id());
//                                   if(assess.patient_id == response.body().getData().getPatient_id()) {
                            if (vac != null) {
                                vac.id = response.body().getData().getPatient_id();
                                vac.save();
                            } else {
                                Toast.makeText(getContext(), "Vaccination not found", Toast.LENGTH_SHORT).show();
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
                            submitVaccination();
                            submitSamples();
                            submitjailTransfers();
                            submitjailRelease();

                        }
                    }
//                    else {
//                        Toast.makeText(getContext(), "" + response.message().toString(), Toast.LENGTH_SHORT).show();
//                    }

                   else if (response.body().getStatus_code() == 203) {

                    Toast.makeText(getContext(), "" + response.body().getMessage().toString(), Toast.LENGTH_LONG).show();

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

                            patientTable.IsSync = 1;
                            patientTable.IS_delete = 1;
                            if (response.body().getData().getMrn_no() != null) {
                                patientTable.mrn_no = response.body().getData().getMrn_no();
                            } else {
                                patientTable.mrn_no = "AAA-00-0000000";
                            }
                            patientTable.patient_id = response.body().getData().getPatient_id();
                            patientTable.deleted_hf_name = response.body().getData().getHf_name();
                            patientTable.deleted_stage = response.body().getData().getStage();
                            patientTable.deleted_name = response.body().getData().getPatient_name();
                            patientTable.deleted_cnic = response.body().getData().getCnic();
                            patientTable.deleted_mrn_no = response.body().getData().getMrn_no();

                            patientTable.save();


                            addvitalll vtl = addvitalll.searchBypid(response.body().getData().getMobile_id());
                            if (vtl != null) {
                                vtl.pid = response.body().getData().getPatient_id();
                                vtl.IsSync = 1;
                                vtl.save();
                            } else {

                                patientTable.ISVital = 1;
                                patientTable.save();

                                Toast.makeText(getContext(), "vital not found", Toast.LENGTH_SHORT).show();
                            }


                            Assessmentt assess = Assessmentt.searchBypid(response.body().getData().getMobile_id());
//                                   if(assess.patient_id == response.body().getData().getPatient_id()) {
                            if (assess != null) {
                                assess.patient_id = response.body().getData().getPatient_id();
                                assess.IsSync = 1;
                                assess.save();
                            } else {
                                patientTable.IS_assessment = 1;
                                patientTable.save();
                                Toast.makeText(getContext(), "Assessment not found", Toast.LENGTH_SHORT).show();
                            }

                            Vaccinationn vac = Vaccinationn.searchBypid(response.body().getData().getMobile_id());
//                                   if(assess.patient_id == response.body().getData().getPatient_id()) {
                            if (vac != null) {
                                vac.id = response.body().getData().getPatient_id();
                                vac.IsSync = 1;
                                vac.save();
                            } else {
                                patientTable.IS_Vaccination = 1;
                                patientTable.save();
                                Toast.makeText(getContext(), "Vaccination not found", Toast.LENGTH_SHORT).show();
                            }


                            Samplee ss = Samplee.searchBypid(response.body().getData().getMobile_id());
                            if (ss != null) {
                                ss.pid = response.body().getData().getPatient_id();
                                ss.IsSync = 1;
                                ss.save();
                            } else {
                                patientTable.ISSample = 1;
                                patientTable.save();
                                Toast.makeText(getContext(), "sample not found", Toast.LENGTH_SHORT).show();
                            }


                            ActiveAndroid.setTransactionSuccessful();
                        } finally {
                            ActiveAndroid.endTransaction();
                        }

                        if (currentPatient + 1 == totalPatient) {
                            submitvitalData();
                            submitAssessment();
                            submitVaccination();
                            submitSamples();
                            submitjailTransfers();

                        }
                    } else {
                        Toast.makeText(getContext(), "" + response.message().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                     else {
                        Toast.makeText(getContext(), "" + response.message().toString(), Toast.LENGTH_SHORT).show();
                        }

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

    private void submitVaccination(){
        vaccinationns = Vaccinationn.searchBySync();
        if (vaccinationns.size() > 0) {

            for (int i = 0; i < vaccinationns.size(); i++) {
                VaccinationRequest vac = new VaccinationRequest();
                if (vaccinationns.get(i).id != null) {
                    vac.setId(vaccinationns.get(i).id);
                }
                if (vaccinationns.get(i).stage != null) {
                    vac.setStage(vaccinationns.get(i).stage);
                } else {
                    vac.setStage(0);
                }
                if (vaccinationns.get(i).dose_date != null) {
                    vac.setDose_date(vaccinationns.get(i).dose_date);
                } else {
                    vac.setDose_date("0");
                }

                if (vaccinationns.get(i).created != null) {
                    vac.setCreated(vaccinationns.get(i).created);
                } else {
                    vac.setCreated("0");

                }

                if (vaccinationns.get(i).user_id != null) {
                    vac.setUser_id(vaccinationns.get(i).user_id);
                } else {
                    vac.setUser_id(0);

                }

                if (vaccinationns.get(i).hospital_id != null) {
                    vac.setHospital_id(vaccinationns.get(i).hospital_id);
                } else {
                    vac.setHospital_id(0);
                }

                if (vaccinationns.get(i).updated != null) {
                    vac.setUpdated(vaccinationns.get(i).updated);
                } else {
                    vac.setUpdated("0");
                }

                if (vaccinationns.get(i).mobile_id != null) {
                    vac.setMobile_id(vaccinationns.get(i).mobile_id);
                } else {
                    vac.setMobile_id(0);
                }

//                List<addPatientModel> fli = addPatientModel.getall();
//
//                sampless.get(i).pid = fli.get(i).getPatient_id();

                submitvaccine(vac);

            }

        }
    }

    private void submitvaccine(VaccinationRequest vac) {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<VaccinationResponse> call = RetrofitClient
                .getInstance().getApi().saveVaccination(vac);
        call.enqueue(new Callback<VaccinationResponse>() {
            @Override
            public void onResponse(Call<VaccinationResponse> call, Response<VaccinationResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    ActiveAndroid.beginTransaction();
                    try {
                        Vaccinationn vc = Vaccinationn.searchBypid(vac.getId());
                        vc.IsSync = 1;
                        vc.save();
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
            public void onFailure(Call<VaccinationResponse> call, Throwable t) {

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

    private void submitjailTransfers() {
        int h=Integer.parseInt(new SharedPref(getContext()).GetLoggedInUser());
        jailes = savejail.searchBySync();
        if (jailes.size() > 0) {

            for (int i = 0; i < jailes.size(); i++) {
                jailObjectModel jai = new jailObjectModel();
                if (jailes.get(i).patient_id != null) {
                    jai.setPatient_id(jailes.get(i).patient_id);
                }
                if (jailes.get(i).getPrison_transfer_status() != null) {
                    jai.setPrison_transfer_status(jailes.get(i).getPrison_transfer_status());
                } else {
                    jai.setPrison_transfer_status("test");
                }
                if (jailes.get(i).getCurrent_hospital_name() != null) {
                    jai.setCurrent_hospital_name(jailes.get(i).getCurrent_hospital_name());
                } else {
                    jai.setCurrent_hospital_name("test");
                }

                if (jailes.get(i).getNew_hospital_id() != null) {
                    jai.setNew_hospital_id(jailes.get(i).getNew_hospital_id());
                } else {
                    jai.setNew_hospital_id(0);
                }

                if (jailes.get(i).getNew_hospital_name() != null) {
                    jai.setNew_hospital_name(jailes.get(i).getNew_hospital_name());
                } else {
                    jai.setNew_hospital_name("test");
                }

                SubmitjailTransfers(jai);

            }

        }

    }

    private void SubmitjailTransfers(jailObjectModel jai) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Vital please wait . . ");
        dialog.show();
        Call<submitJailResponse> call = RetrofitClient
                .getInstance().getApi().saveTranferJails(jai);
        call.enqueue(new Callback<submitJailResponse>() {
            @Override
            public void onResponse(Call<submitJailResponse> call, Response<submitJailResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    ActiveAndroid.beginTransaction();
                    try {
                        savejail jal = savejail.searchBypid(jai.getPatient_id());
                        jal.IsSync = 1;
                        jal.save();
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
            public void onFailure(Call<submitJailResponse> call, Throwable t) {

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

    private void submitjailRelease() {

        releases = ReleaseLocalTable.searchBySync();
        if (releases.size() > 0) {

            for (int i = 0; i < releases.size(); i++) {
                releaseObject rel = new releaseObject();
                if (releases.get(i).getPatient_id() != null) {
                    rel.setPatient_id(releases.get(i).getPatient_id());
                }


                SubmitReleasePatients(rel);

            }

        }

    }

    private void SubmitReleasePatients(releaseObject rel) {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Saving Patient Release please wait . . ");
        dialog.show();
        Call<ReleseResponse> call = RetrofitClient
                .getInstance().getApi().submitreleasepatients(rel);
        call.enqueue(new Callback<ReleseResponse>() {
            @Override
            public void onResponse(Call<ReleseResponse> call, Response<ReleseResponse> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    ActiveAndroid.beginTransaction();
                    try {
                        ReleaseLocalTable jali = ReleaseLocalTable.searchBypid(rel.getPatient_id());
                        jali.setIs_sycn(1);
                        jali.save();
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
            public void onFailure(Call<ReleseResponse> call, Throwable t) {

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
