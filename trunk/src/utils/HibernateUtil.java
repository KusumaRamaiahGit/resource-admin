package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 * @author smihaylenko
 */
@SuppressWarnings("deprecation")
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static {
        try {            
            /*
             * Build a SessionFactory object from session-factory configuration 
             * defined in the hibernate.cfg.xml file. In this file we register 
             * the JDBC connection information, connection pool, the hibernate 
             * dialect that we used and the mapping to our hbm.xml file for each 
             * POJO (Plain Old Java Object).
             * 
             */
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object." 
                + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        // Close caches and connection pools
       getSessionFactory().close();
    }
}


