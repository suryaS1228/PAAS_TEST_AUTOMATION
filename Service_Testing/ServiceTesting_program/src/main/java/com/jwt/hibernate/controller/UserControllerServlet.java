package com.jwt.hibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.solartis.test.servicetesting.ServiceTestingProgram.TestEngine;

public class UserControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		
		String Project = request.getParameter("Project");
		String Api = request.getParameter("Api");
		String Env = request.getParameter("Env");
		String OutputChioce = request.getParameter("OutputChioce");
		String UserName = request.getParameter("UserName");
		String JDBC_DRIVER = request.getParameter("JDBC_DRIVER");
		String DB_URL = request.getParameter("DB_URL");
		String USER = request.getParameter("USER");
		String password = request.getParameter("password");
		String Priority = request.getParameter("Priority");

		System.out.println(Project+Api+Env+OutputChioce+UserName+JDBC_DRIVER+DB_URL+USER+password+Priority);

		HttpSession session = request.getSession(true);
		try 
		{
			String args[]=new String[10];
			args[0]=Project;
			args[1]=Api;
			args[2]=Env;
			args[3]=OutputChioce;
			args[4]=UserName;
			args[5]=JDBC_DRIVER;
			args[6]=DB_URL;
			args[7]=USER;
			args[8]=password;
			args[9]=Priority;
			TestEngine t=new TestEngine();
			t.ServiceTest(args);			
			
			response.sendRedirect("Success");
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}
}
