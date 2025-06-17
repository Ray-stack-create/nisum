package com.pim.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Product {
    private int productId;
    private String productName;
    private int categoryId;
    private String category;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

    private Timestamp createdDate;
    private Timestamp lastModified;

    // Constructor
    public Product() {}

    public Product(int productId, String productName, int categoryId , String category, String status, Timestamp createdDate,Timestamp lastModified) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.category = category;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Published") || status.equals("Draft")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status. Allowed values: 'Published', 'Draft'");
        }
    }
    public Timestamp getCreatedDate() { return createdDate;}
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    // ToString Method
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", createdDate='" + createdDate + '\''+
                ", lastModified=" + lastModified +
                '}';
    }
}
