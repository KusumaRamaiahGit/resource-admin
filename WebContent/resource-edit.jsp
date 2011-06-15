<%@page import="model.Inventarable"%>
<%@page import="model.Countable"%>
<%@page import="model.Resource"%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Редактирование ресурса</title>
</head>
<body>
	<h1>
		Ресурс
		<%-- NullPointerExcception уже не выпрыгивает --%>
		<%
			Resource r = null;
			if (request.getAttribute("resource") != null) {
				r = (Resource) request.getAttribute("resource");
				out.println(r.getResource_name());
			}
		%>
	</h1>
	<%@include file="../menu.jsp"%>
	<form action="ResourceDeleteController" method="POST">
		<h3>Удалить ресурс</h3>
		<input type="hidden" name="resource_id"
			value="<%=r.getResource_id()%>" /> <input type="submit"
			value="Удалить">
	</form>

	<form action="ResourceEditController" method="POST">
		<h3>Редактировать ресурс</h3>
		<h5>(ненужные поля оставить пустыми)</h5>
		<br> Новое название ресурса: <input type="text"
			name="resourceNameNew">
		<%
			//Resource r = (Resource) request.getAttribute("resource");
			if (r != null) {
				if (r instanceof Countable) {
					out.print("Новая вместительность ресурса: <input type=\"text\" name=\"resourceCountNew\">");
				}
				if (r instanceof Inventarable) {
					out.print("Новый инвентарный номер ресурса: <input type=\"text\" name=\"resourceInventNumNew\">");
				}
			}
		%>
		<br> <input type="submit" value="Редактировать"> <input
			type="hidden" name="resource_id" value="<%=r.getResource_id()%>" />
	</form>
	<%
		String msg = (String) request.getAttribute("message");
		if (msg != null)
			out.print(msg);

		request.removeAttribute("message");
	%>
</body>
</html>