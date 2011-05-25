<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome,</h3>
	 <% out.println(((model.User)request.getAttribute("loggedUser")).getLogin()); %>
	 
        <br>Here beans!<br>
        hello, <jsp:useBean type="model.User" scope="request" id="loggedUser"></jsp:useBean>
        
        <jsp:getProperty name="loggedUser" property="login"/>
        <br>end of bean<br>
</body>
</html>