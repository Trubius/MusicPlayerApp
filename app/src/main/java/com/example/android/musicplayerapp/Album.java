package com.example.android.musicplayerapp;

import java.util.ArrayList;

public class Album extends ArrayList<Track> {

    private String mAlbumName;
    private int mAlbumCoverId;

    public Album(String name, int coverId){
        mAlbumName = name;
        mAlbumCoverId = coverId;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public int getAlbumCoverId() {
        return mAlbumCoverId;
    }
}
