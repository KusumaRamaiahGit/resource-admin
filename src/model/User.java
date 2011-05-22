package model;

public class User {
	int id;
	String login;
	String password; // later md5 hash;
	public User() {
		this.id = 1;
		this.login = "admin";
		this.password = "qwerty";
	}

}
