package utils;

import model.Client;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
/**
 * data access object to client table
 * @author rsamoylov
 */
public class ClientDAO
{
    public static void addClient(Client c)
    {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        sess.save(c);

        sess.getTransaction().commit();
    }

    public static ArrayList<Client> getAllClients()
    {
        ArrayList<Client> clients = new ArrayList<Client>();

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        clients = (ArrayList<Client>) sess.createCriteria(Client.class).list();

        sess.getTransaction().commit();

        return clients;
    }

    public static void updateClient(Client c)
    {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        sess.update(c);

        sess.getTransaction().commit();
    }

    public static Client getClientById(Long id)
    {
        Client c;

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        c = (Client) sess.get(Client.class, id);

        sess.getTransaction().commit();

        return c;
    }

        public static Client getClientByLogin(String login)
    {
        Client c;

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        Query query = sess.getNamedQuery("getClientByLogin").setParameter("login",login);
         c = (Client)query.uniqueResult();
        sess.getTransaction().commit();

        return c;
    }
}
