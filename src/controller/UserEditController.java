package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.context.ResponseWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ClientDAO;
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
			String passStr=request.getParameter("client_pasword");
			String contactStr=request.getParameter("client_contact");		
			
			Client editingClient = ClientDAO.getClientById(Long.parseLong(request.getParameter("client_id")));
			if (!editingClient.getLogin().equals(loginStr))
				editingClient.setLogin(loginStr);
			if (!editingClient.getPassword().equals(passStr))
				editingClient.setPassword(passStr);
			if (!editingClient.getContact().equals(contactStr))
				editingClient.setContact(contactStr);
			if (!editingClient.getRegistered() && request.getParameter("authorized")!=null )
				editingClient.setRegistered(true);
			PrintWriter out=response.getWriter();
			out.print("Editing completed successfully!");
		}
		/*
		
		PrintWriter out = response.getWriter();
		out.println("button= "+request.getParameter("showFields"));
		out.println("client= "+request.getParameter("client_id"));*/
	}

}
