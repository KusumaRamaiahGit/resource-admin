package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ResourceDAO;
import model.Countable;
import model.Inventarable;
import model.Resource;

/**
 * Servlet implementation class ResourceCreateController
 */
public class ResourceCreateController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ResourceCreateController()
	{
		super();
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		Resource r = new Resource();
		String name = (String)request.getParameter("resourceName");		
		String invent = (String)request.getParameter("inventNum");
		Integer count = null;
		try
		{
			count = Integer.parseInt((String)request.getParameter("maxCapacity"));
		}
		catch(NumberFormatException ex)
		{
			request.setAttribute("message", "! New maximal capacity field does not contain a parsable integer");
			
			RequestDispatcher dispatch = request.getRequestDispatcher("resource-edit.jsp");
		    dispatch.forward(request, response);
		}
		
		if (name != null && !name.trim().isEmpty())
		{

		}
		if (count != null && count > 0)
			((Countable)r).setMaxCapacity(count);
		if (invent != null && !name.trim().isEmpty())
			((Inventarable)r).setInvenarno(invent);
		
		ResourceDAO.addResource(r);	
		request.setAttribute("message", "Resource updated!");	

	}

}


