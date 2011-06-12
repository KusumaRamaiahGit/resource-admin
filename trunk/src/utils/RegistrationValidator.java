package utils;
/**
 * 
 * @author OKupriianova
 *
 */
public class RegistrationValidator {
	public static boolean Validate(String login, String pass1, String pass2, String email)
	{
		return checkPasswords(pass1, pass2) && checkEmail(email);
	}
	public static boolean checkPasswords(String pass1, String pass2)
	{		
		return (pass1.equals(pass2) && pass1.length()>=4 && pass1.length()<=10);
	}
	public static boolean checkEmail(String email)
	{
		return email.matches("[a-zA-Z\\-_]+@[A-Za-z0-9]+\\.[a-zA-Z]{2,4}");
	}
}
