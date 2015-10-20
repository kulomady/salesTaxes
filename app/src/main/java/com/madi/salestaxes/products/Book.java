package com.madi.salestaxes.products;

import com.madi.salestaxes.constants.Constants;

/**
 * Created by rohmadi on 10/19/15.
 */
public class Book extends Product {

    private double salesTaxPercent = Constants.SALES_TAX_FOR_BOOKS;

    public Book(String title, double cost, int quantity, boolean isImported) {
        super(title, cost, quantity, isImported);
        super.setSalesTaxPercent(this.salesTaxPercent);
    }

}
