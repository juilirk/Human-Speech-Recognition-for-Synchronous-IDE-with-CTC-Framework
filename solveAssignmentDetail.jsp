<%@page import="com.souce.GlobalFunction"%>
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
<body>
	<div id="header">
		<div class="clearfix">
			<div class="logo">
				<h2>Online Compiler</h2>
			</div>
			<ul class="navigation">
				<li class="active"><a href="adminHome.jsp">Home</a></li>
				<li><a href="addAssignment.jsp">Add Assignment</a></li>
				<li><a>view</a>
					<div>
						<a href="adminAssignmentList.jsp">Assignment List</a> <a
							href="adminViewSolveAssi.jsp">Solve Assignment</a>
					</div></li>
				<li><a href="adminForum.jsp">Forum</a></li>
				<li><a href="studRecord.jsp">Record</a></li>
				<li><a href="LogoutServlet">Logout</a></li>
			</ul>
		</div>
	</div>
	<div id="contents">
		<div id="adbox">

			<div class="clearfix">
				<div align="right">

					Welcome: &nbsp;<font style="color: green"><%=session.getAttribute("uname")%>&nbsp;&nbsp;&nbsp;&nbsp;</font>
				</div>

				<div align="center">
					<br />
					<br />
					<%
						String stud_no = request.getParameter("stud_no");
						String fname = "", lname = "";
						GlobalFunction gf = new GlobalFunction();
						fname = gf.getFname(stud_no);
						lname = gf.getLname(stud_no);
					%>
					<h3>
						Solve Assignment List <font style="color: red"><%=fname%>&nbsp;&nbsp;<%=lname%></font>
					</h3>

					<br />
					<table border="1" cellpadding="5">
						<tr>
							<th>Sr. No.</th>
							<th>Assignment</th>
							<th>Add Date</th>
							<th>Add by</th>
							<th>Solve Date</th>
							<th>Program Output</th>

						</tr>
						<%
							Connection con = DbConnection.getConnection();
							Statement stm = con.createStatement();
							ResultSet rs = stm
									.executeQuery("select b.assignment,a.uplaod_date,b.upload_by,b.solve_date,b.program,b.output,b.stud_as_id from assignment a,solve_assignment b where b.stud_id='"
											+ stud_no + "' AND a.assignment_id=b.ass_id AND program_sts!='done'");
							int no = 0;
							while (rs.next()) {
								no++;
						%>
						<tr align="center">
							<td><%=no%></td>
							<td><%=rs.getString(1)%></td>
							<td><%=rs.getString(2)%></td>
							<td><%=rs.getString(3)%></td>
							<td><%=rs.getString(4)%></td>
							
							<td>
								<form action="viewInDetails.jsp" method="post">
									
									<textarea name="assignment" style="display: none;"><%=rs.getString(1)%></textarea>
									<textarea name="program" style="display: none;"><%=rs.getString(5)%></textarea>
									<textarea name="output" style="display: none;"><%=rs.getString(6)%></textarea>
									<textarea name="stud_as_id" style="display: none;"><%=rs.getString(7)%></textarea>
									<input type="submit" value="In Details">
								</form>
							</td>

						</tr>

						<%
							}
						%>

					</table>
					<br /> <br /> <br />
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