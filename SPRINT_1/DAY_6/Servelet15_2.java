package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/step2")
public class Servelet15_2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String age = request.getParameter("age");
        String city = request.getParameter("city");
        HttpSession session = request.getSession();
        session.setAttribute("age", age);
        session.setAttribute("city", city);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Submitted Data</h2>");
        out.println("<p>Name: " + session.getAttribute("name") + "</p>");
        out.println("<p>Email: " + session.getAttribute("email") + "</p>");
        out.println("<p>Age: " + session.getAttribute("age") + "</p>");
        out.println("<p>City: " + session.getAttribute("city") + "</p>");
        out.println("</body></html>");
    }
}
