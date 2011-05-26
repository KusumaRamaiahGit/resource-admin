<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function Clear(id) { 
	document.getElementById(id).value = '';
}
</script>
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
            <input name="login" type="text" id='login' value="логин" onclick="return Clear('login');"/>
            <input name="password" type="password" id="password" value="??????" onclick="return Clear('password');"/>
            <input name="submit" type="submit" id="submit" value="" />
        </form>
        <div align="center"><a href="#">регистрация</a></div>
    </div>
</div>
		
		
</body>
</html>