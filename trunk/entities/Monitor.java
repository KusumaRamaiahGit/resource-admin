package entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author rsamoylov
 */
@Entity
@Table(name = "Monitor")
@PrimaryKeyJoinColumn(name = "resource_id", referencedColumnName = "resource_id")
public class Monitor extends Resource
{
    public Monitor()
    {
        super();
    }

    public Monitor(String name)
    {
        super(name);
    }
}
