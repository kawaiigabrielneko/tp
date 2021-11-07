package seedu.insurancepal.logic.parser;

import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_INSURANCE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.insurancepal.logic.commands.AddCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_INSURANCE, PREFIX_NOTE);

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Insurance> insuranceList = ParserUtil.parseInsurances(argMultimap.getAllValues(PREFIX_INSURANCE));
        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).orElse(""));
        Person person = new Person(name, phone, email, address, tagList, insuranceList,
                note, new Appointment(""), new HashSet<>());

        return new AddCommand(person);
    }



}
