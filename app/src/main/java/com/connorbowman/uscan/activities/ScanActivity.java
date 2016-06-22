package com.connorbowman.uscan.activities;

import android.os.Bundle;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.fragments.MainFragment;
import com.connorbowman.uscan.fragments.ScanFragment;

public class ScanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initializeToolbar();

        if (savedInstanceState == null) {
            pushFragment(new ScanFragment(), ScanFragment.TAG, null, false);
        }
    }
}
