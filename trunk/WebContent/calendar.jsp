<!-- author Martynenko Viktoria-->
<%@page import="java.io.PrintWriter"%>
<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Резервирование ресурсов</title>
<SCRIPT LANGUAGE="JavaScript">
    function showCalendar(month, year) {// показать календать
        month = parseInt(month);//преобразует в число
        year = parseInt(year);
        var i = 0;
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
        document.Reservation.previousYear.value=year-1;
        document.Reservation.nextYear.value=year+1;
        document.Reservation.Showcalendarendar.focus();
        }
        function selectDate() {
            var year = document.Reservation.year.value;
            if (normalYear(year)) {
                var month = document.Reservation.month.selectedIndex;
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
            document.Reservation.month.selectedIndex = month;
            document.Reservation.year.value = year;
            showCalendar(month, year);
        }  
        function showPreviousMonth() {// показать предыдущий месяц
            var year = document.Reservation.year.value;
            var month = document.Reservation.month.selectedIndex;
            if(((normalYear(year))&&(month!=0))||((normalYear(year-1))&&(month == 0))){
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
          if (normalYear(year)) {
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
        var days;
        if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            days=31;
        else
            if (month==4 || month==6 || month==9 || month==11)
                days=30;
        else
            if (month==2) {
                if (isLeapYear(year))
                    { days=29; }
                else
                    { days=28; }
            }
        return (days);
        }
        function isLeapYear (year) {//если год високосный
        if (((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)) {
            return (true);
        }
        else{
            return (false);
        }
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
<body onLoad="showCurrentMonth()" align="center">
    <div>Hello, 
<% Client u = (Client) request.getSession().getAttribute("User");
out.println(""+u.getLogin()+", your contact: "+u.getContact());%> 
</div>
	<p>
	<table width="100%" align="center">
		<tr>
                    <td width="85%"><h1>
                    Резервирование ресурсов</h1></td>
                    <td width="15%">
                        <form action="LogOutController" Method="post">
                                <input align="right" type="submit" value="Log Out"/>
                        </form>
                    </td>
		<tr>
		<td>
			<form action="Controller" method="POST" name="Reservation">
                            <div>
				<table align="center" border="1" cellspacing="2" cellpadding="2">
					<tbody>
						<tr>
                                                    <td>Тип ресурса:<br> <select name="ResourceType">
                                                                    <option></option>
                                                                    <option></option>
                                                                    <option></option>
                                                    </select><br>
                                                    </td>
                                                    <td rowspan="2" align="center">
                                                            <div>
                                                                <table border="1">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td colspan="4">Выберите дату резервирования<br></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><INPUT NAME="year" TYPE=TEXT SIZE=4 MAXLENGTH=4></td>
                                                                            <td></td>
                                                                            <td><INPUT TYPE="button" class="buttonHorisontal" NAME="Showcalendarendar" value="Показать" onClick="selectDate()"></td>
                                                                            <td></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><SELECT NAME="month" size="12"
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
                                                                            </SELECT></td>
                                                                            <td><br><INPUT TYPE=BUTTON class="buttonVertical1" NAME="previousMonth" onClick="showPreviousMonth()">
                                                                                </td>
                                                                            <td>
                                                                                <div class="calendarBackgroud">
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
                                                                                                <td><input type="submit" class="day" name="day0" id="0">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day1" id="1">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day2" id="2">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day3" id="3">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day4" id="4">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day5" id="5">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day6" id="6"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><input type="submit" class="day" name="day7" id="7">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day8" id="8">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day9" id="9">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day10" id="10">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day11" id="11">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day12" id="12">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day13" id="13"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><input type="submit" class="day" name="day14" id="14">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day15" id="15">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day16" id="16">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day17" id="17">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day18" id="18">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day19" id="19">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day20" id="20"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><input type="submit" class="day" name="day21" id="21">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day22" id="22">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day23" id="23">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day24" id="24">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day25" id="25">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day26" id="26">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day27" id="27"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><input type="submit" class="day" name="day28" id="28">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day29" id="29">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day30" id="30">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day31" id="31">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day32" id="32">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day33" id="33">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day34" id="34"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><input type="submit" class="day" name="day35" id="35">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day36" id="36">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day37" id="37">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day38" id="38">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day39" id="39">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day40" id="40">
                                                                                                </td>
                                                                                                <td><input type="submit" class="day" name="day41" id="41"><br>
                                                                                                </td>
                                                                                            </tr>
                                                                                    </table>
                                                                                </div>
                                                                            </td>
                                                                            <td><br><INPUT TYPE=BUTTON class="buttonVertical2" NAME="nextMonth" onClick="showNextMonth()"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td></td>
                                                                            <td></td>
                                                                            <td><INPUT TYPE=BUTTON  class="buttonHorisontal" NAME="currentMonth" VALUE="Текущий месяц" onClick="showCurrentMonth()"></td>
                                                                            <td></td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
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
							<td colspan="2"><input type="submit" class="registrationSubmit" value="Зарезервировать" />
							<td>
						</tr>
					</tbody>
				</table>
                             </div>
			</form>
			</td>
		</tr>
	</table>
</body>
</html>
