<%@page import="com.util.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Online Compiler</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<%
	String stud_id = session.getAttribute("stud_no").toString();
%>

<body>
	<div id="header">
		<div class="clearfix">
			<div class="logo">
				<h2>Online Compiler</h2>
			</div>
			<ul class="navigation">
				<li class="active"><a href="studentHome.jsp">Home</a></li>
				<li><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>
	</div>
	<div id="contents">
		<div id="adbox">
			<div class="clearfix">

				<div align="center">
				<table border="0" cellpadding="10">
				<tr><td>
					<br /> <br /> <br /> <br /> <br />
					<%
						if (request.getParameter("as_id") != null) {
							String ass_id = request.getParameter("as_id");
							Connection con = DbConnection.getConnection();
							Statement stm = con.createStatement();
							ResultSet rs = stm
									.executeQuery("select * from solve_assignment where ass_id='"
											+ ass_id + "'");
							String asignment = "", program = "", program_name = "";

							if (rs.next()) {
								asignment = rs.getString("assignment");
								program = rs.getString("program");
								program_name = rs.getString("program_name");
							}
					%>

					<!-- Edit Solve Program -->
					<h1>Edit Assignment</h1>
					<br />

					<form action="CompilerServlet" method="post">

						<table border="1" cellpadding="5" cellspacing="3">

							<tr align="center">
								<td>Assignment: <%=asignment%><input type="hidden"
									name="as_type" value="old_as"><input type="hidden"
									name="filename" value="<%=stud_id%>_"></td>
							</tr>
							<tr align="center">
								<td>Enter Program Name with Extension:&nbsp;&nbsp;<input
									type="text" value="<%=program_name%>" name="program_name"
									placeholder="ProgramName.java" required></td>
							</tr>
							<tr>
								<td><textarea rows="20" cols="50" name="program"><%=program%></textarea></td>
							</tr>
							<tr align="center">

								<td><input type="submit" value="Compile AND Run"></td>
							</tr>



						</table>

					</form>
					<!-- Add New Program -->
					<%
						}
						if (request.getParameter("nas_id") != null) 
						{
							String nas_id = request.getParameter("nas_id");
							Connection con1 = DbConnection.getConnection();
							Statement stm1 = con1.createStatement();
							System.out.println("new assignment ID "+nas_id);
							ResultSet rs1 = stm1.executeQuery("select * from assignment where assignment_id='"+ nas_id + "'");

							if (rs1.next()) {

								System.out.print("doen");
					%>
					<h1 align="center">New Assignment</h1>
					<br />
					<form action="CompilerServlet" method="post">
						<table border="1" cellpadding="5" cellspacing="3">

							<tr align="center">
								<td>Assignment:-<%=rs1.getString("assignment")%>
								<input type="hidden" name="assignment" value="<%=rs1.getString("assignment")%>">
								<input type="hidden" name="as_type" value="new_as">
								<input type="hidden" name="nas_id" value="<%=nas_id%>">
								<input type="hidden" name="filename" value="<%=stud_id%>_"></td>
							</tr>
							<tr align="center">
								<td>Enter Program Name with Extension:&nbsp;&nbsp;<input
									type="text" name="program_name" placeholder="ProgramName.java"
									required></td>
							</tr>
							<tr>
								<td><textarea rows="20" cols="50" name="program"
										placeholder="Write a Program here"></textarea></td>
							</tr>
							<tr align="center">

								<td><input type="submit" value="Compile AND Run"></td>
							</tr>



						</table>

					</form>
					<%
						}

						}
					%>
					<br /> <br /> <br />
					</td><td>
					<h1 align="center">Output Screen</h1>
					<br />
					<textarea rows="10" cols="40" name="out"></textarea>
					
					</td></tr>
					</table>
				</div>

				<br /> <br /> <br /> <br />

			</div>
			
		</div>


	</div>
	<div id="footer">

		<div id="footnote">
			<div class="clearfix">
				<div class="connect"></div>
				<p>© Copyright 2023 Manes Winchester. All Rights Reserved.</p>
			</div>
		</div>
	</div>
</body>
</html>