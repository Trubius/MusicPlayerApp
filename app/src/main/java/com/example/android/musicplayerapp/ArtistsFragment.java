package com.example.android.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ArtistsFragment extends Fragment {

    public ArtistsFragment(){
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.artist_list, container, false);

        final ArtistsAdapter adapter = new ArtistsAdapter(getActivity());
        ListView listView = (ListView) rootView.findViewById(R.id.artist_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), ArtistActivity.class);
                intent.putExtra("ArtistName", adapter.getItem(position).getArtistName());
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }
}