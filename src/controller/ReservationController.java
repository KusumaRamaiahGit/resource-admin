package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReservationDAO;

import model.Reservation;

/**
 * @author OKupriianova Servlet implementation class ReservationController
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

		GregorianCalendar currDate = new GregorianCalendar();
		int y = currDate.get(Calendar.YEAR) - 1900;
		int m = currDate.get(Calendar.MONTH);
		int d = currDate.get(Calendar.DAY_OF_MONTH);
		try {
			y = Integer.parseInt(year) - 1900;
			m = Integer.parseInt(month);
			d = Integer.parseInt(day);
		} catch (Exception e) {
		}
		Date selectedDate = new Date(y, m, d);
		List<Reservation> todaysReservations = ReservationDAO.getReservationByDate(selectedDate);
		request.setAttribute("reservationsList", todaysReservations);
		request.setAttribute("year", y + 1900);
		request.setAttribute("month", m + 1);
		request.setAttribute("day", d);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("reservations.jsp");
		dispatch.forward(request, response);
	}

}
