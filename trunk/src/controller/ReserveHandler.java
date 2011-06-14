/**
 * 
 */
package controller;

import java.util.LinkedList;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.io.PrintWriter;
//import antlr.collections.List;
import java.util.List;

import utils.ReservationDAO;
import utils.ResourceDAO;

import model.Client;
import model.Countable;
import model.Reservation;
import model.Resource;

/**
 * @author EDudnik
 *
 */
public class ReserveHandler implements IReserveHadler{
	
	private PrintWriter errStream;
	/*private GregorianCalendar start_time=null; 
	private GregorianCalendar end_time=null;
	private Client client=null;
	private Long resource_id=0L;
	private Resource resource=null;
	private List<Reservation> reserve_List = null;
	private Reservation reservation;*/
	@Override
	public void addReservation(long res_id, GregorianCalendar dateOfStart, GregorianCalendar dateOfEnd, Client cl, PrintWriter out) {
		// TODO Auto-generated method stub
		/*Long resource_id=res_id;
		GregorianCalendar start_time=dateOfStart;
		GregorianCalendar end_time=dateOfEnd;
		Client client=cl;*/
		Resource resource=getResourceById(res_id);
		errStream=out;
		List<Reservation> reserve_list=new LinkedList<Reservation>();
		reserve_list=getReservationByTime(resource, dateOfStart, dateOfEnd);
		if(reserve_list.isEmpty()==true){
			addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
		}
		else{
			if(resourceIsCountable(resource)==true){
				if(haveAPlace(resource, dateOfStart, dateOfEnd)>0)
					addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
				else{
					List<Reservation> min_rang_list=getTheSmallestRangReservationList(reserve_list);
					if(checkBossPossibility(min_rang_list, cl)==true){
						while(haveAPlace(resource, dateOfStart, dateOfEnd)<=0)
							deleteMinTimeReservation(min_rang_list);
						//getTheSmallestRangReservationList(reserve_List);
						addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
					}
					else
						errStream.print("You haven't enough wright to place this resource");
				}			
			}
			else{
				if(checkBossPossibility(reserve_list, cl)==true){
					deleteSelectedReservationList(reserve_list);
					addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
				/*if(haveAPlace(resource, dateOfStart, dateOfEnd)>0)
					addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
				else{
					if(checkBossPossibility(reserve_list, cl)==true){
						deleteSelectedReservationList(reserve_list);
					}*/
				}
				else
					errStream.print("You haven't enough wright to place this resource");
			}
		}
	}
		

	public List<Reservation> getReservationByTime(Resource res, Calendar start_time, Calendar end_time){
		List<Reservation> list = null;
		try{
			list=ReservationDAO.getReservationInTime(res, start_time, end_time);
		}
		catch(Exception e){
			errStream.print("Cannot get reservation by time");
		}
		return list;
	}
	
	public long getResourseCurrentCount(Resource res, Calendar start_time, Calendar end_time){
		Long count = 0L;
		try{
			count=ReservationDAO.getReservationCurrentCount(res, start_time, end_time);
		}
		catch(Exception e){
			errStream.print("Cannot get resource current count");
		}
		return count;
	}
	
	public long getResourceMaxCount(Resource res, Calendar start_time, Calendar end_time){
		Long max_count = 0L;
		try{/*
			if(res instanceof Countable){
				max_count=(long)((Countable) res).getMaxCapacity();
			}
			else
				max_count=1L;*/
			max_count=ReservationDAO.getReservationAllCount(res, start_time, end_time);
		}
		catch(Exception e){
			errStream.print("Cannot get resource max count");
		}
		return max_count;
	}
	
	public boolean ReservationIsRepeated(List<Reservation> reservationList, Client client){
		boolean result=false;
		try{
			for(Reservation reserve:reservationList){
				result=reserve.getClient().equals(client);
			}
		}
		catch(Exception e){
			errStream.print("Cannot check repeated reservation");
		}
		return result;
	}
	
	public long haveAPlace(Resource res, Calendar start_time, Calendar end_time){
		long free_place=0L;
		try{
			free_place=getResourceMaxCount(res,start_time,end_time)-getResourseCurrentCount(res, start_time, end_time);
		}
		catch(Exception e){
			errStream.print("Cannot check a free place");
		}
		return free_place;
	}
	
	public boolean resourceIsCountable(Resource res){
		boolean result=false;
		try{
			if(res instanceof Countable)
				result=true;
		}
		catch(Exception e){
			errStream.print("Cannot check if resource is countable");
		}
		return result;
	}
	
	public List<Reservation> getTheSmallestRangReservationList(List<Reservation> reservationList){
		List<Reservation> smallestRangList = new LinkedList<Reservation>();
		Client.RATINGS rating=Client.RATINGS.HIGH;
		try{
			for(Reservation reserve:reservationList){
				if(reserve.getClient().getRating().compareTo(rating)<0){
					rating=reserve.getClient().getRating();
					smallestRangList.clear();
					smallestRangList.add(reserve);
				}
				if(reserve.getClient().getRating().compareTo(rating)==0)
					smallestRangList.add(reserve);
			}
		}
		catch(Exception e){
			errStream.print("Cannot find smallest rating list");			
		}
		return smallestRangList;
	}
	
	public boolean checkBossPossibility(List<Reservation> reservationList, Client client){
		boolean result=true;
		try{
			for(Reservation reserve:reservationList){
				if(client.getRating().compareTo(reserve.getClient().getRating())<0)
					result=false;
			}
			/*if(client.getRating().compareTo(reservationList.get(0).getClient().getRating())>0){
				result=true;
			}*/
		}
		catch(Exception e){
			errStream.print("Cannot check boss possibility");
		}
		return result;
	}
	
	public void deleteMinTimeReservation(List<Reservation> reservationList){
		Reservation minTimeReservation=null;
		Long min_time=1440L;
		try{
			for(Reservation reserve:reservationList){
				if(ReservationDAO.getMinutesInPeriod(reserve.getStart_time(), reserve.getEnd_time())<=min_time){
					min_time=ReservationDAO.getMinutesInPeriod(reserve.getStart_time(), reserve.getEnd_time());
					minTimeReservation=reserve;
				}
			}
			ReservationDAO.deleteReservation(minTimeReservation);
		}
		catch(Exception e){
			errStream.print("Cannot delete min time reservation");
		}
	}
	
	public Resource getResourceById(Long res_id){
		Resource res=null;
		try{
			res=ResourceDAO.getResourceById(res_id);
		}
		catch(Exception e){
			errStream.print("Cannot get resource by id");			
		}
		return res;
	}
	
	public Reservation getReservationById(long reservation_id){
		Reservation reserve=null;
		try{
			reserve=ReservationDAO.getReservationById(reservation_id);
		}
		catch(Exception e){
			errStream.print("Cannot get reservation by id");
		}
		return reserve;
	}
	
	public void addReservationAtLast(Resource res, GregorianCalendar start_time, GregorianCalendar end_time, Client client){
		try{
			Reservation reserve=new Reservation(res, start_time, end_time, client);
			ReservationDAO.addReservation(reserve);
		}
		catch(Exception e){
			errStream.print("Cannot create a new reservation");
		}
	}
	
	public void deleteSelectedReservationList(List<Reservation> reservation_list){
		try{
			for(Reservation reserve:reservation_list){
				ReservationDAO.deleteReservation(reserve);
			}
		}
		catch(Exception e){
			errStream.print("Cannot delete selected reservation list");
		}
	}

}
