package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class LoginViewController
 */
public class LoginViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   synchronized (this) {
    	   if(isLogin(request.getParameter("login"), request.getParameter("password"))==true){
    	    HttpSession session=request.getSession();
    	    RequestDispatcher dispatch = request.getRequestDispatcher("resourceadmin.jsp");
    	    request.setAttribute("loggedUser", new User());
    	    request.setAttribute("HttpSession", session);
    	    dispatch.forward(request, response);
    	   }
    	   else{
    	    RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
    	    dispatch.forward(request, response);
    	   }
//		User u = login (request.getParameter("login"), request.getParameter("password"));
//		if ( u != null) {
//			RequestDispatcher dispatch = request.getRequestDispatcher("resourceadmin.jsp");
//			//request.setAttribute("login", u.getLogin());
//         request.setAttribute("loggedUser", new User());
//			dispatch.forward(request, response);
//		} else {
//			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
//			request.setAttribute("errorType", 1);
//			dispatch.forward(request, response);
//		}
	}
 

}

public User login(String login, String password) {
		return new UserModelController().getUser(login, password);
	}
	
	public boolean isLogin(String login, String password){
		return new LoginService().getLogin(login, password);
	}
