package controller;

import javax.servlet.http.HttpSession;

import model.User;

public class LoginService implements ILoginService {

	@Override
	public boolean createUserSession(String login, String password,
			HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}
   public boolean getLogin(String login,String password){
		return true;
	}


}
