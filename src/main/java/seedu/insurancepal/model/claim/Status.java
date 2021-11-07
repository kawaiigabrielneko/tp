package seedu.insurancepal.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Claim's status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */

public class Status {
    public static final String MESSAGE_CONSTRAINTS = "A status is limited to the following, with any capitalization: "
            + "\"pending\", \"completed\"";
    private final String status;

    private enum StatusState {
        PENDING, COMPLETED
    }

    /**
     * Constructs a {@Code Status}
     *
     * @param status A valid status
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Returns if a given string is valid
     */
    public static boolean isValidStatus(String test) {
        for (StatusState state : StatusState.values()) {
            if (state.name().equals(test.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.status.toUpperCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && this.status.equalsIgnoreCase(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(status.toUpperCase());
    }
}
