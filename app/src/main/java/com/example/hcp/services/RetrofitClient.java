package com.example.hcp.services;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static final String AUTH = "Basic " + Base64.encodeToString(("admin:admin123").getBytes(), Base64.NO_WRAP);

    private static final String BASE_URL = "https://phcp.pshealthpunjab.gov.pk/"; //Live server with live database
//    private static final String BASE_URL = "http://172.16.25.194:8080/phcp-emr/"; // Local server with live database
//    private static final String BASE_URL = "http://172.16.25.194:8080/testemr/";  //Local server with test database
//    private static final String BASE_URL = "http://125.209.111.69:5544/testemr/";  //Live test server with test database
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.i("OKhttp Response", message));
        logging.level(HttpLoggingInterceptor.Level.BODY);
        logging.redactHeader("Authorization");
        logging.redactHeader("Cookie");



        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }

                )
                .addInterceptor(logging)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public GetDataService getApi() {
        return retrofit.create(GetDataService.class);
    }
}
