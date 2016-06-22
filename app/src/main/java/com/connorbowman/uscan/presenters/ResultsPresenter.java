package com.connorbowman.uscan.presenters;

import android.util.Log;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.models.Price;
import com.connorbowman.uscan.utils.TagUtil;
import com.connorbowman.uscan.views.ResultsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsPresenter extends TaskPresenter<ResultsView> {

    public static final String TAG = TagUtil.make(ResultsPresenter.class);

    private Call<List<Price>> mCall;

    private List<Price> mPrices = null;

    @Override
    public void onStart() {
        if (hasView()) {
            getView().onFinished(mPrices);
        }
        super.onStart();
    }

    public void loadPrices(String barcode, boolean forceReload) {

        if (mPrices != null && !forceReload) {
            endTask(true);

        } else {
            startTask();

            mCall = getApi().getPrices(barcode);
            mCall.enqueue(new Callback<List<Price>>() {
                @Override
                public void onResponse(Call<List<Price>> call, Response<List<Price>> response) {
                    if (!response.isSuccessful()) {
                        setFeedback(R.string.an_unknown_error_occurred, true);
                    } else {
                        mPrices = response.body();
                    }
                    endTask(true);
                }

                @Override
                public void onFailure(Call<List<Price>> call, Throwable t) {
                    setFeedback(R.string.network_error);
                    endTask(true);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCall != null && !mCall.isCanceled()) {
            mCall.cancel();
        }
    }
}
