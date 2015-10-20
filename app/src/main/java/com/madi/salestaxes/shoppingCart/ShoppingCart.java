package com.madi.salestaxes.shoppingCart;

import com.madi.salestaxes.products.Product;

import java.util.Iterator;

/**
 * Created by rohmadi on 10/19/15.
 */
public interface ShoppingCart {

    void addProduct(Product product);

    int getItemCount();

    Iterator iterator();

    void getProduct(Product product);

    void empty();

    void removeProduct(Product cancelledProduct);
}