import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainDAO trainDAO = new TrainDAO();
        PassengerDAO passengerDAO = new PassengerDAO();

        while (true) {
            System.out.println("\nRailway Reservation System");
            System.out.println("1. Train Dashboard");
            System.out.println("2. Passenger Dashboard");
            System.out.println("3. Create Booking");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTrainDashboard(scanner, trainDAO);
                    break;
                case 2:
                    showPassengerDashboard(scanner, passengerDAO);
                    break;
                case 3:
                    createBooking(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showTrainDashboard(Scanner scanner, TrainDAO trainDAO) {
        System.out.println("\nTrain Dashboard");
        System.out.println("1. Search by weekly schedule");
        System.out.println("2. Search local trains by start station");
        System.out.println("3. Search goods trains by fare");
        System.out.println("4. Search by train type");
        System.out.println("5. Search super fast trains by date and speed");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter day (e.g., Mon): ");
                String day = scanner.next();
                List<Train> trains = trainDAO.getTrainsByWeeklySchedule(day);
                displayTrains(trains);
                break;
            case 2:
                System.out.println("Enter start station: ");
                String station = scanner.next();
                List<LocalTrain> localTrains = trainDAO.getLocalTrainsByStartStation(station);
                displayTrains(localTrains);
                break;
            case 3:
                System.out.println("Enter minimum fare: ");
                double minFare = scanner.nextDouble();
                System.out.println("Enter maximum fare: ");
                double maxFare = scanner.nextDouble();
                List<GoodsTrain> goodsTrains = trainDAO.getGoodsTrainsByFareCharges(minFare, maxFare);
                displayTrains(goodsTrains);
                break;
            case 4:
                System.out.println("Enter train type (Local/SuperFast/Intercity): ");
                String type = scanner.next();
                List<Train> trainsByType = trainDAO.getTrainsByType(type);
                displayTrains(trainsByType);
                break;
            case 5:
                System.out.println("Enter schedule date (yyyy-MM-dd): ");
                String date = scanner.next();
                System.out.println("Enter minimum average speed: ");
                double speed = scanner.nextDouble();
                List<SuperFastTrain> superFastTrains = trainDAO.getSuperFastTrainsByDateAndSpeed(LocalDate.parse(date), speed);
                displayTrains(superFastTrains);
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private static void showPassengerDashboard(Scanner scanner, PassengerDAO passengerDAO) {
        System.out.println("\nPassenger Dashboard");
        System.out.println("1. Show senior citizens");
        System.out.println("2. Search by coach type");
        System.out.println("3. Search by destination");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<SeniorCitizen> seniors = passengerDAO.getSeniorCitizens();
                displayPassengers(seniors);
                break;
            case 2:
                System.out.println("Enter coach type: ");
                String coachType = scanner.next();
                List<Passenger> passengersByCoach = passengerDAO.getPassengersByCoachType(coachType);
                displayPassengers(passengersByCoach);
                break;
            case 3:
                System.out.println("Enter destination: ");
                String destination = scanner.next();
                List<Passenger> passengersByDestination = passengerDAO.getPassengersByDestination(destination);
                displayPassengers(passengersByDestination);
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private static void createBooking(Scanner scanner) {
        System.out.println("\nBooking Creation");
        System.out.println("Feature to create bookings is under construction.");
        // Implement booking logic
    }

    private static void displayTrains(List<? extends Train> trains) {
        if (trains.isEmpty()) {
            System.out.println("No trains found.");
        } else {
            for (Train train : trains) {
                System.out.println(train);
            }
        }
    }

    private static void displayPassengers(List<? extends Passenger> passengers) {
        if (passengers.isEmpty()) {
            System.out.println("No passengers found.");
        } else {
            for (Passenger passenger : passengers) {
                System.out.println(passenger);
            }
        }
    }
}
