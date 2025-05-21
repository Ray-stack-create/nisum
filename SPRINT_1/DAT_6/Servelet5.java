package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/setcookie")
public class Servelet5 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        Cookie userCookie = new Cookie("username", name);
        userCookie.setMaxAge(60 * 60);
        response.addCookie(userCookie);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Cookie Set Successfully!</h3>");
        out.println("<p>Welcome, " + name + "</p>");
        out.println("<a href='readcookie'>Go to Cookie Reader</a>");
        out.println("</body></html>");
    }
}
