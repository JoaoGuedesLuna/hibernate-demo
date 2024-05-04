package dev.guedes.hibernatedemo.database;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author João Guedes
 */
public class Database {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws DatabaseException {
        try {
            if(Database.sessionFactory == null) {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                Database.sessionFactory = configuration.buildSessionFactory();
            }
            else if (Database.sessionFactory.isClosed()) {
                Database.sessionFactory = null;
                return Database.getSessionFactory();
            }
            return Database.sessionFactory;
        }
        catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
