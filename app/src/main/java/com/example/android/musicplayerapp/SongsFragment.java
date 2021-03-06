package com.example.android.musicplayerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class SongsFragment extends Fragment {

    public SongsFragment(){
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.track_list, container, false);

        final TracksAdapter adapter = new TracksAdapter(getActivity());
        ListView listView = (ListView) rootView.findViewById(R.id.track_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                BaseActivity activity = (BaseActivity) getActivity();
                activity.playTrack(adapter.getItem(position), adapter);
            }
        });

        return rootView;
    }
}