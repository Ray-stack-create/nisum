package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cart")
public class Servelet11 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String item = request.getParameter("item");
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (item != null && !item.trim().isEmpty()) {
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h3>Your Shopping Cart:</h3><ul>");
        for (String cartItem : cart) {
            out.println("<li>" + cartItem + "</li>");
        }
        out.println("</ul>");
        out.println("<form action='cart' method='get'>");
        out.println("Add item: <input type='text' name='item'/>");
        out.println("<input type='submit' value='Add to Cart'/>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
