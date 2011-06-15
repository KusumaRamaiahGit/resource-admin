package utils;

import model.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

/**
 * @author rsamoylov & @smihajlenko & Martynenko Viktoria
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

        Monitor m5 = new Monitor("monitor #5", "100W75");
		ResourceDAO.addResource(m5);

		// --------------------------------------------------------------------------------
		// client table filling
		// --------------------------------------------------------------------------------

		Client c0 = new Client("super-boss", "super-pass", Client.RATINGS.HIGH,"boss@mail.ru",Client.LOCATIONS.KYIV);
		ClientDAO.addClient(c0);
		
		Client c1 = new Client("boss", "pass", Client.RATINGS.HIGH,"boss@mail.ru",Client.LOCATIONS.ODESSA);
		ClientDAO.addClient(c1);

		Client c2 = new Client("manager", "manager", Client.RATINGS.MIDDLE,"manager@mail.ru",Client.LOCATIONS.ODESSA);
		ClientDAO.addClient(c2);

		Client c3 = new Client("developer #1", "developer1", Client.RATINGS.LOW, "developer1@mail.ru",Client.LOCATIONS.ODESSA);
		ClientDAO.addClient(c3);

		Client c4 = new Client("developer #2", "developer2", Client.RATINGS.LOW, "developer2@mail.ru",Client.LOCATIONS.ODESSA);
		ClientDAO.addClient(c4);
		
		Admin admin = new Admin("admin", "admin", Client.RATINGS.LOW, "admin@mail.ru",Client.LOCATIONS.ODESSA);
		admin.setRegistered(true);
		ClientDAO.addClient(admin);

		// --------------------------------------------------------------------------------
		// NewClient table filling
		// --------------------------------------------------------------------------------
		//NewClient nc =new NewClient("developer #2", "developer2", Client.RATINGS.LOW, "developer2@mail.ru");
		//ClientDAO.addClient(nc);

		// --------------------------------------------------------------------------------
		// reservation table filling
		// --------------------------------------------------------------------------------

		//-------------------------------------------------------------------
		//add reservations for statistic - Martynenko Vika
		//-------------------------------------------------------------------
		ArrayList<Resource> resources = ResourceDAO.getAllResources();
		for (int month = 0; month < 12; month++) {			
			for (int day = 1; day < 27; day++) {
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 0, 00), new GregorianCalendar(2011, month, day, 13-month, 00), c0));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 0, 00), new GregorianCalendar(2011, month, day, month*2, 00), c1));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 9, 00), new GregorianCalendar(2011, month, day, 9+month, 00), c2));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 16, 00), c3));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 11, 00), new GregorianCalendar(2011, month, day, month+11, 00), c4));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 0, 00), new GregorianCalendar(2011, month, day, month+2, 00), c3));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 15, 30), c4));
				ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, month, day, 0, 00), new GregorianCalendar(2011, month, day, month*2, 50), admin));
				if (day > 15) {
					ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, 1, 00), new GregorianCalendar(2011, month, day, month*2+1, 00), c0));
					ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, 8, 00), new GregorianCalendar(2011, month, day, month+9, 00), c1));
					ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 14, 00), c2));
                                        ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 16, 00), c3));
					ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, month+3, 00), new GregorianCalendar(2011, month, day, 20, 00), c4));
                                        ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, month, day, 8, 00), new GregorianCalendar(2011, month, day, month+8, 00), admin));
				}
                                ReservationDAO.addReservation(new Reservation(m1, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 18, 00), c0));
                                ReservationDAO.addReservation(new Reservation(m2, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 21, 13, 00), c1));
                                ReservationDAO.addReservation(new Reservation(m3, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 15, 00), c2));
                                ReservationDAO.addReservation(new Reservation(m4, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, month*2, 00), c3));
                                ReservationDAO.addReservation(new Reservation(m5, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar(2011, month, day, 22, 00), admin));
		
			}
			if ((month == 0) || (month == 6)) {
				for (int day = 0; day < 20; day++) {
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 14, 00), c0));
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 15, 00), c1));
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 13, 00), c2));
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 14, 00), c3));
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 16, 00), c4));
					ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, month, day, month, 00), new GregorianCalendar( 2011, month, day, 13, 00), admin));
				}
			}
		}
                ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, 5, 30, 12, 00), new GregorianCalendar(2011, 5, 30, 13, 00), c0));
                ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, 5, 30, 12, 00), new GregorianCalendar(2011, 5, 30, 13, 00), c1));
                ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, 5, 30, 12, 00), new GregorianCalendar(2011, 5, 30, 13, 00), c2));
                ReservationDAO.addReservation(new Reservation(m1, new GregorianCalendar(2011, 5, 30, 15, 00), new GregorianCalendar(2011, 5, 30, 16, 00), c3));
                ReservationDAO.addReservation(new Reservation(m2, new GregorianCalendar(2011, 5, 30, 15, 00), new GregorianCalendar(2011, 5, 30, 16, 00), c4));
                ReservationDAO.addReservation(new Reservation(er, new GregorianCalendar(2011, 5, 29, 12, 00), new GregorianCalendar(2011, 5, 29, 13, 00), c0));
                ReservationDAO.addReservation(new Reservation(dr, new GregorianCalendar(2011, 5, 29, 12, 00), new GregorianCalendar(2011, 5, 29, 13, 00), c1));
                ReservationDAO.addReservation(new Reservation(mr, new GregorianCalendar(2011, 5, 29, 12, 00), new GregorianCalendar(2011, 5, 29, 13, 00), c2));
                ReservationDAO.addReservation(new Reservation(m1, new GregorianCalendar(2011, 5, 29, 15, 00), new GregorianCalendar(2011, 5, 29, 16, 00), c3));
                ReservationDAO.addReservation(new Reservation(m2, new GregorianCalendar(2011, 5, 29, 15, 00), new GregorianCalendar(2011, 5, 29, 16, 00), c4));

		
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
                List<Reservation> list1 = ReservationDAO.getAllReservations();
		for (Reservation rn : list) {
			System.out.println(rn);
		}
                System.out.println("All reservations");
                for (Reservation rn : list1) {
			System.out.println(rn);
		}
		// -------------------------------------------------------------------
		// All resources
		// -------------------------------------------------------------------

		System.out.println("All resources");		
		for (Resource re : resources) {
			System.out.println(re.getResource_name());
		}

		// -------------------------------------------------------------------
		// Add new reservation
		// -------------------------------------------------------------------

		System.out.println("Add new reservation");
		List<Reservation> list_res = null; 														// reservation in the same time of day
		int capacity = 0; 																		// maxCapacity of resource
		boolean want_free = true; 																// wish of boss to free some resource
		Long currCount = 0L; 																	// current count of people for one resource
		
		List<Reservation> list_res_delete = new ArrayList<Reservation>();
		Resource resource = ResourceDAO.getResourceById(1L); 									// resource for reservation
		Client client = ClientDAO.getClientByLogin("boss"); 									// client for reservation
		GregorianCalendar.getInstance(); 														// gets a calendar using the default time zone and locale
		GregorianCalendar time_start = new GregorianCalendar(2011, 5, 20, 10, 00); 				// start time of reservation, where month from 0
		GregorianCalendar time_end = new GregorianCalendar(2011, 5, 20, 19, 00); 				// end time of reservation
		Reservation res = new Reservation(resource, time_start, time_end, client); 				// create a new reservation

		if (!res.getStart_time().before(new Date())) { 											// check that added date not in the past
			currCount = ReservationDAO.getReservationCurrentCount(resource, time_start, time_end); // get current count of people for
																								// one resource between start and end time

			if (resource instanceof Countable) { 												// check whether a countable
				try {
					capacity = (Integer) resource.getClass().getDeclaredMethod("getMaxCapacity").invoke(resource); // get maxCapacity from subclass method of Resource
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
			
			list_res = ReservationDAO.getReservationInTime(resource,time_start, time_end);  // get reservations where time of other reservations 
																							//is between time of our reservation
			for (Reservation rn : list_res) {
				if (client.equals(rn.getClient())) { 										// if client already has own reservation at this time
					list_res_delete.add(rn); 												// add that reservation to list for removal
					currCount--; 															// reduce current count					
				}
			}

			if (currCount >= capacity) { 													// check current count
				ClientComparator cc = new ClientComparator(); 								// create a new comparator of clients
				if (cc.compareByLoginMin(client) != 0) {									// if the client is a highest by comparable parameter
					System.out.println("Time cross! You may try another date or free some human!");

					if (want_free) { 														// if client wants to free some resource
						for (Reservation rn : list_res) { 									// show that reservations
							System.out.println(rn);

							if (currCount >= capacity) { 									// check again current count
								if (cc.compareByLogin(client, rn.getClient()) < 0) { 		// if other clients in reservation has rank lower
									list_res_delete.add(rn); 								// add that reservation to list for removal
									currCount--;											// reduce current count 	
								}
							}
						}
						if ((currCount == 0) || (currCount < capacity)) { 					// if current count less then capacity
							ReservationDAO.addReservation(res); 							// add our new reservation to database
							System.out.println("Reservation added!");
						} else { 															// if level of client not allowed to delete required number of reservations
							System.out.println("Your level is not enough to remove all  available users. You can choose another time!");
						}
					} else {																// if client doesn't want to free resource
						System.out.println("You can choose another time!");
					}
				} else { 																	// if rank of client lower then highest
					System.out.println("Time cross! You can't change it!");
				}
			} else { 																		// if resource was free from beginning
				ReservationDAO.addReservation(res);											// simple add our new reservation to database
				System.out.println("Reservation added!");
			}
			for (Reservation res_delete : list_res_delete)
				ReservationDAO.deleteReservation(res_delete); 								// delete reservations from database by list for removal
		} else { 																			// if date of reservation was before current time
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
			System.out.println(rn);
		}

	}
}
