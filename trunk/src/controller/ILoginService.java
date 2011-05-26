/**
 * 
 */
package controller;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 *
 */
public interface ILoginService {
	 boolean createUserSession(String login, String password, HttpSession session);
	 boolean getLogin(String login,String password);
}
