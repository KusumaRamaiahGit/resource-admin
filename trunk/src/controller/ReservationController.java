package controller;

import java.io.IOException;
//import java.util.GregorianCalendar;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReservationDAO;
import utils.ResourceDAO;

import model.Client;
import model.Reservation;
import model.Resource;

/**
 * @author OKupriianova, IKalashnikov Servlet implementation class
 *         ReservationController
 */
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String dayStr = request.getParameter("day");
		String monthStr = request.getParameter("monthsRadioGroup");
		String yearStr = request.getParameter("year");
		String res_idStr = request.getParameter("resourcesRadioGroup");

		Long res_id = Long.parseLong(res_idStr);
		int year = Integer.parseInt(yearStr.trim());
		int month = Integer.parseInt(monthStr.trim());
		int day = Integer.parseInt(dayStr.trim());

		Resource res = ResourceDAO.getResourceById(res_id);

		GregorianCalendar calendar = new GregorianCalendar(year, month, day);

		List<Reservation> todaysReservations = ReservationDAO
				.getReservationByDateAndResource(calendar, res);

		ListIterator<Reservation> i = todaysReservations.listIterator();
		Map<Client, List<Reservation>> resMap = new HashMap<Client, List<Reservation>>();

		while (i.hasNext()) {
			Reservation bufRes = i.next();
			Client curClient = bufRes.getClient();

			if (!resMap.containsKey(curClient)) {
				resMap.put(curClient, new LinkedList<Reservation>());
			}

			List<Reservation> clientDateList = resMap.get(curClient);
			clientDateList.add(bufRes);
		}

		request.setAttribute("reservationsMap", resMap);

		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("resourceName", res.getResource_name());// TODO:
																		// bad
																		// idea,
																		// we
																		// can
																		// save
																		// resource
																		// id
		request.setAttribute("resourceID", res_idStr);

		RequestDispatcher dispatch = request
				.getRequestDispatcher("WEB-INF/reservations.jsp");
		dispatch.forward(request, response);
	}

}
