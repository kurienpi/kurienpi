package com.zorba.dao;

import com.zorba.model.User;  // Make sure this import is present
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.zorba.util.HibernateUtil;
import javax.persistence.*;

public class UserDAO {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}