package com.jwt.hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Success extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter writer = response.getWriter();

		writer.println("<html>" + "<body bgcolor='cyan'>" + "<center>"
				+ "Details Added Successfully" + "</center>" + "</body>"
				+ "</html>");
		writer.println(request.getParameter("userName")+"added successfully");
	}

}
