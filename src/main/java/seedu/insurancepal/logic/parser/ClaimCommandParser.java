package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.logic.commands.ClaimCommand;
import seedu.insurancepal.logic.commands.ClaimCommand.EditClaimDescriptor;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;


public class ClaimCommandParser implements Parser<ClaimCommand> {

    @Override
    public ClaimCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_STATUS);

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(e.getMessage(), e);
        }

        EditClaimDescriptor editClaimDescriptor = new EditClaimDescriptor(
                ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NAME).get()));

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            Description description = ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get());
            editClaimDescriptor.setDescription(description);
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).orElse(""));
            editClaimDescriptor.setStatus(status);
        }

        return new ClaimCommand(index, editClaimDescriptor);
    }
}
