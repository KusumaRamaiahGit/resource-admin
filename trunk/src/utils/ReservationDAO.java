package ourproject;

import entities.Reservation;
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
         Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.save(r);

        sess.getTransaction().commit();
    }

    public static ArrayList<Reservation> getAllReservations()
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        reservations = (ArrayList<Reservation>) sess.createCriteria(Reservation.class).list();

        sess.getTransaction().commit();
     
        return reservations;
    }

    public static void updateReservations(Reservation r)
    {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.update(r);

        sess.getTransaction().commit();
     }

    public static Reservation getReservationById(Long id)
    {
        Reservation r;

        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        r = (Reservation) sess.get(Reservation.class, id);

        sess.getTransaction().commit();
      
        return r;
    }

     public static List<Reservation> getReservationByDate(Date start_date){
      Session sess = HibernateUtil.getSession();
     sess.beginTransaction();

     Query query = sess.getNamedQuery("FindReservation_ALL").setParameter(0,start_date);
     List<Reservation> list = query.list();


     //sess.getTransaction().commit();
     
     return list;

     }

   
}
