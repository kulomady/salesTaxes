package com.madi.salestaxes.transactionHelper;

import com.madi.salestaxes.constants.Constants;
import com.madi.salestaxes.products.Product;
import com.madi.salestaxes.receipt.ReceiptItems;
import com.madi.salestaxes.shoppingCart.ShoppingCart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by rohmadi on 10/19/15.
 */
public class Receipt{

    private ArrayList<ReceiptItems> receiptItemsList;
    private double grandSalesTaxesTotal = 0;
    private double grandBaseCostTotal = 0;
    private double grandOverallTotal = 0;

    private ShoppingCart cart;

    public Receipt(ShoppingCart cart) {
        this.cart = cart;
        this.receiptItemsList = new ArrayList<ReceiptItems>();
        generateReceipt();
    }



    /** Helper method that does all the calculations */
    private void generateReceipt() {
        Iterator<Product> cartIterator = cart.iterator();

        while (cartIterator.hasNext()) {
            Product product = cartIterator.next();
            int quantity = product.getQuantity();
            String name = product.getName();
            boolean isImported = product.isImported();
            double totalBaseCostForThisProduct = product.getCost();
            double totalSalesTaxForThisProduct = product.getSalesTax();
            double totalCostForThisProduct = totalBaseCostForThisProduct
                    + totalSalesTaxForThisProduct;

            grandSalesTaxesTotal += totalSalesTaxForThisProduct;
            grandBaseCostTotal += totalBaseCostForThisProduct;

            receiptItemsList.add(new ReceiptItems(quantity, name, totalSalesTaxForThisProduct,
                    totalCostForThisProduct, isImported));
        }

        grandOverallTotal = grandBaseCostTotal + grandSalesTaxesTotal;
    }
    /** Get list receiptItem */
    public ArrayList<ReceiptItems> getReceiptItemsList(){
        return receiptItemsList;
    }

    public BigDecimal getGrandSalesTaxesTotal(){
        return new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getGrandOverallTotal(){
        return new BigDecimal(Double.toString(grandOverallTotal)).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
    }

    public void emptyCardOnReceipt(){
        cart.empty();
    }

}