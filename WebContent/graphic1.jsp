<!-- author - Martynenko Viktoria-->
<%@page import="view.Diagram"%>
<%
	Diagram d=new Diagram();
	d.drawAllResourcesAndFullTime(request,response);
%>