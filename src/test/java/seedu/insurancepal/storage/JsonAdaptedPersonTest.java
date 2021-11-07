package seedu.insurancepal.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.insurancepal.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.insurancepal.testutil.Assert.assertThrows;
import static seedu.insurancepal.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INSURANCE = "College";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_REVENUE = String.valueOf(BENSON.getRevenue().value.getInDollars());
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedInsurance> VALID_INSURANCES = BENSON.getInsurances().stream()
            .map(JsonAdaptedInsurance::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedClaim> VALID_CLAIMS = BENSON.getClaims().stream()
            .map(JsonAdaptedClaim::new)
            .collect(Collectors.toList());
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final String VALID_MEETING = BENSON.getAppointment().toString();
    private static final String VALID_BRAND = "NTUC";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                        VALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                VALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_REVENUE, VALID_ADDRESS,
                VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_REVENUE,
                VALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_REVENUE, VALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_REVENUE,
                VALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                        INVALID_ADDRESS, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                null, VALID_TAGS, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                        VALID_ADDRESS, invalidTags, VALID_INSURANCES, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidInsurances_throwsIllegalValueException() {
        List<JsonAdaptedInsurance> invalidInsurances = new ArrayList<>(VALID_INSURANCES);
        invalidInsurances.add(new JsonAdaptedInsurance(INVALID_INSURANCE, VALID_BRAND));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_REVENUE,
                VALID_ADDRESS, VALID_TAGS, invalidInsurances, VALID_NOTE, VALID_MEETING, VALID_CLAIMS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
