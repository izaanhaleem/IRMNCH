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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.hcp.adapters.SearchResultAdapterSample_status;
import com.example.hcp.adapters.SearchResultAdapterTranfer_jail_jail;
import com.example.hcp.models.AdaptersData.SearchResultDatavital;
import com.example.hcp.models.Parcables.vitalDataParceable;
import com.example.hcp.models.hcp.sample_status_Table;
import com.example.hcp.models.hcp.userdataaa;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.GetReaderActivity;
import com.example.hcp.utils.Globals;
import com.example.hcp.utils.MaskedEditText;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004;

public class Dashboard_transfer_jail_to_jail extends Fragment {
    Button Search,AllSampleList;
    String SelectedOption, SelectedOptionVal;
    int SelectedOptionIndex;
    MaskedEditText OptionValue;
    Spinner SearchOptions;
    RecyclerView recyclerView;
    TextView total_record;
    LinearLayout sync_data;
    int vitallistcount = 0;

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
        View view = inflater.inflate(R.layout.fragment_transferjailtojail, container, false);
        m_engine = UareUGlobal.GetEngine();
        Search=view.findViewById(R.id.btnSearch);
        AllSampleList=view.findViewById(R.id.AllAssesementList);
        OptionValue=view.findViewById(R.id.Searchassessment);
        SearchOptions=view.findViewById(R.id.etSearchOptionAssessment);
        total_record = view.findViewById(R.id.total__sync_recordv);
        sync_data = view.findViewById(R.id.sync_datav);
        recyclerView = (RecyclerView) view.findViewById(R.id.AssessmentRecy);
//        allSampletList();

        ma_iv_fingerprint = view.findViewById(R.id.ma_iv_fingerprint_trnasferjail_dashboard);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);

        loaddata();

        SetSearchOptions();
//        AllSampleList.setOnClickListener(
//                v -> allSampletList()
//        );

        Search.setOnClickListener(
                v -> Search()
        );

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
        new Dashboard_transfer_jail_to_jail.ProgressAsyncTask().execute();
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





    private void SetSearchOptions() {
        List<String> categories = new ArrayList<String>();
        categories.add("آپشن منتخب کریں");
        categories.add("خاندان نمبر سے تلاش کریں");
        categories.add("شناختی کارڈ نمبر سے تلاش کریں");
        categories.add("نام سے تلاش کریں");
        categories.add("فون نمبر سے تلاش کریں");

        List<String> categoriesEng = new ArrayList<String>();
        categoriesEng.add("select option");
        categoriesEng.add("Mr No");
        categoriesEng.add("CNIC");
        categoriesEng.add("Afghan CNIC");
        categoriesEng.add("Full Name");
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

//                        OptionValue.addTextChangedListener(Mask.insert(Mask.Mrn_MASK, OptionValue));
                    }else if(SearchOptions.getSelectedItemPosition() == 2){

                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                        OptionValue.setText("");
                        OptionValue.setMask("99999-9999999-9");

//                        OptionValue.setInputType(InputType.TYPE_CLASS_NUMBER);
//
                    }else if(SearchOptions.getSelectedItemPosition() == 3){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("AA-99999999999");

                    }
                    else if(SearchOptions.getSelectedItemPosition() == 4){
////                        OptionValue.addTextChangedListener(new Mask("#############"));

                        OptionValue.setInputType(InputType.TYPE_CLASS_TEXT);
                        OptionValue.setText("");
                        OptionValue.setMask("");

                    }
                    else if(SearchOptions.getSelectedItemPosition() == 5){
////                        OptionValue.addTextChangedListener(new Mask("#############"));
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

    private void Search() {
        SelectedOptionVal = OptionValue.getText().toString().trim();

        if (SelectedOptionVal.isEmpty()) {
            OptionValue.setError("Select this value");
        } else if (SelectedOption.isEmpty()) {
            Toast.makeText(getContext(), "Please Select Option from Search Dropdown", Toast.LENGTH_SHORT).show();
        } else {
            List<userdataaa> transfer;
            switch (SelectedOptionIndex) {
                case 4:
                    transfer = userdataaa.searchBynamejail(SelectedOptionVal);
                    if (transfer.size() > 0) {

                        SetDataArrayy(transfer);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 2:
                case 3:
                    transfer = userdataaa.searchByPatientcnic(SelectedOptionVal);
                    if (transfer.size() > 0) {

                        SetDataArrayy(transfer);
                    }
                    else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 1:
                    transfer = userdataaa.searchByMRNOjail(SelectedOptionVal);
                    if (transfer.size() > 0) {
                        SetDataArrayy(transfer);
                    } else {
                        Toast.makeText(getContext(), "NO Record Found", Toast.LENGTH_LONG).show();
                    }
                    break;


            }

//            SearchCall(SelectedOption, SelectedOptionVal);
        }
    }

    private void SetDataArrayy(List<userdataaa> samples) {

        vitalDataParceable[] FDP = new vitalDataParceable[samples.size()];
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

            if (samples.get(i).getPatient_name() != null) {
                FDP[i].PatientName = samples.get(i).getPatient_name();
            } else {
                FDP[i].PatientName = "N/A";

            }
//            FDP[i].Gender = vitalpatient.get(i).gender;
//            FDP[i].LastName = samples.get(i).lname;
//            FDP[i].pathentContactNo = samples.get(i).contact_no_self;
            FDP[i].LeaderCNIC = samples.get(i).getSelf_cnic();
            FDP[i].MrNo = samples.get(i).getMrn_no();
//            FDP[i].sample_stauts = samples.get(i).getSample_status();
//            FDP[i].sample_number = samples.get(i).getSample_number();
//            FDP[i].hcvviralLoad = samples.get(i).getHcv_viral_load();
//            FDP[i].hbvviralLoad = samples.get(i).getHbv_viral_load();
//            FDP[i].patientType = samples.get(i).patient_type;
//            FDP[i].pid = samples.get(i).getId().intValue();
//            if(samples.get(i).patient_id==0){
                FDP[i].pid = samples.get(i).getPatient_id();
//            }else {
//                FDP[i].pid = samples.get(i).patient_id;
//            }
        }

        SearchResultDatavital[] myListData = new SearchResultDatavital[FDP.length] ;

        for(int i=0;i<myListData.length ; i++)
        {
            myListData[i] = new SearchResultDatavital();
            myListData[i].setPatientName(FDP[i].PatientName);
            myListData[i].setMrNo(FDP[i].MrNo);
//            myListData[i].setGneder(FDP[i].LastName);
            myListData[i].setLeaderCNIC(FDP[i].LeaderCNIC);
//            myListData[i].setSample_staus(FDP[i].sample_stauts);
//            myListData[i].setSample_number(FDP[i].sample_number);
//            myListData[i].setHbvviralload(FDP[i].hbvviralLoad);
//            myListData[i].setHcvviralload(FDP[i].hcvviralLoad);
//            myListData[i].setPatienttype(FDP[i].patientType);
            myListData[i].setPid(FDP[i].pid);

        }
        SearchResultAdapterTranfer_jail_jail adapter = new SearchResultAdapterTranfer_jail_jail(myListData);
        recyclerView.setHasFixedSize(true);
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
//                                                    SetDataArrayy(allData);
                                                    List<userdataaa> samo = new ArrayList<>();
                                                    samo.add(allData.get(i));
                                                    SetDataArrayy(samo);
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