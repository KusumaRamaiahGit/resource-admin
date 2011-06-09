package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author smihaylenko
 */

@Entity
@Table(name = "CLIENT")
public class Admin extends Client implements Serializable{

	private static final long serialVersionUID = 4027253435517096650L;
	
	public Admin() {
	}
	
	public Admin(String login, String password, RATINGS rating, String contact,LOCATIONS location) {
		super(login, password, rating, contact, location);
	}
	
}
