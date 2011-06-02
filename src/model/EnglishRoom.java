package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author smihajlenko
 */

@Entity

public class EnglishRoom extends Resource implements Countable
{
    @Column(name = "maxCapacity")
    private Integer maxCapacity;

    public EnglishRoom()
    {
        super();
    }

    public EnglishRoom(String name, Integer maxCapacity)
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
