package com.pim.model;

import java.util.List;

public class AttributeWrapper {
    private int productId;
    private List<ProductAttribute> attributes;

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public List<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
