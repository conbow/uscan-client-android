package com.connorbowman.uscan.models;

import android.util.Log;

public class Price {

    private String companyName;
    private String companyPrice;

    public Price(String companyName, String companyPrice) {
        this.companyName = companyName;
        this.companyPrice = companyPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyPrice() {
        return companyPrice;
    }
}
