/**
 * 
 */
package controller;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 *
 */
public interface ILoginHandler {
	 boolean createUserSession(String login, String password, HttpSession session);
}
