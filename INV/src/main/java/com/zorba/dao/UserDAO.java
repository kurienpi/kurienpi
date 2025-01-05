package com.zorba.dao;

import com.zorba.model.User;
import com.zorba.model.Role;
import com.zorba.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDAO {
    public boolean validateUser(User user) {
        // Email validation
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Data provided not correct...");
        }

        // Mobile validation
        if (String.valueOf(user.getMobile()).length() != 10) {
            throw new IllegalArgumentException("Mobile number incorrect...");
        }

        // Password validation
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password incorrect should be minimum 8 character...");
        }

        return true;
    }

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (validateUser(user)) {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    public User getUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        }
    }

    public void addRoleToUser(int userId, Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            user.addRole(role);
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
