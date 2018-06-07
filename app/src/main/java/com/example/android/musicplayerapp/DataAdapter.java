package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class DataAdapter extends BaseAdapter {
    protected final LayoutInflater mInflater;
    protected final static ArrayList<Album> mAlbums = new ArrayList<>();
    protected final static ArrayList<Artist> mArtists = Artist.getArtists();

    DataAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        if(mAlbums.size() > 0) return;
        Album album;
        album = new Album("Abba","Sample0", R.drawable.sample_0);
        album.addTrack(new Track("Track1", R.raw.eine));
        album.addTrack(new Track("Track2", R.raw.piano));
        album.addTrack(new Track("Track3", R.raw.figaro));
        mAlbums.add(album);
        album = new Album("Abba","Sample0", R.drawable.sample_1);
        album.addTrack(new Track("Track4", R.raw.piano));
        album.addTrack(new Track("Track5", R.raw.figaro));
        mAlbums.add(album);
        album = new Album("Belga","Sample2", R.drawable.sample_2);
        album.addTrack(new Track("Track6", R.raw.eine));
        album.addTrack(new Track("Track7", R.raw.piano));
        album.addTrack(new Track("Track8", R.raw.figaro));
        album.addTrack(new Track("Track9", R.raw.eine));
        album.addTrack(new Track("Track10", R.raw.piano));
        mAlbums.add(album);
        album = new Album("Belga","Sample1", R.drawable.sample_3);
        album.addTrack(new Track("Track11", R.raw.figaro));
        album.addTrack(new Track("Track12", R.raw.eine));
        mAlbums.add(album);
        album = new Album("Korn","Sample1", R.drawable.sample_4);
        album.addTrack(new Track("Track13", R.raw.piano));
        album.addTrack(new Track("Track14", R.raw.figaro));
        mAlbums.add(album);
    }
}