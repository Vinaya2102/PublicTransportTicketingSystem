import java.util.*;

// Class to represent a Route
class Route {
    private String routeName;
    private double farePerKm;

    public Route(String routeName, double farePerKm) {
        this.routeName = routeName;
        this.farePerKm = farePerKm;
    }

    public String getRouteName() {
        return routeName;
    }

    public double getFarePerKm() {
        return farePerKm;
    }
}

// Class to represent a Ticket
class Ticket {
    private String passengerName;
    private Route route;
    private double distance;
    private double totalFare;

    public Ticket(String passengerName, Route route, double distance) {
        this.passengerName = passengerName;
        this.route = route;
        this.distance = distance;
        this.totalFare = calculateFare();
    }

    private double calculateFare() {
        return distance * route.getFarePerKm();
    }

    public void printTicket() {
        System.out.println("\n--- Ticket Details ---");
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Route: " + route.getRouteName());
        System.out.println("Distance: " + distance + " km");
        System.out.println("Total Fare: $" + String.format("%.2f", totalFare));
    }
}

// Main class for the Ticketing System
public class PublicTransportTicketingSystem {
    private static List<Route> routes = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize some predefined routes
        routes.add(new Route("Route A", 0.5)); // $0.5 per km
        routes.add(new Route("Route B", 0.7)); // $0.7 per km
        routes.add(new Route("Route C", 1.0)); // $1.0 per km

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Public Transport Ticketing System\n");

        while (true) {
            System.out.println("1. View Routes");
            System.out.println("2. Book Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    viewRoutes();
                    break;
                case 2:
                    bookTicket(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Ticketing System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    private static void viewRoutes() {
        System.out.println("\nAvailable Routes:");
        for (int i = 0; i < routes.size(); i++) {
            System.out.println((i + 1) + ". " + routes.get(i).getRouteName() + " - Fare per km: $" + routes.get(i).getFarePerKm());
        }
        System.out.println();
    }

    private static void bookTicket(Scanner scanner) {
        System.out.println("\n--- Book Ticket ---");
        System.out.print("Enter Passenger Name: ");
        String passengerName = scanner.nextLine();

        viewRoutes();
        System.out.print("Select Route (1, 2, or 3): ");
        int routeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (routeChoice < 1 || routeChoice > routes.size()) {
            System.out.println("Invalid route selection. Returning to main menu.\n");
            return;
        }

        Route selectedRoute = routes.get(routeChoice - 1);

        System.out.print("Enter Distance (in km): ");
        double distance = scanner.nextDouble();

        if (distance <= 0) {
            System.out.println("Invalid distance. Returning to main menu.\n");
            return;
        }

        Ticket ticket = new Ticket(passengerName, selectedRoute, distance);
        ticket.printTicket();
        System.out.println();
    }
}
