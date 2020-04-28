<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
				<li class="active"><a href="index.jsp">Home</a></li>
				<li><a href="studentLogin.jsp">Login</a></li>

			</ul>
		</div>
	</div>
	<div id="contents">
		<div id="adbox">
			<div class="clearfix">

				<div align="center">
					<br />
					<h3>Student Registration</h3>
					<br />
					<%
						DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
						Date dateobj = new Date();
						System.out.println(df.format(dateobj));
					%>

					<form action="StudentRegistration" method="post">
						<table border="1" cellpadding="5" cellspacing="3">
							<tr>
								<td>Student Name</td>
								<td><input type="text" name="fname" required></td>
								<td><input type="text" name="mname" required></td>
								<td><input type="text" name="lname" required></td>
							</tr>

							<tr>
								<td>Date Of Birth</td>
								<td><input type="text" name="dob" id="dob" required></td>
								<td>Gender</td>
								<td>&nbsp;Male&nbsp;<input type="radio" name="gender"
									id="gender" value="male"></input> &nbsp;Female&nbsp;<input
									type="radio" name="gender" id="gender" value="female"></input></td>
							</tr>

							<tr>
								<td>Class</td>
								<td><select style="width: 145px; height: 30px" required>
										<option value="F.Y. year">--Select--</option>
										<option value="F.Y. year">F.Y. Year</option>
										<option value="S.Y. year">S.Y. Year</option>
										<option value="T.Y. year">T.Y. Year</option>
										<option value="B.Y. year">B.Y. Year</option>
								</select></td>
								<td>Reg. Date</td>
								<td><input type="text" name="reg_date" readonly="readonly"
									value="<%=dateobj%>" required></td>

							</tr>
							<tr>
								<td>Email Id</td>
								<td><input type="text" name="email" id="email" required></td>
								<td>Mobile No</td>
								<td><input type="text" name="mobile" id="mobile" required></td>
							</tr>



							<tr>
								<td>Student Number</td>
								<td><input type="text" name="stud_no" id="stud_no" required></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><input type="password" name="pwd" id="pwd" required></td>
								<td>Confirm Password</td>
								<td><input type="password" name="pwd" id="c_pwd" required></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Registration"></td>
								<td><input type="reset" value="Reset"></td>
							</tr>



						</table>

					</form>
					<br />
				</div>

				<br /> <br />

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