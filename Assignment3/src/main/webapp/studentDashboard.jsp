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
<% List<Course> enrolledCourses = StudentService.getEnrolledCourses(Long.valueOf(userName)); %>
<% List<Course> availableCourses = StudentService.getAvailableCourses(userName); %>

<% if(enrolledCourses.isEmpty()){ %>
<p>You are not enrolled in any courses.</p>
<% } else { %>
<h3>Enrolled Courses</h3>
<ul>
    <% for(Course course : enrolledCourses){ %>
    <li><%= course.getCourseId() %> - <%= course.getCourseName() %> - Semester <%= course.getSemester() %></li>
    <% } %>
</ul>
<% } %>

<h3>Available Courses</h3>
<p>Select a course to register:</p>
<form action="StudentRegisterCourseServlet" method="post">
    <select name="courseId" required>
        <% for(Course course : availableCourses){ %>
        <option value="<%= course.getCourseId() %>"><%= course.getCourseName() %> - Semester <%= course.getSemester() %></option>
        <% } %>
    </select>
    <input type="submit" value="Register">
</form>
</body>
</html>

