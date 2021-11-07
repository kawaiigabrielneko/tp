package seedu.insurancepal.model.claim;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.util.AppUtil.checkArgument;

import java.util.Objects;



/**
 * Represents a Claim's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */

public class Title implements Comparable<Title> {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Space}]*";
    public static final String MESSAGE_CONSTRAINTS = "A title should only contain alphanumeric characters.\n"
            + "A title is case-sensitive\n"
            + "A title should not be blank";

    private final String title;

    /**
     * Constructs a {@Code Title}.
     *
     * @param title A valid title
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && this.title.equals(((Title) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public int compareTo(Title o) {
        return this.title.compareTo(o.title);
    }
}
