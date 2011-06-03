package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;
import utils.ResourceDAO;

/**
 * Servlet implementation class ReserveController
 */
public class ReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*PrintWriter out = response.getWriter();
		out.print(request.getParameter("start_time") );*/
		String day = request.getParameter("day");
		String month = request.getParameter("monthsRadioGroup");
		String year = request.getParameter("year");	
		String res_id = request.getParameter("resourcesRadioGroup");
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		String[] start=start_time.split("-");
		String[] end=start_time.split("-");
		String h_start=start[0];
		String m_start=start[1];
		String h_end=end[0];
		String m_end=end[1];

		GregorianCalendar currDate = new GregorianCalendar();
		int y = currDate.get(Calendar.YEAR) - 1900;
		int m = currDate.get(Calendar.MONTH);
		int d = currDate.get(Calendar.DAY_OF_MONTH);
		int hh_start = currDate.get(Calendar.HOUR_OF_DAY);
		int mm_start = currDate.get(Calendar.MINUTE);
		int hh_end = currDate.get(Calendar.HOUR_OF_DAY);
		int mm_end = currDate.get(Calendar.MINUTE);
		long r_id=1l;
		try {
			r_id=Long.parseLong(res_id);
			y = Integer.parseInt(year) - 1900;
			m = Integer.parseInt(month);
			d = Integer.parseInt(day);
			hh_start=Integer.parseInt(h_start);
			mm_start=Integer.parseInt(m_start);
			hh_end=Integer.parseInt(h_start);
			mm_end=Integer.parseInt(m_start);
		} catch (Exception e) {	
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.print("Parse error");
		}		
		//Resource res=ResourceDAO.getResourceById(r_id);
		Date selectedDate = new Date(y, m, d);
		Date dateOfStart=new Date(y, m, d, hh_start, mm_start);
		Date dateOfEnd=new Date(y, m, d, hh_end, mm_end);
		addReservation(r_id, dateOfStart, dateOfEnd);
		}
	
	public void addReservation(long res_id,Date date_start,Date date_end){
		new ReserveHandler().addReservation(res_id, date_start, date_end);
	}

}
