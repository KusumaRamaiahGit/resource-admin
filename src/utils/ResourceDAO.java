package utils;

import model.Resource;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * data access object to resource table
 * 
 * @author rsamoylov
 */
public class ResourceDAO {

	public static void addResource(Resource r) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;	
		try {
			tx = sess.beginTransaction();
			sess.save(r);
			sess.getTransaction().commit();			
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not add new resource!", JOptionPane.OK_OPTION);
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
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not show all resources!", JOptionPane.OK_OPTION);
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
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not update resource!", JOptionPane.OK_OPTION);
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
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get resource by ID!", JOptionPane.OK_OPTION);
		} finally {
			sess.close();
		}
		return r;
	}
	
	 public static void deleteResource(Resource r)
    {
    	 try {
             Session sess = HibernateUtil.getSession();
             sess.beginTransaction();

             sess.delete(r);

             sess.getTransaction().commit();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
