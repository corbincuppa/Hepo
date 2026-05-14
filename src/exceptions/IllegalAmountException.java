package exceptions;

/**
 * A class for signaling illegal amounts.
 *
 * @version 1.0
 * @author  Adelina Vozianu
 * @author  Boglárka Csorba-Vitus
 */
public class IllegalAmountException extends RuntimeException {

    /**
     * Variable registering the amount of this illegal amount exception.
     */
    private final int amount;

    /**
     * The Java API strongly recommends to explicitly define a version
     * number for classes that implement the interface Serializable.
     * At this stage, that aspect is of no concern to us.
     */
    private static final long serialVersionUID = 2003001L;


    /**
     * Initialize this new illegal amount exception with given amount.
     *
     * @param   amount
     *          The amount for this new illegal amount exception.
     * @post    The amount for this new illegal amount exception
     *          is equal to the given amount.
     *          | new.getName().equals(amount)
     */
    public IllegalAmountException(int amount) {
        this.amount = amount;
    }

    /**
     * Return the amount of this illegal amount exception.
     */
    public int getAmount() {
        return this.amount;
    }

}