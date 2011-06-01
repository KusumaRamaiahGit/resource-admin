/**
 * 
 */
package controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import utils.ClientDAO;
import utils.ResourceDAO;

import model.Client;
import model.Resource;


/**
 * @author Administrator
 *
 */
@Stateless

public class LoginHandler implements ILoginHandler{
	public boolean createUserSession(String login, String password, HttpSession session) {
		Client u = null;
		u = ClientDAO.getClientByLogin(login); 
		
		if (u!=null && u.getPassword().equals(password)) {
			session.setAttribute("User", u);
			List<Resource> resources = ResourceDAO.getAllResources();
			session.setAttribute("resources", resources);
			return true;
		}
		
		return false;
	}
	
}
