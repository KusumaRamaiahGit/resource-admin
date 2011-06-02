package model;

import java.io.Serializable;
import java.text.DateFormat;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity

@SqlResultSetMapping(name = "Reservation", entities = @EntityResult(entityClass = Reservation.class))
@NamedNativeQueries(
{
@NamedNativeQuery(name="FindReservation_ALL_of_Day_by_Resource",
query="select * "
        + "FROM RESERVATION "
        + "WHERE "
        + "DAYOFYEAR(start_time) = DAYOFYEAR(?) "
        + "and "
        + "resource_fk = ? "
        + "ORDER BY start_time;",
        resultSetMapping="Reservation"),
@NamedNativeQuery(name="FindReservation_ALL_of_Day",
		query="select * "
		        + "FROM RESERVATION "
		        + "WHERE "
		        + "DAYOFYEAR(start_time) = DAYOFYEAR(?) "
		        + "ORDER BY start_time;",
		        resultSetMapping="Reservation"),
    }
)
@Table(name= "RESERVATION")
public class Reservation implements Serializable{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name= "reservation_id", nullable = false, columnDefinition = "integer")
 private Long reservation_id;

  @Column(name= "start_time",nullable=false)
  @Temporal(value=TemporalType.TIMESTAMP)
  private Date  start_time;

  @Column(name= "end_time",nullable=false)
  @Temporal(value=TemporalType.TIMESTAMP)
  private Date     end_time;

   public Reservation() {
    }

    public Reservation( Resource resource, Date start_time, Date end_time) {
        this.resource = resource;
        this.start_time = start_time;
        this.end_time = end_time;
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


  @ManyToMany(targetEntity=Client.class,
 cascade={CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(name = "RESERVATION_CLIENT",
  joinColumns =
  { @JoinColumn(name = "reservation_id") },
    inverseJoinColumns = { @JoinColumn(name = "client_id") })
private Set<Client> clients=new HashSet<Client>(0);
    public Set<Client> getClients() {
        return clients;
    }
     public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
     
    public Date getEnd_time() {
        return end_time;
    }

    public Date  getStart_time() {
        return start_time;
    }

    public void setEnd_time(Date  end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(Date start_time) {
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
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Reservation res = (Reservation) o;
        if ( !start_time.equals( res.start_time) ) return false;
        if ( !end_time.equals( res.end_time ) ) return false;
        if ( !clients.equals( res.clients ) ) return false;
        if ( !resource.equals( res.resource ) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
       int result = 17*clients.hashCode()+19*resource.hashCode();
       return result;
    }

    @Override
    public String toString() {
        DateFormat formatter = DateFormat.getTimeInstance();
             return
             formatter.format(getStart_time()) +" - "
              + formatter.format(getEnd_time())+" "
               + getClients();
    }

 

}

