package controller;

import model.User;

public interface IUserDAO {
	boolean isExist(String login);
	User getUserByLogin(String login);
}
