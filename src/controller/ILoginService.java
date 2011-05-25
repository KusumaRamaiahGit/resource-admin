package controller;
import javax.servlet.http.HttpSession;

/**
* @author OKupriianova
*/
public interface ILoginService {
	boolean createUserSession(String login, String password, HttpSession session);
   public boolean getLogin(String login,String password);
}
