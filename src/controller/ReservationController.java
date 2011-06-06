package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DatePairs;
import utils.ReservationDAO;
import utils.ResourceDAO;

import model.Client;
import model.Reservation;
import model.Resource;
/**
 * @author OKupriianova 
 * Servlet implementation class ReservationController
 */
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String day = request.getParameter("day");
		String month = request.getParameter("monthsRadioGroup");
		String year = request.getParameter("year");	
		String res_id = request.getParameter("resourcesRadioGroup");		

		Long r_id=Long.parseLong(res_id);
		int y = Integer.parseInt(year.trim()) - 1900;
		int m = Integer.parseInt(month.trim());
		int d = Integer.parseInt(day.trim());
		 
		Resource res=ResourceDAO.getResourceById(r_id);
		Date selectedDate = new Date(y, m, d);
		
							
		/*Map<Client, List<DatePairs>> hashMap = new HashMap<Client, List<DatePairs>>();
		
		for (Reservation reservation: todaysReservations)
		{			
			for (Client c:reservation.getClients())
			{		
				List<DatePairs> dp;
				if (hashMap.containsKey(c))
				{
					dp=hashMap.get(c);
					dp.add(new DatePairs(reservation.getStart_time(), reservation.getEnd_time()));			
					hashMap.put(c,dp);
				}
				else
				{
					dp=new LinkedList<DatePairs>();
					dp.add(new DatePairs(reservation.getStart_time(),reservation.getEnd_time()));
					hashMap.put(c,dp);
				}
			}
		}
		
		request.setAttribute("reservationsHashMap", hashMap);
		*/
		List<Reservation> todaysReservations = ReservationDAO.getReservationByDateAndResource(selectedDate, res);
		request.setAttribute("reservationsList", todaysReservations);
		
		request.setAttribute("year", y + 1900);
		request.setAttribute("month", m + 1);
		request.setAttribute("day", d);
		request.setAttribute("resourceName", res.getResource_name());
		
		RequestDispatcher dispatch = request.getRequestDispatcher("reservations.jsp");
		dispatch.forward(request, response);
	}

}
