package greet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GreetingServlet
 */
//@WebServlet("/GreetingServlet")


public class GreetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		// write the data of the response 
		out.println("<html><head><title>Hello</title></head>");
		// web form generation
		out.println("<body bgcolor=\"#ffffff\"><h2>Hi! What's your name?</h2>" +
				"<form method=\"get\">" +
				"<input type=\"text\" name=\"username\" size=\"25\">" +
				"<p></p>" +
				"<input type=\"submit\" value=\"Submit\">" + "<input type=\"reset\" value=\"Reset\">" + "</form>");
		
				String username = request.getParameter("username");
				request.setAttribute("age", 30);
				// dispatch call to another web resource
				if ( username != null && username.length() > 0 ) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/second");
				       if (dispatcher != null)
				    	   dispatcher.forward(request, response); // also try .forward(...)
				} 
				out.println("</body></html>"); 
				out.close();
		}
}
