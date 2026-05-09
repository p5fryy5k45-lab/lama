package sps;

// A payment.
public class Payment implements Chargeable {

    private final String PAYMENT_ID;
    private String paymentMethod;
    private double amountPaid;
    private boolean paid;

    // empty constructor
    public Payment() {
        this("", "", 0.0, false);
    }

    /**
     * Full constructor
     * @param paymentId payment id
     * @param paymentMethod payment method
     * @param amountPaid amount paid
     * @param paid paid status
     */
    public Payment(String paymentId, String paymentMethod, double amountPaid, boolean paid) {
        this.PAYMENT_ID = paymentId;
        setPaymentMethod(paymentMethod);
        setAmountPaid(amountPaid);
        setPaid(paid);
    }

    public String getPaymentId() {
        return PAYMENT_ID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod.trim();
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        if (amountPaid < 0) {
            throw new IllegalArgumentException("Amount paid cannot be negative");
        }
        this.amountPaid = amountPaid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * @param amount amount to pay
     */
    public void completePayment(double amount) {
        setAmountPaid(amount);
        setPaid(true);
    }

    /**
     * @return true if payment is successful
     */
    public boolean isSuccessful() {
        return paid && amountPaid > 0;
    }

    /**
     * @return calculated parking fee
     */
    @Override
    public double calculateParkingFee() {
        return getAmountPaid();
    }

    /**
     * @return receipt information
     */
    @Override
    public String generateReceipt() {
        return String.format("Payment ID: %s\nPayment Method: %s\nAmount Paid: %.2f\nPaid: %b",
                getPaymentId(), getPaymentMethod(), getAmountPaid(), isPaid());
    }

    @Override
    public String toString() {
        return String.format("Payment ID: %s\nPayment Method: %s\nAmount Paid: %.2f\nPaid: %b",
                getPaymentId(), getPaymentMethod(), getAmountPaid(), isPaid());
    }
}
