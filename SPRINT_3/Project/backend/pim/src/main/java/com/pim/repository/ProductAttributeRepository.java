package com.pim.repository;

import com.pim.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
    List<ProductAttribute> findByProduct_ProductId(Integer productId);
    void deleteByProduct_ProductId(Integer productId);

}
