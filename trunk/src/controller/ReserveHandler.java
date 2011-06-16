/**
 * @author EDudnik
 *
 */
package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import model.Client;
import model.Countable;
import model.Reservation;
import model.Resource;
import utils.ClientComparator;
import utils.DatePairs;
import utils.ReservationDAO;
import utils.ResourceDAO;

//import bitel.billing.common.TimeUtils;


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
	public boolean addReservation(long res_id, GregorianCalendar dateOfStart, GregorianCalendar dateOfEnd, Client cl, PrintWriter out) {
		// TODO Auto-generated method stub
		//Long resource_id=res_id;
		Calendar curr_date=Calendar.getInstance();
		GregorianCalendar start_time=dateOfStart;
		GregorianCalendar end_time=dateOfEnd;
		Client client=cl;
		Resource resource=getResourceById(res_id);
		errStream=out;
		if(curr_date.compareTo(start_time)>0){
			return false;
		}
		else{
			List<Reservation> reserve_list=new LinkedList<Reservation>();
			reserve_list=getReservationByTime(resource, start_time, end_time);
			if(reserve_list.isEmpty()==true){
				addReservationAtLast(resource, start_time, end_time, client);
				return true;
			}
			else{
				if(resourceIsCountable(resource)==true){
					if(haveACountablePlace(resource, reserve_list, start_time, end_time)==true){
						addReservationAtLast(resource, start_time, end_time, client);
						return true;
					}
					else{
						List<Reservation> min_rang_list=getTheSmallestRangReservationList(reserve_list);
						if(min_rang_list.isEmpty()==true){
							errStream.print("You haven't enough wright to place this resource");
							return false;
							//while(haveAPlace(resource, start_time, end_time)<=0)
								//deleteMinTimeReservation(min_rang_list);
							//getTheSmallestRangReservationList(reserve_List);
							//addReservationAtLast(resource, start_time, end_time, client);
							//return true;
						}
						else{
							List<List<Reservation>> intersection_list=getResourceIntercestionList(min_rang_list, start_time, start_time);
							while(intersection_list.isEmpty()==false){
								intersection_list=deleteMinHumanReservation(intersection_list);
								//reserve_list=getReservationByTime(resource, start_time, end_time);
								//min_rang_list=getTheSmallestRangReservationList(reserve_list);
								if(haveANewCountablePlace(resource, intersection_list)==true){
									addReservationAtLast(resource, start_time, end_time, client);
									return true;
								}
							}
						}
					}			
				}
				else{
					if(checkBossPossibility(reserve_list, cl)==true){
						deleteSelectedReservationList(reserve_list);
						addReservationAtLast(resource, start_time, end_time, client);
						return true;
					/*if(haveAPlace(resource, dateOfStart, dateOfEnd)>0)
						addReservationAtLast(resource, dateOfStart, dateOfEnd, cl);
					else{
						if(checkBossPossibility(reserve_list, cl)==true){
							deleteSelectedReservationList(reserve_list);
						}*/
					}
					else{
						errStream.print("You haven't enough wright to place this resource");
						return false;
					}
				}
			}
		}
		return false;
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
	
	public boolean haveACountablePlace(Resource res, List<Reservation> reservationList, Calendar start_time, Calendar end_time){
		//long free_place=0L;
		boolean result=false;
		//DatePairs interval;
		int max_capacity;
		//List<Reservation> reserv_list=reservation_list;
		try{
			Countable countable_res=(Countable)res;
			max_capacity=countable_res.getMaxCapacity();
			//if(res instanceof Countable){
				//max_capacity=((Countable) res).getMaxCapacity();
				if((long)max_capacity-getResourseCurrentCount(res, start_time, end_time)>0)
					result=true;
				else{
					List<List<Reservation>> reserv_intersections=getResourceIntercestionList(reservationList, start_time, end_time);
					//while(reserv_list.isEmpty()==false){
						if(max_capacity-reserv_intersections.size()>0)
							result=true;
					}
				//}
			//}
			//free_place=getResourceMaxCount(res,start_time,end_time)-getResourseCurrentCount(res, start_time, end_time);
		}
		catch(Exception e){
			errStream.print("Cannot check a free place");
		}
		return result;
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
		Client example_client=new Client();
		Client.RATINGS rating=Client.RATINGS.HIGH;
		example_client.setRating(rating);
		try{
			for(Reservation reserve:reservationList){
				if(CompareClients(reserve.getClient(), example_client)<0){
					rating=reserve.getClient().getRating();
					smallestRangList.clear();
					smallestRangList.add(reserve);
				}
				if(CompareClients(reserve.getClient(), example_client)==0)
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
				if(CompareClients(reserve.getClient(), client)>0)
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
	
	public int CompareClients(Client c1, Client c2){
		return new ClientComparator().compareByRating(c1, c2);
	}
	
	public DatePairs intersectionDateIntervals (Date date1_start, Date date1_end, Date date2_start, Date date2_end){
		DatePairs result=new DatePairs();
		try{
			if(date1_start.before(date2_start)){
				result.setStart_time(date1_start);
				if(date1_end.before(date2_end))
					result.setEnd_time(date2_end);
				if(date1_end.after(date2_end))
					result.setEnd_time(date1_end);
				else
					result.setEnd_time(date1_end);
			}
			if(date1_start.after(date2_start)){
				result.setStart_time(date2_start);
				if(date1_end.before(date2_end))
					result.setEnd_time(date2_end);
				if(date1_end.after(date2_end))
					result.setEnd_time(date1_end);
				else
					result.setEnd_time(date1_end);
			}
			else{
				result.setStart_time(date1_start);
				if(date1_end.before(date2_end))
					result.setEnd_time(date2_end);
				if(date1_end.after(date2_end))
					result.setEnd_time(date1_end);
				else
					result.setEnd_time(date1_end);
			}
		}
		catch(Exception e){
			errStream.print("Cannot get date interval inersection");
		}
		return result;
	}
	
	public List<List<Reservation>> getResourceIntercestionList (List<Reservation> reserv_list, Calendar start_time, Calendar end_time){
		//List<DatePairs> date_interval_list=new ArrayList<DatePairs>();
		List<List<Reservation>> intersection_list=new LinkedList<List<Reservation>>();
		try{
			for(Reservation reserve:reserv_list){
				for(List<Reservation> reservation_list:intersection_list){
					if(reservation_list.isEmpty()==true){
						reservation_list.add(reserve);
						continue;
					}
					//for(Reservation reservation:reservation_list){
					if(intersectionIntervalList(reservation_list, reserve.getStart_time().getTime(), reserve.getEnd_time().getTime())==false){
							reservation_list.add(reserve);
					}
					else
						continue;
					//}
				}
			}
			/*for(Reservation reserve:reserv_list){
				for(int i=0;i<intersection_list.size();i++){
					for(int j=0;j<intersection_list.get(i).size();j++){
						if(intersection_list.get(i).isEmpty()==true){
							intersection_list.get(i).add(j, reserve);
							date_interval_list.add(i, new DatePairs(reserve.getStart_time().getTime(), reserve.getEnd_time().getTime()));
						}
						else{
							
						}
					}
				}
				/*for(List<Reservation> res_list:intersection_list){
					for(Reservation reserv:res_list){
						
					}
				}
			}*/
		}
		catch(Exception e){
			errStream.print("Cannot get intersection reservation list");
		}
		return intersection_list;
	}
	
	public boolean intersectionIntervals(Date date1_start, Date date1_end, Date date2_start, Date date2_end){
		boolean result=true;
		try{
			if(date1_end.before(date2_start)||date2_end.before(date1_start))
				result=false;
		}
		catch(Exception e){
			errStream.print("Cannot get date interval intersection");
		}
		return result;
	}
	
	public boolean intersectionIntervalList(List<Reservation> reserv_list,Date date_start, Date date_end){
		boolean result=true;
		try{
			for(Reservation reserv:reserv_list){
				if(intersectionIntervals(reserv.getStart_time().getTime(), reserv.getEnd_time().getTime(), date_start, date_end)==false)
					result=false;
			}
		}
		catch(Exception e){
			errStream.print("Cannot ckeck intersection reservation list");
		}
		return result;
	}
	
	public List<List<Reservation>> deleteMinHumanReservation(List<List<Reservation>> intersection_list){
		List<List<Reservation>> inter_list=intersection_list;
		List<Reservation> list=new LinkedList<Reservation>();
		int min_size=0;
		try{
			for(List<Reservation> reserv_list:inter_list){
				if(reserv_list.size()<min_size){
					min_size=reserv_list.size();
					list=reserv_list;
				}
			}
			inter_list.remove(list);
			deleteSelectedReservationList(list);
		}
		catch(Exception e){
			errStream.print("Cannot delete intersection list");
		}
		return inter_list;
	}
	
	public boolean haveANewCountablePlace(Resource res, List<List<Reservation>> intersection_list){
		boolean result=false;
		int max_capacity;
		try{
			Countable countable_res=(Countable)res;
			max_capacity=countable_res.getMaxCapacity();
			if(max_capacity-intersection_list.size()>0)
				result=true;
		}
		catch(Exception e){
			errStream.print("Cannot check a new countable place");
		}
		return result;
	}

}
