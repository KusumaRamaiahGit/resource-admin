<%@page import="model.Inventarable"%>
<%@page import="model.Countable"%>
<%@page import="model.Resource"%>
<%@page import="utils.ResourceDAO"%>
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
<% Resource res=ResourceDAO.getResourceById(Long.parseLong(request.getParameter("res_id"))); %>
	<div id="block">
		<h1>Resource admin</h1>		
		<div class="menu" style="text-align: right; margin-top: 50px">
			<a href='AdminPanel'>Админ. панель</a> | <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
			| <a href="LogOutController">Выход</a>		
						<h2 align="center" >Редактирование ресурса</h2>
			<br>
			<div style="text-align: left">	
				<form action="EditResourceController" method="post" >
					<input type="hidden" name="res_id" value="<%=res.getResource_id() %>" />
					Название:<input type="text" name="res_name" value="<%=res.getResource_name() %>" /><br>
					<%if (res instanceof Countable) {%>
						Вместимость:<input type="text" name="res_cap" value="<%=((Countable)res).getMaxCapacity() %>" /><br>
					<%}else
						if (res instanceof Inventarable)
						{%>
							Инвентарный номер:<input type="text" name="res_no" value="<%=((Inventarable)res).getInvenarno() %>" /><br>
						<%} %>
					
					<input type="submit" name="edit" value="Редактировать"/>
				</form>
			</div>
		</div>
	</div>
			

</body>
</html>