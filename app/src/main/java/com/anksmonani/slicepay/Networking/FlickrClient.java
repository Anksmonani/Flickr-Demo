package com.anksmonani.slicepay.Networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrClient  {

    private static final String SERVER_URL = "https://api.flickr.com";

    public static Retrofit getClient() {

     OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
