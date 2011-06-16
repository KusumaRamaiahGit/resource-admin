package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GraphicResourcesMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GraphicResourcesMonth() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int month=Integer.parseInt(request.getSession().getAttribute("someMonth").toString());
		int year=Integer.parseInt(request.getSession().getAttribute("someYear").toString());
		Diagram d=new Diagram(month,year);
		d.drawAllResourcesForMonth(request,response);
	}
}
