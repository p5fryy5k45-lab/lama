package sps;

// A truck.
public class Truck extends Vehicle {

    private double loadWeight;
    private boolean commercialUse;

    // empty constructor
    public Truck() {
        this("", "", "", 0, "", "", 0.0, false);
    }

    /**
     * Full constructor
     * @param vehicleId vehicle id
     * @param plateNumber plate number
     * @param ownerName owner name
     * @param entryHours entry hours
     * @param parkedSpotId parked spot id
     * @param systemCode system code
     * @param loadWeight load weight
     * @param commercialUse commercial use status
     */
    public Truck(String vehicleId, String plateNumber, String ownerName,
            int entryHours, String parkedSpotId, String systemCode,
            double loadWeight, boolean commercialUse) {
        super(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode);
        setLoadWeight(loadWeight);
        setCommercialUse(commercialUse);
    }

    public double getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(double loadWeight) {
        if (loadWeight < 0) {
            throw new IllegalArgumentException("Load weight cannot be negative");
        }
        this.loadWeight = loadWeight;
    }

    public boolean isCommercialUse() {
        return commercialUse;
    }

    public void setCommercialUse(boolean commercialUse) {
        this.commercialUse = commercialUse;
    }

    /**
     * @return vehicle type
     */
    @Override
    public String getVehicleType() {
        return "Truck";
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        double fee = getEntryHours() * (BASE_RATE * 2.0);
        if (commercialUse) {
            fee = fee + (loadWeight * 0.25);
        }
        return fee;
    }

    // Shows truck details.
    @Override
    public void showDetails() {
        System.out.println("Load weight: " + getLoadWeight());
    }

    @Override
    public String toString() {
        return String.format("%s\nLoad Weight: %.2f\nCommercial Use: %b",
                super.toString(), getLoadWeight(), isCommercialUse());
    }
}
