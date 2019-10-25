package com.demoJavaFX.persistence;

import com.demoJavaFX.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    //private static final Logger logger = LogManager.getLogger(DBService.class.getName());
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory getSessionFactory() {
        try {
            // Создает сессию с hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory= configuration.buildSessionFactory(builder.build());
        }
        catch (Exception ex) {
            //logger.info(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        return sessionFactory;
    }

}
