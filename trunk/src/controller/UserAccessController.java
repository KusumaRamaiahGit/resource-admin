package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.ResponseWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ErrorMessage;
import utils.ClientDAO;
import model.Client;

/**
 * @author OKupriianova
 * Servlet implementation class UserEditController
 */
public class UserAccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccessController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	String[] registering = request.getParameterValues("client_id");

	for (String idStr : registering)		       
	{
		try
		{
			Client client = ClientDAO.getClientById(Long.parseLong(idStr));
			client.setRegistered(true);
			ClientDAO.updateClient(client);
			PrintWriter out = response.getWriter();				
			out.print("Client with id" +idStr + "("+ClientDAO.getClientById(Long.parseLong(idStr))+") registering successfully\n");
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", new ErrorMessage("Error in registering user",ErrorMessage.CUSTOM, "cannot register user with id="+idStr));			
			RequestDispatcher dispatch = request.getRequestDispatcher("error");
			dispatch.forward(request, response);
		}
	}

	}
}
