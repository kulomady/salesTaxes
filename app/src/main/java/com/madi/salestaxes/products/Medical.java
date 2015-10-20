package com.madi.salestaxes.products;

import com.madi.salestaxes.constants.Constants;

/**
 * Created by rohmadi on 10/19/15.
 */
public class Medical extends Product {

    private double salesTaxPercent = Constants.SALES_TAX_FOR_MEDICAL;

    public Medical(String name, double cost, int quantity, boolean isImported) {
        super(name, cost, quantity, isImported);
        super.setSalesTaxPercent(this.salesTaxPercent);
    }

}