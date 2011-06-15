package utils;

import model.Resource;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.PrintStream;

/**
 * data access object to resource table
 * 
 * @author rsamoylov & smihajlenko
 */
public class ResourceDAO {
	private static PrintStream errStream = System.err;

	public static void addResource(Resource r) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;	
		try {
			tx = sess.beginTransaction();
			sess.save(r);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't add new resource");
		} finally {
			sess.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Resource> getAllResources() {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;	
		ArrayList<Resource> resources = new ArrayList<Resource>();
		try {
			tx = sess.beginTransaction();
			resources = (ArrayList<Resource>) sess.createCriteria(Resource.class).list();
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't show all resources");
		} finally {
			sess.close();
		}
		return resources;
	}

	public static void updateResource(Resource r) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;	
		try {
			tx = sess.beginTransaction();
			sess.update(r);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't update resource");
		} finally {
			sess.close();
		}
	}

	public static Resource getResourceById(Long id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;	
		Resource r = null;
		try {
			tx = sess.beginTransaction();
			r = (Resource) sess.get(Resource.class, id);
			sess.getTransaction().commit();
					} catch (Exception e) {
			if (tx!=null) tx.rollback();
			errStream.print("Cann't get resource by ID");
		} finally {
			sess.close();
		}
		return r;
	}
	
	 public static void deleteResourceById(Long id)
	    {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createSQLQuery("delete from reservation where resource_fk=?;").setParameter(0, id);
			query.executeUpdate();
			Query query2 = sess.createSQLQuery("delete from client where resource_id=?;").setParameter(0, id);
			query2.executeUpdate();
			sess.getTransaction().commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			errStream.print("Cann't delete resource by ID");
		} finally {
			sess.close();
		}
	}
}
