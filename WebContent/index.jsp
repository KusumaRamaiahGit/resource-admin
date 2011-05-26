<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource admin</title>
</head>
<body>
	<h1>Welcome!</h1>
	<ul>
		<%--<%if (session.getAttribute("User").toString()!="") {--%>
		<%--	<li><a href="resourceadmin.jsp">Login page</a></li>
<%} else %>
	<li><a href="login.jsp">Login page</a></li>--%>
<%if (session.getAttribute("User") != null) {
    response.sendRedirect("calendar.jsp");
   } else
    response.sendRedirect("login.jsp");
  %>
	</ul>
</body>
</html>