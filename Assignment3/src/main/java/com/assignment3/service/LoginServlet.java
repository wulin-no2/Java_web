package com.assignment3.service;

import com.assignment3.entity.User;
import com.assignment3.service.userService.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// set session attribute to store username and password
		HttpSession session = request.getSession();

		System.out.println("====================post method is running====================");
		response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
 
    	//get parameters from request object.
    	String userName = 
    		request.getParameter("userName").trim();
    	String password = 
    		request.getParameter("password").trim();
		// check user type;
		String role = null;
		if (!userName.equals("") && !userName.equals("admin")){
			UserService userService = new UserService();
			User userByUserName = userService.getUserByUserName(userName);
			role = userByUserName.getRole();
			session.setAttribute("role", role);
		}
		//check for null and empty values.
    	if(userName == null || userName.equals("") 
    			|| password == null || password.equals("")){
    		out.print("Please enter both username " +
    				"and password. <br/><br/>");
    		RequestDispatcher requestDispatcher =
    			request.getRequestDispatcher("/login.html");
    		requestDispatcher.include(request, response);
    	}//Check for valid username and password.
		else if (userName.equals("admin") && password.equals("admin")) {
			session.setAttribute("userName",userName);
			System.out.println(session.getAttribute("userName"));
			response.sendRedirect("adminDashboard.jsp");
		}
		else if(role!=null && role.equals("student")){
			session.setAttribute("userName",userName);
			System.out.println(session.getAttribute("userName"));
//			This is student result:
    		response.sendRedirect("studentDashboard.jsp");
    	}
		else if (role!=null && role.equals("teacher")) {
			session.setAttribute("userName",userName);
			System.out.println(session.getAttribute("userName"));
//			This is lecturer system:
			response.sendRedirect("teacherDashboard.jsp");
		} else{
    		out.print("Wrong username or password. <br/><br/>");
    		RequestDispatcher requestDispatcher = 
    			request.getRequestDispatcher("/login.html");
    		requestDispatcher.include(request, response);
    	}
	}
}
