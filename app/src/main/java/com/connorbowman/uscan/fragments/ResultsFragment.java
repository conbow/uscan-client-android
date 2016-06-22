package com.connorbowman.uscan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.adapters.PricesAdapter;
import com.connorbowman.uscan.models.Price;
import com.connorbowman.uscan.presenters.PresenterManager;
import com.connorbowman.uscan.presenters.ResultsPresenter;
import com.connorbowman.uscan.utils.SwipeRefreshUtil;
import com.connorbowman.uscan.utils.TagUtil;
import com.connorbowman.uscan.views.ResultsView;

import java.util.ArrayList;
import java.util.List;

public class ResultsFragment extends BaseFragment<ResultsPresenter> implements ResultsView {

    public static final String TAG = TagUtil.make(ResultsFragment.class);
    public static final int TITLE = R.string.prices;

    private String mBarcode;
    private PricesAdapter mListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mViewNoResults;
    private View mViewResults;
    private View mProgress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListAdapter = new PricesAdapter(new ArrayList<Price>(0), mItemListener);

        if (getArguments() != null) {
            mBarcode = getArguments().getString(ScanFragment.BarcodeObject);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setup presenter
        setPresenter((ResultsPresenter) PresenterManager.getInstance()
                .get(ResultsPresenter.TAG, new ResultsPresenter()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadPrices(mBarcode, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        // Notes adapter
        ListView listView = (ListView) view.findViewById(R.id.prices_list);
        listView.setAdapter(mListAdapter);

        mProgress = view.findViewById(R.id.progress);

        mViewResults = view.findViewById(R.id.view_results);
        mViewNoResults = view.findViewById(R.id.view_no_results);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.accent));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadPrices(mBarcode, true);
            }
        });

        return view;
    }

    @Override
    public void showLoading(boolean loading) {
        mSwipeRefreshLayout.setRefreshing(loading);
        SwipeRefreshUtil.setRefreshing(mSwipeRefreshLayout, loading);
        if (loading) {
            mProgress.setVisibility(View.VISIBLE);
        } else {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinished() {
        //onFinished(null);
    }

    @Override
    public void onFinished(List<Price> prices) {
        if (prices == null || prices.isEmpty()) {
            mViewResults.setVisibility(View.GONE);
            mViewNoResults.setVisibility(View.VISIBLE);
            mListAdapter.replaceData(null);
        } else {
            mViewResults.setVisibility(View.VISIBLE);
            mViewNoResults.setVisibility(View.GONE);
            mListAdapter.replaceData(prices);
        }
    }

    PricesAdapter.PriceItemListener mItemListener = new PricesAdapter.PriceItemListener() {
        @Override
        public void onPriceClicked(Price price) {
            // TODO
            Log.d(TAG, "Price " + price + " clicked");
        }
    };
}
