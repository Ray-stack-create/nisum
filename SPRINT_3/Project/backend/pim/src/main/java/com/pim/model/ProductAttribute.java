package com.pim.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ProductAttributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttributeID")
    private Integer attributeId;

    @Column(name = "AttributeName")
    private String attributeName;

    @Column(name = "AttributeValue")
    private String attributeValue;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    // Getters and Setters
    public ProductAttribute() {
    }
    public Product getProduct() {
        return product;
    }
    // com.pim.model.ProductAttribute.java
    public ProductAttribute(String name, String value, Product product) {
        this.attributeName = name;
        this.attributeValue = value;
        this.product = product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }
}
