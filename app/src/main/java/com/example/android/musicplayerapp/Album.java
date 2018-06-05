package com.example.android.musicplayerapp;

import java.util.ArrayList;

public class Album extends ArrayList<Track> {

    private String mArtistName;
    private String mAlbumName;
    private int mAlbumCoverId;


    public Album(String artistName, String albumName, int coverId){
        Artist artist = Artist.getOrCreateArtist(artistName);
        mArtistName = artist.getArtistName();
        mAlbumName = albumName;
        mAlbumCoverId = coverId;
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
