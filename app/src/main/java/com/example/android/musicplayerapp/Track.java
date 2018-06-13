package com.example.android.musicplayerapp;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;

public class Track {

    private String mSongName;
    private Album mAlbum;
    private int mAudioResourceId;
    private String mDuration;

    public Track (Context context, String songName, int audioResourceId){
        mSongName = songName;
        mAudioResourceId = audioResourceId;
        mDuration = getFileDuration(context.getResources().openRawResourceFd(mAudioResourceId));
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

    private static String getFileDuration(AssetFileDescriptor assetFileDescriptor){
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        mmr.release();
        return duration;
    }

    public String getDuration(){
        return mDuration;
    }

    @Override
    public String toString() {
        return getSongName() + "\n" + getArtistName();
    }
}
