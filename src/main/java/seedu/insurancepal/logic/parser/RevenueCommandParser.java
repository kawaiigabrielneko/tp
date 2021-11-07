package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.commands.RevenueCommand.COMMAND_WORD;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_REVENUE;

import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.logic.commands.RevenueCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.person.Revenue;

public class RevenueCommandParser implements Parser<RevenueCommand> {

    public static final String MESSAGE_USAGE = ": Add revenue to the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be added on with the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REVENUE + "REVENUE (any number up to 2 decimal places)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REVENUE + "100.21 \n"
            + "Take note that the final sum value for REVENUE cannot be negative!";

    public static final String INVALID_REVENUE_COMMAND_FORMAT = MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_USAGE;
    /**
     * Parses the given {@code String} of arguments in the context of the RevenueCommand
     * and returns a RevenueCommand object for execution.
     *
     * @param args Command input by the user to be parsed.
     * @return RevenueCommand object for execution based on the command input given.
     * @throws ParseException If the user input does not conform the expected format
     */
    public RevenueCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVENUE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(INVALID_REVENUE_COMMAND_FORMAT,
                    COMMAND_WORD), ive);
        }

        Revenue revenue = ParserUtil.parseRevenue(argMultimap.getValue(PREFIX_REVENUE).get());

        return new RevenueCommand(index, revenue);
    }
}
