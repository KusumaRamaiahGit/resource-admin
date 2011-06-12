<!-- author - Martynenko Viktoria-->
<%@page import="model.Resource"%>
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
function imageForPreviousMonth() {
	var previousMonth=document.getElementById("previousMonth").value;
	if(previousMonth==0) {
		document.getElementById("previousMonth").value=11;
		document.getElementById("year").value=document.getElementById("year").value-1;
	}
	else
		document.getElementById("previousMonth").value=previousMonth-1;
	changeImage("previousGraphic");
}
function imageForNextMonth(jsp) {
	var nextMonth=document.getElementById("previousMonth").value;
	if(previousMonth==0) {
		document.getElementById("previousMonth").value=11;
		document.getElementById("year").value=document.getElementById("year").value-1;
	}
	else
		document.getElementById("previousMonth").value=nextMonth+1;
	changeImage("nextGraphic");
}
</script>
</head>
<body>
<div id="block">
<h1>Статистика</h1>
<br><br><br>
  <table border=0  align="center">
  <tr><td>
  <input type="button" value="За все время" onClick='changeImage("graphic1.jsp")'>
  <input type="button" value="За текущий месяц" onClick='changeImage("graphic2.jsp")'> 
  <!--  <input type="button" value="<-" onClick='imageForPreviousMonth()'>
  <input type="button" value="->" onClick='imageForNextMonth()'>-->
  <%
/*	    Calendar date=new GregorianCalendar();
		date.setTime( new Date() );//текущая дата
		if(date.get(2)==0) {
			out.print(" <input type='hidden' name='month' id='previousMonth' value='11'>");
			out.print(" <input type='hidden' name='year' id='yearForPreviousMonth' value='" + (date.get(1)-1) + "'>");
		}
		else {
			out.print(" <input type='hidden' name='month' id='previousMonth' value='" + (date.get(2)-1) + "'>");
			out.print(" <input type='hidden' name='year' id='yearForPreviousMonth' value='" + date.get(1) + "'>");
		}
		if(date.get(2)==11) {
			out.print(" <input type='hidden' name='month' id='nextMonth' value='0'>");
			out.print(" <input type='hidden' name='year' id='yearForNextMonth' value='" + (date.get(1)+1) + "'>");
		}
		else {
			out.print(" <input type='hidden' name='month' id='nextMonth' value='" + (date.get(2)+1) + "'>");
			out.print(" <input type='hidden' name='year' id='yearForNextMonth' value='" + date.get(1) + "'>");
		}
		*/
  %>
  <!-- <input type="button" value="За все время" onClick="changeImage()">
  <input type="button" value="За месяц" onClick="changeImage()">
  <input type="button" value="Пользователи" onClick="changeImage()">-->
<img id="image" src="graphic1.jsp" />
</td></tr>
</table>
</div>
</body>
</html>