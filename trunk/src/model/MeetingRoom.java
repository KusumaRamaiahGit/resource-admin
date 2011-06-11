package model;

import javax.persistence.Entity;

/**
 * @author rsamoylov
 */
@Entity
public class MeetingRoom extends Resource {

	private static final long serialVersionUID = 2090276798604035246L;

	public MeetingRoom() {
		super();
	}

	public MeetingRoom(String name) {
		super(name);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
