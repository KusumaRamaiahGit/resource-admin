package controller;

import model.User;

public class UserModelController implements IUserModelController {

	@Override
	public boolean checkUser(String login, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String login, String pass) {
		User u = new User();
		if (u.getLogin().equals(login) && u.getPassword().equals(pass)) {
			return u;
		}
		return null;
	}

	@Override
	public int getRankByID(int userID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRankByLogin(String login) {
		// TODO Auto-generated method stub
		return 0;
	}

}
