package com.anksmonani.slicepay.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @Expose
    @SerializedName("photo")
    private List<Photo> photo;
    @Expose
    @SerializedName("total")
    private int total;
    @Expose
    @SerializedName("perpage")
    private int perpage;
    @Expose
    @SerializedName("pages")
    private int pages;
    @Expose
    @SerializedName("page")
    private int page;

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public int getTotal() {
        return total;
    }

    public int getPerpage() {
        return perpage;
    }

    public int getPages() {
        return pages;
    }

    public int getPage() {
        return page;
    }
}
