package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ErrorMessage;

import model.Admin;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if ((request.getSession().getAttribute("User") != null) && (request.getSession().getAttribute("User") instanceof Admin)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/admin.jsp");
			dispatch.forward(request, response);
		} else {
			
			request.setAttribute("errorMessage", new ErrorMessage("Admin Panel : access denied", ErrorMessage.ACCESS_DENIED, "You can't access this page."));
			
			RequestDispatcher dispatch = request.getRequestDispatcher("error");
			dispatch.forward(request, response);
		}
	}

}
