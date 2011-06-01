package utils;

import entities.Resource;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 * data access object to resource table
 * @author rsamoylov
 */
public class ResourceDAO
{
    public static void addResource(Resource r)
    {
        try{
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.save(r);

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public static ArrayList<Resource> getAllResources()
    {

        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        resources = (ArrayList<Resource>) sess.createCriteria(Resource.class).list();

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return resources;
    }

    public static void updateResource(Resource r)
    {
        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        sess.update(r);

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static Resource getResourceById(Long id)
    {
        Resource r=null;

        try {
        Session sess = HibernateUtil.getSession();
        sess.beginTransaction();

        r = (Resource) sess.get(Resource.class, id);

        sess.getTransaction().commit();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return r;
    }
}
