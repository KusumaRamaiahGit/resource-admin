package controller;
import javax.servlet.http.HttpSession;

public interface ILoginViewController {
	boolean isLogin(String login, String password, HttpSession session);
}
