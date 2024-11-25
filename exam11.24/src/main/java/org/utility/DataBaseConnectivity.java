import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseConnectivity {
    public static SessionFactory getSessionFactory() {
        //Read the Configuration file
        Configuration configuration = new Configuration();
        configuration.configure("G:\\Vikas\\Testing\\src\\main\\resources\\hibernate-mapping\\hibernate.cfg.xml");

        //Create SessionFactory Object from configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        System.out.println("Session Factory Execution Completed, " +
                "Connection established successfully, all the table created...");
        return sessionFactory;
    }
}