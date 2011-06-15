package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReservationDAO;

import model.Client;

import error.ErrorMessage;

/**
 * Servlet implementation class UserReservationsController
 */
public class UserReservationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReservationsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client user = (Client) request.getSession().getAttribute("User");
		if (user != null) {
			request.setAttribute("reservationsList", ReservationDAO.getReservationAllAfterNowByClient(user));
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/reservationsList.jsp");
			dispatch.forward(request, response);
		} else {
			request.setAttribute("errorMessage", new ErrorMessage("Reservations List  : access denied", ErrorMessage.ACCESS_DENIED, "You can't access this page.\nLog in first."));	
			RequestDispatcher dispatch = request.getRequestDispatcher("error");
			dispatch.forward(request, response);
		}
	}

}
