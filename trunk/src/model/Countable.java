package model;

/**
 * marks, that the resource that implements this interface has maximal capacity
 * property
 * 
 * @author rsamoylov
 */
public interface Countable {
	public int getMaxCapacity();

	public void setMaxCapacity(int maxCapacity);
}
