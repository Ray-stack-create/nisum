package com.pim.model;

public class ProductAttribute {
    private int attributeId;
    private int productId;
    private String attributeName;
    private String attributeValue;
    
    public ProductAttribute(int attributeId, int productId, String attributeName, String attributeValue) {
        this.attributeId = attributeId;
        this.productId = productId;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
    public int getAttributeId() {
        return attributeId;
    }
    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getAttributeName() {
        return attributeName;
    }
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public String getAttributeValue() {
        return attributeValue;
    }
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    @Override
    public String toString() {
        return "ProductAttribute [attributeId=" + attributeId + ", productId=" + productId + ", attributeName="
                + attributeName + ", attributeValue=" + attributeValue + "]";
    }

}
