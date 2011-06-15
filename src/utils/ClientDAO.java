package utils;

import model.Client;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import java.io.PrintStream;

/**
 * data access object to client table
 *
 * @author rsamoylov & smihajlenko
 */
public class ClientDAO {
	private static PrintStream errStream = System.err;
	
	public static void addClient(Client c) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.save(c);
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			errStream.print("Cann't add new user");
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
			errStream.print("Cann't show all users");
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
			errStream.print("Cann't update user");
		} finally {
			sess.close();
		}
	}
	
	public static void deleteClientById(Long id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createSQLQuery("delete from reservation where client_fk=?;").setParameter(0, id);
			query.executeUpdate();
			Query query2 = sess.createSQLQuery("delete from client where client_id=?;").setParameter(0, id);
			query2.executeUpdate();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			errStream.print("Cann't delete user by ID");
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
			errStream.print("Cann't get user by ID");
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
			errStream.print("Cann't get user by login");
		} finally {
			sess.close();
		}
		return c;
	}

	@SuppressWarnings("unchecked")
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
			errStream.print("Cann't get unregistered clients");
		} finally {
			sess.close();
		}
		return list;
	}
}
