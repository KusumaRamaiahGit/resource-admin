package utils;

import model.Reservation;
import model.Resource;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.Date;
import java.util.List;
/**
 * data access object to reservation table
 * @author rsamoylov
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

       //sess.delete(r);
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

     public static List<Reservation> getReservationByDateAndResource(Date start_date,Resource resource)
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

   
}