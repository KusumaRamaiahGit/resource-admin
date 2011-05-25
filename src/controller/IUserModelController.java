package controller;
import model.User;
/**
* @author OKupriianova
*/
public interface IUserModelController {
	public boolean checkUser(String login, String pass);//��������� ������������� ������������ �� ������ � ������
    public User getUser(String login, String pass);//�������� ������ User �� ������ � ������
    public int getRankByID(int userID);//�������� ���� ������������ �� id
    public int getRankByLogin(String login);//�������� ���� ������������ �� ������

}
