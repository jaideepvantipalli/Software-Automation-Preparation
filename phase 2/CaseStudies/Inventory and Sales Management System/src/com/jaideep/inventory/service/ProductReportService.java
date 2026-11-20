
package com.jaideep.inventory.service;

import com.jaideep.inventory.model.Company;
import com.jaideep.inventory.model.Inventory;
import com.jaideep.inventory.model.OrderItem;
import com.jaideep.inventory.model.Product;
import com.jaideep.inventory.model.Transaction;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductReportService {

    private final ProductService productService;
    private final CompanyService companyService;
    private final InventoryService inventoryService;
    private final SalesService salesService;

    public ProductReportService(
            ProductService productService,
            CompanyService companyService,
            InventoryService inventoryService,
            SalesService salesService) {

        this.productService = productService;
        this.companyService = companyService;
        this.inventoryService = inventoryService;
        this.salesService = salesService;
    }

    // 1. Filter products by category
    public List<Product> getProductsByCategory(String category) {

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException(
                    "Category cannot be empty");
        }

        return productService.getAllProducts()
                .stream()
                .filter(p -> p.getCategory() != null
                        && p.getCategory().equalsIgnoreCase(category.trim()))
                .toList();
    }

    // 2. Products above a given price
    public List<Product> getProductsAbovePrice(BigDecimal price) {

        validatePrice(price);

        return productService.getAllProducts()
                .stream()
                .filter(p -> p.getUnitPrice().compareTo(price) > 0)
                .toList();
    }

    // 3. Products below a given price
    public List<Product> getProductsBelowPrice(BigDecimal price) {

        validatePrice(price);

        return productService.getAllProducts()
                .stream()
                .filter(p -> p.getUnitPrice().compareTo(price) < 0)
                .toList();
    }

    // 4. Products within a price range
    public List<Product> getProductsByPriceRange(
            BigDecimal min, BigDecimal max) {

        validatePrice(min);
        validatePrice(max);

        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException(
                    "Minimum price cannot exceed maximum price");
        }

        return productService.getAllProducts()
                .stream()
                .filter(p ->
                        p.getUnitPrice().compareTo(min) >= 0
                                && p.getUnitPrice().compareTo(max) <= 0)
                .toList();
    }

    // 5. Products supplied by a company
    public List<Product> getProductsByCompany(int companyId) {

        return productService.getAllProducts()
                .stream()
                .filter(p -> p.getCompanyId() == companyId)
                .toList();
    }

    // 6. Search products by name
    public List<Product> searchProductsByName(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Search keyword cannot be empty");
        }

        String search = keyword.trim().toLowerCase(Locale.ROOT);

        return productService.getAllProducts()
                .stream()
                .filter(p -> p.getProductName() != null
                        && p.getProductName().toLowerCase(Locale.ROOT).contains(search))
                .toList();
    }


    // 1. Sort by price - ascending
    public List<Product> sortByPriceAscending() {

        return productService.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getUnitPrice))
                .toList();
    }

    // 2. Sort by price - descending
    public List<Product> sortByPriceDescending() {

        return productService.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getUnitPrice)
                        .reversed())
                .toList();
    }

    // 3. Sort by product name
    public List<Product> sortByName() {

        return productService.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(
                        Product::getProductName,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .toList();
    }

    // 4. Sort by category and then by price
    public List<Product> sortByCategoryAndPrice() {

        return productService.getAllProducts()
                .stream()
                .sorted(
                        Comparator.comparing(Product::getCategory,
                                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                                .thenComparing(Product::getUnitPrice)
                )
                .toList();
    }

    // 5. Sort by company ID and then product name
    public List<Product> sortByCompanyAndName() {

        return productService.getAllProducts()
                .stream()
                .sorted(
                        Comparator.comparingInt(Product::getCompanyId)
                                .thenComparing(Product::getProductName)
                )
                .toList();
    }


    // Flatten all order items from all transactions
    private List<OrderItem> getAllOrderItems() {

        return salesService.getTransactions()
                .values()
                .stream()
                .flatMap(t -> t.getOrderItems().stream())
                .toList();
    }

    // 1. Total units sold for every product
    public Map<Integer, Integer> getTotalUnitsSoldByProduct() {

        return getAllOrderItems()
                .stream()
                .collect(Collectors.groupingBy(
                        OrderItem::getProductId,
                        Collectors.summingInt(OrderItem::getQuantity)
                ));
    }

    // 2. Total quantity sold across all products
    public int getTotalUnitsSold() {

        return getAllOrderItems()
                .stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    // 3. Total number of different products sold
    public long getDifferentProductsSold() {

        return getAllOrderItems()
                .stream()
                .map(OrderItem::getProductId)
                .distinct()
                .count();
    }

    // 1. Total revenue from all transactions
    public BigDecimal getTotalRevenue() {

        return salesService.getTransactions()
                .values()
                .stream()
                .map(Transaction::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 2. Revenue generated by each product
    public Map<Integer, BigDecimal> getRevenueByProduct() {

        return getAllOrderItems()
                .stream()
                .collect(Collectors.groupingBy(
                        OrderItem::getProductId,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                OrderItem::getSubtotal,
                                BigDecimal::add
                        )
                ));
    }

    // 3. Average transaction value
    public BigDecimal getAverageTransactionValue() {

        Collection<Transaction> transactions =
                salesService.getTransactions().values();

        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = getTotalRevenue();

        return total.divide(
                BigDecimal.valueOf(transactions.size()),
                2,
                java.math.RoundingMode.HALF_UP
        );
    }

    // 4. Highest-value transaction
    public Optional<Transaction> getHighestValueTransaction() {

        return salesService.getTransactions()
                .values()
                .stream()
                .max(Comparator.comparing(
                        Transaction::getTotalAmount));
    }

    // 1. Count products supplied by each company
    public Map<Integer, Long> getProductCountByCompany() {

        return productService.getAllProducts()
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCompanyId,
                        Collectors.counting()
                ));
    }

    // 2. Display supplier-wise product report
    public void displaySupplierReport() {

        Map<Integer, Long> report = getProductCountByCompany();

        System.out.println("\n========== SUPPLIER REPORT ==========");

        if (report.isEmpty()) {
            System.out.println("No supplier products found.");
            return;
        }

        report.forEach((companyId, count) -> {

            Company company = companyService.getCompany(companyId);

            String companyName = company == null
                    ? "Unknown Company"
                    : company.getCompanyName();

            System.out.println(
                    "Company ID: " + companyId
                            + " | Company: " + companyName
                            + " | Products: " + count
            );
        });
    }

    // 3. Get supplier with the maximum number of products
    public Optional<Map.Entry<Integer, Long>>
    getLargestSupplier() {

        return getProductCountByCompany()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    // 1. Low-stock inventory
    public List<Inventory> getLowStockInventory() {

        return inventoryService.getInventoryMap()
                .values()
                .stream()
                .filter(Inventory::isLowStock)
                .toList();
    }

    // 2. Out-of-stock inventory
    public List<Inventory> getOutOfStockInventory() {

        return inventoryService.getInventoryMap()
                .values()
                .stream()
                .filter(Inventory::isOutOfStock)
                .toList();
    }

    // 3. Normal-stock inventory
    public List<Inventory> getNormalStockInventory() {

        return inventoryService.getInventoryMap()
                .values()
                .stream()
                .filter(Inventory::isNormalStock)
                .toList();
    }

    // 4. Display inventory report with product names
    public void displayInventoryReport(List<Inventory> inventoryList) {

        if (inventoryList.isEmpty()) {
            System.out.println("No inventory records found.");
            return;
        }

        for (Inventory inventory : inventoryList) {

            String productName;
            try {
                Product product =
                        productService.findProductById(inventory.getProductId());
                productName = product.getProductName();
            } catch (Exception e) {
                productName = "Product #" + inventory.getProductId();
            }

            System.out.println(
                    "Product: " + productName
                            + " | Available: " + inventory.getAvailableQuantity()
                            + " | Threshold: " + inventory.getLowStockThreshold()
            );
        }
    }

    // 1. Top-selling product
    public Optional<Map.Entry<Integer, Integer>>
    getTopSellingProduct() {

        return getTotalUnitsSoldByProduct()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    // 2. Top N selling products
    public List<Map.Entry<Integer, Integer>>
    getTopSellingProducts(int limit) {

        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "Limit must be greater than zero");
        }

        return getTotalUnitsSoldByProduct()
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                        .reversed())
                .limit(limit)
                .toList();
    }

    // 3. Highest-revenue product
    public Optional<Map.Entry<Integer, BigDecimal>>
    getHighestRevenueProduct() {

        return getRevenueByProduct()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    // 4. Display top-selling products with names
    public void displayTopSellingProducts(int limit) {

        List<Map.Entry<Integer, Integer>> topProducts =
                getTopSellingProducts(limit);

        System.out.println("\n========== TOP SELLING PRODUCTS ==========");

        if (topProducts.isEmpty()) {
            System.out.println("No sales recorded.");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : topProducts) {

            String productName;
            try {
                Product product =
                        productService.findProductById(entry.getKey());
                productName = product.getProductName();
            } catch (Exception e) {
                productName = "Product #" + entry.getKey();
            }

            System.out.println(
                    productName
                            + " | Units Sold: " + entry.getValue()
            );
        }
    }


    public void displayBusinessDashboard() {

        System.out.println("\n======================================");
        System.out.println("       BUSINESS SUMMARY REPORT");
        System.out.println("======================================");

        System.out.println(
                "Total Products: "
                        + productService.getAllProducts().size()
        );

        System.out.println(
                "Total Transactions: "
                        + salesService.getTotalTransactions()
        );

        System.out.println(
                "Total Units Sold: " + getTotalUnitsSold()
        );

        System.out.println(
                "Total Revenue: Rs. " + getTotalRevenue()
        );

        System.out.println(
                "Average Transaction Value: Rs. "
                        + getAverageTransactionValue()
        );

        System.out.println(
                "Low Stock Products: "
                        + getLowStockInventory().size()
        );

        System.out.println(
                "Out of Stock Products: "
                        + getOutOfStockInventory().size()
        );

        System.out.println("\n--- TOP SELLING PRODUCTS ---");

        displayTopSellingProducts(5);

        System.out.println("\n--- SUPPLIER REPORT ---");

        displaySupplierReport();

        System.out.println("======================================");
    }


    private void validatePrice(BigDecimal price) {

        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be null or negative");
        }
    }

    public void displayProducts(List<Product> products) {

        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("----------------------------------------");

        products.forEach(System.out::println);

        System.out.println("Total products: " + products.size());

        System.out.println("----------------------------------------");
    }
}
