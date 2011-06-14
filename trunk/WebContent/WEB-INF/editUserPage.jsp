<%@page import="model.Client"%>
<%@page import="utils.ClientDAO"%>
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
	<% Client editingClient=ClientDAO.getClientById(Long.parseLong(request.getParameter("client_id"))); %>
	<div id="block">
		<h1>Resource admin</h1>
		<form action="UserEditController" method="post">
			<input type="hidden" name="client_id" value="<%=editingClient.getClient_id() %>" />
			Логин:<input type="text" name="client_login" value="<%=editingClient.getLogin() %>" />
			Пароль:<input type="text" name="client_password" value="<%=editingClient.getPassword() %>" />
			Email:<input type="text" name="client_contact" value="<%=editingClient.getContact() %>" />
			Рейтинг:<select name="client_rating">
					<%
						for (model.Client.RATINGS r : Client.RATINGS.values()) {
					%>
						<option value="<%=r%>" <%if (editingClient.getRating().equals(r)) out.print("selected=\"true\""); %>><%=r%></option>
					<%
						}
					%>			
				</select>
			Авторизирован:<input type="checkbox" name="authorized"  />
			<input type="submit" name="edit" value="Редактировать"/>
		</form>
	</div>
</body>
</html>