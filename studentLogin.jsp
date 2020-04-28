<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Java Compiler</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<div id="header">
		<div class="clearfix">
			<div class="logo">
				<h2>Java Compiler</h2>
			</div>
			<ul class="navigation">
				<li><a href="index.jsp">Home</a></li>
				<li class="active"><a href="studentLogin.jsp">Login</a></li>

			</ul>
		</div>
	</div>
	<div id="contents">
		<div id="adbox">
			<div class="clearfix">

				<div align="center">
					<br /> <br /> <br /> <br />
					<h3>User Login</h3>
					<br />
					<form action="StudentLogin" method="post">
						<table border="1" cellpadding="5" cellspacing="3">
							<tr>
								<td>Username</td>
								<td><input type="text" name="username" id="username" required></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><input type="password" name="pwd" id="pwd" required></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Login"></td>
							</tr>



						</table>

					</form>
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