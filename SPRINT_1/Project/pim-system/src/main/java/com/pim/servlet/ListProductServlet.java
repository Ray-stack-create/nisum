package com.pim.servlet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pim.model.Product;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get-products")
public class ListProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        PrintWriter out = response.getWriter();
        List<Product> products = new ArrayList<>();
        int totalCount = 0;
        int recentCount = 0;

        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT * FROM Product ORDER BY CreatedDate DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            Timestamp oneWeekAgo = new Timestamp(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000);
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setStatus(rs.getString("Status"));
                p.setCreatedDate(rs.getTimestamp("CreatedDate"));
                products.add(p);
                totalCount++;

                if (p.getCreatedDate().after(oneWeekAgo)) {
                    recentCount++;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        // Create a wrapper object to hold everything
        JsonObject responseJson = new JsonObject();
        responseJson.add("products", gson.toJsonTree(products));
        responseJson.addProperty("totalCount", totalCount);
        responseJson.addProperty("recentCount", recentCount);

        out.write(responseJson.toString());

//        String json = new Gson().toJson(products);
//        response.getWriter().write(json);
    }
}