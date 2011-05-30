package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author rsamoylov
 */
@Entity
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
