package com.jaideep.inventory.exception;

public class InsufficientStockException extends RuntimeException {
    private final int availableQuantity;

    public InsufficientStockException(
            String productName,
            int requestedQuantity,
            int availableQuantity) {

        super("Transaction Failed: Only " +
                availableQuantity + " units of " +
                productName + " are available. Requested: " +
                requestedQuantity);

        this.availableQuantity = availableQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
