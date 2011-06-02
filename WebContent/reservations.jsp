<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="model.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
<%@page import="java.util.List"%>

<!--
image range coordinates : 

start side: 0 (max) 22(min)
middle side : -8
end side: -96 (max) ...  -118 (min)
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="block">
	<h1>Resource admin</h1>
	<%
		List<Reservation> todaysReservations = (List<Reservation>)request.getAttribute("reservationsList");	
	%>
    <div id="reservations">
    <div class="head">Зарезервированное время на <% out.print(""+request.getAttribute("day")+"."+request.getAttribute("month")+"."+request.getAttribute("year"));  %></div>
 		<table class="timelineTable" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>Имя</td>
    <td>1</td>
    <td>2</td>
    <td>3</td>
    <td>4</td>
    <td>5</td>
    <td>6</td>
    <td>7</td>
    <td>8</td>
    <td>9</td>
    <td>10</td>
    <td>11</td>
    <td>12</td>
    <td>13</td>
    <td>14</td>
    <td>15</td>
    <td>16</td>
    <td>17</td>
    <td>18</td>
    <td>19</td>
    <td>20</td>
    <td>21</td>
    <td>22</td>
    <td>23</td>
    <td>24</td>
  </tr>
  <%
  for(Reservation r : todaysReservations) {
	  for(Client c : r.getClients()) {
	  out.print("<tr><td>" + c.getLogin() + "</td>");
	  for (int i = 1 ; i<25; i++) {
		  out.print("<td");
		  if (i == r.getStart_time().getHours()) {
			  out.print(" style='width: 20px; background-image:url(img/timeline.png); background-repeat: no-repeat; background-position: "+ (r.getStart_time().getMinutes()/60.0*18)+"px 0px;' ");  
		  } else if (i == r.getEnd_time().getHours()) {
			  out.print(" style='width: 20px; background-image:url(img/timeline.png); background-repeat: no-repeat; background-position: "+ (-118 + r.getEnd_time().getMinutes()/60.0*18)+"px 0px;' ");
		  } else if ((i > r.getStart_time().getHours()) && (i < r.getEnd_time().getHours())) {
			  out.print(" style='width: 20px; background-image:url(img/timeline.png); background-repeat: no-repeat; background-position: -8px 0px;'"); 	
		  }
		  out.print("></td>");
	  }
	  out.print("</tr>");
	  
	  } 
  }
  
  %>
  
</table>
  		<center>
	  		<form method="POST" action="ReserveController">
	            <input name="start_time" class='time-input' type="text" value="00:00" />
	            <input name="end_time" class='time-input' type="text" value="23:45" />
	            <input name="submit" type="submit"  value="Выбрать" />
	        </form>
        </center>
    </div>
</div>
		
</body>
</html>