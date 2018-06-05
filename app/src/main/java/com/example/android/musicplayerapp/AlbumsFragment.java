package com.example.android.musicplayerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class AlbumsFragment extends Fragment {

    public AlbumsFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.album_grid, container, false);

        final AlbumAdapter adapter = new AlbumAdapter(getActivity());
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                intent.putExtra("AlbumKey", adapter.getItem(position).getKey());
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }
}