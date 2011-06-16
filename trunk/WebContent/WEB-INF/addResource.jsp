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
			<h2 align="center" >Содание нового ресурса</h2>
			<br>
			</div>
		
 <div id="block" align="center">	
  <form action="ResourceCreateController" method="POST">
  <table border="2">
				<tr>		
					<td id="#tableCell" align="center">Название нового ресурса:							
					</td>
					<td>
					<select name="resourceName">						
						<option value="Resource"></option>
						<option value="Monitor"></option>
						<option value="DinningRoom"></option>
						<option value="MeetingRoom"></option>
						<option value="EnglishRoom"></option>
						</select> 
					</td>
				</tr>				
				<tr>
				    <td id="#tableCell">
					<input type="checkbox" name="isCountable" value="" style="width: 100px;"> Максимальная вместимость:
					</td>
					<td>
						<input type="text" name="maxCapacity" style="width: 100px;"
						value="<%if (request.getParameter("maxCapacity") != null)
				        out.print(request.getParameter("maxCapacity"));%>" />		
					</td>
				</tr>
				<tr>
					<td id="#tableCell"><input type="checkbox" name="isInventarable" 
					value="" style="width: 100px;"> Инвентарный номер:
					<td>
						<input type="text" name="inventNum" style="width: 100px;
					value="<%if (request.getParameter("inventNum") != null)
				    out.print(request.getParameter("inventNum"));%>" />						
					</td>
				</tr>
			</table>
		<br><input type="submit" value="Создать">		
  </form>
</div>
<%
String msg = (String)request.getAttribute("message");
if (msg != null)
	out.print(msg);

request.removeAttribute("message");
%>

</body>
</html>