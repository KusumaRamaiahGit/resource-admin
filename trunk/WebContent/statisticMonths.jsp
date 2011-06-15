<!-- author - Martynenko Viktoria-->
<%@page import="java.text.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<br>
<form action="GraphicController" method="POST">		
<table border=0  align="center">
	<tr>
		<td></td>
		<td><div>
			<table border=0  align="center">
				<tr>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="<-"></td>
					<td align="center"><%
					int mon=Integer.parseInt(request.getSession().getAttribute("someMonth").toString())+1;
					out.println("<h4>"+request.getSession().getAttribute("someYear")+"."+mon+"</h4>");
					%></td>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="->"></td>
				</tr>
			</table></div>
		</td>
		<td></td>		
	</tr>
	<tr>
		<td>  
			<input type="button" class="buttonHorisontal" value="Статистика за все время" onClick='changeImage("graphic1.jsp")'>
			</td>
			<td>
			<input type="submit" class="buttonHorisontal" name="nextGraphic" value="Статистика за текущий месяц">
			</td>
			<td>
				<input type="button" class="buttonHorisontal" value="Статистика для пользователей" onClick='changeImage("userStatistic.jsp")'>
			</td>
			<!--  
				<input type="button" class="buttonHorisontal" ="Статистика для ресурсов" onClick='changeImage("resourceStatistic.jsp")'>
			-->
	</tr>
	<tr>
		<td colspan=3>
			<img id="image" src="nextGraphic.jsp" />
		</td>
	</tr>
</table>
</form>
<%
//out.println("<input type='hidden' name='month' id='month' value='"+request.getSession().getAttribute("someMonth")+"'>"); 
//out.println("<input type='hidden' name='year' id='year' value='"+request.getSession().getAttribute("someYear")+"'>");
%>
</div>
</body>
</html>