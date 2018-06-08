package com.example.android.musicplayerapp;

public class Track {

    private String mSongName;
    private Album mAlbum;
    private int mAudioResourceId;

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

    public void setAlbum(Album album){
        mAlbum = album;
    }

    public int getImageResourceId(){
        return mAlbum.getAlbumCoverId();
    }

    public String getArtistName(){
        return mAlbum.getArtistName();
    }

    @Override
    public String toString() {
        return getSongName() + "\n" + getArtistName();
    }
}
