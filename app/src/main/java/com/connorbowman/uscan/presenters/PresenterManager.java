package com.connorbowman.uscan.presenters;

import java.util.HashMap;

public class PresenterManager {

    private static PresenterManager sInstance;

    private static final String KEY_PRESENTER_ID = "PRESENTER_ID";

    private HashMap<String, Presenter<?>> mPresenters = new HashMap<>();

    public static PresenterManager getInstance() {
        if (sInstance == null) {
            sInstance = new PresenterManager();
        }
        return sInstance;
    }

    public Presenter add(String tag, Presenter presenter) {
        return mPresenters.put(tag, presenter);
    }

    public Presenter get(String tag, Presenter presenter) {
        if (mPresenters.get(tag) != null) {
            // Presenter already exists, return it
            return mPresenters.get(tag);
        } else {
            // Create new presenter
            mPresenters.put(tag, presenter);
            return presenter;
        }
    }

    public void remove(String tag) {
        mPresenters.remove(tag);
    }

    public void remove(Presenter presenter) {
        mPresenters.remove(presenter);
    }

    public void clear() {
        mPresenters.clear();
    }
}
