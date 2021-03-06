/* 
 * File: 		GetCapabilitiesActivity.java
 * Created:		2013/05/03
 * 
 * copyright (c) 2013 DigitalPersona Inc.
 */

package com.example.hcp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.Reader.Capabilities;
import com.digitalpersona.uareu.Reader.Priority;
import com.example.hcp.R;

public class GetCapabilitiesActivity extends AppCompatActivity {
    private Button m_back;
    private ListView m_capabilities;
    private TextView m_title;
    private String m_sn = "";
    private String m_deviceName = "";

    private Reader reader = null;
    private Bundle savedInstanceState = null;

    Capabilities caps = null;
    Reader.Description descr = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list);

        m_title = (TextView) findViewById(R.id.title);
        m_title.setText("Get Capabilities");
        m_sn = getIntent().getExtras().getString("serial_number");
        m_deviceName = getIntent().getExtras().getString("device_name");
        m_back = (Button) findViewById(R.id.back);

        m_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // initiliaze dp sdk
        try {
            Context applContext = getApplicationContext();
            reader = Globals.getInstance().getReader(m_deviceName, applContext);
            reader.Open(Priority.EXCLUSIVE);
            caps = reader.GetCapabilities();
            descr = reader.GetDescription();
            reader.Close();
        } catch (Exception e) {
            Log.w("dummyMenu", "error during init of reader");
            Intent i = new Intent();
            i.putExtra("serial_number", "");
            i.putExtra("device_name", "");
            setResult(Activity.RESULT_OK, i);
            finish();
            return;
        }

        String[] values = new String[18 + caps.resolutions.length];
        values[0] = "Name: " + String.valueOf(descr.name);
        values[1] = "Serial Number: " + String.valueOf(descr.serial_number);
        values[2] = "Product Id: " + String.valueOf(descr.id.product_id);
        values[3] = "Product Name: " + String.valueOf(descr.id.product_name);
        values[4] = "Vendor Id: " + String.valueOf(descr.id.vendor_id);
        values[5] = "Vendor Name: " + String.valueOf(descr.id.vendor_name);
        values[6] = "Modality: " + String.valueOf(descr.modality).replace('_', ' ');
        values[7] = "Technology: " + String.valueOf(descr.technology).replace('_', ' ');
        values[8] = "Can Capture: " + String.valueOf(caps.can_capture);
        values[9] = "Can Extract Features: " + String.valueOf(caps.can_extract_features);
        values[10] = "Can Identify: " + String.valueOf(caps.can_identify);
        values[11] = "Can Match: " + String.valueOf(caps.can_match);
        values[12] = "Can Stream: " + String.valueOf(caps.can_stream);
        values[13] = "Has Calibration: " + String.valueOf(caps.has_calibration);
        values[14] = "Has Fingerprint Storage: " + String.valueOf(caps.has_fingerprint_storage);
        values[15] = "Has Power Management: " + String.valueOf(caps.has_power_management);
        values[16] = "Indicator Type: " + String.valueOf(caps.indicator_type);
        values[17] = "PIV Compliant: " + String.valueOf(caps.piv_compliant);

        for(int nCount = 0; nCount < caps.resolutions.length; nCount++)
            values[18 + nCount] = "Resolution (" + (nCount + 1) + " of " + caps.resolutions.length + "): " + String.valueOf(caps.resolutions[nCount]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        m_capabilities = (ListView) findViewById(R.id.list);
        m_capabilities.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("serial_number", m_sn);
        i.putExtra("device_name", m_deviceName);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    // called when orientation has changed to manually destroy and recreate activity
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        onCreate(savedInstanceState);

        super.onConfigurationChanged(newConfig);
    }
}
