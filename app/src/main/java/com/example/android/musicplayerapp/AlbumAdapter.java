package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends DataAdapter {

    public AlbumAdapter(Context context) {
        super(context);
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
        ImageView albumImageView;
        TextView albumTextView;
        TextView artistTextView;
        Album album = getItem(position);

        if (view == null) {
            view = mInflater.inflate(R.layout.album_item, parent,false);
            view.setTag(R.id.image, view.findViewById(R.id.image));
            view.setTag(R.id.album_name, view.findViewById(R.id.album_name));
            view.setTag(R.id.artist_name, view.findViewById(R.id.artist_name));
        }

        albumImageView = (ImageView) view.getTag(R.id.image);
        albumTextView = (TextView) view.getTag(R.id.album_name);
        artistTextView = (TextView) view.getTag(R.id.artist_name);

        albumImageView.setImageResource(album.getAlbumCoverId());
        albumTextView.setText(album.getAlbumName());
        artistTextView.setText(album.getArtistName());
        return view;
    }
}