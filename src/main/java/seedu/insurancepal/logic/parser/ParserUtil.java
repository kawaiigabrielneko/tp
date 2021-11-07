package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.logic.parser.RevenueCommandParser.INVALID_REVENUE_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.commons.util.StringUtil;
import seedu.insurancepal.logic.commands.RevenueCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;
import seedu.insurancepal.model.claim.Title;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String revenue} into a {@code Revenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code revenue} is invalid.
     */
    public static Revenue parseRevenue(String revenue) throws ParseException {
        requireNonNull(revenue);
        String trimmedRevenue = revenue.trim();
        if (!Revenue.isValidRevenue(trimmedRevenue)) {
            throw new ParseException(String.format(INVALID_REVENUE_COMMAND_FORMAT,
                    RevenueCommand.COMMAND_WORD));
        }
        float number = Float.valueOf(trimmedRevenue);

        return new Revenue(new Money(number));
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String insurance} into an {@code Insurance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code insurance} is invalid.
     */
    public static Insurance parseInsurance(String insurance) throws ParseException {
        requireNonNull(insurance);
        String trimmedInsurance = insurance.trim();
        String[] insuranceTokens = trimmedInsurance.split("\\s+", 2);
        String insuranceType = insuranceTokens.length > 0 ? insuranceTokens[0] : "";
        String insuranceName = insuranceTokens.length > 1 ? insuranceTokens[1] : "";
        try {
            return Insurance.of(insuranceType, insuranceName);
        } catch (IllegalValueException exception) {
            throw new ParseException(Insurance.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code Collection<String> insurances} into a {@code Set<Insurance>}.
     */
    public static Set<Insurance> parseInsurances(Collection<String> insurances) throws ParseException {
        requireNonNull(insurances);
        final Set<Insurance> insuranceSet = new HashSet<>();
        for (String insuranceName : insurances) {
            insuranceSet.add(parseInsurance(insuranceName));
        }
        return insuranceSet;
    }

    /**
     * Parses {@code String note} into a {@code Note}
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String meetingDateTime} into a {@code Appointment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Appointment parseAppointment(String meetingDateTime) throws ParseException {
        requireNonNull(meetingDateTime);
        String trimmedDateTime = meetingDateTime.trim();
        if (!Appointment.isValidMeetingTime(trimmedDateTime)) {
            throw new ParseException(Appointment.MESSAGE_CONSTRAINTS);
        }
        return new Appointment(trimmedDateTime);
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if given {@code title} is invalid
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(title);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if given {@code description} is invalid
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!(Description.isValidDescription(trimmedDescription))) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }


    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if given {@code status} is invalid
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(status);
    }
}
