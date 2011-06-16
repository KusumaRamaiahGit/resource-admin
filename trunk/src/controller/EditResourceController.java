package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ErrorMessage;

import utils.ResourceDAO;
import model.Inventarable;
import model.Resource;
import model.Countable;

/**
 * Servlet implementation class EditResourceController
 */
public class EditResourceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditResourceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("showFields")!=null)
		{
			//need to show fields
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/editResourcePage.jsp");
			//request.setAttribute("client_id", request.getParameter("client_id"));
			dispatcher.forward(request, response);
		}
		if (request.getParameter("edit")!=null)
		{
			String res_id=request.getParameter("res_id");
			Resource editingRes=ResourceDAO.getResourceById(Long.parseLong(res_id));
			
			if (!editingRes.getResource_name().equals(request.getParameter("res_name")))
					editingRes.setResource_name(request.getParameter("res_name"));
			
			if (editingRes instanceof Countable)
			{
				try 
				{ 
					Integer cap=Integer.parseInt(request.getParameter("res_cap"));
					if (!cap.equals(((Countable)editingRes).getMaxCapacity()))
							((Countable)editingRes).setMaxCapacity(cap);
				}
				catch (NumberFormatException ex)
				{
					request.setAttribute("errorMessage", new ErrorMessage("Resource editing : wrong data", ErrorMessage.CUSTOM, "You have put wrong data."));					
					RequestDispatcher dispatch = request.getRequestDispatcher("error");
					dispatch.forward(request, response);
					return;
				}
			}
			
			if (editingRes instanceof Inventarable)
			{				
				if (!((Inventarable)editingRes).getInvenarno().equals(request.getParameter("res_no")))
					((Inventarable)editingRes).setInvenarno(request.getParameter("res_no"));
			}
			ResourceDAO.updateResource(editingRes);
			
			PrintWriter out=response.getWriter();
			out.println("Ресурс с id="+res_id+" изменен успешно.");
		}
	}

}
