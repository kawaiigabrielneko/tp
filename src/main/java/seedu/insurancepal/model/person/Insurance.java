package seedu.insurancepal.model.person;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.insurancepal.commons.exceptions.IllegalValueException;

/**
 * Represents an insurance policy that a person can own.
 */
public class Insurance {
    /** The suffix that is added to the end of each insurance type */
    public static final String INSURANCE_SUFFIX = " ins.";
    public static final String INVALID_ARG_SUFFIX = " is not an insurance type";
    public static final String LONG_INSURANCE_PREFIX = "Insurance '";
    public static final String LONG_INSURANCE_SUFFIX = "' is longer than 20 characters";
    public static final String MESSAGE_CONSTRAINTS =
            "Insurance type must be one of the following: "
            + String.join(", ", Arrays.stream(InsuranceType.values())
                    .map(InsuranceType::getTypeName)
                    .collect(Collectors.toList()));
    public static final int MAX_INSURANCE_LENGTH = 24;

    private InsuranceType type;
    private String brand;

    /**
     * Class constructor
     * @param type The type of this insurance
     * @param brand The name of the brand of the insurance
     */
    public Insurance(InsuranceType type, String brand) {
        this.type = type;
        this.brand = brand;
    }

    /**
     * Returns an Insurance with the given brand
     * @param insuranceName The type of the Insurance to return
     * @param brand The brand of Insurance to return
     * @return The Insurance with the supplied brand
     */
    public static Insurance of(String insuranceName, String brand) throws IllegalValueException {
        for (InsuranceType type : InsuranceType.values()) {
            if (type.getTypeName().equalsIgnoreCase(insuranceName)) {
                Insurance insurance = new Insurance(type, brand);
                if (insurance.toString().length() > MAX_INSURANCE_LENGTH) {
                    throw new IllegalValueException(
                            LONG_INSURANCE_PREFIX + insurance + LONG_INSURANCE_SUFFIX);
                }
                return insurance;
            }
        }
        throw new IllegalValueException(insuranceName + INVALID_ARG_SUFFIX);
    }

    /**
     * Gets the type of this Insurance
     * @return {@code type}
     */
    public InsuranceType getType() {
        return type;
    }

    /**
     * Returns a String representing the type this Insurance is
     * @return @code{type.getTypeName()}
     */
    public String getTypeName() {
        return type.getTypeName();
    }

    /**
     * Returns the brand of this Insurance
     * @return This Insurance's brand
     */
    public String getBrand() {
        return brand;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Insurance)) {
            return false;
        } else {
            Insurance insuranceObj = (Insurance) obj;
            return this.type.equals(insuranceObj.type)
                    && this.brand.equals(insuranceObj.brand);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand);
    }

    @Override
    public String toString() {
        return type.getTypeName() + INSURANCE_SUFFIX + ": " + brand;
    }
}
