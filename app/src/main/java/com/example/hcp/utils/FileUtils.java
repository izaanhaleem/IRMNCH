package com.example.hcp.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileUtils {
    //importing database
    private void importDB() {
        // TODO Auto-generated method stub

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data  = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "PackageName"
                        + "//databases//" + "DatabaseName";
                String backupDBPath  = "/BackupFolder/DatabaseName";
                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
//                Toast.makeText(getBaseContext(), backupDB.toString(),
//                        Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {

//            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
//                    .show();

        }
    }
    //exporting database
//    private void exportDB() {
//        // TODO Auto-generated method stub
//
//        try {
//            File sd = Environment.getExternalStorageDirectory();
//            File data = Environment.getDataDirectory();
//
//            if (sd.canWrite()) {
//                String  currentDBPath= "//data//" + "PackageName"
//                        + "//databases//" + "DatabaseName";
//                String backupDBPath  = "/BackupFolder/DatabaseName";
//                File currentDB = new File(data, currentDBPath);
//                File backupDB = new File(sd, backupDBPath);
//
//                FileChannel src = new FileInputStream(currentDB).getChannel();
//                FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                dst.transferFrom(src, 0, src.size());
//                src.close();
//                dst.close();
////                Toast.makeText(getBaseContext(), backupDB.toString(),
////                        Toast.LENGTH_LONG).show();
//
//            }
//        } catch (Exception e) {
//
////            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
////                    .show();
//
//        }
//    }


}