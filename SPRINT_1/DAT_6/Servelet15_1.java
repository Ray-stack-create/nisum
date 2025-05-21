package com.nisum;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/step1")
public class Servelet15_1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        response.sendRedirect("page2.html");
    }
}
