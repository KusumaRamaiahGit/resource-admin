package utils;

import model.Reservation;
import model.Resource;
import model.Client;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.PrintStream;

/**
 * data access object to reservation table
 * 
 * @author rsamoylov & smihajlenko & Martynenko Viktoria
 */
public class ReservationDAO {
	private static PrintStream errStream = System.err;

	public static void addReservation(Reservation r) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;		
		try {
			tx = sess.beginTransaction();
			sess.save(r);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx != null)	tx.rollback();
			errStream.print("Cann't add new reservation!");
		} finally {
			sess.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Reservation> getAllReservations() {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			tx = sess.beginTransaction();
			reservations = (ArrayList<Reservation>) sess.createCriteria(Reservation.class).list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't show all reservations!"); 
		} finally {
			sess.close();
		}
		return reservations;
	}

	public static void updateReservations(Reservation r) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;		
		try {
			tx = sess.beginTransaction();
			sess.update(r);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't update reservation!!"); 
		} finally {
			sess.close();
		}
	}

	public static void deleteReservation(Reservation r) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;		
		try {
			tx = sess.beginTransaction();			
			Query query2 = sess.createSQLQuery("delete from reservation where reservation_id=?;")
					.setParameter(0, r.getReservation_id());
			query2.executeUpdate();			
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't delete reservation!");  
		}		
	}

	public static Reservation getReservationById(Long id) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Reservation r = null;		
		try {
			tx = sess.beginTransaction();
			r = (Reservation) sess.get(Reservation.class, id);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservation by ID!");  
		} finally {
			sess.close();
		}
		return r;
	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResourceAndClient( Calendar start_time, Resource resource, Client client) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource_and_Client")
					.setParameter(0, start_time).setParameter(1, start_time)
					.setParameter(2, resource).setParameter(3, client);			
			list = query.list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservation by resource and client!");   
		} finally {
			sess.close();
		}		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationAllByClient(Client client) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("FindReservation_All_by_Client")
					.setParameter(0, client);			
			list = query.list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservation by  client!");  				
		} finally {
			sess.close();
		}		
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationAllAfterNowByClient(Client client) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("FindReservation_All_After_Now_by_Client")
					.setParameter(0, client);			
			list = query.list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservation after now by client!");   
		} finally {
			sess.close();
		}		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationInTime(Resource resource, Calendar start_time, Calendar end_time) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;		
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("FindReservation_ALL_in_Time")
					.setParameter("resource_fk", resource)
					.setParameter("start_time", start_time)
					.setParameter("end_time", end_time);			
			list = query.list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservationsin in time!");  
		} finally {
			sess.close();
		}		
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static Long getReservationCurrentCount(Resource resource, Calendar start_time, Calendar end_time) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Long count = 0L;		
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("Count_Reservation_in_Time")
					.setParameter(0, resource).setParameter(1, start_time)
					.setParameter(2, end_time).setParameter(3, start_time)
					.setParameter(4, end_time);
			List list = query.list();
			count = (Long) list.get(0);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't count current reservations in time!");  
		} finally {
			sess.close();		
		}		
		return count;
	}

	@SuppressWarnings("rawtypes")
	public static Long getReservationAllCount(Resource resource, Calendar start_time, Calendar end_time) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Long count = 0L;		
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("Get_Reservations_Count");
			List list = query.list();
			count = (Long) list.get(0);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't count all reservations!");  
		} finally {
			sess.close();
		}		
		return count;
	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByDateAndResource(Calendar start_date, Resource resource) {		
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;		
		try {
			tx = sess.beginTransaction();			
			Query query = sess.getNamedQuery("FindReservation_ALL_of_Day_by_Resource")
					.setParameter(0, start_date).setParameter(1, resource);			
			list = query.list();			
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get reservations by resurce and date!");   
		} finally {
			sess.close();
		}		
		return list;
	}


//functions for statistic, author - Martynenko Viktoria
	
	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationsByResourceAndClient(Resource resource,Client c) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;		
		try {
			tx = sess.beginTransaction();	
			Query query = sess.getNamedQuery("FindReservation_by_Resource_and_Client")
					.setParameter(0, resource).setParameter(1, c);
			list = query.list();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get all reservations by client and resource!");
		} finally {
			sess.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Reservation> getReservationByResource(Resource resource) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		List<Reservation> list = null;		
		try {
			tx = sess.beginTransaction();	
			Query query = sess.getNamedQuery("FindReservation_by_Resource")
					.setParameter(0, resource);
			list = query.list();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get all reservations by resource!");
		} finally {
			sess.close();
		}
		return list;
	}
	
	public static Long getMillisInPeriod(Calendar start,Calendar end){
		Long mills=(end.getTimeInMillis()-start.getTimeInMillis());		
		return mills;
	}
	public static Long getMinutesInPeriod(Calendar start,Calendar end){
		Long minutes=(end.getTimeInMillis()-start.getTimeInMillis())/60000;
		return minutes;
	}
	public static Long getReservedTimeForResource(Resource resource){
		List<Reservation> res=getReservationByResource(resource);
		return timeUnion(res);
	}
	public static Long getReservedTimeByResourceAndClient(Resource resource,Client c){
		List<Reservation> res=getReservationsByResourceAndClient(resource,c);
		return timeUnion(res);
	}
	
	public static Long getReservedTimeForResourceByMonth(Calendar startDate,Calendar endDate,Resource resource){
		List<Reservation> res=getReservationInTime(resource,startDate,endDate);		
		List<Reservation> res1=new ArrayList<Reservation>();//создаем другой массив, чтоб не менять в базе конец резервации
		for(Reservation r:res) {
			res1.add(new Reservation(r.getResource(),r.getStart_time(),r.getEnd_time(),r.getClient()));
		}
		if(res1.size()>0){
			Calendar c1=new GregorianCalendar();Calendar c2=new GregorianCalendar();
			for(Reservation r:res1) {
				if(r.getStart_time().compareTo(startDate)<0) {//если начало резервации меньше указанного месяца, не учитываем время резервации перед месяцем
					c1.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), 1, 0, 0, 0);				
					r.setStart_time(c1);//началу резервации присваиваем начало заданного месяца
				}
				if(r.getEnd_time().compareTo(endDate)>0) {//если конец резервации превышает указанный месяц, не учитываем время резервации после окончания месяца
					c2.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), 1, 0, 0, 0);
					r.setEnd_time(c2);//концу резервации присваиваем конец заданного месяца
				}					
			}
		}
		return timeUnion(res1);
	}
	public static Long timeUnion(List<Reservation> res) {
		Long time=new Long(0);
		Integer i1=0;
		time=Long.parseLong(i1.toString()) ;
		if(res.size()>0){
			//Calendar startDate=res.get(0).getStart_time().getTime();
			Calendar startD=new GregorianCalendar();
			Calendar endD=new GregorianCalendar();
			startD=res.get(0).getStart_time();//начало промежутка зарезервированного времени
			endD=res.get(0).getEnd_time();//конец промежутка зарезервированного времени
			Calendar start=new GregorianCalendar();Calendar end=new GregorianCalendar();
			start.set(startD.get(Calendar.YEAR), startD.get(Calendar.MONTH),startD.get(Calendar.DATE),startD.get(Calendar.HOUR_OF_DAY), startD.get(Calendar.MINUTE));
			end.set(endD.get(Calendar.YEAR), endD.get(Calendar.MONTH),endD.get(Calendar.DATE),endD.get(Calendar.HOUR_OF_DAY), endD.get(Calendar.MINUTE));
			int i=0;
			for(Reservation r:res){
				if(end.compareTo(r.getStart_time())>=0) {//если конец текущего промежутка зарезервированного времени>=начало следующей резервации 
					if(r.getEnd_time().getTimeInMillis()>end.getTimeInMillis())
						endD=r.getEnd_time();//концу текущего промежутка зарезервированного времени присвоить конец следующей резервации
					end.set(endD.get(Calendar.YEAR), endD.get(Calendar.MONTH),endD.get(Calendar.DATE),endD.get(Calendar.HOUR_OF_DAY), endD.get(Calendar.MINUTE));
					if(i==(res.size()-1)) {//если дошли до последнего элемента в списке
						time+=getMillisInPeriod(start,end);//увеличим время						
					}					
				}				
				else {
					time+=getMillisInPeriod(start,end);//увеличим время
					startD=r.getStart_time();//начало следующего промежутка зарезервированного времени
					endD=r.getEnd_time();//конец следующего промежутка зарезервированного времени										
					start.set(startD.get(Calendar.YEAR), startD.get(Calendar.MONTH),startD.get(Calendar.DATE),startD.get(Calendar.HOUR_OF_DAY), startD.get(Calendar.MINUTE));
					end.set(endD.get(Calendar.YEAR), endD.get(Calendar.MONTH),endD.get(Calendar.DATE),endD.get(Calendar.HOUR_OF_DAY), endD.get(Calendar.MINUTE));
				}				
				i++;
			}
		}
		return time;
	}
}