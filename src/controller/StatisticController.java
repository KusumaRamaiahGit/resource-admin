//author - Martynenko Viktoria
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatisticController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatisticController() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("User") != null) {
			RequestDispatcher dispatch = request
					.getRequestDispatcher("statistic.jsp");
			dispatch.forward(request, response);
		}
	}
}
