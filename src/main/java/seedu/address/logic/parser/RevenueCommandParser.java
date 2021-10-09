package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVENUE;

import seedu.address.commons.core.Money;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RevenueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Revenue;

public class RevenueCommandParser implements Parser<RevenueCommand>{

    public RevenueCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVENUE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RevenueCommand.COMMAND_WORD), ive);
        }

        String revenueText = argMultimap.getValue(PREFIX_REVENUE).orElse("");
        Money revenue = new Money(Float.parseFloat(revenueText));

        return new RevenueCommand(index, new Revenue(revenue));
    }
}
