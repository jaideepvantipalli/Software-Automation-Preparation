
package com.jaideep.inventory.model;

public class Inventory {

    private final int productId;
    private int availableQuantity;
    private int lowStockThreshold;

    public Inventory(int productId, int availableQuantity,
                     int lowStockThreshold) {

        if (productId <= 0)
            throw new IllegalArgumentException("Invalid Product ID");

        if (availableQuantity < 0)
            throw new IllegalArgumentException(
                    "Stock cannot be negative"
            );

        if (lowStockThreshold < 0)
            throw new IllegalArgumentException(
                    "Threshold cannot be negative"
            );

        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }

    public int getProductId() {
        return productId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int threshold) {

        if (threshold < 0)
            throw new IllegalArgumentException(
                    "Threshold cannot be negative"
            );

        this.lowStockThreshold = threshold;
    }

    public void addStock(int quantity) {

        if (quantity <= 0)
            throw new IllegalArgumentException(
                    "Stock quantity must be positive"
            );

        availableQuantity = Math.addExact(
                availableQuantity, quantity
        );
    }

    public void removeStock(int quantity) {

        if (quantity <= 0)
            throw new IllegalArgumentException(
                    "Quantity must be positive"
            );

        if (quantity > availableQuantity)
            throw new IllegalArgumentException(
                    "Insufficient stock"
            );

        availableQuantity -= quantity;
    }

    public boolean isOutOfStock() {
        return availableQuantity == 0;
    }

    public boolean isLowStock() {
        return availableQuantity > 0 &&
                availableQuantity <= lowStockThreshold;
    }

    public boolean isNormalStock() {
        return availableQuantity > lowStockThreshold;
    }

    @Override
    public String toString() {

        return "Product ID: " + productId +
                "\nAvailable Quantity: " + availableQuantity +
                "\nLow Stock Threshold: " + lowStockThreshold;
    }
}
