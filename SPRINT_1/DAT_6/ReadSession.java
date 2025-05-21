package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/getSession")
public class ReadSession extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h3>Session Data:</h3>");
        if (session != null) {
            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");

            if (username != null && email != null) {
                out.println("<p>Username: " + username + "</p>");
                out.println("<p>Email: " + email + "</p>");
            } else {
                out.println("<p>No session attributes found.</p>");
            }
        } else {
            out.println("<p>No active session found.</p>");
        }

        out.println("</body></html>");
    }
}
