package com.book;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.annotation.WebServlet;

@WebServlet("/myServlet")
public class BookDetailServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		// set content-type header before accessing the Writer 

			
	}
}
