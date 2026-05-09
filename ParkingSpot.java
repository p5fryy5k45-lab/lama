package sps;

// A parking spot.
public class ParkingSpot {

    private final String SPOT_ID;
    private final String spotRef;
    private String zoneName;
    private String sizeType;
    private boolean occupied;

    // empty constructor
    public ParkingSpot() {
        this("", "", "", false);
    }

    /**
     * Full constructor
     * @param spotId spot id
     * @param zoneName zone name
     * @param sizeType size type
     * @param occupied occupied status
     */
    public ParkingSpot(String spotId, String zoneName, String sizeType, boolean occupied) {
        this.SPOT_ID = spotId;
        setZoneName(zoneName);
        setSizeType(sizeType);
        setOccupied(occupied);
        this.spotRef = generateSpotRef();
    }

    public String getSpotId() {
        return SPOT_ID;
    }

    public String getSpotRef() {
        return spotRef;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName.trim();
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType.trim().toLowerCase();
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * @param vehicleType vehicle type
     * @return true if vehicle can be assigned
     */
    public boolean assignVehicle(String vehicleType) {
        String type = vehicleType.trim().toLowerCase();

        if (occupied) {
            return false;
        }

        if (sizeType.equalsIgnoreCase("large")) {
            occupied = true;
            return true;
        }

        if (sizeType.equalsIgnoreCase("medium")
                && !type.equalsIgnoreCase("truck")) {
            occupied = true;
            return true;
        }

        if (sizeType.equalsIgnoreCase("small")
                && type.equalsIgnoreCase("motorcycle")) {
            occupied = true;
            return true;
        }

        return false;
    }

    // Releases this spot.
    public void releaseSpot() {
        occupied = false;
    }

    /**
     * @return Lab 1.2 style derived reference
     */
    private String generateSpotRef() {
        String zonePart = "";
        if (zoneName != null && zoneName.length() >= 2) {
            zonePart = zoneName.substring(0, 2);
        }

        String idPart = "";
        if (SPOT_ID != null && SPOT_ID.length() >= 2) {
            idPart = SPOT_ID.substring(SPOT_ID.length() - 2);
        }

        return (zonePart + idPart).toUpperCase();
    }

    @Override
    public String toString() {
        return String.format("Spot ID: %s\nSpot Ref: %s\nZone Name: %s\nSize Type: %s\nOccupied: %b",
                getSpotId(), getSpotRef(), getZoneName(), getSizeType(), isOccupied());
    }
}
