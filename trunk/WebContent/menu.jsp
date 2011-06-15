<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%
	Client user = (Client) session.getAttribute("User");
	if (user != null) {
%>
<p class="menu">

	Здравствуйте,
	<%=user.getLogin()%>
	<img src="img/user-icon.png" />
	<%
		if (user instanceof Admin) {
				out.print(" | <a href='AdminPanel'>Админ. панель</a> ");
			}
	%>
	| <a href="calendar.jsp">Календарь</a> | <a href="StatisticController">Статистика</a>
	| <a href="UserReservationsController">Резервации</a> | <a
		href="LogOutController">Выход</a>
</p>
<%
	}
%>