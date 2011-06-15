<!-- author - Martynenko Viktoria-->
<%@page import="model.Admin"%>
<%@page import="model.Resource"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="model.Client"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="style1.css" rel="stylesheet" type="text/css" />
<title>Резервирование ресурсов | Resource admin</title>
<SCRIPT LANGUAGE="JavaScript">
function showCalendar(month, year) {// показать календать
	month = parseInt(month);//преобразует в число
	year = parseInt(year);
	var days = daysInMonth(month+1,year);//кол-во дней в месяце
	//кол-во дней в предыдущем месяце
	var daysInPreviousMonth;
	if(month==0)
		daysInPreviousMonth=daysInMonth(12,year-1);
	else
		daysInPreviousMonth=daysInMonth(month,year);
	//
	var firstOfMonth = new Date (year, month, 1);
	var startDayOfWeek=firstOfMonth.getDay();//день недели, на который попало 1 число
	if(startDayOfWeek==0)
		startDayOfWeek = 6;
	else
		startDayOfWeek--;
	days += startDayOfWeek;
	//кнопки-дни
	var daysarray = new Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41);
	var i;
	for(i=0;i<42;i++){
		document.getElementById(encodeURIComponent(daysarray[i])).value="";
		document.getElementById(encodeURIComponent(daysarray[i])).disabled=false;
	}
	var previousMonth;//дни предыдущего месяца
	for (i = 0; i < startDayOfWeek; i++) {
		previousMonth=daysInPreviousMonth-startDayOfWeek+i+1;
		document.getElementById(encodeURIComponent(daysarray[i])).value+=previousMonth;
		document.getElementById(encodeURIComponent(daysarray[i])).disabled="disabled";
	}
	for (i = startDayOfWeek; i < days; i++) {
		if (i-startDayOfWeek+1 < 10)
			document.getElementById(encodeURIComponent(daysarray[i])).value+=" ";
		document.getElementById(encodeURIComponent(daysarray[i])).value+=i-startDayOfWeek+1;
	}
	var nextMonth;//дни следующего месяца
	for (i=days; i<42; i++) {
		nextMonth=i+1-days;
		if(nextMonth<10)
			document.getElementById(encodeURIComponent(daysarray[i])).value=" ";
		document.getElementById(encodeURIComponent(daysarray[i])).value+=nextMonth;
		document.getElementById(encodeURIComponent(daysarray[i])).disabled="disabled";
	}
	//document.Reservation.Showcalendar.focus();
}
function selectDate() {
	var year = document.Reservation.year.value;
	if (normalYear(year)) {
		var month = getMonth(document.Reservation.monthsRadioGroup);
		showCalendar(month, year);
	}
}
function showCurrentMonth() {//показать текущий месяц
	var now = new Date();
	var day = now.getDate();
	var month = now.getMonth();
	var year = now.getFullYear();
	if (year < 2000)
		year = year + 1900;
	this.focusDay = day;
	setMonth(document.Reservation.monthsRadioGroup,month);
	document.Reservation.resourcesRadioGroup[0].checked=true;
	document.Reservation.year.value = year;
	showCalendar(month, year);
}
function showPreviousMonth() {// показать предыдущий месяц
	var year = document.Reservation.year.value;
	var month = getMonth(document.Reservation.monthsRadioGroup);
	if(((normalYear(year))&&(month!=0))||((normalYear(year-1))&&(month == 0))){
		if (month == 0){
			month = 11;
			year--;document.Reservation.year.value = year;
		}
		else
			month--;
		setMonth(document.Reservation.monthsRadioGroup,month);
		showCalendar(month, year);
	}
}
function showNextMonth() {// показать следующий месяц
	var year = document.Reservation.year.value;
	var month = getMonth(document.Reservation.monthsRadioGroup);
	if (normalYear(year)) {
		if (month == 11){
			month = 0;
			year++;document.Reservation.year.value = year;
		}
		else
			month++;
		setMonth(document.Reservation.monthsRadioGroup,month);
		showCalendar(month, year);
	}
}
function normalYear(year){
	if(year>=100)
		return true;
	else{
		alert ("Год должен состоять минимум из трех цифр.");
		document.Reservation.year.select();
		document.Reservation.year.focus();
		return false;
	}
}

function daysInMonth(month,year) {//кол-во дней в месяце
	var days=0;
	if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
		days=31;
	else
		if (month==4 || month==6 || month==9 || month==11)
			days=30;
		else
			if (month==2) {
				if (isLeapYear(year)){ 
					days=29;
				}
				else { 
					days=28;
				}
			}
	return days;
}
function isLeapYear (Year) {//если год високосный
	if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
		return (true);
	}
	else{
		return (false);
	}
}
function getMonth(monthsRadioGroup)	{
	for (var i=0; i < monthsRadioGroup.length; i++)
		if (monthsRadioGroup[i].checked)
			return i;
}
function setMonth(monthsRadioGroup,i) {
	monthsRadioGroup[i].checked=true;
}

</SCRIPT>

</head>
<body onLoad="showCurrentMonth()">
	<div id="block">
		<h1>Resource admin</h1>
		<%@include file="../menu.jsp"%>
		<table width="100%" align="center">
			<tr>
				<td width="100%"></td>
			<tr>
				<td colspan=2>
					<form action="ReservationController" method="POST"
						name="Reservation">
						<div>
							<table class="calendar-picker-table" align="center" border="0"
								cellspacing="3" cellpadding="2">
								<tbody>
									<tr>
										<td><div class="blockResource">
												<div>
													<img src="img/resources-icon.png"
														style="vertical-align: middle;"><span
														style="vertical-align: middle;">Ресурсы:</span>
												</div>
												<%
												@SuppressWarnings("unchecked")
													List<Resource> resources = (List<Resource>) session
															.getAttribute("resources");
													for (Resource r : resources) {
														//out.print("<option value='" + r.getResource_id() + "'>"+r.getResource_name());
														out.print("<label><input type='radio' name='resourcesRadioGroup' value='"
																+ r.getResource_id()
																+ "'>"
																+ r.getResource_name()
																+ "<br></label>");
													}
												%><br>
											</div></td>
										<td align="center">
											<div id="blockCalendar">
												<table align="center">
													<tbody>
														<tr>
															<td colspan="4"><h3 align="center">Выберите
																	дату резервирования</h3>
															</td>
														</tr>
														<tr>
															<td><center>
																	<INPUT class="edit" onchange="selectDate()" NAME="year"
																		TYPE=TEXT SIZE=4 MAXLENGTH=4><input
																		TYPE="button" class="refresh-button"
																		NAME="Showcalendarendar" value=""
																		onClick="selectDate()">
																</center>
															</td>
															<td></td>
															<td><center>
																	<input TYPE="BUTTON" id="current-month-btn"
																		NAME="currentMonth" VALUE=""
																		onClick="showCurrentMonth()">
																</center>
															</td>
															<td></td>
														</tr>
														<tr>
															<td><label><input type="radio"
																	name="monthsRadioGroup" onClick="selectDate()"
																	value="0" />Январь </label> <br> <label> <input
																	type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="1" />Февраль </label> <br>
																<label> <input type="radio"
																	name="monthsRadioGroup" onClick="selectDate()"
																	value="2" />Март </label> <br> <label> <input
																	type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="3" />Апрель </label> <br> <label>
																	<input type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="4" />Май </label> <br> <label>
																	<input type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="5" />Июнь </label> <br> <label>
																	<input type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="6" />Июль </label> <br> <label>
																	<input type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="7" />Август </label> <br> <label>
																	<input type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="8" />Сентябрь </label> <br>
																<label> <input type="radio"
																	name="monthsRadioGroup" onClick="selectDate()"
																	value="9" />Октябрь </label> <br> <label> <input
																	type="radio" name="monthsRadioGroup"
																	onClick="selectDate()" value="10" />Ноябрь </label> <br>
																<label> <input type="radio"
																	name="monthsRadioGroup" onClick="selectDate()"
																	value="11" />Декабрь</label></td>
															<td style="vertical-align: middle;"><INPUT
																TYPE=BUTTON class="arrow-left" style="cursor: pointer;"
																NAME="previousMonth" onClick="showPreviousMonth()">
															</td>
															<td>
																<div>
																	<table align="center" cellpadding="3">
																		<tr>
																			<td>Пн</td>
																			<td>Вт</td>
																			<td>Ср</td>
																			<td>Чт</td>
																			<td>Пт</td>
																			<td>Сб</td>
																			<td>Вс</td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="0"></td>
																			<td><input type="submit" class="day" name="day"
																				id="1"></td>
																			<td><input type="submit" class="day" name="day"
																				id="2"></td>
																			<td><input type="submit" class="day" name="day"
																				id="3"></td>
																			<td><input type="submit" class="day" name="day"
																				id="4"></td>
																			<td><input type="submit" class="day" name="day"
																				id="5"></td>
																			<td><input type="submit" class="day" name="day"
																				id="6"><br></td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="7"></td>
																			<td><input type="submit" class="day" name="day"
																				id="8"></td>
																			<td><input type="submit" class="day" name="day"
																				id="9"></td>
																			<td><input type="submit" class="day" name="day"
																				id="10"></td>
																			<td><input type="submit" class="day" name="day"
																				id="11"></td>
																			<td><input type="submit" class="day" name="day"
																				id="12"></td>
																			<td><input type="submit" class="day" name="day"
																				id="13"><br></td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="14"></td>
																			<td><input type="submit" class="day" name="day"
																				id="15"></td>
																			<td><input type="submit" class="day" name="day"
																				id="16"></td>
																			<td><input type="submit" class="day" name="day"
																				id="17"></td>
																			<td><input type="submit" class="day" name="day"
																				id="18"></td>
																			<td><input type="submit" class="day" name="day"
																				id="19"></td>
																			<td><input type="submit" class="day" name="day"
																				id="20"><br></td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="21"></td>
																			<td><input type="submit" class="day" name="day"
																				id="22"></td>
																			<td><input type="submit" class="day" name="day"
																				id="23"></td>
																			<td><input type="submit" class="day" name="day"
																				id="24"></td>
																			<td><input type="submit" class="day" name="day"
																				id="25"></td>
																			<td><input type="submit" class="day" name="day"
																				id="26"></td>
																			<td><input type="submit" class="day" name="day"
																				id="27"><br></td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="28"></td>
																			<td><input type="submit" class="day" name="day"
																				id="29"></td>
																			<td><input type="submit" class="day" name="day"
																				id="30"></td>
																			<td><input type="submit" class="day" name="day"
																				id="31"></td>
																			<td><input type="submit" class="day" name="day"
																				id="32"></td>
																			<td><input type="submit" class="day" name="day"
																				id="33"></td>
																			<td><input type="submit" class="day" name="day"
																				id="34"><br></td>
																		</tr>
																		<tr>
																			<td><input type="submit" class="day" name="day"
																				id="35"></td>
																			<td><input type="submit" class="day" name="day"
																				id="36"></td>
																			<td><input type="submit" class="day" name="day"
																				id="37"></td>
																			<td><input type="submit" class="day" name="day"
																				id="38"></td>
																			<td><input type="submit" class="day" name="day"
																				id="39"></td>
																			<td><input type="submit" class="day" name="day"
																				id="40"></td>
																			<td><input type="submit" class="day" name="day"
																				id="41"><br></td>
																		</tr>
																	</table>
																</div></td>
															<td style="vertical-align: middle;"><INPUT
																TYPE=BUTTON class="arrow-right" style="cursor: pointer;"
																NAME="nextMonth" onClick="showNextMonth()">
															</td>
														</tr>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</tbody>
												</table>
											</div></td>
									</tr>

								</tbody>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr align="center">
				<td></td>
				<td colspan=2>
				<td>
			</tr>
		</table>
	</div>
</body>
</html>