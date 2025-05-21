package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/readcookie")
public class ReadCookies extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    name = c.getValue();
                    break;
                }
            }
        }

        out.println("<html><body>");
        if (name != null) {
            out.println("<h3>Welcome back, " + name + "!</h3>");
        } else {
            out.println("<h3>No user cookie found.</h3>");
        }
        out.println("</body></html>");
    }
}
