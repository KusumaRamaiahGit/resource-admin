<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
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

<div id="block">
<h1>Resource admin</h1>

    <div id="auth">
    <div class="head">Авторизация</div>
    <form method="POST" action="LoginViewController">
    	<input type="text" name="login" value="" />
        <input type="text" name="password" value="" />

        <input name="" type="submit" id="submit" value="" />
    </form>
    </div>
</div>
		
		
</body>
</html>