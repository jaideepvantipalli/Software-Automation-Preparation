package com.jaideep.inventory.service;

import com.jaideep.inventory.model.Inventory;
import com.jaideep.inventory.model.Product;

import com.jaideep.inventory.exception.OutOfStockException;
import com.jaideep.inventory.exception.InsufficientStockException;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {

    private final Map<Integer, Inventory> inventoryMap;
    private ProductService productService;

    public InventoryService() {
        this.inventoryMap = new HashMap<>();
    }

    public InventoryService(ProductService productService) {
        this.inventoryMap = new HashMap<>();
        this.productService = productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private String getProductName(int productId) {
        if (productService != null) {
            try {
                Product product = productService.findProductById(productId);
                if (product != null && product.getProductName() != null) {
                    return product.getProductName();
                }
            } catch (Exception ignored) {
            }
        }
        return "Product #" + productId;
    }

    public void initializeStock(int productId,
                                int quantity,
                                int threshold) {

        if (inventoryMap.containsKey(productId))
            throw new IllegalArgumentException(
                    "Inventory already exists"
            );

        Inventory inventory =
                new Inventory(productId, quantity, threshold);

        inventoryMap.put(productId, inventory);
    }

    public Inventory findInventory(int productId) {

        Inventory inventory = inventoryMap.get(productId);

        if (inventory == null)
            throw new IllegalArgumentException(
                    "Inventory not found for Product ID: " + productId
            );

        return inventory;
    }

    public int getAvailableStock(int productId) {

        return findInventory(productId).getAvailableQuantity();
    }

    public void addStock(int productId, int quantity) {

        Inventory inventory = findInventory(productId);

        inventory.addStock(quantity);

        System.out.println(
                "Stock added successfully. Current Stock: " +
                        inventory.getAvailableQuantity()
        );
    }

    public void validateStock(int productId,
                              String productName,
                              int requestedQuantity) {

        if (requestedQuantity <= 0)
            throw new IllegalArgumentException(
                    "Requested quantity must be positive"
            );

        Inventory inventory = findInventory(productId);

        int available = inventory.getAvailableQuantity();

        if (available == 0) {

            throw new OutOfStockException(
                    "Alert: " + productName +
                            " is currently out of stock."
            );
        }

        if (requestedQuantity > available) {

            throw new InsufficientStockException(
                    productName,
                    requestedQuantity,
                    available
            );
        }
    }

    public void validateStock(int productId,
                              int requestedQuantity) {

        validateStock(productId, getProductName(productId), requestedQuantity);
    }

    // Remove stock with product name
    public void removeStock(int productId, String productName, int quantity) {

        validateStock(productId, productName, quantity);

        Inventory inventory = findInventory(productId);

        inventory.removeStock(quantity);

        System.out.println(
                "Stock removed successfully. Remaining Stock: " +
                        inventory.getAvailableQuantity()
        );

        displayStockAlert(productId, productName);
    }

    // Remove stock
    public void removeStock(int productId, int quantity) {

        removeStock(productId, getProductName(productId), quantity);
    }


    public void displayStockAlert(int productId, String productName) {

        Inventory inventory = findInventory(productId);

        if (inventory.isOutOfStock()) {

            System.out.println(
                    "ALERT: " + productName +
                            " is OUT OF STOCK!"
            );

        } else if (inventory.isLowStock()) {

            System.out.println(
                    "WARNING: " + productName +
                            " has LOW STOCK. Available: " +
                            inventory.getAvailableQuantity()
            );

        } else {

            System.out.println(
                    productName +
                            " has NORMAL STOCK."
            );
        }
    }

    // Display alert for an individual product
    public void displayStockAlert(int productId) {

        displayStockAlert(productId, getProductName(productId));
    }

    public void displayInventory() {

        if (inventoryMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        inventoryMap.values().forEach(inventory -> {

            String productName = getProductName(inventory.getProductId());

            System.out.println(
                    "\nProduct: " + productName
            );

            System.out.println(inventory);

            displayStockAlert(inventory.getProductId(), productName);

            System.out.println("-------------------------");
        });
    }

    public List<Inventory> getLowStockProducts() {

        return inventoryMap.values()
                .stream()
                .filter(Inventory::isLowStock)
                .collect(Collectors.toList());
    }

    public List<Inventory> getOutOfStockProducts() {

        return inventoryMap.values()
                .stream()
                .filter(Inventory::isOutOfStock)
                .collect(Collectors.toList());
    }

    public List<Inventory> getNormalStockProducts() {

        return inventoryMap.values()
                .stream()
                .filter(Inventory::isNormalStock)
                .collect(Collectors.toList());
    }

    public void deleteInventory(int productId) {

        if (!inventoryMap.containsKey(productId))
            throw new IllegalArgumentException(
                    "Inventory not found"
            );

        inventoryMap.remove(productId);
    }

    public void displayLowStockReport() {

        List<Inventory> lowStock =
                getLowStockProducts();

        if (lowStock.isEmpty()) {
            System.out.println("No low-stock products.");
            return;
        }

        System.out.println("\n===== LOW STOCK REPORT =====");

        lowStock.forEach(inventory -> {

            String productName = getProductName(inventory.getProductId());

            System.out.println(
                    productName +
                            " | Available: " +
                            inventory.getAvailableQuantity()
            );
        });
    }

    public void displayOutOfStockReport() {

        List<Inventory> outOfStock =
                getOutOfStockProducts();

        if (outOfStock.isEmpty()) {
            System.out.println("No out-of-stock products.");
            return;
        }

        System.out.println("\n===== OUT OF STOCK REPORT =====");

        outOfStock.forEach(inventory -> {

            String productName = getProductName(inventory.getProductId());

            System.out.println(productName);
        });
    }

    public Map<Integer, Inventory> getInventoryMap() {
        return Map.copyOf(inventoryMap);
    }
}
