package seedu.insurancepal.commons.core;

public class Money {
    private static final float CONVERT_BETWEEN_CURRENCY = 100f;
    private long cents;

    public Money(float dollars) {
        this.cents = (long) (dollars * CONVERT_BETWEEN_CURRENCY);
    }

    public long getCents() {
        return this.cents;
    }

    public float getInDollars() {
        return (this.cents / CONVERT_BETWEEN_CURRENCY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && (cents == ((Money) other).getCents())); // state check
    }

    @Override
    public String toString() {
        return "S$" + String.format("%.02f", this.getInDollars());
    }

    /**
     * Returns the user input for revenue.
     *
     * @return String input by user for revenue field.
     */
    public String stringInputByUser() {
        return String.valueOf(this.getInDollars());
    }
}
