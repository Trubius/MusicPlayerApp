package com.example.android.musicplayerapp;

import java.util.ArrayList;

public class Artist {

    private static ArrayList<Artist> mArtists = new ArrayList<>();
    private String mArtistName;
    private ArrayList<Album> mAlbums = new ArrayList<>();

    public static Artist getOrCreateArtist(String artistName) {
        if (hasArtist(artistName)) {
            return getArtistByName(artistName);
        } else {
            return new Artist(artistName);
        }
        //return hasArtist(artistName)?getArtistByName(artistName):new Artist(artistName);
    }

    private Artist(String artistName) {
        mArtistName = artistName;
        mArtists.add(this);
    }


    public static Artist getArtistByName(String artistName) {
        for (int i = 0; i < mArtists.size(); i++) {
            if (mArtists.get(i).getArtistName().equals(artistName)) {
                return mArtists.get(i);
            }
        }
        return null;
    }

    private static boolean hasArtist(String artistName) {
        for (int i = 0; i < mArtists.size(); i++) {
            if (mArtists.get(i).getArtistName().equals(artistName)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Artist> getArtists() {
        return mArtists;
    }

    public void addAlbum(Album album){
        mAlbums.add(album);
    }

    public String getArtistName() {
        return mArtistName;
    }

    public int getAlbumCount(){
        return mAlbums.size();
    }

    public int getTrackCount(){
        int totalTrackCount = 0;
        for (int i = 0; i < mAlbums.size(); i++){
            totalTrackCount = totalTrackCount + mAlbums.get(i).getTrackCount();
        }
        return totalTrackCount;
    }

    public ArrayList<Track> getTracks(){
        ArrayList<Track> tracks = new ArrayList<>();
        for (int i = 0; i < mAlbums.size(); i++) {
            tracks.addAll(mAlbums.get(i));
        }
        return tracks;
    }
}
