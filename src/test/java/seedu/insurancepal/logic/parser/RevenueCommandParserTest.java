package seedu.insurancepal.logic.parser;

import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.insurancepal.logic.parser.RevenueCommandParser.INVALID_REVENUE_COMMAND_FORMAT;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.logic.commands.RevenueCommand;
import seedu.insurancepal.model.person.Revenue;

public class RevenueCommandParserTest {

    private RevenueCommandParser parser = new RevenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(INVALID_REVENUE_COMMAND_FORMAT,
                RevenueCommand.COMMAND_WORD));
    }

    @Test
    public void parse_validArgs_returnsRevenueCommand() {
        // no leading and trailing whitespaces
        RevenueCommand expectedRevenueCommand =
                new RevenueCommand(INDEX_FIRST_PERSON, new Revenue(new Money(100.21f)));
        assertParseSuccess(parser, "1 r/100.21", expectedRevenueCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, "1 r10", String.format(INVALID_REVENUE_COMMAND_FORMAT,
                RevenueCommand.COMMAND_WORD));
    }
}
