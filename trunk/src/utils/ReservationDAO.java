package utils;

import java.util.ArrayList;

import model.Reservation;

import org.hibernate.Session;

/**
 * data access object to reservation table
 * @author rsamoylov
 */
public class ReservationDAO
{
    public static void addReservation(Reservation r)
    {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();

        sess.save(r);

        sess.getTransaction().commit();
        sess.close();

    }

    public static ArrayList<Reservation> getAllReservations()
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();

        reservations = (ArrayList<Reservation>) sess.createCriteria(Reservation.class).list();

        sess.getTransaction().commit();
        sess.close();
     
        return reservations;
    }

    public static void updateReservations(Reservation r)
    {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();

        sess.update(r);

        sess.getTransaction().commit();
        sess.close();
          }

    public static Reservation getReservationById(Long id)
    {
        Reservation r;

        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();

        r = (Reservation) sess.get(Reservation.class, id);

        sess.getTransaction().commit();
        sess.close();
      
        return r;
    }
}
