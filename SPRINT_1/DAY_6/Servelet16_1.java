package com.nisum;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/start")
public class Servelet16_1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        request.setAttribute("user", username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("target");
        dispatcher.forward(request, response);
    }
}
