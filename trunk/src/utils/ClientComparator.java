package utils;

import java.util.Comparator;
import model.Client;

public class ClientComparator implements Comparator<Client>{

	@Override
	public int compare(Client c1, Client c2) {
		return 0;
	}
	
	public long compareById(Client c1, Client c2){
		 return c1.getClient_id().compareTo(c2.getClient_id());
	}
	
	public int compareByLogin(Client c1, Client c2){
		return c1.getLogin().compareTo(c2.getLogin());
	}
	
	public int compareByRating(Client c1, Client c2){
		return c1.getRating().compareTo(c2.getRating());
	}
	
	public int compareByLocation(Client c1, Client c2){
		return c1.getLocation().compareTo(c2.getLocation());
	}

	public int compareByRatingMax(Client c1){
		return c1.getRating().compareTo(Client.RATINGS.HIGH);
	}
	
	public int compareByLoginMax(Client c1){
		return c1.getLogin().hashCode();
	}
	
	public int compareByLocationMax(Client c1){
		return c1.getLocation().compareTo(Client.LOCATIONS.KYIV);
	}
	
}
