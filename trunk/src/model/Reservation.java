package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.NamedNativeQueries;
import javax.persistence.EntityResult;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "Get_Reservations_Count", query = "select count(*) from Reservation"),

		@NamedQuery(name = "Count_Reservation_in_Time", 
				query = "select count(*) "
				+ "FROM Reservation "
				+ "WHERE "
				+ "resource_fk = ? "
				+ "and "
				+ "(start_time between ? and ? "
				+ "or end_time "
				+ "between ? and ?)") })
@SqlResultSetMapping(name = "Reservation", entities = @EntityResult(entityClass = Reservation.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "FindReservation_ALL_of_Day_by_Resource", 
				query = "select * "
				+ "FROM RESERVATION "
				+ "WHERE "
				+ "DAYOFYEAR(start_time) = DAYOFYEAR(?) "
				+ "and "
				+ "resource_fk = ? " 
				+ "ORDER BY start_time;",
				resultSetMapping = "Reservation"),
		@NamedNativeQuery(name = "FindReservation_ALL_of_Day_by_Resource_and_Client", 
				query = "SELECT * "
				+ "FROM RESERVATION  "
				+ "WHERE "
				+ "DAYOFYEAR(start_time) = DAYOFYEAR(?) "
				+ "and "
				+ "YEAR(start_time)=YEAR(?)"
				+ "and  "
				+ "resource_fk = ? "
				+ "and client_fk=?  " 
				+ "ORDER BY start_time;", 
				resultSetMapping = "Reservation"),				
		@NamedNativeQuery(name = "FindReservation_ALL_in_Time", 
				query = "select * "
				+ "FROM RESERVATION "
				+ "WHERE "
				+ "resource_fk = :resource_fk "
				+ "and "
				+ "((start_time between :start_time and :end_time "
				+ "or end_time "
				+ "between :start_time and :end_time) " 
				+ "or "
				+ "(:start_time between start_time and end_time " 
				+ "or :end_time between start_time and end_time) )"
				+ "ORDER BY start_time;", 
				resultSetMapping = "Reservation"),
		@NamedNativeQuery(name = "FindReservation_by_Resource", //query for statistic
				query = "SELECT * "
				+ "FROM RESERVATION  "
				+ "WHERE "
				+ "resource_fk = ? " 
				+ "ORDER BY start_time,end_time;", 
				resultSetMapping = "Reservation"),
		@NamedNativeQuery(name = "FindReservation_by_Resource_and_Client", //query for statistic
				query = "SELECT * "
				+ "FROM RESERVATION  "
				+ "WHERE "
				+ "resource_fk = ? "
				+ "and "
				+ "client_fk = ? " 
				+ "ORDER BY start_time,end_time;", 
				resultSetMapping = "Reservation")})	
@Table(name = "RESERVATION")
public class Reservation implements Serializable {
	private static final long serialVersionUID = -388790269627716780L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reservation_id", nullable = false, columnDefinition = "integer")
	private Long reservation_id;
	
	@Column(name = "start_time", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar start_time;
	
	@Column(name = "end_time", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar end_time;

	public Reservation() {
	}

	public Reservation(Resource resource, Calendar start_time, Calendar end_time, Client client) {
		this.resource = resource;
		this.start_time = start_time;
		this.end_time = end_time;
		this.client = client;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resource_fk")
	private Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "client_fk")
	private Client client;

	public Calendar getEnd_time() {
		return end_time;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Calendar getStart_time() {
		return start_time;
	}

	public Client getClient() {
		return client;
	}

	public void setEnd_time(Calendar end_time) {
		this.end_time = end_time;
	}

	public void setStart_time(Calendar start_time) {
		this.start_time = start_time;
	}

	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Reservation res = (Reservation) o;
		if (!reservation_id.equals(res.reservation_id)) {
			return false;
		}
		if (!start_time.equals(res.start_time)) {
			return false;
		}
		if (!end_time.equals(res.end_time)) {
			return false;
		}
		if (!client.equals(res.client)) {
			return false;
		}
		if (!resource.equals(res.resource)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 21 * reservation_id.hashCode() + 3 * start_time.hashCode() + 7 * end_time.hashCode() + 17 * client.hashCode() + 19 * resource.hashCode();
		return result;
	}

	@Override
	public String toString() {
		DateFormat formatter = DateFormat.getTimeInstance();
		return "{"+client+"} "+formatter.format(getStart_time().getTime()) + " - "
				+ formatter.format(getEnd_time().getTime());
	}

}
