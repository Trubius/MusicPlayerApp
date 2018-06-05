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
        album.add(new Track("Mammamia"));
        album.add(new Track("Gizi"));
        album.add(new Track("Mammamia"));
        mAlbums.add(album);
        album = new Album("Abba","Sample0", R.drawable.sample_1);
        album.add(new Track("VASDDSA"));
        album.add(new Track("rstghrtgr"));
        mAlbums.add(album);
        album = new Album("Belga","Sample2", R.drawable.sample_2);
        album.add(new Track("VASDDSA"));
        album.add(new Track("rstghrtgr"));
        album.add(new Track("rstghrtgr"));
        album.add(new Track("rstghrtgr"));
        album.add(new Track("rstghrtgr"));
        mAlbums.add(album);
        album = new Album("Belga","Sample1", R.drawable.sample_3);
        album.add(new Track("VASDDSA"));
        album.add(new Track("rstghrtgr"));
        mAlbums.add(album);
        album = new Album("Korn","Sample1", R.drawable.sample_4);
        album.add(new Track("VASDDSA"));
        album.add(new Track("rstghrtgr"));
        mAlbums.add(album);
    }
}