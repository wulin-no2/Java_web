package com.example.wk09_lab;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SimpleServlet", value = "/simple-servlet")
public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // something default:
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Response here!</h1>");

        // get the identifier of the book to display;
        String bookId = request.getParameter("bookId");
        if (bookId!=null) writer.println("The identifier entered for the book is: " + bookId);

        writer.close();

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
