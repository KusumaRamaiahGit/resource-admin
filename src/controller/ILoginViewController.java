package controller;
import model.User;

public interface ILoginViewController {
	User login(String login, String password);
	boolean isLogin(String login, String password);
}
