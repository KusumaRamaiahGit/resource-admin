<!-- author - Martynenko Viktoria-->
<%@page import="java.text.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Client"%>
<%@page import="model.Admin"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<br>
<form action="GraphicController" method="POST">
<table border=0  align="center"  class="menu">
	<tr>
		<td></td>
		<td><div>
			<table border=0  align="center">
				<tr>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="<-"></td>
					<td align="center"><%Calendar date=new GregorianCalendar();
					date.setTime(new Date());
					int mon=date.get(Calendar.MONTH)+1;
					out.println("<h4>"+date.get(Calendar.YEAR)+"."+mon+"</h4>");%>
					</td>
					<td><input type="submit" name="nextGraphic" class="buttonVertical" value="->"></td>
				</tr>
			</table></div>
		</td>
		<td>
			<%
			 	Client user = (Client) session.getAttribute("User");
			 %>
						<%
							if (user instanceof Admin) {
								out.print("  <a href='AdminPanel'>Админ. панель</a> | ");
							}
						%>
						 <a href="CalendarController">Резервация</a> | 
						<a href="LogOutController">Выход</a>
		</td>		
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
	</tr>
	<tr>
		<%
		request.getSession().setAttribute( "someMonth", String.valueOf(date.get(Calendar.MONTH)) );
		request.getSession().setAttribute( "someYear", String.valueOf(date.get(Calendar.YEAR)) );
		%>
		<td colspan=3  align="center">		
			<img id="image" src="graphic1.jsp" />
		</td>		
	</tr>
</table>
<!--  
<input type="button" class="buttonHorisontal" ="Статистика для ресурсов" onClick='changeImage("resourceStatistic.jsp")'>
-->
</form>	
</div>
</body>
</html>