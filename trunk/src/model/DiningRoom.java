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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int getMaxCapacity() {
		return maxCapacity;
	}

	@Override
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final DiningRoom dr = (DiningRoom) o;
		if (!maxCapacity.equals(dr.maxCapacity)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 13 * maxCapacity.hashCode();
	}

	@Override
	public String toString() {
		return super.toString() + ' ' + maxCapacity;
	}
}
