package com.assignment3.service.lecturer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/LecturerWelcomeServlet")
public class LecturerWelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<h1>You are logged in " +
                "successfully.</h1>");
        out.println("<h2> Now following is the lecturer dashboard..Please wait for construction.");
        out.println("</html></body>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}