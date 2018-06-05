package com.example.android.musicplayerapp;

public class Track {

    private String mSongName;
    private int mAudioResourceId;

    public Track(String songName){
        mSongName = songName;
    }

    public Track (String songName, int audioResourceId){
        mSongName = songName;
        mAudioResourceId = audioResourceId;
    }

    public String getSongName() {
        return mSongName;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
