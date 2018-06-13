package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TracksAdapter extends DataAdapter {
    protected ArrayList<Track> mTracks;

    public TracksAdapter(Context context) {
        super(context);
        mTracks = new ArrayList<>();
        for (int i = 0; i < mAlbums.size(); i++) {
            mTracks.addAll(mAlbums.get(i));
        }
    }

    public TracksAdapter(Context context, Album album){
        super(context);
        mTracks = album;
    }

    public TracksAdapter(Context context, Artist artist){
        super(context);
        mTracks = artist.getTracks();
    }

    public int getCount() {
        return mTracks.size();
    }

    public Track getItem(int position) {
        return mTracks.get(position);
    }

    public Track getNextTrack(Track currentTrack) {
        int nextIndex = mTracks.indexOf(currentTrack) + 1;
        return mTracks.get(nextIndex % mTracks.size());
    }

    public Track getPrevTrack(Track currentTrack) {
        int nextIndex = mTracks.size() + mTracks.indexOf(currentTrack) - 1;
        return mTracks.get(nextIndex % mTracks.size());
    }

    public long getItemId(int position) {
        return position;
        //return mTracks.get(position).getAudioResourceId();
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
        trackLengthTextView.setText(formatDuration(Integer.parseInt(track.getDuration())));
        return view;
    }

    private static String formatDuration(int milliseconds){
        int seconds = milliseconds / 1000;
        return String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60);
    }
}