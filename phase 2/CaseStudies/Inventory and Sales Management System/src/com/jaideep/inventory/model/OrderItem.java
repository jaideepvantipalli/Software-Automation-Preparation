
package com.jaideep.inventory.model;

import java.math.BigDecimal;

public class OrderItem {

    private final int productId;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderItem(int productId, int quantity,
                     BigDecimal unitPrice) {

        if (productId <= 0)
            throw new IllegalArgumentException("Invalid Product ID");

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        if (unitPrice == null || unitPrice.signum() < 0)
            throw new IllegalArgumentException("Invalid unit price");

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
                "\nQuantity: " + quantity +
                "\nUnit Price: " + unitPrice +
                "\nSubtotal: " + getSubtotal();
    }
}
