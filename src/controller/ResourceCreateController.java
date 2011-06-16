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
 * Servlet implementation class ResourceCreateController
 */
public class ResourceCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResourceCreateController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String className = (String) request.getParameter("resourceName");
		String maxCapStr = (String) request.getParameter("maxCapacity");
		String inventNumStr = (String) request.getParameter("inventNum");
		String resName = (String) request.getParameter("resName"); 
		
 

		@SuppressWarnings("rawtypes")
		Class resClass = null;

		try {
			resClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object resource = null;

		try {
			resource = resClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if (resource instanceof Resource) {
			((Resource) resource).setResource_name(resName);
			if (resource instanceof Countable) {
				int maxCapacity = 0;
				try {
				maxCapacity = Integer.parseInt(maxCapStr.trim());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				((Countable) resource).setMaxCapacity(maxCapacity);
				
			}
			
			if (resource instanceof Inventarable) {
				((Inventarable) resource).setInvenarno(inventNumStr);
			}

			ResourceDAO.addResource((Resource) resource);
		
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("AdminPanel");
		dispatch.forward(request, response);

	}

}
