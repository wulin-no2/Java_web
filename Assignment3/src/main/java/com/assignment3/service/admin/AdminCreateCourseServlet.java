package com.assignment3.service.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "AdminCreateCourseServlet", value = "/AdminCreateCourseServlet")
public class AdminCreateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService adminService = new AdminService();
        response.setContentType("text/html");
        System.out.println("====================post method is running====================");
        PrintWriter out = response.getWriter();

        //get parameters from request object.
        String courseName =
                request.getParameter("courseName").trim();
        String semester =
                request.getParameter("semester").trim();
        //check for null and empty values.
        if(courseName == null
                || semester == null || courseName.equals("")
                || semester.equals("")){
            out.print("Please enter course id, course name " +
                    "and semester. <br/><br/>");
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("adminDashboard.jsp");
            requestDispatcher.include(request, response);
        }//Check for valid username and password;
        else{
            adminService.addCourse(courseName, Integer.parseInt(semester));
            out.println("<html><body>");

            out.println("<h1>Successfully creat the course: </h1>");
            out.println("<h2> semester: " + semester + "</h2>");
            out.println("<h2> course name: " + courseName + "</h2>");
            out.println("<a href=\"adminDashboard.jsp\">Back to Dashboard</a>");

            out.println("</body></html>");
        }
    }
}

