package com.connorbowman.uscan.presenters;

public interface Presenter<V> {

    void attachView(V view);
    void onStart();
    boolean hasView();
    V getView();
    void detachView();
    void onStop();
}
