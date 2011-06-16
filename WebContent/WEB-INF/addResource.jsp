<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="utils.*"%>
<%@page import="java.util.*"%>
	
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
		<%@include file="../menu.jsp"%>
		<h2 align="center"  style="color: #ccffff;">Содание нового ресурса</h2>
  
		<%
			List<Resource> res = ResourceDAO.getAllResources();
			Iterator<Resource> it = res.iterator();
		%>
		<form action="ResourceCreateController" method="POST">
			<table border="2">
				<tr>
					<td id="#tableCell" align="center">Название нового ресурса:</td>
					<td><select name="resourceName">
							<% while(it.hasNext()) { 
								Resource curRes = it.next();
								out.print("<option value=\""+curRes.getClass().getName()+"\">"+curRes.getClass().getSimpleName()+"</option>");
							}%>
							
					</select></td>
				</tr>
								<tr>
					<td id="#tableCell" align="center">Имя нового ресурса:</td>
					<td><input type="text" name="resName" style="width: 100px;" /></td>
				</tr>
				<tr>
				
					<td id="#tableCell">Максимальная вместимость:
					</td>
					<td><input type="text" name="maxCapacity"
						style="width: 100px;"
						value="" />
					</td>
				</tr>
				<tr>
					<td id="#tableCell">
						Инвентарный номер:
					<td><input type="text" name="inventNum" style="width: 100px;" />
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="Создать">
		</form>
	</div>
	<%
		String msg = (String) request.getAttribute("message");
		if (msg != null)
			out.print(msg);

		request.removeAttribute("message");
	%>

</body>
</html>