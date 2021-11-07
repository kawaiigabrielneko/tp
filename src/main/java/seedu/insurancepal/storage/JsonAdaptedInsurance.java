package seedu.insurancepal.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.model.person.Insurance;

/**
 * Jackson-friendly version of {@link Insurance}.
 */
class JsonAdaptedInsurance {

    private final String insuranceType;
    private final String insuranceBrand;

    /**
     * Constructs an {@code Insurance} with the given {@code insuranceType}
     * and {@code insuranceBrand}.
     */
    @JsonCreator
    public JsonAdaptedInsurance(@JsonProperty("insuranceType") String insuranceType,
            @JsonProperty("insuranceBrand") String insuranceBrand) {
        this.insuranceType = insuranceType;
        this.insuranceBrand = insuranceBrand;
    }

    /**
     * Converts a given {@code Insurance} into this class for Jackson use.
     */
    public JsonAdaptedInsurance(Insurance source) {
        insuranceType = source.getTypeName();
        insuranceBrand = source.getBrand();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Insurance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted insurance.
     */
    public Insurance toModelType() throws IllegalValueException {
        return Insurance.of(insuranceType, insuranceBrand);
    }

}
