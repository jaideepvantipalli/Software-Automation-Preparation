
package com.jaideep.inventory.model;

import java.math.BigDecimal;

public class Product {

    private final int productId;
    private String productName;
    private BigDecimal unitPrice;
    private int companyId;
    private String category;

    public Product(int productId, String productName,
                   BigDecimal unitPrice, int companyId,
                   String category) {

        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.companyId = companyId;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCategory() {
        return category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", companyId=" + companyId +
                ", category='" + category + '\'' +
                '}';
    }
}
