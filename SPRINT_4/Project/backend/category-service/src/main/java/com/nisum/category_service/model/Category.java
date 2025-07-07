package com.nisum.category_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "category_image")
    private String categoryImage;

    @Column(name = "description")
    private String description;

    public Category() {}

    public Category(String name, String categoryImage, String description) {
        this.name = name;
        this.categoryImage = categoryImage;
        this.description = description;
    }

    // Getters and setters
    public int getId() { return categoryId; }
    public void setId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategoryImage() { return categoryImage; }
    public void setCategoryImage(String categoryImage) { this.categoryImage = categoryImage; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
