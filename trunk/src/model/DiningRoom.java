package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author rsamoylov
 */
@Entity
@PrimaryKeyJoinColumn(name = "resource_id", referencedColumnName = "resource_id")
public class DiningRoom extends Resource implements Countable
{
    @Column(name = "maxCapacity")
    private Integer maxCapacity;

    public DiningRoom()
    {
        super();
    }

    public DiningRoom(String name, Integer maxCapacity)
    {
        super(name);
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }
}
