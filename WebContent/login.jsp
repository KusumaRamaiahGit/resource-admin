<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource admin</title>
</head>
<body>
<% 
try {
	if ( (Integer) request.getAttribute("errorType") == 1) { 
		out.println("<p style='background:#FFCFEE;'>Login is incorrect</p>");
	}
} catch (Exception e)
{
	
}
%>
<h3>User autorization page.</h3>
		<form method="POST" action="LoginViewController">
            login:<input type="text" name="login" value="" /><br>
            password:<input type="text" name="password" value="" /><br>
            <input type="submit" value="submit" />
		</form>
</body>
</html>