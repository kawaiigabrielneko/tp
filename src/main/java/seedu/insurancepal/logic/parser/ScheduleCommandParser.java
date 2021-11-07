package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_MEETING;

import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.ScheduleCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.appointment.Appointment;

public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns an ScheduleCommand object for execution.
     * @throws seedu.insurancepal.logic.parser.exceptions.ParseException if user input is not the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEETING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_MEETING).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        Appointment newAppointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_MEETING).get());

        return new ScheduleCommand(index, newAppointment);
    }

}
