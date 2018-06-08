package com.example.android.musicplayerapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {

    private static TracksAdapter tracksAdapter;
    private static Track currentTrack;
    private ImageView playButton;
    private TextView currentPlay;
    private static int currentAudioFocus;

    /** Handles playback of all the sound files */
    private static MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private static AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create and setup the {@link AudioManager} to request audio focus
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setPlayPauseImageResource();
    }

    public void initBottomNav(){
        ImageView nextButton = (ImageView) findViewById(R.id.skip_next);
        ImageView prevButton = (ImageView) findViewById(R.id.skip_previous);
        playButton = (ImageView) findViewById(R.id.play);

        setPlayPauseImageResource();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) nextTrack();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) prevTrack();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer == null) return;
                if (mMediaPlayer.isPlaying()) {   // Checks music if it's playing
                    pauseTrack();
                } else {
                    resumeTrack();
                }
                setPlayPauseImageResource();
            }
        });
    }

    private void setPlayPauseImageResource() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {   // Checks music if it's playing
            playButton.setImageResource(R.drawable.ic_pause);
        } else {
            playButton.setImageResource(R.drawable.ic_play);
        }
    }

    public void playTrack(Track track, TracksAdapter adapter) {
        // Release the media player if it currently exists because we are about to
        // play a different sound file
        releaseMediaPlayer();

        // Request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Start playback

            // Create and setup the {@link MediaPlayer} for the audio resource associated
            // with the current word

            mMediaPlayer = MediaPlayer.create(getApplicationContext(), track.getAudioResourceId());

            // Start the audio file
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            tracksAdapter = adapter;
            currentTrack = track;
            setPlayPauseImageResource();
        }
    }

    private static void pauseTrack() {
        mMediaPlayer.pause();
    }

    private static void resumeTrack() {
        if (currentAudioFocus == AudioManager.AUDIOFOCUS_GAIN) {
            mMediaPlayer.start();
        } else {
            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mMediaPlayer.start();
            }
        }
    }

    public void nextTrack(){
        Track nextTrack = tracksAdapter.getNextTrack(currentTrack);
        playTrack(nextTrack, tracksAdapter);
    }

    public void prevTrack(){
        Track prevTrack = tracksAdapter.getPrevTrack(currentTrack);
        playTrack(prevTrack, tracksAdapter);
    }
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, play next track.
            nextTrack();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private static AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            currentAudioFocus = focusChange;
            switch (focusChange){
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    pauseTrack();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
            }
        }
    };
    /**
     * Clean up the media player by releasing its resources.
     */
    private static void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    private static void releaseAudioFocus() {
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
