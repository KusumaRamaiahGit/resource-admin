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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ErrorMessage;

import model.Admin;
import model.Client;

import utils.ClientDAO;
import utils.RegistrationValidator;
import utils.EmailSender;

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
		response.setContentType("text/html;charset=UTF-8");
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
				redirect(request, response, ErrorMessage.CUSTOM, "Error with Login Name! Try to register once again");
			}			
			else {
				String loginString = request.getParameter("login").toString()
						.trim();
				if (ClientDAO.getClientByLogin(loginString) == null) {
					// if unique
					request.setAttribute("uniqueLogin", true);
				} else {// if the user with such login exists
					request.setAttribute("uniqueLogin", false);
				}
				request.setAttribute("login", loginString);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/registration.jsp");
			rd.forward(request, response);

		} else
		// we want to register a new user
		{
			
			if (request.getParameter("login") == null)
				{redirect(request,response, ErrorMessage.ACCESS_DENIED, "You haven't specified the login.");return;}			
			String loginString = request.getParameter("login").toString()
					.trim();
			if (request.getParameter("pass1") == null)				
				{redirect(request, response, ErrorMessage.ACCESS_DENIED, "You haven't specified the password. Please, try to register once again");return;}
			String pass1String = request.getParameter("pass1").toString()
					.trim();
			if (request.getParameter("pass2") == null)
				{redirect(request, response, ErrorMessage.ACCESS_DENIED, "You haven't specified the password confirmation. Please, try to register once again");return;}
			String pass2String = request.getParameter("pass2").toString()
					.trim();
			if (request.getParameter("email") == null)
				{redirect(request, response, ErrorMessage.ACCESS_DENIED, "You haven't specified the email. Please, try to register once again");return;}
			String emailString = request.getParameter("email").toString()
					.trim();
			if (request.getParameter("rating") == null)
				{redirect(request, response, ErrorMessage.ACCESS_DENIED, "You haven't specified the rating. Please, try to register once again");return;}
			String ratingString = request.getParameter("rating").toString();			
			
		/*	if (request.getParameter("location") == null)
				redirect(request, response,
						"Error with location! Try to register once again");*/
			//String locationString = request.getParameter("location").toString();

			if (RegistrationValidator.Validate(loginString, pass1String,
					pass2String, emailString)) 
			{
				Client regClient;
				if (request.getSession().getAttribute("User")instanceof Admin)
					regClient = new Admin(loginString, pass1String, Client.RATINGS.valueOf(ratingString), emailString);
				else
					regClient = new Client(loginString, pass1String, Client.RATINGS.valueOf(ratingString), emailString);
				
				if (request.getSession().getAttribute("User") instanceof Admin)
					regClient.setRegistered(true);//in constructor false is default
				
				ClientDAO.addClient(regClient);			
				//change this sendings
				try{
						EmailSender.send("Регистрация в системе управления ресурсами", "Поздравляем, вы зарегистрированы\n Имя:"+loginString, regClient.getContact());
						PrintWriter out = response.getWriter();
						out.println("<html>");
						out.println("<head>");
						out.println("<title>Servlet RegistrationController</title>");
						out.println("</head>");
						out.println("<body>");
						out.println("registration success");						
						out.println("</body>");
						out.println("</html>");
						
					}			
				catch (Exception io)
				{
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Servlet RegistrationController</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("Properties not found " + io.getMessage());
					io.printStackTrace();
					out.println("</body>");
					out.println("</html>");
				}		
				
			}
			else {
				redirect(request, response, ErrorMessage.CUSTOM,
						"You have put wrong data! Try to register once again");				
			}
		}
	}

	private void redirect(HttpServletRequest request,
			HttpServletResponse response, int errorCode, String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", new ErrorMessage("Error in registration", errorCode, errorMessage));			
		RequestDispatcher dispatch = request.getRequestDispatcher("error");
		dispatch.forward(request, response);

	}
	

}