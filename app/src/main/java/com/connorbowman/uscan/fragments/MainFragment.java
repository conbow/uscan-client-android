package com.connorbowman.uscan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.utils.TagUtil;

public class MainFragment extends BaseFragment {

    public static final String TAG = TagUtil.make(MainFragment.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonScan = (Button) view.findViewById(R.id.button_scan);
        Button buttonManualEntry = (Button) view.findViewById(R.id.button_manual_entry);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallback().pushFragment(new ScanFragment(), ScanFragment.TAG,
                        ScanFragment.TITLE, true);
            }
        });

        buttonManualEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallback().pushFragment(new ManualEntryFragment(), ManualEntryFragment.TAG,
                        ManualEntryFragment.TITLE, true);
            }
        });

        return view;
    }
}
