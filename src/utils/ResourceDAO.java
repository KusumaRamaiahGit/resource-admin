package ourproject;

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
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        sess.save(r);

        sess.getTransaction().commit();

    }

    public static ArrayList<Resource> getAllResources()
    {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        resources = (ArrayList<Resource>) sess.createCriteria(Resource.class).list();

        sess.getTransaction().commit();

        return resources;
    }

    public static void updateResource(Resource r)
    {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        sess.update(r);

        sess.getTransaction().commit();
    }

    public static Resource getResourceById(Long id)
    {
        Resource r;
        
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        r = (Resource) sess.get(Resource.class, id);

        sess.getTransaction().commit();

        return r;
    }
}
