<%@page import="model.Countable"%>
<%@page import="model.Inventarable"%>
<%@page import="model.Resource"%>
<%@page import="utils.ResourceDAO"%>
<%@page import="java.util.ArrayList"%>
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
	List<Resource> resources=ResourceDAO.getAllResources();
%>
	<div id="block">
		<h1>Resource admin</h1>		
		<div class="menu" style="text-align: right; margin-top: 50px">
			<a href='AdminPanel'>Админ. панель</a> | <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
			| <a href="LogOutController">Выход</a>		
			<h2 align="center" >Выберите ресурс для редактирования</h2>
			<br>
			<div style="text-align: left">	
				<form name="editResourceForm" action="EditResourceController" method="post">
					<%
						for (Resource r: resources)
						{
							if (r instanceof Inventarable)
								out.println("<label><input type=\"radio\" name=\"res_id\" value=\""+r.getResource_id()+"\" >"+r.getResource_name()+" (ИН:"+((Inventarable)r).getInvenarno()+") </input></label><br>");
							else
							if (r instanceof Countable)
								out.println("<label><input type=\"radio\" name=\"res_id\" value=\""+r.getResource_id()+"\" >"+r.getResource_name()+" (Вместимость:"+((Countable)r).getMaxCapacity()+") </input></label><br>");
							else
							out.println("<label><input type=\"radio\" name=\"res_id\" value=\""+r.getResource_id()+"\" >"+r.getResource_name()+"</input></label><br>");
						} %>
						<input type="submit" name="showFields" value="редактировать"/>
				</form>
			</div>
		</div>
	</div>	
			

</body>
</html>