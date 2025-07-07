package com.nisum.product_service.specification;

import com.nisum.product_service.model.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> searchProducts(String search) {
        return (root, query, cb) -> {
            String likePattern = "%" + search.toLowerCase() + "%";

            Predicate namePredicate = cb.like(cb.lower(root.get("name")), likePattern);
            Predicate statusPredicate = cb.like(cb.lower(root.get("status")), likePattern);

            // Join attributes
            var attributesJoin = root.join("attributes");

            Predicate skuPredicate = cb.like(cb.lower(attributesJoin.get("sku")), likePattern);
            Predicate sizePredicate = cb.like(cb.lower(attributesJoin.get("size")), likePattern);

            return cb.or(namePredicate, statusPredicate, skuPredicate, sizePredicate);
        };
    }


    public static Specification<Product> hasStatus(String status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Product> hasCategoryId(Long categoryId) {
        return (root, query, cb) -> cb.equal(root.get("categoryId"), categoryId);
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            BigDecimal min = minPrice != null ? BigDecimal.valueOf(minPrice) : BigDecimal.ZERO;
            BigDecimal max = maxPrice != null ? BigDecimal.valueOf(maxPrice) : new BigDecimal(Double.MAX_VALUE);
            return cb.between(root.join("attributes").get("price"), min, max);
        };
    }
}
