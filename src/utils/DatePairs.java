package utils;

import java.util.Date;

public class DatePairs {
	Date start_time;
	Date end_time;

	public DatePairs(Date start_time, Date end_time) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((end_time == null) ? 0 : end_time.hashCode());
		result = prime * result
				+ ((start_time == null) ? 0 : start_time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatePairs other = (DatePairs) obj;
		if (end_time == null) {
			if (other.end_time != null)
				return false;
		} else if (!end_time.equals(other.end_time))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return "[" + start_time.getHours() + ":"+ start_time.getMinutes()+" ; " + end_time.getHours() + ":" + end_time.getMinutes()
				+ "]";
	}
	
	
	
	
}
