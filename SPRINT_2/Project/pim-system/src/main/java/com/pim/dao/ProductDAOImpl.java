package com.pim.dao;

import com.pim.model.Product;
import com.pim.model.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (ProductName, Category, Status, CreatedDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setString(3, product.getStatus());
            ps.setTimestamp(4, product.getCreatedDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(int productId) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setStatus(rs.getString("Status"));
                p.setCreatedDate(rs.getTimestamp("CreatedDate"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE Product SET ProductName = ?, Category = ?, Status = ?, CreatedDate = ? WHERE ProductID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getStatus());
            stmt.setTimestamp(4, product.getLastModified());
            stmt.setInt(5, product.getProductId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product pr = new Product();
                    pr.setProductId(rs.getInt("ProductID"));
                    pr.setProductName(rs.getString("ProductName"));
                    pr.setCategory(rs.getString("Category"));
                    pr.setStatus(rs.getString("Status"));
                    pr.setCreatedDate(rs.getTimestamp("CreatedDate"));
                    return pr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProductAttributes(List<ProductAttribute> attributes) {
        String sql = "INSERT INTO ProductAttributes (ProductID, AttributeName, AttributeValue) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ProductAttribute attr = attributes.get(i);
                ps.setInt(1, attr.getProductId());
                ps.setString(2, attr.getAttributeName());
                ps.setString(3, attr.getAttributeValue());
            }

            @Override
            public int getBatchSize() {
                return attributes.size();
            }
        });
    }

    @Override
    public List<ProductAttribute> getAttributesByProductId(int productId) {
        List<ProductAttribute> attributes = new ArrayList<>();
        String sql = "SELECT * FROM ProductAttributes WHERE ProductID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductAttribute attr = new ProductAttribute();
                    attr.setAttributeId(rs.getInt("AttributeID"));
                    attr.setProductId(rs.getInt("ProductID"));
                    attr.setAttributeName(rs.getString("AttributeName"));
                    attr.setAttributeValue(rs.getString("AttributeValue"));
                    attributes.add(attr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }
}
