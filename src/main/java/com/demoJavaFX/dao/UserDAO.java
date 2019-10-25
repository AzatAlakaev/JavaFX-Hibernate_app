package com.demoJavaFX.dao;

import com.demoJavaFX.model.User;
import com.demoJavaFX.persistence.DBService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO implements DAO<User> {
    //private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());
    @Override
    public User findById(int id) {
        User user= DBService.getSessionFactory().openSession().get(User.class, id);
        //logger.info("успешно найден User " + user);
        return user;
    }

    public User findByName(String name) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            String queryString = "from User where name = :name";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter("name", name);
            User user = (User) queryObject.getSingleResult();
            session.close();
           // logger.info("User найден " + user);
            return user;
        }
        catch (Exception e) {
            //logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User user) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch (RuntimeException e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }

            session.close();
        //logger.info("успешно сохранен User " + user);
    }
    @Override
    public void update(User user) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
       // logger.info("успешно обновлен User " + user);
    }
    @Override
    public void delete(User user) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
       // logger.info("успешно удален User " + user);
    }

    public void clearTable() {
        Session session = DBService.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
        //logger.info("Таблица users очищена");
    }

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>)  DBService.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }


}
