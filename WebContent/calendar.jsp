<!-- author Martynenko Viktoria-->
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Резервирование ресурсов</title>
<SCRIPT LANGUAGE="JavaScript">        
function showCurrentMonth() {//показать текущий месяц
        var now = new Date();
        var day = now.getDate();
        var month = now.getMonth();
        var year = now.getYear();
        if (year < 2000)
            year = year + 1900;
        this.focusDay = day;
        document.Reservation.month.selectedIndex = month;
        document.Reservation.year.value = year;
        showCalendar(month, year);
        }
        function fourDigitsInYear(year) {//год должен быть четырехзначный
        if (year.length != 4) {
            alert ("Год должен состоять из четырех цифр.");
            document.Reservation.year.select();
            document.Reservation.year.focus();
            return(false);
        }
        else {
            return (true);
        }
        }
        function selectDate() {
        var year = document.Reservation.year.value;
        if (fourDigitsInYear(year)) {
            var month = document.Reservation.month.selectedIndex;
            showCalendar(month, year);
        }
        }
        function showPreviousMonth() {// показать предыдущий месяц
            var year = document.Reservation.year.value;
            var month = document.Reservation.month.selectedIndex;
            if(((year==1000)&&(month==0))||(year<1000))
                fourDigitsInYear(year-1);
            else{
                if (month == 0){
                month = 11;
                year--;document.Reservation.year.value = year;
                }
                else
                    month--;
                document.Reservation.month.selectedIndex = month;
            showCalendar(month, year);
            } 
        }
        function showNextMonth() {// показать следующий месяц
            var year = document.Reservation.year.value;
            var month = document.Reservation.month.selectedIndex;
            if(year<1000)
                fourDigitsInYear(year);
            else{
                if (month == 11){
                month = 0;
                year++;document.Reservation.year.value = year;
                }
                else
                    month++;
                document.Reservation.month.selectedIndex = month;
            showCalendar(month, year);
            }
        }
        function showPreviousYear() {// показать этот месяц предыдущего года
        var year = document.Reservation.year.value;
        if (year>1000) {
            var month = document.Reservation.month.selectedIndex;
            year--;
            document.Reservation.year.value = year;
            showCalendar(month, year);
        }
        else
            fourDigitsInYear(year-1);
        }
        function showNextYear() {// показать этот месяц следующего года
        var year = document.Reservation.year.value;
        if (fourDigitsInYear(year)) {
            var month = document.Reservation.month.selectedIndex;
            year++;
            document.Reservation.year.value = year;
            showCalendar(month, year);
        }
        else
            fourDigitsInYear(year);
        }
        function showCalendar(month, year) {// показать календать
        month = parseInt(month);//преобразует в число
        year = parseInt(year);
        var i = 0;
        var days = daysInMonth(month+1,year);//кол-во дней в месяце
        //кол-во дней впредыдущем месяце
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
        document.getElementById(encodeURIComponent(daysarray[i])).value+="  ";
        document.getElementById(encodeURIComponent(daysarray[i])).value+=i-startDayOfWeek+1;
        }
        var nextMonth;//дни следующего месяца
        for (i=days; i<42; i++) {
            nextMonth=i+1-days;
            if(nextMonth<10)
                document.getElementById(encodeURIComponent(daysarray[i])).value="  ";
            document.getElementById(encodeURIComponent(daysarray[i])).value+=nextMonth;
            document.getElementById(encodeURIComponent(daysarray[i])).disabled="disabled";
        }
        if(month==0)
            document.Reservation.previousMonth.value="Декабрь";
        else
            document.Reservation.previousMonth.value=document.forms["Reservation"].elements["month"].options[month-1].value;
        if(month==11)
            document.Reservation.nextMonth.value="Январь";
        else
            document.Reservation.nextMonth.value=document.forms["Reservation"].elements["month"].options[month+1].value;
        document.Reservation.previousYear.value=year-1;
        document.Reservation.nextYear.value=year+1;
        document.Reservation.Showcalendarendar.focus();
        }
        function daysInMonth(month,year) {//кол-во дней в месяце
        var days;
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) days=31;
        else if (month==4 || month==6 || month==9 || month==11) days=30;
        else if (month==2) {
        if (isLeapYear(year)) { days=29; }
        else { days=28; }
        }
        return (days);
        }
        function isLeapYear (Year) {//если год високосный
        if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
        return (true);
        } else { return (false); }
        }
        -->
        
        </SCRIPT>
</head>
<%!
	String [] arr=new String[5];

	public void jspInit()
	{
		String msg="init method";
		arr[0]="hello0";
		arr[1]="hello1";
		arr[2]="hello2";
		arr[3]="hello3";
		arr[4]="hello4";		
	}	
%>
<body onLoad="showCurrentMonth()">
	<p>
	<table width="100%" >
		<tr>
			<td width="85%"><h1>
			Резервирование ресурсов</h1></td>
			<td width="15%">
				<form action="LogOutController" Method="post">
					<input align="right" type="submit" value="Log Out"/>
				</form>
			</td>
		<tr>
		<td width="100%">
			<form action="Controller" method="POST" name="Reservation">
				<table border="1" cellspacing="2" cellpadding="2">
					<tbody>
						<tr>
							<td>Тип ресурса:<br> <select name="ResourceType">
									<option></option>
									<option></option>
									<option></option>
							</select><br>
							</td>
							<td rowspan="2" align="center">Выберите дату резервирования<br>
								<div>
									<TABLE>
										<TR>
											<TD align="center"><SELECT NAME="month"
												onChange="selectDate()">
													<OPTION value="Январь">Январь
													<OPTION value="Февраль">Февраль
													<OPTION value="Март">Март
													<OPTION value="Апрель">Апрель
													<OPTION value="Май">Май
													<OPTION value="Июнь">Июнь
													<OPTION value="Июль">Июль
													<OPTION value="Август">Август
													<OPTION value="Сентябрь">Сентябрь
													<OPTION value="Октябрь">Октябрь
													<OPTION value="Ноябрь">Ноябрь
													<OPTION value="Декабрь">Декабрь
											</SELECT> <INPUT NAME="year" TYPE=TEXT SIZE=4 MAXLENGTH=4> <INPUT
												TYPE="button" NAME="Showcalendarendar" value="Показать"
												onClick="selectDate()">
											</TD>
										</TR>
										<TR>
											<TD align="center">
												<div>
													<table align="center">
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
															<td><input type="submit" name="day0" id="0">
															</td>
															<td><input type="submit" name="day1" id="1">
															</td>
															<td><input type="submit" name="day2" id="2">
															</td>
															<td><input type="submit" name="day3" id="3">
															</td>
															<td><input type="submit" name="day4" id="4">
															</td>
															<td><input type="submit" name="day5" id="5">
															</td>
															<td><input type="submit" name="day6" id="6"><br>
															</td>
														</tr>
														<tr>
															<td><input type="submit" name="day7" id="7">
															</td>
															<td><input type="submit" name="day8" id="8">
															</td>
															<td><input type="submit" name="day9" id="9">
															</td>
															<td><input type="submit" name="day10" id="10">
															</td>
															<td><input type="submit" name="day11" id="11">
															</td>
															<td><input type="submit" name="day12" id="12">
															</td>
															<td><input type="submit" name="day13" id="13"><br>
															</td>
														</tr>
														<tr>
															<td><input type="submit" name="day14" id="14">
															</td>
															<td><input type="submit" name="day15" id="15">
															</td>
															<td><input type="submit" name="day16" id="16">
															</td>
															<td><input type="submit" name="day17" id="17">
															</td>
															<td><input type="submit" name="day18" id="18">
															</td>
															<td><input type="submit" name="day19" id="19">
															</td>
															<td><input type="submit" name="day20" id="20"><br>
															</td>
														</tr>
														<tr>
															<td><input type="submit" name="day21" id="21">
															</td>
															<td><input type="submit" name="day22" id="22">
															</td>
															<td><input type="submit" name="day23" id="23">
															</td>
															<td><input type="submit" name="day24" id="24">
															</td>
															<td><input type="submit" name="day25" id="25">
															</td>
															<td><input type="submit" name="day26" id="26">
															</td>
															<td><input type="submit" name="day27" id="27"><br>
															</td>
														</tr>
														<tr>
															<td><input type="submit" name="day28" id="28">
															</td>
															<td><input type="submit" name="day29" id="29">
															</td>
															<td><input type="submit" name="day30" id="30">
															</td>
															<td><input type="submit" name="day31" id="31">
															</td>
															<td><input type="submit" name="day32" id="32">
															</td>
															<td><input type="submit" name="day33" id="33">
															</td>
															<td><input type="submit" name="day34" id="34"><br>
															</td>
														</tr>
														<tr>
															<td><input type="submit" name="day35" id="35">
															</td>
															<td><input type="submit" name="day36" id="36">
															</td>
															<td><input type="submit" name="day37" id="37">
															</td>
															<td><input type="submit" name="day38" id="38">
															</td>
															<td><input type="submit" name="day39" id="39">
															</td>
															<td><input type="submit" name="day40" id="40">
															</td>
															<td><input type="submit" name="day41" id="41"><br>
															</td>
														</tr>
													</table>
												</div>
											</TD>
										<TR>
											<TD align="center"><INPUT TYPE=BUTTON
												NAME="currentMonth" VALUE="Текущий месяц"
												onClick="showCurrentMonth()"><br> <INPUT
												TYPE=BUTTON NAME="previousMonth" VALUE="Предыдущий"
												onClick="showPreviousMonth()"> <INPUT TYPE=BUTTON
												NAME="nextMonth" VALUE="Следующий" onClick="showNextMonth()"><br>
												Год<br> <INPUT TYPE=BUTTON NAME="previousYear"
												VALUE="Предыдущий" onClick="showPreviousYear()"> <INPUT
												TYPE=BUTTON NAME="nextYear" VALUE="Следующий"
												onClick="showNextYear()">
											</TD>
										</TR>
									</TABLE>
									<BR>
								</div>
							</td>
						</tr>
						<tr>
							<td>Ресурс:<br> <select name="Resource">
							<%
								for (int i=0; i<arr.length; i++)
								{
									out.println("<option> " + arr[i]); 
								}
							
							%>
									<option>Кухня
									<option>Meating room
									<option>English room
							</select><br>
							</td>
						</tr>
						<tr>
							<td>Начало:<br> <input type="text" name="Begin"
								value="" /><br> Окончание:<br> <input type="text"
								name="End" value="" /><br></td>
							<td>Расписание<br>
							</td>
						</tr>
						<tr colspan="2">
							<td colspan="2"><input type="submit" value="Зарезервировать" />
							<td>
						</tr>
					</tbody>
				</table>
			</form>
			</td>
		</tr>
	</table>
</body>
</html>
