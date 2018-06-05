package com.example.android.musicplayerapp;

import java.util.ArrayList;

public class Album extends ArrayList<Track> {

    private Artist mArtist;
    private String mAlbumName;
    private int mAlbumCoverId;

    public Album(String artistName, String albumName, int coverId){
        mArtist = Artist.getOrCreateArtist(artistName);
        mArtist.addAlbum(this);
        mAlbumName = albumName;
        mAlbumCoverId = coverId;
    }

    public String getArtistName() {
        return mArtist.getArtistName();
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public int getAlbumCoverId() {
        return mAlbumCoverId;
    }

    public int getTrackCount(){
        return size();
    }

    public void addTrack(Track track){
        add(track);
        track.setAlbum(this);
    }
}
