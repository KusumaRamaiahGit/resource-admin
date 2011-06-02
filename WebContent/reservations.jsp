<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
image range coordinates : 

start side: 0 (max) 22(min)
middle side : -8
end side: -96 (max) ...  -118 (min)
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function Clear(id) { 
	document.getElementById(id).value = '';67
}
</script>
</head>
<body>
<div id="block">
	<h1>Resource admin</h1>
	<%		
	//out.print(todaysReservations + " :  SIZE " + todaysReservations.size());
	   %>
    <div id="reservations">
    <div class="head">Зарезервированное время</div>
    <%List<Reservation> todaysReservations =   (List<Reservation>)request.getAttribute("reservationsList");
    if(todaysReservations.size()>0){
		for(Reservation r:todaysReservations)
			out.println(r.getResource());
	}%>
 	<%// List<Reservation> selectDateReservations=(List<Reservation>)request.getAttribute("selectreservationList");
 	//out.println(selectDateReservations.get(0));
 	%>
    </div>
</div>

		
		
</body>
</html>