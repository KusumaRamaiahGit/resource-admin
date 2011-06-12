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
		
		Long r_id=Long.parseLong(request.getParameter("year").trim());
		int y = Integer.parseInt(request.getParameter("year").trim()) - 1900;
		int m = Integer.parseInt(request.getParameter("month").trim());
		int d = Integer.parseInt(request.getParameter("day").trim());
		int hh_start=Integer.parseInt(request.getParameter("start_time").trim().split(":")[0]);
		int mm_start=Integer.parseInt(request.getParameter("start_time").trim().split(":")[1]);
		int hh_end=Integer.parseInt(request.getParameter("end_time").trim().split(":")[0]);
		int mm_end=Integer.parseInt(request.getParameter("end_time").trim().split(":")[1]);
		
		
		PrintWriter out = response.getWriter();
		out.print(" "+hh_start+":"+mm_start+" - "+hh_end+":"+mm_end+" {"+d+"."+m+"."+y+"}");
		
		//Date selectedDate = new Date(y, m, d);
//		Date dateOfStart=new Date(y, m, d, hh_start, mm_start);
		
//		Date dateOfEnd=new Date(y, m, d, hh_end, mm_end);
//		addReservation(r_id, dateOfStart, dateOfEnd);
		}
	
	public void addReservation(long res_id,Date date_start,Date date_end){
		new ReserveHandler().addReservation(res_id, date_start, date_end);
	}

}
