package com.example.android.musicplayerapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Album extends ArrayList<Track> {

    private String mArtistName;
    private String mAlbumName;
    private int mAlbumCoverId;
    private static HashMap<String, Integer> mAlbumCounts = new HashMap<String, Integer>();

    public Album(String artistName, String albumName, int coverId){
        mArtistName = artistName;
        mAlbumName = albumName;
        mAlbumCoverId = coverId;
        if (mAlbumCounts.containsKey(artistName)) {
            mAlbumCounts.put(artistName, mAlbumCounts.get(artistName) + 1);
        }else{
            mAlbumCounts.put(artistName, 1);
        }
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public int getAlbumCoverId() {
        return mAlbumCoverId;
    }
}
