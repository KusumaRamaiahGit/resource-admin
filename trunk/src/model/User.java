package model;
import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String login;
	private String password; // later md5 hash;
	public User() {
		this.id = 1;
		this.login = "admin";
		this.password = "qwerty";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return login;
	}

	
}
