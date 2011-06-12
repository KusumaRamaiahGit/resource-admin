package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Countable;
import model.Inventarable;
import model.Resource;

import utils.ResourceDAO;

/**
 * Servlet implementation class ResourceEditController
 */
public class ResourceEditController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ResourceEditController()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		Resource r = (Resource)request.getAttribute("resource");
		String newName = (String)request.getAttribute("resourceNameNew");		
		String newInvent = (String)request.getAttribute("resourceInventNumNew");
		Integer newCount = null;
		try
		{
			newCount = Integer.parseInt((String)request.getAttribute("resourceCountNew"));
		}
		catch(NumberFormatException ex)
		{
			request.setAttribute("message", "! New maximal capacity field does not contain a parsable integer");
			
			RequestDispatcher dispatch = request.getRequestDispatcher("resource-edit.jsp");
		    dispatch.forward(request, response);
		}
		
		if (newName != null && !newName.trim().isEmpty())
			r.setResource_name(newName);
		if (newCount != null && newCount > 0)
			((Countable)r).setMaxCapacity(newCount);
		if (newInvent != null && !newName.trim().isEmpty())
			((Inventarable)r).setInvenarno(newInvent);
		
		ResourceDAO.updateResource(r);		
		request.setAttribute("message", "Resource updated!");			   
	}

}
