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
 * @author SMikhailenko, OKupriianova
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
			StringBuffer builder=new StringBuffer();
			builder.append("Ваша учетная запись авторизирована.\n");
			/*String loginStr=request.getParameter("client_login");
			String passStr=request.getParameter("client_password");				
			String ratingStr=request.getParameter("client_rating");
			
			if (!client.getLogin().equals(loginStr))
            {
                    builder.append("\nСтарый логин : "+client.getLogin()+"; новый логин :"+loginStr);
                    client.setLogin(loginStr);
            }                               
		    if (!client.getPassword().equals(passStr))
		    {
		            builder.append("\nСтарый пароль : "+client.getPassword()+"; новый пароль :"+passStr);
		            client.setPassword(passStr);
		    }
		    
		    if (!client.getRating().equals(Client.RATINGS.valueOf(ratingStr)))
		    {
		            builder.append("\nСтарый рейтинг : "+client.getRating()+"; новый рейтинг :"+ratingStr);
		            client.setRating(Client.RATINGS.valueOf(ratingStr));                             
		    }*/
			builder.append("Теперь вы можете войти в систему.");
			
			ClientDAO.updateClient(client);
			EmailSender.send("Авторизация учетной записи в системе управления ресурсами", builder.toString(), client.getContact());
			PrintWriter out = response.getWriter();				
			out.print("Client with id" +idStr + "("+ClientDAO.getClientById(Long.parseLong(idStr))+") registered successfully\n");
		}
		catch(IOException io)
		{
			request.setAttribute("errorMessage", new ErrorMessage("Error in sending email to user",ErrorMessage.CUSTOM, "cannot send email to user with id="+idStr));			
			RequestDispatcher dispatch = request.getRequestDispatcher("error");
			dispatch.forward(request, response);
			return;				
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", new ErrorMessage("Error in registering user",ErrorMessage.CUSTOM, "cannot register user with id="+idStr));			
			RequestDispatcher dispatch = request.getRequestDispatcher("error");
			dispatch.forward(request, response);
			return;
		}
		
	}
	}
	
}
