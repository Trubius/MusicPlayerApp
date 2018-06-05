package com.example.android.musicplayerapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Album extends ArrayList<Track> {

    private Artist mArtist;
    private String mAlbumName;
    private int mAlbumCoverId;
    private String mKey = UUID.randomUUID().toString();
    private static HashMap<String, Album> mAlbumsByKey = new HashMap<>();

    public Album(String artistName, String albumName, int coverId){
        mArtist = Artist.getOrCreateArtist(artistName);
        mArtist.addAlbum(this);
        mAlbumName = albumName;
        mAlbumCoverId = coverId;
        mAlbumsByKey.put(mKey, this);
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
    public String getKey(){
        return mKey;
    }

    public static Album getAlbumByKey(String key){
        return mAlbumsByKey.get(key);
    }
}
