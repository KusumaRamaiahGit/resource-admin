package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ErrorMessage;

import utils.ClientDAO;
import utils.EmailSender;
import model.Client;
/**
 * @author OKupriianova
 * Servlet implementation class UserDeleteController
 */
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String[] deleting = request.getParameterValues("client_id");
	
		for (String idStr : deleting)		       
		{
			try
			{
				Client tmp=ClientDAO.getClientById(Long.parseLong(idStr));
				String login=tmp.getLogin();
				ClientDAO.deleteClientById(Long.parseLong(idStr));
				EmailSender.send("Удаление учетной записи", "Ваша учетная запись ("+login+") в системе управления ресурсами была удалена.", tmp.getContact());
				PrintWriter out = response.getWriter();				
				out.print("Client with id" +idStr +"("+login+ ") deleted successfully\n");
			}
			catch (IOException io)
			{
				request.setAttribute("errorMessage", new ErrorMessage("Error in sending message to deleted user",ErrorMessage.CUSTOM, "cannot send email to deleted user with id="+idStr));			
				RequestDispatcher dispatch = request.getRequestDispatcher("error");
				dispatch.forward(request, response);
			}
			catch (Exception ex)
			{
				request.setAttribute("errorMessage", new ErrorMessage("Error in deteing user",ErrorMessage.CUSTOM, "cannot delete user with id="+idStr));			
				RequestDispatcher dispatch = request.getRequestDispatcher("error");
				dispatch.forward(request, response);
			}
		}
	}

}