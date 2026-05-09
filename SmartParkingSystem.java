package sps;

import java.util.ArrayList;

// Smart parking manager.
public class SmartParkingSystem {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<ParkingTicket> tickets;

    // empty constructor
    public SmartParkingSystem() {
        vehicles = new ArrayList<Vehicle>();
        tickets = new ArrayList<ParkingTicket>();
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<ParkingTicket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<ParkingTicket> tickets) {
        this.tickets = tickets;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    public void addTicket(ParkingTicket ticket) {
        tickets.add(ticket);
    }

    public boolean removeTicket(ParkingTicket ticket) {
        return tickets.remove(ticket);
    }

    /**
     * @param vehicleId vehicle id
     * @return found vehicle or null
     */
    public Vehicle findVehicleById(String vehicleId) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equalsIgnoreCase(vehicleId)) {
                return vehicles.get(i);
            }
        }
        return null;
    }

    /**
     * @param ticketId ticket id
     * @return found ticket or null
     */
    public ParkingTicket findTicketById(String ticketId) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getTicketId().equalsIgnoreCase(ticketId)) {
                return tickets.get(i);
            }
        }
        return null;
    }

    /**
     * @param keyword search keyword
     * @return matching vehicles
     */
    public String searchVehicle(String keyword) {
        String result = "";
        String key = keyword.trim().toLowerCase();

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            if (vehicle.getOwnerName().toLowerCase().contains(key)
                    || vehicle.getPlateNumber().toLowerCase().contains(key)
                    || vehicle.getVehicleType().toLowerCase().contains(key)) {
                result += vehicle + "\n------------------------------------------\n";
            }
        }

        if (result.length() == 0) {
            result = "No vehicles found";
        }

        return result;
    }

    /**
     * @return all vehicles
     */
    public String displayAllVehicles() {
        String result = "";

        for (int i = 0; i < vehicles.size(); i++) {
            result += vehicles.get(i) + "\n------------------------------------------\n";
        }

        if (result.length() == 0) {
            result = "No vehicles available";
        }

        return result;
    }

    /**
     * @return all cars
     */
    public String displayCars() {
        String result = "";

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) instanceof Car) {
                result += vehicles.get(i) + "\n------------------------------------------\n";
            }
        }

        if (result.length() == 0) {
            result = "No cars available";
        }

        return result;
    }

    /**
     * @return all motorcycles
     */
    public String displayMotorcycles() {
        String result = "";

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) instanceof Motorcycle) {
                result += vehicles.get(i) + "\n------------------------------------------\n";
            }
        }

        if (result.length() == 0) {
            result = "No motorcycles available";
        }

        return result;
    }

    /**
     * @return all trucks
     */
    public String displayTrucks() {
        String result = "";

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) instanceof Truck) {
                result += vehicles.get(i) + "\n------------------------------------------\n";
            }
        }

        if (result.length() == 0) {
            result = "No trucks available";
        }

        return result;
    }

    /**
     * @return all tickets
     */
    public String displayAllTickets() {
        String result = "";

        for (int i = 0; i < tickets.size(); i++) {
            result += tickets.get(i) + "\n==========================================\n";
        }

        if (result.length() == 0) {
            result = "No tickets available";
        }

        return result;
    }

    /**
     * @return total ticket amount
     */
    public double calculateTotalIncome() {
        double total = 0.0;

        for (int i = 0; i < tickets.size(); i++) {
            total += tickets.get(i).calculateTotal();
        }

        return total;
    }
}
