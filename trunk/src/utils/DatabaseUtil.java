package utils;

import model.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

/**
 * @author rsamoylov & @smihajlenko)
 */
public class DatabaseUtil {

	public static void FillDatabase() {

		// --------------------------------------------------------------------------------
		// resource table filling
		// --------------------------------------------------------------------------------

		DiningRoom dr = new DiningRoom("DiningRoom", 5);
		ResourceDAO.addResource(dr);

		Resource mr = new MeetingRoom("MeetingRoom");
		ResourceDAO.addResource(mr);

		EnglishRoom er = new EnglishRoom("EnglishRoom", 4);
		ResourceDAO.addResource(er);

		Monitor m1 = new Monitor("monitor #1", "100W78");
		ResourceDAO.addResource(m1);

		Monitor m2 = new Monitor("monitor #2", "100W79");
		ResourceDAO.addResource(m2);

		Monitor m3 = new Monitor("monitor #3", "100W77");
		ResourceDAO.addResource(m3);

		Monitor m4 = new Monitor("monitor #4", "100W76");
		ResourceDAO.addResource(m4);

		// --------------------------------------------------------------------------------
		// client table filling
		// --------------------------------------------------------------------------------

		Client c1 = new Client("boss", "pass", Client.RATINGS.HIGH,"boss@mail.ru");
		ClientDAO.addClient(c1);

		Client c2 = new Client("manager", "manager", Client.RATINGS.MIDDLE,"manager@mail.ru");
		ClientDAO.addClient(c2);

		Client c3 = new Client("developer #1", "developer1", Client.RATINGS.LOW, "developer1@mail.ru");
		ClientDAO.addClient(c3);

		Client c4 = new Client("developer #2", "developer2", Client.RATINGS.LOW, "developer2@mail.ru");
		ClientDAO.addClient(c4);

		// --------------------------------------------------------------------------------
		// reservation table filling
		// --------------------------------------------------------------------------------

		// ---DinnerRoom---
		GregorianCalendar.getInstance();
		Reservation res1 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 10, 00), new GregorianCalendar(2011, 5, 20, 11, 00), c4);
		ReservationDAO.addReservation(res1);

		Reservation res2 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 12, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c2);
		ReservationDAO.addReservation(res2);

		Reservation res3 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 14, 00), new GregorianCalendar(2011, 5, 20, 15, 00), c1);
		ReservationDAO.addReservation(res3);

		Reservation res4 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 12, 15), new GregorianCalendar(2011, 5, 20, 12, 45), c3);
		ReservationDAO.addReservation(res4);

		Reservation res5 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 13, 20), new GregorianCalendar(2011, 5, 20, 13, 45), c3);
		ReservationDAO.addReservation(res5);

		Reservation res6 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 14, 20), new GregorianCalendar(2011, 5, 20, 15, 00), c2);
		ReservationDAO.addReservation(res6);

		Reservation res7 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 14, 00), new GregorianCalendar(2011, 5, 20, 15, 00), c4);
		ReservationDAO.addReservation(res7);

		Reservation res8 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 14, 15), new GregorianCalendar(2011, 5, 20, 14, 50), c1);
		ReservationDAO.addReservation(res8);

		Reservation res9 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 11, 00), new GregorianCalendar(2011, 5, 20, 11, 30), c2);
		ReservationDAO.addReservation(res9);

		Reservation res10 = new Reservation(dr, new GregorianCalendar(2011, 5, 20, 16, 00), new GregorianCalendar(2011, 5, 20, 16, 40), c3);
		ReservationDAO.addReservation(res10);

		// ---EnglishRoom---
		Reservation res11 = new Reservation(er, new GregorianCalendar(2011, 5, 20, 12, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c1);
		ReservationDAO.addReservation(res11);

		Reservation res12 = new Reservation(er, new GregorianCalendar(2011, 5, 20, 12, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c2);
		ReservationDAO.addReservation(res12);

		Reservation res13 = new Reservation(er, new GregorianCalendar(2011, 5, 20, 12, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c3);
		ReservationDAO.addReservation(res13);

		Reservation res14 = new Reservation(er, new GregorianCalendar(2011, 5, 20, 12, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c4);
		ReservationDAO.addReservation(res14);

		// --------Monitors-----------------
		Reservation res15 = new Reservation(m1, new GregorianCalendar(2011, 5, 20, 00, 00), new GregorianCalendar(2012, 1, 1, 00, 00), c2);
		ReservationDAO.addReservation(res15);

		Reservation res16 = new Reservation(m2, new GregorianCalendar(2011, 5, 20, 00, 00), new GregorianCalendar(2012, 1, 1, 00, 00), c3);
		ReservationDAO.addReservation(res16);

		Reservation res17 = new Reservation(m3, new GregorianCalendar(2011, 5, 20, 00, 00), new GregorianCalendar(2012, 1, 1, 00, 00), c4);
		ReservationDAO.addReservation(res17);

		// ------------MeetingRoom-----------
		Reservation res18 = new Reservation(mr, new GregorianCalendar(2011, 5, 20, 10, 00), new GregorianCalendar(2011, 5, 20, 11, 00), c1);
		ReservationDAO.addReservation(res18);

		Reservation res19 = new Reservation(mr, new GregorianCalendar(2011, 5, 20, 11, 15), new GregorianCalendar(2011, 5, 20, 12, 00), c2);
		ReservationDAO.addReservation(res19);

		Reservation res20 = new Reservation(mr, new GregorianCalendar(2011, 5, 20, 13, 00), new GregorianCalendar(2011, 5, 20, 14, 00), c4);
		ReservationDAO.addReservation(res20);

		Reservation res21 = new Reservation(mr, new GregorianCalendar(2011, 5, 20, 16, 00), new GregorianCalendar(2011, 5, 20, 17, 00), c1);
		ReservationDAO.addReservation(res21);

		// -------------------------------------------------------------------
		// All reservations by Date with Showing Cliet
		// -------------------------------------------------------------------

		Client c = new Client();
		c = ClientDAO.getClientByLogin("boss");
		System.out.println(c);

		System.out.println("All reservations per day");
		GregorianCalendar.getInstance();
		GregorianCalendar d1 = new GregorianCalendar(2011, 5, 20, 10, 00);

		System.out.println("All reservations per day DinningRoom");

		List<Reservation> list = ReservationDAO.getReservationByDateAndResource(d1, dr);
		for (Reservation rn : list) {
			System.out.println(rn);
		}
		// -------------------------------------------------------------------
		// All resources
		// -------------------------------------------------------------------

		System.out.println("All resources");
		ArrayList<Resource> resources = ResourceDAO.getAllResources();
		for (Resource re : resources) {
			System.out.println(re.getResource_name());
		}

		// -------------------------------------------------------------------
		// Add new reservation
		// -------------------------------------------------------------------

		System.out.println("Add new reservation");
		Resource resource = ResourceDAO.getResourceById(4L);                       //  resource for reservation
		Client client = ClientDAO.getClientByLogin("boss");                        //  client for reservation
		GregorianCalendar.getInstance();										   //  gets a calendar using the default time zone and locale
		GregorianCalendar time_start = new GregorianCalendar(2011, 5, 20, 10, 00); //  start time of reservation, where month from 0
		GregorianCalendar time_end = new GregorianCalendar(2011, 5, 20, 19, 00);   //  end time of reservation
		Reservation res = new Reservation(resource, time_start, time_end, client); //  create a new reservation
		
		List<Reservation> list_res = null;										   //  reservation in the same time of day
		int capacity = 0;														   //  maxCapacity of resource
		boolean want_free = true;                                                  //  wish of boss to free some resource
		Long currCount = 0L; 												       //  current count of people for one resource
		
		if (!res.getStart_time().before(new Date())) {							   //  check that added date not in the past
			currCount = ReservationDAO.getReservationCurrentCount(resource,	time_start, time_end);   //  get current count of people for one resource
																									 //  between start and end time

			if (resource instanceof Countable) { 												 	 // check whether a countable
				try {
					capacity = (Integer) resource.getClass().getDeclaredMethod("getMaxCapacity").invoke(resource); 	 //  get maxCapacity from method subclass of Resource 
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				}
			}

			if (currCount >= capacity) {																	 //  check current count
				if (client.getRating().compareTo(Client.RATINGS.HIGH) == 0) {								 //  if the client is a boss
					System.out.println("Time cross! You may try another date or free some human!");
					if (want_free) {																		 //  if boss wants to free some resource
						list_res = ReservationDAO.getReservationInTime(resource, time_start, time_end);	     //  get reservations where time of other reservations is between time of our reservation
						
						for (Reservation rn : list_res) {													 //  show that reservations
							System.out.println(rn);

							if (currCount >= capacity) {													//  check again current count
								if (client.getRating().compareTo(rn.getClient().getRating()) < 0) {			//  if other clients in reservation has rank lower
									ReservationDAO.deleteReservation(rn);									//  delete that reservation from database
 									currCount--;															//  reduce current count
								}
							}
						}
						ReservationDAO.addReservation(res);													//  add our reservation to database
 						System.out.println("Reservation added!");
					}
					else{																				    //  if client doesn't want to free resource
						System.out.println("You can choose another time!");
					}
				} else {																				    //  if rank of client lower boss 
					System.out.println("Time cross! You can't change it!");
				}
			} else {																						//  if resource was free from beginning
				ReservationDAO.addReservation(res);
				System.out.println("Reservation added!");
			}
		}

		else {																								//  if date of reservation was before current time
			System.out.println("You can't reservate in PAST!");
		}

		// -------------------------------------------------------------------
		// Just check how it works
		// -------------------------------------------------------------------
		GregorianCalendar.getInstance();
		GregorianCalendar d2 = new GregorianCalendar(2011, 5, 20);
		System.out.println("All");
		List<Reservation> list3 = ReservationDAO.getReservationByDateAndResource(d2, resource);
		for (Reservation rn : list3) {
			System.out.println(rn + " " + rn.getClient().getLogin());
		}

	}
}
