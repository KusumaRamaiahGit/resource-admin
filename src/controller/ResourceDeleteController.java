package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResourceDAO;

import model.Resource;

/**
 * Servlet implementation class ResourceDeleteController
 */
public class ResourceDeleteController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ResourceDeleteController()
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
		
		ResourceDAO.deleteResource(r);
		
		request.setAttribute("message", "Resource deleted!");
	}

}
