package seedu.insurancepal.logic.parser;

import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_DESCRIPTION_DESC_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_STATUS_DESC_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_TITLE_DESC_AMY;
import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.insurancepal.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.logic.commands.ClaimCommand;
import seedu.insurancepal.testutil.ClaimBuilder;

public class ClaimCommandParserTest {
    private final ClaimCommandParser parser = new ClaimCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        assertParseSuccess(parser, "1" + CLAIM_TITLE_DESC_AMY
                + CLAIM_DESCRIPTION_DESC_AMY + CLAIM_STATUS_DESC_AMY,
                new ClaimCommand(INDEX_FIRST_PERSON, new ClaimBuilder(CLAIM_AMY).buildEditClaimDescriptor()));
    }

    @Test
    public void parse_titleFieldMissing_throwsParseException() {
        assertParseFailure(parser, "1" + CLAIM_DESCRIPTION_DESC_AMY + CLAIM_STATUS_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexMissing_throwsParseException() {
        assertParseFailure(parser, CLAIM_TITLE_DESC_AMY + CLAIM_DESCRIPTION_DESC_AMY + CLAIM_STATUS_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a" + CLAIM_TITLE_DESC_AMY + CLAIM_DESCRIPTION_DESC_AMY + CLAIM_STATUS_DESC_AMY,
                MESSAGE_INVALID_INDEX);
    }
}
