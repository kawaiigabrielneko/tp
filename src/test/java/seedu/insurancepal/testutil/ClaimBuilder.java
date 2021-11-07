package seedu.insurancepal.testutil;

import seedu.insurancepal.logic.commands.ClaimCommand.EditClaimDescriptor;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;
import seedu.insurancepal.model.claim.Title;

/**
 * A utility class to help with building Claim objects.
 */
public class ClaimBuilder {
    public static final String DEFAULT_TITLE = "Heart Surgery";
    public static final String DEFAULT_DESCRIPTION = "by Dr. Chan Keng Song at TTSH";
    public static final String DEFAULT_STATUS = "Pending";

    private Title title;
    private Description description;
    private Status status;

    /**
     * Constructor for a default claim
     */
    public ClaimBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Constructor for a specific claim
     *
     * @param title A valid title
     * @param description A valid description
     * @param status A valid status
     */
    public ClaimBuilder(String title, String description, String status) {
        this.title = new Title(title);
        this.description = new Description(description);
        this.status = new Status(status);
    }

    /**
     * Constructor that copies a claim into a claim builder
     *
     * @param claim A valid claim
     */
    public ClaimBuilder(Claim claim) {
        this.title = claim.getTitle();
        this.description = claim.getDescription();
        this.status = claim.getStatus();

    }

    /**
     * Sets the {@code Title} of the {@code Claim} that we are building.
     *
     * @param title A valid title
     */
    public ClaimBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Claim} that we are building.
     *
     * @param description A valid description
     */
    public ClaimBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Claim} that we are building.
     *
     * @param status A valid status
     */
    public ClaimBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Build the claim and returns it.
     *
     * @return A valid claim with the given attributes
     */
    public Claim buildClaim() {
        return new Claim(title, description, status);
    }

    /**
     * Build the {@Code EditClaimDescriptor} and returns it
     *
     * @return A valid EditClaimDescriptor with the given attributes
     */
    public EditClaimDescriptor buildEditClaimDescriptor() {
        EditClaimDescriptor editClaimDescriptor = new EditClaimDescriptor(title);
        editClaimDescriptor.setDescription(description);
        editClaimDescriptor.setStatus(status);
        return editClaimDescriptor;
    }
}
