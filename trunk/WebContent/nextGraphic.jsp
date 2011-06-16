<!-- author - Martynenko Viktoria-->
<%@page import="view.Diagram"%>
<%
	int month=Integer.parseInt(session.getAttribute("someMonth").toString());
	int year=Integer.parseInt(session.getAttribute("someYear").toString());
	Diagram d=new Diagram(month,year);
	d.drawAllResourcesForMonth(request,response);	
%>