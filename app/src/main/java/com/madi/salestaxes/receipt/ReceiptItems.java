package com.madi.salestaxes.receipt;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rohmadi on 10/19/15.
 */
public class ReceiptItems {
    private int quantity;
    private String name;
    private BigDecimal totalCost;
    private BigDecimal salesTax;
    private boolean isImported;

    public ReceiptItems(int quantity, String name, double salesTax, double totalCost,
                        boolean isImported) {
        this.quantity = quantity;
        this.name = name;
        this.salesTax = new BigDecimal(Double.toString(salesTax)).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
        this.totalCost = new BigDecimal(Double.toString(totalCost)).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
        this.isImported = isImported;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setIsImported(boolean isImported) {
        this.isImported = isImported;
    }
}