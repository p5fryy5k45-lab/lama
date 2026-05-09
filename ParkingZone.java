package sps;

// A parking zone.
public class ParkingZone {

    private final String ZONE_ID;
    private String zoneName;
    private int capacity;
    private int occupiedCount;
    private ParkingSpot[] spots;

    // empty constructor
    public ParkingZone() {
        this("", "", 0, 0, null);
    }

    /**
     * Full constructor
     * @param zoneId zone id
     * @param zoneName zone name
     * @param capacity capacity
     * @param occupiedCount occupied count
     * @param spots parking spots
     */
    public ParkingZone(String zoneId, String zoneName, int capacity,
            int occupiedCount, ParkingSpot[] spots) {
        this.ZONE_ID = zoneId;
        setZoneName(zoneName);
        setCapacity(capacity);
        setOccupiedCount(occupiedCount);
        setSpots(spots);
    }

    public String getZoneId() {
        return ZONE_ID;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName.trim();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
    }

    public int getOccupiedCount() {
        return occupiedCount;
    }

    public void setOccupiedCount(int occupiedCount) {
        if (occupiedCount < 0) {
            throw new IllegalArgumentException("Occupied count cannot be negative");
        }
        this.occupiedCount = occupiedCount;
    }

    public ParkingSpot[] getSpots() {
        return spots;
    }

    public void setSpots(ParkingSpot[] spots) {
        this.spots = spots;
    }

    /**
     * @param vehicleType vehicle type
     * @return available parking spot
     */
    public ParkingSpot findAvailableSpot(String vehicleType) {
        if (spots == null) {
            return null;
        }

        for (int i = 0; i < spots.length; i++) {
            if (spots[i] != null && spots[i].assignVehicle(vehicleType)) {
                occupiedCount++;
                return spots[i];
            }
        }

        return null;
    }

    /**
     * @return available spot count
     */
    public int getAvailableCount() {
        return capacity - occupiedCount;
    }

    @Override
    public String toString() {
        return String.format("Zone ID: %s\nZone Name: %s\nCapacity: %d\nOccupied Count: %d\nAvailable Count: %d",
                getZoneId(), getZoneName(), getCapacity(), getOccupiedCount(),
                getAvailableCount());
    }
}
