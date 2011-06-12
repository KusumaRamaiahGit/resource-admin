package utils;

import model.Client;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Query;

/**
 * data access object to client table
 * 
 * @author rsamoylov
 */
public class ClientDAO {
	public static void addClient(Client c) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.save(c);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not add new user!",
					JOptionPane.OK_OPTION);
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Client> getAllClients() {
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			clients = (ArrayList<Client>) sess.createCriteria(Client.class).list();

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not show all users!",
					JOptionPane.OK_OPTION);
		}
		return clients;
	}

	public static void updateClient(Client c) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.update(c);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not update user!",
					JOptionPane.OK_OPTION);
		}
	}

	public static Client getClientById(Long id) {
		Client c = null;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			c = (Client) sess.get(Client.class, id);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get user by ID!",
					JOptionPane.OK_OPTION);
		}
		return c;
	}

	public static Client getClientByLogin(String login) {
		Client c = null;
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			Query query = sess.getNamedQuery("getClientByLogin").setParameter("login", login);
			c = (Client) query.uniqueResult();
			
			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get user by login!",
					JOptionPane.OK_OPTION);
		}
		return c;
	}
}
