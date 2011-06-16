<!-- author - Martynenko Viktoria-->
<%@page import="java.text.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.*"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="style1.css" rel="stylesheet" type="text/css" />
<title>Статистика</title>
<script language="javascript">
function changeImage(jsp) {
	var img = document.getElementById("image");
    img.setAttribute("src", jsp);
}
</script>
</head>
<body>
<div id="block" align="left">
<h1>Статистика</h1>
<%@include file="../menu.jsp"%>
<form action="GraphicController" method="POST">	
<table border=0  align="center">
	<tr>
		<td></td>
		<td><div>
			<table border=0  align="center">
				<tr>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="<-"></td>
					<td align="center"><%Calendar date=new GregorianCalendar();
					date.setTime(new Date());
					int mon=date.get(Calendar.MONTH)+1;
					out.print(date.get(Calendar.YEAR)+"."+mon);
					%>
					</td>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="->"></td>
				</tr>
			</table></div>
		</td>
		<td>
		</td>		
	</tr>
	<tr>
		<td>  
			<input type="button" class="buttonHorisontal" value="Статистика за все время" onClick='changeImage("GraphicResources")'>
		</td>
		<td>
			<input type="submit" class="buttonHorisontal" name="nextGraphic" value="Статистика за текущий месяц">
		</td>
		<td align="left">
			<input type="button" class="buttonHorisontal" value="Статистика для пользователей" onClick='changeImage("UserStatistic")'>
		</td>			
	</tr>
	<tr>
		<%
		request.getSession().setAttribute( "someMonth", String.valueOf(date.get(Calendar.MONTH)) );
		request.getSession().setAttribute( "someYear", String.valueOf(date.get(Calendar.YEAR)) );
		%>
		<td colspan=3>
		<img id="image" src="GraphicResources" />		 
		</td>		
	</tr>
</table>
</form>	
</div>
</body>
</html>
