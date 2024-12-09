package main;

import model.*;
import dao.PassengerDAO;
import java.util.List;
import java.util.Scanner;

public class RailwayReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PassengerDAO passengerDAO = new PassengerDAO();

        // Input and Save General Passengers
        System.out.println("--- General Passengers ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter General Passenger " + i + " Details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Mobile: ");
            String mobile = scanner.nextLine();

            System.out.print("Destination: ");
            String destination = scanner.nextLine();

            System.out.print("Seat Number: ");
            String seatNumber = scanner.nextLine();

            System.out.print("Coach Type: ");
            String coachType = scanner.nextLine();

            GeneralPassenger generalPassenger = new GeneralPassenger(
                    name, email, mobile, destination, seatNumber, coachType
            );
            passengerDAO.saveGeneralPassenger(generalPassenger);
        }

        // Input and Save Senior Citizen Passengers
        System.out.println("\n--- Senior Citizen Passengers ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter Senior Citizen Passenger " + i + " Details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Mobile: ");
            String mobile = scanner.nextLine();

            System.out.print("Destination: ");
            String destination = scanner.nextLine();

            System.out.print("Seat Number: ");
            String seatNumber = scanner.nextLine();

            System.out.print("Discount: ");
            double discount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Senior Citizen ID: ");
            String seniorCitizenId = scanner.nextLine();

            SeniorCitizenPassenger seniorCitizenPassenger = new SeniorCitizenPassenger(
                    name, email, mobile, destination, seatNumber, discount, seniorCitizenId
            );
            passengerDAO.saveSeniorCitizenPassenger(seniorCitizenPassenger);
        }

        // Input and Save Physically Handicapped Passengers
        System.out.println("\n--- Physically Handicapped Passengers ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter Physically Handicapped Passenger " + i + " Details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Mobile: ");
            String mobile = scanner.nextLine();

            System.out.print("Destination: ");
            String destination = scanner.nextLine();

            System.out.print("Seat Number: ");
            String seatNumber = scanner.nextLine();

            System.out.print("Proof of Disability: ");
            String proofOfDisability = scanner.nextLine();

            PhysicallyHandicappedPassenger physicallyHandicappedPassenger = new PhysicallyHandicappedPassenger(
                    name, email, mobile, destination, seatNumber, proofOfDisability
            );
            passengerDAO.savePhysicallyHandicappedPassenger(physicallyHandicappedPassenger);
        }

        // Fetch and Display Physically Handicapped Passengers
        System.out.println("\n--- Physically Handicapped Passengers ---");
        List<PhysicallyHandicappedPassenger> physicallyHandicappedPassengers =
                passengerDAO.getPhysicallyHandicappedPassengers();

        for (PhysicallyHandicappedPassenger passenger : physicallyHandicappedPassengers) {
            System.out.println("Name: " + passenger.getName());
            System.out.println("Email: " + passenger.getEmail());
            System.out.println("Mobile: " + passenger.getMobile());
            System.out.println("Destination: " + passenger.getDestination());
            System.out.println("Seat Number: " + passenger.getSeatNumber());
            System.out.println("Proof of Disability: " + passenger.getProofOfDisability());
            System.out.println("---");
        }

        // Close resources
        scanner.close();
    }
}