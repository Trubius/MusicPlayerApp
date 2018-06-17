package com.example.android.musicplayerapp;

import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {

    private static TracksAdapter tracksAdapter;
    private static Track currentTrack;
    private ImageView playButton;
    private TextView currentTrackText;
    private TextView durationText;
    private TextView currentTimeText;
    private SeekBar seekBar;
    private static Handler mHandler = new Handler();
    private Runnable polling;

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

        polling = new Runnable() {

            @Override
            public void run() {
                updateDurationUI();
                mHandler.postDelayed(this, 1000);
            }
        };
    }

    private void updateDurationUI() {
        if (mMediaPlayer != null) {
            int mCurrentPosition = mMediaPlayer.getCurrentPosition();
            seekBar.setProgress(mCurrentPosition);
            currentTimeText.setText(formatDuration(mCurrentPosition));
            durationText.setText(formatDuration(mMediaPlayer.getDuration()));
        } else {
            currentTimeText.setText(formatDuration(0));
            durationText.setText(formatDuration(0));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBottomNav();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initBottomNav();
    }

    public void initBottomNav(){
        ImageView nextButton = (ImageView) findViewById(R.id.skip_next);
        ImageView prevButton = (ImageView) findViewById(R.id.skip_previous);
        playButton = (ImageView) findViewById(R.id.play);
        currentTrackText = (TextView) findViewById(R.id.current_playing);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        durationText = (TextView) findViewById(R.id.track_duration);
        currentTimeText = (TextView) findViewById(R.id.current_time);

        setPlayPauseImageResource();
        initSeekBar();
        runOnUiThread(polling);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) nextTrack();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    if (mMediaPlayer.getCurrentPosition() >= 3000){
                        mMediaPlayer.seekTo(0);
                    } else {
                        prevTrack();
                    }
                }
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
            // with the current track

            mMediaPlayer = MediaPlayer.create(getApplicationContext(), track.getAudioResourceId());

            // Start the audio file
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            tracksAdapter = adapter;
            currentTrack = track;
            setPlayPauseImageResource();
            initSeekBar();
        }
    }

    private static String formatDuration(int milliseconds){
        int seconds = milliseconds / 1000;
        return String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60);
    }

    private void initSeekBar() {
        if (currentTrack != null) {
            currentTrackText.setTypeface(null, Typeface.BOLD);
            currentTrackText.setText(currentTrack.toString());
            seekBar.setMax(mMediaPlayer.getDuration());
            seekBar.setProgress(mMediaPlayer.getCurrentPosition());
            seekBar.setEnabled(true);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        mMediaPlayer.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } else {
            seekBar.setEnabled(false);
        }
    }

    private static void pauseTrack() {
        mMediaPlayer.pause();
    }

    private static void resumeTrack() {
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            mMediaPlayer.start();
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
