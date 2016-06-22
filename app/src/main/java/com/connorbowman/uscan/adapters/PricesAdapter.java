package com.connorbowman.uscan.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.connorbowman.uscan.R;
import com.connorbowman.uscan.models.Price;

import java.util.List;

public class PricesAdapter extends BaseAdapter {

    private List<Price> mPrices;
    private PriceItemListener mItemListener;

    public PricesAdapter(List<Price> prices, PriceItemListener itemListener) {
        setList(prices);
        mItemListener = itemListener;
    }

    public void replaceData(List<Price> prices) {
        setList(prices);
        notifyDataSetChanged();
    }

    private void setList(List<Price> prices) {
        mPrices = prices;
    }

    @Override
    public int getCount() {
        if (mPrices != null && !mPrices.isEmpty()) {
            return mPrices.size();
        } else {
            return 0;
        }
    }

    @Override
    public Price getItem(int i) {
        return mPrices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rowView = inflater.inflate(R.layout.item_price, viewGroup, false);
        }

        final Price price = getItem(i);

        TextView companyName = (TextView) rowView.findViewById(R.id.company_name);
        companyName.setText(price.getCompanyName());

        TextView companyPrice = (TextView) rowView.findViewById(R.id.company_price);
        companyPrice.setText(price.getCompanyPrice());
        Log.d("TEST", "price is " + price.getCompanyPrice());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onPriceClicked(price);
            }
        });

        return rowView;
    }

    public interface PriceItemListener {

        void onPriceClicked(Price price);
    }
}
