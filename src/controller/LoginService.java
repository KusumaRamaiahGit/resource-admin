/**
 * 
 */
package controller;

import javax.servlet.http.HttpSession;

import utils.ClientDAO;

import model.Client;


/**
 * @author Administrator
 *
 */
public class LoginService implements ILoginService{
	public boolean createUserSession(String login, String password, HttpSession session) {
		//Client u = new Client();
		Client u = ClientDAO.getClientByLogin("boss");
		session.setAttribute("User", u);
		return true;
	}
	public boolean getLogin(String login,String password){
		return true;
	}
}
