package com.example.android.musicplayerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        final String artistName = getIntent().getStringExtra("ArtistName");
        Artist mArtist = Artist.getArtistByName(artistName);
        TracksAdapter adapter = new TracksAdapter(this, mArtist);
        ListView listView = (ListView) findViewById(R.id.current_artist_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
