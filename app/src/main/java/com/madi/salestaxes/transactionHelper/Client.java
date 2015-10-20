package com.madi.salestaxes.transactionHelper;

import com.madi.salestaxes.products.Product;
import com.madi.salestaxes.products.ProductFactory;
import com.madi.salestaxes.shoppingCart.CustomerCart;
import com.madi.salestaxes.shoppingCart.ShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rohmadi on 10/19/15.
 */
public class Client {
    CategoryLookup lookup;
    ArrayList<InputData> inputData;
    ProductFactory productFactory;
    ShoppingCart shoppingCart;
    Receipt receipt;

    public Client(ArrayList<InputData> inputData){
        this.inputData = inputData;
    }

    public Receipt performTransaction(String customerName, String ClientName)
            throws  NumberFormatException, IOException {
        // read and prepare input data
        lookup = CategoryLookup.getInstance();


        productFactory = ProductFactory.getInstance();
        shoppingCart = new CustomerCart(customerName, customerName, new Date().toString());

        for (InputData data : inputData) {
            int quantity = data.getQuantity();
            String productDescription = data.getProductDescription();
            double cost = data.getCost();
            boolean isImported = data.isImported();
            String category = lookup.getCategoryFor(productDescription);

            Product product = productFactory.createProduct(category, productDescription, cost,
                    quantity, isImported);
            shoppingCart.addProduct(product);
        }

        receipt = new Receipt(shoppingCart);
        return receipt;
    }

}