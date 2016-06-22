package com.connorbowman.uscan.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.connorbowman.uscan.BuildConfig;
import com.connorbowman.uscan.R;
import com.connorbowman.uscan.utils.TagUtil;

public class ManualEntryFragment extends BaseFragment {

    public static final String TAG = TagUtil.make(ManualEntryFragment.class);
    public static final int TITLE = R.string.enter_manually;

    private TextInputLayout mInputBarcodeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual_entry, container, false);

        mInputBarcodeLayout = (TextInputLayout) view.findViewById(R.id.input_barcode_layout);
        final TextInputEditText inputBarcode = (TextInputEditText) view.findViewById(R.id.input_barcode);
        Button buttonSubmit = (Button) view.findViewById(R.id.button_submit);

        if (BuildConfig.DEBUG) {
            inputBarcode.setText("889842070019");
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBarcode(inputBarcode.getText().toString());
            }
        });

        return view;
    }

    private void submitBarcode(String barcode) {
        // Reset errors
        View focusView = null;
        mInputBarcodeLayout.setError(null);

        // Validate
        if (barcode.isEmpty()) {
            mInputBarcodeLayout.setError(getString(R.string.barcode_is_required));
            focusView = mInputBarcodeLayout;
        } else if (barcode.length() < 7) {
            mInputBarcodeLayout.setError(getString(R.string.barcode_is_invalid));
            focusView = mInputBarcodeLayout;
        }

        // Request focus if has errors or submit
        if (focusView != null) {
            focusView.requestFocus();
        } else {
            Fragment fragment = new ResultsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ScanFragment.BarcodeObject, barcode);
            fragment.setArguments(bundle);
            getCallback().pushFragment(fragment, ResultsFragment.TAG, ResultsFragment.TITLE, true);
        }
    }
}
