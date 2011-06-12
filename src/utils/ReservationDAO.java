package utils;

import model.Reservation;
import model.Resource;
import model.Client;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * data access object to reservation table
 * 
 * @author rsamoylov & smihajlenko & Martynenko Viktoria
 */
public class ReservationDAO {

	public static void addReservation(Reservation r) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.save(r);

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not add new reservation!",
					JOptionPane.OK_OPTION);
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Reservation> getAllReservations() {

		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			reservations = (ArrayList<Reservation>) sess.createCriteria(
					Reservation.class).list();

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not show all reservations!",
					JOptionPane.OK_OPTION);
		}

		return reservations;
	}

	public static void updateReservations(Reservation r) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.update(r);

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not update reservation!",
					JOptionPane.OK_OPTION);
		}
	}

	public static void deleteReservation(Reservation r) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();
			Query query2 = sess.createSQLQuery(
					"delete from reservation where reservation_id=?;")
					.setParameter(0, r.getReservation_id());
			query2.executeUpdate();
			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not delete reservation!",
					JOptionPane.OK_OPTION);
		}
	}

	public static Reservation getReservationById(Long id) {
		Reservation r = null;

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			r = (Reservation) sess.get(Reservation.class, id);

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get reservation by ID!",
					JOptionPane.OK_OPTION);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResourceAndClient(
			Calendar start_time, Resource resource, Client client) {
		List<Reservation> list = null;

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess
					.getNamedQuery(
							"FindReservation_ALL_of_Day_by_Resource_and_Client")
					.setParameter(0, start_time).setParameter(1, start_time)
					.setParameter(2, resource).setParameter(3, client);
			list = query.list();

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get reservation by resource and client!",
					JOptionPane.OK_OPTION);
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationInTime(Resource resource,
			Calendar start_time, Calendar end_time) {
		List<Reservation> list = null;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess.getNamedQuery("FindReservation_ALL_in_Time")
					.setParameter("resource_fk", resource)
					.setParameter("start_time", start_time)
					.setParameter("end_time", end_time);
			list = query.list();

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get reservationsin in time!",
					JOptionPane.OK_OPTION);
		}
		return list;

	}

	@SuppressWarnings("rawtypes")
	public static Long getReservationCurrentCount(Resource resource,
			Calendar start_time, Calendar end_time) {
		Long count = 0L;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess.getNamedQuery("Count_Reservation_in_Time")
					.setParameter(0, resource).setParameter(1, start_time)
					.setParameter(2, end_time).setParameter(3, start_time)
					.setParameter(4, end_time);

			List list = query.list();
			count = (Long) list.get(0);

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not count current reservations in time!",
					JOptionPane.OK_OPTION);
		}
		return count;

	}

	@SuppressWarnings("rawtypes")
	public static Long getReservationAllCount(Resource resource,
			Calendar start_time, Calendar end_time) {
		Long count = 0L;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess.getNamedQuery("Get_Reservations_Count");

			List list = query.list();
			count = (Long) list.get(0);

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not count all reservations!",
					JOptionPane.OK_OPTION);
		}
		return count;

	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResource(
			Calendar start_date, Resource resource) {
		List<Reservation> list = null;

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess
					.getNamedQuery("FindReservation_ALL_of_Day_by_Resource")
					.setParameter(0, start_date).setParameter(1, resource);
			list = query.list();

			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get reservations by resurce and date!",
					JOptionPane.OK_OPTION);;
		}

		return list;

	}

	//functions for statistic, author - Martynenko Viktoria
	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByResource(Resource resource) {
		List<Reservation> list = null;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();
			Query query = sess.getNamedQuery("FindReservation_by_Resource")
					.setParameter(0, resource);
			list = query.list();
			sess.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get all reservations by resource!",
					JOptionPane.OK_OPTION);
		}

		return list;
	}
	public static Long getMinutesInPeriod(Date start,Date end){
		Long minutes=(end.getTime()-start.getTime())/60000;
		return minutes;
	}
	public static Long getReservedTimeForResource(Resource resource){
		Long time=new Long(0);
		List<Reservation> res=getReservationByResource(resource);
		if(res.size()>0){
			Date start=new Date();Date end=new Date();
			start=res.get(0).getStart_time().getTime();//начало промежутка зарезервированного времени
			end=res.get(0).getEnd_time().getTime();//конец промежутка зарезервированного времени
			for(Reservation r:res){
				if(end.compareTo(r.getStart_time().getTime())>=0) {//если конец текущего промежутка зарезервированного времени>=начало следующей резервации 
					end=r.getEnd_time().getTime();//концу текущего промежутка зарезервированного времени присвоить конец следующей резервации
				}
				else {
					time+=getMinutesInPeriod(start,end);//увеличим время
					start=r.getStart_time().getTime();//начало следующего промежутка зарезервированного времени
					end=r.getEnd_time().getTime();//конец следующего промежутка зарезервированного времени										
				}
				if(r.equals(res.get(res.size()-1))) {//если дошли до последнего элемента в списке
					time+=getMinutesInPeriod(start,end);//увеличим время					
				}
			}						
		}				
		return time;
	}
}
