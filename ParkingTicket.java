package sps;

// A parking ticket.
public class ParkingTicket implements Chargeable {

    private final String TICKET_ID;
    private String issueDate;
    private String exitDate;
    private int durationHours;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private Payment payment;
    private static int count;

    // empty constructor
    public ParkingTicket() {
        this("", "", 0, null, null, null);
    }

    /**
     * Full constructor
     * @param issueDate issue date
     * @param exitDate exit date
     * @param durationHours duration hours
     * @param vehicle vehicle
     * @param spot parking spot
     * @param payment payment
     */
    public ParkingTicket(String issueDate, String exitDate, int durationHours,
            Vehicle vehicle, ParkingSpot spot, Payment payment) {
        this.TICKET_ID = String.valueOf(generateId());
        setIssueDate(issueDate);
        setExitDate(exitDate);
        setDurationHours(durationHours);
        setVehicle(vehicle);
        setSpot(spot);
        setPayment(payment);
        count++;
    }

    private int generateId() {
        int min = 1000;
        int max = 9999;
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public String getTicketId() {
        return TICKET_ID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate.trim();
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate.trim();
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        if (durationHours < 0) {
            throw new IllegalArgumentException("Duration hours cannot be negative");
        }
        this.durationHours = durationHours;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ParkingTicket.count = count;
    }

    /**
     * @return total ticket amount
     */
    public double calculateTotal() {
        double total = 0.0;

        if (vehicle != null) {
            total = vehicle.calculateParkingFee();
        }

        if (payment != null) {
            payment.completePayment(total);
        }

        return total;
    }

    // Closes this ticket.
    public void closeTicket() {
        if (spot != null) {
            spot.releaseSpot();
        }
        if (payment != null) {
            payment.completePayment(calculateTotal());
        }
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        return calculateTotal();
    }

    /**
     * @return receipt information
     */
    @Override
    public String generateReceipt() {
        return String.format("Ticket ID: %s\nIssue Date: %s\nExit Date: %s\nTotal: %.2f",
                getTicketId(), getIssueDate(), getExitDate(), calculateTotal());
    }

    @Override
    public String toString() {
        return String.format("Ticket ID: %s\nIssue Date: %s\nExit Date: %s\nDuration Hours: %d\nVehicle:\n%s\nSpot:\n%s\nPayment:\n%s",
                getTicketId(), getIssueDate(), getExitDate(), getDurationHours(),
                getVehicle(), getSpot(), getPayment());
    }
}
