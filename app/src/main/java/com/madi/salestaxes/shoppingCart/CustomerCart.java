package com.madi.salestaxes.shoppingCart;

import com.madi.salestaxes.products.Product;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by rohmadi on 10/19/15.
 */
public class CustomerCart implements ShoppingCart {

    private String customerName;
    private String cashierName;
    private String date;
    private ArrayList<Product> cart;
    private static Iterator<Product> iterator;

    public CustomerCart(String customerName, String cashierName, String date) {
        this.customerName = customerName;
        this.cashierName = cashierName;
        this.date = date;
        cart = new ArrayList<Product>();

    }

    @Override
    public void addProduct(Product product) {
        cart.add(product);
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    @Override
    public Iterator iterator() {
        return cart.iterator();
    }

    @Override
    public void getProduct(Product product) {
        cart.remove(product);
    }

    @Override
    public void empty() {
        cart.clear();
    }

    @Override
    public void removeProduct(Product cancelledProduct) {
        cart.remove(cancelledProduct);
    }

}