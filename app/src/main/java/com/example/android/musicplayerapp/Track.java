package com.example.android.musicplayerapp;

public class Track {

    private String mArtist;
    private String mSongName;
    private int mAudioResourceId;

    public Track(String artist, String songName, int audioResourceId){
        mArtist = artist;
        mSongName = songName;
        mAudioResourceId = audioResourceId;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getSongName() {
        return mSongName;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
