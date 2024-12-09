package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PassengerDAO {
    // Save General Passenger
    public void saveGeneralPassenger(GeneralPassenger passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start transaction
            transaction = session.beginTransaction();

            // Save the passenger
            session.save(passenger);

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Save Senior Citizen Passenger
    public void saveSeniorCitizenPassenger(SeniorCitizenPassenger passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(passenger);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Save Physically Handicapped Passenger
    public void savePhysicallyHandicappedPassenger(PhysicallyHandicappedPassenger passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(passenger);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Fetch Physically Handicapped Passengers
    public List<PhysicallyHandicappedPassenger> getPhysicallyHandicappedPassengers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM PhysicallyHandicappedPassenger",
                    PhysicallyHandicappedPassenger.class
            ).getResultList();
        }
    }
}