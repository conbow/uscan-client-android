package com.connorbowman.uscan.views;

import com.connorbowman.uscan.models.Price;
import com.connorbowman.uscan.presenters.ResultsPresenter;

import java.util.List;

public interface ResultsView extends TaskView<ResultsPresenter> {

    void onFinished(List<Price> prices);
}
