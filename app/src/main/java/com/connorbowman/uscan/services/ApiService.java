package com.connorbowman.uscan.services;

import android.content.Context;
import android.util.Log;

import com.connorbowman.uscan.BuildConfig;
import com.connorbowman.uscan.models.Price;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiService {

    public static final double VERSION = 1.0;
    public static final String URL = "http://connorbowman.com/uscan/";
    //public static final String URL_BASE = URL + VERSION + "/";
    public static final String URL_BASE = URL;

    private static Api sApi;

    public ApiService(final Context context) {

        // Create new HTTP Client builder
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();

        // Intercept all requests
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
                Response response = chain.proceed(requestBuilder.build());

                // Add 5 second delay in all network calls for debugging
                if (BuildConfig.DEBUG) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {}
                }

                // Print raw json to console for debugging or just return response
                if (BuildConfig.DEBUG) {
                    String rawJson = response.body().string();
                    Log.d(BuildConfig.APPLICATION_ID, String.format("Network response: %s", rawJson));
                    return response.newBuilder().body(ResponseBody.create(response.body().contentType(), rawJson)).build();
                } else {
                    return response;
                }
            }
        });

        // Create API service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        sApi = retrofit.create(Api.class);
    }

    public static Api getInstance() {
        return sApi;
    }

    public interface Api {

        @GET("prices/{barcode}")
        Call<List<Price>> getPrices(@Path("barcode") String barcode);
    }
}
