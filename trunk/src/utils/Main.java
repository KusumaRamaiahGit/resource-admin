
package utils;
import org.hibernate.Session;
public class Main {
    public static void main(String[] args) {
 Session sess = HibernateUtil.getSessionFactory().openSession();
  DatabaseUtil.FillDatabase();
sess.close();
    }

}
