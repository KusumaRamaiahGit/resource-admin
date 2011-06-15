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
 <%
	List<Client> clients=ClientDAO.getAllClients();
%>
<div id="block">
		<h1>Resource admin</h1>				
		<div class="menu" style="text-align: right; margin-top: 50px">
		<h2 align="center" >Выберите пользователей для удаления</h2>
			<br>
			<a href='AdminPanel'>Админ. панель</a> | <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
			| <a href="LogOutController">Выход</a>
			
			<br>
			<div style="text-align: left">
			<form name="userDeleteForm" action="UserDeleteController" method="post">
				<%
					for (Client c:clients)
					{
						if (!c.equals((Client)request.getSession().getAttribute("User")))
						out.println("<input type=\"checkbox\" name=\"client_id\" value=\""+c.getClient_id()+"\" >"+c.toString()+"</input><br>");
					}
				%>
				<input type="submit" value="удалить выбранных"/>
			</form>
			</div>
	</div>
 </div>
</body>
</html>