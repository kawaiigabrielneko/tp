package seedu.insurancepal.testutil;

import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_INSURANCE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_REVENUE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.insurancepal.logic.commands.AddCommand;
import seedu.insurancepal.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getInsurances().stream().forEach(
            i -> sb.append(PREFIX_TAG + i.getType().getTypeName() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRevenue().ifPresent(revenue -> sb.append(PREFIX_REVENUE).append(revenue.stringInputByUser())
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNote().ifPresent(note -> sb.append(PREFIX_NOTE).append(note.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getInsurances().isPresent()) {
            Set<Insurance> insurances = descriptor.getInsurances().get();
            if (insurances.isEmpty()) {
                sb.append(PREFIX_INSURANCE).append(" ");
            } else {
                insurances.forEach(i -> sb.append(PREFIX_INSURANCE)
                        .append(i.getType().getTypeName()).append(" "));
            }
        }

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code Person}'s note
     */
    public static String getNoteDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NOTE).append(person.getNote().value);
        return sb.toString();
    }
}
