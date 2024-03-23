<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="/Assignment3_war_exploded/LoginServlet" method="post">
    Username:<input type="text" name="userName"/>
    <br/><br/>
    Password:<input type="password" name="password"/>
    <br/><br/>
    <input type="submit" value="login"/>
</form>
</body>
</html>