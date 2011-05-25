package controller;
import model.User;
/**
* @author OKupriianova
*/
public interface IUserModelController {
	public boolean checkUser(String login, String pass);//проверить существование пользователя по логину и паролю
    public User getUser(String login, String pass);//получить объект User по логину и паролю
    public int getRankByID(int userID);//получить ранг пользователя по id
    public int getRankByLogin(String login);//получить ранг пользователя по логину

}
