package com.connorbowman.uscan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash screen
        final Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
        finish();
    }
}
