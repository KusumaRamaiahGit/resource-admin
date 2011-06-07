package controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReservationDAO;
import utils.ResourceDAO;

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

		String dayStr = request.getParameter("day");
		String monthStr = request.getParameter("monthsRadioGroup");
		String yearStr = request.getParameter("year");	
		String res_idStr = request.getParameter("resourcesRadioGroup");		

		Long res_id=Long.parseLong(res_idStr);
		int year = Integer.parseInt(yearStr.trim());
		int month = Integer.parseInt(monthStr.trim());
		int day = Integer.parseInt(dayStr.trim());
		 
		Resource res=ResourceDAO.getResourceById(res_id);
		//Date selectedDate = new Date(y, m, d);
		GregorianCalendar calendar=new GregorianCalendar(year, month, day);
		
							
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
		
		List<Reservation> todaysReservations = ReservationDAO.getReservationByDateAndResource(calendar, res);
		request.setAttribute("reservationsList", todaysReservations);
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("resourceName", res.getResource_name());//TODO: bad idea, we can save resource id
		request.setAttribute("resourceID", res_idStr);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("reservations.jsp");
		dispatch.forward(request, response);
	}

}

