package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

/**
 * @author smihaylenko
 */
@Entity
@Table(name = "RES")
public class Resource implements Serializable {

    private static final long serialVersionUID = 4850528633995997789L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id", nullable = false, columnDefinition = "integer")
    private Long resource_id;
    private String resource_name;
    private Reservation reservation;

    @OneToOne(mappedBy = "resource")
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Resource() {
    }

    public Resource(String resource_name) {
        this.resource_name = resource_name;
    }

    @Column(name = "resource_name", nullable = false)
    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }

    public Long getResource_id() {
        return resource_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Resource res = (Resource) o;
        if (!resource_name.equals(res.resource_name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 15 * resource_name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return resource_name;
    }
}
