package controller;

import java.awt.event.ActionListener;
import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Client;


/**
 * Servlet implementation class LoginViewController
 */
public class LoginViewController extends HttpServlet implements ILoginViewController {
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
	    HttpSession session=request.getSession();  
    	synchronized (session) {
    	   if(isLogin(request.getParameter("login"), request.getParameter("password"), session)==true){
    	    RequestDispatcher dispatch = request.getRequestDispatcher("calendar.jsp");
    	    dispatch.forward(request, response);
    	   }
    	   else{
    	    RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
    	    dispatch.forward(request, response);
    	   }
    	}		
	}

	public boolean isLogin(String login, String password, HttpSession session){
		return new LoginService().createUserSession(login, password, session);
	}

}
