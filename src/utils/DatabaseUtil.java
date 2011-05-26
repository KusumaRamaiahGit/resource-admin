package utils;
import java.util.ArrayList;
import java.util.List;

import model.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.Date;


/**
 * @author rsamoylov
 */
public class DatabaseUtil
{
    /**
     * fills all project tables;
     * to refill tables, replace the hbm2ddl.auto parameter in hibernate.cfg.xml file
     * from true to create;
     * to use filled database replace it back
     */
    public static void FillDatabase()
    {
        //--------------------------------------------------------------------------------
        // resource table filling
        //--------------------------------------------------------------------------------
        DiningRoom d = new DiningRoom("dinnner", 10);
        ResourceDAO.addResource(d);

        Resource r = new Resource("some resource");
        ResourceDAO.addResource(r);

        Resource r1=new MeetingRoom("MeetingRoom");
        ResourceDAO.addResource(r1);

        r = new Monitor("mon");
        ResourceDAO.addResource(r);

        r = new MeetingRoom("mr");
        ResourceDAO.addResource(r);

        //--------------------------------------------------------------------------------
        // client table filling
        //--------------------------------------------------------------------------------

        Client c = new Client("boss", "pass", Client.RATINGS.LOW, "ab");
        ClientDAO.addClient(c);

        Client c1 = new Client("manager", "pass", Client.RATINGS.LOW, "ab");
        ClientDAO.addClient(c1);

        //--------------------------------------------------------------------------------
        // reservation table filling
        //--------------------------------------------------------------------------------
        Reservation res1 = new Reservation(r, new Date("2011/05/26"), new Date(" 2011/05/26 10:00:00"), new Date("2011/05/26 11:00:00"), c1);
        ReservationDAO.addReservation(res1);

        Reservation res2 = new Reservation(r, new Date("2011/05/26"), new Date(" 2011/05/26 12:00:00"), new Date("2011/05/26 14:00:00"), c);
        ReservationDAO.addReservation(res2);

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sess.beginTransaction();

        /*System.out.println("Все брони за день");
        Date d1=new Date("2011/05/26");
        Query query2 = sess.getNamedQuery("FindReservation_ALL").setParameter(0,d1);
         List<Reservation> list2 = query2.list();
         for( Reservation rn : list2) {
            System.out.println(rn.toString());
          }*/
        
        System.out.println("Все ресурсы Для выпадающего списка");
        ArrayList<Resource> resources = ResourceDAO.getAllResources();
       for (Resource re: resources) {
            System.out.println(re.getResource_name());
         }
       
        System.out.println("Внесение новой брони");
        boolean overlay=false;
        Date date= new Date("2011/05/26");
        Date time_start= new Date("2011/05/26 18:00:00");
        Date time_end=  new Date("2011/05/26 20:00:00");
        
        Reservation res = new Reservation(r,date,time_start,time_end, c);
        ArrayList<Reservation> reservations = ReservationDAO.getAllReservations();
        for( Reservation rn : reservations) {
           if  (date.equals(rn.getStart_date())&& 
                   (time_start.getHours()>=(rn.getStart_time().getHours()))
                   && (time_start.getHours()<=rn.getEnd_time().getHours())
                   && (time_end.getHours()<=rn.getEnd_time().getHours())
                   )
             overlay=true;
           }
        if (overlay)
           System.out.println("Перекрест времени!");
        else{
            ReservationDAO.addReservation(res);
            System.out.println("Бронь добавлена!");
        }
        
         Date d2=new Date("2011/05/26");
         Query query3 = sess.getNamedQuery("FindReservation_ALL").setParameter(0,d2);
         List<Reservation> list3 = query3.list();
         for( Reservation rn : list3) {
            System.out.println(rn.toString());
          }

    }
}
