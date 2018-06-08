package com.example.android.musicplayerapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

public class BottomNav extends ConstraintLayout {

    public BottomNav(Context context){
        super(context);
        init(context);
    }

    public BottomNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_nav, this, true);
    }
}
