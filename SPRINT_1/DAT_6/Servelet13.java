package com.nisum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletContext;

@WebServlet("/feedback")
public class Servelet13 extends HttpServlet {

    public void init() {
        ServletContext context = getServletContext();
        if (context.getAttribute("feedbackList") == null) {
            context.setAttribute("feedbackList", new ArrayList<String>());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        ServletContext context = getServletContext();
        List<String> feedbackList = (List<String>) context.getAttribute("feedbackList");
        feedbackList.add("From " + name + ": " + message);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h3>Thank you for your feedback!</h3>");
        out.println("<h4>All Feedback Received:</h4><ul>");

        for (String feedback : feedbackList) {
            out.println("<li>" + feedback + "</li>");
        }

        out.println("</ul><a href='feedback.html'>Back to Form</a>");
        out.println("</body></html>");
    }
}
