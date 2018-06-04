package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final ArrayList<Album> mAlbums= new ArrayList<Album>();

    public AlbumAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mAlbums.add(new Album("Sample0", R.drawable.sample_0));
        mAlbums.add(new Album("Sample1", R.drawable.sample_1));
        mAlbums.add(new Album("Sample2", R.drawable.sample_2));
        mAlbums.add(new Album("Sample3", R.drawable.sample_3));
        mAlbums.add(new Album("Sample4", R.drawable.sample_4));
        mAlbums.add(new Album("Sample5", R.drawable.sample_5));
        mAlbums.add(new Album("Sample6", R.drawable.sample_6));
        mAlbums.add(new Album("Sample7", R.drawable.sample_7));
    }

    public int getCount() {
        return mAlbums.size();
    }

    public Album getItem(int position) {
        return mAlbums.get(position);
    }

    public long getItemId(int position) {
        return mAlbums.get(position).getAlbumCoverId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView imageView;
        TextView textView;

        if (view == null) {
            view = mInflater.inflate(R.layout.list_album, parent,false);
            view.setTag(R.id.image, view.findViewById(R.id.image));
            view.setTag(R.id.album_name, view.findViewById(R.id.album_name));
        }

        imageView = (ImageView) view.getTag(R.id.image);
        textView = (TextView) view.getTag(R.id.album_name);

        Album album = getItem(position);

        imageView.setImageResource(album.getAlbumCoverId());
        textView.setText(album.getAlbumName());
        return view;
    }

}