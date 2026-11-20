package com.jaideep.inventory.service;

import com.jaideep.inventory.model.Company;
import com.jaideep.inventory.model.Inventory;
import com.jaideep.inventory.model.Product;
import com.jaideep.inventory.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProductService {

    private final Map<Integer, Product> products;
    private final Map<String, Integer> productNameIndex;

    private final CompanyService companyService;
    private final InventoryService inventoryService;

    public ProductService(CompanyService companyService,
                          InventoryService inventoryService) {

        this.products = new HashMap<>();
        this.productNameIndex = new HashMap<>();

        this.companyService = companyService;
        this.inventoryService = inventoryService;
    }
    private String normalizeName(String name) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(
                    "Product name cannot be empty"
            );

        return name.trim().toLowerCase(Locale.ROOT);
    }

    // Add Product with default inventory (0 stock, 5 threshold)
    public void addProduct(Product product) {
        addProduct(product, 0, 5);
    }

    // Add Product with initial stock (5 threshold)
    public void addProduct(Product product, int initialStock) {
        addProduct(product, initialStock, 5);
    }

    public void addProduct(Product product,
                           int initialStock,
                           int lowStockThreshold) {

        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");

        int productId = product.getProductId();

        String productName =
                normalizeName(product.getProductName());

        if (productId <= 0)
            throw new IllegalArgumentException("Invalid Product ID");

        if (products.containsKey(productId))
            throw new IllegalArgumentException(
                    "Product ID already exists"
            );

        if (productNameIndex.containsKey(productName))
            throw new IllegalArgumentException(
                    "Product name already exists"
            );

        if (product.getUnitPrice() == null ||
                product.getUnitPrice().signum() <= 0)
            throw new IllegalArgumentException(
                    "Product price must be greater than zero"
            );

        // Verify supplier
        companyService.findCompany(product.getCompanyId());

        inventoryService.initializeStock(
                productId,
                initialStock,
                lowStockThreshold
        );

        // Register product
        products.put(productId, product);

        // Register name index
        productNameIndex.put(productName, productId);

        System.out.println("Product added successfully!");
    }

    public boolean exists(int productId) {
        return products.containsKey(productId);
    }

    public Product findProductById(int productId) {

        Product product = products.get(productId);

        if (product == null)
            throw new ProductNotFoundException(
                    "Product not found: " + productId
            );

        return product;
    }

    public Product findProductByName(String productName) {

        String key = normalizeName(productName);

        Integer productId = productNameIndex.get(key);

        if (productId == null)
            throw new ProductNotFoundException(
                    "Product not found: " + productName
            );

        return findProductById(productId);
    }

    // Update Product using Product object
    public void updateProduct(int productId, Product updatedProduct) {

        if (updatedProduct == null)
            throw new IllegalArgumentException("Product cannot be null");

        updateProduct(
                productId,
                updatedProduct.getProductName(),
                updatedProduct.getUnitPrice(),
                updatedProduct.getCompanyId(),
                updatedProduct.getCategory()
        );
    }

    // Update Product using Product object alone
    public void updateProduct(Product updatedProduct) {

        if (updatedProduct == null)
            throw new IllegalArgumentException("Product cannot be null");

        updateProduct(updatedProduct.getProductId(), updatedProduct);
    }

    public void updateProduct(int productId,
                              String newName,
                              BigDecimal newPrice,
                              int newCompanyId,
                              String newCategory) {

        Product product = findProductById(productId);

        String normalizedNewName = normalizeName(newName);

        Integer existingId =
                productNameIndex.get(normalizedNewName);

        if (existingId != null && existingId != productId)
            throw new IllegalArgumentException(
                    "Another product already uses this name"
            );

        if (newPrice == null || newPrice.signum() <= 0)
            throw new IllegalArgumentException("Invalid price");

        companyService.findCompany(newCompanyId);

        // Update name index if name changed
        String oldName = normalizeName(product.getProductName());

        if (!oldName.equals(normalizedNewName)) {
            productNameIndex.remove(oldName);
            productNameIndex.put(normalizedNewName, productId);
        }

        product.setProductName(newName.trim());
        product.setUnitPrice(newPrice);
        product.setCompanyId(newCompanyId);
        product.setCategory(newCategory);

        System.out.println("Product updated successfully!");
    }

    public void displayAllProducts() {

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products.values()) {

            System.out.println(product);

            Company company = null;
            try {
                company = companyService.findCompany(product.getCompanyId());
            } catch (Exception ignored) {
            }

            Inventory inventory = null;
            try {
                inventory = inventoryService.findInventory(product.getProductId());
            } catch (Exception ignored) {
            }

            System.out.println(
                    "Supplier: " + (company != null ? company.getCompanyName() : "Unknown Company")
            );

            System.out.println(
                    "Available Stock: " +
                            (inventory != null ? inventory.getAvailableQuantity() : "N/A")
            );

            System.out.println("----------------------");
        }
    }

    public void deleteProduct(int productId) {

        Product product = findProductById(productId);

        products.remove(productId);

        productNameIndex.remove(
                normalizeName(product.getProductName())
        );

        inventoryService.deleteInventory(productId);

        System.out.println("Product deleted successfully!");
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products.values());
    }
}
