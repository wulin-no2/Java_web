<%--
  Created by IntelliJ IDEA.
  User: wulin
  Date: 2024/3/23
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.assignment3.Entity.Assessment" %>
<%@ page import="com.assignment3.service.student.StudentService" %>
<%@ page import="com.assignment3.service.student.StudentCheckCourseMarksServlet" %>
<html>
<head>
    <title>Course Assessments</title>
</head>
<body>
<h2>Course Assessments</h2>

<%
    //get parameters from request object.
    String courseIdString = request.getParameter("courseId");
    Long courseId1 = Long.parseLong(courseIdString);
//    Long courseId = Long.parseLong(request.getParameter("courseId"));
    HttpSession requestSession = request.getSession();
    String username = (String)requestSession.getAttribute("userName");

    // get assessments:
    StudentService studentService = new StudentService();
    List<Assessment> assessments = studentService.getAssessmentByCourseIdAndUserName(username, courseId1);
//    SELECT c.course_name,a.assessment_type, u.username, a.marks
%>

<table border="1">
    <thead>
    <tr>
        <th>Course Name</th>
        <th>Assessment Type</th>
        <th>Student Name</th>
        <th>Marks</th>
    </tr>
    </thead>
    <tbody>
    <% for(Assessment assessment : assessments) { %>
    <tr>
        <td><%= assessment.getCourse().getCourseName() %></td>
        <td><%= assessment.getAssessmentType() %></td>
        <td><%= assessment.getUser().getUsername() %></td>
        <td><%= assessment.getMarks()%></td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>
<a href="studentDashboard.jsp">Back to StudentDashboard</a>

</body>
</html>
