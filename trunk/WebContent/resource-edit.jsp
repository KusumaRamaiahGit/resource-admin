<%@page import="model.Inventarable"%>
<%@page import="model.Countable"%>
<%@page import="model.Resource"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
          
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Редактирование ресурса</title>
</head>
<body>
<h1>Ресурс <%=((Resource)request.getAttribute("resource")).getResource_name() %></h1>

 <form action="ResourceDeleteController" method="POST">
 <h3>Удалить ресурс</h3>
 <input type="submit" value="Удалить">
 </form>
 <form action="ResourceEditController" method="POST">
 <h3>Редактировать ресурс</h3>
 <h5>(ненужные поля оставить пустыми)</h5>
 <br>
 Новое название ресурса: <input type="text" name="resourceNameNew">
 <% 
 Resource r = (Resource)request.getAttribute("resource");
 if (r instanceof Countable)
 {
	 out.print("Новая вместительность ресурса: <input type=\"text\" name=\"resourceCountNew\">");
 }
 if (r instanceof Inventarable)
 {
	 out.print("Новый инвентарный номер ресурса: <input type=\"text\" name=\"resourceInventNumNew\">");
 }
 
 %>
 <br>
 <input type="submit" value="Редактировать">
 </form>		
<%
String msg = (String)request.getAttribute("message");
if (msg != null)
	out.print(msg);

request.removeAttribute("message");
%>
</body>
</html>