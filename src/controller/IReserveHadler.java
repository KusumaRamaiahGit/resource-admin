/**
 * 
 */
package controller;

import java.io.PrintWriter;
//import java.util.Date;
import java.util.GregorianCalendar;

import model.Client;
/**
 * @author EDudnik
 *
 */
public interface IReserveHadler {
	boolean addReservation(long res_id, GregorianCalendar d_start, GregorianCalendar d_end, Client client, PrintWriter out);
}