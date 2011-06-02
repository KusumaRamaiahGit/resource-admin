package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

/**
 * @author smihaylenko
 */
@Entity

@Table(name = "CLIENT")

@NamedQuery(name = "getClientByLogin", query = "from Client c where c.login = :login")

public class Client implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false, columnDefinition = "integer")

    private Long client_id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", nullable = false, length=20)
    private RATINGS rating;

    @Column(name = "contact", nullable = false)
    private String contact;

     public enum RATINGS
    {
        HIGH,
        MIDDLE,
        LOW
    }
  
    public Client()
    {
    }

    public Client(String login, String password, RATINGS rating, String contact)
    {
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.contact = contact;
    }


        @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.LAZY,
        mappedBy = "clients",
        targetEntity = Reservation.class
    )
    private Set<Reservation> reservations=new HashSet<Reservation>(0);
        
    public Set<Reservation> getReservations() {
        return reservations;
    }
     public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


    public Long getClient_id()
    {
        return client_id;
    }

    public String getContact()
    {
        return contact;
    }

    public String getLogin()
    {
        return login;
    }

    public RATINGS getRating()
    {
        return rating;
    }

    public void setClient_id(Long client_id)
    {
        this.client_id = client_id;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setRating(RATINGS rating)
    {
        this.rating = rating;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Client client = (Client) o;
        if ( !login.equals( client.login ) ) return false;
        if ( !password.equals( client.password) ) return false;
        if ( !rating.equals( client.rating) ) return false;
        if ( !contact.equals( client.contact ) ) return false;
        return true;
    }

     @Override
    public int hashCode() {
       int result = 9*login.hashCode() + 3*password.hashCode()+7*rating.hashCode()+13*contact.hashCode();
       return result;
    }

    @Override
    public String toString() {
        return login;
    }

}