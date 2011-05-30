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
        Resource r = new Resource("some resource");
        ResourceDAO.addResource(r);

        DiningRoom d = new DiningRoom("dinnner", 10);
        ResourceDAO.addResource(d);

        Resource r1=new MeetingRoom("MeetingRoom");
        ResourceDAO.addResource(r1);

        Monitor m = new Monitor("monitor #1","100W21");
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
        /*Reservation res1 = new Reservation(new Date("2011/05/26"), new Date(" 2011/05/26 10:00:00"), new Date("2011/05/26 11:00:00"));
        res1.getResources().add(r);
        res1.getClients().add(c);
        ReservationDAO.addReservation(res1);

        Reservation res2 = new Reservation( new Date("2011/05/26"), new Date(" 2011/05/26 12:00:00"), new Date("2011/05/26 14:00:00"));
        res2.getResources().add(d);
        res1.getClients().add(c1);
        ReservationDAO.addReservation(res2);

        
        System.out.println("Все брони за день");
        Date d1=new Date("2011/05/26");

        List<Reservation> list = ReservationDAO.getReservationByDate(d1);
         for( Reservation rn : list) {
           System.out.println(rn);
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
        Date time_end=  new Date("2011/05/26 10:00:00");

        Reservation res = new Reservation(date,time_start,time_end);
        res.getResources().add(r);
        res.getClients().add(c);
          for( Reservation rn : list) {
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

          Date d2=new Date("2011/05/26");
         List<Reservation> list1 = ReservationDAO.getReservationByDate(d2);
         for( Reservation rn : list1) {
           System.out.println(rn);
          }*/
          
         }

    }

