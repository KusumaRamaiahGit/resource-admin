<!-- author - Martynenko Viktoria-->
<%@page import="model.Resource"%>
<%@page import="view.Diagram"%>
<%@page import="java.awt.*"%>
<%@page import="java.awt.image.*"%>
<%@page import="com.sun.image.codec.jpeg.*"%>
<%@ page contentType="image/jpeg"%>
<%
	Diagram d=new Diagram();
	d.drawAllResourcesAndFullTime(response);
%>