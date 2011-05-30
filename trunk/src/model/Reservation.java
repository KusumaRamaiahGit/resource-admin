package model;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.NamedNativeQueries;
import javax.persistence.EntityResult;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@SqlResultSetMapping(name = "Reservation", entities = @EntityResult(entityClass = Reservation.class))
@NamedNativeQueries(
{
@NamedNativeQuery(name="FindReservation_ALL",
query="select *"
        + "from RESERVATION r,CLIENT c "
        + "where r.client=c.client_id "
        + "and "
        + "start_date =? "
        + "order by start_time;",
        resultSetMapping="Reservation"),
    }
)
@Table(name= "RESERVATION")
public class Reservation implements Serializable{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name= "reservation_id")
 private Long reservation_id;

  @Column(name= "start_date",nullable=false)
  @Temporal(value=TemporalType.TIMESTAMP)
  private Date  start_date;

  @Column(name= "start_time",nullable=false)
  @Temporal(value=TemporalType.TIME)
  private Date  start_time;

  @Column(name= "end_time",nullable=false)
  @Temporal(value=TemporalType.TIME)
  private Date     end_time;

   public Reservation() {
    }

    public Reservation( Date start_date, Date start_time, Date end_time) {
       // this.resources = resources;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_time = end_time;
        //this.clients = clients;
    }

   private Set<Resource> resources=new HashSet<Resource>(0);
  @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
  @JoinTable(name = "RESERVATION_RESOURCE",
  joinColumns =
  { @JoinColumn(name = "reservation_id") },
    inverseJoinColumns = { @JoinColumn(name = "resource_id") })

     public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

  private Set<Client> clients=new HashSet<Client>(0);
  @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
  @JoinTable(name = "RESERVATION_CLIENT",
  joinColumns =
  { @JoinColumn(name = "reservation_id") },
          inverseJoinColumns = { @JoinColumn(name = "client_id") })

    public Set<Client> getClients() {
        return clients;
    }

     public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    @Id
     public Long getReservation_id() {
        return reservation_id;
    }

    public Date getStart_date() {
        return start_date;
    }


    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }


    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

  @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Reservation res = (Reservation) o;
        if ( !start_date.equals( res.start_date ) ) return false;
        if ( !start_time.equals( res.start_time) ) return false;
        if ( !end_time.equals( res.end_time ) ) return false;
        if ( !clients.equals( res.clients ) ) return false;
        if ( !resources.equals( res.resources ) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
       int result = 9*start_date.hashCode() +17*clients.hashCode()+19*resources.hashCode();
       return result;
    }

    @Override
    public String toString() {
             return getStart_time()+" - "
               + getEnd_time()+" "
               + getClients();
    }
 }
