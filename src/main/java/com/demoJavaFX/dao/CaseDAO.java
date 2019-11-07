package com.demoJavaFX.dao;

import com.demoJavaFX.model.Case;
import com.demoJavaFX.persistence.DBService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CaseDAO implements DAO<Case> {

    @Override
    public Case findById(int id) {
        return DBService.getSessionFactory().openSession().get(Case.class, id);
    }

    @Override
    public void save(Case aCase) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(aCase);
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
    }

    @Override
    public void update(Case aCase) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.update(aCase);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Case aCase) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.delete(aCase);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Case> findAll() {
        return (List<Case>)  DBService.getSessionFactory().openSession().createQuery("From Case").list();
    }

    public Case findByName(String name) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            String queryString = "from Case where name = :name";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter("name", name);
            Case aCase = (Case) queryObject.getSingleResult();
            session.close();
            // logger.info("Case найден " + aCase);
            return aCase;
        }
        catch (Exception e) {
            //logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

}
