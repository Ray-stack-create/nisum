package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/setSession")
public class Servelet12 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", "Sankha");
        session.setAttribute("email", "sankha@gmail.com");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Session attributes set successfully!</h3>");
        out.println("<a href='getSession'>Go to Session Reader</a>");
        out.println("</body></html>");
    }
}
