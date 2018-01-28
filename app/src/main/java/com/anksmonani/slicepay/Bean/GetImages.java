package com.anksmonani.slicepay.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ankit on 1/28/2018.
 */

public class GetImages {
    @Expose
    @SerializedName("stat")
    private String stat;
    @Expose
    @SerializedName("photos")
    private Photos photos;

    public String getStat() {
        return stat;
    }

    public Photos getPhotos() {
        return photos;
    }
}
