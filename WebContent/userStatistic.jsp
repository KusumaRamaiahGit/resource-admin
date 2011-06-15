<!-- author - Martynenko Viktoria-->

<%@page import="view.Diagram"%>
<%@page import="java.awt.*"%>
<%@page import="java.awt.image.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.sun.image.codec.jpeg.*"%>
<%@ page contentType="image/jpeg"%>
<%	
	Diagram d=new Diagram();
	d.drawUserStatistic(response);
%>