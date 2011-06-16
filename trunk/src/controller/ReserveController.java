package controller;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Client;
//import model.Resource;
//import utils.ResourceDAO;

/**
 * Servlet implementation class ReserveController
 * @author EDudnik
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
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		synchronized (session) {
			//String res_id=request.getParameter("resourceID");
			//String year=request.getParameter("year");
			//String month=request.getParameter("month");
			//String day=request.getParameter("day");
			Client client=(Client)session.getAttribute("User");
			Long r_id=Long.parseLong(request.getParameter("resourceID").trim());
			int y = Integer.parseInt(request.getParameter("year").trim());
			int m = Integer.parseInt(request.getParameter("month").trim());
			int d = Integer.parseInt(request.getParameter("day").trim());
			int hh_start=Integer.parseInt(request.getParameter("start_time").trim().split(":")[0]);
			int mm_start=Integer.parseInt(request.getParameter("start_time").trim().split(":")[1]);
			int hh_end=Integer.parseInt(request.getParameter("end_time").trim().split(":")[0]);
			int mm_end=Integer.parseInt(request.getParameter("end_time").trim().split(":")[1]);
			
			
			PrintWriter out = response.getWriter();
			//out.print(" "+hh_start+":"+mm_start+" - "+hh_end+":"+mm_end+" {"+d+"."+m+"."+y+"}");
			
			//Date selectedDate = new Date(y, m, d);
	//		Date dateOfStart=new Date(y, m, d, hh_start, mm_start);
			GregorianCalendar dateOfStart,dateOfEnd;
	//		Date dateOfEnd=new Date(y, m, d, hh_end, mm_end);
	//		addReservation(r_id, dateOfStart, dateOfEnd);
			dateOfStart=new GregorianCalendar(y, m, d, hh_start, mm_start);
			dateOfEnd=new GregorianCalendar(y, m, d, hh_end, mm_end);
			if(addReservation(r_id, dateOfStart, dateOfEnd, client, out)==true){
				RequestDispatcher dispatch = request.getRequestDispatcher("calendar.jsp");
				dispatch.forward(request, response);
			}
			else{
				//out.print("input type=\"hidden\" name=\"day\"value="+day+"/>");
				//out.print("<input type=\"hidden\" name=\"monthsRadioGroup\" value="+month+"/>");
				//out.print("<input type=\"hidden\" name=\"year\" value="+year+"/>");
				//out.print("<input type=\"hidden\" name=\"resourcesRadioGroup\" value="+res_id+"/>");
				RequestDispatcher dispatch = request.getRequestDispatcher("calendar.jsp");
				dispatch.forward(request, response);
			}	
			//RequestDispatcher dispatch = request.getRequestDispatcher("calendar.jsp");
			//dispatch.forward(request, response);
		}
	}
	
	public boolean addReservation(long res_id,GregorianCalendar dateOfStart,GregorianCalendar dateOfEnd, Client client,PrintWriter out){
		return new ReserveHandler().addReservation(res_id, dateOfStart, dateOfEnd, client, out);
	}

}
