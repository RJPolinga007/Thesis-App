package com.bisu.herbalplantidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class scanlottie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanlottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(scanlottie.this, DetectorActivity.class);
                scanlottie.this.startActivity(mainIntent);
                scanlottie.this.finish();
            }
        }, 2000);
    }
}