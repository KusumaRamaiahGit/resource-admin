<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Client" %>
<p class="menu">
	<%
		Client user = (Client) session.getAttribute("User");
	%>
	Здравствуйте,
	<%=user.getLogin()%>
	<img src="img/user-icon.png" /> | <a href="calendar.jsp">Календарь</a>
	| <a href="StatisticController">Статистика</a> | <a
		href="LogOutController">Выход</a>
</p>