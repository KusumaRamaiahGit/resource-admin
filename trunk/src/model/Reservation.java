package model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
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
  @Column(name= "reservation_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
 private Long reservation_id;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name= "resource")
  private Resource resource_id;

  @Column(name= "start_date",nullable=false)
  @Temporal(value=TemporalType.DATE)
  private Date  start_date;

  @Column(name= "start_time",nullable=false)
  @Temporal(value=TemporalType.TIME)
  private Date  start_time;

  @Column(name= "end_time",nullable=false)
  @Temporal(value=TemporalType.TIME)
  private Date     end_time;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name= "client")
  private Client   client_id;

    public Reservation() {
    }

    public Reservation( Resource resource_id, Date start_date, Date start_time, Date end_time, Client client_id) {
        this.resource_id = resource_id;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.client_id = client_id;
    }


    public Client getClient_id() {
        return client_id;
    }
    public Date getEnd_time() {
        return end_time;
    }
    public Resource getResource() {
        return resource_id;
    }
    public Date getStart_time() {
        return start_time;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
    public void setResource(Resource resource_id) {
        this.resource_id = resource_id;
    }
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

     public Long getReservation_id() {
        return reservation_id;
    }

    public Date getStart_date() {
        return start_date;
    }


    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setResource_id(Resource resource_id) {
        this.resource_id = resource_id;
    }

    public Resource getResource_id() {
        return resource_id;
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
        if ( !client_id.equals( res.client_id ) ) return false;
        if ( !resource_id.equals( res.resource_id ) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
       int result = 9*start_date.hashCode() +17*client_id.hashCode()+19*resource_id.hashCode();
       return result;
    }

    @Override
    public String toString() {
             return getStart_time()+" - "
               + getEnd_time()+" "
               + getClient_id().getLogin();
    }
    }
