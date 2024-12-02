package com.zorba.exam.service;

import com.zorba.exam.exception.ValidationException;
import com.zorba.exam.model.Customer;
import com.zorba.exam.model.Product;
import com.zorba.exam.model.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class ShoppingCartService {
    private SessionFactory sessionFactory;

    public ShoppingCartService() {
        // Initialize Hibernate SessionFactory
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Validate phone number
    private void validatePhoneNumber(Long phoneNumber) throws ValidationException {
        if (phoneNumber == null || phoneNumber.toString().length() != 10) {
            throw new ValidationException("Phone number must be exactly 10 digits");
        }
    }

    // Validate email
    private void validateEmail(String email) throws ValidationException {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    // Initialize product types
    public void initializeProductTypes() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Check if product types already exist
            Long count = (Long) session.createQuery("select count(*) from ProductType").uniqueResult();
            if (count == 0) {
                ProductType grocery = new ProductType("grocery", new BigDecimal("30"));
                ProductType cosmetics = new ProductType("cosmetics", new BigDecimal("50"));
                ProductType dairyProduct = new ProductType("dairyproduct", new BigDecimal("35"));

                session.save(grocery);
                session.save(cosmetics);
                session.save(dairyProduct);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void processShoppingCart() {
        Scanner scanner = new Scanner(System.in);
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            // Input customer details
            System.out.println("Enter Customer Name:");
            String custName = scanner.nextLine();

            System.out.println("Enter Customer Email:");
            String custEmail = scanner.nextLine();
            validateEmail(custEmail);

            System.out.println("Enter Customer Mobile Number:");
            Long custMobile = scanner.nextLong();
            scanner.nextLine(); // Consume newline
            validatePhoneNumber(custMobile);

            // Input product details
            System.out.println("Enter Product Name:");
            String productName = scanner.nextLine();

            System.out.println("Enter Product Type (grocery/cosmetics/dairyproduct):");
            String productTypeName = scanner.nextLine().toLowerCase();

            System.out.println("Enter Product Quantity:");
            BigDecimal productQuantity = scanner.nextBigDecimal();

            // Begin transaction
            tx = session.beginTransaction();

            // Find Product Type
            ProductType productType = (ProductType) session.createQuery("FROM ProductType WHERE productType = :type")
                    .setParameter("type", productTypeName)
                    .uniqueResult();

            if (productType == null) {
                throw new ValidationException("Invalid product type");
            }

            // Calculate final price
            BigDecimal finalPrice = productType.getRate().multiply(productQuantity);

            // Create Product
            Product product = new Product(
                    UUID.randomUUID().toString(),
                    productName,
                    productQuantity,
                    productType
            );
            session.save(product);

            // Create Customer
            Customer customer = new Customer(
                    UUID.randomUUID().toString(),
                    custName,
                    custEmail,
                    custMobile,
                    finalPrice
            );
            session.save(customer);

            // Commit transaction
            tx.commit();

            System.out.println("Data saved successfully!");
            System.out.println("Total Price: $" + finalPrice);

        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
            if (tx != null) tx.rollback();
        } catch (Exception e) {
            System.out.println("Error processing shopping cart: " + e.getMessage());
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        ShoppingCartService service = new ShoppingCartService();
        service.initializeProductTypes();
        service.processShoppingCart();
    }
}