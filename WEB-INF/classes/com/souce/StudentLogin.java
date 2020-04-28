package com.souce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	Connection con = null;
	PreparedStatement ps;
	ResultSet rs;

	public void init(ServletConfig config) throws ServletException {
		try {

			con = DbConnection.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fname = "", lname = "",year="";
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();

		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement("select * from speechdata where Name='"+ username + "' AND Password='" + pwd + "'");
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				fname = rs.getString("name");
				session.setAttribute("fname", fname);
				System.out.println("Login Done");
				response.sendRedirect("studentHome.jsp?login=done");

			} else {
				System.out.println("Login fail");
				response.sendRedirect("studentLogin.jsp?login=fail");
			}
		} catch (SQLException e) {
			System.out.println("Exception Occure :- " + e);
		}

	}

}
