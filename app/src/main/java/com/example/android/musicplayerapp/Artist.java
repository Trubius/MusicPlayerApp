package com.example.android.musicplayerapp;

import java.util.ArrayList;

public class Artist {

    private static ArrayList<Artist> mArtists = new ArrayList<>();
    private String mArtistName;

    public static Artist getOrCreateArtist(String artistName) {
        if (hasArtist(artistName)) {
            return getArtistByName(artistName);
        } else {
            return new Artist(artistName);
        }
    }

    private Artist(String artistName) {
        mArtistName = artistName;
        mArtists.add(this);
    }


    public static Artist getArtistByName(String artistName) {
        for (int i = 0; i < mArtists.size(); i++) {
            if (mArtists.get(i).getArtistName() == artistName) {
                return mArtists.get(i);
            }
        }
        return null;
    }

    public static boolean hasArtist(String artistName) {
        for (int i = 0; i < mArtists.size(); i++) {
            if (mArtists.get(i).getArtistName() == artistName) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Artist> getArtists() {
        return mArtists;
    }

    public String getArtistName() {
        return mArtistName;
    }
}
