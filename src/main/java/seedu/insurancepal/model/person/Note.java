package seedu.insurancepal.model.person;

import static java.util.Objects.requireNonNull;

//@author xianlinc-reused
//Reused from https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html

/**
 * Represents a Note of the person in the InsurancePal.
 * Guarantees: immutable;
 */
public class Note {
    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs an {@code Note}.
     *
     * @param note A valid Note.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && value.equals(((Note) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
