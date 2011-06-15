<%@page import="model.Client"%>
<%@page import="utils.ClientDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Resource admin</title>

</head>
<body>
	<div id="block">
		<h1>Resource admin</h1>		
		<div class="menu" style="text-align: right; margin-top: 50px">
			<h2 align="center" >Авторизация пользователей</h2>
			<br>
			<a href='AdminPanel'>Админ. панель</a> | <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
			| <a href="LogOutController">Выход</a>
			
			<br><br>
			<div style="text-align: left">
				<form name="userAcceptForm" action="UserAcceptController" method="post">
				<table border="2"  style="color: #FFF;">
					<tr>
					    <th style="width: 10px;  height:15px; top: 50px"></th>
						<th style="width: 150px; height:15px; top: 50px">Логин</th>
						<th style="width: 150px; height:15px; top: 50px">Пароль</th>
						<th style="width: 150px; height:15px; top: 50px">Email</th>	
						<th style="width: 100px; height:15px; top: 50px">Рейтинг</th>
						
						<!--<th style="width: 100px; height:15px; top: 50px">Офис</th>-->
					</tr>
					<%for (Client c :ClientDAO.getUnauthorizedClients()) {%>
					<tr>
						<td><%out.println("<input type=\"checkbox\" name=\"client_id\" value=\""+c.getClient_id()+"\" ></input><br>");%></td>
						<td><%= c.getLogin()%></td>
						<td><%= c.getPassword() %></td>
						<td><%= c.getContact() %></td>
						<td><%= c.getRating() %></td>
						<!--  <td><%= c.getLocation() %></td>-->
						<%} %>
					</tr>
				</table>
				 <div>
					<input type="submit" name="accept" value="Авторизировать" />
	         	 </div>
	       		</form>
			</div>
			</div>
		</div>	
</body>
</html>