package sps;

// An electric car.
public class ElectricCar extends Car {

    private double batteryLevel;
    private boolean chargingRequired;

    // empty constructor
    public ElectricCar() {
        this("", "", "", 0, "", "", 4, false, 0.0, false);
    }

    /**
     * Full constructor
     * @param vehicleId vehicle id
     * @param plateNumber plate number
     * @param ownerName owner name
     * @param entryHours entry hours
     * @param parkedSpotId parked spot id
     * @param systemCode system code
     * @param numberOfDoors number of doors
     * @param vipMember viP member status
     * @param batteryLevel battery level
     * @param chargingRequired charging required status
     */
    public ElectricCar(String vehicleId, String plateNumber, String ownerName,
            int entryHours, String parkedSpotId, String systemCode,
            int numberOfDoors, boolean vipMember, double batteryLevel,
            boolean chargingRequired) {
        super(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId,
                systemCode, numberOfDoors, vipMember);
        setBatteryLevel(batteryLevel);
        setChargingRequired(chargingRequired);
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        if (batteryLevel < 0) {
            throw new IllegalArgumentException("Battery level cannot be negative");
        }
        this.batteryLevel = batteryLevel;
    }

    public boolean isChargingRequired() {
        return chargingRequired;
    }

    public void setChargingRequired(boolean chargingRequired) {
        this.chargingRequired = chargingRequired;
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        double fee = super.calculateParkingFee();
        if (chargingRequired) {
            fee = fee + 15.0;
        }
        return fee;
    }

    // Shows electric car details.
    @Override
    public void showDetails() {
        System.out.println("Battery level: " + getBatteryLevel());
    }

    @Override
    public String toString() {
        return String.format("%s\nBattery Level: %.2f\nCharging Required: %b",
                super.toString(), getBatteryLevel(), isChargingRequired());
    }
}
