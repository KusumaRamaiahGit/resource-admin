package controller;
import model.User;

public interface ILoginViewController {
	User login(String login, String password);
}
