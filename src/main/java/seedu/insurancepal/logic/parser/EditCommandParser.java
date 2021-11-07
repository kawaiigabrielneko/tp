package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_INSURANCE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_REVENUE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.EditCommand;
import seedu.insurancepal.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_REVENUE,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_INSURANCE, PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_REVENUE).isPresent()) {
            Revenue revenue = ParserUtil.parseRevenue(argMultimap.getValue(PREFIX_REVENUE).get());
            checkForNegativeRevenue(editPersonDescriptor, revenue);
            checkForOverflowRevenue(editPersonDescriptor, revenue);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseInsurancesForEdit(argMultimap.getAllValues(PREFIX_INSURANCE))
                .ifPresent(editPersonDescriptor::setInsurances);
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editPersonDescriptor.setNote(ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get()));
        }
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        requireNonNull(tags);

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> insurances} into a {@code Set<Insurance>}
     * if {@code insurances} is non-empty.
     * If {@code insurances} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Insurance>} containing zero insurances.
     */
    private Optional<Set<Insurance>> parseInsurancesForEdit(Collection<String> insurances)
            throws ParseException {
        assert insurances != null;

        if (insurances.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> insuranceSet = insurances.size() == 1 && insurances.contains("")
                ? Collections.emptySet() : insurances;
        return Optional.of(ParserUtil.parseInsurances(insuranceSet));
    }

    private void checkForNegativeRevenue(EditPersonDescriptor editPersonDescriptor, Revenue revenue)
            throws ParseException {
        if (revenue.isValidResultingRevenue()) {
            editPersonDescriptor.setRevenue(revenue);
        } else {
            throw new ParseException(Revenue.MESSAGE_CONSTRAINTS);
        }
    }

    private void checkForOverflowRevenue(EditPersonDescriptor editPersonDescriptor, Revenue revenue)
        throws ParseException {
        if (!revenue.isMaxRevenue()) {
            editPersonDescriptor.setRevenue(revenue);
        } else {
            throw new ParseException(Revenue.MESSAGE_CONSTRAINTS);
        }
    }

}
