package model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author rsamoylov
 */
@Entity
public class DiningRoom extends Resource implements Countable {

    private static final long serialVersionUID = -2080231761496926953L;
    @Column(name = "maxCapacity")
    private Integer maxCapacity;

    public DiningRoom() {
        super();
    }

    public DiningRoom(String name, Integer maxCapacity) {
        super(name);
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
