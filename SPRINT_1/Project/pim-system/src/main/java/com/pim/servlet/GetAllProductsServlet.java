package com.pim.servlet;
import com.pim.dao.ProductDAO;
import com.pim.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllProducts")
public class GetAllProductsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();

        response.setContentType("text/plain"); // Set response type
        PrintWriter out = response.getWriter();

        if (!products.isEmpty()) {
            for (Product product : products) {
                out.println("Product ID: " + product.getProductId());
                out.println("Product Name: " + product.getProductName());
                out.println("ProductId: " + product.getProductId());
                out.println("Category: " + product.getCategory());
                out.println("Status: " + product.getStatus());
                out.println("Last Modified: " + product.getLastModified());
                out.println("--------------------------------------");
            }
        } else {
            out.println("No products found in the database.");
        }
    }
}
