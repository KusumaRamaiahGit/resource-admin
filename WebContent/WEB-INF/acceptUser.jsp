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
	<div id="block" align="center">
		<h1>Resource admin</h1>
		<form name="userAcceptForm" action="UserAcceptController" method="post">
			<table border="2" >
				<tr>
				    <td id="#tableCell"  style="width: 10px;  height:15px; top: 50px"></td>
					<td id="#tableCell"  style="width: 150px; height:15px; top: 50px";>Логин</td>
					<td id="#tableCell"  style="width: 150px; height:15px; top: 50px">Пароль</td>
					<td id="#tableCell"  style="width: 150px; height:15px; top: 50px">Email</td>	
					<td id="#tableCell"  style="width: 100px; height:15px; top: 50px">Рейтинг</td>
					<td id="#tableCell"  style="width: 100px; height:15px; top: 50px">Офис</td>
				</tr>
				<%for (Client c :ClientDAO.getAllClients()) {%>
				<tr>
				<td><%out.println("<input type=\"checkbox\" name=\"client_id\" value=\""+c.getClient_id()+"\" ></input><br>");%></td>
					<td><%= c.getLogin()%></td>
					<td><%= c.getPassword() %></td>
					<td><%= c.getContact() %></td>
					<td><%= c.getRating() %></td>
					<td><%= c.getLocation() %></td>
					<%} %>
				</tr>
			</table>
			<div>
			<input type="submit" name="accept" value="Зарегистрировать" />
          </div>
       </form>
	</div>
	
</body>
</html>