/**
 * 
 * @author OKupriianova
 *
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;

import utils.ClientDAO;
import utils.RegistrationValidator;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("checkLogin") != null) {
			// we just want to know login's uniqueness

			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet RegistrationController</title>");
			out.println("</head>");
			out.println("<body>");
			out.println(request.getParameter("login"));
			out.println("</body>");
			out.println("</html>");

			if (request.getParameter("login") == null
					|| request.getParameter("login").equals("")) {
			}
			// redirect(request, response,
			// "Error with Login Name! Try to register once again");
			else {
				String loginString = request.getParameter("login").toString()
						.trim();
				if (ClientDAO.getClientByLogin(loginString) == null) {
					// if unique
					request.setAttribute("uniqueLogin", true);
				} else {// if the user with such login exists
					request.setAttribute("uniqueLogin", false);
				}
			}
			// request.setAttribute("login", s);
			RequestDispatcher rd = request.getRequestDispatcher("regg.jsp");
			rd.forward(request, response);

		} else
		// we want to register a new user
		{
			if (request.getParameter("login") == null)
				redirect(request, response,
						"Error with Login Name! Try to register once again");
			String loginString = request.getParameter("login").toString()
					.trim();
			if (request.getParameter("pass1") == null)
				redirect(request, response,
						"Error with password! Try to register once again");
			String pass1String = request.getParameter("pass1").toString()
					.trim();
			if (request.getParameter("pass2") == null)
				redirect(request, response,
						"Error with password confirmation! Try to register once again");
			String pass2String = request.getParameter("pass2").toString()
					.trim();
			if (request.getParameter("email") == null)
				redirect(request, response,
						"Error with email! Try to register once again");
			String emailString = request.getParameter("email").toString()
					.trim();
			if (request.getParameter("rating") == null)
				redirect(request, response,
						"Error with rating! Try to register once again");
			String ratingString = request.getParameter("rating").toString();
			if (request.getParameter("location") == null)
				redirect(request, response,
						"Error with location! Try to register once again");
			String locationString = request.getParameter("location").toString();

			if (RegistrationValidator.Validate(loginString, pass1String,
					pass2String, emailString)) {

				Client regClient = new Client(loginString, pass1String,
						Client.RATINGS.valueOf(ratingString), emailString,
						Client.LOCATIONS.valueOf(locationString));
				// по умолчанию регистрация не подтверждена ПЕРЕДЕЛАТЬ!!!
				ClientDAO.addClient(regClient);
				
				
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet RegistrationController</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Registration completed successfully");
				out.println("</body>");
				out.println("</html>");
			} else {
				redirect(request, response,
						"You have put wrong data! Try to register once again");
			}
		}
		/*
		 * PrintWriter out = response.getWriter();
		 * 
		 * 
		 * out.println("<html>"); out.println("<head>");
		 * out.println("<title>Servlet RegistrationController</title>");
		 * out.println("</head>"); out.println("<body>");
		 * out.println("<h1>Servlet NewServlet at " + request.getContextPath ()
		 * + "</h1>");
		 * 
		 * out.println("1 submit  "+request.getParameter("checkLogin"));
		 * out.println("2 submit  "+request.getParameter("register"));
		 * 
		 * out.println("</body>"); out.println("</html>");
		 */

	}

	private void redirect(HttpServletRequest request,
			HttpServletResponse response, String errormsg) throws IOException {
		request.setAttribute("errormsg", errormsg);
		response.sendRedirect("registerError.jsp");

	}

}
