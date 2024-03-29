package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ClientDAO;
import utils.EmailSender;
import model.Client;

/**
 * @author OKupriianova
 * Servlet implementation class UserEditController
 */
public class UserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("showFields")!=null)
		{//need to show fields
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/editUserPage.jsp");
			//request.setAttribute("client_id", request.getParameter("client_id"));
			dispatcher.forward(request, response);
		}
		if (request.getParameter("edit")!=null)
		{//need to edit user			
			String loginStr=request.getParameter("client_login");
			String passStr=request.getParameter("client_password");
			String contactStr=request.getParameter("client_contact");		
			String ratingStr=request.getParameter("client_rating");
									
			Client editingClient = ClientDAO.getClientById(Long.parseLong(request.getParameter("client_id")));
			String secondReceiver=editingClient.getContact();
			boolean sendSecond=false;
			
			StringBuffer builder=new StringBuffer();
			builder.append("���������: \n");
            
            if (!editingClient.getLogin().equals(loginStr))
                    {
                            builder.append("\n������ ����� : "+editingClient.getLogin()+"; ����� ����� :"+loginStr);
                            editingClient.setLogin(loginStr);
                    }                               
            if (!editingClient.getPassword().equals(passStr))
            {
                    builder.append("\n������ ������ : "+editingClient.getPassword()+"; ����� ������ :"+passStr);
                    editingClient.setPassword(passStr);
            }
            if (!editingClient.getContact().equals(contactStr))
            {
                    builder.append("\n������ email : "+editingClient.getContact()+"; ����� email :"+contactStr);
                    editingClient.setContact(contactStr);
                    sendSecond=true;
            }
            if (!editingClient.getRating().equals(Client.RATINGS.valueOf(ratingStr)))
            {
                    builder.append("\n������ ������� : "+editingClient.getRating()+"; ����� ������� :"+ratingStr);
                    editingClient.setRating(Client.RATINGS.valueOf(ratingStr));                             
            }
            if (!editingClient.getRegistered() && request.getParameter("authorized")!=null )
            {
                    builder.append("\n����������� ������������. ������ �� ������ ����� � �������");
                    editingClient.setRegistered(new Boolean(true));
            }
            
            ClientDAO.updateClient(editingClient);
            EmailSender.send("��������� ������� ������", builder.toString() , editingClient.getContact());
            if (sendSecond)
                    EmailSender.send("��������� ������� ������", builder.toString() , secondReceiver);
            PrintWriter out=response.getWriter();
            out.print("Editing completed successfully!");
		}
		/*
		
		PrintWriter out = response.getWriter();
		out.println("button= "+request.getParameter("showFields"));
		out.println("client= "+request.getParameter("client_id"));*/
	}

}
