package com.anksmonani.slicepay.Networking;

import com.anksmonani.slicepay.Bean.GetImages;
import com.anksmonani.slicepay.Bean.ImageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface APIInterface {


    @GET("/services/rest/?method=flickr.interestingness.getList")
    Call<GetImages> getImageDetails(@Query("api_key")String api_key, @Query("page") int page
    , @Query("format")String format, @Query("nojsoncallback")String nojsoncallback, @Query("per_page")String per_page);



}
