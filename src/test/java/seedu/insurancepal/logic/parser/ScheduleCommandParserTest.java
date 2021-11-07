package seedu.insurancepal.logic.parser;

import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.insurancepal.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.logic.commands.ScheduleCommand;
import seedu.insurancepal.model.appointment.Appointment;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsScheduleCommand() {
        assertParseSuccess(parser, "1 m/", new ScheduleCommand(INDEX_FIRST_PERSON, new Appointment("")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }
}
