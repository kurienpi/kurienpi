package com.property;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Getting rent flat beans
        RentOfOneBHKFlat rentOneBHK = context.getBean("rentOneBHK", RentOfOneBHKFlat.class);
        RentOfTwoBHKFlat rentTwoBHK = context.getBean("rentTwoBHK", RentOfTwoBHKFlat.class);
        RentOfThreeBHKFlat rentThreeBHK = context.getBean("rentThreeBHK", RentOfThreeBHKFlat.class);

        // Getting autowired self-owned flat beans
        SelfOwnedOneBHKFlat selfOneBHK = context.getBean(SelfOwnedOneBHKFlat.class);
        SelfOwnedTwoBHKFlat selfTwoBHK = context.getBean(SelfOwnedTwoBHKFlat.class);
        SelfOwnedThreeBHKFlat selfThreeBHK = context.getBean(SelfOwnedThreeBHKFlat.class);

        // Display rent flat information
        System.out.println("=== Rental Flats ===");
        rentOneBHK.displayInfo();
        rentTwoBHK.displayInfo();
        rentThreeBHK.displayInfo();

        // Set dimensions and display self-owned flat information
        System.out.println("\n=== Self Owned Flats ===");

        // One BHK
        selfOneBHK.length = 30;
        selfOneBHK.breadth = 20;
        selfOneBHK.height = 10;
        selfOneBHK.displayInfo();

        // Two BHK
        selfTwoBHK.length = 40;
        selfTwoBHK.breadth = 30;
        selfTwoBHK.height = 10;
        selfTwoBHK.displayInfo();

        // Three BHK
        selfThreeBHK.length = 50;
        selfThreeBHK.breadth = 40;
        selfThreeBHK.height = 10;
        selfThreeBHK.displayInfo();
    }
}