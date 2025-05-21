package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/validate")
public class Servelet17 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        boolean validName = name != null && name.matches("[A-Za-z ]+");
        boolean validEmail = email != null && Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
        out.println("<html><body>");
        if (validName && validEmail) {
            out.println("<h2> Registration Successful!</h2>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Email: " + email + "</p>");
        } else {
            out.println("<h2> Invalid Input</h2>");
            if (!validName) out.println("<p>Error: Name must contain only letters and spaces.</p>");
            if (!validEmail) out.println("<p>Error: Email is not in a valid format.</p>");
            out.println("<a href='form.html'>Go Back</a>");
        }

        out.println("</body></html>");
    }
}
