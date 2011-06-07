package utils;

import model.Reservation;
import model.Resource;
import model.Client;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * data access object to reservation table
 * @author rsamoylov & smihajlenko
 */
public class ReservationDAO {

    public static void addReservation(Reservation r) {
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            sess.save(r);

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public static ArrayList<Reservation> getAllReservations() {

            ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            reservations = (ArrayList<Reservation>) sess.createCriteria(Reservation.class).list();

            sess.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при попытке получить все брони за день!", JOptionPane.OK_OPTION);
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
            e.printStackTrace();
        }
    }

    public static void deleteReservation(Reservation r) {
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();
            Query query2 = sess.createSQLQuery("delete from reservation where reservation_id=?;").setParameter(0, r.getReservation_id());
            query2.executeUpdate();
            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return r;
    }

    @SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResourceAndClient(Calendar start_time, Resource resource, Client client) {
        List<Reservation> list = null;

        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource_and_Client").setParameter(0, start_time).setParameter(1, start_time).setParameter(2, resource).setParameter(3, client);
            list = query.list();

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @SuppressWarnings("unchecked")
	public static List<Reservation> getReservationInTime(Resource resource, Calendar start_time, Calendar end_time) {
        List<Reservation> list = null;
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            Query query = sess.getNamedQuery("FindReservation_ALL_in_Time").setParameter(0, resource).setParameter(1, start_time).setParameter(2, end_time).setParameter(3, start_time).setParameter(4, end_time);
            list = query.list();

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @SuppressWarnings("rawtypes")
	public static Long getReservationCurrentCount(Resource resource, Calendar start_time, Calendar end_time) {
        Long count = 0L;
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            Query query = sess.getNamedQuery("Count_Reservation_in_Time").setParameter(0, resource).setParameter(1, start_time).setParameter(2, end_time).setParameter(3, start_time).setParameter(4, end_time);

            List list = query.list();
            count = (Long) list.get(0);

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

	@SuppressWarnings("rawtypes")
	public static Long getReservationAllCount(Resource resource, Calendar start_time, Calendar end_time) {
        Long count = 0L;
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            Query query = sess.getNamedQuery("Get_Reservations_Count");

            List list = query.list();
            count = (Long) list.get(0);

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }


    @SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResource(Calendar start_date,Resource resource)
    {
        List<Reservation> list=null;

        try{
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

       Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource").setParameter(0,start_date).setParameter(1, resource);
       list = query.list();

       sess.getTransaction().commit();
        } catch (Exception e) {
               e.printStackTrace();
       }

    return list;

    }
    @SuppressWarnings("unchecked")
	public static List<Reservation> getReservationInTime2(Resource resource, Calendar start_time,Calendar end_time) {
        List<Reservation> list = null;
        try {
            Session sess = HibernateUtil.getSession();
            sess.beginTransaction();

            Query query = sess.getNamedQuery("FindReservation_ALL_in_Time_2").setParameter(0, resource).setParameter(1, start_time).setParameter(2, end_time);
            list = query.list();

            sess.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
}
