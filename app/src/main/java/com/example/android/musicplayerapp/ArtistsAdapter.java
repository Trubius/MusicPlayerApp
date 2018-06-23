package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArtistsAdapter extends DataAdapter {
    Context mContext;

    public ArtistsAdapter(Context context) {
        super(context);
        mContext = context;
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
        TextView albumCountTextView;
        TextView artistTextView;
        TextView trackCountTextView;
        Artist artist = getItem(position);

        if (view == null) {
            view = mInflater.inflate(R.layout.artist_item, parent,false);
            view.setTag(R.id.artist_item_name, view.findViewById(R.id.artist_item_name));
            view.setTag(R.id.artist_album_count, view.findViewById(R.id.artist_album_count));
            view.setTag(R.id.artist_track_count, view.findViewById(R.id.artist_track_count));
        }

        artistTextView = (TextView) view.getTag(R.id.artist_item_name);
        albumCountTextView = (TextView) view.getTag(R.id.artist_album_count);
        trackCountTextView = (TextView) view.getTag(R.id.artist_track_count);

        artistTextView.setText(artist.getArtistName());
        albumCountTextView.setText(quantityString(R.plurals.numberOfAlbums, artist.getAlbumCount(), artist.getAlbumCount()));
        trackCountTextView.setText(quantityString(R.plurals.numberOfSongs, artist.getTrackCount(), artist.getTrackCount()));
        return view;
    }

    private String quantityString(int id, int quantity, int count){
        return mContext.getResources().getQuantityString(id, quantity, count);
    }

}