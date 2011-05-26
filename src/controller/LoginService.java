/**
 * 
 */
package controller;

import javax.servlet.http.HttpSession;

import model.User;


/**
 * @author Administrator
 *
 */
public class LoginService implements ILoginService{
	public boolean createUserSession(String login, String password, HttpSession session) {
		User u=new User();
		session.setAttribute("User", u);
    // TODO Auto-generated method stub
		return true;
	}
	public boolean getLogin(String login,String password){
		return true;
	}
}
