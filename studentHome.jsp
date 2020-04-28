<%@page import="java.sql.PreparedStatement"%>
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
	String stud_id = session.getAttribute("fname").toString();

	System.out.println("fname : " + stud_id);
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
				<div align="right">
					Welcome: <font style="color: green"><%=session.getAttribute("fname")%>&nbsp;&nbsp;</font>
				</div>
				<div align="center">
					<table border="0" cellpadding="10">
						<tr>
							<td><br /> <br />
								<%
									//if (request.getParameter("as_id") != null) 
									{
										String ass_id = request.getParameter("as_id");
										Connection con = DbConnection.getConnection();
										Statement stm = con.createStatement();
										ResultSet rs = stm.executeQuery("select * from speechdata where Name='" + stud_id + "'");
										String asignment = "", program = "", program_name = "", output = "";

										if (rs.next()) {
											program = rs.getString("speech");
											output = rs.getString("output");

										}
								%> <!-- Edit Solve Program -->
								<h1>Edit Program</h1> <br />

								<form action="CompilerServlet" method="post">

									<table border="1" cellpadding="5" cellspacing="3">

										<tr align="center">
											<td>Enter Program Name :&nbsp;&nbsp;<input type="text"
												value="<%=program_name%>" name="program_name"
												placeholder="ProgramName.java" required></td>
										</tr>
										<tr>
											<td><textarea rows="20" cols="50" name="program"><%=program%></textarea></td>
										</tr>
										<tr align="center">

											<td><input type="submit" value="Compile AND Run"></td>
										</tr>



									</table>

								</form> <%
 	}
 	if (request.getParameter("nas_id") != null) {
 		String nas_id = request.getParameter("nas_id");
 		Connection con1 = DbConnection.getConnection();
 		Statement stm1 = con1.createStatement();
 		System.out.println("new assignment ID " + nas_id);
 		ResultSet rs1 = stm1.executeQuery("select * from speech where Name ='" + stud_id + "'");

 		if (rs1.next()) {
 %>
								<h1 align="center">New Assignment</h1> <br />
								<form action="CompilerServlet" method="post">
									<table border="1" cellpadding="5" cellspacing="3">

										<tr align="center">
											<td>Assignment:-<%=rs1.getString("assignment")%> <input
												type="hidden" name="assignment"
												value="<%=rs1.getString("assignment")%>"> <input
												type="hidden" name="as_type" value="new_as"> <input
												type="hidden" name="nas_id" value="<%=nas_id%>"> <input
												type="hidden" name="filename" value="<%=stud_id%>_">
											</td>
										</tr>
										<tr align="center">
											<td>Enter Only Program :&nbsp;&nbsp;<input type="text"
												name="program_name" placeholder="ProgramName.java" required>
											</td>
										</tr>
										<tr>
											<td><textarea rows="20" cols="50" name="program"
													placeholder="Write a Program here"></textarea></td>
										</tr>
										<tr align="center">

											<td><input type="submit" value="Compile AND Run"></td>
										</tr>



									</table>

								</form> <%
 	}

 	}
 %> <br /> <br /> <br /></td>

							<%
								if (request.getParameter("as_id") != null) {
									String aas_id = request.getParameter("as_id");

									Connection conn = DbConnection.getConnection();
									PreparedStatement ps_1 = conn
											.prepareStatement("SELECT * FROM `speechdata` where Name= '" + stud_id + "'");
									ResultSet rs_1 = ps_1.executeQuery();
									String c_op = "", r_op = "";
									if (rs_1.next()) {

										c_op = rs_1.getString("output");

										System.out.println("cm 1 opt " + c_op);
										System.out.println("rt 2 opt " + r_op);

										if (c_op.equals("")) {
							%>
							<td>
								<h1 align="center">Output Screen</h1> <br /> <textarea
									rows="10" cols="40" name="out"><%=rs_1.getString("output")%><%-- <%=rs_1.getString("ass_status")%> --%></textarea>
							</td>
							<%
								} else {
							%>
							<td>
								<h1 align="center">Output Screen</h1> <br /> <textarea
									rows="10" cols="40" name="out"><%=rs_1.getString("output")%><%-- <%=rs_1.getString("ass_status")%> --%></textarea>
							</td>
							<%
								}
									}
								}
							%>
						</tr>
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