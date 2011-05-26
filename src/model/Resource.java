package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;

/**
 * @author smihaylenko
 */
@Entity
@Table(name = "RES")
@SequenceGenerator(name = "RESOURCE_SEQUENCE", sequenceName = "RESOURCE_ID_SEQ")
public class Resource implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "RESOURCE_SEQUENCE")
    @Column(name = "resource_id", nullable = false, columnDefinition = "integer")
    private Long resource_id;
    @Column(name = "resource_name", nullable = false, columnDefinition = "varchar(255)")
    private String resource_name;

    public Resource()
    {
    }

    public Resource(String resource_name)
    {
        this.resource_name = resource_name;
    }

    public Long getResource_id()
    {
        return resource_id;
    }

    public String getResource_name()
    {
        return resource_name;
    }

    public void setResource_id(Long resource_id)
    {
        this.resource_id = resource_id;
    }

    public void setResource_name(String resource_name)
    {
        this.resource_name = resource_name;
    }

   public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Resource res = (Resource) o;
        if ( !resource_name.equals( res.resource_name ) ) return false;
        return true;
    }

    public int hashCode() {
      int  result = 15*resource_name.hashCode();
        return result;
    }

}
