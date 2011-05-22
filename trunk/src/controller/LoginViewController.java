package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class LoginViewController
 */
public class LoginViewController extends HttpServlet implements ILoginViewController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = login (request.getParameter("login"), request.getParameter("password"));
		if ( u != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("resourceadmin.jsp");
			//request.setAttribute("login", u.getLogin());
         request.setAttribute("loggedUser", new User());
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			request.setAttribute("errorType", 1);
			dispatch.forward(request, response);
		}
	}

	@Override
	public User login(String login, String password) {
		return new UserModelController().getUser(login, password);
	}

}
