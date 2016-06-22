package com.connorbowman.uscan.views;

import com.connorbowman.uscan.presenters.Presenter;

public interface TaskView<P extends Presenter> extends BaseView<P> {

    void showLoading(boolean loading);
    void onFinished();
}
