package com.assignment3.service.UserService;

import com.assignment3.service.UserService.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserRegisterCourseServlet", value = "/UserRegisterCourseServlet")
public class UserRegisterCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        System.out.println("====================post method is running====================");
        PrintWriter out = response.getWriter();

        //get parameters from request object.
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("userName");

        //add user course;
        UserService userService = new UserService();
        userService.registerCourse(username,courseId);
            out.println("<html><body>");

            out.println("<h1>Successfully register the course: </h1>");
            out.println("<h2> course information: " + userService.getCourseByCourseId(Long.valueOf(courseId)).toString() + "</h2>");
            if (username.equals("student")){
                out.println("<a href=\"studentDashboard.jsp\">Back to Dashboard</a>");
            }
            if (username.equals("teacher")){
                out.println("<a href=\"teacherDashboard.jsp\">Back to Dashboard</a>");
            }
            out.println("</body></html>");
    }
}
