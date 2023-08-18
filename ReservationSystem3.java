import java.util.*;

class ReservationSystem3 {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Train> trains = new ArrayList<>();

    public static void main(String[] args) {
        // Adding example users
        users.add(new User("nitish", "123456"));
        users.add(new User("user", "user123"));

        // Adding example trains
        trains.add(new Train(143, "Passanger", "2023-08-18", "Bhimavaram to Niddadavolu"));
        trains.add(new Train(456, "SuperFast Train", "2023-08-20", "Rajahmundry to Vijayawada"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                loginUser(scanner);
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                showReservationMenu(scanner, user);
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    public static void showReservationMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();

            if (choice == 1) {
                makeReservation(scanner, user);
            } else if (choice == 2) {
                cancelReservation(scanner, user);
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void makeReservation(Scanner scanner, User user) {
        System.out.println("Available Trains:");
        for (Train train : trains) {
            System.out.println("Train Number: " + train.getTrainNumber());
            System.out.println("Train Name: " + train.getTrainName());
            System.out.println("Date: " + train.getDate());
            System.out.println("Destination: " + train.getDestination());
            System.out.println();
        }

        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        // Other input fields

        // Check if the selected train exists
        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                selectedTrain = train;
                break;
            }
        }

        if (selectedTrain != null) {
            Reservation reservation = new Reservation(user.getUsername(), selectedTrain);
            reservations.add(reservation);
            System.out.println("Reservation successfully made!");
        } else {
            System.out.println("Invalid train number.");
        }
    }

    public static void cancelReservation(Scanner scanner, User user) {
        System.out.println("Your reservations:");
        for (Reservation reservation : reservations) {
            if (reservation.getUsername().equals(user.getUsername())) {
                Train train = reservation.getTrain();
                System.out.println("Train Number: " + train.getTrainNumber());
                System.out.println("Train Name: " + train.getTrainName());
                System.out.println("Date: " + train.getDate());
                System.out.println("Destination: " + train.getDestination());
                System.out.println();
            }
        }

        System.out.print("Enter train number to cancel reservation: ");
        int trainNumber = scanner.nextInt();

        for (Reservation reservation : reservations) {
            Train train = reservation.getTrain();
            if (reservation.getUsername().equals(user.getUsername()) && train.getTrainNumber() == trainNumber) {
                reservations.remove(reservation);
                System.out.println("Reservation successfully canceled!");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Train {
    private int trainNumber;
    private String trainName;
    private String date;
    private String destination;

    public Train(int trainNumber, String trainName, String date, String destination) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.date = date;
        this.destination = destination;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {
        return destination;
    }
}

class Reservation {
    private String username;
    private Train train;

    public Reservation(String username, Train train) {
        this.username = username;
        this.train = train;
    }

    public String getUsername() {
        return username;
    }

    public Train getTrain() {
        return train;
    }
}
