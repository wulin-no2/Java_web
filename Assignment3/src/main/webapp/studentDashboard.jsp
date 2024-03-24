<%@ page import="com.assignment3.Entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.service.student.StudentService" %><%--
  Created by IntelliJ IDEA.
  User: wulin
  Date: 2024/3/23
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
</head>
<body>
<h2>Student Dashboard</h2>

<%-- userName is stored in session after login --%>
<% String userName = (String) session.getAttribute("userName"); %>
<% StudentService studentService = new StudentService(); %>
<% List<Course> enrolledCourses = studentService.getEnrolledCoursesByUserName(userName); %>
<% List<Course> availableCourses = studentService.getAvailableCoursesByUserName(userName); %>

<% if(enrolledCourses.isEmpty()){ %>
<p>You are not enrolled in any courses.</p>
<% } else { %>
<h3>Enrolled Courses</h3>
<ul>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Semester</th>
            <th>Operations</th>
        </tr>
        <% for(Course course : enrolledCourses){ %>
        <tr>
            <td><%= course.getCourseId() %></td>
            <td><%= course.getCourseName() %></td>
            <td>Semester <%= course.getSemester() %></td>
            <td>
                <form action="StudentCheckCourseMarksServlet" method="get">
                    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                    <input type="submit" value="Check Results">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
<% } %>

<h3>Available Courses</h3>
<p>Select a course to register:</p>
<table border="1">
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Semester</th>
        <th>Operation</th>
    </tr>
    <% for(Course course : availableCourses){ %>
    <tr>
        <td><%= course.getCourseId() %></td>
        <td><%= course.getCourseName() %></td>
        <td>Semester <%= course.getSemester() %></td>
        <td>
            <form action="StudentRegisterCourseServlet" method="post">
                <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                <input type="submit" value="Register">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<br>

<a href="/Assignment3_war_exploded/logout">log out</a>
</body>
</html>

