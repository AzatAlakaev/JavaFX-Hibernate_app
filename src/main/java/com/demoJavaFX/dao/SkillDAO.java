package com.demoJavaFX.dao;

import com.demoJavaFX.model.Skill;
import com.demoJavaFX.persistence.DBService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SkillDAO implements DAO<Skill> {

    @Override
    public Skill findById(int id) {
        Skill skill= DBService.getSessionFactory().openSession().get(Skill.class, id);
        //logger.info("успешно найден Skill " + skill);
        return skill;
    }

    @Override
    public void save(Skill skill) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(skill);
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
    public void update(Skill skill) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Skill skill) {
        Session session=DBService.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        session.delete(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> skills = (List<Skill>)  DBService.getSessionFactory().openSession().createQuery("From Skill").list();
        return skills;
    }

    public Skill findByName(String name) {
        try {
            Session session = DBService.getSessionFactory().openSession();
            String queryString = "from Skill where name = :name";
            Query queryObject = session.createQuery(queryString);
            queryObject.setParameter("name", name);
            Skill skill = (Skill) queryObject.getSingleResult();
            session.close();
            // logger.info("Skill найден " + skill);
            return skill;
        }
        catch (Exception e) {
            //logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

}
