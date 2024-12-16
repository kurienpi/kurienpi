package model;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.*; // Make sure to replace 'main.model' with the actual package name for your model classes

public class RailwayReservationSystem {
    private static SessionFactory sessionFactory;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Create SessionFactory
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Train.class)
                    .addAnnotatedClass(LocalTrain.class)
                    .addAnnotatedClass(IntercityTrain.class)
                    .addAnnotatedClass(SuperFastTrain.class)
                    .addAnnotatedClass(GoodsTrain.class)
                    .addAnnotatedClass(Passenger.class)
                    .addAnnotatedClass(GeneralPassenger.class)
                    .addAnnotatedClass(SeniorCitizenPassenger.class)
                    .addAnnotatedClass(PhysicallyHandicappedPassenger.class)
                    .buildSessionFactory();

            // Create and save trains
            createAndSaveTrain();

            // Fetch and display train information
            fetchTrainInformation();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
            scanner.close();
        }
    }

    private static void createAndSaveTrain() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Create Local Train
            LocalTrain localTrain = new LocalTrain(
                    "Mumbai Local", 6, "CST", "Thane",
                    "Mon,Wed,Fri", 60.5, 50.0, "Hourly"
            );

            // Create Intercity Train
            IntercityTrain intercityTrain = new IntercityTrain(
                    "Mumbai Express", 8, "Mumbai", "Delhi",
                    "All", 100.5, 800.0, 5
            );

            // Create Super Fast Train
            SuperFastTrain superFastTrain = new SuperFastTrain(
                    "Rajdhani", 12, "Delhi", "Kolkata",
                    "Tue,Thu", 120.5, 1200.0, 500.0
            );

            // Create Goods Train
            GoodsTrain goodsTrain = new GoodsTrain(
                    "Freight Train", 10, "Mumbai", "Chennai",
                    "Mon,Wed,Sat", 80.5, 0.0, "Containers"
            );

            // Save trains
            session.save(localTrain);
            session.save(intercityTrain);
            session.save(superFastTrain);
            session.save(goodsTrain);

            // Create and Map Passengers
            GeneralPassenger generalPassenger1 = new GeneralPassenger(
                    "John Doe", "john@example.com", "1234567890",
                    "Thane", "A1", "General"
            );
            generalPassenger1.setTrain(localTrain);
            localTrain.getPassengers().add(generalPassenger1);

            SeniorCitizenPassenger seniorPassenger = new SeniorCitizenPassenger(
                    "Alice Smith", "alice@example.com", "9876543210",
                    "Kolkata", "B2", 20.0, "SC001"
            );
            seniorPassenger.setTrain(superFastTrain);
            superFastTrain.getPassengers().add(seniorPassenger);

            PhysicallyHandicappedPassenger handicappedPassenger = new PhysicallyHandicappedPassenger(
                    "Bob Johnson", "bob@example.com", "7890123456",
                    "Delhi", "C3", "Disability Certificate"
            );
            handicappedPassenger.setTrain(intercityTrain);
            intercityTrain.getPassengers().add(handicappedPassenger);

            // Save passengers
            session.save(generalPassenger1);
            session.save(seniorPassenger);
            session.save(handicappedPassenger);

            session.getTransaction().commit();
            System.out.println("Trains and Passengers saved successfully!");

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void fetchTrainInformation() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Fetch all trains
            List<Train> trains = session.createQuery("from Train", Train.class).getResultList();

            System.out.println("\n--- Train Information ---");
            for (Train train : trains) {
                System.out.println("\nTrain Details:");
                System.out.println("ID: " + train.getTrainId());
                System.out.println("Name: " + train.getTrainName());
                System.out.println("Start Station: " + train.getTrainStartStation());
                System.out.println("End Station: " + train.getTrainEndStation());
                System.out.println("Weekly Schedule: " + train.getTrainWeeklyDaysSchedule());
            } // Closing the loop

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
