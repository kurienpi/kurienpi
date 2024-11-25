package org.xmlBased.execution;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import org.xmlBased.entity.*;

public class XMLBasedExecution {
    private static SessionFactory sessionFactory;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            //Create SessionFactory Object from configuration
            SessionFactory sessionFactory = configuration.buildSessionFactory();
          //  sessionFactory = new Configuration().configure().buildSessionFactory();

            // Insert initial subjects
            insertSubjects();

            // Insert students
            insertStudents();

            // Insert teachers
            insertTeachers();

            // Display all records
            displayAllRecords();

            // Update Mathematics to Astronomy
            updateSubject();

            // Delete Physics students
            deletePhysicsStudents();

            displayAllRecords();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    private static void insertSubjects() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String[] subjects = {"Physics", "Chemistry", "Mathematics", "Computer"};
            for (String subName : subjects) {
                Subject subject = new Subject();
                subject.setSubName(subName);
                session.save(subject);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void insertStudents() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Display available subjects
            Query<Subject> query = session.createQuery("from Subject", Subject.class);
            List<Subject> subjects = query.list();

            System.out.println("Available subjects:");
            for (Subject subject : subjects) {
                System.out.println(subject.getSubjectId() + ": " + subject.getSubName());
            }

            System.out.println("Enter student details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Roll Number: ");
            int rollNumber = scanner.nextInt();

            System.out.print("Choose subject ID: ");
            int subjectId = scanner.nextInt();

            Subject subject = session.get(Subject.class, subjectId);

            Student student = new Student();
            student.setStudName(name);
            student.setStudRollNumber(rollNumber);
            student.setSubject(subject);

            session.save(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void insertTeachers() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            System.out.println("Enter teacher details:");
            System.out.print("Name: ");
            scanner.nextLine(); // Clear buffer
            String name = scanner.nextLine();

            System.out.print("Qualification: ");
            String qualification = scanner.nextLine();

            System.out.print("Years of Experience: ");
            int experience = scanner.nextInt();

            // Display available subjects and their students
            Query<Subject> query = session.createQuery("from Subject", Subject.class);
            List<Subject> subjects = query.list();

            System.out.println("Available subjects and their students:");
            for (Subject subject : subjects) {
                System.out.println(subject.getSubjectId() + ": " + subject.getSubName());
                Query<Student> studentQuery = session.createQuery(
                        "from Student where subject.id = :subjectId", Student.class);
                studentQuery.setParameter("subjectId", subject.getSubjectId());
                List<Student> students = studentQuery.list();
                for (Student student : students) {
                    System.out.println("  - " + student.getStudName());
                }
            }

            System.out.print("Choose subject ID: ");
            int subjectId = scanner.nextInt();

            Subject subject = session.get(Subject.class, subjectId);
            Query<Student> studentQuery = session.createQuery(
                    "from Student where subject.id = :subjectId", Student.class);
            studentQuery.setParameter("subjectId", subjectId);
            List<Student> students = studentQuery.list();

            Teacher teacher = new Teacher();
            teacher.setTeacherName(name);
            teacher.setTeacherQualification(qualification);
            teacher.setExperienceOfTeaching(experience);
            teacher.setSubject(subject);
            teacher.setStudents(new HashSet<>(students));

            session.save(teacher);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void displayAllRecords() {
        Session session = sessionFactory.openSession();

        try {
            System.out.println("\n=== Subjects ===");
            List<Subject> subjects = session.createQuery("from Subject", Subject.class).list();
            for (Subject subject : subjects) {
                System.out.println(subject.getSubName());
            }

            System.out.println("\n=== Students ===");
            List<Student> students = session.createQuery("from Student", Student.class).list();
            for (Student student : students) {
                System.out.println(student.getStudName() + " - " +
                        student.getSubject().getSubName());
            }

            System.out.println("\n=== Teachers ===");
            List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).list();
            for (Teacher teacher : teachers) {
                System.out.println(teacher.getTeacherName() + " - " +
                        teacher.getSubject().getSubName());
                System.out.println("Students:");
                for (Student student : teacher.getStudents()) {
                    System.out.println("  - " + student.getStudName());
                }
            }
        } finally {
            session.close();
        }
    }

    private static void updateSubject() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(
                    "update Subject set subName = :newName where subName = :oldName");
            query.setParameter("newName", "Astronomy");
            query.setParameter("oldName", "Mathematics");
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void deletePhysicsStudents() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(
                    "delete from Student where subject.subName = :subjectName");
            query.setParameter("subjectName", "Physics");
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
