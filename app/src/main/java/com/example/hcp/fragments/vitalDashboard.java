package com.example.hcp.fragments;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.hcp.R;
import com.example.hcp.activities.VerificationActivity;
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
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.models.hcp.vitalListt;
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

public class vitalDashboard extends Fragment {
    Button Search,allpatientList;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    MaskedEditText OptionValue;
    Spinner SearchOptions;
    RecyclerView recyclerView;
    TextView total_record;
    LinearLayout sync_data;
    int vitallistcount = 0;
    List<addvitalll> vitalss;
    ImageView scanner,ma_iv_fingerprint;

    private String m_sn = "";
    private String m_deviceName = "";
    Reader m_reader;
    private final int GENERAL_ACTIVITY_RESULT = 1;
    private static final String ACTION_USB_PERMISSION = "com.digitalpersona.uareu.dpfpddusbhost.USB_PERMISSION";
    private Engine m_engine = null;
    List<userdataaa> allData = new ArrayList<>();

    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vital_dashboard, container, false);
        m_engine = UareUGlobal.GetEngine();
        Search=view.findViewById(R.id.btnSearch);
        allpatientList=view.findViewById(R.id.allpatientList);
        OptionValue=view.findViewById(R.id.vitalSearchVal);
        SearchOptions=view.findViewById(R.id.etSearchOptionvital);
        total_record = view.findViewById(R.id.total__sync_recordv);
        sync_data = view.findViewById(R.id.sync_datav);
        ma_iv_fingerprint = view.findViewById(R.id.ma_iv_fingerprint_vital_dashboard);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);

        totalSYncREcord();

        loaddata();


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
        new vitalDashboard.ProgressAsyncTask().execute();
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
            allData = userdataaa.searchByCNICgetall();
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





    private void allvitalList() {

        List<userdataaa> vitals;
        vitals=userdataaa.searchallISvital();
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

        SelectedOptionVal = OptionValue.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
//            List<addPatientModel> vitals; addPatientModel.searchByISvital();
            List<userdataaa> patientlist;
            List<userdataaa> vitals;
            switch (SelectedOptionIndex) {

                case 3:
                case 1:

                    vitals = userdataaa.searchByName(SelectedOptionVal);
                    if (vitals.size() > 0) {

                        SetDataArrayy(vitals);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 2:
                    vitals = userdataaa.searchByISvital(SelectedOptionVal);
                    if (vitals.size() > 0) {

                                SetDataArrayy(vitals);
                            }
                            else {
                                Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                            }

                    break;
                case 4:

//                    patientlist = addPatientModel.searchByMrno(SelectedOptionVal);
//                    if (patientlist.size() > 0) {
//                        SetDataArrayy(patientlist);
//                    } else {
//                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
//                    }


            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
    }

    private void SetDataArrayy(List<userdataaa> vitalpatient) {

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

            try {
                if(vitalpatient.get(i).patient_id==0){
                    FDP[i].pid = vitalpatient.get(i).getId().intValue();
                }else {
                    FDP[i].pid = vitalpatient.get(i).patient_id;
                }
            }catch (Exception e){
                e.printStackTrace();
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
//        categoriesEng.add("Mr No");
        categoriesEng.add("CNIC");
        categoriesEng.add("Full Name");
        categoriesEng.add("Afghan CNIC");
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

//                    if(SearchOptions.getSelectedItemPosition() == 1) {
//                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
//                        OptionValue.setText("");
//                        OptionValue.setMask("AAA-99-99-99999999999");
//
////                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
//                    }else
                        if(SearchOptions.getSelectedItemPosition() == 1){

                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("99999-9999999-9");

//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
//
                    }else if(SearchOptions.getSelectedItemPosition() == 2){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("");

                    }else if(SearchOptions.getSelectedItemPosition() == 3){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AA-99999999999");

                    }
                        else if(SearchOptions.getSelectedItemPosition() == 4){
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
                                                    SetDataArrayy(allData);
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