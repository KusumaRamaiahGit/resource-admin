package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;

/**
 * @author smihaylenko
 */

@Entity
@Table(name = "CLIENT")
@NamedQuery(name = "getClientByLogin", query = "from Client c where c.login = :login")
public class Client implements Serializable {
	private static final long serialVersionUID = 4895898373317713855L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id", nullable = false, columnDefinition = "integer")
	private Long client_id;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "rating", nullable = false, length = 20)
	private RATINGS rating;

	public enum RATINGS {
		HIGH, MIDDLE, LOW
	}

	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "registered", nullable = false)
	private Boolean registered;

	@Enumerated(EnumType.STRING)
	@Column(name = "location", nullable = false, length = 20)
	private LOCATIONS location;

	public enum LOCATIONS {
		KYIV, ODESSA
	}

	public Client() {
	}

	public Client(String login, String password, RATINGS rating,
			String contact, LOCATIONS location) {
		this.login = login;
		this.password = password;
		this.rating = rating;
		this.contact = contact;
		this.location = location;
		this.registered = false;
	}

	private Reservation reservation;

	@OneToOne(mappedBy = "client")
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Long getClient_id() {
		return client_id;
	}

	public String getContact() {
		return contact;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public String getLogin() {
		return login;
	}

	public RATINGS getRating() {
		return rating;
	}

	public String getPassword() {
		return password;
	}

	public LOCATIONS getLocation() {
		return location;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setRating(RATINGS rating) {
		this.rating = rating;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public void setLocation(LOCATIONS location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Client client = (Client) o;
		if (!login.equals(client.login))
			return false;
		if (!password.equals(client.password))
			return false;
		if (!rating.equals(client.rating))
			return false;
		if (!contact.equals(client.contact))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = 9 * login.hashCode() + 3 * password.hashCode() + 7
				* rating.hashCode() + 13 * contact.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return login;
	}

}
