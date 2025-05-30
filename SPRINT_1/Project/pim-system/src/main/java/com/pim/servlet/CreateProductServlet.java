package com.pim.servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/add-product")
public class CreateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String date = request.getParameter("date");
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Product ( ProductName, Category, Status, CreatedDate) VALUES ( ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setString(2, category);
            ps.setString(3, status);
            ps.setString(4, date);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect("pages/homepage.html"); // Redirect to list page after creation
            } else {
                response.getWriter().println("Failed to insert product.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

