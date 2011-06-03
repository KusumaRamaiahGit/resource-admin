/**
 * 
 */
package controller;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public interface IReserveHadler {
	void addReservation(long res_id, Date d_start, Date d_end);
}
