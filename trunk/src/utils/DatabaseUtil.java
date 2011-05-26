package ourproject;
import java.util.ArrayList;
import java.util.List;
import entities.*;
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

        r = new Monitor("monitor #1");
        ResourceDAO.addResource(r);

      
        //--------------------------------------------------------------------------------
        // client table filling
        //--------------------------------------------------------------------------------

        Client c = new Client("boss", "pass", Client.RATINGS.LOW, "ab");
        ClientDAO.addClient(c);

        Client c1 = new Client("manager", "pass", Client.RATINGS.LOW, "ab");
        ClientDAO.addClient(c1);

        Client c2 = new Client();
        c2=ClientDAO.getClientByLogin("boss");
        System.out.println(c2);
        //--------------------------------------------------------------------------------
        // reservation table filling
        //--------------------------------------------------------------------------------
        Reservation res1 = new Reservation(r, new Date("2011/05/26"), new Date(" 2011/05/26 10:00:00"), new Date("2011/05/26 11:00:00"), c1);
        ReservationDAO.addReservation(res1);

        Reservation res2 = new Reservation(r, new Date("2011/05/26"), new Date(" 2011/05/26 12:00:00"), new Date("2011/05/26 14:00:00"), c);
        ReservationDAO.addReservation(res2);

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sess.beginTransaction();

        System.out.println("Все брони за день");
        Date d1=new Date("2011/05/26");
        Query query2 = sess.getNamedQuery("FindReservation_ALL").setParameter(0,d1);
         List<Reservation> list2 = query2.list();
         for( Reservation rn : list2) {
            System.out.println(rn.toString());
          }
        
        System.out.println("Все ресурсы Для выпадающего списка");
        ArrayList<Resource> resources = ResourceDAO.getAllResources();
       for (Resource re: resources) {
            System.out.println(re.getResource_name());
         }
       
        System.out.println("Внесение новой брони");
        boolean overlay=true;
        Date date= new Date("2011/05/26");
        Date time_start= new Date("2011/05/26 9:00:00");
        Date time_end=  new Date("2011/05/26 10:40:00");



        Reservation res = new Reservation(r,date,time_start,time_end, c);
        List<Reservation> list3 = query2.list();
        for( Reservation rn : list3) {
         /*  if  (date.equals(rn.getStart_date())&&
                   (time_start.getHours()>(rn.getStart_time().getHours()))
                   && (time_start.getHours()<rn.getEnd_time().getHours())
                   && (time_end.getHours()<rn.getEnd_time().getHours())
                   )*/
                if (date.equals(rn.getStart_date())&&
                   (time_start.getHours()<=rn.getStart_time().getHours())&&
                   (time_end.getHours()<=rn.getStart_time().getHours())
                   )
             overlay=false;
           }
        if (overlay)
           System.out.println("Перекрест времени!");
        else{
            ReservationDAO.addReservation(res);
            System.out.println("Бронь добавлена!");
        }

       transaction.commit();
       Session sess1 = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction1 = sess1.beginTransaction();
         Date d2=new Date("2011/05/26");
         Query query3 = sess1.getNamedQuery("FindReservation_ALL").setParameter(0,d2);
         List<Reservation> list4 = query3.list();
         for( Reservation rn : list4) {
            System.out.println(rn.toString());
          }

    }
}
