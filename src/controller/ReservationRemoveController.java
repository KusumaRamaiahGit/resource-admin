package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReservationDAO;

/**
 * Servlet implementation class ReservationRemoveController
 */
public class ReservationRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String[] reservations = request.getParameterValues("reservations");
		
		for (int i = 0; i < reservations.length; i++) {
			Long curResId = null;
			try {
				curResId = Long.parseLong(reservations[i]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (curResId != null) {
				ReservationDAO.deleteReservationById(curResId);
			}
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("UserReservations");
		dispatch.forward(request, response);
	}

}
