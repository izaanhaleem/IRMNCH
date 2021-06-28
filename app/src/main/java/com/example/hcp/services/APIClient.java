package com.example.hcp.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit;

//  private static final String BASE_URL = "http://172.16.5.216:2546";
//  private static final String BASE_URL = "http://172.16.8.90:8982";
//  private static final String BASE_URL = "http://172.16.8.90:33339";
//private static final String BASE_URL = "http://172.16.19.132:8982";
    private static final String BASE_URL = "https://lhw.pshealthpunjab.gov.pk";
//  private static final String BASE_URL = "http://172.16.8.90";

    public static Retrofit getRetrofitInstance() {


//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.level(HttpLoggingInterceptor.Level.BASIC);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.i("OKhttp Response", message));
        logging.level(HttpLoggingInterceptor.Level.BODY);
        logging.redactHeader("Authorization");
        logging.redactHeader("Cookie");


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
