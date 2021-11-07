package seedu.insurancepal.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;
import seedu.insurancepal.model.claim.Title;

class JsonAdaptedClaim {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Claim's %s field is missing!";

    private final String title;
    private final String description;
    private final String status;

    @JsonCreator
    public JsonAdaptedClaim(@JsonProperty("title") String title,
                            @JsonProperty("description") String description,
                            @JsonProperty("status") String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public JsonAdaptedClaim(Claim claim) {
        this.title = claim.getTitle().toString();
        this.description = claim.getDescription().toString();
        this.status = claim.getStatus().toString();
    }

    public Claim toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        final Description modelDescription = new Description(description);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }

        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }

        final Status modelStatus = new Status(status);

        return new Claim(modelTitle, modelDescription, modelStatus);
    }
}
