<%@page import="model.Resource"%>
<%@page import="java.awt.*"%>
<%@page import="java.awt.image.*"%>
<%@page import="com.sun.image.codec.jpeg.*"%>
<%@ page contentType="image/jpeg"%>
<%
	int width = 200, height = 200;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	g.setColor(Color.GREEN);
	g.fillRect(0, 0, width, height);
	g.setColor(Color.cyan);
	g.dispose();
	ServletOutputStream sos = response.getOutputStream();
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
	encoder.encode(image);
%>