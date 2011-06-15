<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="admin.css" rel="stylesheet" type="text/css" />

 
</head>
<body>
	<div id="block">
		<h1>Resource admin</h1>
		<%@include file="../menu.jsp"%>
		<div id="reservations" style="color: #999;">
			<div class="head">Зарезервированное время.</div>

			<%
				List<Reservation> resList = (List<Reservation>) request
						.getAttribute("reservationsList");
				Iterator<Reservation> it = resList.iterator();

				if (it.hasNext()) {
					out.print("<form method=\"POST\" action=\"ReservationRemoveController\">");
					DateFormat timeFormat = DateFormat.getDateTimeInstance();
					out.println("<ul>");
					while (it.hasNext()) {
						Reservation curRes = it.next();
						out.println("<label><li><input TYPE=checkbox name=reservations VALUE="
								+ curRes.getReservation_id()
								+ ">"
								+ timeFormat.format(curRes.getStart_time()
										.getTime())
								+ " - "
								+ timeFormat.format(curRes.getEnd_time().getTime())
								+ " ["
								+ curRes.getResource().getResource_name()
								+ "]</li></label>");
					}
					out.println("<ul>");
					out.println("<input name=\"submit\" type=\"submit\" value=\"Удалить выбранное\" onclick=\"return confirm('Вы уверены, что хотите удалить выбранные ресурсы?')\" /></form>");
				} else {
					out.println("<h4>У вас нет зарезервированных ресурсов.</h4>");
				}
			%>
		</div>

	</div>
</body>
</html>