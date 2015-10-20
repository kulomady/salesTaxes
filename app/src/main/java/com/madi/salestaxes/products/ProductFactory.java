package com.madi.salestaxes.products;

import com.madi.salestaxes.constants.Constants;

/**
 * Created by rohmadi on 10/19/15.
 */
public class ProductFactory {

    private static ProductFactory instance;

    public static ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }

    private ProductFactory() {

    }

    public Product createProduct(String productType, String name, double cost, int quantity,
                                 boolean isImported) {

        Product product = null;

        if (productType.equals(Constants.OFFICE_SUPPLIES)) {
            product = new Book(name, cost, quantity, isImported);
        } else if (productType.equals(Constants.FOOD_PRODUCT)) {
            product = new Food(name, cost, quantity, isImported);
        } else if (productType.equals(Constants.MEDICAL_PRODUCT)) {
            product = new Medical(name, cost, quantity, isImported);
        } else {
            product = new Other(name, cost, quantity, isImported);
        }

        return product;
    }

}