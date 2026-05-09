package sps;

// A motorcycle.
public class Motorcycle extends Vehicle {

    private int helmetCount;
    private boolean deliveryUse;

    // empty constructor
    public Motorcycle() {
        this("", "", "", 0, "", "", 0, false);
    }

    /**
     * Full constructor
     * @param vehicleId vehicle id
     * @param plateNumber plate number
     * @param ownerName owner name
     * @param entryHours entry hours
     * @param parkedSpotId parked spot id
     * @param systemCode system code
     * @param helmetCount helmet count
     * @param deliveryUse delivery use status
     */
    public Motorcycle(String vehicleId, String plateNumber, String ownerName,
            int entryHours, String parkedSpotId, String systemCode,
            int helmetCount, boolean deliveryUse) {
        super(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode);
        setHelmetCount(helmetCount);
        setDeliveryUse(deliveryUse);
    }

    public int getHelmetCount() {
        return helmetCount;
    }

    public void setHelmetCount(int helmetCount) {
        if (helmetCount < 0) {
            throw new IllegalArgumentException("Helmet count cannot be negative");
        }
        this.helmetCount = helmetCount;
    }

    public boolean isDeliveryUse() {
        return deliveryUse;
    }

    public void setDeliveryUse(boolean deliveryUse) {
        this.deliveryUse = deliveryUse;
    }

    /**
     * @return vehicle type
     */
    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        double fee = getEntryHours() * (BASE_RATE * 0.50);
        if (deliveryUse) {
            fee = fee + 5.0;
        }
        return fee;
    }

    // Shows motorcycle details.
    @Override
    public void showDetails() {
        System.out.println("Helmet count: " + getHelmetCount());
    }

    @Override
    public String toString() {
        return String.format("%s\nHelmet Count: %d\nDelivery Use: %b",
                super.toString(), getHelmetCount(), isDeliveryUse());
    }
}
