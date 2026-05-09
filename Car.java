package sps;

// A car.
public class Car extends Vehicle {

    private int numberOfDoors;
    private boolean vipMember;

    // empty constructor
    public Car() {
        this("", "", "", 0, "", "", 4, false);
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
     * @param vipMember vip member status
     */
    public Car(String vehicleId, String plateNumber, String ownerName,
            int entryHours, String parkedSpotId, String systemCode,
            int numberOfDoors, boolean vipMember) {
        super(vehicleId, plateNumber, ownerName, entryHours, parkedSpotId, systemCode);
        setNumberOfDoors(numberOfDoors);
        setVipMember(vipMember);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 0) {
            throw new IllegalArgumentException("Number of doors cannot be negative");
        }
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isVipMember() {
        return vipMember;
    }

    public void setVipMember(boolean vipMember) {
        this.vipMember = vipMember;
    }

    /**
     * @return true if the car is eligible for vip discount
     */
    public boolean isVipEligible() {
        return vipMember && getEntryHours() >= 2;
    }

    /**
     * @return vehicle type
     */
    @Override
    public String getVehicleType() {
        return "Car";
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        double fee = getEntryHours() * BASE_RATE;
        if (vipMember) {
            fee = fee * 0.90;
        }
        return fee;
    }

    // Shows car details.
    @Override
    public void showDetails() {
        System.out.println("Car doors: " + getNumberOfDoors());
    }

    @Override
    public String toString() {
        return String.format("%s\nNumber Of Doors: %d\nVIP Member: %b",
                super.toString(), getNumberOfDoors(), isVipMember());
    }
}
