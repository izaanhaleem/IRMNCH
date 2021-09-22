package com.example.hcp.utils;

import android.util.Base64;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by User on 10/4/2017.
 */

public class FormatHelper {

    public static String FmdBytesToBase64Xml(byte[] bytes) {
        String str = "";

        byte[] data = Base64.encode(bytes, Base64.NO_PADDING);

        for (byte b :
                data) {
            str += (char)b;
        }

        Log.d("----",str);

        //String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Fid><Bytes>" + str + "</Bytes><Format>1769473</Format><Version>1.0.0</Version></Fid>";
        String xml = str;

        Log.d("----",xml);

        byte[] xml64Bytes = Base64.encode(xml.getBytes(), Base64.DEFAULT);

        String xml64 = "";
        for (byte b :
                xml64Bytes) {
            xml64 += (char)b;
        }

        return xml64;
    }

}
