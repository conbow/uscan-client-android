package com.connorbowman.uscan.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.activities.ActivityCallback;
import com.connorbowman.uscan.presenters.Presenter;
import com.connorbowman.uscan.presenters.PresenterManager;
import com.connorbowman.uscan.utils.TagUtil;
import com.connorbowman.uscan.views.BaseView;

public class BaseFragment<P extends Presenter> extends Fragment implements BaseView<P> {

    private ActivityCallback mCallback;
    private P mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCallback((ActivityCallback) getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void setCallback(ActivityCallback callback) {
        mCallback = callback;
    }

    @Override
    public ActivityCallback getCallback() {
        return mCallback;
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void showFeedback(int feedback) {
        showFeedback(getString(feedback));
    }

    @Override
    public void showFeedback(String feedback) {
        showFeedback(feedback, null, null);
    }

    @Override
    public void showFeedbackWithAction(int feedback) {}

    protected void showFeedback(String feedback, String action, View.OnClickListener listener) {
        if (feedback == null) {
            feedback = getString(R.string.an_unknown_error_occurred);
        }
        View view = getView();
        if (view != null) {
            Snackbar snackbar = Snackbar.make(view, feedback, Snackbar.LENGTH_LONG);
            if (listener != null) {
                snackbar.setAction(action, listener);
            }
            snackbar.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null && isRemoving()) {
            mPresenter.onStop();
            PresenterManager.getInstance().remove(TagUtil.make(mPresenter.getClass()));
            mCallback = null;
            mPresenter = null;
        }
    }
}
