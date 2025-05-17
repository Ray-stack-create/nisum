package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchProduct {

    public static List<Product> searchProducts(String category, Double minPrice, Double maxPrice) {
        List<Product> results = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT id, name, category, price FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (category != null && !category.isEmpty()) {
            query.append(" AND category = ?");
            params.add(category);
        }
        if (minPrice != null) {
            query.append(" AND price >= ?");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            query.append(" AND price <= ?");
            params.add(maxPrice);
        }

        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            // Set parameters dynamically
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                );
                results.add(product);
            }

        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }

        return results;
    }

    public static void main(String[] args) {
        List<Product> result = searchProducts("Electronics", 100.0, 500.0);
        for (Product p : result) {
            System.out.println(p);
        }
    }
}
