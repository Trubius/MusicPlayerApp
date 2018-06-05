package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtistsAdapter extends DataAdapter {

    public ArtistsAdapter(Context context) {
        super(context);
    }

    public int getCount() {
        return mArtists.size();
    }

    public Artist getItem(int position) {
        return mArtists.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView albumImageView;
        TextView albumTextView;
        TextView artistTextView;
        Artist artist = getItem(position);

        if (view == null) {
            view = mInflater.inflate(R.layout.album_item, parent,false);
            view.setTag(R.id.image, view.findViewById(R.id.image));
            view.setTag(R.id.album_name, view.findViewById(R.id.album_name));
            view.setTag(R.id.artist_name, view.findViewById(R.id.artist_name));
        }

        albumImageView = (ImageView) view.getTag(R.id.image);
        albumTextView = (TextView) view.getTag(R.id.album_name);
        artistTextView = (TextView) view.getTag(R.id.artist_name);

        //albumImageView.setImageResource(artist.getAlbumCoverId());
        //albumTextView.setText(artist.getAlbumName());
        artistTextView.setText(artist.getArtistName());
        return view;
    }
}