<%@page import="model.Resource"%>
<%@page import="utils.ResourceDAO"%>
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
		<a href='AdminPanel'>Админ. панель</a> | <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
			| <a href="LogOutController">Выход</a>		
			<h2 align="center" >Удаление ресурсов</h2>

        <div style="text-align: left">
		<form name="userDeleteForm" action="ResourceDeleteController" method="post">
			<%
				for (Resource r: ResourceDAO.getAllResources())
				{
   		     out.println("<input type=\"checkbox\" name=\"resource_id\" value=\""+r.getResource_id()+"\" >"+r.getResource_name().toString()+"</input><br>");
				
				}
			%>
			<input type="submit" value="Удалить"/>
	</form>
 </div>
</body>
</html>