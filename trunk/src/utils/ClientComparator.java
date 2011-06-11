package utils;

import java.util.ArrayList;
import java.util.Comparator;
import model.Client;
import java.util.Collections;

public class ClientComparator implements Comparator<Client> {

	@Override
	public int compare(Client c1, Client c2) {
		return compareByLogin(c1, c2);
	}

	public long compareById(Client c1, Client c2) {
		return c1.getClient_id().compareTo(c2.getClient_id());
	}

	public int compareByLogin(Client c1, Client c2) {
		return c1.getLogin().compareTo(c2.getLogin());
	}

	public int compareByRating(Client c1, Client c2) {
		return c1.getRating().compareTo(c2.getRating());
	}

	public int compareByLocation(Client c1, Client c2) {
		return c1.getLocation().compareTo(c2.getLocation());
	}

	public int compareByIdMax(Client c1) {
		ArrayList<Client> clients = ClientDAO.getAllClients();
		return c1.getClient_id().compareTo(clients.get(clients.size()).getClient_id());
	}

	public int compareByRatingMax(Client c1) {
		return c1.getRating().compareTo(Client.RATINGS.HIGH);
	}

	public int compareByLoginMax(Client c1) {
		Client firstClient = null;
		ClientComparator comp = new ClientComparator();
		ArrayList<Client> clients = ClientDAO.getAllClients();
		firstClient = Collections.min(clients, comp);
		return c1.getLogin().compareTo(firstClient.getLogin());
	}

	public int compareByLocationMax(Client c1) {
		return c1.getLocation().compareTo(Client.LOCATIONS.KYIV);
	}

	public int compareByRatingMin(Client c1) {
		return c1.getRating().compareTo(Client.RATINGS.LOW);
	}

	public int compareByLoginMin(Client c1) {
		Client lastClient = null;
		ClientComparator comp = new ClientComparator();
		ArrayList<Client> clients = ClientDAO.getAllClients();
		lastClient = Collections.max(clients, comp);
		return c1.getLogin().compareTo(lastClient.getLogin());
	}

	public int compareByLocationMin(Client c1) {
		return c1.getLocation().compareTo(Client.LOCATIONS.ODESSA);
	}

	public int compareByIdMin(Client c1) {
		return c1.getClient_id().compareTo(1L);
	}

}
