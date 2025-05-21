package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servelet7 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String company = context.getInitParameter("companyName");
        String email = context.getInitParameter("supportEmail");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>ServletContext Parameters</h3>");
        out.println("<p><strong>Company:</strong> " + company + "</p>");
        out.println("<p><strong>Support Email:</strong> " + email + "</p>");
        out.println("</body></html>");
    }
}
