package utils;

import model.Resource;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

/**
 * data access object to resource table
 * 
 * @author rsamoylov
 */
public class ResourceDAO {

	public static void addResource(Resource r) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.save(r);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not add new resource!",
					JOptionPane.OK_OPTION);
		}

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Resource> getAllResources() {

		ArrayList<Resource> resources = new ArrayList<Resource>();

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			resources = (ArrayList<Resource>) sess.createCriteria(Resource.class).list();

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not show all resources!",
					JOptionPane.OK_OPTION);
		}

		return resources;
	}

	public static void updateResource(Resource r) {
		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			sess.update(r);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not update resource!",
					JOptionPane.OK_OPTION);
		}
	}

	public static Resource getResourceById(Long id) {
		Resource r = null;

		try {
			Session sess = HibernateUtil.getSession();
			sess.beginTransaction();

			r = (Resource) sess.get(Resource.class, id);

			sess.getTransaction().commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Could not get resource by ID!",
					JOptionPane.OK_OPTION);
		}

		return r;
	}
}
