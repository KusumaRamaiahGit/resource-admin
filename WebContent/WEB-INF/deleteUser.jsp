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
<body style="color: #ffffff;">
	<%
		List<Client> clients = ClientDAO.getAllClients();
	%>
	<div id="block">
		<h1>Resource admin</h1>
		<%@include file="../menu.jsp"%>
		<h2 align="center" style="color: #ccffff;">Удаление пользователей</h2>
		<div style="text-align: left" >
			<form name="userDeleteForm" action="UserDeleteController"
				method="post">
				<%
					for (Client c : clients) {
						if (!c.equals((Client) request.getSession()
								.getAttribute("User")))
							out.println("<label><input type=\"checkbox\" name=\"client_id\" value=\""
									+ c.getClient_id()
									+ "\" ><label>"
									+ c.toString()
									+ "</input><br>");
					}
				%>
				<input type="submit" value="удалить выбранных" />
			</form>
		</div>
	</div>
	
</body>
</html>