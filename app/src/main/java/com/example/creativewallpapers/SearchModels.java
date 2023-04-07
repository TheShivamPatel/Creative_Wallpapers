package com.example.creativewallpapers;

import java.util.ArrayList;

public class SearchModels {
    private ArrayList<ImageModels> photos;

    public SearchModels(ArrayList<ImageModels> photos) {
        this.photos = photos;
    }

    public ArrayList<ImageModels> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<ImageModels> photos) {
        this.photos = photos;
    }
}
