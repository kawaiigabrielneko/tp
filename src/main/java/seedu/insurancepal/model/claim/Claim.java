package seedu.insurancepal.model.claim;

import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person's claim in the address book.
 * Guarantees: immutable
 */
public class Claim {
    private final Title title;
    private final Description description;
    private final Status status;

    /**
     * Constructs a {@code Claim}
     *
     * @param title A valid title
     * @param description A valid description
     * @param status A valid status
     */
    public Claim(Title title, Description description, Status status) {
        requireAllNonNull(title, description, status);
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Claim: "
                + getTitle()
                + "; Description: "
                + getDescription()
                + "; Status: "
                + getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Claim)) {
            return false;
        }

        Claim otherClaim = (Claim) o;
        return this.title.equals(otherClaim.title)
                && this.description.equals(otherClaim.description)
                && this.status.equals(otherClaim.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status);
    }
}
