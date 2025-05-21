package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
public class Servelet6 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String adminName = config.getInitParameter("adminName");
        String adminEmail = config.getInitParameter("adminEmail");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h3>ServletConfig Initialization Parameters</h3>");
        out.println("<p><strong>Admin Name:</strong> " + adminName + "</p>");
        out.println("<p><strong>Admin Email:</strong> " + adminEmail + "</p>");
        out.println("</body></html>");
    }
}
