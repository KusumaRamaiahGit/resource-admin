package utils;
import java.util.ArrayList;
import java.util.List;
import model.*;
import java.util.Date;

/**
 * @author rsamoylov & @smihajlenko)
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

        DiningRoom dr = new DiningRoom("DinnnerRoom", 5);
        ResourceDAO.addResource(dr);

        Resource mr=new MeetingRoom("MeetingRoom");
        ResourceDAO.addResource(mr);
        
        EnglishRoom er = new EnglishRoom("EnglishRoom",4);
        ResourceDAO.addResource(er);

        Monitor m1 = new Monitor("monitor #1","100W78");
        ResourceDAO.addResource(m1);

        Monitor m2 = new Monitor("monitor #2","100W79");
        ResourceDAO.addResource(m2);

        Monitor m3 = new Monitor("monitor #3","100W77");
        ResourceDAO.addResource(m3);

        Monitor m4 = new Monitor("monitor #4","100W76");
        ResourceDAO.addResource(m4);
      
        //--------------------------------------------------------------------------------
        // client table filling
        //--------------------------------------------------------------------------------

        Client c1 = new Client("boss", "pass", Client.RATINGS.HIGH, "boss@mail.ru");
        ClientDAO.addClient(c1);

        Client c2 = new Client("manager", "manager", Client.RATINGS.MIDDLE, "manager@mail.ru");
        ClientDAO.addClient(c2);

        Client c3 = new Client("developer #1", "developer1", Client.RATINGS.LOW, "developer1@mail.ru");
        ClientDAO.addClient(c3);

        Client c4 = new Client("developer #2", "developer2", Client.RATINGS.LOW, "developer2@mail.ru");
        ClientDAO.addClient(c4);
        
        //--------------------------------------------------------------------------------
        // reservation table filling
        //--------------------------------------------------------------------------------

        //---DinnerRoom---
        Reservation res1 = new Reservation(dr, new Date(" 2011/06/03 10:00"), new Date("2011/06/03 11:00"));
        res1.getClients().add(c1);
        ReservationDAO.addReservation(res1);
     
        Reservation res2 = new Reservation(dr, new Date(" 2011/06/03 12:00"), new Date("2011/06/03 14:00"));
        res2.getClients().add(c2);
        ReservationDAO.addReservation(res2);

        Reservation res3 = new Reservation(dr, new Date(" 2011/06/03 14:00"), new Date("2011/06/03 15:00"));
        res3.getClients().add(c1);
        ReservationDAO.addReservation(res3);
        
        Reservation res4 = new Reservation(dr, new Date(" 2011/06/03 12:15"), new Date("2011/06/03 12:45"));
        res4.getClients().add(c1);
        ReservationDAO.addReservation(res4);
        
        Reservation res5 = new Reservation(dr, new Date(" 2011/06/03 13:20"), new Date("2011/06/03 13:45"));
        res5.getClients().add(c3);
        ReservationDAO.addReservation(res5);
        
        Reservation res6 = new Reservation(dr, new Date(" 2011/06/03 14:20"), new Date("2011/06/03 15:00"));
        res6.getClients().add(c2);
        ReservationDAO.addReservation(res6);
        
        Reservation res7 = new Reservation(dr, new Date(" 2011/06/03 14:00"), new Date("2011/06/03 15:00"));
        res7.getClients().add(c4);
        ReservationDAO.addReservation(res7);
        
        Reservation res8 = new Reservation(dr, new Date(" 2011/06/03 14:15"), new Date("2011/06/03 14:50"));
        res8.getClients().add(c1);
        ReservationDAO.addReservation(res8);
        
        Reservation res9 = new Reservation(dr, new Date(" 2011/06/03 11:00"), new Date("2011/06/03 11:20"));
        res9.getClients().add(c2);
        ReservationDAO.addReservation(res9);
        
        Reservation res10 = new Reservation(dr, new Date(" 2011/06/03 16:00"), new Date("2011/06/03 16:40"));
        res10.getClients().add(c3);
        ReservationDAO.addReservation(res10);

        //---EnglishRoom---
        Reservation res11 = new Reservation(er, new Date(" 2011/06/03 12:00"), new Date("2011/06/03 12:00"));
        res11.getClients().add(c1);
        res11.getClients().add(c2);
        res11.getClients().add(c3);
        res11.getClients().add(c4);
        ReservationDAO.addReservation(res11);

        //--------Monitors-----------------
        Reservation res12 = new Reservation(m1, new Date(" 2011/06/03 00:00"), new Date("2012/01/01 00:00"));
        res12.getClients().add(c2);
        ReservationDAO.addReservation(res12);

        Reservation res13 = new Reservation(m2, new Date(" 2011/06/03 00:00"), new Date("2012/01/01 00:00"));
        res13.getClients().add(c3);
        ReservationDAO.addReservation(res13);

        Reservation res14 = new Reservation(m3, new Date(" 2011/06/03 00:00"), new Date("2012/01/01 00:00"));
        res14.getClients().add(c4);
        ReservationDAO.addReservation(res14);

        //------------MeetingRoom-----------
        Reservation res15 = new Reservation(mr, new Date(" 2011/06/03 10:00"), new Date("2011/06/03 11:00"));
        res15.getClients().add(c3);
        ReservationDAO.addReservation(res15);

        Reservation res16 = new Reservation(mr, new Date(" 2011/06/03 11:15"), new Date("2011/06/03 12:00"));
        res16.getClients().add(c2);
        ReservationDAO.addReservation(res16);

        Reservation res17 = new Reservation(mr, new Date(" 2011/06/03 13:00"), new Date("2011/06/03 14:00"));
        res17.getClients().add(c4);
        ReservationDAO.addReservation(res17);

        Reservation res18 = new Reservation(mr, new Date(" 2011/06/03 16:00"), new Date("2011/06/03 17:00"));
        res18.getClients().add(c1);
        ReservationDAO.addReservation(res18);

        //-------------------------------------------------------------------
        //All reservations by Date with Showing Cliet
        //-------------------------------------------------------------------

        Client c = new Client();
        c=ClientDAO.getClientByLogin("boss");
        System.out.println(c);
 
        System.out.println("Все брони за день");
        Date d1=new Date("2011/06/03");


        System.out.println("Все брони за день DinningRoom");
        ArrayList<Client> clients = ClientDAO.getAllClients();
           for (Client cl: clients) {
             List<Reservation> list = ReservationDAO.getReservationByDateAndResourceAndClient(d1,dr,cl);
              for( Reservation rn : list) {
                 System.out.println(rn+" "+cl.getLogin());
              }
               System.out.println("--------");
         }
       
        //-------------------------------------------------------------------
        //All resources
        //-------------------------------------------------------------------

        System.out.println("All resources");
        ArrayList<Resource> resources = ResourceDAO.getAllResources();
           for (Resource re: resources) {
            System.out.println(re.getResource_name());
         }

        //-------------------------------------------------------------------
        //Add new reservation
        //-------------------------------------------------------------------

        System.out.println("Add new reservation");
        Date time_start= new Date("2011/06/03 10:00");
        Date time_end=  new Date("2011/06/03 19:00");


        Reservation res = new Reservation(er,time_start,time_end);
        res.getClients().add(c1);
        int i=0;

        //m1 - monitor
        if (!res.getStart_time().before(new Date()))
        {
            List<Reservation> list = ReservationDAO.getReservationByDateAndResource(d1,dr);
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
        if (i>dr.getMaxCapacity())
        {
           System.out.println("Time cross!");
        }
        else{
            ReservationDAO.addReservation(res);
            System.out.println("Reservation added!");
        }
        }
         else
             System.out.println("You can't reservate in PAST!");

       
         //ReservationDAO.deleteReservation(res2);

        //-------------------------------------------------------------------
        //Just check how it works
        //-------------------------------------------------------------------
         Date d2=new Date("2011/06/03");
           for (Client cl: clients) {
             List<Reservation> list = ReservationDAO.getReservationByDateAndResourceAndClient(d2,dr,cl);
              for( Reservation rn : list) {
                 System.out.println(rn+" "+cl.getLogin());
              }
           }

         }

    }

