package com.souce;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewProgramAdd
 */
@WebServlet("/ViewProgramAdd")
public class ViewProgramAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException 
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Do Get");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Do Post");
//INSERT INTO `compiler`.`viewprogram` (`vp_id`, `program`, `output`, `stud_no`, `marks`, `viewby`) VALUES (NULL, 'a', NULL, NULL, NULL, NULL);
	}

}
