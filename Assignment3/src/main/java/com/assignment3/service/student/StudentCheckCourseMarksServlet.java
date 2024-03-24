package com.assignment3.service.student;

import com.assignment3.Entity.Assessment;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentCheckCourseMarksServlet", value = "/StudentCheckCourseMarksServlet")
public class StudentCheckCourseMarksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("====================post method is running====================");
        PrintWriter out = response.getWriter();
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("studentCourseAssessment.jsp");
        requestDispatcher.include(request, response);
//        response.sendRedirect("studentCourseAssessment.jsp");
//        out.append("Served at: ").append(request.getContextPath());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        System.out.println("====================post method is running====================");
        PrintWriter out = response.getWriter();

    }
}