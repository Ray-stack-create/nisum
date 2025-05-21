package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class Servelet8 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validUsername = "admin";
        String validPassword = "1234";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (username.equals(validUsername) && password.equals(validPassword)) {
            out.println("<html><body>");
            out.println("<h3>Login Successful!</h3>");
            out.println("<p>Welcome, " + username + "!</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h3>Login Failed</h3>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}
