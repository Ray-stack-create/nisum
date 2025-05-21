package com.nisum;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
@WebFilter("/*")
public class Servelet14 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURL = httpRequest.getRequestURL().toString();
        System.out.println("Incoming request: " + requestURL);
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}
