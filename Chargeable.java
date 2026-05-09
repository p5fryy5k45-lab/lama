package sps;

// Parking fee contract.
public interface Chargeable {

    public static final double BASE_RATE = 10.0;

    /**
     * @return calculated parking fee
     */
    public abstract double calculateParkingFee();

    /**
     * @return receipt information
     */
    public abstract String generateReceipt();
}
