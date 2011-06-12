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
	Calendar startDate=new GregorianCalendar();
	Calendar endDate=new GregorianCalendar();
	Date date=new Date();
	endDate.setTime(date);//текущая дата
	endDate.set(Calendar.DAY_OF_MONTH,startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
	endDate.set(Calendar.HOUR_OF_DAY,23);
	endDate.set(Calendar.MINUTE,59);
	startDate.setTime(date);//текущая дата
	startDate.set(Calendar.DAY_OF_MONTH,1);
	startDate.set(Calendar.HOUR_OF_DAY,0);
	startDate.set(Calendar.MINUTE,0);
	d.drawAllResourcesForMonth(response,startDate,endDate);
%>