/**
 * 
 */
package controller;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import utils.ClientDAO;

import model.Client;


/**
 * @author Administrator
 *
 */
@Stateless

public class LoginService implements ILoginService{
	public boolean createUserSession(String login, String password, HttpSession session) {
		Client u = null;
		u = ClientDAO.getClientByLogin(login); 
		
		if (u==null) {
			return false;
		} else {
			session.setAttribute("User", u);
			return true;
		}
	}
	public boolean getLogin(String login,String password){
		return true;
	}
}
