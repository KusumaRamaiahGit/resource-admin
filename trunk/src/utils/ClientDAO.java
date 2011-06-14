package utils;

import model.Client;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * data access object to client table
 *
 * @author rsamoylov
 */
public class ClientDAO {
	public static void addClient(Client c) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.save(c);
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not add new user!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Client> getAllClients() {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			tx = sess.beginTransaction();
			clients = (ArrayList<Client>) sess.createCriteria(Client.class).list();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not show all users!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
		return clients;
	}

	public static void updateClient(Client c) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.update(c);
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not update user!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
	}

	public static Client getClientById(Long id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Client c = null;
		try {
			tx = sess.beginTransaction();
			c = (Client) sess.get(Client.class, id);
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get user by ID!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
		return c;
	}

	public static Client getClientByLogin(String login) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Client c = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("getClientByLogin").setParameter("login", login);
			c = (Client) query.uniqueResult();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get user by login!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
		return c;
	}

	public static List<Client> getUnauthorizedClients() {
                List<Client> list = null;
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;		
		try {
			tx = sess.beginTransaction();
			Query query = sess.getNamedQuery("getUnauthorizedClients");			
                        list = query.list();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get unauthorized clientsn!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
		return list;
	}
}
