package io.androidblog.simpletodo.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.androidblog.simpletodo.R;


public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        setCursiveFont();
        startMainActivity();
    }

    private void setCursiveFont() {
        String fontPath = "fonts/Allura-Regular.ttf";
        TextView txtTitle = (TextView) findViewById(R.id.fullscreen_content);

        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtTitle.setTypeface(tf);
    }

    private void startMainActivity() {

        //Initiates the MainActivity after 1000L
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }

        }, 1600L);

    }
}
