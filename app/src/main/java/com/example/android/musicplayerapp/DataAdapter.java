package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class DataAdapter extends BaseAdapter {
    protected final LayoutInflater mInflater;
    protected final ArrayList<Album> mAlbums = new ArrayList<Album>();

    DataAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mAlbums.add(new Album("Abba","Sample0", R.drawable.sample_0));
        /*album.add(new Track("Mammamia"));
        mAlbums.add(album);*/
        mAlbums.add(new Album("Abba","Sample0", R.drawable.sample_1));
        mAlbums.add(new Album("Belga","Sample2", R.drawable.sample_2));
        mAlbums.add(new Album("Belga","Sample1", R.drawable.sample_3));
        mAlbums.add(new Album("Korn","Sample1", R.drawable.sample_4));
        mAlbums.add(new Album("Korn","Sample2", R.drawable.sample_5));
        mAlbums.add(new Album("Belga","Sample1", R.drawable.sample_6));
        mAlbums.add(new Album("Abba","Sample0", R.drawable.sample_7));

    }
}
