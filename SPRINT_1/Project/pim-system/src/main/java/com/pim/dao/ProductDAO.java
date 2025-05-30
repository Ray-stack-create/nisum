package com.pim.dao;

import com.pim.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/pim_db";
    private static final String USER = "root";
    private static final String PASSWORD = "beluhecker8@";

    // Establish Database Connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create a New Product
    public void addProduct(Product product) {
        String query = "INSERT INTO Product (ProductName, categoryId, Category, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch a List of Products
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryId"),
                        rs.getString("Category"),
                        rs.getString("Status"),
                        rs.getTimestamp("CreatedDate"),
                        rs.getTimestamp("LastModified")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Fetch Product by ID
    public Product getProductById(int productId) {
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getInt("CategoryId"),
                            rs.getString("Category"),
                            rs.getString("Status"),
                            rs.getTimestamp("CreatedDate"),
                            rs.getTimestamp("LastModified")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update Product Information
    public static boolean updateProduct(String productId, String category, String status, String date) {
        boolean updated = false;

        String sql = "UPDATE Product SET Category = ?, Status = ?, CreatedDate = ? WHERE ProductID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            stmt.setString(2, status);
            stmt.setString(3, date); // Ensure your DB format matches the input (yyyy-MM-dd)
            stmt.setString(4, productId);

            int rowsAffected = stmt.executeUpdate();
            updated = rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Delete Product
    public void deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
