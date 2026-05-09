package sps;

// Parent vehicle class.
public abstract class Vehicle implements Chargeable {

    private final String VEHICLE_ID;
    private String plateNumber;
    private String ownerName;
    private int entryHours;
    private String parkedSpotId;
    private static int totalVehicles;
    private String systemCode;

    // empty constructor
    public Vehicle() {
        this("", "", "", 0, "", "");
    }

    /**
     * Full constructor
     * @param vehicleId vehicle id
     * @param plateNumber plate number
     * @param ownerName owner name
     * @param entryHours entry hours
     * @param parkedSpotId parked spot id
     * @param systemCode system code
     */
    public Vehicle(String vehicleId, String plateNumber, String ownerName,
            int entryHours, String parkedSpotId, String systemCode) {
        this.VEHICLE_ID = vehicleId;
        setPlateNumber(plateNumber);
        setOwnerName(ownerName);
        setEntryHours(entryHours);
        setParkedSpotId(parkedSpotId);
        setSystemCode(systemCode);
        totalVehicles++;
    }

    public String getVehicleId() {
        return VEHICLE_ID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber.trim().toUpperCase();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName.trim();
    }

    public int getEntryHours() {
        return entryHours;
    }

    public void setEntryHours(int entryHours) {
        if (entryHours < 0) {
            throw new IllegalArgumentException("Entry hours cannot be negative");
        }
        this.entryHours = entryHours;
    }

    public String getParkedSpotId() {
        return parkedSpotId;
    }

    public void setParkedSpotId(String parkedSpotId) {
        this.parkedSpotId = parkedSpotId.trim().toUpperCase();
    }

    public static int getTotalVehicles() {
        return totalVehicles;
    }

    public static void setTotalVehicles(int totalVehicles) {
        Vehicle.totalVehicles = totalVehicles;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode.trim().toUpperCase();
    }

    /**
     * @return vehicle type
     */
    public abstract String getVehicleType();

    /**
     * @return calculated parking fee
     */
    @Override
    public abstract double calculateParkingFee();

    /**
     * @return receipt information
     */
    @Override
    public String generateReceipt() {
        return String.format("Vehicle ID: %s\nPlate Number: %s\nVehicle Type: %s\nFee: %.2f",
                getVehicleId(), getPlateNumber(), getVehicleType(), calculateParkingFee());
    }

    // Shows vehicle details.
    public abstract void showDetails();

    @Override
    public String toString() {
        return String.format("Vehicle ID: %s\nPlate Number: %s\nOwner Name: %s\nEntry Hours: %d\nParked Spot ID: %s\nSystem Code: %s",
                getVehicleId(), getPlateNumber(), getOwnerName(), getEntryHours(),
                getParkedSpotId(), getSystemCode());
    }
}
