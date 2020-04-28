package com.souce;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DbConnection;


@WebServlet("/StudentRegistration")
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	Connection con = null;
	PreparedStatement ps;
	ResultSet rs;


	public void init(ServletConfig config) throws ServletException 
	{
		try
		{
			
			con = DbConnection.getConnection();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String lname=request.getParameter("lname");
		String reg_date=request.getParameter("reg_date");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String year=request.getParameter("year");
		String email=request.getParameter("email");
		String mobno=request.getParameter("mobno");
		String stud_no=request.getParameter("stud_no");
		String pwd=request.getParameter("pwd");
		
		/*
		 * Get Current Date
		 * 
		 * DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));*/
		
		try
		{
			PreparedStatement ps1 = con.prepareStatement("select student_no from student where student_no='"+stud_no+"'");
			ResultSet rs =ps1.executeQuery();
			if(!rs.next())
			{
				ps=con.prepareStatement("INSERT INTO `compiler`.`student` (`stud_id`, `fname`, `mname`, `lname`, `dob`, `gender`,`year`, `mobno`, `email`, `student_no`, `pwd`, `reg_date`, `status`) VALUES (NULL, '"+fname+"', '"+mname+"','"+lname+"','"+dob+"','"+gender+"','"+year+"','"+mobno+"','"+email+"', '"+stud_no+"', '"+pwd+"', '"+reg_date+"', 'False')");
				int result=ps.executeUpdate();
				if(result>0)
				{
					System.out.println("Registration Successfully");
					response.sendRedirect("studentLogin.jsp?reg=done");
				}
				else 
				{
					System.out.println("Registration Fail");
					response.sendRedirect("studentRegistration.jsp?reg=fail");
				}
			}
			else{
				System.out.println("Allready Registration Done");
				response.sendRedirect("studentRegistration.jsp?already=done");
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Occure:- "+e);
		}
		
	
		
	}


}
