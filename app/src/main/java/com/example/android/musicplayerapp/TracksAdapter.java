package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TracksAdapter extends DataAdapter {
    protected final ArrayList<Track> mTracks = new ArrayList<>();

    public TracksAdapter(Context context) {
        super(context);
        for (int i = 0; i < mAlbums.size(); i++) {
            mTracks.addAll(mAlbums.get(i));
        }
    }

    public int getCount() {
        return mTracks.size();
    }

    public Track getItem(int position) {
        return mTracks.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView albumCover;
        TextView trackNameTextView;
        TextView artistNameTextView;
        TextView trackLengthTextView;
        Track track = getItem(position);

        if (view == null) {
            view = mInflater.inflate(R.layout.track_item, parent,false);
            view.setTag(R.id.track_image, view.findViewById(R.id.track_image));
            view.setTag(R.id.track_item_name, view.findViewById(R.id.track_item_name));
            view.setTag(R.id.track_artist_name, view.findViewById(R.id.track_artist_name));
            view.setTag(R.id.track_length, view.findViewById(R.id.track_length));
        }

        albumCover = (ImageView) view.getTag(R.id.track_image);
        trackNameTextView = (TextView) view.getTag(R.id.track_item_name);
        artistNameTextView = (TextView) view.getTag(R.id.track_artist_name);
        trackLengthTextView = (TextView) view.getTag(R.id.track_length);

        albumCover.setImageResource(track.getImageResourceId());
        trackNameTextView.setText(track.getSongName());
        artistNameTextView.setText(track.getArtistName());
        trackLengthTextView.setText("Track length");
        return view;
    }
}