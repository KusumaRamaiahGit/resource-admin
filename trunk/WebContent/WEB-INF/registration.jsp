<%@page import="model.Client"%>
<%@page import="model.Admin"%>
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
	function checkAndDisable() {		
		if (document.getElementById("signupForm").elements.login.value == "") {
			alert('Сначала нужно проверить логин на уникальность!');
			document.getElementById("signupForm").elements.register.disabled = true;
		}
	}
	function fenable() {
		document.getElementById("signupForm").elements.register.disabled = false;
	}

	$()
			.ready(
					function() {
						// validate signup form on keyup and submit
						$("#signupForm1")
								.validate(
										{
											rules : {
												login : {
													required : true,
													minlength : 2,
													maxlength : 100
												}
											},
											messages : {
												login : {
													required : "Введите логин!",
													minlength : "Логин должен содержать как минимум 2 символа!",
													maxlength : "Слишком длинный логин!"
												}
											}
										});
						$("#signupForm")
								.validate(
										{
											rules : {

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
													email : true,
													maxlength : 100
												}
											},
											messages : {

												pass1 : {
													required : "Введите пароль!",
													minlength : "Пароль должен содержать как минимум 4 символа!",
													maxlength : "Пароль не может превышать 10 символов!"
												},
												pass2 : {
													required : "Подтвердите пароль!",
													minlength : "Пароль должен содержать как минимум 4 символа!",
													maxlength : "Пароль не может превышать 10 символов!",
													equalTo : "Пароли должны совпадать!"
												},
												email : "Введите действующий email",
												maxlength : "Слишком длинный логин!"
											}
										});

					});
</script>

</head>
<body style="color:#cff">
<div id="block">
		<h1>Resource admin</h1> 
			<%@include file="../menu.jsp"%>
			<h2 align="center" style="color: #ccffff;">Регистрация нового
				пользователя</h2>
		<div style="text-align: left">	
			
			<form id="signupForm1" method="post" action="RegistrationController" name="loginForm">			
			<table border="1">
				<tr>
					<td id="#tableCell"><label for="login" >Логин:</label></td>
					<td id="#tableCell" colspan="2">
						<input id="login" name="login" style="padding-top: 5px;"
							value="<%if (request.getParameter("login") != null)
							out.print(request.getParameter("login"));%>" />						
					</td>
				</tr>
				<tr>
					<td id="#tableCell" colspan="2">
						<input type="submit" align="center" name="checkLogin" 
							value="Проверить логин на уникальность" onClick="enable()" />					
					</td>
					<td id="#tableCell">
						<%
						if (request.getAttribute("uniqueLogin") != null) {
							if (request.getAttribute("uniqueLogin").toString() == "true")
								out.print("выбранное имя пользователя СВОБОДНО");
							else
								out.print("выбранное имя пользователя ЗАНЯТО");
						}
						%>						
					</td>
				</tr>
			</table>
			</form>			
			
			<form id="signupForm" method="post" action="RegistrationController"
				name="paramForm">			
			<table border="2">
				<tr>
				
					<td id="#tableCell">
						<input id="login" name="login" type="hidden"							
							value="<%if (request.getParameter("login") != null)
								out.print(request.getParameter("login"));%>" />	
						<label for="pass1">Пароль:</label>
					</td>
					<td id="#tableCell">
						<input id="password" name="pass1" type="password" /> 
					</td>
				</tr>				
				<tr>
					<td id="#tableCell"><label for="pass2">Подтверждение пароля:</label></td>
					<td id="#tableCell">
						<input id="pass2" name="pass2" type="password" />
					</td>
				</tr>
				<tr>
					<td id="#tableCell"><label for="email">Email:</label></td>
					<td id="#tableCell">
						<input id="email" name="email"
					value="<%if (request.getParameter("email") != null)
				out.print(request.getParameter("email"));%>" />						
					</td>
				</tr>
				<tr>
					<td id="#tableCell"><label for="rating">Рейтинг:</label></td>
					<td id="#tableCell">
						<select name="rating">
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
				<% if (request.getSession().getAttribute("User") instanceof Admin)
					{%>
					<tr>
						<td id="#tableCell"><label for="admin">Администратор:</label></td>
						<td id="#tableCell">
							<input type="checkbox" name=admin value="false"/>
						</td>
					</tr>
					<%} %>
				
				<tr>
					<td id="#tableCell">Зарегистрироваться:</td>
					<td id="#tableCell"><input type="submit" align="center" name="register"
						value="зарегистрироваться" onClick="checkAndDisable()" />
					</td>
				</tr>				
			</table>
			</form>			
		</div>
	</div>
</div>
	
</body>
</html>