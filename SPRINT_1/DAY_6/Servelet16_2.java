package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/target")
public class Servelet16_2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = (String) request.getAttribute("user");
        out.println("<html><body>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>This message is from the TargetServlet.</p>");
        out.println("</body></html>");
    }
}
