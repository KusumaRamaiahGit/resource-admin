<%@page import="model.Client"%>
<%@page import="utils.ClientDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<%@include file="../menu.jsp"%>
		<h2 align="center" style="color: #ccffff;">Редактирование пользователей</h2> 
			<div style="text-align: left">		
				<form name="userEditForm" action="UserEditController" method="post">
					<%
						for (Client c:clients)
						{
							out.println("<input type=\"radio\" name=\"client_id\" value=\""+c.getClient_id()+"\" >"+c.toString()+"</input><br>");
						}
					%>
					<input type="submit" name="showFields" value="редактировать выбранного"/>
				</form>
			</div>
		</div>
 
</body>
</html>