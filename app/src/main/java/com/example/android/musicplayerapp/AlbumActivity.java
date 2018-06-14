package com.example.android.musicplayerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        final String albumKey = getIntent().getStringExtra("AlbumKey");
        Album mAlbum = Album.getAlbumByKey(albumKey);
        final TracksAdapter adapter = new TracksAdapter(this, mAlbum);
        ListView listView = (ListView) findViewById(R.id.current_album_track_list);
        listView.setAdapter(adapter);
        TextView textViewAlbum = (TextView) findViewById(R.id.current_album_name);
        textViewAlbum.setText(mAlbum.getAlbumName());
        TextView textViewArtist = (TextView) findViewById(R.id.current_album_artist_name);
        textViewArtist.setText(mAlbum.getArtistName());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                playTrack(adapter.getItem(position), adapter);
            }
        });
    }
}
