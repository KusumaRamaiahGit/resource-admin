package utils;
/**
 * 
 * @author OKupriianova
 *
 */
public class RegistrationValidator {
	public static boolean Validate(String login, String pass1, String pass2, String email)
	{
		return checkLoginForUniqueness(login) && checkPasswords(pass1, pass2) && checkEmail(email);
	}
	public static boolean checkPasswords(String pass1, String pass2)
	{		
		return (pass1.equals(pass2) && pass1.length()>=4 && pass1.length()<=10);
	}
	public static boolean checkEmail(String email)
	{
		return email.matches("[a-zA-Z0-9\\.\\-_]+@[A-Za-z0-9]+\\.[a-zA-Z]+");
	}
	public static boolean checkLoginForUniqueness(String login)
	{
		return (ClientDAO.getClientByLogin(login)==null);
	}
}
