package com.pim.servlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String date = request.getParameter("date");
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Product SET Category = ?, Status = ?, CreatedDate= ? WHERE ProductID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, status);
            ps.setString(3, date);
            ps.setInt(4, productId);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update product");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
