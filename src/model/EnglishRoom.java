package model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author smihajlenko
 */
@Entity
public class EnglishRoom extends Resource implements Countable {

	private static final long serialVersionUID = 1021759746456387321L;
	@Column(name = "maxCapacity")
	private Integer maxCapacity;

	public EnglishRoom() {
		super();
	}

	public EnglishRoom(String name, Integer maxCapacity) {
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
		final EnglishRoom er = (EnglishRoom) o;
		if (!maxCapacity.equals(er.maxCapacity)) {
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
