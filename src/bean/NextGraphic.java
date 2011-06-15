package bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NextGraphic {
	private static int month;
	private static int year;
	public NextGraphic() {
	}
	public NextGraphic(int month1,int year1) {
		month=month1;
		year=year1;
	}
	public static int getMonth() {
		return month;
	}
	public static int getYear() {
		return year;
	}
	public static void setMonth(int month1) {
		month=month1;
	}
	public static void setYear(int year1) {
		year=year1;
	}
	public static void currentMonth() {
		Calendar date=new GregorianCalendar();
		date.setTime(new Date());
		month=date.get(Calendar.MONTH);
		year=date.get(Calendar.YEAR);
	}
	public static void previousMonth() {
		if(month==0) {
			month=11;
			year--;
		}
		else
			month--;
	}
	public static void nextMonth() {
		if(month==11) {
			month=0;
			year++;
		}
		else
			month++;
	}
}
