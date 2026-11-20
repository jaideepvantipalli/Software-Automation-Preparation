package com.jaideep.inventory;

import com.jaideep.inventory.model.*;
import com.jaideep.inventory.service.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static CompanyService companyService;
    private static InventoryService inventoryService;
    private static ProductService productService;
    private static SalesService salesService;
    private static ProductReportService reportService;

    public static void main(String[] args) {

        initializeServices();

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1 -> companyMenu();

                    case 2 -> productMenu();

                    case 3 -> inventoryMenu();

                    case 4 -> salesMenu();

                    case 5 -> transactionMenu();

                    case 6 -> reportMenu();

                    case 7 -> productService.displayAllProducts();

                    case 8 -> inventoryService.displayInventory();

                    case 9 -> {
                        System.out.println("Thank you for using the application.");
                        running = false;
                    }

                    default -> System.out.println("Invalid menu choice.");
                }

            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void initializeServices() {

        companyService = new CompanyService();

        inventoryService = new InventoryService();

        productService = new ProductService(companyService, inventoryService);

        inventoryService.setProductService(productService);

        salesService = new SalesService(productService, inventoryService);

        reportService =
                new ProductReportService(
                        productService,
                        companyService,
                        inventoryService,
                        salesService
                );
    }
    private static void displayMainMenu() {

        System.out.println("\n========================================");
        System.out.println(" INVENTORY & SALES MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.println("1. Company Management");
        System.out.println("2. Product Management");
        System.out.println("3. Inventory Management");
        System.out.println("4. Sales Management");
        System.out.println("5. Transaction History");
        System.out.println("6. Business Reports");
        System.out.println("7. Display All Products");
        System.out.println("8. Display All Inventory");
        System.out.println("9. Exit");
    }

    private static void companyMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== COMPANY MANAGEMENT ==========");
            System.out.println("1. Add Company");
            System.out.println("2. Update Company");
            System.out.println("3. Delete Company");
            System.out.println("4. Search Company");
            System.out.println("5. Display All Companies");
            System.out.println("6. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> {

                        int id = readInt("Company ID: ");
                        String name = readString("Company Name: ");
                        String contact = readString("Contact: ");
                        String location = readString("Location: ");

                        Company company =
                                new Company(id, name, contact, location);

                        companyService.addCompany(company);
                    }

                    case 2 -> {

                        int id = readInt("Company ID: ");

                        String name = readString("New Company Name: ");
                        String contact = readString("New Contact: ");
                        String location = readString("New Location: ");

                        Company updatedCompany =
                                new Company(id, name, contact, location);

                        companyService.updateCompany(updatedCompany);
                    }

                    case 3 -> {

                        int id = readInt("Company ID to delete: ");

                        companyService.deleteCompany(id);
                    }

                    case 4 -> {

                        int id = readInt("Company ID: ");

                        Company company = companyService.getCompany(id);

                        if (company != null) {
                            System.out.println(company);
                        } else {
                            System.out.println("Company not found.");
                        }
                    }

                    case 5 -> companyService.displayAllCompanies();

                    case 6 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
    private static void productMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== PRODUCT MANAGEMENT ==========");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Search Product By ID");
            System.out.println("5. Search Product By Name");
            System.out.println("6. Display All Products");
            System.out.println("7. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> {

                        int id = readInt("Product ID: ");
                        String name = readString("Product Name: ");
                        BigDecimal price = readBigDecimal("Price: ");
                        int companyId = readInt("Company ID: ");
                        String category = readString("Category: ");
                        int quantity = readInt("Initial Stock: ");

                        if (quantity < 0) {
                            System.out.println("Initial stock cannot be negative.");
                            break;
                        }

                        Product product =
                                new Product(id, name, price, companyId, category);

                        productService.addProduct(product);

                        if (quantity > 0) {
                            inventoryService.addStock(id, quantity);
                        }
                    }

                    case 2 -> {

                        int id = readInt("Product ID: ");

                        String name = readString("New Product Name: ");
                        BigDecimal price = readBigDecimal("New Price: ");
                        int companyId = readInt("New Company ID: ");
                        String category = readString("New Category: ");

                        Product updatedProduct =
                                new Product(id, name, price, companyId, category);

                        productService.updateProduct(id, updatedProduct);
                    }

                    case 3 -> {

                        int id = readInt("Product ID to delete: ");

                        productService.deleteProduct(id);
                    }

                    case 4 -> {

                        int id = readInt("Product ID: ");

                        System.out.println(
                                productService.findProductById(id));
                    }

                    case 5 -> {

                        String name = readString("Product Name: ");

                        System.out.println(
                                productService.findProductByName(name));
                    }

                    case 6 -> productService.displayAllProducts();

                    case 7 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void inventoryMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== INVENTORY MANAGEMENT ==========");
            System.out.println("1. Add Stock");
            System.out.println("2. Remove Stock");
            System.out.println("3. Check Stock");
            System.out.println("4. Display Inventory");
            System.out.println("5. Low Stock Report");
            System.out.println("6. Out Of Stock Report");
            System.out.println("7. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> {

                        int id = readInt("Product ID: ");
                        int quantity = readInt("Quantity to add: ");

                        inventoryService.addStock(id, quantity);
                    }

                    case 2 -> {

                        int id = readInt("Product ID: ");
                        int quantity = readInt("Quantity to remove: ");

                        Product product = productService.findProductById(id);

                        inventoryService.removeStock(
                                id,
                                product.getProductName(),
                                quantity
                        );
                    }

                    case 3 -> {

                        int id = readInt("Product ID: ");

                        System.out.println(
                                "Available Stock: "
                                        + inventoryService.getAvailableStock(id));
                    }

                    case 4 -> inventoryService.displayInventory();

                    case 5 -> inventoryService.displayLowStockReport();

                    case 6 -> inventoryService.displayOutOfStockReport();

                    case 7 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void salesMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== SALES MANAGEMENT ==========");
            System.out.println("1. Create Customer Order");
            System.out.println("2. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> createCustomerOrder();

                    case 2 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void createCustomerOrder() {

        Map<Integer, Integer> order = new LinkedHashMap<>();

        boolean adding = true;

        while (adding) {

            int productId = readInt("Product ID: ");
            int quantity = readInt("Quantity: ");

            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            try {
                productService.findProductById(productId);
            } catch (RuntimeException e) {
                System.out.println("Product not found: " + productId);
                continue;
            }

            if (order.containsKey(productId)) {

                int oldQuantity = order.get(productId);
                order.put(productId, Math.addExact(oldQuantity, quantity));

            } else {

                order.put(productId, quantity);
            }

            String answer = readString("Add another product? (Y/N): ");

            adding = answer.equalsIgnoreCase("Y");
        }

        if (order.isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }

        System.out.println("\nConfirm sale? (Y/N)");

        String confirmation = readString("Your choice: ");

        if (confirmation.equalsIgnoreCase("Y")) {

            Transaction transaction =
                    salesService.processSale(order);

            System.out.println("\nSale completed successfully!");

            transaction.displayTransactionDetails();

        } else {

            System.out.println("Sale cancelled.");
        }
    }

    private static void transactionMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== TRANSACTION HISTORY ==========");
            System.out.println("1. Display All Transactions");
            System.out.println("2. Search Transaction");
            System.out.println("3. Total Transactions");
            System.out.println("4. Total Revenue");
            System.out.println("5. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> salesService.displayAllTransactions();

                    case 2 -> {

                        String id = readString("Transaction ID: ");

                        System.out.println(
                                salesService.findTransaction(id));
                    }

                    case 3 -> System.out.println(
                            "Total Transactions: "
                                    + salesService.getTotalTransactions());

                    case 4 -> System.out.println(
                            "Total Revenue: "
                                    + salesService.calculateTotalRevenue());

                    case 5 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void reportMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== BUSINESS REPORTS ==========");
            System.out.println("1. Filter By Category");
            System.out.println("2. Filter By Price");
            System.out.println("3. Sort By Price");
            System.out.println("4. Sort By Name");
            System.out.println("5. Units Sold By Product");
            System.out.println("6. Revenue By Product");
            System.out.println("7. Supplier Report");
            System.out.println("8. Low Stock Report");
            System.out.println("9. Top Selling Products");
            System.out.println("10. Business Dashboard");
            System.out.println("11. Back");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> {

                        String category = readString("Category: ");

                        reportService.displayProducts(
                                reportService.getProductsByCategory(category));
                    }

                    case 2 -> {

                        BigDecimal price = readBigDecimal("Price: ");

                        reportService.displayProducts(
                                reportService.getProductsAbovePrice(price));
                    }

                    case 3 -> reportService.displayProducts(
                            reportService.sortByPriceAscending());

                    case 4 -> reportService.displayProducts(
                            reportService.sortByName());

                    case 5 -> System.out.println(
                            reportService.getTotalUnitsSoldByProduct());

                    case 6 -> System.out.println(
                            reportService.getRevenueByProduct());

                    case 7 -> reportService.displaySupplierReport();

                    case 8 -> reportService.displayInventoryReport(
                            reportService.getLowStockInventory());

                    case 9 -> {

                        int limit = readInt("Number of top products: ");

                        reportService.displayTopSellingProducts(limit);
                    }

                    case 10 -> reportService.displayBusinessDashboard();

                    case 11 -> back = true;

                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static BigDecimal readBigDecimal(String message) {

        while (true) {

            System.out.print(message);

            try {

                return new BigDecimal(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Enter a valid price.");
            }
        }
    }
}
