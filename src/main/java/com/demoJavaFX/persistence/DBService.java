package com.demoJavaFX.persistence;

import com.demoJavaFX.model.Case;
import com.demoJavaFX.model.Skill;
import com.demoJavaFX.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class DBService {
    //private static final Logger logger = LogManager.getLogger(DBService.class.getName());
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory getSessionFactory() {
        try {
            // Создает сессию с hibernate.cfg.xml
            Configuration configuration = new Configuration();
            //configuration.configure(new File("./hibernate.cfg.xml")); //велосипед для jar запуска
            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Skill.class);
            configuration.addAnnotatedClass(Case.class);
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
