package com.connorbowman.uscan.views;

import com.connorbowman.uscan.activities.ActivityCallback;
import com.connorbowman.uscan.presenters.Presenter;

public interface BaseView<P extends Presenter> {

    void setCallback(ActivityCallback callback);
    ActivityCallback getCallback();
    void setPresenter(P presenter);
    P getPresenter();
    void showFeedback(int feedback);
    void showFeedback(String feedback);
    void showFeedbackWithAction(int feedback);
}
