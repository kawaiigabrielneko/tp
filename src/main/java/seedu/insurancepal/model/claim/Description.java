package seedu.insurancepal.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.util.AppUtil.checkArgument;

import java.util.Objects;



/**
 * Represents a Claim's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */

public class Description {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Punct}\\p{Blank}]*";
    public static final String MESSAGE_CONSTRAINTS = "A description can only contain alphanumeric characters, "
            + "blank spaces and punctuations.\n"
            + "A description cannot be blank.";

    private final String description;

    /**
     * Constructs a {@Code Description}
     *
     * @param description A valid description
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
