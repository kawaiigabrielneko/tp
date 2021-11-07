package seedu.insurancepal.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.insurancepal.testutil.Assert.assertThrows;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;
import seedu.insurancepal.model.claim.Title;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INSURANCE = "Money";
    private static final String INVALID_CLAIM_TITLE = "@Title ";
    private static final String INVALID_CLAIM_DESCRIPTION = " &*&*&*&*";
    private static final String INVALID_CLAIM_STATUS = "isCompleted";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_INSURANCE_TYPE_1 = "Life";
    private static final String VALID_INSURANCE_TYPE_2 = "Health";
    private static final String VALID_INSURANCE_TYPE_ANY_CAPS = "gENEraL";
    private static final String VALID_INSURANCE_BRAND_1 = "NTUC";
    private static final String VALID_INSURANCE_BRAND_2 = "Aviva";
    private static final String VALID_CLAIM_TITLE = "Title";
    private static final String VALID_CLAIM_DESCRIPTION = "Description description.";
    private static final String VALID_CLAIM_STATUS = "Completed";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseDescription_null_throwsException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
    }

    @Test
    public void parseDescription_validDescription_returnDescription() throws Exception {
        Description actualDescription = ParserUtil.parseDescription(VALID_CLAIM_DESCRIPTION);
        Description expectedDescription = new Description(VALID_CLAIM_DESCRIPTION);

        assertEquals(actualDescription, expectedDescription);
    }

    @Test
    public void parseDescription_invalidDescription_throwsException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_CLAIM_DESCRIPTION));
    }

    @Test
    public void parseTitle_null_throwsException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle(null));
    }

    @Test
    public void parseTitle_validTitle_returnTitle() throws Exception {
        Title actualTitle = ParserUtil.parseTitle(VALID_CLAIM_TITLE);
        Title expectedTitle = new Title(VALID_CLAIM_TITLE);

        assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void parseTitle_invalidTitle_throwsException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_CLAIM_TITLE));
    }

    @Test
    public void parseStatus_null_throwsException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus(null));
    }

    @Test
    public void parseStatus_validStatus_returnStatus() throws Exception {
        Status actualStatus = ParserUtil.parseStatus(VALID_CLAIM_STATUS);
        Status expectedStatus = new Status(VALID_CLAIM_STATUS);

        assertEquals(actualStatus, expectedStatus);
    }

    @Test
    public void parseStatus_invalidStatus_throwsException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_CLAIM_STATUS));
    }

    @Test
    public void parseInsurance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInsurance(null));
    }

    @Test
    public void parseInsurance_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInsurance(INVALID_INSURANCE));
    }

    @Test
    public void parseInsurance_validValue_returnsTag() throws Exception {
        Insurance expectedInsurance = Insurance.of(VALID_INSURANCE_TYPE_1, VALID_INSURANCE_BRAND_1);
        assertEquals(expectedInsurance, ParserUtil.parseInsurance(
                VALID_INSURANCE_TYPE_1 + WHITESPACE + VALID_INSURANCE_BRAND_1));
    }

    @Test
    public void parseInsurance_validValueAnyCaps_returnsTag() throws Exception {
        Insurance expectedInsurance = Insurance.of(VALID_INSURANCE_TYPE_ANY_CAPS, VALID_INSURANCE_BRAND_2);
        assertEquals(expectedInsurance, ParserUtil.parseInsurance(
                VALID_INSURANCE_TYPE_ANY_CAPS + WHITESPACE + VALID_INSURANCE_BRAND_2));
    }

    @Test
    public void parseInsurances_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInsurances(null));
    }

    @Test
    public void parseInsurances_collectionWithInvalidInsurances_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInsurances(
                Arrays.asList(VALID_INSURANCE_BRAND_1 + " " + VALID_INSURANCE_BRAND_1,
                        INVALID_INSURANCE + " " + VALID_INSURANCE_BRAND_2)));
    }

    @Test
    public void parseInsurances_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseInsurances(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseInsurances_collectionWithValidInsurances_returnsInsuranceSet() throws Exception {
        Set<Insurance> actualInsuranceSet = ParserUtil.parseInsurances(
                Arrays.asList(VALID_INSURANCE_TYPE_1 + " " + VALID_INSURANCE_BRAND_1,
                        VALID_INSURANCE_TYPE_2 + " " + VALID_INSURANCE_BRAND_2));
        Set<Insurance> expectedInsuranceSet = new HashSet<Insurance>(
                Arrays.asList(Insurance.of(VALID_INSURANCE_TYPE_1, VALID_INSURANCE_BRAND_1),
                        Insurance.of(VALID_INSURANCE_TYPE_2, VALID_INSURANCE_BRAND_2)));

        assertEquals(expectedInsuranceSet, actualInsuranceSet);
    }
}
