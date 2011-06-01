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
        Resource r = new Resource("some resource");
        ResourceDAO.addResource(r);

        DiningRoom d = new DiningRoom("dinnner", 1);
        ResourceDAO.addResource(d);

        Resource r1=new MeetingRoom("MeetingRoom");
        ResourceDAO.addResource(r1);

        Monitor m = new Monitor("monitor #1","100W78");
        ResourceDAO.addResource(m);
      
        //--------------------------------------------------------------------------------
        // client table filling
        //--------------------------------------------------------------------------------

        Client c = new Client("boss", "pass", Client.RATINGS.LOW, "ab@mail.ru");
        ClientDAO.addClient(c);

        Client c1 = new Client("manager", "pass", Client.RATINGS.LOW, "ab@mail.ru");
        ClientDAO.addClient(c1);

        Client c2 = new Client();
        c2=ClientDAO.getClientByLogin("boss");
        System.out.println(c2);
        //--------------------------------------------------------------------------------
        // reservation table filling
        //--------------------------------------------------------------------------------
        Reservation res1 = new Reservation(d, new Date(" 2011/05/26 10:00"), new Date("2011/05/26 11:00"));
        res1.getClients().add(c);
        res1.getClients().add(c1);
        ReservationDAO.addReservation(res1);
     
        Reservation res2 = new Reservation(d, new Date(" 2011/05/26 12:00"), new Date("2011/05/26 14:00"));
        res2.getClients().add(c1);
        ReservationDAO.addReservation(res2);

 
        System.out.println("Все брони за день");
        Date d1=new Date("2011/05/26");

       List<Reservation> list = ReservationDAO.getReservationByDateAndResource(d1,d);
         for( Reservation rn : list) {
           System.out.println(rn);
          }
        
        System.out.println("All resources");
        ArrayList<Resource> resources = ResourceDAO.getAllResources();
           for (Resource re: resources) {
            System.out.println(re.getResource_name());
         }
       
        System.out.println("Add new reservation");
        Date time_start= new Date("2011/05/26 10:00");
        Date time_end=  new Date("2011/05/26 19:00");

        Reservation res = new Reservation(d,time_start,time_end);
        res.getClients().add(c);
        int i=0;
          for( Reservation rn : list) {
                if ((time_start.getDate()==rn.getStart_time().getDate())&&
                   (time_start.getYear()==rn.getStart_time().getYear())&&
                   (time_start.getMonth()==rn.getStart_time().getMonth())&&
                   (time_start.getHours()<rn.getEnd_time().getHours())&&
                   (time_end.getHours()>rn.getStart_time().getHours())
                   ){
                   i=i+ rn.getClients().size();
              }
           }
        if (i>d.getMaxCapacity())
        {
           System.out.println("Time cross!");
        }
        else{
            ReservationDAO.addReservation(res);
            System.out.println("Reservation added!");
        }
         System.out.println(i);

        // ReservationDAO.deleteReservation(res1);
         //--------Show res lt-----------------
         Date d2=new Date("2011/05/26");
         List<Reservation> list1 = ReservationDAO.getReservationByDateAndResource(d2,d);
         for( Reservation rn : list1) {
           System.out.println(rn);
          }
         
         
         }

    }

