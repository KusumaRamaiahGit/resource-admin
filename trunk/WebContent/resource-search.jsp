<%@page import="utils.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Resource"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Редактирование ресурсов</title>
</head>
<body>
<h1>Редактирование ресурсов</h1>
<%@include file="../menu.jsp"%>
Список ресурсов:
<form action="ResourceSearchController" method="POST">
	<select name="resourceSelector" size="10">
	<%
	ArrayList<Resource> resources = null; //= ResourceDAO.getAllResources();
		
	for (Resource r : resources)
	{
		out.print("<option value=" + r.getResource_id() + ">" + r.getResource_name() + "</option>");		
	}	
	%>			
	</select>
	<br><input type="submit" value="Редактировать">
</form>

<h1>Содание нового ресурса</h1>
<form action="ResourceCreateController" method="POST">
		Имя нового ресурса: <input type="text" name="resourceName">
		<br><input type="checkbox" name="isCountable" value=""> Максимальная вместимость: <input type="text" name="maxCapacity"> 
		<br><input type="checkbox" name="isInventarable" value=""> Инвентарный номер: <input type="text" name="inventNum">
		<br><input type="submit" value="Создать">		
</form>

<%
String msg = (String)request.getAttribute("message");
if (msg != null)
	out.print(msg);

request.removeAttribute("message");
%>

</body>
</html>