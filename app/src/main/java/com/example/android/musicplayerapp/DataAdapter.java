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
        album = new Album(context.getString(R.string.ashamaluevmusic),context.getString(R.string.cinematic_inspiration), R.drawable.cinematic);
        album.addTrack(new Track(context,context.getString(R.string.cinematic_piano_ambient), R.raw.cinematic_piano_ambient));
        album.addTrack(new Track(context,context.getString(R.string.inspired), R.raw.inspired));
        album.addTrack(new Track(context,context.getString(R.string.beautiful), R.raw.beautiful));
        album.addTrack(new Track(context,context.getString(R.string.cinematic_inspiration), R.raw.cinematic_inspiration));
        album.addTrack(new Track(context,context.getString(R.string.cinematic_trailer), R.raw.cinematic_trailer));
        album.addTrack(new Track(context,context.getString(R.string.emotional), R.raw.emotional));
        album.addTrack(new Track(context,context.getString(R.string.inspirational_acoustic), R.raw.inspirational_acoustic));
        album.addTrack(new Track(context,context.getString(R.string.inspirational_day), R.raw.inspirational_day));
        album.addTrack(new Track(context,context.getString(R.string.inspire), R.raw.inspire));
        mAlbums.add(album);
        album = new Album(context.getString(R.string.ashamaluevmusic),context.getString(R.string.motivation), R.drawable.motivation);
        album.addTrack(new Track(context,context.getString(R.string.epic_motivational), R.raw.epic_motivational));
        album.addTrack(new Track(context,context.getString(R.string.motivational), R.raw.motivational));
        album.addTrack(new Track(context,context.getString(R.string.motivational_upbeat), R.raw.motivational_upbeat));
        album.addTrack(new Track(context,context.getString(R.string.my_motivation), R.raw.my_motivation));
        album.addTrack(new Track(context,context.getString(R.string.triumphal), R.raw.triumphal));
        mAlbums.add(album);
        album = new Album(context.getString(R.string.ashamaluevmusic),context.getString(R.string.positive), R.drawable.positive);
        album.addTrack(new Track(context,context.getString(R.string.positive), R.raw.positive));
        album.addTrack(new Track(context,context.getString(R.string.energetic_upbeat), R.raw.energetic_upbeat));
        album.addTrack(new Track(context,context.getString(R.string.uplifting_rock), R.raw.uplifting_rock));
        album.addTrack(new Track(context,context.getString(R.string.happy_upbeat), R.raw.happy_upbeat));
        album.addTrack(new Track(context,context.getString(R.string.positive_pop_rock), R.raw.positive_pop_rock));
        album.addTrack(new Track(context,context.getString(R.string.inspirational_pop_rock), R.raw.inspirational_pop_rock));
        album.addTrack(new Track(context,context.getString(R.string.upbeat_rock), R.raw.upbeat_rock));
        album.addTrack(new Track(context,context.getString(R.string.motivational_pop_rock), R.raw.motivational_pop_rock));
        album.addTrack(new Track(context,context.getString(R.string.happy_pop_rock), R.raw.happy_pop_rock));
        album.addTrack(new Track(context,context.getString(R.string.energetic_rock), R.raw.energetic_rock));
        album.addTrack(new Track(context,context.getString(R.string.motivational_rock), R.raw.motivational_rock));
        album.addTrack(new Track(context,context.getString(R.string.happy_ukulele), R.raw.happy_ukulele));
        album.addTrack(new Track(context,context.getString(R.string.uplifting_pop_rock), R.raw.uplifting_pop_rock));
        album.addTrack(new Track(context,context.getString(R.string.be_happy), R.raw.be_happy));
        mAlbums.add(album);
    }
}