<!-- author - Martynenko Viktoria-->
<%@page import="view.Diagram"%>
<%@page import="java.awt.*"%>
<%@page import="java.awt.image.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.sun.image.codec.jpeg.*"%>
<%@page contentType="image/jpeg"%>
<%@page import="java.awt.Graphics"%>

<%
	int month=Integer.parseInt(session.getAttribute("someMonth").toString());
	int year=Integer.parseInt(session.getAttribute("someYear").toString());
	Diagram d=new Diagram(month,year);
	d.drawAllResourcesForMonth(response);	
%>