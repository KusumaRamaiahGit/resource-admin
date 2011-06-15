package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;

import error.ErrorMessage;

/**
 * @author OKupriianova
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirectAddress="";
		if (request.getParameter("page").equals("add"))
		{
			if (request.getSession().getAttribute("User")!=null && !(request.getSession().getAttribute("User")instanceof Admin))
				{redirect (request, response, ErrorMessage.ACCESS_DENIED, "Sorry, you don't have access to this page");return;}
			else
				redirectAddress="WEB-INF/registration.jsp";			
		}
		else
		if (request.getParameter("page").equals("accept"))
		{
			if (request.getSession().getAttribute("User") instanceof Admin)			
				redirectAddress="WEB-INF/acceptUser.jsp";			
			
			else
			{
				redirect (request, response, ErrorMessage.ACCESS_DENIED, "Sorry, you don't have access to this page");
				return;
			}		
		}
		
		else
		if (request.getParameter("page").equals("delete"))
		{
			if (request.getSession().getAttribute("User") instanceof Admin)			
				redirectAddress="WEB-INF/deleteUser.jsp";			
			
			else
			{
				redirect (request, response, ErrorMessage.ACCESS_DENIED, "Sorry, you don't have access to this page");
				return;
			}				
		}
		if (request.getParameter("page").equals("edit"))
		{
			if (request.getSession().getAttribute("User") instanceof Admin)			
				redirectAddress="WEB-INF/editUser.jsp";			
			
			else
			{
				redirect (request, response, ErrorMessage.ACCESS_DENIED, "Sorry, you don't have access to this page");
				return;
			}
		}
		
		//more if with parameters
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(redirectAddress);
	    dispatch.forward(request, response);

		
	}	
	
	private void redirect(HttpServletRequest request,
			HttpServletResponse response, int errorCode, String errorMessage) throws ServletException, IOException {
		
		request.setAttribute("errorMessage", new ErrorMessage("Error in user administration", errorCode, errorMessage));			
		RequestDispatcher dispatch = request.getRequestDispatcher("error");
		dispatch.forward(request, response);

	}

}
