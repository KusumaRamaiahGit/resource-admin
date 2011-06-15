package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import bean.NextGraphic;

public class GraphicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	

		int month=Integer.parseInt(request.getSession().getAttribute( "someMonth").toString());
		int year=Integer.parseInt(request.getSession().getAttribute( "someYear").toString());
		String graphic=request.getParameter("nextGraphic").toString();
		NextGraphic.setMonth(month);
		NextGraphic.setYear(year);		
		if(graphic.equalsIgnoreCase("<-"))
			NextGraphic.previousMonth();
		else {
			if(graphic.equalsIgnoreCase("->"))
				NextGraphic.nextMonth();
			else
				NextGraphic.currentMonth();
		}
		request.getSession().setAttribute( "someMonth", NextGraphic.getMonth() );
		request.getSession().setAttribute( "someYear", NextGraphic.getYear() );
		if (request.getSession().getAttribute("User")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("statisticMonths.jsp");
			dispatch.forward(request, response);
		}			
	}

}
