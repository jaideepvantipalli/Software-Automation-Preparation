
package com.jaideep.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private final String transactionId;
    private final List<OrderItem> orderItems;
    private final BigDecimal totalAmount;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId,
                       List<OrderItem> orderItems) {

        if (transactionId == null || transactionId.isBlank())
            throw new IllegalArgumentException("Invalid Transaction ID");

        if (orderItems == null || orderItems.isEmpty())
            throw new IllegalArgumentException("Order cannot be empty");

        this.transactionId = transactionId;

        // Defensive copy
        this.orderItems = new ArrayList<>(orderItems);

        this.totalAmount = calculateTotal();

        this.timestamp = LocalDateTime.now();
    }

    private BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void displayTransactionDetails() {

        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Timestamp: " + timestamp);

        System.out.println("\nPurchased Products:");

        for (OrderItem item : orderItems) {
            System.out.println(item);
            System.out.println("---------------------");
        }

        System.out.println("Total Amount: " + totalAmount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction ID: ").append(transactionId)
          .append("\nTimestamp: ").append(timestamp)
          .append("\nPurchased Products:\n");
        for (OrderItem item : orderItems) {
            sb.append(item).append("\n---------------------\n");
        }
        sb.append("Total Amount: ").append(totalAmount);
        return sb.toString();
    }
}
