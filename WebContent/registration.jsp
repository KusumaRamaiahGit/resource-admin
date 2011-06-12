<%@page import="model.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">
	$().ready(
					function() {
						// validate signup form on keyup and submit
						$("#signupForm")
								.validate(
										{
											rules : {
												login : {
													required : true,
													minlength : 2,
												},
												pass1 : {
													required : true,
													minlength : 4,
													maxlength : 10
												},
												pass2 : {
													required : true,
													minlength : 4,
													maxlength : 10,
													equalTo : "#password"
												},
												email : {
													required : true,
													email : true
												}
											},
											messages : {
												login : {
													required : "Введите логин!",
													minlength : "Логин должен содержать как минимум 2 символа!"
												},
												pass1 : {
													required : "Введите пароль!",
													minlength : "Пароль должен содержать как минимум 4 символа!"
												},
												pass2 : {
													required : "Подтвердите пароль!",
													minlength : "Пароль должен содержать как минимум 4 символа!",
													equalTo : "Пароли должны совпадать!"
												},
												email : "Введите действующий email"
											}
										});
					});
</script>

</head>
<body>
	<div id="block">
		<h1>Resource admin</h1>
		<div id="reg">
		<div class="head">Регистрация нового пользователя</div>
			<form  id="signupForm" method="post"
				action="RegistrationController">			
					<table style="margin: 5px" border=2>
						<tr>
							<td id="tableCell"><label for="login">login</label></td>
							<td id="tableCell"><input id="login" name="login" style="padding-top: 5px;"
								value="<%if (request.getParameter("login") != null)
				out.print(request.getParameter("login"));%>" />
							</td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" align="center"
								name="checkLogin" value="Check Login" /> <%
 	if (request.getAttribute("uniqueLogin") != null) {
 		if (request.getAttribute("uniqueLogin").toString() == "true")
 			out.print("login OK");
 		else
 			out.print("login WRONG");
 	}
 %>
							</td>
						</tr>
						<tr>
							<td><label for="pass1">Password</label></td>
							<td id="tableCell"><input id="password" name="pass1" type="password" /></td>
						</tr>
						<tr>
							<td><label for="pass2">Confirm password</label></td>
							<td id="tableCell"><input id="pass2" name="pass2" type="password" /></td>
						</tr>
						<tr>
							<td><label for="email">Email</label></td>
							<td id="tableCell"><input id="email" name="email"
								value="<%if (request.getParameter("email") != null)
				out.print(request.getParameter("email"));%>" />
							</td>
						</tr>
						<tr>
							<td>Rating:</td>
							<td><select name="rating">
									<%
										for (model.Client.RATINGS r : Client.RATINGS.values()) {
									%>
									<option value="<%=r%>"><%=r%></option>
									<%
										}
									%>
							</select>
							</td>
						</tr>
						<tr>
							<td>Location:</td>
							<td><select name="location">
									<%
										for (model.Client.LOCATIONS loc : Client.LOCATIONS.values()) {
									%>
									<option value="<%=loc%>"><%=loc%></option>
									<%
										}
									%>
							</select>
							</td>
						</tr>
						<tr>
							<td colspan=2>								
									<input type="submit" align="center" name="register"
										value="register" />
							</td>
						</tr>
					</table>				
			</form>

		</div>
	</div>
</body>
</html>