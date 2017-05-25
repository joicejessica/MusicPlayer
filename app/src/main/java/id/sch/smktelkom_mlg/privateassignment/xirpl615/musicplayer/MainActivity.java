package id.sch.smktelkom_mlg.privateassignment.xirpl615.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener {
    private ImageButton play, pause, stop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageButton) findViewById(R.id.imageButton2);
        pause = (ImageButton) findViewById(R.id.imageButton3);
        stop = (ImageButton) findViewById(R.id.imageButton4);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
        setup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stop.isEnabled()) {
            stop();
        }
    }

    public void onCompletion(MediaPlayer mp) {
        stop();
    }

    private void play() {
        mp.start();
        play.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    private void stop() {
        mp.stop();
        pause.setEnabled(false);
        stop.setEnabled(false);

        try {
            mp.prepare();
            mp.seekTo(0);
            play.setEnabled(true);
        } catch (Throwable t) {
            goBlooey(t);
        }
    }

    private void pause() {
        mp.pause();

        play.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(true);
    }

    private void loadClip() {
        try {
            mp = MediaPlayer.create(this, R.raw.fallforyou);
            mp.setOnCompletionListener(this);
        } catch (Throwable t) {
            goBlooey(t);
        }
    }

    private void goBlooey(Throwable t) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setTitle("Peringatan")
                .setMessage(t.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    private void setup() {
        loadClip();
        play.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
    }
}
