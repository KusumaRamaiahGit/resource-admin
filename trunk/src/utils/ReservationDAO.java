package utils;

import model.Reservation;
import model.Resource;
import model.Client;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * data access object to reservation table
 * @author rsamoylov & @smihajlenko
 */
public class ReservationDAO
{
    public static void addReservation(Reservation r)
    {
        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.save(r);

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static ArrayList<Reservation> getAllReservations()
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        reservations = (ArrayList<Reservation>) sess.createCriteria(Reservation.class).list();

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }
     
        return reservations;
    }

    public static void updateReservations(Reservation r)
    {
        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.update(r);

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }
     }

        public static void deleteReservation(Reservation r)
    {
        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();
        Query query = sess.createSQLQuery("delete from reservation_client where reservation_id=?;")
                .setParameter(0, r.getReservation_id());
        query.executeUpdate();
        Query query2 = sess.createSQLQuery("delete from reservation where reservation_id=?;")
                .setParameter(0, r.getReservation_id());
        query2.executeUpdate();
        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }
     }


    public static Reservation getReservationById(Long id)
    {
        Reservation r=null;

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

     public static List<Reservation> getReservationByDateAndResourceAndClient(Date start_time,Resource resource,Client client)
     {
         List<Reservation> list=null;

         try{
         Session sess = HibernateUtil.getSession();
         sess.beginTransaction();

        Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource_and_Client")
                .setParameter(0,start_time)
                .setParameter(1,resource)
                .setParameter(2,client);
        list = query.list();

        sess.getTransaction().commit();
         } catch (Exception e) {
                e.printStackTrace();
        }

     return list;

     }


      public static List<Reservation> getReservationByDateAndResource(Date start_time,Resource resource)
     {
         List<Reservation> list=null;

         try{
         Session sess = HibernateUtil.getSession();
         sess.beginTransaction();

        Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource")
                .setParameter(0,start_time)
                .setParameter(1,resource);
        list = query.list();

        sess.getTransaction().commit();
         } catch (Exception e) {
                e.printStackTrace();
        }

     return list;

     }
   
}
