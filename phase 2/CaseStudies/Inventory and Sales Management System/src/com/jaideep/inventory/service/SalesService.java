
package com.jaideep.inventory.service;

import com.jaideep.inventory.model.Product;
import com.jaideep.inventory.model.OrderItem;
import com.jaideep.inventory.model.Transaction;

import com.jaideep.inventory.exception.OutOfStockException;
import com.jaideep.inventory.exception.InsufficientStockException;
import com.jaideep.inventory.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class SalesService {

    private final Map<String, Transaction> transactions;

    private final ProductService productService;
    private final InventoryService inventoryService;

    public SalesService(ProductService productService,
                        InventoryService inventoryService) {

        this.transactions = new LinkedHashMap<>();

        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    // Generate unique transaction ID
    private String generateTransactionId() {

        return "TXN-" + UUID.randomUUID();
    }

    // Process customer order
    public Transaction processSale(Map<Integer, Integer> order) {

        if (order == null || order.isEmpty())
            throw new IllegalArgumentException(
                    "Order cannot be empty"
            );

        // Prepare order items
        List<OrderItem> orderItems = new ArrayList<>();

        // Validate every product and stock
        for (Map.Entry<Integer, Integer> entry : order.entrySet()) {

            int productId = entry.getKey();
            int quantity = entry.getValue();

            // Retrieve product
            Product product =
                    productService.findProductById(productId);

            // Validate stock
            inventoryService.validateStock(
                    productId,
                    product.getProductName(),
                    quantity
            );

            // Capture current product price
            OrderItem item = new OrderItem(
                    productId,
                    quantity,
                    product.getUnitPrice()
            );

            orderItems.add(item);
        }

        // Create transaction
        Transaction transaction = new Transaction(
                generateTransactionId(),
                orderItems
        );

        // Deduct stock only after all validations
        List<OrderItem> deductedItems = new ArrayList<>();

        try {

            for (OrderItem item : orderItems) {

                Product product =
                        productService.findProductById(
                                item.getProductId()
                        );

                inventoryService.removeStock(
                        item.getProductId(),
                        product.getProductName(),
                        item.getQuantity()
                );

                deductedItems.add(item);
            }

            // Save transaction after successful deduction
            transactions.put(
                    transaction.getTransactionId(),
                    transaction
            );

        } catch (RuntimeException exception) {

            // Roll back already deducted stock
            for (OrderItem item : deductedItems) {

                inventoryService.addStock(
                        item.getProductId(),
                        item.getQuantity()
                );
            }

            throw exception;
        }

        return transaction;
    }

    public Transaction findTransaction(String transactionId) {

        Transaction transaction = transactions.get(transactionId);

        if (transaction == null)
            throw new IllegalArgumentException(
                    "Transaction not found: " + transactionId
            );

        return transaction;
    }

    public void displayAllTransactions() {

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");
            return;
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        transactions.values().forEach(transaction -> {

            transaction.displayTransactionDetails();

            System.out.println("-----------------------------");
        });
    }

    public BigDecimal calculateTotalRevenue() {

        return transactions.values()
                .stream()
                .map(Transaction::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalTransactions() {
        return transactions.size();
    }

    public Map<String, Transaction> getTransactions() {

        return Collections.unmodifiableMap(
                new LinkedHashMap<>(transactions)
        );
    }
}
