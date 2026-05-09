package sps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Main menu for Smart Parking System.
public class Main {

    public static SmartParkingSystem system = new SmartParkingSystem();
    public static Scanner input = new Scanner(System.in);
    public static ParkingZone mainZone;

    public static void main(String[] args) {
        int choice = 0;
        fillList();

        System.out.println("**** Welcome To Smart Parking System ****");

        do {
            try {
                menu();
                choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        createTicket();
                        break;
                    case 3:
                        displayVehicles();
                        break;
                    case 4:
                        displayTickets();
                        break;
                    case 5:
                        addSpot();
                        break;
                    case 6:
                        removeSpot();
                        break;
                    case 7:
                        displaySpots();
                        break;
                    case 8:
                        searchSpot();
                        break;
                    case 9:
                        writeText();
                        break;
                    case 10:
                        readText();
                        break;
                    case 11:
                        openGui();
                        break;
                    case 12:
                        System.out.println("Thank you!");
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Invalid input");
                input.nextLine();
            } catch (NullPointerException ex) {
                System.err.println(ex);
            } catch (ClassCastException ex) {
                System.err.println(ex);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println(ex);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } while (choice != 12);
    }

    // Prints the menu.
    public static void menu() {
        System.out.print(
              "\n1.  Add vehicle.\n"
            + "2.  Create parking ticket.\n"
            + "3.  Display vehicles.\n"
            + "4.  Display tickets.\n"
            + "5.  Add parking spot.\n"
            + "6.  Remove parking spot.\n"
            + "7.  Display parking spots.\n"
            + "8.  Search parking spot.\n"
            + "9.  Save tickets.\n"
            + "10. Read tickets.\n"
            + "11. Open GUI.\n"
            + "12. Exit.\n"
            + ">> ");
    }

    // Fills the system with sample data.
    public static void fillList() {
        ParkingSpot[] spots = {
            new ParkingSpot("S-101", "North", "medium", false),
            new ParkingSpot("S-102", "North", "small", false),
            new ParkingSpot("S-201", "East", "large", false)
        };

        mainZone = new ParkingZone("Z-01", "Main Zone", 3, 0, spots);

        Vehicle v1 = new Car("V-001", "ABC123", "Lamar Saud", 3, "S-101", "SPS", 4, true);
        Vehicle v2 = new Motorcycle("V-002", "MTR456", "Sara Ali", 2, "S-102", "SPS", 1, false);
        Vehicle v3 = new Truck("V-003", "TRK789", "Omar Saleh", 4, "S-201", "SPS", 1200.0, true);
        Vehicle v4 = new ElectricCar("V-004", "EV2026", "Nora Adel", 5, "S-101", "SPS", 4, true, 70.0, true);

        system.addVehicle(v1);
        system.addVehicle(v2);
        system.addVehicle(v3);
        system.addVehicle(v4);

        system.addTicket(new ParkingTicket("01/05/2026", "01/05/2026", 3, v1, spots[0], new Payment("P-001", "Card", 0.0, false)));
        system.addTicket(new ParkingTicket("02/05/2026", "02/05/2026", 2, v2, spots[1], new Payment("P-002", "Cash", 0.0, false)));
    }

    // Adds a vehicle.
    public static void addVehicle() {
        int type = readIntInRange("Vehicle type 1-Car 2-Motorcycle 3-Truck 4-Electric Car: ", 1, 4);
        String vehicleId = "V-" + readPositiveInt("Vehicle number: ");
        String plateNumber = readPlate("Plate number: ");
        String ownerName = readName("Owner name: ");
        int entryHours = readPositiveInt("Entry hours: ");
        String parkedSpotId = readLocation("Parked spot id: ");
        String systemCode = "SPS";

        if (type == 1) {
            int doors = readPositiveInt("Number of doors: ");
            boolean vip = readBoolean("VIP member true/false: ");
            system.addVehicle(new Car(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode, doors, vip));
        } else if (type == 2) {
            int helmets = readPositiveInt("Helmet count: ");
            boolean delivery = readBoolean("Delivery use true/false: ");
            system.addVehicle(new Motorcycle(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode, helmets, delivery));
        } else if (type == 3) {
            double weight = readPositiveDouble("Load weight: ");
            boolean commercial = readBoolean("Commercial use true/false: ");
            system.addVehicle(new Truck(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode, weight, commercial));
        } else {
            int doors = readPositiveInt("Number of doors: ");
            boolean vip = readBoolean("VIP member true/false: ");
            double battery = readPositiveDouble("Battery level: ");
            boolean charging = readBoolean("Charging required true/false: ");
            system.addVehicle(new ElectricCar(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode, doors, vip, battery, charging));
        }

        System.out.println("Vehicle added.");
    }

    // Creates a ticket.
    public static void createTicket() {
        String vehicleId = "V-" + readPositiveInt("Vehicle number: ");
        Vehicle vehicle = system.findVehicleById(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        ParkingSpot spot = mainZone.findAvailableSpot(vehicle.getVehicleType());
        if (spot == null) {
            System.out.println("No available spot");
            return;
        }

        String issueDate = readDate("Issue date DD/MM/YYYY: ");
        String exitDate = readDate("Exit date DD/MM/YYYY: ");
        int duration = readPositiveInt("Duration hours: ");
        Payment payment = new Payment("P-" + readPositiveInt("Payment number: "), "Card", 0.0, false);

        ParkingTicket ticket = new ParkingTicket(issueDate, exitDate, duration, vehicle, spot, payment);
        system.addTicket(ticket);

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            System.out.println("VIP eligible: " + car.isVipEligible());
        }

        System.out.println(ticket.generateReceipt());
    }

    // Displays vehicles.
    public static void displayVehicles() {
        System.out.println(system.displayAllVehicles());
    }

    // Displays tickets.
    public static void displayTickets() {
        System.out.println(system.displayAllTickets());
    }

    // Adds one spot by growing the array.
    public static void addSpot() {
        ParkingSpot spot = new ParkingSpot(
                "S-" + readPositiveInt("Spot number: "),
                readLocation("Zone name: "),
                readModel("Size type: "),
                false);

        ParkingSpot[] oldSpots = mainZone.getSpots();
        ParkingSpot[] newSpots = new ParkingSpot[oldSpots.length + 1];

        for (int i = 0; i < oldSpots.length; i++) {
            newSpots[i] = oldSpots[i];
        }

        newSpots[newSpots.length - 1] = spot;
        mainZone.setSpots(newSpots);
        mainZone.setCapacity(newSpots.length);
        System.out.println("Spot added.");
    }

    // Removes one spot by shrinking the array.
    public static void removeSpot() {
        String spotId = "S-" + readPositiveInt("Spot number: ");
        ParkingSpot[] oldSpots = mainZone.getSpots();
        int index = -1;

        for (int i = 0; i < oldSpots.length; i++) {
            if (oldSpots[i].getSpotId().equalsIgnoreCase(spotId)) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("Spot not found");
            return;
        }

        ParkingSpot[] newSpots = new ParkingSpot[oldSpots.length - 1];
        int j = 0;

        for (int i = 0; i < oldSpots.length; i++) {
            if (i != index) {
                newSpots[j] = oldSpots[i];
                j++;
            }
        }

        mainZone.setSpots(newSpots);
        mainZone.setCapacity(newSpots.length);
        System.out.println("Spot removed.");
    }

    // Iterates through the array.
    public static void displaySpots() {
        ParkingSpot[] spots = mainZone.getSpots();

        for (int i = 0; i < spots.length; i++) {
            System.out.println(spots[i]);
            System.out.println("------------------------------------------");
        }
    }

    // Searches the array.
    public static void searchSpot() {
        String spotId = "S-" + readPositiveInt("Spot number: ");
        ParkingSpot[] spots = mainZone.getSpots();

        for (int i = 0; i < spots.length; i++) {
            if (spots[i].getSpotId().equalsIgnoreCase(spotId)) {
                System.out.println(spots[i]);
                return;
            }
        }

        System.out.println("Spot not found");
    }

    // Opens JavaFX GUI.
    public static void openGui() {
        try {
            GUI.main(null);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            boolean valid = text.length() > 0;

            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) < '0' || text.charAt(i) > '9') {
                    valid = false;
                }
            }

            if (valid && Integer.parseInt(text) > 0) {
                return Integer.parseInt(text);
            }

            System.out.println("Enter a positive number");
        }
    }

    private static int readIntInRange(String prompt, int min, int max) {
        int value;
        do {
            value = readPositiveInt(prompt);
            if (value < min || value > max) {
                System.out.println("Enter a number from " + min + " to " + max);
            }
        } while (value < min || value > max);

        return value;
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            boolean valid = text.length() > 0;
            int dotCount = 0;

            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '.') {
                    dotCount++;
                } else if (text.charAt(i) < '0' || text.charAt(i) > '9') {
                    valid = false;
                }
            }

            if (valid && dotCount <= 1 && Double.parseDouble(text) > 0) {
                return Double.parseDouble(text);
            }

            System.out.println("Enter a positive decimal number");
        }
    }

    private static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();

            if (text.equalsIgnoreCase("true")) {
                return true;
            }

            if (text.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Enter true or false");
        }
    }

    private static String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            boolean valid = text.length() >= 2 && text.length() <= 50;

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == ' ')) {
                    valid = false;
                }
            }

            if (valid) {
                return text;
            }

            System.out.println("Enter English letters and spaces, length 2 to 50");
        }
    }

    private static String readPlate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim().toUpperCase();
            boolean valid = text.length() >= 6 && text.length() <= 8;

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (!((ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))) {
                    valid = false;
                }
            }

            if (valid) {
                return text;
            }

            System.out.println("Enter uppercase letters and digits, length 6 to 8");
        }
    }

    private static String readModel(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            boolean valid = text.length() >= 2 && text.length() <= 50;

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
                        || (ch >= '0' && ch <= '9') || ch == ' ')) {
                    valid = false;
                }
            }

            if (valid) {
                return text;
            }

            System.out.println("Enter letters, digits, and spaces, length 2 to 50");
        }
    }

    private static String readLocation(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            boolean valid = text.length() >= 2 && text.length() <= 100;

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
                        || (ch >= '0' && ch <= '9') || ch == ' ' || ch == '-')) {
                    valid = false;
                }
            }

            if (valid) {
                return text;
            }

            System.out.println("Enter letters, digits, spaces, or dash, length 2 to 100");
        }
    }

    private static String readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();

            if (text.length() == 10 && text.charAt(2) == '/' && text.charAt(5) == '/') {
                boolean digits = true;

                for (int i = 0; i < text.length(); i++) {
                    if (i != 2 && i != 5 && (text.charAt(i) < '0' || text.charAt(i) > '9')) {
                        digits = false;
                    }
                }

                if (digits) {
                    int day = Integer.parseInt(text.substring(0, 2));
                    int month = Integer.parseInt(text.substring(3, 5));
                    int year = Integer.parseInt(text.substring(6));

                    if (year >= 2020 && year <= 2030 && month >= 1 && month <= 12
                            && day >= 1 && day <= daysInMonth(month, year)) {
                        return text;
                    }
                }
            }

            System.out.println("Enter date as DD/MM/YYYY");
        }
    }

    private static int daysInMonth(int month, int year) {
        if (month == 2) {
            return year % 4 == 0 ? 29 : 28;
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        return 31;
    }

    /**
     * Saves all tickets to the text file.
     */
    public static void writeText() {
        try {
            Formatter output = new Formatter("tickets.txt");
            if (system.getTickets().isEmpty()) {
                System.out.println("No tickets yet.");
            } else {
                for (ParkingTicket ele : system.getTickets()) {
                    output.format(ele.toString() + "\n");
                    output.format("\n---------------------------------------------\n");
                }
                System.out.println("All tickets saved to the text file tickets.txt");
            }
            output.close();
        } catch (SecurityException ex) {
            System.err.println("You do not have write access to this file.");
            System.err.println(ex);
        } catch (FileNotFoundException ex) {
            System.err.println("Error opening or creating file.");
            System.err.println(ex);
        }
    }

    /**
     * Reads the text file line by line and prints each line.
     */
    public static void readText() {
        try {
            Scanner fileIn = new Scanner(new File("tickets.txt"));
            while (fileIn.hasNextLine()) {
                System.out.println(fileIn.nextLine());
            }
            fileIn.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error opening or creating file.");
            System.err.println(ex);
        } catch (NoSuchElementException ex) {
            System.err.println("File improperly formed.");
            System.err.println(ex);
        } catch (IllegalStateException ex) {
            System.err.println("Error reading from file.");
            System.err.println(ex);
        }
    }
}
