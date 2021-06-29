package com.example.hcp.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.R;
import com.example.hcp.models.AppSettings.AppSettingsResponse;

import com.example.hcp.models.Locations.DistrictsResponse;
import com.example.hcp.models.Locations.TehsilResponse;
import com.example.hcp.models.Locations.UCResponse;
import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.example.hcp.utils.UtilsLocal;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.activeandroid.Cache.getContext;

public class SplashActivity extends AppCompatActivity {

    private ProgressDialog dialog;



    List<DistrictsResponse.Datum> districtslocal = new ArrayList<>();
    List<TehsilResponse.TehsilDatum> tehsilslocal = new ArrayList<>();
    List<UCResponse.UCDatum> ucslocal = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActiveAndroid.initialize(this);

//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 3300);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        setContentView(R.layout.activity_splash);

//        GetRelationShipTypes();

        GetPermissions();
//        GetEducation();
//        GetSchoolType();
//        GetProfession();
//        GetBirthLocation();
//        GetLanguage();


    }

    public void GetAppSettings() {

        dialog = new ProgressDialog(SplashActivity.this);
        dialog.setMessage("برائے مہربانی انتظار کریں !");
        dialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
        Call<AppSettingsResponse> call = service.getAppSettings();
        call.enqueue(new Callback<AppSettingsResponse>() {
            @Override
            public void onResponse(Call<AppSettingsResponse> call, Response<AppSettingsResponse> response) {

                dialog.dismiss();
                if (response.body() != null && response.body().getStatus()) {
                    if (new SharedPref(SplashActivity.this).CheckLoggedIn() && Prefs.getBoolean(Constants.isDataLoaded,false)) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        LoadLogin();
                    }
                } else {
                    Toast.makeText(SplashActivity.this, Constants.ServerError, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AppSettingsResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SplashActivity.this, Constants.GeneralError + " " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    void LoadLogin() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 100);

    }

    void GetPermissions() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA};
        String rationale = Constants.GetPermission;
        Permissions.Options options = new Permissions.Options().setRationaleDialogTitle("Info").setSettingsDialogTitle("Warning");
        Permissions.check(SplashActivity.this, permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
                if(UtilsLocal.isNetworkAvailable(getContext())){
                    if (new SharedPref(SplashActivity.this).CheckLoggedIn() && Prefs.getBoolean(Constants.isDataLoaded,false)) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        LoadLogin();
                    }
                }else {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (new SharedPref(SplashActivity.this).CheckLoggedIn() && Prefs.getBoolean(Constants.isDataLoaded,false)) {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                LoadLogin();
                            }
                        }
                    }, 2000);


                }

            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("Required!")
                        .setCancelable(false)
                        .setMessage("App require read and write permission to run normally!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                GetPermissions();
                            }
                        }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                    }
                }).show();
            }
        });
    }

}