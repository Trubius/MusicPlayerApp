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
}
