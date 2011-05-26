package controller;
import javax.servlet.http.HttpSession;

import model.User;

public interface ILoginViewController {
	User login(String login, String password);
	boolean isLogin(String login, String password, HttpSession session);
}
