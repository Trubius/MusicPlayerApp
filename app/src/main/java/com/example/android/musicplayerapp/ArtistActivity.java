package com.example.android.musicplayerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ArtistActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        final String artistName = getIntent().getStringExtra("ArtistName");
        Artist mArtist = Artist.getArtistByName(artistName);
        final TracksAdapter adapter = new TracksAdapter(this, mArtist);
        ListView listView = (ListView) findViewById(R.id.current_artist_list);
        listView.setAdapter(adapter);
        TextView textView = (TextView) findViewById(R.id.current_artist_name);
        textView.setText(mArtist.getArtistName());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                playTrack(adapter.getItem(position), adapter);
            }
        });
    }
}
